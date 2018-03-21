package com.epam.task3;

import com.epam.task3.businessobjects.DraftsBO;
import com.epam.task3.businessobjects.GmailLogInBO;
import com.epam.task3.data.dataproviders.CustomDataProvider;
import com.epam.task3.data.model.Letter;
import com.epam.task3.data.model.User;
import com.epam.task3.driver.SafeThreadDriverCreator;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class DraftGmailTest {
    private SoftAssert softAssert = new SoftAssert();
    private static final Logger LOG = Logger.getLogger(DraftGmailTest.class);

    @Test(dataProvider = "getData", dataProviderClass = CustomDataProvider.class)
    public void draftSentLetterTest(User user, Letter letter) {
        LOG.info("++ User begins to login ++++++++++++++++++ ");
        GmailLogInBO gmailLogInBO = new GmailLogInBO();
        gmailLogInBO.openLoginPage();
        gmailLogInBO.login(user);
        softAssert.assertTrue(gmailLogInBO.isUserLoggedIn(), "User is logged in");
        LOG.info("++ User finishes login ++++++++++++++++++ ");

        LOG.info("++ User begins to create draft ++++++++++++++++++ ");
        DraftsBO draftsBO = new DraftsBO();
        draftsBO.createDraft(letter);
        draftsBO.openDrafts();
        softAssert.assertTrue(draftsBO.isMessageInDrafts(letter), "The message was created as a draft");
        LOG.info("++ User finishes to create draft ++++++++++++++++++ ");

        LOG.info("++ User begins to sent draft ++++++++++++++++++ ");
        draftsBO.sendMessageFromDrafts(letter);
        draftsBO.openSentMails();
        softAssert.assertTrue(draftsBO.isMessageInSent(letter), "The draft was sent and foun in sent letters");
        LOG.info("++ User finishes to sent draft ++++++++++++++++++ ");
        softAssert.assertAll();

    }

    @AfterMethod
    public void driverQuit() {
        SafeThreadDriverCreator.getInstance().removeDriver();
    }

}
