package com.lizheng.testWeb;

import com.lizheng.common.ExcelWriter;

public class testExcelWriter {
    public static void main(String[] args) {
        ExcelWriter ew = new ExcelWriter("Cases/WebCases.xlsx", "Cases/WebCases_test1.xlsx");
        for(int sheets=0;sheets<ew.getTotalSheetNo();sheets++){
            System.out.println("当前是第：" + sheets + "个sheet页！");
            ew.useSheetByIndex(sheets);
            for(int rows=0;rows<ew.rows;rows++){
                if(rows>2) {
                    ew.writeCell(rows, 9, "PASS!");
                    ew.writeFailCell(rows, 10, "FAIL!");
                }
            }
        }
        ew.save();
    }
}
