package com.dinhpu.m4casestudy.dao.user;

import com.dinhpu.m4casestudy.model.real_estate.RealEstate;
import com.dinhpu.m4casestudy.model.user.Customers;
import com.dinhpu.m4casestudy.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomersDAO extends JpaRepository<Customers,Long> {
    List<Customers> findAllByBuyer(User buyer);

    @Query(value="select * from customers  where buyer_id=?1",
    countQuery="select count(*) from customers where buyer_id=?1",
    nativeQuery=true)
    Page<Customers> findAllRealEstateByBuyer(int buyer_id, Pageable pageable);

    @Modifying
    @Query(value="delete from customers where buyer_id=?1 and real_estate_id=?2",
    nativeQuery=true)
    void deleteCustomersByBuyerAndRealEstateCustom(int buyer_id,int real_estate_id );

    @Modifying
    @Query(value="delete from customers where real_estate_id=?1",nativeQuery=true)
    void deleteCustomersByRealEstateId(int id);

    @Query(
            value="select * from customers where owner_id=?1",
            countQuery="select count(*) from customers where owner_id=?1",
            nativeQuery=true)
    Page<Customers> findAllByOwnerUSer(int owner_id,Pageable pageable);

    @Modifying
    @Query(value="update customers set status=true where id=?1",
    nativeQuery=true)
    void changeStatus(int id);
}
