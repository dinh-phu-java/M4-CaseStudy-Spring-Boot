package com.dinhpu.m4casestudy.services.user;

import com.dinhpu.m4casestudy.dao.user.CustomersDAO;
import com.dinhpu.m4casestudy.model.user.Customers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServices implements ICustomerServices{
    @Autowired
    private CustomersDAO customersDAO;

    @Override
    public List<Customers> findAll() {
        return customersDAO.findAll();
    }

    @Override
    public Customers findById(Long id) {
        Optional<Customers> optional=customersDAO.findById(id);
        Customers customers=null;
        if (optional.isPresent()){
            customers=optional.get();
        }
        return customers;
    }

    @Override
    public Customers save(Customers model) {
        return customersDAO.save(model);
    }

    @Override
    public Customers remove(Long id) {
        Customers removeObj=findById(id);
        customersDAO.deleteById(id);
        return removeObj;
    }
}
