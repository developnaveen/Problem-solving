package com.example.sampleservlet.model;

public class Offer {

    private String offerId;
    private String offerName;
    private String productType;
    private String customerType;
    private String minLoanAmount;
    private String maxLoanAmount;
    private String tenure;
    private String interestRateType;
    private String interestRate;
    private String creditScore;
    private String offerValidFrom;
    private String offerValidTo;

    public Offer(){

    }


    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getMinLoanAmount() {
        return minLoanAmount;
    }

    public void setMinLoanAmount(String minLoanAmount) {
        this.minLoanAmount = minLoanAmount;
    }

    public String getMaxLoanAmount() {
        return maxLoanAmount;
    }

    public void setMaxLoanAmount(String maxLoanAmount) {
        this.maxLoanAmount = maxLoanAmount;
    }

    public String getTenure() {
        return tenure;
    }

    public void setTenure(String tenure) {
        this.tenure = tenure;
    }

    public String getInterestRateType() {
        return interestRateType;
    }

    public void setInterestRateType(String interestRateType) {
        this.interestRateType = interestRateType;
    }

    public String getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(String interestRate) {
        this.interestRate = interestRate;
    }

    public String getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(String creditScore) {
        this.creditScore = creditScore;
    }

    public String getOfferValidFrom() {
        return offerValidFrom;
    }

    public void setOfferValidFrom(String offerValidFrom) {
        this.offerValidFrom = offerValidFrom;
    }

    public String getOfferValidTo() {
        return offerValidTo;
    }

    public void setOfferValidTo(String offerValitTo) {
        this.offerValidTo = offerValitTo;
    }
}
