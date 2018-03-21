package com.epam.task3.data.dataproviders;

import com.epam.task3.data.handlers.LattersDataCreator;
import com.epam.task3.data.handlers.UsersDataReader;
import com.epam.task3.data.model.Letters;
import com.epam.task3.data.model.Users;
import com.epam.task3.data.utils.Data2ArrayConverter;
import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class GenericDataProvider {
    private static final Logger LOG = Logger.getLogger(GenericDataProvider.class);

    @DataProvider(name = "getData", parallel = true)
    public static Object[][] getData(Method method) {
        Annotation annotation=method.getAnnotation(TestData.class);

        String usersFilePath=method.getDeclaredAnnotation(TestData.class).usersFilePath();
        String lettersFilePath=method.getDeclaredAnnotation(TestData.class).lettersFilePath();

        Users users = new UsersDataReader().getUsers(usersFilePath);
        Letters letters = new LattersDataCreator().getLetters(lettersFilePath);
        return Data2ArrayConverter.getData(users, letters);
    }
}
