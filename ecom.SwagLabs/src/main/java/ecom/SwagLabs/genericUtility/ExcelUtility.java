package ecom.SwagLabs.genericUtility;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {

    public String readDataFromExcel(
            String sheetName,
            int rowIndex,
            int columnIndex) throws IOException {

        String filePath = "./src/test/resources/TestData.xlsx";

        try (FileInputStream input = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(input)) {

            Sheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                throw new IOException(
                    "Sheet not found: " + sheetName);
            }

            Row row = sheet.getRow(rowIndex);

            if (row == null || row.getCell(columnIndex) == null) {
                throw new IOException(
                    "Cell not found: sheet=" + sheetName
                    + ", row=" + rowIndex
                    + ", column=" + columnIndex);
            }

            DataFormatter formatter = new DataFormatter();

            return formatter.formatCellValue(
                row.getCell(columnIndex));
        }
    }
}
