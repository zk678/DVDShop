package com.bbm.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import com.bbm.entity.DVDType;
import com.bbm.dao.DVDTypeDao;

public class DVDTypeManage extends JFrame implements MouseListener, ActionListener {
	private JTextField textField_1;
	private JTextField textField_2;
	private JScrollPane scrollPane_1;
	private JPanel panel, panel_1;
	private JLabel lblNewLabel_1;
	private JLabel label;
	private JButton button, button_1, button_3, button_4;
	private JTable j;
//	private String[][] data = new String[][] {};
private String[][] data;
	private String[] readersearch;

	public DVDTypeManage() {

		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.pink);

		panel = new JPanel();
		panel.setBounds(0, 0, 550, 400);
		getContentPane().add(panel);
		panel.setLayout(null);

		panel_1 = new JPanel();
		panel_1.setBounds(41, 60, 457, 153);
		panel_1.setLayout(null);
		panel.add(panel_1);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 457, 153);
		readersearch = new String[] { "类型编号", "类型名称" };
		DVDTypeDao DVDTypeDao = new DVDTypeDao();
		data = DVDTypeDao.getArrayData(DVDTypeDao.findAll());
		j = new JTable(data, readersearch);
		j.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		j.getColumnModel().getColumn(0).setPreferredWidth(229);
		j.getColumnModel().getColumn(1).setPreferredWidth(229);
		scrollPane_1.setViewportView(j);
		panel_1.add(scrollPane_1);
		j.addMouseListener(this);

		lblNewLabel_1 = new JLabel("类型编号：");
		lblNewLabel_1.setFont(new Font("宋体", Font.BOLD, 17));
		lblNewLabel_1.setBounds(51, 223, 147, 21);
		// lblNewLabel_1.setEnabled(false);//设置不可修改内容
		panel.add(lblNewLabel_1);

		label = new JLabel("类型名：");
		label.setFont(new Font("宋体", Font.BOLD, 17));
		label.setBounds(51, 272, 147, 21);
		panel.add(label);

		textField_1 = new JTextField();
		textField_1.setBounds(227, 223, 236, 21);
		panel.add(textField_1);
		textField_1.setColumns(10);
		textField_1.setEnabled(false);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(227, 274, 236, 21);
		panel.add(textField_2);

		button_3 = new JButton("添加");
		button_3.setFont(new Font("宋体", Font.BOLD, 17));
		button_3.setBounds(64, 326, 84, 21);
		panel.add(button_3);
		button_3.addActionListener(this);

		button_4 = new JButton("修改");
		button_4.setFont(new Font("宋体", Font.BOLD, 17));
		button_4.setBounds(162, 326, 84, 21);
		panel.add(button_4);
		button_4.addActionListener(this);

		button = new JButton("删除");
		button.setFont(new Font("宋体", Font.BOLD, 17));
		button.setBounds(266, 326, 84, 21);
		panel.add(button);
		button.addActionListener(this);

		button_1 = new JButton("退出");
		button_1.setFont(new Font("宋体", Font.BOLD, 17));
		button_1.setBounds(367, 326, 84, 21);
		panel.add(button_1);
		button_1.addActionListener(this);

		setTitle("类型管理");
		setBounds(100, 100, 566, 438);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);// 取消最大化
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// 添加类别
		if (e.getSource() == button_3) {

			DVDType DVDType = new DVDType();
			DVDType.setTypeName(textField_2.getText());
			if (DVDType.getTypeName() == null || "".equals(DVDType.getTypeName())) {
				JOptionPane.showMessageDialog(this, "类型名不能为空！");
			}
			DVDTypeDao DVDTypeDao = new DVDTypeDao();
			int row = DVDTypeDao.save(DVDType);
			if (row == 1) {
				data = DVDTypeDao.getArrayData(DVDTypeDao.findAll());
				j = new JTable(data, readersearch);
				j.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				j.getColumnModel().getColumn(0).setPreferredWidth(229);
				j.getColumnModel().getColumn(1).setPreferredWidth(229);
				scrollPane_1.setViewportView(j);
				panel_1.add(scrollPane_1);
				j.addMouseListener(this);
				JOptionPane.showMessageDialog(this, "添加成功！");
			} else {
				JOptionPane.showMessageDialog(this, "添加失败！");
			}
		}
		// 修改DVD类别
		if (e.getSource() == button_4) {
			DVDType DVDType = new DVDType();
			DVDType.setId(Integer.valueOf(textField_1.getText()));
			DVDType.setTypeName(textField_2.getText());
			DVDTypeDao DVDTypeDao = new DVDTypeDao();
			int row = DVDTypeDao.update(DVDType);
			if (row == 1) {
				data = DVDTypeDao.getArrayData(DVDTypeDao.findAll());
				j = new JTable(data, readersearch);
				j.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				j.getColumnModel().getColumn(0).setPreferredWidth(229);
				j.getColumnModel().getColumn(1).setPreferredWidth(229);
				scrollPane_1.setViewportView(j);
				panel_1.add(scrollPane_1);
				j.addMouseListener(this);
				JOptionPane.showMessageDialog(this, "修改成功！");
			} else {
				JOptionPane.showMessageDialog(this, "修改失败！");
			}
		}
		// 删除DVD类别
		if (e.getSource() == button) {
			DVDType DVDType = new DVDType();
			DVDType.setId(Integer.valueOf(textField_1.getText()));
			DVDTypeDao DVDTypeDao = new DVDTypeDao();
			int row = DVDTypeDao.delete(DVDType);
			if (row == 1) {
				data = DVDTypeDao.getArrayData(DVDTypeDao.findAll());
				j = new JTable(data, readersearch);
				j.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				j.getColumnModel().getColumn(0).setPreferredWidth(229);
				j.getColumnModel().getColumn(1).setPreferredWidth(229);
				scrollPane_1.setViewportView(j);
				panel_1.add(scrollPane_1);
				j.addMouseListener(this);
				textField_1.setText(null);
				textField_2.setText(null);
				JOptionPane.showMessageDialog(this, "删除成功！");
			} else {
				JOptionPane.showMessageDialog(this, "删除失败！");
			}
		}
		if(e.getSource() == button_1) {
			dispose();
		}

	}

	@Override
	// 鼠标点击事件
	public void mouseClicked(MouseEvent e) {

		if (e.getSource() == button_1) {
			dispose();
		}
		if (e.getSource() == j) {
			int row = j.getSelectedRow();
			textField_1.setText(data[row][0]);
			textField_2.setText(data[row][1]);
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}