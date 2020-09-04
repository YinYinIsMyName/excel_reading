package com.spring.read.file.helper;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.spring.read.file.models.Tutorial;

public class ExcelHelper {
	/*
	 * static means no need to declare further object
	 */
	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";;
	static String[] HEADERS = { "id", "title", "descriptions", "published" };
	static String SHEET = "Tutorial";

	public static boolean hasExcelFormat(MultipartFile file) {
		if (!TYPE.equals(file.getContentType())) {
			return false;
		}
		return true;
	}

	public static List<Tutorial> excelToTutorals(InputStream is) {
       
		try {
			Workbook workbook = new XSSFWorkbook(is);
			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rows = sheet.iterator();
			List<Tutorial> tutorials = new ArrayList<Tutorial>();

			int rowNumber = 0;
			while (rows.hasNext()) {
				Row currentRow = rows.next();

				// skip header
				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}

				Iterator<Cell> cellsInRow = currentRow.iterator();

				Tutorial tutorial = new Tutorial();

				int cellIdx = 0;
				while (cellsInRow.hasNext()) {
					Cell currentCell = cellsInRow.next();

					switch (cellIdx) {
					case 0:
						tutorial.setId((long) currentCell.getNumericCellValue());
						break;

					case 1:
						tutorial.setTitle(currentCell.getStringCellValue());
						break;

					case 2:
						tutorial.setDescriptions(currentCell.getStringCellValue());
						break;

					case 3:
						tutorial.setPublished(currentCell.getStringCellValue());
						break;

					default:
						break;
					}

					cellIdx++;
				}

				tutorials.add(tutorial);
			}

			workbook.close();

			return tutorials;

		} catch (Exception e) {
			throw new RuntimeException("fail to parse excel" + e.getMessage());
		}

	}

}
