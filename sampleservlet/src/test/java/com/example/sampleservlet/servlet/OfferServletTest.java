package com.example.sampleservlet.servlet;

import com.example.sampleservlet.model.Offer;
import com.example.sampleservlet.service.OfferServiceImpl;
import com.example.sampleservlet.testutil.FakeHttpServletRequest;
import com.example.sampleservlet.testutil.FakeHttpServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OfferServletTest {

    private OfferServlet servlet;
    private FakeHttpServletRequest request;
    private FakeHttpServletResponse response;

    static class FakeOfferService extends OfferServiceImpl {
        FakeOfferService() {
            super(null);
        }

        @Override
        public List<Offer> getAllOffer() {
            Offer o = new Offer();
            o.setOfferName("Test Offer");
            return List.of(o);
        }

        @Override
        public String offerUpload(Offer offer) {
            return "Offer uploaded";
        }

        @Override
        public String deleteOffer(String id) {
            return "Offer deleted successfully";
        }

        @Override
        public String updateOffer(Offer offer) {
            return "Offer updated successfully";
        }
    }

    @BeforeEach
    void setUp() throws Exception {
        servlet = new OfferServlet();

        var field = OfferServlet.class.getDeclaredField("offerServiceImpl");
        field.setAccessible(true);
        field.set(servlet, new FakeOfferService());

        request = new FakeHttpServletRequest();
        response = new FakeHttpServletResponse();
    }

    @Test
    void shouldFetchAllOffers() throws Exception {
        servlet.doGet(request, response);

        assertEquals(HttpServletResponse.SC_OK, response.getStatus());
        assertTrue(response.getBody().contains("Test Offer"));
    }

    @Test
    void shouldReturnEmptyOffers() throws Exception {
        OfferServiceImpl emptyService = new FakeOfferService() {
            @Override
            public List<Offer> getAllOffer() {
                return List.of();
            }
        };

        var field = OfferServlet.class.getDeclaredField("offerServiceImpl");
        field.setAccessible(true);
        field.set(servlet, emptyService);

        servlet.doGet(request, response);

        assertTrue(response.getBody().contains("[]"));
    }

    @Test
    void shouldCreateOffer() throws Exception {
        String json = """
        {
          "offerId": "OFF10",
          "offerName": "New Year Offer"
        }
        """;

        request.setBody(json);

        servlet.doPost(request, response);

        assertTrue(response.getBody().contains("SUCCESS"));
        assertTrue(response.getBody().contains("Offer uploaded"));
    }

    @Test
    void shouldUpdateOffer() throws Exception {
        String json = """
        {
          "offerId": "OFF20",
          "offerName": "Updated Offer"
        }
        """;

        request.setBody(json);

        servlet.doPut(request, response);

        assertTrue(response.getBody().contains("Offer updated successfully"));
    }

    @Test
    void shouldDeleteOffer() throws Exception {
        request.setParameter("offerId", "OFF30");

        servlet.doDelete(request, response);

        assertTrue(response.getBody().contains("Offer deleted successfully"));
    }
}
