package com.duny.fcr.serviceImp;

import java.util.Base64;

public class UtilityClass {

    public String base64(byte[] bytes){
        return Base64.getEncoder().encodeToString(bytes);
    }
}
