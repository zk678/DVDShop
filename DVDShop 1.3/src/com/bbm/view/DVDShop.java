package com.bbm.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class DVDShop extends JFrame {

	/**
	 * 图书借阅系统
	 */
	private JMenu menu1, menu2, menu3, menu4, menu5;//菜单
	private JMenuItem menuitem11, menuitem12, menuitem21, menuitem22, menuitem31, menuitem32, menuitem41, menuitem42,
			menuitem43, menuitem51, menuitem52, menuitem53;//子菜单

	public static void main(String[] args) {
		new DVDShop();
	}

	public DVDShop() {
		setTitle("光盘借阅系统");
		setBounds(200, 200, 500, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		JMenuBar menubar = new JMenuBar();
		setJMenuBar(menubar);

		menu1 = new JMenu("借阅者信息管理");
		menubar.add(menu1);
		menuitem11 = new JMenuItem("借阅信息添加");
		menu1.add(menuitem11);
		menuitem11.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ReaderAdd();
			}
		});
		menuitem12 = new JMenuItem("借阅者信息查询与修改");
		menu1.add(menuitem12);
		menuitem12.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ReaderSelectAndModify();
			}
		});
		menu2 = new JMenu("光盘信息管理");
		menubar.add(menu2);
		menuitem21 = new JMenuItem("光盘信息添加");
		menu2.add(menuitem21);
		menuitem21.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new DVDAdd();
			}
		});
		menuitem22 = new JMenuItem("光盘信息查询与修改");
		menu2.add(menuitem22);
		menuitem22.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 new DVDSelectAndUpdate();
			}
		});
		menu3 = new JMenu("光盘借阅管理");
		menubar.add(menu3);
		menuitem31 = new JMenuItem("借阅");
		menu3.add(menuitem31);
		menuitem31.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 new DVDBorrow();
			}
		});
		menuitem32 = new JMenuItem("归还");
		menu3.add(menuitem32);
		menuitem32.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 new DVDReturn();
			}
		});

		menu4 = new JMenu("光盘类型维护");
		menubar.add(menu4);
		menuitem41 = new JMenuItem("光盘类型管理");
		menu4.add(menuitem41);
		menuitem41.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new DVDTypeManage();
			}
		});

		menu5 = new JMenu("管理员");
		menubar.add(menu5);
		menuitem51 = new JMenuItem("添加管理员");
		menu5.add(menuitem51);
		menuitem51.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 new UserAdd();
			}
		});
		menuitem52 = new JMenuItem("修改密码");
		menu5.add(menuitem52);
		menuitem52.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 new UpdatePWD();
			}
		});
		menuitem53 = new JMenuItem("删除管理员");
		menu5.add(menuitem53);
		menuitem53.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 new UserDel();
			}
		});


		this.setVisible(true);// 设置显示窗体
		setResizable(true);// 最大化
	}
}