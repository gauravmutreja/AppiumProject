import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SwipeGesture extends BaseTest{

    @Test
    public void swipeTest() throws InterruptedException {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Gallery")).click();
        driver.findElement((By.xpath("//android.widget.TextView[@text=\"1. Photos\"]"))).click();
        WebElement firstImage = driver.findElement(By.xpath("//android.widget.ImageView [1]"));

        Assert.assertEquals(firstImage.getDomAttribute("focusable"), "true");

//      Swipe
        swipeAction(firstImage, "left");
        Assert.assertEquals(driver.findElement(By.xpath("//android.widget.ImageView [1]"))
                .getDomAttribute("focusable"), "false");
    }

    public void swipeAction(WebElement ele, String direction){
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) ele).getId(),
                "direction", direction,
                "percent", 0.75
        ));
    }
}
