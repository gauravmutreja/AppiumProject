import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class AppiumBasic extends BaseTest {

    @Test
    public void wifiSettingName() throws URISyntaxException, MalformedURLException {
        // Execution process: Appium Code => Appium Server => Mobile
        //Set WiFi Name

        driver.findElement(AppiumBy.accessibilityId("Preference")).click();
        driver.findElement(By.xpath("//android.widget.TextView[@content-desc='3. Preference dependencies']")).click();
        driver.findElement(By.id("android:id/checkbox")).click();
        driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click();

        //Grab text and put assertion
        String alertText = driver.findElement(By.id(("android:id/alertTitle"))).getText();
        Assert.assertEquals(alertText, "WiFi settings");

        driver.findElement(By.id(("android:id/edit"))).sendKeys("Gaurav WI-FI");
        driver.findElements(AppiumBy.className("android.widget.Button")).get(1).click();
    }
}
