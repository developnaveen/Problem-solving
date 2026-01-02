package com.example.sampleservlet.service;


import com.example.sampleservlet.dao.OfferDao;
import com.example.sampleservlet.model.Offer;

import java.util.List;

public class OfferServiceImpl implements OfferService {
    private OfferDao offerDao = new OfferDao();

    @Override
    public String offerUpload(Offer offer) {
        return offerDao.saveOffer(offer);

    }

    @Override
    public List<Offer> getAllOffer() {
        return offerDao.getOffer();
    }

    @Override
    public Offer getOfferById(String id) {
        return null;
    }

    @Override
    public String deleteOffer(String id){
        return offerDao.deleteOffer(id);
    }

    @Override
    public String updateOffer(Offer offer){
        return offerDao.updateOffer(offer);
    }
}
