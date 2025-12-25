package com.example.springbatch.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import jakarta.mail.internet.MimeMessage;

public class SendEmailTasklet implements Tasklet {

    private JavaMailSender mailSender;

    // setter injection (XML)
    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution,
                                ChunkContext chunkContext) throws Exception {

        System.out.println(">>> Sending email with CSV attachment");

        // 1️⃣ Create message
        MimeMessage message = mailSender.createMimeMessage();

        // 2️⃣ Create helper (THIS WAS NULL BEFORE)
        MimeMessageHelper helper =
                new MimeMessageHelper(message, true);

        // 3️⃣ Set mail details
        helper.setTo("malalatha60@gmail.com");
        helper.setSubject("Batch Job Completed");
        helper.setText(
                "Hello,\n\nBatch job completed successfully.\n\nCSV is attached.",
                false
        );

        // 4️⃣ Attach CSV (classpath)
        ClassPathResource csvFile = new ClassPathResource("users.csv");
        helper.addAttachment("users.csv", csvFile);

        // 5️⃣ Send
        mailSender.send(message);

        System.out.println(">>> Email sent successfully");

        return RepeatStatus.FINISHED;
    }
}
