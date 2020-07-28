package com.dinhpu.m4casestudy.dao.real_estate;

import com.dinhpu.m4casestudy.model.real_estate.RealEstate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RealEstateDAO extends JpaRepository<RealEstate,Long> {

    @Query(value="select * from real_estate where user_id =?1",
        countQuery="select count(*) from real_estate where user_id=?1",
            nativeQuery=true)
    public Page<RealEstate> findAllRealEstateByUserId(int id,Pageable pageable);
}
