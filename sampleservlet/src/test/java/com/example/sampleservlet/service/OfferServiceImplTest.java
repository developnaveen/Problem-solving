package com.example.sampleservlet.service;

import com.example.sampleservlet.dao.OfferDaoImpl;
import com.example.sampleservlet.model.Offer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OfferServiceImplTest {

    private OfferServiceImpl offerService;
    private Offer offer;

    @BeforeEach
    void setUp() {
        OfferDaoImpl offerDao = new OfferDaoImpl();
        offerService = new OfferServiceImpl(offerDao);

        offer = new Offer();
        offer.setOfferId("OFF_TEST_1001");
        offer.setOfferName("JUnit Offer");
        offer.setProductType("LOAN");
        offer.setCustomerType("SALARIED");
        offer.setMinLoanAmount("10000");
        offer.setMaxLoanAmount("50000");
        offer.setTenure("12");
        offer.setInterestRate("10");
        offer.setInterestRateType("FIXED");
        offer.setCreditScore("700");
        offer.setOfferValidFrom("2025-01-01");
        offer.setOfferValidTo("2025-12-31");

        offerService.deleteOffer(offer.getOfferId());
    }

    @Test
    void shouldUploadOffer() {
        String result = offerService.offerUpload(offer);
        assertEquals("Offer uploaded", result,"success");
    }

    @Test
    void shouldGetAllOffers() {
        offerService.offerUpload(offer);

        List<Offer> offers = offerService.getAllOffer();

        assertNotNull(offers);
        assertTrue(
                offers.stream().anyMatch(o -> o.getOfferId().equals("OFF_TEST_1001"))
        );
    }

    @Test
    void shouldUpdateOffer() {
        offerService.offerUpload(offer);

        offer.setOfferName("Updated Offer Name");

        String result = offerService.updateOffer(offer);

        assertEquals("Offer updated successfully", result,"success");
    }

    @Test
    void shouldDeleteOffer() {
        offerService.offerUpload(offer);

        String result = offerService.deleteOffer(offer.getOfferId());

        assertEquals("Offer deleted successfully", result,"success");
    }
}
