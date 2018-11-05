package com.grokonez.kotlin.excel.util

import com.grokonez.kotlin.excel.model.Customer
import org.apache.poi.ss.usermodel.IndexedColors
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

class ExcelGenerator {

    companion object {
        fun customerPDFReport(customers: List<Customer>): ByteArrayInputStream {
            val columns = arrayOf<String>("Id", "Name", "Address", "Age")

            val workbook = XSSFWorkbook()
            val createHelper = workbook.creationHelper

            val sheet = workbook.createSheet("Customers")

            val headerFont = workbook.createFont()
            headerFont.bold = true
            headerFont.color = IndexedColors.BLUE.getIndex()

            val headerCellStyle = workbook.createCellStyle()
            headerCellStyle.setFont(headerFont)

            // Row for Header
            val headerRow = sheet.createRow(0)

            // Header
            for (col in columns.indices) {
                val cell = headerRow.createCell(col)
                cell.setCellValue(columns[col])
                cell.cellStyle = headerCellStyle
            }

            // CellStyle for Age
            val ageCellStyle = workbook.createCellStyle()
            ageCellStyle.dataFormat = createHelper.createDataFormat().getFormat("#")

            var rowIdx = 1
            for (customer in customers) {
                val row = sheet.createRow(rowIdx++)
                row.createCell(0).setCellValue(customer.id.toString())
                row.createCell(1).setCellValue(customer.name)
                row.createCell(2).setCellValue(customer.address)
                val ageCell = row.createCell(3)
                ageCell.setCellValue(customer.age.toDouble())
                ageCell.cellStyle = ageCellStyle
            }

            var out = ByteArrayOutputStream()
            workbook.write(out)
            workbook.close()

            return ByteArrayInputStream(out.toByteArray());
        }
    }
}