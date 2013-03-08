
package ch.hearc.coursjava.kitbase.excel;

import java.io.File;
import java.io.IOException;

import jxl.Workbook;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class ExcelGenerator
	{

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public static void generateXLS(int[] tabFreq, String filename) throws IOException, RowsExceededException, WriteException
		{
		WritableWorkbook workbook = Workbook.createWorkbook(new File(filename));
		WritableSheet sheet = workbook.createSheet("Histogram Sheet", 0);
		for(int i = 0; i < tabFreq.length; i++)
			{
			Number num = new Number(0, i, tabFreq[i]);
			sheet.addCell(num);
			}
		workbook.write();
		workbook.close();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	}
