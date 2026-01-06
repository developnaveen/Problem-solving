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

        assertEquals("OFF123", offer.getOfferId(),"success");
        assertEquals("Festival Loan Offer", offer.getOfferName(),"success");
        assertEquals("Home Loan", offer.getProductType(),"success");
        assertEquals("Retail", offer.getCustomerType(),"success");
        assertEquals("100000", offer.getMinLoanAmount(),"success");
        assertEquals("5000000", offer.getMaxLoanAmount(),"success");
        assertEquals("240", offer.getTenure(),"success");
        assertEquals("8.5", offer.getInterestRate(),"success");
        assertEquals("Floating", offer.getInterestRateType(),"success");
        assertEquals("750", offer.getCreditScore(),"success");
        assertEquals("2026-01-01", offer.getOfferValidFrom(),"success");
        assertEquals("2026-12-31", offer.getOfferValidTo(),"success");
    }
}
