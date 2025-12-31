package com.example.sampleservlet.dao;

import com.example.sampleservlet.config.ConnectionProvider;
import com.example.sampleservlet.model.Offer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OfferDao {

    public void saveOffer(Offer offer) {

        String sql = """
            INSERT INTO bank_offer(
                offer_id,
                offer_name,
                product_type,
                customer_type,
                min_loan_amount,
                max_loan_amount,
                tenure,
                interest_rate,
                interest_rate_type,
                credit_score,
                offer_valid_from,
                offer_valid_to
            )
            VALUES (?,?,?,?,?,?,?,?,?,?,?,?)
            """;

        try (
                Connection con = ConnectionProvider.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setString(1, offer.getOfferId());
            ps.setString(2, offer.getOfferName());
            ps.setString(3, offer.getProductType());
            ps.setString(4, offer.getCustomerType());
            ps.setString(5, offer.getMinLoanAmount());
            ps.setString(6, offer.getMaxLoanAmount());
            ps.setString(7, offer.getTenure());
            ps.setString(8, offer.getInterestRate());
            ps.setString(9, offer.getInterestRateType());
            ps.setString(10, offer.getCreditScore());
            ps.setString(11, offer.getOfferValidFrom());
            ps.setString(12, offer.getOfferValidTo());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error saving offer", e);
        }
    }
}

