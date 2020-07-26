package com.dinhpu.m4casestudy.dao.real_estate;

import com.dinhpu.m4casestudy.model.real_estate.AreaType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaTypeDAO extends JpaRepository<AreaType,Long> {
}
