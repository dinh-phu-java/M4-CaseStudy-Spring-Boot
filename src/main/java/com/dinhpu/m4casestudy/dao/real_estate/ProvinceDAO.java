package com.dinhpu.m4casestudy.dao.real_estate;

import com.dinhpu.m4casestudy.model.real_estate.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProvinceDAO extends JpaRepository<Province,Long> {

}
