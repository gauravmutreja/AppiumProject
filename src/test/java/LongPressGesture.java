import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LongPressGesture extends BaseTest{

    @Test
    public void longPressTest() throws InterruptedException {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(By.xpath("//android.widget.TextView[@content-desc='Expandable Lists']")).click();
        driver.findElement(AppiumBy.accessibilityId("1. Custom Adapter")).click();
        WebElement ele = driver.findElement(By.xpath("//android.widget.TextView[@text='People Names']"));
        longPressAction(ele);
        //Grab and assert text
        String menu = driver.findElement(By.id("android:id/title")).getText();
        Assert.assertEquals(menu,"Sample menu");
        Assert.assertTrue(driver.findElement(By.id("android:id/title")).isDisplayed());

    }

    public void longPressAction(WebElement ele){
        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) ele).getId(),
                "duration",2000
        ));
    }

}
