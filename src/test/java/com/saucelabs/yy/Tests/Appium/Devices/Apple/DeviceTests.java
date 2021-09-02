package com.saucelabs.yy.Tests.Appium.Devices.Apple;

import com.saucelabs.yy.Tests.Appium.Devices.OCR;
import org.openqa.selenium.OutputType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.Locale;

public class DeviceTests extends TestBase {

    /**
     * Run the test twice to increase chance to also hit the same device model but different device
     */
    @Test(dataProvider = "RDCDataProvider")
    public void checkForSignedInAppleID(String platform, String deviceName, String platformVersion, Method methodName) throws MalformedURLException {
        createDriver(platform, deviceName, platformVersion, methodName.getName());

        driver.get().activateApp("com.apple.Preferences");
        long startTime = System.currentTimeMillis();
        //String result = ocr.get().getText(driver.get().getScreenshotAs(OutputType.FILE)).toLowerCase(Locale.ROOT);
        OCR ocr = new OCR();
        String result = ocr.getText(driver.get().getScreenshotAs(OutputType.FILE)).toLowerCase(Locale.ROOT);
        long endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds");

        Assert.assertTrue(result.contains("sign in to") && result.contains("testobject"), deviceName + System.lineSeparator() + result);
    }
}
