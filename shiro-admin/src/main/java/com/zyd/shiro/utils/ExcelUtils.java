package com.zyd.shiro.utils;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zyd.shiro.entity.ArcData;
import lombok.SneakyThrows;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * excel工具类
 */
public class ExcelUtils {


    /**
     * 判断文件格式
     * @param in
     * @param fileName
     * @return
     */
    public static Workbook getWorkbook(InputStream in, String fileName) throws Exception {
        Workbook book = null;
        String filetype = fileName.substring(fileName.lastIndexOf("."));

        if(".xls".equals(filetype)) {
            book = new HSSFWorkbook(in);
        } else if (".xlsx".equals(filetype)) {
            book = new XSSFWorkbook(in);
        } else {
            throw new Exception("请上传excel文件！");
        }

        return book;
    }


    /*private static boolean containsField(List<Calibration> calibrations, Row titleRow){
        int result=calibrations.size();
        for(int i = 0;i<calibrations.size();i++){
            for (int j = 0; j < titleRow.getLastCellNum(); j++) {
               if(calibrations.get(i).getName().equals(titleRow.getCell(j).toString().trim())){
                   --result;
               }
            }
        }
        return result!=0?false:true;
    }*/

   /* private static List<Calibration> configSub(List<Calibration> calibrations,Row titleRow){
        int result=calibrations.size();
        for(int i = 0;i<calibrations.size();i++){
            for (int j = 0; j < titleRow.getLastCellNum(); j++) {
                if(calibrations.get(i).getName().equals(titleRow.getCell(j).toString().trim())){
                    calibrations.get(i).setIndex(j);
                }
            }
        }
        return calibrations;
    }*/

    /*private static boolean inIntervals(Calibration calibration,String param){
        if(!calibration.isIntervals()){
            return calibration.getValue().equals(param.trim());
        }
        if(!calibration.isInterType()){
            int paramInt = Integer.parseInt(param);
            int startInt = Integer.parseInt(calibration.getStartVal());
            int endInt = Integer.parseInt(calibration.getEndVal());
            return (paramInt>=startInt&&paramInt<=endInt)?true:false;
        }else{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date paramDate = sdf.parse(param);
                Date startDate = sdf.parse(calibration.getStartVal());
                Date endDate = sdf.parse(calibration.getEndVal());
                return (paramDate.after(endDate)&&paramDate.before(startDate))?true:false;
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        return true;
    }*/


    @SneakyThrows
    public static  <T> T creT(Row row, Class<T> tClass) {
        T t = tClass.newInstance();
        JSONArray array = new JSONArray();
        for (int i = row.getFirstCellNum(); i < row.getLastCellNum(); i++) {
            Cell cell = row.getCell(i);
            if(cell!=null){
                String val = getCellStringValue(row.getCell(i));
                //JSONObject jsonObject = new JSONObject();
                //jsonObject.put(String.valueOf(i),val);
                Method method = tClass.getDeclaredMethod("setFile"+i,String.class);
                method.invoke(t,val);
                array.add(val);
            }

        }
        Method method = tClass.getDeclaredMethod("setSearchString",String.class);
        method.invoke(t,array.toString());
        return t;
    }

    public static String getCellStringValue(Cell cell) {
        String cellValue = "";
        if (cell != null) {
            // cell.getCellTypeEnum(),获取单元格类型,case不同类型进行不同处理
            switch (cell.getCellTypeEnum()) {
                case _NONE: // 未知类型，用于表示初始化前的状态或缺少具体类型。仅供内部使用。
                    break;
                case NUMERIC: // 数字类型 小数,整数,日期

                    // 如果是数字类型的话,判断是不是日期类型
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        Date d = cell.getDateCellValue();
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        cellValue = formatter.format(d);
                    } else if(cell.getCellStyle().getDataFormat() == 57) {
                        Date d = cell.getDateCellValue();
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
                        cellValue = formatter.format(d);
                    } else {
                        DecimalFormat df = new DecimalFormat("0");
                        cellValue = df.format(cell.getNumericCellValue());
                    }

                    break;
                case STRING: // 字符串类型
                    cellValue = cell.getStringCellValue();
                    break;
                case FORMULA: // 公式类型
                    cellValue = String.valueOf(cell.getNumericCellValue());
                    break;
                case BLANK: // 空白的单元格
                    break;
                case BOOLEAN: // 布尔类型
                    break;
                case ERROR: // 错误类型
                    break;
                default:
                    break;
            }
        }
        return cellValue;
    }
    @SneakyThrows
    public static <T>  Map<Integer,String> getT(Class<T> tClass,int num){
            T t = tClass.newInstance();
            Map<Integer,String> map = new HashMap<>();

            for(int i=0;i<num;i++){
                Method method = tClass.getDeclaredMethod("getFile"+i);
                String val = (String)method.invoke(t);
                if(val!=null){
                    map.put(i,val);
                }
            }
            return map;
    }


    public static HSSFWorkbook  exportExcel(String title, String[] headers, List<ArcData> list, int num) {
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet(title);
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth(20);
        // 生成一个样式
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置这些样式
        // 生成一个字体
        HSSFFont font = workbook.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 11);
        // 把字体应用到当前的样式
        style.setFont(font);
        // 生成并设置另一个样式
        HSSFCellStyle style2 = workbook.createCellStyle();

        // 生成另一个字体
        HSSFFont font2 = workbook.createFont();
        // 把字体应用到当前的样式
        style2.setFont(font2);

        // 产生表格标题行
        HSSFRow row = sheet.createRow(0);
        HSSFCell cellHeader;
        for (int i = 0; i < headers.length; i++) {
            cellHeader = row.createCell(i);
            cellHeader.setCellStyle(style);
            cellHeader.setCellValue(new HSSFRichTextString(headers[i]));
        }
        HSSFCell cell;
        Object value;
        Class tClass = ArcData.class;
        for(int i=0;i<list.size();i++){
            row = sheet.createRow(i+1);
            ArcData arcData = list.get(i);
            for(int j=0;j<num;j++){
                cell = row.createCell(j);
                cell.setCellStyle(style2);
                try {
                    Method method = tClass.getDeclaredMethod("getFile"+j);
                    value = method.invoke(arcData);
                    cell.setCellValue(value==null?"":value.toString());
                } catch (SecurityException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } finally {
                    // 清理资源
                }
            }
        }
        return workbook;
    }


}
