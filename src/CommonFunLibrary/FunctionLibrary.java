package CommonFunLibrary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utilities.PropertyFileUtil;

public class FunctionLibrary {
	
	public static WebDriver driver;
	
	
	/*Project Name: ERP_Accounting
	 * Module Name : startBrowser
	 * Tester Name : Pooja
	 * Creation Date : 30/10/19
	 */
	public static WebDriver startBrowser(WebDriver driver) throws Throwable 
	{
		if(PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "D:\\Ojt4oclock\\ERP_Accounting\\Commonjars\\chromedriver.exe");
			driver= new ChromeDriver();
		}
		else if(PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "D:\\Ojt4oclock\\ERP_Accounting\\Commonjars\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else if(PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("ie"))
		{
			
		}
		
		return driver;
	
	}
	
	
	/*Project Name: ERP_Accounting
	 * Module Name : openApplication
	 * Tester Name : Pooja
	 * Creation Date : 31/10/19
	 */
	public static void openApplication(WebDriver driver) throws Throwable
	{
		driver.get(PropertyFileUtil.getValueForKey("url"));
		driver.manage().window().maximize();
	}
	
	
	/*Project Name: ERP_Accounting
	 * Module Name : waitForElement
	 * Tester Name : Pooja
	 * Creation Date : 31/10/19
	 */
	public static void waitForElement(WebDriver driver,String locatort,String locatorv,String mywait)
	{
		WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(mywait));
		if(locatort.equalsIgnoreCase("id"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorv)));
		}
		else if(locatort.equalsIgnoreCase("name"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorv)));
			
		}
		else if(locatort.equalsIgnoreCase("xpath"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorv)));
		}
	}
	
	
	/*Project Name: ERP_Accounting
	 * Module Name : typeAction
	 * Tester Name : Pooja
	 * Creation Date : 31/10/19
	 */
	public static void typeAction(WebDriver driver,String locatort,String locatorv,String testdata)
	{
		if(locatort.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(locatorv)).clear();
			driver.findElement(By.id(locatorv)).sendKeys(testdata);
		}
		else if(locatort.equalsIgnoreCase("name"))
		{
			driver.findElement(By.name(locatorv)).clear();
			driver.findElement(By.name(locatorv)).sendKeys(testdata);
		}
		else if(locatort.equalsIgnoreCase("xpath"))
		{
			driver.findElement(By.xpath(locatorv)).clear();
			driver.findElement(By.xpath(locatorv)).sendKeys(testdata);
		}
	}
	
	
	/*Project Name: ERP_Accounting
	 * Module Name : clickAction
	 * Tester Name : Pooja
	 * Creation Date : 31/10/19
	 */
	public static void clickAction(WebDriver driver,String locatort,String locatorv)
	{
		if(locatort.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(locatorv)).sendKeys(Keys.ENTER);
		}
		else if(locatort.equalsIgnoreCase("name"))
		{
			driver.findElement(By.name(locatorv)).click();
		}
		else if(locatort.equalsIgnoreCase("xpath"))
		{
			driver.findElement(By.xpath(locatorv)).click();
		}
	}
	
	
	/*Project Name: ERP_Accounting
	 * Module Name : closeBrowser
	 * Tester Name : Pooja
	 * Creation Date : 31/10/19
	 */
	public static void closeBrowser(WebDriver driver)
	{
		driver.quit();
	}
	
	
	/*Project Name: ERP_Accounting
	 * Module Name : generateDate
	 * Tester Name : Pooja
	 * Creation Date : 31/10/19
	 */
	public static String generateDate()
	{
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY_MM_dd_ss");
		return sdf.format(date);
		
	}
	
	
	/*Project Name: ERP_Accounting
	 * Module Name : captureData
	 * Tester Name : Pooja
	 * Creation Date : 5/11/19
	 */
	public static void captureData(WebDriver driver,String locatort,String locatorv)throws Throwable
	{
		String snumber = "";
		if(locatort.equalsIgnoreCase("id"))
		{
			snumber = driver.findElement(By.id(locatorv)).getAttribute("value");
		}
		else if(locatort.equalsIgnoreCase("name"))
		{
			snumber = driver.findElement(By.name(locatorv)).getAttribute("value");
		}
		else if(locatort.equalsIgnoreCase("xpath"))
		{
			snumber = driver.findElement(By.xpath(locatorv)).getAttribute("value");
		}
		
		FileWriter fw = new FileWriter("D:\\Ojt4oclock\\ERP_Accounting\\Capturedata\\supplier.txt");
		BufferedWriter br = new BufferedWriter(fw);
		br.write(snumber);
		br.flush();
		br.close();
		
		
		 	
	}
	
	
	/*Project Name: ERP_Accounting
	 * Module Name : tableValidation
	 * Tester Name : Pooja
	 * Creation Date : 5/11/19
	 */
	public static void tableValidation(WebDriver driver,String columndata) throws Throwable
	{
		Thread.sleep(4000);
		FileReader fr = new FileReader("D:\\Ojt4oclock\\ERP_Accounting\\Capturedata\\supplier.txt");
		BufferedReader br = new BufferedReader(fr);
		String Exp_data = br.readLine();
		//convert column data to integer
		int coldata = Integer.parseInt(columndata);
				
		if(driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search-box"))).isDisplayed())
		{
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search-box"))).clear();
			Thread.sleep(4000);
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search-box"))).sendKeys(Exp_data);
			Thread.sleep(4000);
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search-button"))).click();
			Thread.sleep(4000);
			
			
		}
		
		else
		{
			//click on search panel
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search-panel"))).click();
			Thread.sleep(4000);
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search-box"))).clear();
			Thread.sleep(4000);
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search-box"))).sendKeys(Exp_data);
			Thread.sleep(4000);
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search-button"))).click();
			Thread.sleep(4000);
		}
		
		//validate table data 
		WebElement table = driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("sup-table")));
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		for(int i=1;i<rows.size();i++)
		{
			String Act_data = driver.findElement(By.xpath("//table[@id='tbl_a_supplierslist']/tbody/tr["+i+"]/td["+coldata+"]/div/span/span")).getText();
			System.out.println(Exp_data+"          "+Act_data);
			Assert.assertEquals(Exp_data, Act_data , "Supplier number is not matching");
			break;
		}
				
	}
	
	
	/*Project Name: ERP_Accounting
	 * Module Name : stockCategories
	 * Tester Name : Pooja
	 * Creation Date : 5/11/19
	 */
	public static void stockCategories(WebDriver driver)
	{
		Actions ac = new Actions(driver);
		WebElement stockitems = driver.findElement(By.xpath("//li[@id='mi_a_stock_items']//a[contains(text(),'Stock Items')]"));
		ac.moveToElement(stockitems).perform();
		WebElement stockc = driver.findElement(By.xpath("//li[@id='mi_a_stock_categories']//a[contains(text(),'Stock Categories')]"));
		ac.moveToElement(stockc).click().perform();
		
	}
	
	
	
	/*Project Name: ERP_Accounting
	 * Module Name : stockValidation
	 * Tester Name : Pooja
	 * Creation Date : 5/11/19
	 */
	public static void stockValidation(WebDriver driver ,String Exp_data) throws Throwable
	{
				
		if(driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search-box1"))).isDisplayed())
		{
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search-box1"))).clear();
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search-box1"))).sendKeys(Exp_data);
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search-button1"))).click();
			
			
		}
		
		else
		{
			//click on search panel
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search-panel1"))).click();
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search-box1"))).clear();
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search-box1"))).sendKeys(Exp_data);
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search-button1"))).click();
		}
		
		WebElement table = driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("sot-table")));
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		for(int i = 1;i<rows.size();i++)
		{
			String Act_data = driver.findElement(By.xpath("//table[@id='tbl_a_stock_categorieslist']/tbody/tr["+i+"]/td[4]//span[@id='el1_a_stock_categories_Category_Name']/span")).getText();
			System.out.println(Exp_data+"               "+Act_data);
			Assert.assertEquals(Exp_data, Act_data,"Stock Category Not Matching");
			break;
				
		}
		
	}
	

}
