package utility;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class helper {
	//Method to take screenshot in case of test case failure

	public static void captureScreenShot(WebDriver driver,String screenshotName)
	{
		Path dest =Paths.get("./screenShots",screenshotName+".png");
		try {
			Files.createDirectories(dest.getParent());
			FileOutputStream out=new FileOutputStream(dest.toString());
			out.write(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES));
			out.close();

		} catch (IOException e) {
			System.out.println("Exception while taking screenshot"+e.getMessage());
		}

	}

}
