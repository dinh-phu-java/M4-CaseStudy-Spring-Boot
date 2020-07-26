package com.dinhpu.m4casestudy.services.real_estate;

import com.dinhpu.m4casestudy.dao.real_estate.DistrictDAO;
import com.dinhpu.m4casestudy.model.real_estate.District;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class DistrictServices implements IDistrictServices{
    @Autowired
    private DistrictDAO districtDAO;

    @Override
    public List<District> findAll() {
        return districtDAO.findAll();
    }

    @Override
    public District findById(Long id) {
        Optional<District> optional=districtDAO.findById(id);
        District district=null;
        if (optional.isPresent()){
            district=optional.get();
        }
        return district;
    }

    @Override
    public District save(District model) {
        return null;
    }


    @Override
    public District remove(Long id) {
        return null;
    }

    @Override
    public List<District> myAllDistrictQueryByProvinceName(String provinceName) {
        return districtDAO.myAllQueryDistrictByProvinceName(provinceName);
    }

}
