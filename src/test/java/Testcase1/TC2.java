package Testcase1;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import Base.BaseClass;

public class TC2 extends BaseClass {
	@Test
	void method2(){
		driver.findElement(By.xpath("//div[text()='Orders']")).click();
		 
	 }
}
