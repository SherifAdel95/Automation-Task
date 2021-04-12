package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class productDetailedPage extends pageBase {

	public productDetailedPage(WebDriver driver) {
		super(driver);
	jse=(JavascriptExecutor)driver;
		// TODO Auto-generated constructor stub
	}

	@FindBy(css="span.x-crumb")
	public WebElement productDetails;

	@FindBy(className = "addToCartContainer_20u-G")
	WebElement AddToCartBtn;
	
	@FindBy(css="h1")
	public WebElement sucessMessage;
	
	@FindBy(css="button.button_E6SE9.primary_1oCqK.viewCart.regular_1jnnf.fitContainer_1nk1i")
	WebElement viewCartBtn;
	
	
	public void addToCart() throws InterruptedException
	{
		scrollToBottom();
		Thread.sleep(2000);
		clickButton(AddToCartBtn);
		Thread.sleep(5000);
		
	}
	public void viewCart()
	{
		clickButton(viewCartBtn);
	}
	
}
