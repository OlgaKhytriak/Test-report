package com.epam.task3.data.dataproviders;

import com.epam.task3.data.handlers.LattersDataCreator;
import com.epam.task3.data.handlers.UsersDataReader;
import com.epam.task3.data.model.Letters;
import com.epam.task3.data.model.Users;
import com.epam.task3.data.utils.Data2ArrayConverter;
import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;

import static com.epam.task3.constant.Constant.*;

public class CustomDataProvider {
    private static final Logger LOG = Logger.getLogger(CustomDataProvider.class);
    @DataProvider(parallel = true)
    public static Object[][] getData() {
        Users users = new UsersDataReader().getUsers(USERS_XLS_FILE_PATH);
        Letters letters = new LattersDataCreator().getLetters(LETTERS_XLS_FILE_PATH);
        LOG.info(users.toString());
        LOG.info(letters.toString());
        return Data2ArrayConverter.getData(users, letters);
    }
}
