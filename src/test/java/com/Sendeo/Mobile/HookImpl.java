package com.Sendeo.Mobile;

import com.Sendeo.Mobile.selector.Selector;
import com.Sendeo.Mobile.selector.SelectorFactory;
import com.Sendeo.Mobile.selector.SelectorType;
import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.FluentWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class HookImpl {
    private Logger logger = LoggerFactory.getLogger(getClass());
    protected static AppiumDriver<MobileElement> appiumDriver;
    protected static FluentWait<AppiumDriver<MobileElement>> appiumFluentWait;
    protected boolean localAndroid = true;
    protected static Selector selector;


    @BeforeScenario
    public void beforeScenario() throws MalformedURLException {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!! Test baslıyor !!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        if (localAndroid) {
            logger.info("Local Browser");
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities
                    .setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
            desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "android");
            desiredCapabilities
                    .setCapability(AndroidMobileCapabilityType.APP_PACKAGE,
                            "io.selendroid.testapp");
            desiredCapabilities
                    .setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,
                            "io.selendroid.testapp.HomeScreenActivity");
            desiredCapabilities
                    .setCapability(MobileCapabilityType.NO_RESET, false);
            desiredCapabilities
                    .setCapability(MobileCapabilityType.FULL_RESET, false);
            desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 3000);
            desiredCapabilities.setCapability("unicodeKeyboard", true);
            desiredCapabilities.setCapability("resetKeyboard", true);
            URL url = new URL("http://127.0.0.1:4723/wd/hub");
            appiumDriver = new AndroidDriver(url, desiredCapabilities);
        } else {
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
            desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");

            desiredCapabilities.setCapability(MobileCapabilityType.UDID, "12345678987654321234567");
            desiredCapabilities.setCapability(IOSMobileCapabilityType.BUNDLE_ID, "tempBundleId");
            desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone7");
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "14.0.1");

            desiredCapabilities.setCapability(MobileCapabilityType.NO_RESET, true);
            desiredCapabilities.setCapability(MobileCapabilityType.FULL_RESET, false);
            desiredCapabilities.setCapability("connectHardwareKeyboard", false);
            desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 300);
            desiredCapabilities.setCapability("sendKeyStrategy", "setValue");

            URL url = new URL("http://127.0.0.1:4723/wd/hub");
            appiumDriver = new IOSDriver(url, desiredCapabilities);

        }

        selector = SelectorFactory
                .createElementHelper(localAndroid ? SelectorType.ANDROID : SelectorType.IOS);
        //appiumDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        appiumFluentWait = new FluentWait<AppiumDriver<MobileElement>>(appiumDriver);
        appiumFluentWait.withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(450))
                .ignoring(NoSuchElementException.class);

    }


    @AfterScenario
    public void afterScenario() {
        if (appiumDriver != null)
            appiumDriver.quit();
    }

}