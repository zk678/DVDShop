package com.bbm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bbm.utils.JDBCUtils;
import org.junit.Test;

public class TestUtils {
	@Test
	public void test() {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();
			//sql语句 
			String sql = "insert into user(username,password) values(?,?)";
			//创建语句执行者
			st = conn.prepareStatement(sql);
			//设置参数
			st.setString(1, "1");//账号
			st.setString(2, "1");//密码
			int i = st.executeUpdate();
			if(i==1)
				System.out.println("数据添加成功");
			else
				System.out.println("数据添加失败");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
