import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

public class MobileBrowser_BaseTest {

    public AppiumDriverLocalService service;
    public AndroidDriver driver;

    @BeforeClass
    public void configureAppium() throws URISyntaxException, MalformedURLException {
        service = new AppiumServiceBuilder().withAppiumJS(new File("/Users/gauravmutreja/node_modules/appium/build/lib/main.js"))
                .withIPAddress("127.0.0.1").usingPort(4723).build();
        service.start();
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("GauravEmulator");
//        options.setDeviceName("Medium Phone API 35");
        options.setChromedriverExecutable("/Users/gauravmutreja/Projects/IdeaProjects/chromedriver");
        options.setCapability("browserName","Chrome");
        options.setCapability("userAgent",
                "Mozilla/5.0 (Linux; Android 10; Pixel 4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.106 Mobile Safari/537.36");

        driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterClass
    public void tearDown() {
        //stop server
        driver.quit();
        service.stop();
    }
}
