package com.Sendeo.Mobile;

import au.com.bytecode.opencsv.CSVReader;
import com.Sendeo.Mobile.helper.RandomString;
import com.Sendeo.Mobile.helper.StoreHelper;
import com.Sendeo.Mobile.model.SelectorInfo;
import com.google.common.collect.ImmutableMap;
import com.thoughtworks.gauge.Step;

import javax.annotation.Nullable;
import java.io.FileReader;
import java.sql.Timestamp;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.time.Duration.ofMillis;

public class SendeoSpesificSteps extends StepImpl {

    @Step({"<csvName> isimli dosyadan bir kayıt ile kullanıcı kayıt sayfası verileri doldurulur ve tutulur",})
    public void sendKeysUserValueWithCsvFile(String csvName) {

        try {
            CSVReader reader = new CSVReader(new FileReader(csvName));
            List<String[]> allData = new ArrayList<>();
            String[] nextRecord;

            while ((nextRecord = reader.readNext()) != null) {
                allData.add(nextRecord);
            }

            Random rand = new Random();
            int randomRowIndex = rand.nextInt(allData.size());
            randomRowIndex = (randomRowIndex == 0) ? 1 : randomRowIndex;

            String[] randomRecord = allData.get(randomRowIndex);
            String[] headerRow = allData.get(0);

            String userName = randomRecord[getColumnIndexer(headerRow, "UserName")];
            StoreHelper.INSTANCE.saveValue("userName", userName);

            String email = randomRecord[getColumnIndexer(headerRow, "Email")];
            StoreHelper.INSTANCE.saveValue("email", email);

            String password = randomRecord[getColumnIndexer(headerRow, "Password")];
            StoreHelper.INSTANCE.saveValue("password", password);

            String name = randomRecord[getColumnIndexer(headerRow, "Name")];
            StoreHelper.INSTANCE.saveValue("name", name);

            findElementByKey("RegisterPage_UserName").sendKeys(userName);
            logger.info("RegisterPage_UserName elementine " + userName + " değeri yazıldı.");

            findElementByKey("RegisterPage_Email").sendKeys(email);
            logger.info("RegisterPage_Email elementine " + email + " değeri yazıldı.");

            findElementByKey("RegisterPage_Password").sendKeys(password);
            logger.info("RegisterPage_Password elementine " + password + " değeri yazıldı.");

            findElementByKey("RegisterPage_Name").clear();
            findElementByKey("RegisterPage_Name").sendKeys(name);
            logger.info("RegisterPage_Name elementine " + name + " değeri yazıldı.");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}


