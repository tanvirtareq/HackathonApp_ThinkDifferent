package com.example.thinkdifferent;

import com.google.firebase.auth.UserInfo;

import java.util.HashMap;
import java.util.Map;

public class UserProfileClass {


    public   String Email, UID ;


    UserProfileClass(String Email, String UID){

         this.Email=Email;
         this.UID=UID;
    }

    public   Map UserInfoMap(){

        Map userInfo;
        userInfo = new HashMap();
        userInfo.put("Email",Email);
        userInfo.put("UID",UID);
        return userInfo;
    }

}
