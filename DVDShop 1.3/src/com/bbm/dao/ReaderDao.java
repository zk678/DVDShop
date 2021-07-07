package com.bbm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bbm.entity.Reader;
import com.bbm.utils.JDBCUtils;

public class ReaderDao {
	private Connection conn = null;
	private PreparedStatement st = null;
	private ResultSet rs = null;

	/**
	 * 添加读者信息
	 */
	public int addReader(Reader reader) {
		String sql = "insert into reader values(?,?,?,?,now(),1)";
		int row = 0;
		try {
			conn = JDBCUtils.getConnection();
			st = conn.prepareStatement(sql);
			st.setString(1, reader.getReaderId());
			st.setString(2, reader.getName());
			st.setString(3, reader.getSex());
			st.setString(4, reader.getPhone());
			row = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.colseAll(conn, st, rs);
		}
		return row;
	}

	/**
	 * 根据读着编号查询读者是否存在
	 */
	public int findByReaderId(String readerId) {
		String sql = "select * from reader where readerid = ? ";//and state = 1
		try {
			conn = JDBCUtils.getConnection();
			st = conn.prepareStatement(sql);
			st.setString(1, readerId);
			rs = st.executeQuery();
			if (rs.next())
				return 1;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.colseAll(conn, st, rs);
		}
		return 0;
	}

	/**
	 * 查询所有读着
	 */
	public List<Reader> findAll() {
		String sql = "select * from reader where state = 1";
		List<Reader> list = new ArrayList<>();
		Reader reader;
		try {
			conn = JDBCUtils.getConnection();
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			while (rs.next()) {
				reader = new Reader();
				reader.setReaderId(rs.getString(1));
				reader.setName(rs.getString(2));
				reader.setSex(rs.getString(3));
				reader.setPhone(rs.getString(4));
				reader.setRegDate(rs.getString(5));
				list.add(reader);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.colseAll(conn, st, rs);
		}
		return list;
	}

	/**
	 * 将list<Reader> 转为 string 数组
	 */
	public String[][] getArrayData(List<Reader> lists) {
		String[][] data = new String[lists.size()][5];
		Reader reader = null;
		for (int i = 0; i < lists.size(); i++) {
			reader = lists.get(i);
			data[i][0] = reader.getReaderId();
			data[i][1] = reader.getName();
			data[i][3] = reader.getSex();
			data[i][2] = reader.getPhone();
			data[i][4] = reader.getRegDate();
		}
		return data;
	}

	/**
	 * 根据readerId 删除 reader
	 */
	public int delete(String readerId) {
		String sql = "update reader set state = 0 where readerid = ?";
		try {
			conn = JDBCUtils.getConnection();
			st = conn.prepareStatement(sql);
			st.setString(1, readerId);
			int row = st.executeUpdate();
			if (row == 1)
				return 1;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.colseAll(conn, st, rs);
		}
		return 0;
	}

	/**
	 * 更新读着信息
	 */
	public int update(Reader reader) {
		String sql = "update reader set name = ?,sex = ?,phone = ?  where readerid = ?";//,state = 1
		int row = 0;
		try {
			conn = JDBCUtils.getConnection();
			st = conn.prepareStatement(sql);
			st.setString(1, reader.getName());
			st.setString(2, reader.getSex());
			st.setString(3, reader.getPhone());
			st.setString(4, reader.getReaderId());

			row = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.colseAll(conn, st, rs);
		}
		return row;
	}

	/**
	 * 根据读者编号查询读者
	 */
	public Reader findReaderById(String readerId) {
		Reader reader = null;
		String sql = "select readerid ,`name` from reader where readerid = ? ";
		try {
			conn = JDBCUtils.getConnection();
			st = conn.prepareStatement(sql);
			st.setString(1, readerId);
			rs = st.executeQuery();
			if (rs.next()) {
				reader = new Reader();
				reader.setReaderId(rs.getString(1));
				reader.setName(rs.getString(2));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.colseAll(conn, st, rs);
		}
		return reader;
	}
}
