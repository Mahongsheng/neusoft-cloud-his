package com.neu.his.vojo;

import lombok.Data;

@Data
public class RegistrationInfo {
    private int medicalRecordID;
    private String patientName;
    private String gender;
    private int age;
    private String birthday;
    private String numID;
    private String address;
}
