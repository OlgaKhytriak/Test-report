package com.epam.task3;

import com.epam.task3.businessobjects.DraftsBO;
import com.epam.task3.businessobjects.GmailLogInBO;
import com.epam.task3.data.dataproviders.CustomDataProvider;
import com.epam.task3.data.dataproviders.GenericDataProvider;
import com.epam.task3.data.dataproviders.TestData;
import com.epam.task3.data.model.Letter;
import com.epam.task3.data.model.User;
import com.epam.task3.driver.SafeThreadDriverCreator;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.epam.task3.constant.Constant.LETTERS_CSV_FILE_PATH;
import static com.epam.task3.constant.Constant.USERS_WRONG_CSV_FILE_PATH;
import static org.testng.AssertJUnit.assertTrue;

public class MustFailTest {
    private static final Logger LOG = Logger.getLogger(MustFailTest.class);

        @TestData(usersFilePath=USERS_WRONG_CSV_FILE_PATH, lettersFilePath =LETTERS_CSV_FILE_PATH )
        @Test(dataProvider = "getData", dataProviderClass = GenericDataProvider.class)
        public void loginWrondTest(User user, Letter letter) {
            LOG.info(user.toString());
            GmailLogInBO gmailLogInBO = new GmailLogInBO();
            gmailLogInBO.openLoginPage();
            gmailLogInBO.login(user);
            assertTrue(gmailLogInBO.isUserLoggedIn());
        }

    @AfterMethod
    public void driverQuit() {
        SafeThreadDriverCreator.getInstance().removeDriver();
    }

}
