package com.Sendeo.Mobile;


import com.Sendeo.Mobile.helper.StoreHelper;
import com.Sendeo.Mobile.model.SelectorInfo;
import com.thoughtworks.gauge.Step;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import java.util.*;

import static java.time.Duration.ofMillis;

public class StepImpl extends HookImpl {

    protected Logger logger = LoggerFactory.getLogger(getClass());
    public static int DEFAULT_MAX_ITERATION_COUNT = 70;
    public static int DEFAULT_MILLISECOND_WAIT_AMOUNT = 100;



    public MobileElement findElementByKey(String key) {
        SelectorInfo selectorInfo = selector.getSelectorInfo(key);

        MobileElement mobileElement = null;
        try {
            mobileElement = selectorInfo.getIndex() > 0 ? findElements(selectorInfo.getBy())
                    .get(selectorInfo.getIndex()) : findElement(selectorInfo.getBy());
        } catch (Exception e) {
            Assertions.fail("key = %s by = %s Element not found ", key, selectorInfo.getBy().toString());
            e.printStackTrace();
        }
        return mobileElement;
    }

    public List<MobileElement> findElements(By by) throws Exception {
        List<MobileElement> webElementList = null;
        try {
            webElementList = appiumFluentWait.until(new ExpectedCondition<List<MobileElement>>() {
                @Nullable
                @Override
                public List<MobileElement> apply(@Nullable WebDriver driver) {
                    List<MobileElement> elements = driver.findElements(by);
                    return elements.size() > 0 ? elements : null;
                }
            });
            if (webElementList == null) {
                throw new NullPointerException(String.format("by = %s Web element list not found", by.toString()));
            }
        } catch (Exception e) {
            throw e;
        }
        return webElementList;
    }

    public MobileElement findElement(By by) throws Exception {
        MobileElement mobileElement;
        try {
            mobileElement = findElements(by).get(0);
        } catch (Exception e) {
            throw e;
        }
        return mobileElement;
    }

    public MobileElement findElementWithAssertion(By by) {
        MobileElement mobileElement = null;
        try {
            mobileElement = findElement(by);
        } catch (Exception e) {
            Assertions.fail("by = %s Element not found ", by.toString());
            e.printStackTrace();
        }
        return mobileElement;
    }

    public MobileElement findElementWithoutAssert(By by) {
        MobileElement mobileElement = null;
        try {
            mobileElement = findElement(by);
        } catch (Exception e) {
            //   e.printStackTrace();
        }
        return mobileElement;
    }

    public List<MobileElement> findElementsByKey(String key) {
        SelectorInfo selectorInfo = selector.getSelectorInfo(key);
        List<MobileElement> mobileElements = null;
        try {
            mobileElements = findElements(selectorInfo.getBy());
        } catch (Exception e) {
            Assertions.fail("key = %s by = %s Elements not found ", key, selectorInfo.getBy().toString());
            e.printStackTrace();
        }
        return mobileElements;
    }




    @Step({"<key> li elementi bul ve tıkla", "Click element by <key>"})
    public void clickByKey(String key) {
        findElementByKey(key).click();
        logger.info(key + " elementine tıklandı");
    }

    @Step({"<key> li element var mı"})
    public void checkElementWithAllPageKey(String key) {
        try {
            MobileElement element = findElementByKey(key);
            logger.info(key + " keyli element sayfada bulundu.");
        } catch (Exception ex) {
        }
    }

    @Step({"<seconds> saniye bekle", "Wait <second> seconds"})
    public void waitBySecond(int seconds) {
        try {
            logger.info(seconds + " saniye bekleniyor.");
            Thread.sleep(seconds * 1000);
            logger.info(seconds + " saniye beklendi.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Step("Verilen <x> <y> koordinatına tıkla")
    public void tapElementWithCoordinate(int x, int y) {
        TouchAction a2 = new TouchAction(appiumDriver);
        a2.tap(PointOption.point(x, y)).perform();
    }

    @Step({"Textinde <text> geçen element var mı"})
    public void checkElementWithAllPageTextContains(String text) {
        try {
            MobileElement element = findElementWithAssertion(By.xpath("//*[contains(@text, '" + text + "')]"));
            logger.info("Expected Contains: " + text);
            logger.info("Screen : " + element.getText());
        } catch (Exception ex) {
            Assertions.fail("Textinde " + text + " geçen element bulunamadı.");
        }
    }

    @Step({"<attribute> değerinde <text> geçen element var mı kontrol edilir"})
    public void checkElementWithAllPageDynamicAttributeContains(String attribute, String text) {
        try {
            MobileElement element = findElementWithAssertion(By.xpath("//*[contains(@" + attribute + ", '" + text + "')]"));
            logger.info("Expected Contains: " + text);
            logger.info("Screen : " + element.getText());
        } catch (Exception ex) {
            Assertions.fail(attribute + " değeri " + text + " element bulunamadı.");
        }
    }

    @Step({"Textinde <text> geçen elementin olmadığını kontrol et"})
    public void checkElementWithAllPageTextNotContains(String text) {

        MobileElement element = findElementWithoutAssert(By.xpath("//*[contains(@text, '" + text + "')]"));

        if (element == null) {
            logger.info(text + " geçen element bulunamadı.(OK)");
        } else {
            Assert.fail(text + " geçen elementin sayfada bulunmaması gerekiyordu.!");
        }

    }

    @Step({"<attribute> değeri <text> olan element var mı kontrol edilir"})
    public void checkElementWithAllPageDynamicAttribute(String attribute, String text) {
        try {
            MobileElement element = findElementWithAssertion(By.xpath("//*[@" + attribute + "='" + text + "']"));
            logger.info("Expected Contains: " + text);
            logger.info("Screen : " + element.getText());
        } catch (Exception ex) {
            Assertions.fail(attribute + " değerinde " + text + " geçen element bulunamadı.");
        }
    }

    @Step({"<key> li elementi bul ve <text> değerini yaz"})
    public void sendKeysByKeyNotClear(String key, String text) {
        findElementByKey(key).sendKeys(text);
        logger.info(key + " elementine " + text + " değeri yazıldı.");

    }

    @Step({"<key> li elementi bul, temizle ve <text> değerini yaz"})
    public void sendKeysByKey(String key, String text) {
        MobileElement webElement = findElementByKey(key);
        webElement.click();
        webElement.clear();
        webElement.sendKeys(text);
    }

    @Step({"<key> listesinden random bir elemente tıkla"})
    public void clickRandomElementWithKey(String key) {

        List<MobileElement> elementList = findElementsByKey(key);
        Random rand = new Random();
        MobileElement element = elementList.get(rand.nextInt(elementList.size()));
        element.click();
        logger.info(key + " listesinden random bir elemente tıklandı.");
    }

    @Step({"<key> elementinin <attribute> değerini, <variable> değişkeni olarak kaydet"})
    public void saveValueWithAttributeElementForVariable(String key, String attribute, String variable) {

        MobileElement element = findElementByKey(key);

        StoreHelper.INSTANCE.saveValue(variable, element.getAttribute(attribute));
        String savedVariable = StoreHelper.INSTANCE.getValue(variable);
        logger.info("Hafızaya kaydedilen " + attribute + " value = " + savedVariable);
    }

    @Step({"<key> elementinin <attribute> değeri <variable> değişkenindeki değer ile aynı mı"})
    public void checkSavedValueEqualWithElementAttributeValueForVariavle(String key, String attribute, String variable) {

        String value = StoreHelper.INSTANCE.getValue(variable);
        String savedVariable = value.toLowerCase();
        logger.info("Hafızadaki value = " + savedVariable);

        MobileElement element = findElementByKey(key);
        String elementAttributeValue = element.getAttribute(attribute).toLowerCase();


        logger.info("Elementin " + attribute + " değeri = " + elementAttributeValue);


        Assert.assertTrue("hafızadaki değer ile elementin değeri eşleşmiyor.", elementAttributeValue.equals(savedVariable));
    }


    @Step({"<key> elementinin <attribute> değeri <value> değerini içeriyor mu"})
    public void checkWithContainsElementAttributeValue(String key, String attribute, String value) {

        String elementAttributeValue = findElementByKey(key).getAttribute(attribute).toLowerCase();
        logger.info("Elementin " + attribute + " değeri = " + elementAttributeValue);


        Assert.assertTrue(value + " değeri ile elementin değeri eşleşmiyor!", elementAttributeValue.contains(value));
    }

    @Step({"Check if element <expectedText> contains text <expectedText>"})
    public void checkElementContainsText(String key, String expectedText) {
        logger.info("Expected: " + expectedText);
        logger.info("Screen : " + findElementByKey(key).getText());
        Assert.assertTrue("Expected text is not contained", findElementByKey(key).getText().contains(expectedText));
    }


    @Step({"<attribute> değerinde <text> geçen elementi bul ve tıkla"})
    public void clickWithDinamicAttributeValueContains(String attribute, String text) {
        try {
            MobileElement element = findElementWithAssertion(By.xpath("//*[contains(@" + attribute + ", '" + text + "')]"));
            element.click();
            logger.info(attribute + " değerinde " + text + " gecen elemente tıklandı");

        } catch (Exception ex) {
            logger.info(attribute + " değerinde " + text + " gecen element bulunamadı.");
        }
    }

    @Step({"<key> elementinin <attribute> değeri <variable> değişkenindeki değeri içeriyor mu"})
    public void checkSavedValueWithContainsElementAttributeValueForVariavle(String key, String attribute, String variable) {

        String value = StoreHelper.INSTANCE.getValue(variable);
        String savedVariable = value.toLowerCase();
        logger.info("Hafızadaki value = " + savedVariable);

        MobileElement element = findElementByKey(key);
        String elementAttributeValue = element.getAttribute(attribute).toLowerCase();


        logger.info("Elementin " + attribute + " değeri = " + elementAttributeValue);


        Assert.assertTrue("hafızadaki değer ile elementin değeri eşleşmiyor.", elementAttributeValue.contains(savedVariable));
    }


    public int getColumnIndexer(String[] headerRow, String ColumnName) {


        int indexValue = 0;
        for (String item : headerRow) {

            if (item.equals(ColumnName)) {
                break;
            }

            indexValue++;
        }
        return indexValue;
    }

}


