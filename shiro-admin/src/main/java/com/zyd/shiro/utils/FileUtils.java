package com.zyd.shiro.utils;


import java.io.File;

public class FileUtils {

    public final static String UPLOAD_PATH_PREFIX = "static/upload/";

    public static String creDire(String oldName){
        String realPath = new String("src/main/resources/" + UPLOAD_PATH_PREFIX);
        File filePath = new File(realPath);
        if(!filePath.isDirectory()){
            //递归生成文件夹
            filePath.mkdirs();
        }
        return filePath.getAbsolutePath() + File.separator + oldName;
    }











}
