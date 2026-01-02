package com.example.sampleservlet.dao;

import com.example.sampleservlet.model.Offer;
import java.util.List;

public interface OfferDao {
    String saveOffer(Offer offer);
    List<Offer> getOffer();
    String deleteOffer(String id);
    String updateOffer(Offer offer);
}
