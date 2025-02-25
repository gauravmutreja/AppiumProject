import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

public class AppiumBasicswithoutBase {

    @Test
    public void AppiumTest() throws URISyntaxException, MalformedURLException {
        //Code to start server instead of explicitly starting from terminal

        // Execution process: Appium Code => Appium Server => Mobile

        //Start driver first: AndroidDriver, IOSDriver
        AppiumDriverLocalService service  = new AppiumServiceBuilder()
                .withAppiumJS(new File("/Users/gauravmutreja/node_modules/appium/build/lib/main.js"))
                .withIPAddress("127.0.0.1")
                .usingPort(4723).build();
        service.start();
        UiAutomator2Options options  = new UiAutomator2Options();
        options.setDeviceName("GauravEmulator");
        options.setApp("/Users/gauravmutreja/Projects/IdeaProjects/AppiumProject/src/test/resources/ApiDemos-debug.apk");
        AndroidDriver driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(),options);

        //Actual Automation
        driver.findElement(AppiumBy.accessibilityId("Preference")).click();


        //stop server
        driver.quit();
        service.stop();

    }
}
