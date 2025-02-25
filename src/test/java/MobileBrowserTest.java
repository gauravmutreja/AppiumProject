import org.openqa.selenium.*;
import org.testng.annotations.Test;

import java.time.Duration;

public class MobileBrowserTest extends MobileBrowser_BaseTest {

    @Test
    public void browserTest() throws InterruptedException {
        driver.get("http://google.com");
        driver.manage().addCookie(new Cookie("SOCS","CAISHAgBEhJnd3NfMjAyNTAyMTItMF9SQzEaAmVuIAEaBgiAvtS9Bg"));
        driver.navigate().refresh();
//        Thread.sleep(4000);
        sendKeysSlowlyWithDelay(driver.findElement(By.name("q")),"Indian King");
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
        Thread.sleep(2000);
//        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
//        driver.getCurrentUrl();
//        driver.getTitle();
//        driver.findElement(By.cssSelector("[class='navbar-toggler-icon']")).click();
//        Thread.sleep(2000);
//        driver.findElement(By.cssSelector("li a[routerlink='/products']")).click();
//        ((JavascriptExecutor)driver).executeScript("window.scrollBy(0,1000)","");
//        String text = driver.findElement(By.xpath("//a[contains(@href,'products/3')]")).getText();
//        System.out.println("text = " + text);
//        Assert.assertEquals(text,"Devops");
//        Thread.sleep(2000);
    }

    private void sendKeysSlowlyWithDelay(WebElement element, String value) throws InterruptedException {
        char[] charArray = value.toCharArray();
        int index = 0;
        while(true){
            if(index>=charArray.length) break;
            element.sendKeys(String.valueOf(charArray[index++]));
            Thread.sleep(500);
        }
    }
}