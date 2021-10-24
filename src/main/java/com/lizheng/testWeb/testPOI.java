package com.lizheng.testWeb;

import com.lizheng.keyWord.WebKeyWord;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class testPOI {
    public static void main(String[] args) throws IOException, InvalidFormatException {
        WebKeyWord wkw = new WebKeyWord();
        Workbook wb =new XSSFWorkbook(new File("Cases/WebCases.xlsx"));
        Sheet sheetOne = wb.getSheetAt(0);
        List<List<String>> sheetcontent = new ArrayList<>();
        for(int rowNo=0;rowNo<sheetOne.getPhysicalNumberOfRows();rowNo++){
            Row row = sheetOne.getRow(rowNo);
            List<String> rowcontent = new ArrayList<>();
            for(int colNo=0;colNo<row.getPhysicalNumberOfCells();colNo++){
                Cell cell = row.getCell(colNo);
                String cellValue = "";
                if(cell.getCellType().equals(CellType.NUMERIC)){
                    cellValue = cell.getNumericCellValue() + "";
                }else{
                    cellValue = cell.getStringCellValue();
                }
                rowcontent.add(cellValue);

            }
            System.out.println("第" + rowNo + "行的内容是：" + rowcontent);
            switch (rowcontent.get(3)){
                case "打开浏览器":
                    System.out.println("调用打开浏览器方法");
                    break;
                case "visitWeb":
                    System.out.println("调用访问url方法");
                    break;
                case "input":
                    System.out.println("调用输入字符串方法");
                    break;
                case "click":
                    System.out.println("调用点击元素方法");
                    break;
                case "intoIframe":
                    System.out.println("调用进入iframe方法");
                    break;
                case "selectByValue":
                    System.out.println("调用通过value选择select方法");
                    break;
                case "halt":
                    System.out.println("调用鼠标悬停方法");
                    break;
                case "closeBrowser":
                    System.out.println("调用关闭浏览器方法");
                    break;
                default:
                    System.out.println("没有匹配到方法！！！");
                    break;

            }
            sheetcontent.add(rowcontent);
        }

    }
}
