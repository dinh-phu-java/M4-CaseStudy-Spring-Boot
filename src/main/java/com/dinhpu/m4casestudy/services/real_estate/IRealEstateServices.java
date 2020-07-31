package com.dinhpu.m4casestudy.services.real_estate;

import com.dinhpu.m4casestudy.model.real_estate.RealEstate;
import com.dinhpu.m4casestudy.model.user.User;
import com.dinhpu.m4casestudy.services.IServices;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IRealEstateServices {
    public Page<RealEstate> findAll(int pageNo, int pageSize, Optional<String> sortBy);
    public RealEstate findById(Long id);
    public RealEstate save(RealEstate model);
    public RealEstate remove(Long id);
    public List<RealEstate> findAll();
    Page<RealEstate> findAllRealEstateByUserId(int id,Pageable pageable);
    public List<RealEstate> findAllByUser(User loginUser);
    public List<RealEstate> findAllByAdvertise(boolean ad,Pageable pageble);

    List<RealEstate> findAllRecent();
}
