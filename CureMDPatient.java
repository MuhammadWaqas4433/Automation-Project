package addingPatient;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CureMDPatient {
	WebDriver driver;
	
	
	@Test (priority=0)
	public void Login() throws InterruptedException {
	driver = new ChromeDriver();
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\4433\\Downloads\\chromedriver_win32\\chromedriver.exe");
	driver.get("http://release01.curemd.com/curemdy/datlogin.asp");
	driver.manage().window().maximize();
	Thread.sleep(2000);
	driver.findElement(By.id("vchLogin_Name")).sendKeys("ChargeGeneralAt");
	driver.findElement(By.id("vchPassword")).sendKeys("SuPPort.2014");
	driver.findElement(By.xpath("//*[contains(text(),'Login')]")).click();
	Thread.sleep(2000);
	driver.close();
	for (String child: driver.getWindowHandles())
	{driver.switchTo().window(child);}
	driver.manage().window().maximize();
	Assert.assertEquals(driver.getTitle(), "Personal: Dashboard");
	}
	
	@Test (priority=1)
	public void addPatient() throws InterruptedException {	
	for (String child: driver.getWindowHandles())
	{driver.switchTo().window(child);}
	driver.manage().window().maximize();
	driver.switchTo().frame(0);
	driver.findElement(By.xpath("//*[@id=\"patientBtn\"]")).click();
	driver.switchTo().defaultContent();
	driver.switchTo().frame(driver.findElement(By.xpath("//*[@id=\"fraCureMD_Body\"]")));
	driver.findElement(By.xpath("//*[@id=\"frmPatient_Search\"]/table[2]/tbody/tr[2]/td[3]")).click();
	//driver.findElement(By.id("Add Patient")).click();
	Thread.sleep(2000);
	driver.switchTo().defaultContent();
	WebElement mainframe = driver.findElement(By.xpath("//*[@id=\"fraCureMD_Body\"]"));
	driver.switchTo().frame(mainframe);
	Select drpdn1 = new Select (driver.findElement(By.id("cmbVTitle")));
	drpdn1.selectByIndex(3);
	driver.findElement(By.id("txtVFNAME")).sendKeys("Adil");
	driver.findElement(By.id("txtVLNAME")).sendKeys("Rasheed");
	Select drpdn2 = new Select (driver.findElement(By.id("cmbVSEX")));
	drpdn2.selectByIndex(0);
	driver.findElement(By.id("txtDDOB")).sendKeys("06/15/2006");
	Select drpdn3 = new Select (driver.findElement(By.xpath("//*[@id=\"select2-cmbILOCID-container\"]")));
	drpdn3.selectByIndex(4);
	
	driver.findElement(By.xpath("//*[@id=\"lblCity\"]")).sendKeys("New York");
	driver.findElement(By.xpath("//*[@id=\"txtVSTATE\"]")).sendKeys("WA");
	driver.findElement(By.xpath("//*[@id=\"txtVZIP\"]")).sendKeys("45630");
	driver.findElement(By.xpath("//*[@id=\"txtVEMAIL\"]")).sendKeys("uio@gmail.com");
	
	driver.findElement(By.xpath("//*[@id=\"imgpInsurance\"]")).click();
	Select drpdn4 = new Select (driver.findElement(By.id("cmbIPLANID")));
	drpdn4.selectByIndex(4);
	Select drpdn5 = new Select (driver.findElement(By.id("cmbPlanAdd")));
	drpdn5.selectByIndex(0);
	driver.findElement(By.id("txtDSIGNONFILE")).sendKeys("12/15/2022");
	
	driver.findElement(By.xpath("//*[@id=\"imgSInsurance\"]")).click();
	Select drpdn6 = new Select (driver.findElement(By.id("cmbSECPLANID")));
	drpdn6.selectByIndex(8);
	Select drpdn7 = new Select (driver.findElement(By.id("cmbSecPlanAdd")));
	drpdn7.selectByIndex(1);
	driver.findElement(By.id("txtSecDSIGNONFILE")).sendKeys("12/14/2022");

	driver.findElement(By.id("tdsave")).click();
	Assert.assertEquals(driver.getTitle(), "Patient > Demographics");
	}
	
	@Test (priority=2)
	public void createCase() throws InterruptedException {
	driver.switchTo().defaultContent();
	WebElement menuframe = driver.findElement(By.xpath("//*[@id=\"fraCureMD_Patient_Menu\"]"));
	driver.switchTo().frame(menuframe);
	driver.findElement(By.id("webfx-tree-object-11-plus")).click();
	driver.findElement(By.id("webfx-tree-object-12-anchor")).click();
	driver.switchTo().defaultContent();
	WebElement openframe = driver.findElement(By.xpath("//*[@id=\"fraCureMD_Body\"]"));
	driver.switchTo().frame(openframe);
	driver.findElement(By.xpath("//*[@id=\"txtVCNAME\"]")).sendKeys("Accident");
	driver.findElement(By.xpath("//*[@id=\"txtDSTART\"]")).sendKeys("12/15/2022");
	driver.findElement(By.xpath("//*[@id=\"cmdSubmit\"]")).click();
	Thread.sleep(2000);
	Assert.assertEquals(driver.getTitle(), "Patient: Confirmation");
	}
	
	@Test (priority=3)
	public void createProviderNote() throws InterruptedException {
		driver.switchTo().defaultContent();
		WebElement menu1frame = driver.findElement(By.xpath("//*[@id=\"fraCureMD_Patient_Menu\"]"));
		driver.switchTo().frame(menu1frame);
	driver.findElement(By.xpath("//*[@id=\"webfx-tree-object-11-plus\"]")).click();
	driver.findElement(By.xpath("//*[@id=\"webfx-tree-object-12-anchor\"]")).click();
	driver.findElement(By.xpath("//*[@id=\"SpAdd1\"]")).click();

	driver.findElement(By.id("Sdate")).sendKeys("12/14/2022");
	Select drpdn8 = new Select (driver.findElement(By.id("select2-cmbProvider-container")));
	drpdn8.selectByIndex(0);
	Select drpdn9 = new Select (driver.findElement(By.id("cmbRTemplate")));
	drpdn9.selectByIndex(0);
	Select drpdn10 = new Select (driver.findElement(By.id("txtVREASON")));
	drpdn10.selectByIndex(0);
	Select drpdn11 = new Select (driver.findElement(By.id("select2-cmbLocation-container")));
	drpdn11.selectByIndex(0);
	driver.findElement(By.name("btnSave")).click();
	Thread.sleep(2000);
	Assert.assertEquals(driver.getTitle(), "Patient: Clinical > Case > Notes > Add Notes");
	}
	
	@Test (priority=4)
	public void addDiagnosis() throws InterruptedException {
	driver.findElement(By.xpath("//*[@id=\"webfx-tree-object-30\"]/a")).click();
	driver.findElement(By.name("q")).sendKeys("A00.0"+Keys.ENTER);
	
	driver.findElement(By.name("Save")).click();
	Thread.sleep(2000);
	Assert.assertEquals(driver.getTitle(), "");
	}
	
	@Test (priority=5)
	public void addProcedure() throws InterruptedException {
	driver.findElement(By.xpath("//*[@id=\"webfx-tree-object-31-plus\"]")).click();
	driver.findElement(By.xpath("//*[@id=\"webfx-tree-object-33\"]/a")).click();
	driver.findElement(By.name("q")).sendKeys("99211"+Keys.ENTER);
	driver.findElement(By.xpath("//*[@Contains(99211)")).click();
	driver.findElement(By.name("Save & Accept")).click();
	Thread.sleep(2000);
	Assert.assertEquals(driver.getTitle(), "");
	}
	
	@Test (priority=6)
	public void createESuperbill() throws InterruptedException {
	driver.findElement(By.name("Create Superbill")).click();
	Select drpdn12 = new Select (driver.findElement(By.id("Evaluation and Management (Time Based Coding-2021)")));
	drpdn12.selectByIndex(0);
	driver.findElement(By.xpath("//*[@id=\"chk99211\"]")).click();
	driver.findElement(By.id("Dx. Ptr")).sendKeys("10");
	driver.findElement(By.name("Save")).click();
	Thread.sleep(2000);
	Assert.assertEquals(driver.getTitle(), "Patient: Provider Notes: eSuperbill");
	}
	
}
