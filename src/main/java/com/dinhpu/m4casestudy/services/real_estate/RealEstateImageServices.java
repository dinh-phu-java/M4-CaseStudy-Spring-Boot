package com.dinhpu.m4casestudy.services.real_estate;

import com.dinhpu.m4casestudy.dao.real_estate.RealEstateImageDAO;
import com.dinhpu.m4casestudy.model.real_estate.RealEstate;
import com.dinhpu.m4casestudy.model.real_estate.RealEstateImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RealEstateImageServices implements IRealEstateImage{
    @Autowired
    private RealEstateImageDAO realEstateImageDAO;

    @Override
    @Transactional
    public List<RealEstateImage> findAll() {
        return null;
    }

    @Override
    @Transactional
    public RealEstateImage findById(Long id) {
        return null;
    }

    @Override
    @Transactional
    public RealEstateImage save(RealEstateImage model) {
        return null;
    }

    @Override
    @Transactional
    public RealEstateImage remove(Long id) {
        return null;
    }

    @Override
    @Transactional
    public List<RealEstateImage> findAllByRealEstate(RealEstate realEstate) {
        return realEstateImageDAO.findAllByRealEstate(realEstate);
    }

    @Override
    @Transactional
    public void removeAllByRealEstate(RealEstate realEstate) {
        realEstateImageDAO.removeAllByRealEstate(realEstate);
    }
}
