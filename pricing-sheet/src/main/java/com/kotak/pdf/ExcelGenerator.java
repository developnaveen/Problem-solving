package com.kotak.pdf;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kotak.fyn.builder.RobofileQueryBuilder;
import com.kotak.fyn.generic.dto.QueryRequest;
import com.kotak.fyn.generic.dto.QueryResponse;
import com.kotak.fyn.roboFile.dao.LoanRequestDao;
import com.kotak.fyn.roboFile.dto.LoanReqDto;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ExcelGenerator {
    private final LoanRequestDao  loanRepo;
    public ExcelGenerator(LoanRequestDao dao) {
        this.loanRepo = dao;
    }
    public <T> Workbook generateExcel(Class<T> dtoClass, String segmentName) {
        // 1) create a queryRequest by passing segment name
        QueryRequest request = RobofileQueryBuilder.buildQueryForLoanData(segmentName);
        // call db to get data
        QueryResponse response = loanRepo.findAll(request);
        List<Map<String, Object>> loanResponse = (List<Map<String, Object>>) response.getData();
        System.out.println(response);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<LoanReqDto> dtoList =
                loanResponse.stream()
                        .map(map -> mapper.convertValue(map, LoanReqDto.class))
                        .collect(Collectors.toList());
        // Generate Excel
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("loanSheet");
        Field[] fields = dtoClass.getDeclaredFields();
        // Header row
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < fields.length; i++) {
            if(!fields[i].getName().equals("referenceId"))
                headerRow.createCell(i).setCellValue(fields[i].getName());
        }
        // Data rows
        int rowIndex = 1;
        for (LoanReqDto item : dtoList) {
            item.setKramanCase(RunTimeValueCalculator.calculateKramanCase(item.getReferenceId()));
            item.setInstalmentStartDate(RunTimeValueCalculator.calculateInstalmentStartDate(item.getLoanPeriod()));
            item.setAccountOpeningDate(RunTimeValueCalculator.calculateAccountOpeningDate());
            Row dataRow = sheet.createRow(rowIndex++);
            for (int i = 0; i < fields.length; i++) {
                if(!fields[i].getName().equals("referenceId"))
                    fields[i].setAccessible(true);
                try {
                    Object value = fields[i].get(item);
                    dataRow.createCell(i).setCellValue(value == null ? "" : value.toString());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return workbook;
    }

    public <T> Workbook generateExcel(Class<T> dtoClass, String segmentName,String referenceId) {
        // 1) create a queryRequest by passing segment name
        QueryRequest request = RobofileQueryBuilder.buildQueryForLoanData(segmentName,referenceId);
        // call db to get data
        QueryResponse response = loanRepo.findAll(request);
        List<Map<String, Object>> loanResponse = (List<Map<String, Object>>) response.getData();
        System.out.println(response);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<LoanReqDto> dtoList =
                loanResponse.stream()
                        .map(map -> mapper.convertValue(map, LoanReqDto.class))
                        .collect(Collectors.toList());
        // Generate Excel
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("loanSheet");
        Field[] fields = dtoClass.getDeclaredFields();

        // Header row
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < fields.length; i++) {
            if(!fields[i].getName().equals("referenceId"))
                headerRow.createCell(i).setCellValue(fields[i].getName());
        }
        // Data rows
        int rowIndex = 1;
        for (LoanReqDto item : dtoList) {
            item.setKramanCase(RunTimeValueCalculator.calculateKramanCase(item.getReferenceId()));
            item.setAccountOpeningDate(RunTimeValueCalculator.calculateAccountOpeningDate());
            item.setInstalmentStartDate(RunTimeValueCalculator.calculateInstalmentStartDate(item.getLoanPeriod()));
            item.setInterestStartDate(String.valueOf(RunTimeValueCalculator.calculateInterestStartDate(item.getLoanPeriod())));
            item.setValueDate(item.getAccountOpeningDate());
            Row dataRow = sheet.createRow(rowIndex++);
            for (int i = 0; i < fields.length; i++) {
                if(!fields[i].getName().equals("referenceId"))
                    fields[i].setAccessible(true);
                try {
                    Object value = fields[i].get(item);
                    dataRow.createCell(i).setCellValue(value == null ? "" : value.toString());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return workbook;
    }
}
