package com.epam.task3.businessobjects;

import com.epam.task3.data.model.Letter;
import com.epam.task3.pages.HomePage;
import com.epam.task3.pages.SentPage;
import org.apache.log4j.Logger;

import com.epam.task3.pages.DraftsPage;

public class DraftsBO {
    private static final Logger LOG = Logger.getLogger(DraftsBO.class);
    private DraftsPage draftsPage;
    private HomePage homePage;
    private SentPage sentPage;

    public DraftsBO() {
        draftsPage = new DraftsPage();
        homePage = new HomePage();
        sentPage = new SentPage();
    }
    public void openDrafts() {
        LOG.info(String.format(" ---- %s.openDrafts() ----- ", this.getClass().getSimpleName()));
        draftsPage.openPage();
    }

    public void createDraft(Letter letter) {
        LOG.info(String.format(" ---- %s.createDraft() ----- ", this.getClass().getSimpleName()));
        homePage.craeteMessage(letter.getSentTo(), letter.getMessageSubject(), letter.getMessageText());
    }

    public boolean isMessageInDrafts(Letter letter) {
        LOG.info(String.format(" ---- %s.isMessageInDrafts() ----- ", this.getClass().getSimpleName()));
        return draftsPage.isMasageFound(letter.getMessageSubject());
    }

    public void sendMessageFromDrafts(Letter letter) {
        LOG.info(" ---- DraftsBO.sendMessageFromDrafts() ---- ");
        draftsPage.sendMasageFound(letter.getMessageSubject());
    }

    public void openSentMails() {
        LOG.info(String.format(" ---- %s.openSentMails() ----- ", this.getClass().getSimpleName()));
        sentPage.openPage();
    }

    public boolean isMessageInSent(Letter letter) {
        LOG.info(String.format(" ---- %s.isMessageInSent() ----- ", this.getClass().getSimpleName()));
        return sentPage.isMasageFound(letter.getMessageSubject());
    }


}
