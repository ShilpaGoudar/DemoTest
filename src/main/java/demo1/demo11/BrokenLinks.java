package demo1.demo11;

import java.net.HttpURLConnection;

import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrokenLinks {

	public static void main(String[] args) throws Exception {
	
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("http://www.deadlinkcity.com/");
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("http://www.deadlinkcity.com/");
		
		List <WebElement> links = driver.findElements(By.tagName("a"));
		System.out.println(links.size());
		
		for(int i=0; i<links.size(); i++)
		{
			WebElement element = links.get(i);
			String url = element.getAttribute("href");
			
			URL link = new URL(url);
			
			HttpURLConnection httpConn = (HttpURLConnection)link.openConnection();
			Thread.sleep(3000);
			httpConn.connect();
			
			int resCode = httpConn.getResponseCode();
			
			if(resCode>=400)
			{
				System.out.println(url + " :   "+ "is a broken link");
			}
			else
			{
				System.out.println(url + " :   "+ "is a valid link");
			}
		}

	}

}
