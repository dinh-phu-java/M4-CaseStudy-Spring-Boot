package com.dinhpu.m4casestudy.services.real_estate;

import com.dinhpu.m4casestudy.dao.real_estate.LegalPaperDAO;
import com.dinhpu.m4casestudy.model.real_estate.LegalPaper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LegalPaperServices implements ILegalPaperServices{
    @Autowired
    private LegalPaperDAO legalPaperDAO;

    @Override
    public List<LegalPaper> findAll() {
        return legalPaperDAO.findAll();
    }

    @Override
    public LegalPaper findById(Long id) {
        Optional<LegalPaper> optional=legalPaperDAO.findById(id);
        LegalPaper legalPaper=null;
        if (optional.isPresent()){
            legalPaper=optional.get();
        }
        return legalPaper;
    }

    @Override
    public LegalPaper save(LegalPaper model) {
        return null;
    }

    @Override
    public LegalPaper remove(Long id) {
        return null;
    }
}
