package com.hafele;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import com.hafele.iframe.AboutUsIFrame;
import com.hafele.iframe.BookBackIFrame;
import com.hafele.iframe.BookBorrowIFrame;
import com.hafele.iframe.BookInfoAddIFrame;
import com.hafele.iframe.BookInfoModiAndDelIFrame;
import com.hafele.iframe.BookSearchIFrame;
import com.hafele.iframe.BookTypeAddIFrame;
import com.hafele.iframe.BookTypeModifyFrame;
import com.hafele.iframe.ChangePasswordIFrame;
import com.hafele.iframe.ReaderAddIFrame;
import com.hafele.iframe.ReaderModiAndDelIFrame;
import com.hafele.iframe.UserAddIFrame;
import com.hafele.iframe.UserModiAndDelIFrame;

/**
* @author Dragon Wen E-mail:18475536452@163.com
* @version 创建时间：2017年9月21日 下午5:25:24
* 菜单和按钮的Action对象
*/
public class MenuActions {

	private static Map<String, JInternalFrame> frames; // 子窗体集合
	
	public static PasswordModiAction MODIFY_PASSWORD;// 修改密码窗体动作
	public static ReaderAddAction READER_ADD; // 读者信息添加窗体动作
	public static ReaderModiAction READER_MODIFY; // 读者信息修改窗体动作
	public static BookTypeAddAction BOOKSTYLE_ADD;//图书类型添加窗体动作
	public static BookTypeModiAction BOOKTYPE_MODIFY; // 图书类型修改窗体动作
	public static BookInfoAddAction BOOK_ADD; // 图书信息添加窗体动作
	public static BookModiAction BOOK_MODIFY; // 图书信息修改窗体动作
	public static BorrowAction BORROW; // 图书借阅窗体动作
	public static GiveBackAction GIVE_BACK; // 图书归还窗体动作
	public static BookSearchAction BOOK_SEARCH; // 图书搜索窗体动作
	public static UserAddAction USER_ADD;//管理员信息添加
	public static UserModiAndDelAction USER_MODIFY;//管理员信息修改或删除
	public static AboutUsAction ABOUT_US;//关于我们
	public static ExitAction EXIT; // 系统退出动作
	
	static {
		frames = new HashMap<String, JInternalFrame>();
		MODIFY_PASSWORD = new PasswordModiAction();
		READER_ADD = new ReaderAddAction();
		READER_MODIFY = new ReaderModiAction();
		BOOKSTYLE_ADD = new BookTypeAddAction();
		BOOKTYPE_MODIFY = new BookTypeModiAction();
		BOOK_ADD = new BookInfoAddAction();
		BOOK_MODIFY = new BookModiAction();
		BORROW = new BorrowAction();
		GIVE_BACK = new GiveBackAction();
		BOOK_SEARCH = new BookSearchAction();
		USER_ADD = new UserAddAction();
		USER_MODIFY = new UserModiAndDelAction();
		ABOUT_US = new AboutUsAction();
		EXIT = new ExitAction();
	}
	
	@SuppressWarnings("serial")
	private static class AboutUsAction extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (!frames.containsKey("关于我们")||frames.get("关于我们").isClosed()) {
				AboutUsIFrame iframe = new AboutUsIFrame();
				frames.put("关于我们", iframe);
				LibraryMain.addIFame(iframe);
			}
		}	
	}
	
	@SuppressWarnings("serial")
	private static class BookSearchAction extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("图书查询")||frames.get("图书查询").isClosed()) {
				BookSearchIFrame iframe = new BookSearchIFrame();
				frames.put("图书查询", iframe);
				LibraryMain.addIFame(iframe);
			}
		}
	}
	
	@SuppressWarnings("serial")
	private static class GiveBackAction extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (!frames.containsKey("图书归还管理")||frames.get("图书归还管理").isClosed()) {
				BookBackIFrame iframe = new BookBackIFrame();
				frames.put("图书归还管理", iframe);
				LibraryMain.addIFame(iframe);
			}
		}
	}
	
	@SuppressWarnings("serial")
	private static class BorrowAction extends AbstractAction{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("图书借阅管理")||frames.get("图书借阅管理").isClosed()) {
				BookBorrowIFrame iframe = new BookBorrowIFrame();
				frames.put("图书借阅管理", iframe);
				LibraryMain.addIFame(iframe);
			}
		}
	}
	
	@SuppressWarnings("serial")
	private static class BookModiAction extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("图书信息修改与删除")||frames.get("图书信息修改与删除").isClosed()) {
				BookInfoModiAndDelIFrame iframe = new BookInfoModiAndDelIFrame();
				frames.put("图书信息修改与删除", iframe);
				LibraryMain.addIFame(iframe);
			}
		}
	}
	
	@SuppressWarnings("serial")
	private static class BookInfoAddAction extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("图书信息添加")||frames.get("图书信息添加").isClosed()) {
				BookInfoAddIFrame iframe = new BookInfoAddIFrame();
				frames.put("图书信息添加", iframe);
				LibraryMain.addIFame(frames.get("图书信息添加"));
			}
		}
	}
	
	@SuppressWarnings("serial")
	private static class BookTypeModiAction extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("图书类型修改与删除")||frames.get("图书类型修改与删除").isClosed()) {
				BookTypeModifyFrame iframe=new BookTypeModifyFrame();
				frames.put("图书类型修改与删除", iframe);
				LibraryMain.addIFame(frames.get("图书类型修改与删除"));
			}
		}
	}
	
	@SuppressWarnings("serial")
	private static class BookTypeAddAction extends AbstractAction{

		@Override
		public void actionPerformed(final ActionEvent e) {
			if (!frames.containsKey("图书类别添加")||frames.get("图书类别添加").isClosed()) {
				BookTypeAddIFrame iframe=new BookTypeAddIFrame();
				frames.put("图书类别添加", iframe);
				LibraryMain.addIFame(frames.get("图书类别添加"));
			}
		}
	}
	
	@SuppressWarnings("serial")
	private static class PasswordModiAction extends AbstractAction {
		
		@Override
		public void actionPerformed(final ActionEvent e) {
			if (!frames.containsKey("修改密码")||frames.get("修改密码").isClosed()) {
				ChangePasswordIFrame iframe=new ChangePasswordIFrame();
				frames.put("修改密码", iframe);
				LibraryMain.addIFame(frames.get("修改密码"));
			}
		}
	}
	
	@SuppressWarnings("serial")
	private static class UserAddAction extends AbstractAction{

		@Override
		public void actionPerformed(final ActionEvent e) {
			if (!frames.containsKey("添加管理员")||frames.get("添加管理员").isClosed()) {
				UserAddIFrame iframe=new UserAddIFrame();
				frames.put("添加管理员", iframe);
				LibraryMain.addIFame(frames.get("添加管理员"));
			}
		}
		
	}
	
	@SuppressWarnings("serial")
	private static class UserModiAndDelAction extends AbstractAction{

		@Override
		public void actionPerformed(final ActionEvent e) {
			if (!frames.containsKey("管理员信息修改与删除")||frames.get("管理员信息修改与删除").isClosed()) {
				UserModiAndDelIFrame iframe=new UserModiAndDelIFrame();
				frames.put("管理员信息修改与删除", iframe);
				LibraryMain.addIFame(frames.get("管理员信息修改与删除"));
			}
		}
		
	}
	@SuppressWarnings("serial")
	private static class ReaderAddAction extends AbstractAction{
		public void actionPerformed(final ActionEvent e) {
			if (!frames.containsKey("读者信息添加")||frames.get("读者信息添加").isClosed()) {
				ReaderAddIFrame iframe=new ReaderAddIFrame();
				frames.put("读者信息添加", iframe);
				LibraryMain.addIFame(frames.get("读者信息添加"));
			}
		}
	}
	
	@SuppressWarnings("serial")
	private static class ReaderModiAction extends AbstractAction{
		public void actionPerformed(final ActionEvent e) {
			if (!frames.containsKey("读者信息修改与删除")||frames.get("读者信息修改与删除").isClosed()) {
				ReaderModiAndDelIFrame iframe=new ReaderModiAndDelIFrame();
				frames.put("读者信息修改与删除", iframe);
				LibraryMain.addIFame(frames.get("读者信息修改与删除"));
			}
		}
		
	}
	
	@SuppressWarnings("serial")
	private static class ExitAction extends AbstractAction{
		public void actionPerformed(final ActionEvent e) {
			int result=JOptionPane.showConfirmDialog(null, "是否退出系统");
			if(result==0){
				System.exit(0);
			}
		}
	}
	private MenuActions() {
		super();
	}
}
