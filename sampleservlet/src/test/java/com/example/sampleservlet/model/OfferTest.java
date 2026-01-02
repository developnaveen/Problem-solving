package com.example.sampleservlet.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OfferTest {

    @Test
    void shouldSetAndGetAllFields() {
        Offer offer = new Offer();

        offer.setOfferId("OFF123");
        offer.setOfferName("Festival Loan Offer");
        offer.setProductType("Home Loan");
        offer.setCustomerType("Retail");
        offer.setMinLoanAmount("100000");
        offer.setMaxLoanAmount("5000000");
        offer.setTenure("240");
        offer.setInterestRate("8.5");
        offer.setInterestRateType("Floating");
        offer.setCreditScore("750");
        offer.setOfferValidFrom("2026-01-01");
        offer.setOfferValidTo("2026-12-31");

        assertEquals("OFF123", offer.getOfferId());
        assertEquals("Festival Loan Offer", offer.getOfferName());
        assertEquals("Home Loan", offer.getProductType());
        assertEquals("Retail", offer.getCustomerType());
        assertEquals("100000", offer.getMinLoanAmount());
        assertEquals("5000000", offer.getMaxLoanAmount());
        assertEquals("240", offer.getTenure());
        assertEquals("8.5", offer.getInterestRate());
        assertEquals("Floating", offer.getInterestRateType());
        assertEquals("750", offer.getCreditScore());
        assertEquals("2026-01-01", offer.getOfferValidFrom());
        assertEquals("2026-12-31", offer.getOfferValidTo());
    }
}
