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

public class BaseTest {

    public AppiumDriverLocalService service;
    public AndroidDriver driver;

    @BeforeClass
    public void configureAppium() throws URISyntaxException, MalformedURLException {
        String ipAddress = "127.0.0.1";
        int portNumber = 4723;
        String hostname = "http://" + ipAddress + ":" + portNumber;
        String host = String.format("http://%s:%d", ipAddress, portNumber);
        service = new AppiumServiceBuilder()
                .withAppiumJS(new File("/Users/gauravmutreja/node_modules/appium/build/lib/main.js"))
                .withIPAddress(ipAddress)
                .usingPort(portNumber)
                .build();
        service.start();
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("GauravEmulator");
        options.setApp("/Users/gauravmutreja/Projects/IdeaProjects/AppiumProject/src/test/resources/ApiDemos-debug.apk");
//        options.setApp("/Users/gauravmutreja/Projects/IdeaProjects/AppiumProject/src/test/resources/General-Store.apk");
        options.setChromedriverExecutable("/Users/gauravmutreja/Projects/IdeaProjects/chromedriver");
        driver = new AndroidDriver(new URI(host).toURL(), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterClass
    public void tearDown() {
        //stop server
        driver.quit();
        service.stop();
    }
}
