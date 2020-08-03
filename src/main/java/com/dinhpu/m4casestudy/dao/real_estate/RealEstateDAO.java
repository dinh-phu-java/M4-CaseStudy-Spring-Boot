package com.dinhpu.m4casestudy.dao.real_estate;

import com.dinhpu.m4casestudy.model.real_estate.RealEstate;
import com.dinhpu.m4casestudy.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RealEstateDAO extends JpaRepository<RealEstate,Long> {

    @Query(value="select * from real_estate where advertise=?1"
            ,countQuery="select count(*) from real_estate where advertise=?1"
            ,nativeQuery=true)
    public List<RealEstate> findAllByAdvertise(boolean ad,Pageable pageable);

    @Query(value="select * from real_estate where user_id =?1",
        countQuery="select count(*) from real_estate where user_id=?1",
            nativeQuery=true)
    public Page<RealEstate> findAllRealEstateByUserId(int id,Pageable pageable);

    public List<RealEstate> findAllByUser(User user);

    @Query(value="select * from real_estate where advertise=false"
    ,countQuery="select count(*) from real_estate where advertise=false"
            ,nativeQuery=true
    )
    List<RealEstate> findAllRecent(Pageable pageable);


    @Query(value="select * from real_estate where province like %?1% and legal_paper like ?2% and real_estate_type like %?3% and category like %?4% and direction like %?5%",
    countQuery="select count(*) from real_estate where province like %?1% and legal_paper like ?2% and real_estate_type like %?3% and category like %?4% and direction like %?5",
    nativeQuery=true)
    Page<RealEstate> searchQuery(String province,String price,String realEstateType,String category,String direction, Pageable pageable);

    @Query(value="select * from real_estate where advertise=true",
    countQuery="select count(*) from real_estate where advertise=true",
    nativeQuery=true)
    Page<RealEstate> findAllByAdvertise(Pageable pageable);

    @Modifying
    @Query(value="update real_estate set advertise=false where id=?1",nativeQuery=true)
    void removeAdvertise(Long id);

    @Modifying
    @Query(value="update real_estate set advertise=true where id=?1",nativeQuery = true)
    void updateAdvertise(Long id);


    @Query(value="select * from real_estate where user_id=?1"
            ,countQuery="select count(*) from real_estate where user_id=?1"
            ,nativeQuery=true)
    Page<RealEstate> findAllBySelectUser(int id,Pageable pageable);
}
