package com.neu.his.vojo;

import lombok.Data;

@Data
public class LoginReturn {
    private String userType;
    private String userName;
    private boolean ifNameRight;
    private boolean ifPswRight;
}
