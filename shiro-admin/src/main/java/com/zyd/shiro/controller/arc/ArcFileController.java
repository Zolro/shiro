package com.zyd.shiro.controller.arc;



import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zyd.shiro.entity.ArcDire;
import com.zyd.shiro.entity.ArcField;
import com.zyd.shiro.entity.ArcFile;
import com.zyd.shiro.entity.ArcFileType;
import com.zyd.shiro.service.*;
import com.zyd.shiro.vo.FileConditionVO;
import com.zyd.shiro.framework.object.PageResult;
import com.zyd.shiro.framework.object.ResponseVO;
import com.zyd.shiro.util.ResultUtil;
import com.zyd.shiro.utils.ExcelUtil;
import com.zyd.shiro.utils.FileUtils;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@Slf4j
@RequestMapping("/file")
public class ArcFileController {
    @Autowired
    private ArcFileService fileService;

    @Autowired
    private ArcLogService logService;



    @RequestMapping(value = "/folder/import", method = RequestMethod.POST)
    public ResponseVO folderImport(@RequestParam(value="file") MultipartFile[] files) {
        if(files.length==0){
            return ResultUtil.error("请上传文件！");
        }
        try{
            for(MultipartFile file:files){
                String path = file.getOriginalFilename();
                String name = path.substring(path.lastIndexOf("/")+1,path.length());
                String levelNumber = name.substring(0,name.indexOf("."));
                Optional<ArcFile> fileOpt = fileService.findByLevelNumber(levelNumber);
                if(!fileOpt.isPresent()){
                    logService.generate("批量挂接失败 文件级档号["+levelNumber+"]不存在！");
                    return ResultUtil.error("文件级档号["+levelNumber+"]不存在！");
                }
                File newFile = new File(FileUtils.createFilePath(name));
                file.transferTo(newFile);
                ArcFile entFile = fileOpt.get();
                entFile.setIncluding(1);
                entFile.setFilePath("/upload/"+name);
                fileService.edit(entFile);

            }
            logService.generate("批量挂接成功");
            return ResultUtil.success("添加成功！");
        }catch(Exception e){
            log.error("导入失败", e);
            return ResultUtil.error(e.getMessage());
        }
    }


    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public ResponseVO count() {
        return ResultUtil.success(null,fileService.countData());
    }


    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public ResponseVO fileImport(MultipartFile file,Long direId,Integer status) throws Exception {
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.indexOf("."),fileName.length());
        if(suffix.equals(".xlsx")||suffix.equals(".xls")){
            Workbook workbook = null;
            String sheetName= "Sheet1";
            File newFile = new File(FileUtils.createFilePath(fileName));
            file.transferTo(newFile);
            try {
                if(newFile.getName().endsWith("xlsx")){
                    workbook = new XSSFWorkbook(new FileInputStream(newFile));
                }else{
                    workbook = new HSSFWorkbook(new FileInputStream(newFile));
                }
                Sheet sheet = workbook.getSheet(sheetName);
                if (!"".equals(sheetName.trim())) {
                    sheet = workbook.getSheet(sheetName);// 如果指定sheet名,则取指定sheet中的内容.
                }
                if (sheet == null) {
                    sheet = workbook.getSheetAt(0); // 如果传入的sheet名不存在则默认指向第1个sheet.
                }
                //创建类型
                ExcelUtil<ArcFile> excelUtil = new ExcelUtil<>() ;
                // 获取数据
                List<ArcFile> acfForms = excelUtil.dispatch(sheet,ArcFile.class);

                for (ArcFile acf : acfForms) {
                    if(null==acf.getFileNumber()){
                        logService.generate("上传目录失败 请确保每条[文件]包含的[档号]信息");
                        return ResultUtil.error("请确保每条[文件]包含的[档号]信息");
                    }
                    if(status==1){
                        if(acf.getLevelNumber()==null){
                            logService.generate("上传目录失败 请确保每条[文件]包含的[文件级档号]信息");
                            return  ResultUtil.error("请确保每条[文件]包含的[文件级档号]信息");
                        }
                        if(!fileService.findByFileNumber(acf.getFileNumber()).isPresent()){
                            logService.generate("上传目录失败 无法找到[案卷]的[档号]信息");
                            return  ResultUtil.error("上传目录失败 无法找到[案卷]的[档号]信息");
                        }
                        if(fileService.findByLevelNumber(acf.getLevelNumber()).isPresent()){
                            logService.generate("上传目录失败 文件级档号["+acf.getLevelNumber()+"]已存在");
                            return  ResultUtil.error("上传目录失败 文件级档号["+acf.getLevelNumber()+"]已存在");
                        }
                    }else{
                        if(fileService.findByFileNumber(acf.getFileNumber()).isPresent()){
                            logService.generate("上传目录失败 档号["+acf.getFileNumber()+"]已存在");
                            return  ResultUtil.error("上传目录失败 档号["+acf.getFileNumber()+"]已存在");
                        }
                    }

                    acf.setIncluding(0);
                    acf.setDireId(direId);
                    acf.setType(status);
                    fileService.add(acf);
                }
                logService.generate("上传目录成功");
                return ResultUtil.success("上传目录成功！");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        logService.generate("上传目录失败 请导入excel文件!");
        return ResultUtil.error("上传目录失败 请导入excel文件!");
    }

    @GetMapping(value ="/dire/{id}")
    public PageResult searchDireByCondit(@PathVariable(name="id") long id,@RequestParam(required = false,defaultValue = "1") int page,@RequestParam(required = false,defaultValue = "10") int limit,@RequestParam(required = false,defaultValue = "") String param) {
        return ResultUtil.tablePage(fileService.selectPageByDireId(id,page,limit,param));
    }

    @GetMapping(value ="/dire/count/{id}")
    public ResponseVO searchDireByConditCount(@PathVariable(name="id") long id,@RequestParam(required = false,defaultValue = "") String param) {
        return ResultUtil.success("获取成功！",fileService.countByDireId(id,param));

    }

    @GetMapping(value ="/dire/fileNumber/{fileNumber}")
    public List<ArcFile> searchFileNumberByCondit(@PathVariable(name="fileNumber") String fileNumber) {
        return fileService.selectPageByFileNumber(fileNumber);
    }

    @DeleteMapping(value ="/{id}")
    public ResponseVO delete(@PathVariable(name="id") long id) {
        return fileService.delete(id);
    }

    @PutMapping
    public ResponseVO edit(ArcFile file) {
        fileService.edit(file);
        logService.generate("修改目录成功 ID["+file.getId()+"]");
        return ResultUtil.success("修改成功！");
    }

    @PostMapping
    public ResponseVO add(ArcFile file) {
        fileService.add(file);
        logService.generate("添加目录成功 ID["+file.getId()+"]");
        return ResultUtil.success("添加成功！");
    }

    @PostMapping(value ="/fullSearch")
    public PageResult fullTextSearch(@RequestParam(required = false,defaultValue = "1") int page,@RequestParam(required = false,defaultValue = "10") int limit,@RequestParam(required = false,defaultValue = "1=1") String param) {
        return ResultUtil.tablePage(fileService.fullSearch(page,limit,param));

    }

}
