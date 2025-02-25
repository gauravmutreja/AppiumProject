import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

public class ScrollGesture extends BaseTest{

    @Test
    public void scrollTest() throws InterruptedException {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        //Two ways to scroll:

        //1. Till where to scroll i.e. exact element is known prior
        driver.findElement(
                AppiumBy.androidUIAutomator(
                        "new UiScrollable(new UiSelector()).scrollIntoView(text(\"WebView\"));"
                )
        );
        driver.findElement(By.xpath("//android.widget.TextView[@text='WebView']")).click();

        //2. When where to scroll is unknown
//        scrollToEndAction();
    }

    public void scrollToEndAction(){
         //Use do-while loop to scroll till end of page.
        boolean canScrollMore;
        do {canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
                "left", 100, "top", 100, "width", 200, "height", 200,
                "direction", "down",
                "percent", 3.0));
        }
        while (canScrollMore);
    }

}
