package com.example.csvwrite.repository;

import com.example.csvwrite.dto.OfferUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
public class CsvRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Logger log = LoggerFactory.getLogger(CsvRepository.class);

    public int saveToDb(OfferUpload dto) {
        log.info("save to db");
        String sql = """
            INSERT INTO OFFER_UPLOAD (
                CUSTOMER_ID, ORG_CIF_NAME,
                MIN_TENURE_DAYS, MAX_TENURE_DAYS,
                PRODUCT_CODE,
                VALID_FROM, VALID_TO,
                OFFER_TYPE,
                R1, R2, R3,
                R4_TEMPLATE, R4_1_1, R4_1_2, R4_2_1, R4_2_2,
                R5,
                FIXED_RATE, REPO_RATE, MCLR_RATE,
                SOURCE_TYPE, DESCRIPTION,
                DIGITAL_WCDL, EWS_CHECK, PSL_STATUS, MSME_STATUS,
                LOB
            ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)
        """;
        log.info("save to db");

        return jdbcTemplate.update(sql,
                dto.getCustomerId(),
                dto.getOrgCifName(),
                dto.getMinTenureDays(),
                dto.getMaxTenureDays(),
                dto.getProductCode(),
                dto.getValidFrom(),
                dto.getValidTo(),
                dto.getOfferType(),
                dto.getR1(),
                dto.getR2(),
                dto.getR3(),
                dto.getR4Template(),
                dto.getR4_1_1(),
                dto.getR4_1_2(),
                dto.getR4_2_1(),
                dto.getR4_2_2(),
                dto.getR5(),
                dto.getFixedRate(),
                dto.getRepoRate(),
                dto.getMclrRate(),
                dto.getSourceType(),
                dto.getDescription(),
                dto.getDigitalWcdl(),
                dto.getEwsCheck(),
                dto.getPslStatus(),
                dto.getMsmeStatus(),
                dto.getLob()
        );

    }
}
