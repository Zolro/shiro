package com.zyd.shiro.controller;


import com.zyd.shiro.entity.ArcData;
import com.zyd.shiro.entity.ArcDire;
import com.zyd.shiro.entity.ArcFileType;
import com.zyd.shiro.entity.ArcTitle;
import com.zyd.shiro.framework.object.ResponseVO;
import com.zyd.shiro.service.*;
import com.zyd.shiro.util.ResultUtil;
import com.zyd.shiro.utils.ArcUtils;
import com.zyd.shiro.utils.ExcelUtils;
import com.zyd.shiro.utils.FileUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    private ArcFileTypeService arcFileTypeService;
    @Autowired
    private ArcDireService arcDireService;
    @Autowired
    private ArcTitleService arcTitleService;
    @Autowired
    private ArcDataService arcDataService;
    @Autowired
    private FileService fileService;

    /**
     * excel导入
     * @param
     * @return
     */
    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public ResponseVO folderImport(MultipartFile file) throws Exception {
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.indexOf("."),fileName.length());
        String fileDire =fileName.substring(0,fileName.indexOf("."));
        String direStr = fileName.substring(fileName.indexOf("·")+1,fileName.length());
        String[] direArr = direStr.split("-");
        if(direArr.length>2){
            return ResultUtil.error("只支持二级目录结构!");
        }

        if(suffix.equals(".xlsx")||suffix.equals(".xls")){
            //String fileDire =fileName.substring(0,fileName.indexOf("."));
            String code = fileDire.substring(0,fileDire.indexOf("·"));
            Optional<ArcFileType> arcFileTypeOpt = arcFileTypeService.findDistinctByCode(code);
            if(!arcFileTypeOpt.isPresent()){
                return ResultUtil.error("档案类型不存在！");
            }
            ArcDire arcDire = creDireByFileDire(code,fileDire);
            extrSingleFile(file,arcDire);
            return ResultUtil.success("添加成功！");
        }
        return ResultUtil.error("请输入excel文件!");
    }

    /**
     * excel导出
     * @param
     * @return
     */
    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public ResponseVO export(String ids,long titleId,HttpServletResponse response) {
        ArcTitle arcTitle = arcTitleService.selectOnById(titleId);
        ArcDire arcDire = arcDireService.findById(arcTitle.getFileDire());
        List<ArcData> arcDataList = arcDataService.selectListByIds(ids);
        String[] columnNames = ArcUtils.getTitle(ArcTitle.class,arcTitle.getFileNum(),arcTitle);
        HSSFWorkbook workbook = ExcelUtils.exportExcel(arcDire.getName(), columnNames, arcDataList,arcTitle.getFileNum());
        response.setContentType("application/octet-stream");
        //这后面可以设置导出Excel的名称，此例中名为student.xls
        response.setHeader("Content-disposition", "attachment;filename="+System.currentTimeMillis()+".xls");
        //刷新缓冲
        try {
            response.flushBuffer();
            workbook.write(response.getOutputStream());
            return ResultUtil.success("导出成功！");
        } catch (IOException e) {
            e.printStackTrace();
            log.error("导出失败，类型为[{}]",arcDire.getName(), e);
            return ResultUtil.error(e.getMessage());
        }

    }

    /**
     * 批量文件下载
     *
     * @param ids 文件id集合
     * @return
     */
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public ResponseVO fileMultiDownload(@RequestBody String ids, HttpServletRequest request, HttpServletResponse response) throws Exception {
        fileService.downloadMulti(ids, request, response);
        return ResultUtil.success("下载成功！");
    }


    @RequestMapping(value = "/folder/import", method = RequestMethod.POST)
    public ResponseVO folderImport(@RequestParam("file")MultipartFile file, HttpServletResponse response) {
        response.setCharacterEncoding("utf-8");
        try{
            String relPath = "/"+ FileUtils.UPLOAD_PATH_PREFIX;
            String name = file.getOriginalFilename();
            File newFile = new File(FileUtils.creDire(name));
            file.transferTo(newFile);
            String fileNum = name.substring(0,name.indexOf("."));
            Optional<ArcData> arcDataOpt = arcDataService.findDistinctByFile4(fileNum);
            if(arcDataOpt.isPresent()){
                ArcData arcData = arcDataOpt.get();
                arcData.setFilePath(relPath+name);
                arcDataService.save(arcData);
                return ResultUtil.success("添加成功！");
            }
            Exception e = new Exception();
            log.error("导入失败，文档号[{}]不存在！",fileNum,e);
            return ResultUtil.error(e.getMessage());
        }catch(Exception e){
            log.error("导入失败", e);
            return ResultUtil.error(e.getMessage());
        }

    }





    @SneakyThrows
    private void extrSingleFile(MultipartFile file,ArcDire arcDire){
        InputStream inputStream = file.getInputStream();
        String fileName = file.getOriginalFilename();
        Workbook work = ExcelUtils.getWorkbook(inputStream,fileName);
        if (null == work) {
            System.err.println("创建Excel工作薄为空！");
            throw new Exception();
        }
        ArcTitle arcTitle = arcTitleService.creTitle(work,arcDire.getId());
        arcDataService.getCourseListByExcel(work,arcTitle.getId());
    }

    private ArcDire creDireByFileDire(String code,String fileDire) throws Exception {
        String param = fileDire.substring(fileDire.indexOf("·")+1);
        return initDire(code,param);
    }

    private ArcDire initDire(String code,String param){
        if((param.indexOf("-")==-1)){
            return findOrSaveDireByCodeAndName(code,param);
        }
        String name = param.substring(0,param.indexOf("-"));
        ArcDire arcDire = findOrSaveDireByCodeAndName(code,name);
        return creaTypeCycle(arcDire.getId(),param.substring(param.indexOf("-")+1,param.length()));

    }

    private ArcDire creaTypeCycle(long parId,String param){
        if(param.indexOf("-")==-1){
            return findOrSaveDireByParIdAndName(parId,param);
        }
        String name = param.substring(0,param.indexOf("-"));
        ArcDire arcDire = findOrSaveDireByParIdAndName(parId,name);
        return creaTypeCycle(arcDire.getId(),param.substring(param.indexOf("-")+1,param.length()));
    }

    private ArcDire findOrSaveDireByParIdAndName(long parId,String name){
        Optional<ArcDire> arcDireOpt = arcDireService.findDistinctByNameAndParDire(name,parId);
        if(!arcDireOpt.isPresent()){
            ArcDire arcDire = new ArcDire();
            arcDire.setName(name);
            arcDire.setParDire(parId);
            return arcDireService.save(arcDire);
        }
        return arcDireOpt.get();
    }

    private ArcDire findOrSaveDireByCodeAndName(String code,String name){
        Optional<ArcDire> arcDireOpt = arcDireService.findDistinctByCodeAndName(code,name);
        if(!arcDireOpt.isPresent()){
            ArcDire arcDire = new ArcDire();
            arcDire.setName(name);
            arcDire.setCode(code);
            return arcDireService.save(arcDire);
        }
        return arcDireOpt.get();
    }

}
