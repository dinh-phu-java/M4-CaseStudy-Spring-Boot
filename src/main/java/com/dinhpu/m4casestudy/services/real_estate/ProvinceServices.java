package com.dinhpu.m4casestudy.services.real_estate;

import com.dinhpu.m4casestudy.dao.real_estate.ProvinceDAO;
import com.dinhpu.m4casestudy.model.real_estate.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProvinceServices implements IProvinceServices{
    @Autowired
    private ProvinceDAO provinceDAO;

    @Override
    public List<Province> findAll() {
        return provinceDAO.findAll();
    }

    @Override
    public Province findById(Long id) {
        Optional<Province> optional=provinceDAO.findById(id);
        Province province=new Province();
        if (optional.isPresent()){
            province=optional.get();
        }
        return province;
    }

    @Override
    public Province save(Province model) {
        return null;
    }

    @Override
    public Province remove(Long id) {
        return null;
    }
}
