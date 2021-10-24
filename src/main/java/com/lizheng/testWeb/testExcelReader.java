package com.lizheng.testWeb;

import com.lizheng.common.ExcelReader;

import java.util.List;

public class testExcelReader {
    public static void main(String[] args) {
        ExcelReader er =new ExcelReader("Cases/WebCases.xlsx");

        for(int sheets=0;sheets<er.getTotalSheetNo();sheets++){
            System.out.println("当前是第：" + sheets + "个sheet页！");
            er.useSheetByIndex(sheets);
            for(int rows=0;rows<er.rows;rows++){
                List<String> rowContents = er.readLine(rows);
                System.out.print("第" + rows + "行:   ");
                for(int i=0;i<rowContents.size();i++){
                    System.out.print("第" + i + "列的数据为：" + rowContents.get(i) + " ");
                }
                System.out.println();
            }
        }

    }

}
