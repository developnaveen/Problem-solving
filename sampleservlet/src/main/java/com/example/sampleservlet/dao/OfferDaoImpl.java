package com.example.sampleservlet.dao;

import com.example.sampleservlet.config.ConnectionProvider;
import com.example.sampleservlet.model.Offer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OfferDaoImpl implements OfferDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(OfferDaoImpl.class);

    private static final int IDX_OFFER_ID = 1;
    private static final int IDX_OFFER_NAME = 2;
    private static final int IDX_PRODUCT_TYPE = 3;
    private static final int IDX_CUSTOMER_TYPE = 4;
    private static final int IDX_MIN_LOAN_AMOUNT = 5;
    private static final int IDX_MAX_LOAN_AMOUNT = 6;
    private static final int IDX_TENURE = 7;
    private static final int IDX_INTEREST_RATE = 8;
    private static final int IDX_INTEREST_RATE_TYPE = 9;
    private static final int IDX_CREDIT_SCORE = 10;
    private static final int IDX_OFFER_VALID_FROM = 11;
    private static final int IDX_OFFER_VALID_TO = 12;

    private static final int U_OFFER_NAME = 1;
    private static final int U_PRODUCT_TYPE = 2;
    private static final int U_CUSTOMER_TYPE = 3;
    private static final int U_MIN_LOAN_AMOUNT = 4;
    private static final int U_MAX_LOAN_AMOUNT = 5;
    private static final int U_TENURE = 6;
    private static final int U_INTEREST_RATE = 7;
    private static final int U_INTEREST_RATE_TYPE = 8;
    private static final int U_CREDIT_SCORE = 9;
    private static final int U_OFFER_VALID_FROM = 10;
    private static final int U_OFFER_VALID_TO = 11;
    private static final int U_OFFER_ID = 12;


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
            LOGGER.info("Enterd into insert the offer");

            ps.setString(IDX_OFFER_ID, offer.getOfferId());
            ps.setString(IDX_OFFER_NAME, offer.getOfferName());
            ps.setString(IDX_PRODUCT_TYPE, offer.getProductType());
            ps.setString(IDX_CUSTOMER_TYPE, offer.getCustomerType());
            ps.setString(IDX_MIN_LOAN_AMOUNT , offer.getMinLoanAmount());
            ps.setString(IDX_MAX_LOAN_AMOUNT , offer.getMaxLoanAmount());
            ps.setString(IDX_TENURE, offer.getTenure());
            ps.setString(IDX_INTEREST_RATE, offer.getInterestRate());
            ps.setString(IDX_INTEREST_RATE_TYPE, offer.getInterestRateType());
            ps.setString(IDX_CREDIT_SCORE, offer.getCreditScore());
            ps.setString(IDX_OFFER_VALID_FROM, offer.getOfferValidFrom());
            ps.setString(IDX_OFFER_VALID_TO, offer.getOfferValidTo());

            int row = ps.executeUpdate();
            if(row!=0){
                return "Offer uploaded";
            }
            else{
                return "offer doesn't uploaded";
            }

        } catch (SQLException e) {
            LOGGER.error("Error saving offer with id={}", offer.getOfferId(), e);
            throw new OfferDaoException("Failed to save offer with id: " + offer.getOfferId(), e);
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
            LOGGER.info("Entered into select offer");
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
            LOGGER.error("Error fetching offers from database", e);
            throw new OfferDaoException("Error fetching offers from database", e);
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
            LOGGER.info("enterd into the deleteoffer");
            ps.setString(1, id);

            int row = ps.executeUpdate();
            if (row > 0) {
                return "Offer deleted successfully";
            } else {
                return "Offer not found or could not be deleted";
            }
        } catch (SQLException e) {
            LOGGER.error("Error deleting offer with id={}", id, e);
            throw new OfferDaoException("Failed to delete offer with id: " + id, e);
        }
    }

    public void deleteOfferById(String offerId) {
        String sql = "DELETE FROM bank_offer WHERE offer_id = ?";

        try (
                Connection con = ConnectionProvider.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            LOGGER.info("Entered to delete the user");
            ps.setString(1, offerId);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error cleaning offer with id={}", offerId, e);
            throw new OfferDaoException("Error cleaning offer with id: " + offerId, e);
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

            ps.setString(U_OFFER_NAME, offer.getOfferName());
            ps.setString(U_PRODUCT_TYPE, offer.getProductType());
            ps.setString(U_CUSTOMER_TYPE, offer.getCustomerType());
            ps.setString(U_MIN_LOAN_AMOUNT , offer.getMinLoanAmount());
            ps.setString(U_MAX_LOAN_AMOUNT , offer.getMaxLoanAmount());
            ps.setString(U_TENURE, offer.getTenure());
            ps.setString(U_INTEREST_RATE, offer.getInterestRate());
            ps.setString(U_INTEREST_RATE_TYPE, offer.getInterestRateType());
            ps.setString(U_CREDIT_SCORE, offer.getCreditScore());
            ps.setString(U_OFFER_VALID_FROM, offer.getOfferValidFrom());
            ps.setString(U_OFFER_VALID_TO, offer.getOfferValidTo());

            ps.setString(U_OFFER_ID, offer.getOfferId());

            int row = ps.executeUpdate();
            if (row > 0) {
                return "Offer updated successfully";
            } else {
                return "Offer not found with ID: " + offer.getOfferId();
            }
        } catch (SQLException e) {
            throw new OfferDaoException("Error updating offer", e);
        }
    }

}

