package precheckStories;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import precheck.Base;

public class MMP391_ETQPythonScriptSQLQueries extends Base {
	static Logger log = Logger.getLogger(MMP391_ETQPythonScriptSQLQueries.class.getName());

	/**
	 * This method is to validate the list have id and description column name
	 * 
	 * @throws SQLException
	 * @throws IOException
	 */
	@Test
	public void tc01_IsListHaveIdAndDescriptionColumnName() throws SQLException, IOException {
		log.info("tc01_Is List Have Id And Description ColumnName started....................");
		prop = loadQueryFile("\\src\\test\\resources\\precheck\\queries\\MMP391_ETQPythonScriptSQLQueries.properties");
		sourceQuery = query(prop.getProperty("idAndDescriptionList"));
		while (sourceQuery.next()) {
			Assert.assertNotNull(sourceQuery.getObject(2).toString(),
					"ID is null for = " + sourceQuery.getObject(1).toString());
			Assert.assertNotNull(sourceQuery.getObject(3).toString(),
					"Description is null for = " + sourceQuery.getObject(1).toString());
		}
		log.info("tc01_Is List Have Id And Description ColumnName ended....................");
	}

	/**
	 * This method is to validate report captured Specified headings
	 * 
	 * @throws Exception
	 */
	@Test
	public void tc06_IsSpecifiedDetailsCapturedInReport() throws Exception {
		log.info("tc06_Is Specified Details Captured In Report started....................");
		loadHighLevelReportInBrowser();
		xpathProperties = loadXpathFile();
		listOfWebElement = xtexts(xpathProperties.getProperty("sqlReferenceList"));
		listOfText = listString();
		for (int i = 0; i < listOfText.size(); i++) {
			if (i == 0) {
				Assert.assertEquals(listOfText.get(i), "File Name");
			}
			if (i == 1) {
				Assert.assertEquals(listOfText.get(i), "Keyword");
			}
			if (i == 2) {
				Assert.assertEquals(listOfText.get(i), "Occurrence");
			}

		}
		log.info("tc06_Is Specified Details Captured In Report ended....................");
	}

	/**
	 * This method is to validate item name captured as SQL Lookups Reference in
	 * report
	 * 
	 * @throws Exception
	 */
	@Test
	public void tc10_IsItemNameCapturedAsSQLLookupsReference() throws Exception {
		log.info("tc06_Is Item Name Captured As SQL Lookups Reference started....................");
		loadHighLevelReportInBrowser();
		xpathProperties = loadXpathFile();
		text = xtext(xpathProperties.getProperty("itemReference"));
		Assert.assertEquals(text, "SQL Lookups Reference");
		log.info("tc06_Is Item Name Captured As SQL Lookups Reference ended....................");
	}

}
