package com.neu.his.vojo;

import lombok.Data;

import java.util.Date;

@Data
public class RegistrationInfo {
    private int medicalRecordID;
    private String patientName;
    private String gender;
    private int age;
    private Date birthday;
    private String numID;
    private String address;
}
