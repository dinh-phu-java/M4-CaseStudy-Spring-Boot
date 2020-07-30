package com.dinhpu.m4casestudy.services.real_estate;

import com.dinhpu.m4casestudy.model.real_estate.RealEstate;
import com.dinhpu.m4casestudy.model.real_estate.RealEstateImage;
import com.dinhpu.m4casestudy.services.IServices;

import java.util.List;

public interface IRealEstateImage extends IServices<RealEstateImage> {
    public List<RealEstateImage> findAllByRealEstate(RealEstate realEstate);
    public void removeAllByRealEstate(RealEstate realEstate);
}
