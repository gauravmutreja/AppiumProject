import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class Ecommerce_Test_2 extends BaseTest {

    @Test
    public void fillForm() throws InterruptedException {
        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Gaurav");
        driver.hideKeyboard();
        driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
        driver.findElement(AppiumBy.className("android.widget.Spinner")).click();
        driver.findElement(
                AppiumBy.androidUIAutomator(
                        "new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));"
                ));
        driver.findElement(By.xpath("//android.widget.TextView[@text='Argentina']")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

        //Add to Cart
        driver.findElement(
                AppiumBy.androidUIAutomator(
                        "new UiScrollable(new UiSelector()).scrollIntoView(text(\"Converse All Star\"));"
                ));
        int productCount = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();
        System.out.println("productCount: " + productCount);


        for (int i = 0; i < productCount; i++) {
            String productName = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();
            if (productName.equalsIgnoreCase("Converse All Star")) {
                driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
                break;
            }
        }
        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();

        //        Wait until Cart page appears , only then assert if product is added to cart is correct or not.
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.attributeContains(
                driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")),"text","Cart"));

        String AddedProduct = driver.findElement(By.id("com.androidsample.generalstore:id/productName")).getText();
        Assert.assertEquals(AddedProduct,"Converse All Star");

    }
}
