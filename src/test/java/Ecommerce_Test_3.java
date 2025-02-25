import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class Ecommerce_Test_3 extends BaseTest {

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

//        Add two items to Cart: can be done using xpath with index or find elements with get() method
//        1.Method
//        driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();
//        driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();

//        ###########  Not WOrking ###############
//        driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(0).click();//using get(0) cus now the first one is replaced to "Added to Cart", so only one item exist
//        driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(0).click();//using get(0) cus now the first one is replaced to "Added to Cart", so only one item exist

//        2.Method
        driver.findElement(By.xpath("(//android.widget.TextView[@text='ADD TO CART'])[1]")).click();
        driver.findElement(By.xpath("(//android.widget.TextView[@text='ADD TO CART'])[1]")).click();


        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();

        //        Wait until Cart page appears , only then assert if product added to cart is correct or not.
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.attributeContains(
                driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")), "text", "Cart"));
        List<WebElement> productPrices = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"));
        int count = productPrices.size();
        double totalAmt = 0;
        for (int i = 0; i < count; i++) {
            String priceText = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(i).getText();
            double formattedPrice = getFormattedAmt(priceText);
            totalAmt += formattedPrice;
        }
        System.out.println("Total formattedPrice = " + totalAmt);
        String DisplayedAmt = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
        Double formattedDisplayedAmt = getFormattedAmt(DisplayedAmt);
        System.out.println("FormattedDisplayedAmt = " + formattedDisplayedAmt);
        Assert.assertEquals(totalAmt, formattedDisplayedAmt);

        //Long Press T&C
        WebElement ele = driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));
        longPressAction(ele);
        driver.findElement(AppiumBy.className("android.widget.Button")).click();
        driver.findElement(AppiumBy.className("android.widget.CheckBox")).click();
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnProceed")).click();
        Thread.sleep(6000);

        //Switch Context from native app to webview
        Set<String> contexts = driver.getContextHandles(); // to get names of all context available
        for (String context : contexts) {
            System.out.println(context);
        }
        driver.context("WEBVIEW_com.androidsample.generalstore");
        System.out.println("driver = " + driver.getCurrentUrl());

        driver.manage().addCookie(new Cookie("SOCS","CAISHAgBEhJnd3NfMjAyNTAyMTItMF9SQzEaAmVuIAEaBgiAvtS9Bg"));
        driver.navigate().refresh();
//        Thread.sleep(5000);   // Manually scroll and click accept and the rest of the code will execute just fine. :)
        driver.findElement(By.name("q")).sendKeys("Indian King");
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        driver.context("NATIVE_APP");
        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Gaurav");


    }

    public Double getFormattedAmt(String amt) {
        Double price = Double.parseDouble(amt.substring(1));
        return price;
    }

    public void longPressAction(WebElement ele) {
        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) ele).getId(), "duration", 2000
        ));
    }
}
