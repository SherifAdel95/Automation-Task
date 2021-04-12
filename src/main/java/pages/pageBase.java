package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class pageBase {
protected WebDriver driver;
public JavascriptExecutor jse;
public Select select;
public Actions action;
	//create constructor
	public pageBase(WebDriver driver)
	{
		// Intialize elements of the page
		PageFactory.initElements(driver,this);
	}
	
	protected static void clickButton(WebElement button)
	{
		button.click();
	}
	protected static void setElementTxt(WebElement element,String value)
	{
		element.sendKeys(value);
	}
	protected void scrollToBottom()
	{
		jse.executeScript("scrollBy(0,300)");
		
	}
	protected void clearText(WebElement element)
	{
		element.clear();
	}
	
}
