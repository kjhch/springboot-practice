/*
 * Cainiao.com Inc.
 * Copyright (c) 2013-2021 All Rights Reserved.
 */

package com.hch.excel;

import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.merge.AbstractMergeStrategy;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * @author 科君
 * @since 2021-03-12
 */
public class MyMergeStrategy extends AbstractMergeStrategy {
    @Override
    protected void merge(Sheet sheet, Cell cell, Head head, Integer integer) {
        int rowIndex = cell.getRowIndex();
        int colIndex = cell.getColumnIndex();
        if (rowIndex <= 5 && rowIndex >= 2 && colIndex < 1) {
            System.out.println("merge...");
            sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0, 1));
        }
    }
}