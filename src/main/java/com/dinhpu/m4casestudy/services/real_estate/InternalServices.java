package com.dinhpu.m4casestudy.services.real_estate;

import com.dinhpu.m4casestudy.dao.real_estate.InternalUtilsDAO;
import com.dinhpu.m4casestudy.model.real_estate.InternalUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InternalServices implements IInternalServices{
    @Autowired
    private InternalUtilsDAO internalUtilsDAO;

    @Override
    public List<InternalUtilities> findAll() {
        return internalUtilsDAO.findAll();
    }

    @Override
    public InternalUtilities findById(Long id) {
        Optional<InternalUtilities> optional=internalUtilsDAO.findById(id);
        InternalUtilities internalUtilities=null;
        if(optional.isPresent()){
            internalUtilities=optional.get();
        }
        return internalUtilities;
    }

    @Override
    public InternalUtilities save(InternalUtilities model) {
        return internalUtilsDAO.save(model);
    }

    @Override
    public InternalUtilities remove(Long id) {
        InternalUtilities deleteInternalUtilities=findById(id);
        if (deleteInternalUtilities != null){
            internalUtilsDAO.deleteById(id);
        }
        return deleteInternalUtilities;
    }
}
