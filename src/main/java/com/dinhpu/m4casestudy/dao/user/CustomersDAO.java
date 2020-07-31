package com.dinhpu.m4casestudy.dao.user;

import com.dinhpu.m4casestudy.model.user.Customers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomersDAO extends JpaRepository<Customers,Long> {
}
