package com.example.sampleservlet.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.example.sampleservlet.dao.OfferDaoImpl;
import com.example.sampleservlet.model.Offer;
import com.example.sampleservlet.service.OfferServiceImpl;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/loans/*")
public class OfferServlet extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(OfferServlet.class);

    private final ObjectMapper mapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    private OfferServiceImpl offerServiceImpl;

    public OfferServlet() {
        super();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        OfferDaoImpl offerDao = new OfferDaoImpl();
        this.offerServiceImpl = new OfferServiceImpl(offerDao);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");

        try {
            log.info("Fetching all offers via GET");
            List<Offer> offers = offerServiceImpl.getAllOffer();

            res.setStatus(HttpServletResponse.SC_OK);
            mapper.writeValue(res.getWriter(), offers);

        } catch (Exception e) {
            log.error("Error fetching offers", e);
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            mapper.writeValue(res.getWriter(), Map.of(
                    "status", "ERROR",
                    "message", "Could not retrieve offers: " + e.getMessage()
            ));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");

        try {
            log.info("Received POST request to create offer");
            Offer offer = buildOffer(req);

            String result = offerServiceImpl.offerUpload(offer);

            res.setStatus(HttpServletResponse.SC_CREATED);
            mapper.writeValue(res.getWriter(), Map.of(
                    "status", "SUCCESS",
                    "message", result
            ));

        } catch (Exception e) {
            log.error("Exception while creating offer", e);
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            mapper.writeValue(res.getWriter(), Map.of(
                    "status", "ERROR",
                    "message", e.getMessage()
            ));
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");

        try {
            log.info("Entered doDelete for offerId: {}", req.getParameter("offerId"));

            String result = offerServiceImpl.deleteOffer(req.getParameter("offerId"));

            res.setStatus(HttpServletResponse.SC_OK);
            mapper.writeValue(res.getWriter(), Map.of(
                    "status", "SUCCESS",
                    "message", result
            ));
        } catch (Exception e) {
            log.error("Exception at the delete of offer", e);
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            mapper.writeValue(res.getWriter(), Map.of(
                    "status", "ERROR",
                    "message", e.getMessage()
            ));
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");

        try {
            log.info("Received request to update offer");

            Offer offerToUpdate = buildOffer(req);
            String result = offerServiceImpl.updateOffer(offerToUpdate);

            res.setStatus(HttpServletResponse.SC_OK);
            mapper.writeValue(res.getWriter(), Map.of(
                    "status", "SUCCESS",
                    "message", result
            ));
        } catch (Exception e){
            log.error("Exception during update", e);
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            mapper.writeValue(res.getWriter(), Map.of(
                    "status", "ERROR",
                    "message", e.getMessage()
            ));
        }
    }


    public Offer buildOffer(HttpServletRequest req) {
        try {
            return mapper.readValue(req.getInputStream(), Offer.class);
        } catch (IOException e) {
            log.warn("Failed to parse JSON body");
            throw new IllegalArgumentException("Invalid JSON body in request", e);
        }
    }
}
