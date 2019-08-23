package com.neu.his.dto;

import lombok.Data;

import java.util.Date;

@Data
public class RegisterDTO {
    private Integer invoiceID;//发票号
    private Integer medicalRecordID;//病历号
    private String name;//患者名
    private String gender;//性别
    private Integer age;//年龄
    private String ageType;//岁、月、日
    private Date birthday;//生日
    private String numID;//身份证号
    private String address;//家庭住址
    private String chargeType;//结算类别
    private Date registerDate;//挂号日期
    private String registerNoon;//挂号午别
    private String department;//科室
    private String registerLevel;//挂号等级
    private String doctorName;//医生姓名
    private Integer registerNum;//挂号额
    private Integer registerUsedNum;//已挂号额
    private String medicalBook;//是否需要病历本
    private Double money;//金额
    private String chargeWay;//收费方式
    private Short registerUserID;//挂号员ID
}
