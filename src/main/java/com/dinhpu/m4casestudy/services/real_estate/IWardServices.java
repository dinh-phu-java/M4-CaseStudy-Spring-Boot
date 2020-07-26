package com.dinhpu.m4casestudy.services.real_estate;

import com.dinhpu.m4casestudy.model.real_estate.Ward;
import com.dinhpu.m4casestudy.services.IServices;

import java.util.List;

public interface IWardServices extends IServices<Ward> {
    public List<Ward> findAllWardByDistrictName(String districtName);
}
