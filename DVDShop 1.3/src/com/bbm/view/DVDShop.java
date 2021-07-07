package com.bbm.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class DVDShop extends JFrame {

	/**
	 * ͼ�����ϵͳ
	 */
	private JMenu menu1, menu2, menu3, menu4, menu5;//�˵�
	private JMenuItem menuitem11, menuitem12, menuitem21, menuitem22, menuitem31, menuitem32, menuitem41, menuitem42,
			menuitem43, menuitem51, menuitem52, menuitem53;//�Ӳ˵�

	public static void main(String[] args) {
		new DVDShop();
	}

	public DVDShop() {
		setTitle("���̽���ϵͳ");
		setBounds(200, 200, 500, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		JMenuBar menubar = new JMenuBar();
		setJMenuBar(menubar);

		menu1 = new JMenu("��������Ϣ����");
		menubar.add(menu1);
		menuitem11 = new JMenuItem("������Ϣ���");
		menu1.add(menuitem11);
		menuitem11.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ReaderAdd();
			}
		});
		menuitem12 = new JMenuItem("��������Ϣ��ѯ���޸�");
		menu1.add(menuitem12);
		menuitem12.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ReaderSelectAndModify();
			}
		});
		menu2 = new JMenu("������Ϣ����");
		menubar.add(menu2);
		menuitem21 = new JMenuItem("������Ϣ���");
		menu2.add(menuitem21);
		menuitem21.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new DVDAdd();
			}
		});
		menuitem22 = new JMenuItem("������Ϣ��ѯ���޸�");
		menu2.add(menuitem22);
		menuitem22.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 new DVDSelectAndUpdate();
			}
		});
		menu3 = new JMenu("���̽��Ĺ���");
		menubar.add(menu3);
		menuitem31 = new JMenuItem("����");
		menu3.add(menuitem31);
		menuitem31.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 new DVDBorrow();
			}
		});
		menuitem32 = new JMenuItem("�黹");
		menu3.add(menuitem32);
		menuitem32.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 new DVDReturn();
			}
		});

		menu4 = new JMenu("��������ά��");
		menubar.add(menu4);
		menuitem41 = new JMenuItem("�������͹���");
		menu4.add(menuitem41);
		menuitem41.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new DVDTypeManage();
			}
		});

		menu5 = new JMenu("����Ա");
		menubar.add(menu5);
		menuitem51 = new JMenuItem("��ӹ���Ա");
		menu5.add(menuitem51);
		menuitem51.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 new UserAdd();
			}
		});
		menuitem52 = new JMenuItem("�޸�����");
		menu5.add(menuitem52);
		menuitem52.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 new UpdatePWD();
			}
		});
		menuitem53 = new JMenuItem("ɾ������Ա");
		menu5.add(menuitem53);
		menuitem53.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 new UserDel();
			}
		});


		this.setVisible(true);// ������ʾ����
		setResizable(true);// ���
	}
}