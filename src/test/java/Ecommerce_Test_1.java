import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Ecommerce_Test_1 extends BaseTest {

    @Test
    public void fillForm() throws InterruptedException {
//        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Gaurav");
//        driver.hideKeyboard();
        driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
        driver.findElement(AppiumBy.className("android.widget.Spinner")).click();
        driver.findElement(
                AppiumBy.androidUIAutomator(
                        "new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));"
                ));
        driver.findElement(By.xpath("//android.widget.TextView[@text='Argentina']")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

        //Toast Message: A pop-up which comes and disappears.
        // Properties: Always has the tag name ie. android.widget.Toast
        // Its message can be be retrieved from its "name" attribute value
        String toastMessage = driver.findElement(By.xpath("//android.widget.Toast")).getDomAttribute("name");
        Assert.assertEquals(toastMessage, "Please enter your name");



    }
}
