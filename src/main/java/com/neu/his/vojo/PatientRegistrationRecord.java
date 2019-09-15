package com.neu.his.vojo;

import lombok.Data;

@Data
public class PatientRegistrationRecord {
    private int registrationID;
    private String registrationDate;
    private String registrationNoon;
    private String department;
    private String registrationState;

    private int wholePage;
}
