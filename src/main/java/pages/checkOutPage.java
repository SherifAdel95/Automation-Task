package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class checkOutPage extends pageBase {

	public checkOutPage(WebDriver driver) {
		super(driver);
		jse=(JavascriptExecutor)driver;
	}

	@FindBy(className = "title_3A6Uh")
	public WebElement cartPageTitle;
	
	@FindBy(xpath = "//*[@id=\"root\"]/div/div[4]/div[2]/div[2]/section/div/main/section/section[2]/div[2]/div/a")
	WebElement checkoutBtn;
	
	@FindBy(css="h1.page-title")
	public WebElement checkoutPage;
	
	@FindBy(css="a._3Po4I.guest-continue-link")
	WebElement continueAsguestBtn;
	
	@FindBy(id="email")
	WebElement emailTxt;
	
	@FindBy(id="firstName")
	WebElement firstNameTxt;
	
	@FindBy(id="lastName")
	WebElement lastNameTxt;
	
	@FindBy(id="addressLine")
	WebElement addressLineTxt;
	
	@FindBy(id="city")
	WebElement cityTxt;
	
	@FindBy(id="phone")
	WebElement phoneNumberTxt;
	
	@FindBy(css="button.button_E6SE9.primary_1oCqK.continue-to-payment.regular_1jnnf")
	WebElement continuebtn;
	

	@FindBy(id="shownCardNumber")
	WebElement cardNumberTxt;
	
	@FindBy(id="expirationMonth")
	WebElement expirationMonthTxt;
	
	@FindBy(id="expirationYear")
	WebElement expirationYearTxt;
	
	@FindBy(id="cvv")
	WebElement cvvTxt;
	
	@FindBy(css="button.button_E6SE9.primary_1oCqK.continue-to-review.regular_1jnnf")
	WebElement continueToReviewBtn;
	
	@FindBy(css="div.contentContainer_U9oxR")
	public WebElement errorMessage;
	
	public void checkout() throws InterruptedException
	{
		scrollToBottom();
		Thread.sleep(2000);
		clickButton(checkoutBtn);
	}

	public void checkOutAsAGuest(String email,String firstName,String lastName,
			String city,String address,String phoneNumber) throws InterruptedException
	{
		clickButton(continueAsguestBtn);
		Thread.sleep(4000);
		setElementTxt(emailTxt, email);
		setElementTxt(firstNameTxt, firstName);
		setElementTxt(lastNameTxt, lastName);
		setElementTxt(addressLineTxt, address);
		setElementTxt(cityTxt, city);
		setElementTxt(phoneNumberTxt, phoneNumber);
		clickButton(continuebtn);
	}
		
	public void fillPaymentDetails(String CardNumber,String cvvNumber)
	{
		setElementTxt(cardNumberTxt, CardNumber);
	
		Select expiryDate=new Select(expirationMonthTxt);
		expiryDate.selectByValue("2");
		
		Select expiryYear=new Select(expirationYearTxt);
		expiryYear.selectByValue("2021");
		setElementTxt(cvvTxt, cvvNumber);
		clickButton(continueToReviewBtn);
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

