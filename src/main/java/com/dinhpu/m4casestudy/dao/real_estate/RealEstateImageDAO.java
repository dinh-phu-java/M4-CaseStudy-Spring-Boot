package com.dinhpu.m4casestudy.dao.real_estate;

import com.dinhpu.m4casestudy.model.real_estate.RealEstate;
import com.dinhpu.m4casestudy.model.real_estate.RealEstateImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RealEstateImageDAO  extends JpaRepository<RealEstateImage,Long> {
    public List<RealEstateImage> findAllByRealEstate(RealEstate real);
    public void removeAllByRealEstate(RealEstate real);
}
