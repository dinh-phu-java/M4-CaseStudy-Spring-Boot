package com.dinhpu.m4casestudy.services.user;

import com.dinhpu.m4casestudy.dao.user.CustomersDAO;
import com.dinhpu.m4casestudy.model.real_estate.RealEstate;
import com.dinhpu.m4casestudy.model.user.Customers;
import com.dinhpu.m4casestudy.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public List<Customers> findAllByBuyer(User buyer) {
        return customersDAO.findAllByBuyer(buyer);
    }

    @Override
    public Page<Customers> findAllRealEstateByBuyer(int buyer_id, Pageable pageable) {
        return customersDAO.findAllRealEstateByBuyer(buyer_id,pageable);
    }

    @Override
    @Transactional
    public void deleteCustomersByBuyerAndRealEstateCustom(int buyer_id, int real_estate_id) {
        customersDAO.deleteCustomersByBuyerAndRealEstateCustom(buyer_id,real_estate_id);
    }

    @Override
    public Page<Customers> findAllByOwnerUSer(int owner_id, Pageable pageable) {
        return customersDAO.findAllByOwnerUSer(owner_id,pageable);
    }

    @Override
    @Transactional
    public void changeStatus(int id) {
        customersDAO.changeStatus(id);
    }

    @Override
    @Transactional
    public void deleteCustomersByRealEstateId(int id) {
        customersDAO.deleteCustomersByRealEstateId(id);
    }
}
