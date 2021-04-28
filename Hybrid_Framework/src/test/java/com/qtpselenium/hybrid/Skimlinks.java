package com.qtpselenium.hybrid;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Skimlinks {
	WebDriver driver = new ChromeDriver();
		
//Opening the given URL in Chrome browser
	
@BeforeMethod	
	public void SignUpBrowser() throws InterruptedException{
	
	driver.manage().deleteAllCookies();	
	driver.get("https://signup.skimlinks.com/");
	driver.manage().window().maximize();
	}

//Verifying redirection to Terms of service page
@Test
public void termsOfServicepage() {
	WebElement termsOfService = driver.findElement(By.xpath("//a[contains(text(),'Terms of Service')]"));
	termsOfService.click();
	
	Set <String> allWindows = driver.getWindowHandles();
	for(String handle : allWindows) {
		driver.switchTo().window(handle);
			}
	
	String expectedURL = "https://skimlinks.com/terms-of-service/";
	String actualURL = driver.getCurrentUrl();
	Assert.assertEquals(actualURL, expectedURL);
	String parentHandle = driver.getWindowHandle();
	driver.switchTo().window(parentHandle); 
	
	
}

//Verifying redirection to Privacy policy page
@Test
public void privacyPolicy() {
	WebElement privacyPolicy = driver.findElement(By.xpath("//a[contains(text(),'Privacy Policy')]"));
	privacyPolicy.click();
	
	Set <String> allWindows = driver.getWindowHandles();
	for(String handle : allWindows) {
		driver.switchTo().window(handle);
			}
	
	String expectedURL = "https://skimlinks.com/privacy-policies/";
	String actualURL = driver.getCurrentUrl();
	Assert.assertEquals(actualURL, expectedURL);
	driver.navigate().back();
	
}

//Verifying redirection to Program policies page
@Test
public void programPolicies() {
	WebElement programPolicy = driver.findElement(By.xpath("//a[contains(text(),'Privacy Policy')]"));
	programPolicy.click();
	
	Set <String> allWindows = driver.getWindowHandles();
	for(String handle : allWindows) {
		driver.switchTo().window(handle);
			}
	
	String expectedURL = "https://skimlinks.com/privacy-policies/";
	String actualURL = driver.getCurrentUrl();
	Assert.assertEquals(actualURL, expectedURL);
	driver.navigate().back();
	
	
}
//Registration without providing Name
@Test
public void noNameTest() throws InterruptedException{
	WebElement userName = driver.findElement(By.xpath("//body/div[@id='__next']/div[1]/div[1]/div[1]/form[1]/div[1]/div[2]/input[1]"));
	userName.sendKeys("");
	WebElement emailAddress = driver.findElement(By.name("email"));
	emailAddress.sendKeys("Test1@yahoo.com");
	WebElement password = driver.findElement(By.xpath("//input[@type=\'password\']"));
	password.sendKeys("Pass@123");
	WebElement agreeTickbox = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/form[1]/div[4]/label[1]/div[1]/div[1]"));
	agreeTickbox.click();
	WebElement signUp = driver.findElement(By.xpath("//button[contains(text(),'Sign up')]"));
	signUp.click();
	
	String errorMessage = "Please enter your name";
	WebElement error = driver.findElement(By.xpath("//div[contains(text(),'Please enter your name')]"));
	String actualErrorMessage = error.getText();
	
	Assert.assertEquals(actualErrorMessage, errorMessage);
	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	driver.getCurrentUrl();
	
}

//Registration using invalid email id
@Test
public void invalidEmailTest(){
	WebElement userName = driver.findElement(By.xpath("//body/div[@id='__next']/div[1]/div[1]/div[1]/form[1]/div[1]/div[2]/input[1]"));
	userName.sendKeys("P");
	
//Empty email address field	
	WebElement emailAddress1 = driver.findElement(By.name("email"));
	emailAddress1.sendKeys("");
	WebElement password1 = driver.findElement(By.xpath("//input[@type=\'password\']"));
	password1.click();
	
	String errorMessage1 = "Sorry, there's a problem. Please check the highlighted fields above.";
	WebElement error1 = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/form[1]/div[5]"));
	String actualErrorMessage1 = error1.getText();
	Assert.assertEquals(actualErrorMessage1, errorMessage1);
	System.out.println("Empty email address check: " + actualErrorMessage1);
    driver.findElement(By.name("email")).clear();


	
// Enter an email address without @ symbol
	WebElement emailAddress2 = driver.findElement(By.name("email"));
	emailAddress2.sendKeys("testyahoo.com");
	WebElement password2 = driver.findElement(By.xpath("//input[@type=\'password\']"));
	password2.click();
		
	String errorMessage2 = "Please enter a valid email. E.g: 'your@email.com'";
	WebElement error2 = driver.findElement(By.xpath("//div[contains(text(),\"Please enter a valid email. E.g: 'your@email.com'\")]"));
	String actualErrorMessage2 = error2.getText();
	
	Assert.assertEquals(actualErrorMessage2, errorMessage2);
	System.out.println("Email address without @ symbol: " + actualErrorMessage2);
	driver.navigate().refresh();
	
// Enter an email address that has a missing dot
	WebElement emailAddress3 = driver.findElement(By.name("email"));
	emailAddress3.sendKeys("test@yahoocom");
	WebElement password3 = driver.findElement(By.xpath("//input[@type=\'password\']"));
	password3.click();
			
	String errorMessage3 = "Please enter a valid email. E.g: 'your@email.com'";
	WebElement error3 = driver.findElement(By.xpath("//div[contains(text(),\"Please enter a valid email. E.g: 'your@email.com'\")]"));
	String actualErrorMessage3 = error3.getText();
		
	Assert.assertEquals(actualErrorMessage3, errorMessage3);
	System.out.println("Email address that has a missing dot: " + actualErrorMessage3);
//	driver.getCurrentUrl();
	driver.navigate().refresh();

// Enter a random string instead of a real email
	WebElement emailAddress4 = driver.findElement(By.name("email"));
	emailAddress4.sendKeys("test@yahoocom");
	WebElement password4 = driver.findElement(By.xpath("//input[@type=\'password\']"));
	password4.click();
				
	String errorMessage4 = "Please enter a valid email. E.g: 'your@email.com'";
	WebElement error4 = driver.findElement(By.xpath("//div[contains(text(),\"Please enter a valid email. E.g: 'your@email.com'\")]"));
	String actualErrorMessage4 = error4.getText();
			
	Assert.assertEquals(actualErrorMessage4, errorMessage4);
	System.out.println("Random string instead of a real email: " + actualErrorMessage4);
//	driver.getCurrentUrl();
	driver.navigate().refresh();
	
//Enter an already registered e-mail id
	WebElement emailAddress5 = driver.findElement(By.name("email"));
	emailAddress4.sendKeys("poornima.prabhakaran@gmail.com");
	WebElement password5 = driver.findElement(By.xpath("//input[@type=\'password\']"));
	password5.sendKeys("Pass@123");
	WebElement agreeTickbox = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/form[1]/div[4]/label[1]/div[1]/div[1]"));
	agreeTickbox.click();
	WebElement signUp = driver.findElement(By.xpath("//button[contains(text(),'Sign up')]"));
	signUp.click();
	WebElement siteDomain = driver.findElement(By.name("domain"));
	siteDomain.sendKeys("Test1.com");
	WebElement company = driver.findElement(By.name("company"));
	company.sendKeys("Test1Company");
	WebElement getStarted = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/form[1]/button[1]"));
	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	getStarted.click();

					
	String errorMessage5 = "Something went wrong, please try again later";
	WebElement error5 = driver.findElement(By.xpath("//div[contains(text(),'Something went wrong, please try again later')]"));
	String actualErrorMessage5 = error5.getText();
				
	Assert.assertEquals(actualErrorMessage5, errorMessage5);
	System.out.println("E-mail id is already registered: " + actualErrorMessage5);
	
}

//Registration without providing password
@Test
public void emptyPasswordTest(){
	WebElement userName = driver.findElement(By.xpath("//body/div[@id='__next']/div[1]/div[1]/div[1]/form[1]/div[1]/div[2]/input[1]"));
	userName.sendKeys("P");
	WebElement emailAddress1 = driver.findElement(By.name("email"));
	emailAddress1.sendKeys("Test2@yahoo.com");

	
//Check the password limit for minimum limit
	
	WebElement password2 = driver.findElement(By.xpath("//input[@type=\'password\']"));
	password2.sendKeys("abcdef");
		
	String expectedErrorMessage2 = "Password should be more than 6 characters long.";
	WebElement exp2 = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/form[1]/div[3]/div[3]"));
	String actualErrorMessage2 = exp2.getText();
	Assert.assertEquals(actualErrorMessage2, expectedErrorMessage2);
	System.out.println("Password with lower than minimum character limit: " + expectedErrorMessage2);
	driver.navigate().refresh();
	
//Check the password with 7 or more lower case characters
	
	WebElement password3 = driver.findElement(By.xpath("//input[@type=\'password\']"));
	password3.sendKeys("abcdefg");
			
	String expectedErrorMessage3 = "Password should contain (1) a lower-case character, (2) an upper-case character, (3) a digit and (4) a special character such as \"$\", \"!\" or \"@\".";
	WebElement exp3 = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/form[1]/div[3]/div[3]"));
	String actualErrorMessage3 = exp3.getText();
	Assert.assertEquals(actualErrorMessage3, expectedErrorMessage3);
	System.out.println("Password with 7 lower case characters: " + expectedErrorMessage3);
	driver.navigate().refresh();
	
//Check the password with only numbers
	
	WebElement password4 = driver.findElement(By.xpath("//input[@type=\'password\']"));
	password4.sendKeys("1234567");
				
	String expectedErrorMessage4 = "Password should contain (1) a lower-case character, (2) an upper-case character, (3) a digit and (4) a special character such as \"$\", \"!\" or \"@\".";
	WebElement exp4 = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/form[1]/div[3]/div[3]"));
	String actualErrorMessage4 = exp4.getText();
	Assert.assertEquals(actualErrorMessage3, expectedErrorMessage3);
	System.out.println("Password with only numbers: " + expectedErrorMessage4);
	driver.navigate().refresh();

//Empty password field	
	
	WebElement password1 = driver.findElement(By.xpath("//input[@type=\'password\']"));
	password1.sendKeys("");
		
	String expectedErrorMessage1 = "Use 7 or more characters with a mix of upper and lower case letters, numbers & symbols.";
	WebElement exp1 = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/form[1]/div[3]/div[3]"));
	String actualErrorMessage1 = exp1.getText();
	Assert.assertEquals(actualErrorMessage1, expectedErrorMessage1);
	System.out.println("Empty password field: " + expectedErrorMessage1);
	driver.navigate().refresh();	
	
}
//Invalid domain field and empty company field check
@Test
public void siteDomainValidation(){
	WebElement userName = driver.findElement(By.xpath("//body/div[@id='__next']/div[1]/div[1]/div[1]/form[1]/div[1]/div[2]/input[1]"));
	userName.sendKeys("Test1");
	WebElement emailAddress = driver.findElement(By.name("email"));
	emailAddress.sendKeys("Test1@yahoo.com");
	WebElement password = driver.findElement(By.xpath("//input[@type=\'password\']"));
	password.sendKeys("Pass@123");
	WebElement agreeTickbox = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/form[1]/div[4]/label[1]/div[1]/div[1]"));
	agreeTickbox.click();
	WebElement signUp = driver.findElement(By.xpath("//button[contains(text(),'Sign up')]"));
	signUp.click();
	WebElement siteDomain = driver.findElement(By.name("domain"));
	siteDomain.sendKeys("y");
	WebElement company = driver.findElement(By.name("company"));
	company.sendKeys("");
	company.click();
	
	
	String domainErrorMessage = "Please enter a valid domain or url path. E.g: 'your-domain.com' or 'your-domain.com/section'";
	WebElement domainE = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[3]"));
	String actualDomainError = domainE.getText();
	String emptyCompanyMessage = "Field is required";
	WebElement companyError = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/form[1]/div[2]/div[3]"));
	String actualCompanyError = companyError.getText();
	Assert.assertEquals(actualDomainError, domainErrorMessage);
	Assert.assertEquals(actualCompanyError, emptyCompanyMessage);
	System.out.println("Invalid data in domain field: " + actualDomainError);
	System.out.println("Empty Company field: " + emptyCompanyMessage );
	driver.navigate().refresh();	
	
	
}

//Empty domain field check
@Test
public void emptyDomainCheck(){
	driver.get("https://signup.skimlinks.com/");
	WebElement userName = driver.findElement(By.xpath("//body/div[@id='__next']/div[1]/div[1]/div[1]/form[1]/div[1]/div[2]/input[1]"));
	userName.click();
	userName.sendKeys("Test1");
	WebElement emailAddress = driver.findElement(By.name("email"));
	emailAddress.sendKeys("Test1@yahoo.com");
	WebElement password = driver.findElement(By.xpath("//input[@type=\'password\']"));
	password.sendKeys("Pass@123");
	WebElement agreeTickbox = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/form[1]/div[4]/label[1]/div[1]/div[1]"));
	agreeTickbox.click();
	WebElement signUp = driver.findElement(By.xpath("//button[contains(text(),'Sign up')]"));
	signUp.click();
	WebElement siteDomain = driver.findElement(By.name("domain"));
	siteDomain.sendKeys("");
	WebElement company = driver.findElement(By.name("company"));
	company.sendKeys("abc");
		
	String emptyDomainError = "Sorry, there's a problem. Please check the highlighted fields above.";
	WebElement domainErr = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/form[1]/div[3]"));
	String actDomainError = domainErr.getText();
	Assert.assertEquals(actDomainError, emptyDomainError);
	System.out.println("Empty domain field: " + actDomainError);
	driver.navigate().refresh();	
	
	
}

// Registration with valid data
@Test
public void validRegistrationTest() throws InterruptedException{
	WebElement userName = driver.findElement(By.xpath("//body/div[@id='__next']/div[1]/div[1]/div[1]/form[1]/div[1]/div[2]/input[1]"));
	userName.sendKeys("Test1");
	WebElement emailAddress = driver.findElement(By.name("email"));
	emailAddress.sendKeys("Test1@yahoo.com");
	WebElement password = driver.findElement(By.xpath("//input[@type=\'password\']"));
	password.sendKeys("Pass@123");
	WebElement agreeTickbox = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/form[1]/div[4]/label[1]/div[1]/div[1]"));
	agreeTickbox.click();
	WebElement signUp = driver.findElement(By.xpath("//button[contains(text(),'Sign up')]"));
	signUp.click();
	WebElement siteDomain = driver.findElement(By.name("domain"));
	siteDomain.sendKeys("Test1.com");
	WebElement company = driver.findElement(By.name("company"));
	company.sendKeys("Test1Company");
	WebElement getStarted = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/form[1]/button[1]"));
	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	getStarted.click();
}

//Main method
public static void main(String[] args) throws InterruptedException {
	Skimlinks reg = new Skimlinks();
	reg.SignUpBrowser();
	reg.noNameTest();
	reg.invalidEmailTest();
	reg.emptyPasswordTest();
	reg.siteDomainValidation();
	reg.emptyDomainCheck();
	reg.validRegistrationTest();
	reg.termsOfServicepage();
	reg.privacyPolicy();
	reg.programPolicies();
	
}

@AfterTest

  public void close() {
	driver.quit();

}
	

	}

		
		


