package com.dinhpu.m4casestudy.utils;

import com.dinhpu.m4casestudy.dto.real_estate.RealEstateDTO;
import com.dinhpu.m4casestudy.model.real_estate.InternalUtilities;
import com.dinhpu.m4casestudy.model.real_estate.RealEstate;

public class RealEstateUtils {
    public static void setInternalUtilities(String checkBoxValue, InternalUtilities internalUtilities){
        switch (checkBoxValue){
            case "internet":
                internalUtilities.setInternet(true);
                break;
            case "television":
                internalUtilities.setTelevision(true);
                break;
            case "toilet":
                internalUtilities.setToilet(true);
                break;
            case "aircondition":
                internalUtilities.setAirCondition(true);
                break;
            case "kitchen":
                internalUtilities.setKitchen(true);
                break;
        }
    }

    public static RealEstate realEstateDTOToRealEstate(RealEstateDTO realEstateDTO) {
        RealEstate realEstate=new RealEstate();
        realEstate.setAddress(realEstateDTO.getAddress());
        realEstate.setAreaType(realEstateDTO.getAreaType());
        realEstate.setBathRoomQuantity(realEstateDTO.getBathRoomQuantity());
        realEstate.setBedRoomQuantity(realEstateDTO.getBathRoomQuantity());
        realEstate.setCategory(realEstateDTO.getCategory());
        realEstate.setDescription(realEstateDTO.getDescription());
        realEstate.setDirection(realEstateDTO.getDirection());
        realEstate.setDistrict(realEstateDTO.getDistrict());
        realEstate.setAroundUtilities(realEstateDTO.getAroundUtilities());
        realEstate.setExternalUtilities(realEstateDTO.getExternalUtilities());
        realEstate.setInternalUtilities(realEstateDTO.getInternalUtilities());
        realEstate.setLegalPaper(realEstateDTO.getLegalPaper());
        realEstate.setPriceUnit(realEstateDTO.getPriceUnit());
        realEstate.setProvince(realEstateDTO.getProvince());
        realEstate.setRealEstateImage(realEstateDTO.getRealEstateImage());
        realEstate.setRealEstateType(realEstateDTO.getRealEstateType());
        realEstate.setTitle(realEstateDTO.getTitle());
        realEstate.setTotalArea(realEstateDTO.getTotalArea());
        realEstate.setTotalprice(realEstateDTO.getTotalprice());
        realEstate.setUser(realEstateDTO.getUser());
        realEstate.setWard(realEstateDTO.getWard());
        return realEstate;
    }
}
