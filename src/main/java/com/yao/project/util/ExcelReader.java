package com.yao.project.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {
    public static void main(String[] args) {
        // 文件路径
        String excelFilePath = "resources/example.xlsx";

        // 使用FileInputStream打开文件
        try (FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            // 获取第一个工作表（Sheet）
            Sheet firstSheet = workbook.getSheetAt(0);

            // 遍历行和列
            for (Row nextRow : firstSheet) {
                for (Cell cell : nextRow) {
                    // 获取单元格的值
                    String cellValue = getCellValue(cell);
                    System.out.print(cellValue + " ");
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 根据单元格的不同类型，获取其值
    private static String getCellValue(Cell cell) {
        DataFormatter formatter = new DataFormatter();
        return formatter.formatCellValue(cell);
    }
}
