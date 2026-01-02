package com.example.sampleservlet.dao;

import com.example.sampleservlet.config.ConnectionProvider;
import com.example.sampleservlet.model.Offer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OfferDao {

    public String saveOffer(Offer offer) {

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

            int row = ps.executeUpdate();
            if(row!=0){
                return "Offer uploaded";
            }
            else{
                return "offer doesn't uploaded";
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error saving offer", e);
        }
    }


    public List<Offer> getOffer() {
        List<Offer> offers = new ArrayList<>();
        String sql = """
            SELECT 
                * 
            FROM
                bank_offer
            """;

        try (
                Connection con = ConnectionProvider.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                Offer offer = new Offer();


                offer.setOfferId(rs.getString("offer_id"));
                offer.setOfferName(rs.getString("offer_name"));
                offer.setProductType(rs.getString("product_type"));
                offer.setCustomerType(rs.getString("customer_type"));
                offer.setMinLoanAmount(rs.getString("min_loan_amount"));
                offer.setMaxLoanAmount(rs.getString("max_loan_amount"));
                offer.setTenure(rs.getString("tenure"));
                offer.setInterestRate(rs.getString("interest_rate"));
                offer.setInterestRateType(rs.getString("interest_rate_type"));
                offer.setCreditScore(rs.getString("credit_score"));
                offer.setOfferValidFrom(rs.getString("offer_valid_from"));
                offer.setOfferValidTo(rs.getString("offer_valid_to"));


                offers.add(offer);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching offers from database", e);
        }
        return offers;
    }

    public String deleteOffer(String id) {
        String sql = """
                DELETE FROM bank_offer 
                WHERE offer_id = ?
                       """;

        try (
                Connection con = ConnectionProvider.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, id);

            int row = ps.executeUpdate();
            if (row > 0) {
                return "Offer deleted successfully";
            } else {
                return "Offer not found or could not be deleted";
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting offer", e);
        }
    }

    public String updateOffer(Offer offer) {
        String sql = """
           UPDATE bank_offer
           SET 
                offer_name = ?,
                product_type = ?,
                customer_type = ?,
                min_loan_amount = ?,
                max_loan_amount = ?,
                tenure = ?,
                interest_rate = ?,
                interest_rate_type = ?,
                credit_score = ?,
                offer_valid_from = ?,
                offer_valid_to = ?
           WHERE
                offer_id = ?
           """;

        try (
                Connection con = ConnectionProvider.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setString(1, offer.getOfferName());
            ps.setString(2, offer.getProductType());
            ps.setString(3, offer.getCustomerType());
            ps.setString(4, offer.getMinLoanAmount());
            ps.setString(5, offer.getMaxLoanAmount());
            ps.setString(6, offer.getTenure());
            ps.setString(7, offer.getInterestRate());
            ps.setString(8, offer.getInterestRateType());
            ps.setString(9, offer.getCreditScore());
            ps.setString(10, offer.getOfferValidFrom());
            ps.setString(11, offer.getOfferValidTo());

            ps.setString(12, offer.getOfferId());

            int row = ps.executeUpdate();
            if (row > 0) {
                return "Offer updated successfully";
            } else {
                return "Offer not found with ID: " + offer.getOfferId();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error updating offer", e);
        }
    }

}

