package Base;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseClass {

	public static WebDriver driver;
	public static ResourceBundle rb;
	public static Logger log;
	@BeforeSuite
	public void openURL() {
		rb= ResourceBundle.getBundle("config");
		log= LogManager.getLogger(this.getClass());
		driver = new ChromeDriver();
		driver.get(rb.getString("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
	}
	
	@AfterSuite
	public void closeTheUrl() {
		driver.close();
	}
	
	public String screenshot(String tname) throws IOException {
		String timestamp= new SimpleDateFormat("yyyy.mm.dd.hh.mm.ss").format(new Date());
		TakesScreenshot ts= (TakesScreenshot)driver;
		File source =ts.getScreenshotAs(OutputType.FILE);
		String dest= System.getProperty("user.dir")+"\\ScreenShot\\"+timestamp+"_"+tname+"_img.png";
		try {
		FileUtils.copyFile(source, new File(dest));
		}catch(Exception e){
			e.getMessage();
		}
		
		return dest;
		
	}
	
	
}
