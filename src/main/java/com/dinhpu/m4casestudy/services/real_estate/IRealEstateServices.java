package com.dinhpu.m4casestudy.services.real_estate;

import com.dinhpu.m4casestudy.model.real_estate.RealEstate;
import com.dinhpu.m4casestudy.services.IServices;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IRealEstateServices {
    public Page<RealEstate> findAll(Pageable pageable);
    public RealEstate findById(Long id);
    public RealEstate save(RealEstate model);
    public RealEstate remove(Long id);
}
