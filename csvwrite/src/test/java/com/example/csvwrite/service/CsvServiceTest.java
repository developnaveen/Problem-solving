package com.example.csvwrite.service;

import com.example.csvwrite.repository.CsvRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mock.web.MockMultipartFile;

import java.nio.charset.StandardCharsets;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CsvServiceTest {

    @Mock
    private CsvRepository csvRepository;

    @Mock
    private JavaMailSender javaMailSender;

    @InjectMocks
    private CsvService csvService;

    @Test
    void shouldReadCsvAndSaveToDatabase() throws Exception {

        String csvContent =
                "CUSTOMER_ID,ORG_CIF_NAME,MIN_TENURE_DAYS,MAX_TENURE_DAYS,PRODUCT_CODE,VALID_FROM,VALID_TO,OFFER_TYPE,R1,R2,R3,R4_TEMPLATE,R4_1_1,R4_1_2,R4_2_1,R4_2_2,R5,FIXED_RATE,REPO_RATE,MCLR_RATE,SOURCE_TYPE,DESCRIPTION,DIGITAL_WCDL,EWS_CHECK,PSL_STATUS,MSME_STATUS,LOB\n" +
                        "CUST001,ABC Traders,30,365,WCDL_STD,2025-01-01,2025-12-31,STANDARD,Y,Y,N,TEMP_A,Y,N,Y,N,N,10.50,6.50,8.75,DIGITAL,Standard Offer,Y,Y,Y,Y,SME";

        MockMultipartFile file = new MockMultipartFile(
                "file",
                "offer_upload.csv",
                "text/csv",
                csvContent.getBytes(StandardCharsets.UTF_8)
        );

        csvService.csvUpload(file);

        // Verify DB insert
        verify(csvRepository, times(1)).saveToDb(any());

        // Verify NO email sent during upload
        verifyNoInteractions(javaMailSender);
    }

    @Test
    void shouldSendEmailSuccessfully() {

        String email = "test@example.com";

        csvService.sendMail(email);

        // Verify email sent once
        verify(javaMailSender, times(1)).send(any(SimpleMailMessage.class));

    }
}
