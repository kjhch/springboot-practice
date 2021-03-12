/*
 * Cainiao.com Inc.
 * Copyright (c) 2013-2021 All Rights Reserved.
 */

package com.hch.excel;

import java.util.List;

import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * @author 科君
 * @since 2021-03-12
 */
public class CustomCellWriteHandler implements CellWriteHandler {
    @Override
    public void beforeCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Row row,
                                 Head head, Integer integer, Integer integer1, Boolean aBoolean) {
        //System.out.println("beforeCellCreate");
    }

    @Override
    public void afterCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Cell cell,
                                Head head, Integer integer, Boolean aBoolean) {

        //if ("未反馈".equals(cell.getStringCellValue())) {
        //    Workbook workbook = writeSheetHolder.getSheet().getWorkbook();
        //    CellStyle cellStyle = workbook.createCellStyle();
        //    Font cellFont = workbook.createFont();
        //    //cellFont.setBold(true);
        //    cellFont.setColor(Font.COLOR_RED);
        //    cellStyle.setFont(cellFont);
        //    cell.setCellStyle(cellStyle);
        //}
        //System.out.println("afterCellCreate");
        //System.out.println(cell.getCellStyle().getAlignmentEnum());

    }

    @Override
    public void afterCellDataConverted(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder,
                                       CellData cellData, Cell cell, Head head, Integer integer, Boolean aBoolean) {
        //System.out.println("afterCellDataConverted");

    }

    @Override
    public void afterCellDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder,
                                 List<CellData> list, Cell cell, Head head, Integer integer, Boolean aBoolean) {
        //System.out.println("afterCellDispose");
        Workbook workbook = writeSheetHolder.getSheet().getWorkbook();
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        if (cell.getColumnIndex() == 2) {
            cellStyle.setFillForegroundColor(HSSFColorPredefined.LIME.getIndex());
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        }

        if (cell.getRowIndex() <= 5) {
            cellStyle.setFillForegroundColor(HSSFColorPredefined.GOLD.getIndex());
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        }

        //cell.setCellStyle();
        if (CellType.STRING.equals(cell.getCellTypeEnum()) && "未反馈".equals(cell.getStringCellValue())) {

            Font cellFont = workbook.createFont();
            //cellFont.setBold(true);
            cellFont.setColor(HSSFColorPredefined.RED.getIndex());
            cellStyle.setFont(cellFont);
        }
        if (CellType.STRING.equals(cell.getCellTypeEnum()) && "已反馈".equals(cell.getStringCellValue())) {
            Font cellFont = workbook.createFont();
            //cellFont.setBold(true);
            cellFont.setColor(HSSFColorPredefined.GREEN.getIndex());
            cellStyle.setFont(cellFont);
        }
        cell.setCellStyle(cellStyle);

    }


}