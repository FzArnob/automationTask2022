package automationTask2022;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
public class createAccount {
	public WebDriver driver;
	public void sleep(int ms){
		try
		{
			Thread.sleep(ms);
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	public createAccount(WebDriver driver) {
		this.driver = driver;
	}
	// Format: [data]
			//  String email, => 0
			//  String gender, => 1
			//  String fname, => 2
			//  String lname, => 3
			//  String pass, => 4
			//  String bday, => 5
			//  String bmonth, => 6
			//  String byear, => 7
			//  String newsletter, => 8
			//  String offers, => 9
			//  String company, => 10
			//  String address1, => 11
			//  String address2, => 12
			//  String city, => 13
			//  String state, => 14
			//  String zip, => 15
			//  String country, => 16
			//  String addinfo, => 17
			//  String hphone, => 18
			//  String mphone, => 19
			//  String alias => 20
	public String createAccountWithData(String[] data) {
		try {
		System.out.println("Filling up the form...");
		if (!driver.findElements(By.linkText("Sign out")).isEmpty()) throw new Exception("A user is already logged in!");
		driver.findElement(By.linkText("Sign in")).click();
		sleep(1000);
		driver.findElement(By.id("email_create")).sendKeys(data[0]);
		sleep(2000);
		driver.findElement(By.id("SubmitCreate")).click();
		System.out.println("Waiting for form to be visible..");
		while (driver.findElements(By.id("id_gender"+data[1])).isEmpty()) {
			if(driver.findElement(By.id("create_account_error")).isDisplayed()) throw new Exception("Account with this email already exists");
			sleep(1000);
		}
		driver.findElement(By.id("id_gender"+data[1])).click();
		driver.findElement(By.id("customer_firstname")).sendKeys(data[2]);
		driver.findElement(By.id("customer_lastname")).sendKeys(data[3]);
		driver.findElement(By.id("passwd")).sendKeys(data[4]);
		Select d = new Select(driver.findElement(By.id("days")));
		d.selectByValue(data[5]);
		Select m = new Select(driver.findElement(By.id("months")));
		m.selectByValue(data[6]);
		Select y = new Select(driver.findElement(By.id("years")));
		y.selectByValue(data[7]);
		if(data[8].equals("true")) driver.findElement(By.id("newsletter")).click();
		if(data[9].equals("true")) driver.findElement(By.id("optin")).click();
		driver.findElement(By.id("company")).sendKeys(data[10]);
		driver.findElement(By.id("address1")).sendKeys(data[11]);
		driver.findElement(By.id("address2")).sendKeys(data[12]);
		driver.findElement(By.id("city")).sendKeys(data[13]);
		new Select(driver.findElement(By.id("id_state"))).selectByVisibleText(data[14]);
		driver.findElement(By.id("postcode")).sendKeys(data[15]);
		new Select(driver.findElement(By.id("id_country"))).selectByVisibleText(data[16]);
		driver.findElement(By.id("other")).sendKeys(data[17]);
		driver.findElement(By.id("phone")).sendKeys(data[18]);
		driver.findElement(By.id("phone_mobile")).sendKeys(data[19]);
		driver.findElement(By.id("alias")).clear();
		driver.findElement(By.id("alias")).sendKeys(data[20]);
		System.out.println("Waiting for inspection...");
		sleep(5000);
		driver.findElement(By.id("submitAccount")).click();
		sleep(1000);
		if(driver.findElements(By.linkText("Sign out")).isEmpty()) throw new Exception("Account creation failed, crud operation error");
		return "Account creation successful";
		} catch (Exception e) {
			
			return "Error: "+ e;
		}
		
	}
}
