package com.dinhpu.m4casestudy.utils;

import com.dinhpu.m4casestudy.model.real_estate.InternalUtilities;

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
}
