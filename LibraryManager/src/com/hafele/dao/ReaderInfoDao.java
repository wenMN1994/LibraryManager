package com.hafele.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.hafele.model.ReadersInfo;
import com.hafele.util.DbHelper;

/**
* @author Dragon Wen E-mail:18475536452@163.com
* @version 创建时间：2017年9月23日 下午3:07:20
* ReaderInfoDao类，对读者信息表执行相关操作
*/
public class ReaderInfoDao {
	//添加读者信息操作
	public static int insertReader(String readerName, String readerSex, String readerAge, String readerOccupation, int validDocumentation, String idNumber,
			String telNumber, BigDecimal deposit, Date registrationDate, String readerNum) {
		int i = 0;
		Connection conn = null;
		try{
			conn = DbHelper.getConnection();
			String sql="insert into system_readers(reader_name,reader_sex,reader_age,reader_occupation,id_type,id_number,tel_number,guarantee_deposit,regdate,readerid) values"
					+ "('"+readerName+"','"+readerSex+"','"+readerAge+"','"+readerOccupation+"','"+validDocumentation+"','"+idNumber+"','"+telNumber+"',"+deposit+",'"+registrationDate+"','"+readerNum+"')";
			System.out.println(sql);
			i=conn.createStatement().executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
			DbHelper.close(conn);
			JOptionPane.showMessageDialog(null, "异常：请检查数据是否有误！");
		}
		DbHelper.close(conn);
		return i;
	}

	//获取全部读者信息
	public static List<ReadersInfo> selectReader() {
		List<ReadersInfo> list = new ArrayList<ReadersInfo>();
		Connection conn = null;
		try {
			conn = DbHelper.getConnection();
			String sql = "select * from system_readers";
			ResultSet re = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(sql);
			while(re.next()) {
				ReadersInfo readersInfo = new ReadersInfo();
				readersInfo.setReaderName(re.getString("reader_name"));
				readersInfo.setReaderSex(re.getString("reader_sex"));
				readersInfo.setReaderAge(re.getString("reader_age"));
				readersInfo.setReaderOccupation(re.getString("reader_occupation"));
				readersInfo.setReaderIdType(re.getInt("id_type"));
				readersInfo.setReaderIdNumber(re.getString("id_number"));
				readersInfo.setReaderTelNumber(re.getString("tel_number"));
				readersInfo.setGuaranteeDeposit(re.getDouble("guarantee_deposit"));
				readersInfo.setRegdate(re.getDate("regdate"));
				readersInfo.setReaderId(re.getString("readerid"));
				list.add(readersInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			DbHelper.close(conn);
			JOptionPane.showMessageDialog(null, "异常：请检查数据是否有误！");
		}
		DbHelper.close(conn);
		return list;
	}

	//删除读者信息
	public static int deleteReader(String readerNumber) {
		int i = 0;
		Connection conn = null;
		try {
			conn = DbHelper.getConnection();
			String sql="delete from system_readers where readerid='"+readerNumber+"'";
			i = conn.createStatement().executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			DbHelper.close(conn);
			JOptionPane.showMessageDialog(null, "异常：请检查数据是否有误！");
		}
		DbHelper.close(conn);
		return i;
	}

	//更新读者数据
	public static int updateReader(String readerName, String readerSex, String readerAge, String readerOccupation, int validDocumentation,
			String idNumber, String telNumber, BigDecimal deposit, Date registrationDate, String readerNum) {
		int i = 0;
		Connection conn = null;
		try {
			conn = DbHelper.getConnection();
			String sql = "update system_readers set reader_name='"+readerName+"',reader_sex='"+readerSex+"',reader_age='"+readerAge+"',reader_occupation='"+readerOccupation+"',id_type='"+validDocumentation+"',id_number='"+idNumber+"',tel_number='"+telNumber+"',guarantee_deposit='"+deposit+"',regdate='"+registrationDate+"'where readerid='"+readerNum+"'";
			i = conn.createStatement().executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			DbHelper.close(conn);
			JOptionPane.showMessageDialog(null, "异常：请检查数据是否有误！");
		}
		DbHelper.close(conn);
		return i;
	}

	//根据读者ID获取读者信息
	public static List<ReadersInfo> selectReader(String readerId) {
		List<ReadersInfo> list = new ArrayList<ReadersInfo>();
		String sql = "select * from system_readers where readerid='"+readerId+"'";
		System.out.println(sql);
		Connection conn = null;
		try {
			conn = DbHelper.getConnection();
			ResultSet re = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(sql);
			while(re.next()) {
				ReadersInfo readersInfo = new ReadersInfo();
				readersInfo.setReaderName(re.getString("reader_name"));
				readersInfo.setReaderSex(re.getString("reader_sex"));
				readersInfo.setReaderAge(re.getString("reader_age"));
				readersInfo.setReaderOccupation(re.getString("reader_occupation"));
				readersInfo.setReaderIdType(re.getInt("id_type"));
				readersInfo.setReaderIdNumber(re.getString("id_number"));
				readersInfo.setReaderTelNumber(re.getString("tel_number"));
				readersInfo.setGuaranteeDeposit(re.getDouble("guarantee_deposit"));
				readersInfo.setRegdate(re.getDate("regdate"));
				readersInfo.setReaderId(re.getString("readerid"));
				list.add(readersInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			DbHelper.close(conn);
			JOptionPane.showMessageDialog(null, "异常：请检查数据是否有误！");
		}
		DbHelper.close(conn);
		return list;
	}
	
}
