package com.dinhpu.m4casestudy.utils;

import com.dinhpu.m4casestudy.dto.user.CrmChangePasswordUser;
import com.dinhpu.m4casestudy.dto.user.CrmUpdateUser;
import com.dinhpu.m4casestudy.model.user.User;

public class UserUtils {
    public static CrmUpdateUser userToCrmUpdateUser(User theUser){
        CrmUpdateUser crmUser=new CrmUpdateUser();

        crmUser.setId(theUser.getId());
        crmUser.setUserName(theUser.getUserName());

        crmUser.setFullName(theUser.getFullName());
        crmUser.setPhoneNumber(theUser.getPhoneNumber());
        crmUser.setAddress(theUser.getAddress());
        crmUser.setMoneyAmount(theUser.getMoneyAmount());
        crmUser.setEmail(theUser.getEmail());

        return crmUser;
    }

    public static User crmUserToUser(CrmUpdateUser theCrmUser,String logoPath){
        User user= new User();
        user.setId(theCrmUser.getId());
        user.setUserName(theCrmUser.getUserName());
//        user.setPassword(theCrmUser.getPassword());
        user.setFullName(theCrmUser.getFullName());
        user.setPhoneNumber(theCrmUser.getPhoneNumber());
        user.setAddress(theCrmUser.getAddress());
        user.setMoneyAmount(theCrmUser.getMoneyAmount());
        user.setEmail(theCrmUser.getEmail());
        user.setLogoUrl(logoPath);
        return user;
    }


    public static CrmChangePasswordUser userToCrmChangePasswordUser(User theUser){
        CrmChangePasswordUser crmUser=new CrmChangePasswordUser();
        crmUser.setId(theUser.getId());
        crmUser.setUserName(theUser.getUserName());
        crmUser.setPassword(theUser.getPassword());

        return crmUser;
    }

}
