package data;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

// spring boot에서 하기 전 연습
public class Naver {
	// 드라이버 id 및 경로
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_PATH = "C://selenium/chromedriver.exe";
	
	public static void main(String[] args) throws Exception {
		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH); // 드라이버 설정
		
		ChromeOptions co = new ChromeOptions();
//		co.addArguments("headless"); // 크롬창이 눈에 보이지 않음
		WebDriver driver = new ChromeDriver(co);
		
		try {
			driver.get("https://search.naver.com/search.naver?where=nexearch&sm=tab_etc&mra=bjBC&qvt=0&query=%EC%A0%84%EC%8B%9C%ED%9A%8C");
			Thread.sleep(1000); // 브라우저가 로드되도록 1초 기다리기
			
			String lastPage = driver.findElement(By.xpath("//*[@id=\"main_pack\"]/div[2]/div[2]/div/div/div[3]/div/span/span[3]")).getText(); 
			System.out.println("총 페이지 수 : " + lastPage);
			
			for(int page=1; page<=4/*Integer.parseInt(lastPage)*/; page++) {
				// 크롤링
				List<WebElement> title = driver.findElements(By.cssSelector("div.title > div > strong > a"));
				List<WebElement> info = driver.findElements(By.cssSelector("dl.info_group > dd.no_ellip"));
				
				for(int i=0; i<title.size(); i++) {
					String t = title.get(i).getText();
					String p = info.get(i+i).getText();
					String l = info.get(i+i+1).getText();
					
					String start = p.substring(0, 11);
					String end = p.substring(12);
					
					System.out.println(t + "   " + start+end + "   " + l);
				}
				
				// 다음 페이지로 이동
				driver.findElement(By.cssSelector("div.cm_paging_area._page > div > a.pg_next.on")).click();
				Thread.sleep(1000);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
			
//		driver.close();	// 드라이버 연결 해제
//		driver.quit();	// 프로세스 종료
	}
}