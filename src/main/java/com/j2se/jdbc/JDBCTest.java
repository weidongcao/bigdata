package com.j2se.jdbc;

import java.sql.*;

public class JDBCTest {
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String url = null;
		String user = null;
		String password = null;
		String sql = null;
		try {
			Class.forName("com.mysql.jdbc.Driver"); // 加载mysq驱动
		} catch (ClassNotFoundException e) {
			System.out.println("驱动加载错误");
			e.printStackTrace();// 打印出错详细信息
		}
		try {
			url = "jdbc:mysql://hadoop-senior.carmon.com:3306/com.bigdata";// 简单写法：url
																																				// =
																																				// "jdbc:myqsl://localhost/test(数据库名)? user=root(用户)&password=yqs2602555(密码)";
			user = "root";
			password = "123123";
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			System.out.println("数据库链接错误");
			e.printStackTrace();
		}
		try {
			stmt = conn.createStatement();
//			sql = "insert into peo(fid, fname, sex, age, relation, city, profession, birthday,treasure, salary, province) values(140, '东奔西走枯无可奈何霜期', 1, 30, '超友谊非恋爱关系', '深圳', '网络工程师', 1993-1-1, 324.12, 23453.23, 20);";// dept这张表有deptno，deptname和age这三个字段
			sql = "insert into peo(fid, fname, sex, age, relation, city, profession, birthday,treasure, salary, province) values(141, '东奔西走枯无可奈何霜期', 1, 30, '超友谊非恋爱关系', '深圳', '网络工程师', 1993-1-1, 324.12, 23453.23, 20);";// dept这张表有deptno，deptname和age这三个字段
			//rs = stmt.executeQuery(sql);// 执行sql语句
			stmt.execute(sql);
//			while (rs.next()) {
//				System.out.print(rs.getInt("deptno") + "   ");
//				System.out.print(rs.getString("deptname") + "   ");
//				System.out.println(rs.getInt("age") + "   ");
//			}
			
		} catch (SQLException e) {
			System.out.println("数据操作错误");
			e.printStackTrace();
		}
		// 关闭数据库
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
		} catch (Exception e) {
			System.out.println("数据库关闭错误");
			e.printStackTrace();
		}
	}
}
