package com.neu.his.dto;

import lombok.Data;

import java.util.Date;

@Data
public class RegisterBackDTO {
    private Integer medicalRecordID;
    private Date registerDate;
    private String registerNoon;
    private String department;
}
