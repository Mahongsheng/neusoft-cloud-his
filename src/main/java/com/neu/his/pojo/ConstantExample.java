package com.neu.his.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ConstantExample implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table constant
     *
     * @mbg.generated Mon Jul 22 23:45:36 CST 2019
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table constant
     *
     * @mbg.generated Mon Jul 22 23:45:36 CST 2019
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table constant
     *
     * @mbg.generated Mon Jul 22 23:45:36 CST 2019
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table constant
     *
     * @mbg.generated Mon Jul 22 23:45:36 CST 2019
     */
    public ConstantExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table constant
     *
     * @mbg.generated Mon Jul 22 23:45:36 CST 2019
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table constant
     *
     * @mbg.generated Mon Jul 22 23:45:36 CST 2019
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table constant
     *
     * @mbg.generated Mon Jul 22 23:45:36 CST 2019
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table constant
     *
     * @mbg.generated Mon Jul 22 23:45:36 CST 2019
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table constant
     *
     * @mbg.generated Mon Jul 22 23:45:36 CST 2019
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table constant
     *
     * @mbg.generated Mon Jul 22 23:45:36 CST 2019
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table constant
     *
     * @mbg.generated Mon Jul 22 23:45:36 CST 2019
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table constant
     *
     * @mbg.generated Mon Jul 22 23:45:36 CST 2019
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table constant
     *
     * @mbg.generated Mon Jul 22 23:45:36 CST 2019
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table constant
     *
     * @mbg.generated Mon Jul 22 23:45:36 CST 2019
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table constant
     *
     * @mbg.generated Mon Jul 22 23:45:36 CST 2019
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andConstantIdIsNull() {
            addCriterion("constant_id is null");
            return (Criteria) this;
        }

        public Criteria andConstantIdIsNotNull() {
            addCriterion("constant_id is not null");
            return (Criteria) this;
        }

        public Criteria andConstantIdEqualTo(Integer value) {
            addCriterion("constant_id =", value, "constantId");
            return (Criteria) this;
        }

        public Criteria andConstantIdNotEqualTo(Integer value) {
            addCriterion("constant_id <>", value, "constantId");
            return (Criteria) this;
        }

        public Criteria andConstantIdGreaterThan(Integer value) {
            addCriterion("constant_id >", value, "constantId");
            return (Criteria) this;
        }

        public Criteria andConstantIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("constant_id >=", value, "constantId");
            return (Criteria) this;
        }

        public Criteria andConstantIdLessThan(Integer value) {
            addCriterion("constant_id <", value, "constantId");
            return (Criteria) this;
        }

        public Criteria andConstantIdLessThanOrEqualTo(Integer value) {
            addCriterion("constant_id <=", value, "constantId");
            return (Criteria) this;
        }

        public Criteria andConstantIdIn(List<Integer> values) {
            addCriterion("constant_id in", values, "constantId");
            return (Criteria) this;
        }

        public Criteria andConstantIdNotIn(List<Integer> values) {
            addCriterion("constant_id not in", values, "constantId");
            return (Criteria) this;
        }

        public Criteria andConstantIdBetween(Integer value1, Integer value2) {
            addCriterion("constant_id between", value1, value2, "constantId");
            return (Criteria) this;
        }

        public Criteria andConstantIdNotBetween(Integer value1, Integer value2) {
            addCriterion("constant_id not between", value1, value2, "constantId");
            return (Criteria) this;
        }

        public Criteria andConstantTypeIdIsNull() {
            addCriterion("constant_type_id is null");
            return (Criteria) this;
        }

        public Criteria andConstantTypeIdIsNotNull() {
            addCriterion("constant_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andConstantTypeIdEqualTo(Byte value) {
            addCriterion("constant_type_id =", value, "constantTypeId");
            return (Criteria) this;
        }

        public Criteria andConstantTypeIdNotEqualTo(Byte value) {
            addCriterion("constant_type_id <>", value, "constantTypeId");
            return (Criteria) this;
        }

        public Criteria andConstantTypeIdGreaterThan(Byte value) {
            addCriterion("constant_type_id >", value, "constantTypeId");
            return (Criteria) this;
        }

        public Criteria andConstantTypeIdGreaterThanOrEqualTo(Byte value) {
            addCriterion("constant_type_id >=", value, "constantTypeId");
            return (Criteria) this;
        }

        public Criteria andConstantTypeIdLessThan(Byte value) {
            addCriterion("constant_type_id <", value, "constantTypeId");
            return (Criteria) this;
        }

        public Criteria andConstantTypeIdLessThanOrEqualTo(Byte value) {
            addCriterion("constant_type_id <=", value, "constantTypeId");
            return (Criteria) this;
        }

        public Criteria andConstantTypeIdIn(List<Byte> values) {
            addCriterion("constant_type_id in", values, "constantTypeId");
            return (Criteria) this;
        }

        public Criteria andConstantTypeIdNotIn(List<Byte> values) {
            addCriterion("constant_type_id not in", values, "constantTypeId");
            return (Criteria) this;
        }

        public Criteria andConstantTypeIdBetween(Byte value1, Byte value2) {
            addCriterion("constant_type_id between", value1, value2, "constantTypeId");
            return (Criteria) this;
        }

        public Criteria andConstantTypeIdNotBetween(Byte value1, Byte value2) {
            addCriterion("constant_type_id not between", value1, value2, "constantTypeId");
            return (Criteria) this;
        }

        public Criteria andConstantCodeIsNull() {
            addCriterion("constant_code is null");
            return (Criteria) this;
        }

        public Criteria andConstantCodeIsNotNull() {
            addCriterion("constant_code is not null");
            return (Criteria) this;
        }

        public Criteria andConstantCodeEqualTo(String value) {
            addCriterion("constant_code =", value, "constantCode");
            return (Criteria) this;
        }

        public Criteria andConstantCodeNotEqualTo(String value) {
            addCriterion("constant_code <>", value, "constantCode");
            return (Criteria) this;
        }

        public Criteria andConstantCodeGreaterThan(String value) {
            addCriterion("constant_code >", value, "constantCode");
            return (Criteria) this;
        }

        public Criteria andConstantCodeGreaterThanOrEqualTo(String value) {
            addCriterion("constant_code >=", value, "constantCode");
            return (Criteria) this;
        }

        public Criteria andConstantCodeLessThan(String value) {
            addCriterion("constant_code <", value, "constantCode");
            return (Criteria) this;
        }

        public Criteria andConstantCodeLessThanOrEqualTo(String value) {
            addCriterion("constant_code <=", value, "constantCode");
            return (Criteria) this;
        }

        public Criteria andConstantCodeLike(String value) {
            addCriterion("constant_code like", value, "constantCode");
            return (Criteria) this;
        }

        public Criteria andConstantCodeNotLike(String value) {
            addCriterion("constant_code not like", value, "constantCode");
            return (Criteria) this;
        }

        public Criteria andConstantCodeIn(List<String> values) {
            addCriterion("constant_code in", values, "constantCode");
            return (Criteria) this;
        }

        public Criteria andConstantCodeNotIn(List<String> values) {
            addCriterion("constant_code not in", values, "constantCode");
            return (Criteria) this;
        }

        public Criteria andConstantCodeBetween(String value1, String value2) {
            addCriterion("constant_code between", value1, value2, "constantCode");
            return (Criteria) this;
        }

        public Criteria andConstantCodeNotBetween(String value1, String value2) {
            addCriterion("constant_code not between", value1, value2, "constantCode");
            return (Criteria) this;
        }

        public Criteria andConstantNameIsNull() {
            addCriterion("constant_name is null");
            return (Criteria) this;
        }

        public Criteria andConstantNameIsNotNull() {
            addCriterion("constant_name is not null");
            return (Criteria) this;
        }

        public Criteria andConstantNameEqualTo(String value) {
            addCriterion("constant_name =", value, "constantName");
            return (Criteria) this;
        }

        public Criteria andConstantNameNotEqualTo(String value) {
            addCriterion("constant_name <>", value, "constantName");
            return (Criteria) this;
        }

        public Criteria andConstantNameGreaterThan(String value) {
            addCriterion("constant_name >", value, "constantName");
            return (Criteria) this;
        }

        public Criteria andConstantNameGreaterThanOrEqualTo(String value) {
            addCriterion("constant_name >=", value, "constantName");
            return (Criteria) this;
        }

        public Criteria andConstantNameLessThan(String value) {
            addCriterion("constant_name <", value, "constantName");
            return (Criteria) this;
        }

        public Criteria andConstantNameLessThanOrEqualTo(String value) {
            addCriterion("constant_name <=", value, "constantName");
            return (Criteria) this;
        }

        public Criteria andConstantNameLike(String value) {
            addCriterion("constant_name like", value, "constantName");
            return (Criteria) this;
        }

        public Criteria andConstantNameNotLike(String value) {
            addCriterion("constant_name not like", value, "constantName");
            return (Criteria) this;
        }

        public Criteria andConstantNameIn(List<String> values) {
            addCriterion("constant_name in", values, "constantName");
            return (Criteria) this;
        }

        public Criteria andConstantNameNotIn(List<String> values) {
            addCriterion("constant_name not in", values, "constantName");
            return (Criteria) this;
        }

        public Criteria andConstantNameBetween(String value1, String value2) {
            addCriterion("constant_name between", value1, value2, "constantName");
            return (Criteria) this;
        }

        public Criteria andConstantNameNotBetween(String value1, String value2) {
            addCriterion("constant_name not between", value1, value2, "constantName");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table constant
     *
     * @mbg.generated do_not_delete_during_merge Mon Jul 22 23:45:36 CST 2019
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table constant
     *
     * @mbg.generated Mon Jul 22 23:45:36 CST 2019
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}