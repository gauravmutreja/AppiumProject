import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class MiscellanousActivities extends BaseTest {

    @Test
    public void MiscActions() throws URISyntaxException, MalformedURLException, InterruptedException {
        // Execution process: Appium Code => Appium Server => Mobile
        //Set WiFi Name

        //Go directly to Preference Dependencies Page - Using app package and app activity
        Activity activity = new Activity("io.appium.android.apis","io.appium.android.apis.preference.PreferenceDependencies");
        goToPage( activity);
        driver.findElement(By.id("android:id/checkbox")).click();

        //Turn potrait to lanscape mode
        DeviceRotation landscape = new DeviceRotation(0,0,90);
        driver.rotate(landscape);

        driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click();

        //Copy on clipboard -> paste it from clipboard
        driver.setClipboardText("Gaurav WI-FI");
        driver.findElement(By.id(("android:id/edit"))).sendKeys(driver.getClipboardText());

        //KeyEvents
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
        driver.findElements(AppiumBy.className("android.widget.Button")).get(1).click();
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        driver.pressKey(new KeyEvent(AndroidKey.HOME));
        Thread.sleep(1000);
    }

    public void goToPage(Activity activity){
        ((JavascriptExecutor) driver).executeScript("mobile: startActivity", ImmutableMap.of(
                "intent",
                "io.appium.android.apis/io.appium.android.apis.preference.PreferenceDependencies"
        ));

    }

    //driver.startActivity(new Activity("com.example", "ActivityName"));
}
