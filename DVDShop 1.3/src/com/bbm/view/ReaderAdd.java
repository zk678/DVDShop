package com.bbm.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.bbm.entity.Reader;
import com.bbm.dao.ReaderDao;

public class ReaderAdd extends JFrame implements ActionListener, FocusListener {
	private static final long serialVersionUID = 1L;

	private JPanel readerAddJP, sexJP, buttonJP;
	private ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton JRB1, JRB2;
	private JLabel IDJL, readerNameJL, sexJL, phoneJLabel, regJLabel;
	private JTextField IDJTF, readerNameJTF, phoneJTF, regtimeJTF;
	private JButton addJB, closeJB;

	public ReaderAdd() {
		setBounds(200, 200, 400, 300);
		setTitle("借阅者信息添加");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		// 登录取消按钮面板
		buttonJP = new JPanel();

		// 信息添加面板设计
		readerAddJP = new JPanel();
		readerAddJP.setBorder(new EmptyBorder(5, 10, 5, 10));
		final GridLayout gridLayout = new GridLayout(5, 2);
		gridLayout.setVgap(10);
		gridLayout.setHgap(10);
		readerAddJP.setLayout(gridLayout);
		getContentPane().add(readerAddJP);

		IDJL = new JLabel("编号：");
		IDJL.setHorizontalAlignment(SwingConstants.CENTER);
		readerAddJP.add(IDJL);
		IDJTF = new JTextField();
		readerAddJP.add(IDJTF);

		readerNameJL = new JLabel("姓名：");
		readerNameJL.setHorizontalAlignment(SwingConstants.CENTER);
		readerAddJP.add(readerNameJL);
		readerNameJTF = new JTextField();
		readerAddJP.add(readerNameJTF);

		sexJL = new JLabel("性别：");
		sexJL.setHorizontalAlignment(SwingConstants.CENTER);
		readerAddJP.add(sexJL);
		sexJP = new JPanel();
		final FlowLayout flowLayout = new FlowLayout();
		flowLayout.setHgap(0);
		flowLayout.setVgap(0);
		sexJP.setLayout(flowLayout);
		readerAddJP.add(sexJP);

		JRB1 = new JRadioButton();
		sexJP.add(JRB1);
		JRB1.setSelected(true);
		buttonGroup.add(JRB1);
		JRB1.setText("男");

		JRB2 = new JRadioButton();
		sexJP.add(JRB2);
		buttonGroup.add(JRB2);
		JRB2.setText("女");

		phoneJLabel = new JLabel("电话：");
		phoneJLabel.setHorizontalAlignment(SwingConstants.CENTER);
		readerAddJP.add(phoneJLabel);
		phoneJTF = new JTextField();
		readerAddJP.add(phoneJTF);

		regJLabel = new JLabel("添加日期：");
		regJLabel.setHorizontalAlignment(SwingConstants.CENTER);
		readerAddJP.add(regJLabel);
		regtimeJTF = new JTextField();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String str = format.format(new java.util.Date());
		regtimeJTF.setText(str);
		regtimeJTF.setEditable(false);
		readerAddJP.add(regtimeJTF);

		// 登录取消按钮面板设计
		addJB = new JButton("添加");
		closeJB = new JButton("关闭");
		buttonJP.add(addJB);
		buttonJP.add(closeJB);

		addJB.addActionListener(this);
		closeJB.addActionListener(this);
		IDJTF.addFocusListener(this);

		this.add(readerAddJP, BorderLayout.CENTER);
		this.add(buttonJP, BorderLayout.SOUTH);
		this.setVisible(true);// 设置窗体显示，否则不显示。
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addJB) {
			Reader reader = new Reader();
			reader.setReaderId(IDJTF.getText());
			if (reader.getReaderId() == null || "".equals(reader.getReaderId())) {
				JOptionPane.showMessageDialog(this, "编号不能为空！");
				return;
			}

			reader.setName(readerNameJTF.getText());
			String sex = "";
			if (JRB1.isSelected()) {
				sex = JRB1.getText();
			} else {
				sex = JRB2.getText();
			}
			reader.setSex(sex);
			reader.setPhone(phoneJTF.getText());
			ReaderDao readerDao = new ReaderDao();
			Reader reader1 = readerDao.findReaderById(reader.getReaderId());
			if (reader1 != null) {
				JOptionPane.showMessageDialog(this, "编号已经存在,如未查到可能管理员已经用!");
				return;
			}
			int row = readerDao.addReader(reader);
			if (row == 1) {
				dispose();
				JOptionPane.showMessageDialog(this, "添加成功！");
			} else {
				IDJTF.setText(null);
				readerNameJTF.setText(null);
				phoneJTF.setText(null);
				JOptionPane.showMessageDialog(this, "添加失败！");
			}
		}
		if (e.getSource() == closeJB) {
			dispose();
		}
	}

	@Override
	public void focusGained(FocusEvent e) {

	}

	@Override
	// 判定读着编号是否可用
	public void focusLost(FocusEvent e) {
		//System.out.println("---------------");
		if (e.getSource() == IDJTF) {
			if ("".equals(IDJTF.getText()))
				return;
			String readerId = IDJTF.getText();
			if (readerId != null) {
				ReaderDao readerDao = new ReaderDao();
				int row = readerDao.findByReaderId(readerId);
				if (row == 1)
					JOptionPane.showMessageDialog(this, "该编号已被占用！");
			}
			if (readerId.length() > 8) {
				IDJTF.setText(null);
				JOptionPane.showMessageDialog(this, "编号的长度小于等于8位！");
			}
		}
	}
}