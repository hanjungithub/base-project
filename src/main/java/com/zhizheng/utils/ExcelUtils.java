package com.zhizheng.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by DELL on 2017/12/22.
 */
public class ExcelUtils {

    private static final Logger logger = LoggerFactory.getLogger(ExcelUtils.class);


    /**
     * 将数据导出excel,outputStream使用完后记得关闭
     *
     * @param outputStream 输出管道流
     * @param data         要导出的数据
     * @param fields       导出的数据字段名称
     * @param headers      导出的头部名称
     */
    @SuppressWarnings("Since15")
    public static void writeExcel(OutputStream outputStream, List<Map<String, String>> data,
                                  List<String> fields, List<String> headers) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        //创建头部
        Row row = sheet.createRow(0);
        for (int i = 0; i < headers.size(); i++) {
            Cell cell = row.createCell(i);
            CellStyle cellStyle = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true);
            //设置字体加粗
            cellStyle.setFont(font);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(headers.get(i));
        }
        for (int i = 0; i < data.size(); i++) {
            Row r = sheet.createRow(i + 1);
            Map<String, String> map = data.get(i);
            for (int n = 0; n < fields.size(); n++) {

                Cell cell = r.createCell(n);
                cell.setCellValue(map.getOrDefault(fields.get(n), ""));
                //设置列宽
                sheet.setColumnWidth(n, map.getOrDefault(fields.get(n), "").length() * 256*3+184);
            }

        }
        try {
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage(), e);
            }
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                if (logger.isErrorEnabled()) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
    }


}
