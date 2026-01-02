package com.example.sampleservlet.service;

import com.example.sampleservlet.model.Offer;

import java.util.List;

public interface OfferService {

    public String offerUpload(Offer offer);
    public List<Offer> getAllOffer();
    public Offer getOfferById(String id);
    public String deleteOffer(String id);
    public String updateOffer(Offer offer);
}
