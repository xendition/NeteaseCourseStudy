package com.michael.demos.excel;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * 类功能描述:
 * <pre>
 *   xxxx
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2019/9/15 9:51
 */
public class Import {

    public static void main(String[] args) throws IOException {

        String filePath = "D:\\Michael\\Desktop\\十二刻度APP开发工时评估.xlsx";

        //1.读取Excel文档对象
        XSSFWorkbook hssfWorkbook = new XSSFWorkbook(new FileInputStream(filePath));
        //2.获取要解析的表格（第一个表格）
        XSSFSheet sheet = hssfWorkbook.getSheetAt(0);
        //获得最后一行的行号
        int lastRowNum = sheet.getLastRowNum();
        for (int i = 2; i <= lastRowNum; i++) {//遍历每一行
            //3.获得要解析的行
            XSSFRow row = sheet.getRow(i);
            //4.获得每个单元格中的内容（String）
            XSSFCell cell = row.getCell(0);
            cell.setCellType(CellType.STRING);
            String stringCellValue0 = cell.getStringCellValue();
//            String stringCellValue1 = row.getCell(1).getStringCellValue();
//            String stringCellValue2 = row.getCell(2).getStringCellValue();
//            String stringCellValue3 = row.getCell(3).getStringCellValue();
//            row.getCell(4).setCellType(CellType.STRING);
//            String stringCellValue4 = row.getCell(4).getStringCellValue();
            System.out.println(i + "->" + stringCellValue0);

        }
    }
}
