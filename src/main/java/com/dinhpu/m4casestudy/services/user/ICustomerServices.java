package com.dinhpu.m4casestudy.services.user;

import com.dinhpu.m4casestudy.model.real_estate.RealEstate;
import com.dinhpu.m4casestudy.model.user.Customers;
import com.dinhpu.m4casestudy.model.user.User;
import com.dinhpu.m4casestudy.services.IServices;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICustomerServices extends IServices<Customers> {
    List<Customers> findAllByBuyer(User buyer);
    Page<Customers> findAllRealEstateByBuyer(int buyer_id, Pageable pageable);
    void deleteCustomersByBuyerAndRealEstateCustom(int buyer_id,int real_estate_id );
    Page<Customers> findAllByOwnerUSer(int owner_id,Pageable pageable);
    void changeStatus(int id);
    void deleteCustomersByRealEstateId(int id);
}
