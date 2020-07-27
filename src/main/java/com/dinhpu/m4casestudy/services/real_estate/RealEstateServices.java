package com.dinhpu.m4casestudy.services.real_estate;

import com.dinhpu.m4casestudy.dao.real_estate.RealEstateDAO;
import com.dinhpu.m4casestudy.model.real_estate.RealEstate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class RealEstateServices implements IRealEstateServices{
    @Autowired
    private RealEstateDAO realEstateDAO;

    @Override
    public Page<RealEstate> findAll(Pageable pageable) {
        return realEstateDAO.findAll(pageable);
    }

    @Override
    public RealEstate findById(Long id) {
        Optional<RealEstate> optional=realEstateDAO.findById(id);
        RealEstate realEstate=null;
        if (optional.isPresent()){
            realEstate=optional.get();
        }
        return realEstate;
    }

    @Override
    public RealEstate save(RealEstate model) {
        return realEstateDAO.save(model);
    }

    @Override
    public RealEstate remove(Long id) {
        RealEstate realEstate=findById(id);

        if (realEstate!=null){
            realEstateDAO.deleteById(id);
        }
        //else nen throw ra loi
        return realEstate;
    }
}
