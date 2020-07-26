package com.dinhpu.m4casestudy.services.real_estate;

import com.dinhpu.m4casestudy.dao.real_estate.AreaTypeDAO;
import com.dinhpu.m4casestudy.model.real_estate.AreaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AreaTypeServices implements IAreaTypeServices{
    @Autowired
    private AreaTypeDAO areaTypeDAO;

    @Override
    public List<AreaType> findAll() {
        return areaTypeDAO.findAll();
    }

    @Override
    public AreaType findById(Long id) {
        Optional<AreaType> optional=areaTypeDAO.findById(id);
        AreaType areaType=null;
        if (optional.isPresent()){
            areaType=optional.get();
        }
        return areaType;
    }

    @Override
    public AreaType save(AreaType model) {
        return null;
    }

    @Override
    public AreaType remove(Long id) {
        return null;
    }
}
