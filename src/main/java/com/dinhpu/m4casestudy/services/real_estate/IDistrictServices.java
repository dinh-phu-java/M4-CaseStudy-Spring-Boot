package com.dinhpu.m4casestudy.services.real_estate;

import com.dinhpu.m4casestudy.model.real_estate.District;
import com.dinhpu.m4casestudy.services.IServices;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IDistrictServices extends IServices<District> {

    public List<District> myAllDistrictQueryByProvinceName(String provinceName);
}
