package com.dinhpu.m4casestudy.utils;

import com.dinhpu.m4casestudy.dto.real_estate.RealEstateDTO;
import com.dinhpu.m4casestudy.model.real_estate.AroundUtilities;
import com.dinhpu.m4casestudy.model.real_estate.ExternalUtilities;
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

    public static void loopForSetInternalUtilites(String[] internals,InternalUtilities internalUtilities){
        for (int i=0;i< internals.length;i++){
            RealEstateUtils.setInternalUtilities(internals[i],internalUtilities);
        }
    }

    public static void setExternalUtilities(String checkBoxValue, ExternalUtilities externalUtilities){
        switch (checkBoxValue){
            case "balcony":
                externalUtilities.setBalcony(true);
                break;
            case "garden":
                externalUtilities.setGarden(true);
                break;
            case "swimmingpool":
                externalUtilities.setSwimmingPool(true);
                break;
            case "services":
                externalUtilities.setServices(true);
                break;
            case "security":
                externalUtilities.setSecurity(true);
                break;
        }
    }


    public static void loopForSetExternalUtilites(String[] external,ExternalUtilities externalUtilities){
        for (int i=0;i< external.length;i++){
            RealEstateUtils.setExternalUtilities(external[i],externalUtilities);
        }
    }

    public static void setAroundUtilities(String checkBoxValue, AroundUtilities aroundUtilities){
        switch (checkBoxValue){
            case "hospital":
                aroundUtilities.setHospital(true);
                break;
            case "market":
                aroundUtilities.setMarket(true);
                break;
            case "police":
                aroundUtilities.setPolice(true);
                break;
            case "school":
                aroundUtilities.setSchool(true);
                break;

        }
    }


    public static void loopForSetAroundUtilites(String[] arounds,AroundUtilities aroundUtilities){
        for (int i=0;i< arounds.length;i++){
            RealEstateUtils.setAroundUtilities(arounds[i],aroundUtilities);
        }
    }

    public static RealEstate realEstateDTOToRealEstate(RealEstateDTO realEstateDTO, String donVi) {
        RealEstate realEstate=new RealEstate();
        realEstate.setId(realEstateDTO.getId());
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
        realEstate.setPriceUnit(realEstateDTO.getPriceUnit()+" triệu VND/m2");
        realEstate.setProvince(realEstateDTO.getProvince());
//        realEstate.setRealEstateImage(realEstateDTO.getRealEstateImage());
        realEstate.setRealEstateType(realEstateDTO.getRealEstateType());
        realEstate.setTitle(realEstateDTO.getTitle());
        realEstate.setTotalArea(realEstateDTO.getTotalArea());
        realEstate.setTotalprice(realEstateDTO.getTotalprice()+" "+donVi);
        realEstate.setUser(realEstateDTO.getUser());
        realEstate.setWard(realEstateDTO.getWard());
        return realEstate;
    }


    public static RealEstateDTO realEstateToRealEstateDTO(RealEstate realEstate) {
        RealEstateDTO realEstateDTO=new RealEstateDTO();
        realEstateDTO.setId(realEstate.getId());
        realEstateDTO.setAddress(realEstate.getAddress());
        realEstateDTO.setAreaType(realEstate.getAreaType());
        realEstateDTO.setBathRoomQuantity(realEstate.getBathRoomQuantity());
        realEstateDTO.setBedRoomQuantity(realEstate.getBathRoomQuantity());
        realEstateDTO.setCategory(realEstate.getCategory());
        realEstateDTO.setDescription(realEstate.getDescription());
        realEstateDTO.setDirection(realEstate.getDirection());
        realEstateDTO.setDistrict(realEstate.getDistrict());
        realEstateDTO.setAroundUtilities(realEstate.getAroundUtilities());
        realEstateDTO.setExternalUtilities(realEstate.getExternalUtilities());
        realEstateDTO.setInternalUtilities(realEstate.getInternalUtilities());
        realEstateDTO.setLegalPaper(realEstate.getLegalPaper());
        realEstateDTO.setPriceUnit(realEstate.getPriceUnit());
        realEstateDTO.setProvince(realEstate.getProvince());

        realEstateDTO.setRealEstateType(realEstate.getRealEstateType());
        realEstateDTO.setTitle(realEstate.getTitle());
        realEstateDTO.setTotalArea(realEstate.getTotalArea());
        realEstateDTO.setTotalprice(realEstate.getTotalprice());
        realEstateDTO.setUser(realEstate.getUser());
        realEstateDTO.setWard(realEstate.getWard());
        return realEstateDTO;
    }
}
