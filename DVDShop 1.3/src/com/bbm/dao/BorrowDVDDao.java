package com.bbm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bbm.entity.BorrowDVD;
import com.bbm.utils.JDBCUtils;
;

public class BorrowDVDDao {
	private Connection conn = null;
	private PreparedStatement st = null;
	private ResultSet rs = null;

	/**
	 * 根据读者id查询借书信息
	 */
	public List<BorrowDVD> findBorrowByReaderId(String readerId) {
		List<BorrowDVD> lists = new ArrayList<BorrowDVD>();
		BorrowDVD borrowDVD = null;
		String sql = "select DVDID,borrowdate from borrowdvd where readerid = ? and returndate is null ";
		try {
			conn = JDBCUtils.getConnection();
			st = conn.prepareStatement(sql);
			st.setString(1, readerId);
			rs = st.executeQuery();
			while (rs.next()) {
				borrowDVD = new BorrowDVD();
				borrowDVD.setDVDID(rs.getString(1));
				borrowDVD.setBorrowDate(rs.getString(2));
				lists.add(borrowDVD);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.colseAll(conn, st, rs);
		}
		return lists;
	}

	/**
	 * 将 lists 转为二维数组
	 */
	public String[][] getArrayData(List<BorrowDVD> lists) {
		String[][] data = new String[lists.size()][3];
		DVDDao DVDDao = new DVDDao();
		for (int i = 0; i < lists.size(); i++) {
			BorrowDVD borrowDVD = lists.get(i);
			data[i][0] = borrowDVD.getDVDID();
			data[i][1] = DVDDao.findDVDById(borrowDVD.getDVDID()).getDvdName();
			data[i][2] = borrowDVD.getBorrowDate();
		}
		return data;
	}

	/**
	 * 保存借阅信息
	 */
	public int save(String readerid, String iSBN) {
		String sql = "insert into borrowdvd(readerid,DVDID,borrowdate) values(?,?,now())";
		try {
			conn = JDBCUtils.getConnection();
			st = conn.prepareStatement(sql);
			st.setString(1, readerid);
			st.setString(2, iSBN);
			int row = st.executeUpdate();
			if (row !=0 )
				return 1;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.colseAll(conn, st, rs);
		}

		return 0;
	}
	/**
	 * 更新借阅记录的归还时间
	 * */
	public int update(String readerid, String iSBN) {
		String sql = "update borrowdvd set  returndate = now() where readerid = ? and DVDID = ?";
		try {
			conn = JDBCUtils.getConnection();
			st = conn.prepareStatement(sql);
			st.setString(1, readerid);
			st.setString(2, iSBN);
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
