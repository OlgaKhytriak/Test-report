package com.epam.task3.businessobjects;

import com.epam.task3.data.model.User;
import org.apache.log4j.Logger;

import com.epam.task3.pages.LogInPage;
import com.epam.task3.pages.PasswordPage;
import com.epam.task3.pages.HomePage;

public class GmailLogInBO {
    private static final Logger LOG = Logger.getLogger(GmailLogInBO.class);
    private LogInPage logInPage;
    private HomePage homePage;

    public GmailLogInBO() {
        logInPage = new LogInPage();
        homePage = new HomePage();
    }

    public void openLoginPage() {
        LOG.info(String.format(" ---- %s.openLoginPage() ----- ", this.getClass().getSimpleName()));
        logInPage.openPage();
    }

    public void login(User user) {
        LOG.info(String.format(" ---- %s.login() ----- ", this.getClass().getSimpleName()));
        PasswordPage passwordPage = logInPage.inputLogIn(user.getUserLogin());
        passwordPage.inputPassword(user.getUserPassword());
    }

    public boolean isUserLoggedIn() {
        LOG.info(String.format(" ---- %s.isUserLoggedIn() ----- ", this.getClass().getSimpleName()));
        return homePage.isOpened();
    }

}
