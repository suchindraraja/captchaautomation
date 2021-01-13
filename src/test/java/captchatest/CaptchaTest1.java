package captchatest;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.sourceforge.tess4j.Tesseract;

public class CaptchaTest1 
{

	public static void main(String[] args) throws Exception
	{
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.get("https://www.incometaxindiaefiling.gov.in/");
		//maximize window 
		driver.manage().window().maximize();
		WebDriverWait wait=new WebDriverWait(driver,10);
		WebElement e=driver.findElement(By.xpath("(//span[text()='Close']/parent::*)[1]"));
		if(e.isDisplayed())
		{
			e.click();
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@value,'Login Here')]"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("userName"))).sendKeys("suchindra");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password"))).sendKeys("12345");
		WebElement e1=driver.findElement(By.id("captchaImg"));
		File src=e1.getScreenshotAs(OutputType.FILE);
		/*File src=driver.getScreenshotAs(OutputType.FILE);
		//take dimensions of an element
		int x=e1.getLocation().getX();
		int y=e1.getLocation().getY();
		int w=e1.getSize().getWidth();
		int h=e1.getSize().getHeight();
		//take element level screenshot
		BufferedImage bf=ImageIO.read(src);
		BufferedImage bele=bf.getSubimage(x,y,w,h);
		ImageIO.write(bele,"PNG",src);
		File dest=new File("captchaimage.png");
		FileHandler.copy(src, dest);*/
		Tesseract t=new Tesseract();
		t.setDatapath("G:\\eng.traineddata");
		String otp=t.doOCR(src);
		Thread.sleep(2000);
		driver.findElement(By.name("captchaCode")).sendKeys(otp);
		
		
		

	}

}
