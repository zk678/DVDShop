package com.bbm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bbm.entity.DVDType;
import com.bbm.utils.JDBCUtils;

public class DVDTypeDao {
	private Connection conn = null;
	private PreparedStatement st = null;
	private ResultSet rs = null;

	/**
	 * 查询所有书籍类型名
	 */
	public List<String> findNameAll() {
		List<String> list = new ArrayList<>();
		String sql = "select typename from dvdtype";
		try {
			conn = JDBCUtils.getConnection();
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			while (rs.next()) {
				list.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.colseAll(conn, st, rs);
		}
		return list;
	}

	/**
	 * 查询所有书本类型
	 */
	public List<DVDType> findAll() {
		List<DVDType> list = new ArrayList<>();
		DVDType DVDType = null;
		String sql = "select * from dvdtype";
		try {
			conn = JDBCUtils.getConnection();
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			while (rs.next()) {
				DVDType = new DVDType();
				DVDType.setId(rs.getInt(1));
				DVDType.setTypeName(rs.getString(2));
				list.add(DVDType);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.colseAll(conn, st, rs);
		}
		return list;
	}

	/**
	 * 将list集合转成数组
	 */
	public String[][] getArrayData(List<DVDType> DVDType) {
		String[][] data = new String[DVDType.size()][2];
		for (int i = 0; i < DVDType.size(); i++) {
			DVDType r = DVDType.get(i);
			data[i][0] = r.getId() + "";
			data[i][1] = r.getTypeName() + "";
			// System.out.println(data[i][0] + " " + data[i][1]);
		}
		return data;
	}

	// 插入书本类别
	public int save(DVDType DVDType) {
		String sql = "insert into dvdtype(typename) values(?)";
		try {
			conn = JDBCUtils.getConnection();
			st = conn.prepareStatement(sql);
			st.setString(1, DVDType.getTypeName());
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
	 * 根据类型名查询书籍类型
	 */
	public List<DVDType> search(String typeName) {
		List<DVDType> list = new ArrayList<>();
		DVDType DVDType = null;
		String sql = "select * from dvdtype where typename like ?";
		try {
			conn = JDBCUtils.getConnection();
			st = conn.prepareStatement(sql);
			typeName = "%" + typeName + "%";
			st.setString(1, typeName);
			rs = st.executeQuery();
			while (rs.next()) {
				DVDType = new DVDType();
				DVDType.setId(rs.getInt(1));
				DVDType.setTypeName(rs.getString(2));
				list.add(DVDType);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.colseAll(conn, st, rs);
		}
		return list;
	}

	/**
	 * 更新书籍类型名信息
	 */
	public int update(DVDType DVDType) {
		String sql = "update dvdtype set typename = ? where id = ?";
		try {
			conn = JDBCUtils.getConnection();
			st = conn.prepareStatement(sql);
			st.setString(1, DVDType.getTypeName());
			st.setInt(2, DVDType.getId());
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
	 * 删除书籍
	 */
	public int delete(DVDType DVDType) {
		String sql = "delete from dvdtype where id = ?";
		try {
			conn = JDBCUtils.getConnection();
			st = conn.prepareStatement(sql);
			st.setInt(1, DVDType.getId());
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
	 * 根据类型名查询ID
	 */
	public Integer findIdByName(String typename) {
		String sql = "select id from dvdtype where typename = ?";
		try {
			conn = JDBCUtils.getConnection();
			st = conn.prepareStatement(sql);
			st.setString(1, typename);
			rs = st.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.colseAll(conn, st, rs);
		}
		return 0;
	}

	/**
	 * 根据类型名查询ID
	 */
	public String findNameById(Integer typeId) {
		String sql = "select typename from dvdtype where id = ?";
		try {
			conn = JDBCUtils.getConnection();
			st = conn.prepareStatement(sql);
			st.setInt(1, typeId);
			rs = st.executeQuery();
			if (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.colseAll(conn, st, rs);
		}
		return "";
	}

}
