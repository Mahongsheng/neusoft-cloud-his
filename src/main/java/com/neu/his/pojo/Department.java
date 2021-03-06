package com.neu.his.pojo;

import java.io.Serializable;

public class Department implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column department.dept_id
     *
     * @mbg.generated Mon Jul 22 23:45:36 CST 2019
     */
    private Short deptId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column department.dept_code
     *
     * @mbg.generated Mon Jul 22 23:45:36 CST 2019
     */
    private String deptCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column department.dept_name
     *
     * @mbg.generated Mon Jul 22 23:45:36 CST 2019
     */
    private String deptName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column department.dept_category
     *
     * @mbg.generated Mon Jul 22 23:45:36 CST 2019
     */
    private Byte deptCategory;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column department.dept_type
     *
     * @mbg.generated Mon Jul 22 23:45:36 CST 2019
     */
    private Boolean deptType;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column department.dept_id
     *
     * @return the value of department.dept_id
     *
     * @mbg.generated Mon Jul 22 23:45:36 CST 2019
     */
    public Short getDeptId() {
        return deptId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column department.dept_id
     *
     * @param deptId the value for department.dept_id
     *
     * @mbg.generated Mon Jul 22 23:45:36 CST 2019
     */
    public void setDeptId(Short deptId) {
        this.deptId = deptId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column department.dept_code
     *
     * @return the value of department.dept_code
     *
     * @mbg.generated Mon Jul 22 23:45:36 CST 2019
     */
    public String getDeptCode() {
        return deptCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column department.dept_code
     *
     * @param deptCode the value for department.dept_code
     *
     * @mbg.generated Mon Jul 22 23:45:36 CST 2019
     */
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode == null ? null : deptCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column department.dept_name
     *
     * @return the value of department.dept_name
     *
     * @mbg.generated Mon Jul 22 23:45:36 CST 2019
     */
    public String getDeptName() {
        return deptName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column department.dept_name
     *
     * @param deptName the value for department.dept_name
     *
     * @mbg.generated Mon Jul 22 23:45:36 CST 2019
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column department.dept_category
     *
     * @return the value of department.dept_category
     *
     * @mbg.generated Mon Jul 22 23:45:36 CST 2019
     */
    public Byte getDeptCategory() {
        return deptCategory;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column department.dept_category
     *
     * @param deptCategory the value for department.dept_category
     *
     * @mbg.generated Mon Jul 22 23:45:36 CST 2019
     */
    public void setDeptCategory(Byte deptCategory) {
        this.deptCategory = deptCategory;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column department.dept_type
     *
     * @return the value of department.dept_type
     *
     * @mbg.generated Mon Jul 22 23:45:36 CST 2019
     */
    public Boolean getDeptType() {
        return deptType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column department.dept_type
     *
     * @param deptType the value for department.dept_type
     *
     * @mbg.generated Mon Jul 22 23:45:36 CST 2019
     */
    public void setDeptType(Boolean deptType) {
        this.deptType = deptType;
    }
}