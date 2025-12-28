package com.example.csvwrite.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class OfferUpload {

    private String customerId;
    private String orgCifName;

    private Integer minTenureDays;
    private Integer maxTenureDays;

    private String productCode;

    private LocalDate validFrom;
    private LocalDate validTo;

    private String offerType;

    private String r1;
    private String r2;
    private String r3;

    private String r4Template;
    private String r4_1_1;
    private String r4_1_2;
    private String r4_2_1;
    private String r4_2_2;

    private String r5;

    private BigDecimal fixedRate;
    private BigDecimal repoRate;
    private BigDecimal mclrRate;

    private String sourceType;
    private String description;

    private String digitalWcdl;
    private String ewsCheck;
    private String pslStatus;
    private String msmeStatus;

    private String lob;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getOrgCifName() {
        return orgCifName;
    }

    public void setOrgCifName(String orgCifName) {
        this.orgCifName = orgCifName;
    }

    public Integer getMinTenureDays() {
        return minTenureDays;
    }

    public void setMinTenureDays(Integer minTenureDays) {
        this.minTenureDays = minTenureDays;
    }

    public Integer getMaxTenureDays() {
        return maxTenureDays;
    }

    public void setMaxTenureDays(Integer maxTenureDays) {
        this.maxTenureDays = maxTenureDays;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public LocalDate getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(LocalDate validFrom) {
        this.validFrom = validFrom;
    }

    public LocalDate getValidTo() {
        return validTo;
    }

    public void setValidTo(LocalDate validTo) {
        this.validTo = validTo;
    }

    public String getOfferType() {
        return offerType;
    }

    public void setOfferType(String offerType) {
        this.offerType = offerType;
    }

    public String getR1() {
        return r1;
    }

    public void setR1(String r1) {
        this.r1 = r1;
    }

    public String getR2() {
        return r2;
    }

    public void setR2(String r2) {
        this.r2 = r2;
    }

    public String getR3() {
        return r3;
    }

    public void setR3(String r3) {
        this.r3 = r3;
    }

    public String getR4Template() {
        return r4Template;
    }

    public void setR4Template(String r4Template) {
        this.r4Template = r4Template;
    }

    public String getR4_1_1() {
        return r4_1_1;
    }

    public void setR4_1_1(String r4_1_1) {
        this.r4_1_1 = r4_1_1;
    }

    public String getR4_1_2() {
        return r4_1_2;
    }

    public void setR4_1_2(String r4_1_2) {
        this.r4_1_2 = r4_1_2;
    }

    public String getR4_2_1() {
        return r4_2_1;
    }

    public void setR4_2_1(String r4_2_1) {
        this.r4_2_1 = r4_2_1;
    }

    public String getR4_2_2() {
        return r4_2_2;
    }

    public void setR4_2_2(String r4_2_2) {
        this.r4_2_2 = r4_2_2;
    }

    public String getR5() {
        return r5;
    }

    public void setR5(String r5) {
        this.r5 = r5;
    }

    public BigDecimal getFixedRate() {
        return fixedRate;
    }

    public void setFixedRate(BigDecimal fixedRate) {
        this.fixedRate = fixedRate;
    }

    public BigDecimal getRepoRate() {
        return repoRate;
    }

    public void setRepoRate(BigDecimal repoRate) {
        this.repoRate = repoRate;
    }

    public BigDecimal getMclrRate() {
        return mclrRate;
    }

    public void setMclrRate(BigDecimal mclrRate) {
        this.mclrRate = mclrRate;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDigitalWcdl() {
        return digitalWcdl;
    }

    public void setDigitalWcdl(String digitalWcdl) {
        this.digitalWcdl = digitalWcdl;
    }

    public String getEwsCheck() {
        return ewsCheck;
    }

    public void setEwsCheck(String ewsCheck) {
        this.ewsCheck = ewsCheck;
    }

    public String getPslStatus() {
        return pslStatus;
    }

    public void setPslStatus(String pslStatus) {
        this.pslStatus = pslStatus;
    }

    public String getMsmeStatus() {
        return msmeStatus;
    }

    public void setMsmeStatus(String msmeStatus) {
        this.msmeStatus = msmeStatus;
    }

    public String getLob() {
        return lob;
    }

    public void setLob(String lob) {
        this.lob = lob;
    }
}
