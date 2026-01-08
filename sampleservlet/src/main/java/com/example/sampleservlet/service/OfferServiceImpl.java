package com.example.sampleservlet.service;

import com.example.sampleservlet.dao.OfferDao;
import com.example.sampleservlet.model.Offer;
import com.example.sampleservlet.util.MailSender;

import java.util.List;

public class OfferServiceImpl implements OfferService {

    private final OfferDao offerDao;

    public OfferServiceImpl(OfferDao offerDao) {
        this.offerDao = offerDao;
    }

    @Override
    public String offerUpload(Offer offer) {
        MailSender.send("malalatha60@gmail.com","hi");
        return offerDao.saveOffer(offer);
    }

    @Override
    public List<Offer> getAllOffer() {
        return offerDao.getOffer();
    }

    @Override
    public String deleteOffer(String id) {
        return offerDao.deleteOffer(id);
    }

    @Override
    public String updateOffer(Offer offer) {
        return offerDao.updateOffer(offer);
    }

}
