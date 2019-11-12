package driverFactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import CommonFunLibrary.FunctionLibrary;
import utilities.ExcelFileUtil;

public class DriverScript {
	
	WebDriver driver;
	ExtentReports report;
	ExtentTest test;
	@Test
	public void startTest() throws Throwable
	{
		//create reference object for accessing excelutil methods
		ExcelFileUtil excel = new ExcelFileUtil();
		
		//iterate all row in MasterTestCases sheet
		for(int i =1;i<=excel.rowCount("MasterTestCases");i++)
		{
			String ModuleStatus="";
			
			if(excel.getCellData("MasterTestCases", i, 2).equalsIgnoreCase("Y"))
			{
				//store module name into TCmodule
				String TCModule = excel.getCellData("MasterTestCases", i, 1);
			
				//generate report under project
				report = new ExtentReports("D:\\Ojt4oclock\\ERP_Accounting\\Reports\\"+TCModule+" "+FunctionLibrary.generateDate()+".html");
				
				//iterate all rows in TCModule
				for(int j =1;j<=excel.rowCount(TCModule);j++)
				{
					test = report.startTest(TCModule);
					//read all columns from TCModule
					String Description = excel.getCellData(TCModule, j, 0);
					String Object_type = excel.getCellData(TCModule, j, 1);
					String Locator_Type = excel.getCellData(TCModule, j, 2);
					String Locator_Value = excel.getCellData(TCModule, j, 3);
					String Test_Data = excel.getCellData(TCModule, j, 4);
					
					try
					{
						if(Object_type.equalsIgnoreCase("startBrowser"))
						{
							driver = FunctionLibrary.startBrowser(driver);
							test.log(LogStatus.INFO, Description);
						}
						else if(Object_type.equalsIgnoreCase("openApplication"))
						{
							FunctionLibrary.openApplication(driver);
							test.log(LogStatus.INFO, Description);
						}
						else if(Object_type.equalsIgnoreCase("waitForElement"))
						{
							FunctionLibrary.waitForElement(driver, Locator_Type, Locator_Value, Test_Data);
							test.log(LogStatus.INFO, Description);
						}
						else if(Object_type.equalsIgnoreCase("typeAction"))
						{
							FunctionLibrary.typeAction(driver, Locator_Type, Locator_Value, Test_Data);
							test.log(LogStatus.INFO, Description);
						}
						else if(Object_type.equalsIgnoreCase("clickAction"))
						{
							FunctionLibrary.clickAction(driver, Locator_Type, Locator_Value);
							test.log(LogStatus.INFO, Description);
						}
						else if(Object_type.equalsIgnoreCase("closeBrowser"))
						{
							FunctionLibrary.closeBrowser(driver);
							test.log(LogStatus.INFO, Description);
						}
						else if(Object_type.equalsIgnoreCase("captureData"))
						{
							FunctionLibrary.captureData(driver, Locator_Type, Locator_Value);
							test.log(LogStatus.INFO, Description);
						}
						else if(Object_type.equalsIgnoreCase("tableValidation"))
						{
							FunctionLibrary.tableValidation(driver, Test_Data);
							test.log(LogStatus.INFO, Description);
						}
						else if(Object_type.equalsIgnoreCase("stockCategories"))
						{
							FunctionLibrary.stockCategories(driver);
							test.log(LogStatus.INFO, Description);
						}
						else if(Object_type.equalsIgnoreCase("stockValidation"))
						{
							FunctionLibrary.stockValidation(driver, Test_Data);
							test.log(LogStatus.INFO, Description);
						}
						
						//write as pass into status column
						excel.setCellData(TCModule, j, 5, "Pass");
						test.log(LogStatus.PASS, Description);
						ModuleStatus = "true";
						
					}catch(Throwable t)
					{
						System.out.println(t.getMessage());
						
						//write as fail into status column
						excel.setCellData(TCModule, j, 5, "Fail");
						test.log(LogStatus.FAIL, Description);
						File screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
						FileUtils.copyFile(screen, new File("D:\\Ojt4oclock\\ERP_Accounting\\Screenshots\\screen"+Description+FunctionLibrary.generateDate()+".png"));
						ModuleStatus = "false";
						break;
					}
					
					if(ModuleStatus.equalsIgnoreCase("true"))
					{
						excel.setCellData("MasterTestCases", i, 3, "Pass");
					}
					else if(ModuleStatus.equalsIgnoreCase("false"))
					{
						excel.setCellData("MasterTestCases", i, 3, "Fail");
					}
					
					report.flush();
					report.endTest(test);
					
					
				}
				
			}
			else
			{
				//Write status as not executed for testcases which are flag N
				excel.setCellData("MasterTestCases", i, 3, "Not Executed");
				
			}
		}
	}
	
	
	

}
