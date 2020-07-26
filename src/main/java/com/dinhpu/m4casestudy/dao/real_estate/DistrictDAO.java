package com.dinhpu.m4casestudy.dao.real_estate;

import com.dinhpu.m4casestudy.model.real_estate.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DistrictDAO extends JpaRepository<District,Long> {

    @Query(value="select district.* from district inner join province on district.province_id=province.id where 1=1 and province.name=:name",nativeQuery=true)
    public List<District> myAllQueryDistrictByProvinceName(@Param("name") String name);

}
