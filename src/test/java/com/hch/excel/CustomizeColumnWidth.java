/*
 * Cainiao.com Inc.
 * Copyright (c) 2013-2021 All Rights Reserved.
 */

package com.hch.excel;

import java.util.List;

import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import com.alibaba.excel.write.style.column.AbstractColumnWidthStyleStrategy;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 * @author 科君
 * @since 2021-03-12
 */
public class CustomizeColumnWidth extends AbstractColumnWidthStyleStrategy {
    @Override
    protected void setColumnWidth(WriteSheetHolder writeSheetHolder, List<CellData> list, Cell cell,
                                  Head head, Integer integer, Boolean isHead) {
        // 测试为 COLUMN 宽度定制.
        if (isHead && cell.getRowIndex() == 0) {
            //先固定设置一个值,后面看需求进行调整
            int columnWidth = 25;
            int cellIndex = cell.getColumnIndex();
            writeSheetHolder.getSheet().setColumnWidth(cellIndex, columnWidth * 256);
        }
    }

    @Override
    public void beforeCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Row row,
                                 Head head, Integer columnIndex, Integer relativeRowIndex, Boolean isHead) {
        // 设置行高测试
        short height = 300;
        row.setHeight(height);
    }
}