package com.dinhpu.m4casestudy.dao.real_estate;

import com.dinhpu.m4casestudy.model.real_estate.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WardDAO extends JpaRepository<Ward,Long> {
    @Query(value="select w.* from ward w inner join district d on w.district_id=d.id where d.name=:name",nativeQuery=true)
    public List<Ward> findAllWardByDistrictName(@Param("name") String name);
}
