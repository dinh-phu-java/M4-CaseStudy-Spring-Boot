package com.dinhpu.m4casestudy.services.real_estate;

import com.dinhpu.m4casestudy.dao.real_estate.WardDAO;
import com.dinhpu.m4casestudy.model.real_estate.Ward;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WardServices implements IWardServices{
    @Autowired
    private WardDAO wardDAO;

    @Override
    public List<Ward> findAllWardByDistrictName(String districtName) {
        return wardDAO.findAllWardByDistrictName(districtName);
    }

    @Override
    public List<Ward> findAll() {
        return wardDAO.findAll();
    }

    @Override
    public Ward findById(Long id) {
        Optional<Ward> optional=wardDAO.findById(id);
        Ward ward=null;
        if (optional.isPresent()){
            ward=optional.get();
        }
        return ward;
    }

    @Override
    public Ward save(Ward model) {
        return null;
    }

    @Override
    public Ward remove(Long id) {
        return null;
    }
}
