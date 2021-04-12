package tests;

import java.io.IOException;
import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import data.ExcelReader;
import pages.ResultsPage;
import pages.checkOutPage;
import pages.productDetailedPage;
import pages.searchPage;

public class TestCases extends testBase {

	
	String productName="ipad";
	searchPage searchObj;
	ResultsPage resultObj;
	productDetailedPage productObj;
	checkOutPage checkoutObj;
	
	// Getting user data from Excel sheet
	@DataProvider(name="UserData")
	public Object[][] userData() throws IOException
	{
		ExcelReader ER=new ExcelReader();
		return ER.getExcelData();
	}
	
	// Master card data
	@DataProvider(name="cardData") 
	public static Object[][] cardData()
	{
		return new Object[][]{
				{"5425233430109903","956"}
		};
	}
	
	@Test
	public void UserCanSearchUsingAutoSuggest()
	{
		searchObj=new searchPage(driver);
		searchObj.searchUsingAutoSuggest(productName);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		resultObj=new ResultsPage(driver);

		Assert.assertTrue(resultObj.productsTitle.isDisplayed());
	}
	
	
	@Test(dependsOnMethods = {"UserCanSearchUsingAutoSuggest"})
	public void userCanApplyOnlineFilter()
	{
		resultObj.applyOnlineFilter();
		// check if online filter is applied 
		Assert.assertTrue(resultObj.onlineFilterApplied.getText().contains("Online Only"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	@Test(dependsOnMethods = {"userCanApplyOnlineFilter"})
	public void userCanOpenProductPage()
	{
		productObj=new productDetailedPage(driver);
		resultObj.openProductDetailedPage();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		//Make sure than I entered product details page
		Assert.assertTrue(productObj.productDetails.isDisplayed());
	
	}
	
	@Test(dependsOnMethods = {"userCanOpenProductPage"})
	public void userAddItemToCart() throws InterruptedException
	{
		productObj=new productDetailedPage(driver);
		productObj.addToCart();
		Assert.assertTrue(productObj.sucessMessage.getText().contains("This item has been added to your cart"));
		productObj.viewCart();
		
	}
	
	
	// using user data provided from excel sheet
	@Test(dependsOnMethods = {"userAddItemToCart"},dataProvider = "UserData")
	public void userCanCheckoutProduct(String email,String fName,String LName,String city,String Address,String phoneNumber)  throws InterruptedException
	{
		checkoutObj =new checkOutPage(driver);
		
		//Check than i entered cart page
		Assert.assertEquals(checkoutObj.cartPageTitle.getText(), "Your Cart");
		checkoutObj.checkout();
		
		//Check than i entered checkout page
		Assert.assertTrue(checkoutObj.checkoutPage.getText().contains("Checkout"));
		checkoutObj.checkOutAsAGuest(email,fName,LName,city,Address, phoneNumber);
	}
	
	
	@Test(dependsOnMethods = {"userCanCheckoutProduct"},dataProvider = "cardData")
	public void userFailedToSubmitOrderWithExpiredCard(String cardNumber,String cvvNumber)
	{
		
		checkoutObj.fillPaymentDetails(cardNumber,cvvNumber);
		Assert.assertTrue(checkoutObj.errorMessage.getText().contains("The credit card you entered or selected is expired"));
		
	}
	
}
