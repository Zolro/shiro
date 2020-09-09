package com.zyd.shiro.utils;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
@Slf4j
public class FileUtils {

    public final static String UPLOAD_PATH_PREFIX = "static/upload/";
    public static final String separator = File.separator;



    public static String createFilePath(String name) {
        String realPath = new String(System.getProperty("user.dir")+"/shiro-admin/src/main/resources/"+ UPLOAD_PATH_PREFIX);
        File filePath = new File(realPath);
        if (!filePath.isDirectory()) {
            filePath.mkdirs();
        }
        return filePath.getAbsolutePath() + File.separator + name;
    }

    public static ResponseEntity<InputStreamResource> download(String fileName) {
        String route = "static/upload";
        String path = null;
        ResponseEntity<InputStreamResource> response = null;
        try {
            path = route + separator + fileName;
            ClassPathResource classPathResource = new ClassPathResource(path);
            InputStream inputStream = classPathResource.getInputStream();
            //File file = new File(path);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Content-Disposition",
                    "attachment; filename="
                            + fileName);
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");
            response = ResponseEntity.ok().headers(headers)
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .body(new InputStreamResource(inputStream));
        } catch (FileNotFoundException e1) {
            log.error("找不到指定的文件", e1);
        } catch (IOException e) {
            log.error("获取不到文件流", e);
        }
        return response;
    }

    /**
     * 写入txt文件
     *
     * @param result
     * @param fileName
     * @return
     */
    public static boolean writeDataHubData(String result, String fileName) {
        long start = System.currentTimeMillis();
        String filePath = "D:";
        StringBuilder content = new StringBuilder();
        boolean flag = false;
        BufferedWriter out = null;
        try {
            if (result != null && !result.isEmpty() && StringUtils.isNotEmpty(fileName)) {
                fileName += "_" + System.currentTimeMillis() + ".ftl";
                File pathFile = new File(filePath);
                if (!pathFile.exists()) {
                    pathFile.mkdirs();
                }
                String relFilePath = filePath + File.separator + fileName;
                File file = new File(relFilePath);
                if (!file.exists()) {
                    file.createNewFile();
                }
                out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "GBK"));
                //标题头
//                out.write("curr_time,link_id,travel_time,speed,reliabilitycode,link_len,adcode,time_stamp,state,public_rec_time,ds");
//                out.newLine();

                out.write(result);
                out.newLine();

                flag = true;
//                logger.info("写入文件耗时：*********************************" + (System.currentTimeMillis() - start) + "毫秒");
                System.out.println("写入文件耗时：*********************************" + (System.currentTimeMillis() - start) + "毫秒");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return flag;
        }
    }


}
