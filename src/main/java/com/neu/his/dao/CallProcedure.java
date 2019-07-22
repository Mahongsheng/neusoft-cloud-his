package com.neu.his.dao;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 此类为dao中用于调用存储过程的JDBC
 *
 * @author 软英1702 马洪升 20175188
 * @date 2019年7月22日10:28:17
 */
public class CallProcedure {

    public static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/his?serverTimezone=UTC";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "mage@1125";

    public static void callProcedure_regist() {
        try {
            Class.forName(DRIVER_CLASS);
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String sql = "{CALL procedure_regist(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"; //调用存储过程
            CallableStatement cstm = connection.prepareCall(sql); //实例化对象cstm

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date birthday = sdf.parse("1999-01-12");
            Date today = new Date();
            java.sql.Date birthdaySQL = new java.sql.Date(birthday.getTime());
            java.sql.Date todaySQL = new java.sql.Date(today.getTime());


            cstm.setString(1, "211202199901120056");
            cstm.setString(2, "马洪升");
            cstm.setInt(3, 71);
            cstm.setDate(4, birthdaySQL);
            cstm.setInt(5, 21);
            cstm.setString(6, "岁");
            cstm.setString(7, "铁岭");
            cstm.setDate(8, todaySQL);
            cstm.setString(9, "上午");
            cstm.setInt(10, 3);
            cstm.setInt(11, 1);
            cstm.setInt(12, 2);
            cstm.setInt(13, 1);
            cstm.setInt(14, 1);
            cstm.setInt(15, 301);

            cstm.registerOutParameter(16, Types.VARCHAR); // 设置返回值类型 即返回值
            cstm.execute(); // 执行存储过程
            System.out.println(cstm.getString(16));

            cstm.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public static void callProcedure_regist_back() {
        try {
            Class.forName(DRIVER_CLASS);
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String sql = "{CALL procedure_regist_back(?,?)}"; //调用存储过程
            CallableStatement cstm = connection.prepareCall(sql); //实例化对象cstm

            cstm.setInt(1, 208);

            cstm.registerOutParameter(2, Types.VARCHAR); // 设置返回值类型 即返回值
            cstm.execute(); // 执行存储过程
            System.out.println(cstm.getString(2));

            cstm.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void callProcedure_prescribe() {
        try {
            Class.forName(DRIVER_CLASS);
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String sql = "{CALL procedure_prescribe(?,?,?,?)}"; //调用存储过程
            CallableStatement cstm = connection.prepareCall(sql); //实例化对象cstm

            cstm.setInt(1, 3);
            cstm.setString(2, "测试用例");
            cstm.setString(3, "山药颗粒，5，口服，100g，一日三次|黄连颗粒，10，口服，50g，一日一次");


            cstm.registerOutParameter(4, Types.VARCHAR); // 设置返回值类型 即返回值
            cstm.execute(); // 执行存储过程
            System.out.println(cstm.getString(4));

            cstm.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void callProcedure_diagnose() {
        try {
            Class.forName(DRIVER_CLASS);
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String sql = "{CALL procedure_diagnose(?,?,?)}"; //调用存储过程
            CallableStatement cstm = connection.prepareCall(sql); //实例化对象cstm

            cstm.setInt(1, 3);
            cstm.setString(2, "测试");

            cstm.registerOutParameter(3, Types.VARCHAR); // 设置返回值类型 即返回值
            cstm.execute(); // 执行存储过程
            System.out.println(cstm.getString(3));

            cstm.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void callProcedure_charge() {
        try {
            Class.forName(DRIVER_CLASS);
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String sql = "{CALL procedure_charge(?,?,?)}"; //调用存储过程
            CallableStatement cstm = connection.prepareCall(sql); //实例化对象cstm

            cstm.setInt(1, 99);
            cstm.setInt(2, 1);

            cstm.registerOutParameter(3, Types.VARCHAR); // 设置返回值类型 即返回值
            cstm.execute(); // 执行存储过程
            System.out.println(cstm.getString(3));

            cstm.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void callProcedure_refund() {
        try {
            Class.forName(DRIVER_CLASS);
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String sql = "{CALL procedure_refund(?,?,?,?)}"; //调用存储过程
            CallableStatement cstm = connection.prepareCall(sql); //实例化对象cstm

            cstm.setInt(1, 99);
            cstm.setInt(2, 800839);//现找一下发票号
            cstm.setInt(3, 1);


            cstm.registerOutParameter(4, Types.VARCHAR); // 设置返回值类型 即返回值
            cstm.execute(); // 执行存储过程
            System.out.println(cstm.getString(4));

            cstm.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void callProcedure_dispensing() {
        try {
            Class.forName(DRIVER_CLASS);
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String sql = "{CALL procedure_dispensing(?,?)}"; //调用存储过程
            CallableStatement cstm = connection.prepareCall(sql); //实例化对象cstm

            cstm.setInt(1, 99);

            cstm.registerOutParameter(2, Types.VARCHAR); // 设置返回值类型 即返回值
            cstm.execute(); // 执行存储过程
            System.out.println(cstm.getString(2));

            cstm.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        callProcedure_regist();
//        callProcedure_regist_back();
//        callProcedure_prescribe();
//        callProcedure_diagnose();
//        callProcedure_charge();
//        callProcedure_refund();
//        callProcedure_dispensing();
    }

}
