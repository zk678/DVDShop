package com.bbm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bbm.entity.DVD;
import com.bbm.utils.JDBCUtils;

public class DVDDao {
	private Connection conn = null;
	private PreparedStatement st = null;
	private ResultSet rs = null;

	/**
	 * 保存书籍信息
	 * */
	public int save(DVD DVD) {
		String sql = "insert into dvd values(?,?,?,?,?,1);";
		try {
			conn = JDBCUtils.getConnection();
			st = conn.prepareStatement(sql);
			st.setString(1, DVD.getDVDID());
			st.setInt(2, DVD.getTypeId());
			st.setString(3, DVD.getDvdName());
			st.setString(4, DVD.getAuthor());
			st.setString(5, DVD.getPublish());
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
	 * 查询所有图书
	 * */
	public List<DVD> findAll() {
		List<DVD> list = new ArrayList<DVD>();
		DVD DVD = null;
		String sql = "select * from dvd where state = 1";
		try {
			conn = JDBCUtils.getConnection();
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			while (rs.next()) {
				DVD = new DVD();
				DVD.setDVDID(rs.getString(1));
				DVD.setTypeId(rs.getInt(2));
				DVD.setDvdName(rs.getString(3));
				DVD.setAuthor(rs.getString(4));
				DVD.setPublish(rs.getString(5));
				list.add(DVD);
			}
			// System.out.println("--------");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.colseAll(conn, st, rs);
		}
		return list;
	}

	/**
	 * 将书籍 lists 转成 二维数组
	 * */
	public String[][] getArrayData(List<DVD> lists) {
		String[][] data = new String[lists.size()][5];
		DVDTypeDao DVDTypeDao = new DVDTypeDao();
		for (int i = 0; i < lists.size(); i++) {
			DVD DVD = lists.get(i);
			data[i][0] = DVD.getDVDID() + "";
			data[i][1] = DVDTypeDao.findNameById(DVD.getTypeId()) + "";
			data[i][2] = DVD.getDvdName() + "";
			data[i][3] = DVD.getAuthor() + "";
			data[i][4] = DVD.getPublish() + "";
		}
		return data;
	}

	/**
	 * 根据ISBN查询书籍
	 * */
	public DVD findDVDById(String dvdid) {
		DVD DVD = null;
		String sql = "select * from dvd where dvdid = ? ";
		try {
			conn = JDBCUtils.getConnection();
			st = conn.prepareStatement(sql);
			st.setString(1, dvdid);
			rs = st.executeQuery();
			if (rs.next()) {
				DVD = new DVD();
				DVD.setDVDID(rs.getString(1));
				DVD.setTypeId(rs.getInt(2));
				DVD.setDvdName(rs.getString(3));
				DVD.setAuthor(rs.getString(4));
				DVD.setPublish(rs.getString(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return DVD;
	}

	/**
	 * 更新根据ISBN书籍信息
	 */
	public int update(DVD DVD) {

		String sql = "update dvd set typeid = ?,dvdname = ?,author=?,publish = ?,state = 1 where dvdid = ? ";
		try {
			conn = JDBCUtils.getConnection();
			st = conn.prepareStatement(sql);
			st.setString(5, DVD.getDVDID());
			st.setInt(1, DVD.getTypeId());
			st.setString(2, DVD.getDvdName());
			st.setString(3, DVD.getAuthor());
			st.setString(4, DVD.getPublish());

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
	 * 根据ISBN删除书籍
	 */
	public int delete(String dvdid) {
		String sql = "update dvd set state = 0 where dvdid = ?";
		try {
			conn = JDBCUtils.getConnection();
			st = conn.prepareStatement(sql);
			st.setString(1, dvdid);
			int row = st.executeUpdate();
			if (row != 0)
				return 1;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.colseAll(conn, st, rs);
		}
		return 0;
	}

}
