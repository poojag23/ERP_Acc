package CommonFunLibrary;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ERP_Functions {
	
	WebDriver driver;
	/*
	 * ProjectName:ERP_Stock_Accounting
	 * ModuleName:Launching url
	 * TesterName:Pooja
	 */

public String launchUrl (String url) throws Throwable
{
	String res="";
	System.setProperty("webdriver.chrome.driver", "./CommonJars/chromedriver.exe");
	driver=new ChromeDriver();
	driver.get(url);
	driver.manage().window().maximize();
	Thread.sleep(5000);
	
	if(driver.findElement(By.id("btnsubmit")).isDisplayed())
	{
		res="Launch Application success";
	}
	else
	{
		res="Launch Application fail";
	}
	return res;
}

/*
 * ProjectName:ERP_Stock_Accounting
 * ModuleName:Login
 * TesterName:Pooja
 */
public String verifyLogin(String username,String password) throws Throwable
{
	String res="";
	WebElement user=driver.findElement(By.name("username"));
	user.clear();
	Thread.sleep(2000);
	user.sendKeys(username);
	Thread.sleep(2000);
	
	WebElement pass=driver.findElement(By.name("password"));
	pass.clear();
	Thread.sleep(2000);
	pass.sendKeys(password);
	Thread.sleep(2000);
	driver.findElement(By.name("btnsubmit")).click();
	Thread.sleep(2000);
	
	
	if(driver.findElement(By.id("mi_logout")).isDisplayed())
	{
		res="login success";
	}
	else 
	{
		res="login fail";
	}
	return res;
	
}

/*
 * ProjectName:ERP_Stock_Accounting
 * ModuleName:Login
 * TesterName:Pooja
 */
public String verifyLogout()throws Throwable
{
	String res="";
	driver.findElement(By.xpath("//li[@id='mi_logout']")).click();
	Thread.sleep(5000);
	if(driver.findElement(By.name("btnsubmit")).isDisplayed())
	{
		res="Application Logout Success";
	}
	else
	{
		res="Application Logout Fail";
		
	}
	return res;
}

/*
 * ProjectName:ERP_Stock_Accounting
 * ModuleName:Supplier Creation
 * TesterName:Pooja
 */

public String verifySupplier(String sname,String address,String city,String country,String cperson,String pnumber,
		String email,String mnumber,String note )throws Throwable
{
	String res="";
	driver.findElement(By.linkText("Suppliers")).click();
	Thread.sleep(2000);
	driver.findElement(By.xpath("(//span[@class='glyphicon glyphicon-plus ewIcon'])[1]")).click();
	Thread.sleep(2000);
	String Exp_data=driver.findElement(By.name("x_Supplier_Number")).getAttribute("value");
	driver.findElement(By.id("x_Supplier_Name")).sendKeys(sname);
	driver.findElement(By.id("x_Address")).sendKeys(address);
	driver.findElement(By.id("x_City")).sendKeys(city);
	driver.findElement(By.id("x_Country")).sendKeys(country);
	driver.findElement(By.id("x_Contact_Person")).sendKeys(cperson);
	driver.findElement(By.id("x_Phone_Number")).sendKeys(pnumber);
	driver.findElement(By.id("x__Email")).sendKeys(email);
	driver.findElement(By.id("x_Mobile_Number")).sendKeys(mnumber);
	driver.findElement(By.id("x_Notes")).sendKeys(note);
	
	driver.findElement(By.id("btnAction")).sendKeys(Keys.ENTER);
	driver.findElement(By.xpath("//button[text()='OK!']")).click();
	Thread.sleep(2000);
	driver.findElement(By.xpath("//button[@class='ajs-button btn btn-primary']")).click();
	
	if(driver.findElement(By.id("psearch")).isDisplayed())
	{
		driver.findElement(By.id("psearch")).clear();
		Thread.sleep(3000);
		driver.findElement(By.id("psearch")).sendKeys(Exp_data);
		driver.findElement(By.id("btnsubmit")).click();
		Thread.sleep(3000);
		
	}
	else
	{
		driver.findElement(By.xpath("//span[@class='glyphicon glyphicon-search ewIcon']")).click();
		driver.findElement(By.id("psearch")).clear();
		Thread.sleep(3000);
		driver.findElement(By.id("psearch")).sendKeys(Exp_data);
		driver.findElement(By.id("btnsubmit")).click();
		Thread.sleep(3000);
	}
	
	String Act_data=driver.findElement(By.xpath("//table[@id='tbl_a_supplierslist']/tbody/tr[1]/td[6]/div/span/span")).getText();
	System.out.println(Exp_data+"           "+Act_data);
	if(Exp_data.equals(Act_data))
	{
		res="Pass";
		
	}
	else
	{
		res="Fail";
	}
	
	return res;
}

public void teardown(){
	driver.quit();
}

}
