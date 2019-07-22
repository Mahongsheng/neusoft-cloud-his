package com.neu.his.dto;

import lombok.Data;

@Data
public class UserLoginDTO {
    private String userType;
    private String userLoginName;
    private String userPsw;
}
