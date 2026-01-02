package com.example.sampleservlet.dao;

import com.example.sampleservlet.model.Offer;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OfferDaoTest {

    private OfferDaoImpl offerDao;
    private Offer testOffer;

    @BeforeEach
    void setUp() {
        offerDao = new OfferDaoImpl();

        testOffer = new Offer();
        testOffer.setOfferId("OFF1001");
        testOffer.setOfferName("New Year Loan Offer");
        testOffer.setProductType("Personal Loan");
        testOffer.setCustomerType("Retail");
        testOffer.setMinLoanAmount("50000");
        testOffer.setMaxLoanAmount("500000");
        testOffer.setTenure("24");
        testOffer.setInterestRate("10.5");
        testOffer.setInterestRateType("Fixed");
        testOffer.setCreditScore("750");
        testOffer.setOfferValidFrom("2026-01-01");
        testOffer.setOfferValidTo("2026-12-31");

        offerDao.deleteOfferById("OFF1001");
    }


    @Test
    void shouldSaveOffer() {
        String result = offerDao.saveOffer(testOffer);
        assertEquals("Offer uploaded", result);
    }

    @Test
    void shouldGetOffer() {
        offerDao.saveOffer(testOffer);

        List<Offer> offers = offerDao.getOffer();

        assertNotNull(offers);
        assertTrue(offers.size() > 0);
    }

    @Test
    void shouldUpdateOffer() {
        offerDao.saveOffer(testOffer);

        testOffer.setOfferName("Updated Loan Offer");

        String result = offerDao.updateOffer(testOffer);

        assertEquals("Offer updated successfully", result);
    }

    @Test
    void shouldDeleteOffer() {
        offerDao.saveOffer(testOffer);

        String result = offerDao.deleteOffer(testOffer.getOfferId());

        assertEquals("Offer deleted successfully", result);
    }

}
