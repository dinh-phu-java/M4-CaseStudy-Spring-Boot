package com.dinhpu.m4casestudy.services.real_estate;

import com.dinhpu.m4casestudy.dao.real_estate.CategoryDAO;
import com.dinhpu.m4casestudy.model.real_estate.Category;
import com.dinhpu.m4casestudy.services.IServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServices implements ICategoryServices {
    @Autowired
    private CategoryDAO categoryDAO;
    @Override
    public List<Category> findAll() {
        return categoryDAO.findAll();
    }

    @Override
    public Category findById(Long id) {
        return null;
    }

    @Override
    public Category save(Category model) {
        return null;
    }

    @Override
    public Category remove(Long id) {
        return null;
    }
}
