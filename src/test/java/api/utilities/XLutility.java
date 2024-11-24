package api.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLutility {

    public FileInputStream fi;
    public FileOutputStream fo;
    public XSSFWorkbook workbook;
    public XSSFSheet sheet;
    public XSSFRow row;
    public XSSFCell cell;
    public CellStyle style;
    String path;

    // Corrected Constructor
    public XLutility(String path) {
        this.path = path;
    }

    public int getrowCount(String sheetName) throws IOException {
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            workbook.close();
            fi.close();
            throw new IllegalArgumentException("Sheet " + sheetName + " does not exist in " + path);
        }
        int rowcount = sheet.getLastRowNum();
        workbook.close();
        fi.close();
        return rowcount;
    }


    public int getcolCount(String sheetName) throws IOException {
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
        int colcount = sheet.getRow(0).getLastCellNum();
        workbook.close();
        fi.close();
        return colcount;
    }

    public String getCellData(String sheetname, int rownum, int colnum) throws IOException {
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetname);
        row = sheet.getRow(rownum);
        cell = row.getCell(colnum);
        DataFormatter formatter = new DataFormatter();
        String data;
        try {
            data = formatter.formatCellValue(cell);
        } catch (Exception e) {
            data = "";
        }
        workbook.close();
        fi.close();
        return data;
    }

    public void setcellData(String sheetName, int rownum, int colnum, String data) throws IOException {
        File xlfile = new File(path);
        if (!xlfile.exists()) // if file not exist, create a new file
        {
            workbook = new XSSFWorkbook();
            fo = new FileOutputStream(path);
            workbook.write(fo);
        }
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        if (workbook.getSheetIndex(sheetName) == -1)
            workbook.createSheet();
        sheet = workbook.getSheet(sheetName);
        if (sheet.getRow(rownum) == null)
            sheet.createRow(rownum);
        row = sheet.getRow(rownum);
        cell = row.createCell(colnum);
        cell.setCellValue(data);
        fo = new FileOutputStream(path);
        workbook.write(fo);
        workbook.close();
        fi.close();
        fo.close();
    }
}
