package com.hafele.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.hafele.model.Admin;
import com.hafele.util.DbHelper;

/**
* @author Dragon Wen E-mail:18475536452@163.com
* @version 创建时间：2017年9月20日 下午3:53:25
* AdminDao类
*/
public class AdminDao {

	//登录数据操作
	public static Admin login(Connection conn, Admin admin) throws Exception {
		Admin resultAdmin=null;
		String sql="select * from sys_admin where sys_name=? and sys_password=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, admin.getSys_name());
		pstmt.setString(2, admin.getSys_password());
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			
			resultAdmin=new Admin();
			resultAdmin.setSys_number(rs.getString("sys_number"));
			resultAdmin.setSys_name(rs.getString("sys_name"));
			resultAdmin.setSys_password(rs.getString("sys_password"));
		}
		return resultAdmin;
	}

	//更新密码操作
	public static int updatePassword(String sys_password, String sys_name) {
		int i = 0;
		String sql = "update sys_admin set sys_password='"+sys_password+"' where sys_name='"+sys_name+"'";
		Connection conn = null;
		try {
			conn = DbHelper.getConnection();
			i=conn.createStatement().executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			DbHelper.close(conn);
			JOptionPane.showMessageDialog(null, "异常：请检查数据是否有误！");
		}
		DbHelper.close(conn);
		return i;
	}

	//添加管理员信息
	public static int insertAdmin(String userNumber, String userName, String passwordMD5, String sex, String age, Date regdate, String telNumber,
			String idCard) {
		int i = 0;
		String sql = "insert into sys_admin(sys_number,sys_name,sys_password,sys_sex,sys_age,sys_regdate,sys_telNumber,sys_idCard) values ('"+userNumber+"','"+userName+"','"+passwordMD5+"','"+sex+"','"+age+"','"+regdate+"','"+telNumber+"','"+idCard+"')";
		Connection conn = null;
		try {
			conn = DbHelper.getConnection();
			System.out.println(sql);
			i = conn.createStatement().executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			DbHelper.close(conn);
			JOptionPane.showMessageDialog(null, "异常：请检查数据是否有误！");
		}
		DbHelper.close(conn);
		return i;
	}

	//从数据库中获取所有管理员信息
	public static List<Admin> selectuser() {
		List<Admin> list = new ArrayList<Admin>();
		String sql = "select * from sys_admin";
		Connection conn = null;
		try {
			conn = DbHelper.getConnection();
			ResultSet re = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(sql);
			while(re.next()) {
				Admin admin = new Admin();
				admin.setSys_number(re.getString("sys_number"));
				admin.setSys_name(re.getString("sys_name"));
				admin.setSys_sex(re.getString("sys_sex"));
				admin.setSys_age(re.getString("sys_age"));
				admin.setSys_regdate(re.getDate("sys_regdate"));
				admin.setSys_telNumber(re.getString("sys_telNumber"));
				admin.setSys_idCard(re.getString("sys_idCard"));
				admin.setSys_password(re.getString("sys_password"));
				list.add(admin);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			DbHelper.close(conn);
			JOptionPane.showMessageDialog(null, "异常：请检查数据是否有误！");
		}
		DbHelper.close(conn);
		return list;
	}

	//更新管理员信息
	public static int updateAdmin(String userNumber, String userName, String sex, String age, String idCard,
			Date regdate, String telNumber) {
		int i = 0;
		String sql = "update sys_admin set sys_name='"+userName+"',sys_sex='"+sex+"',sys_age='"+age+"',sys_idCard='"+idCard+"',sys_regdate='"+regdate+"',sys_telNumber='"+telNumber+"'where sys_number='"+userNumber+"'";
		System.out.println(sql);
		Connection conn = null;
		try {
			conn = DbHelper.getConnection();
			i = conn.createStatement().executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			DbHelper.close(conn);
			JOptionPane.showMessageDialog(null, "异常：请检查数据是否有误！");
		}
		DbHelper.close(conn);
		return i;
	}

	//删除管理员信息
	public static int deleteAdmin(String adminNumber) {
		int i = 0;
		String sql = "delete from sys_admin where sys_number='"+adminNumber+"'";
		Connection conn = null;
		try {
			conn = DbHelper.getConnection();
			i = conn.createStatement().executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			DbHelper.close(conn);
			JOptionPane.showMessageDialog(null, "异常：请检查数据是否有误！");
		}
		DbHelper.close(conn);
		return i;
	}
	
}
