package com.bigdata.hive;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

/**
 * 用于测试世理机器人调度的数据结果是否正确
 * Created by CaoWeidong on 2016/11/29 0029.
 */
public class TestAnalyzeResult {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        final Logger logger = LoggerFactory.getLogger(TestAnalyzeResult.class);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String original1 = "select count(%s) from %s where ds like %s";
        String original2 = "select * from %s";
        String  tablename = null;
        String date = null;
        String sql = null;
        String fullSql = null;
        String desc = "desc %s";

        if ((null == args) || (2 > args.length)) {
            System.out.println("没有输入表名或者查询日期,请输入表名:");
            tablename = br.readLine();
            System.out.println("请输入查询日期");
            date = br.readLine();
        } else {
            tablename = args[0];
            date = args[1];
        }

        Connection conn = HiveUtil.getConnection(HiveUtil.DRIVERNAME, HiveUtil.URL, HiveUtil.USERNAME, HiveUtil.PASSWORD);
        Statement stmt = conn.createStatement();
        logger.debug("run sql : {}", fullSql);
        ResultSet res = stmt.executeQuery(String.format(desc, tablename));
        res.getString(1);
        /*while (res.next()) {
            System.out.println(res.getString(1) + "\t" + res.getString(2));
//            System.out.println(res.getString(1));
        }*/


        HiveUtil.closeConnection(conn, stmt);
    }




}
