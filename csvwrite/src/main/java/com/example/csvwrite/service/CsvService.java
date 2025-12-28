package com.example.csvwrite.service;

import com.example.csvwrite.dto.OfferUpload;
import com.example.csvwrite.repository.CsvRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvService {

    @Autowired
    private CsvRepository csvRepository;
    @Autowired
    private JavaMailSender javaMailSender;

    private Logger log = LoggerFactory.getLogger(CsvService.class);

    public void csvUpload(MultipartFile file) throws IOException {
        log.info("csvUpload started");
        List<OfferUpload> dtos = readCsv(file);

        for (OfferUpload dto : dtos) {
            csvRepository.saveToDb(dto);
        }
        log.info("csvUpload finished");
    }

    public List<OfferUpload> readCsv(MultipartFile file) throws IOException {
        log.info("readCsv started");
        List<OfferUpload> records = new ArrayList<>();

        try (BufferedReader debugReader =
                     new BufferedReader(
                             new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {

        }

        try (BufferedReader reader =
                     new BufferedReader(
                             new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
             CSVParser csvParser =
                     new CSVParser(reader,
                             CSVFormat.RFC4180
                                     .withFirstRecordAsHeader()
                                     .withIgnoreEmptyLines(true)
                                     .withTrim())
        ) {

            for (CSVRecord csv : csvParser) {

                OfferUpload dto = new OfferUpload();

                dto.setCustomerId(csv.get("CUSTOMER_ID"));
                dto.setOrgCifName(csv.get("ORG_CIF_NAME"));

                dto.setMinTenureDays(Integer.parseInt(csv.get("MIN_TENURE_DAYS")));
                dto.setMaxTenureDays(Integer.parseInt(csv.get("MAX_TENURE_DAYS")));

                dto.setProductCode(csv.get("PRODUCT_CODE"));

                dto.setValidFrom(LocalDate.parse(csv.get("VALID_FROM")));
                dto.setValidTo(LocalDate.parse(csv.get("VALID_TO")));

                dto.setOfferType(csv.get("OFFER_TYPE"));

                dto.setR1(csv.get("R1"));
                dto.setR2(csv.get("R2"));
                dto.setR3(csv.get("R3"));

                dto.setR4Template(csv.get("R4_TEMPLATE"));
                dto.setR4_1_1(csv.get("R4_1_1"));
                dto.setR4_1_2(csv.get("R4_1_2"));
                dto.setR4_2_1(csv.get("R4_2_1"));
                dto.setR4_2_2(csv.get("R4_2_2"));

                dto.setR5(csv.get("R5"));

                dto.setFixedRate(new BigDecimal(csv.get("FIXED_RATE")));
                dto.setRepoRate(new BigDecimal(csv.get("REPO_RATE")));
                dto.setMclrRate(new BigDecimal(csv.get("MCLR_RATE")));

                dto.setSourceType(csv.get("SOURCE_TYPE"));
                dto.setDescription(csv.get("DESCRIPTION"));

                dto.setDigitalWcdl(csv.get("DIGITAL_WCDL"));
                dto.setEwsCheck(csv.get("EWS_CHECK"));
                dto.setPslStatus(csv.get("PSL_STATUS"));
                dto.setMsmeStatus(csv.get("MSME_STATUS"));

                dto.setLob(csv.get("LOB"));

                records.add(dto);
            }

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("Error while reading CSV file", e);
        }
        log.info("csvUpload finished");
        return records;
    }

    public void sendMail(String to) {
        log.info("sendMail started");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("new offer uploaded");
        message.setText("This is a new offer uploaded.");

        javaMailSender.send(message);
        log.info("sendMail finished");
    }
}
