import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class VideoUploadFirefox {

	
	@Test(invocationCount = 1)
	public void testVideoUploadFirefox() throws AWTException, InterruptedException
	{
		WebDriver driver = new FirefoxDriver();
		
		driver.get("https://qatest.workplace.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		
		//login flow
		driver.findElement(By.xpath("//*[@name=\"email\"]")).sendKeys("bhaktadeepak+dyi@debugg14.wptst.com");
		driver.findElement(By.xpath("//*[text()=\"Continue\"]")).click();
		driver.findElement(By.xpath("//*[@name=\"pass\"]")).sendKeys("lsspl@123");
		driver.findElement(By.xpath("//*[text()=\"Continue\"]")).click();
		
		//navigate to group 
		driver.findElement(By.xpath("//ul[@aria-label='Pinned groups']/div/li/div/a")).click();
		driver.findElement(By.xpath("//*[text() = 'Image/video']")).click();
			
		
		String filePath = "C:\\Users\\debug\\Downloads\\sample.mp4";
		StringSelection s = new StringSelection(filePath);
		
		 Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
         clipboard.setContents(s, null);
         
         Thread.sleep(2000);  // Wait for the file dialog to open
         Robot robot = new Robot();
         String os = System.getProperty("os.name").toLowerCase();
         if (os.contains("mac")) {
             robot.keyPress(KeyEvent.VK_META); // Command key
         } else {
             robot.keyPress(KeyEvent.VK_CONTROL); // Control key
         }
         robot.keyPress(KeyEvent.VK_V);
         robot.keyRelease(KeyEvent.VK_V);
         if (os.contains("mac")) {
             robot.keyRelease(KeyEvent.VK_META);
         } else {
             robot.keyRelease(KeyEvent.VK_CONTROL);
         }
         robot.keyPress(KeyEvent.VK_ENTER);
         robot.keyRelease(KeyEvent.VK_ENTER);
         
         WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));
         w.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@aria-label='Post']//div[@class='x6s0dn4 x78zum5 xl56j7k x1608yet xljgi0e x1e0frkt']")));
         
         Actions a = new Actions(driver);
         WebElement postBttn = driver.findElement(By.xpath("//div[@aria-label='Post']//div[@class='x6s0dn4 x78zum5 xl56j7k x1608yet xljgi0e x1e0frkt']"));
         a.moveToElement(postBttn).click().build().perform();
             
         driver.close();
	}
}
