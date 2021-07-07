package com.bbm.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import com.bbm.entity.User;
import com.bbm.dao.UserDao;
public class UserAdd extends JFrame implements ActionListener, FocusListener {
	private JLabel lab1, lab2, lab3;
	private JTextField txt1;
	private JPasswordField pwdJP1, pwdJP2;
	private JButton but1, but2;
	public UserAdd() {
		x();
		setTitle("����û�");
		setBounds(50, 50, 300, 250);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	public void x() {

		lab1 = new JLabel("�û�����");
		lab1.setBounds(50, 50, 53, 25);
		getContentPane().add(lab1);

		txt1 = new JTextField();
		txt1.setBounds(110, 50, 150, 25);
		getContentPane().add(txt1);

		lab2 = new JLabel("�� �룺");
		lab2.setBounds(50, 100, 50, 25);
		getContentPane().add(lab2);

		pwdJP1 = new JPasswordField();
		pwdJP1.setBounds(110, 100, 150, 25);
		getContentPane().add(pwdJP1);

		but1 = new JButton("���");
		but1.setBounds(80, 140, 60, 25);
		getContentPane().add(but1);

		but2 = new JButton("ȡ��");
		but2.setBounds(180, 140, 60, 25);
		getContentPane().add(but2);

		txt1.addFocusListener(this);
		but1.addActionListener(this);
		but2.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == but1) {
			UserDao userDao = new UserDao();
			User user = new User();
			user.setUserName(txt1.getText());
			user.setPassword(pwdJP1.getText());
			if ("".equals(user.getUserName()) || "".equals(user.getPassword())) {
				JOptionPane.showMessageDialog(this, "�û���������Ϊ�գ�����");
				return;
			} else {
				int row = userDao.save(user);
				if (row != 0) {
					txt1.setText(null);
					pwdJP1.setText(null);
					JOptionPane.showMessageDialog(this, "�û���ӳɹ�");
				} else {
					JOptionPane.showMessageDialog(this, "�û����ʧ�ܣ�����");
				}
			}
		}
		if (e.getSource() == but2) {
			txt1.setText(null);
			pwdJP1.setText(null);
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		UserDao userDao = new UserDao();
		User user = userDao.findUserByName(txt1.getText());
		if (user != null) {
			txt1.setText(null);
			JOptionPane.showMessageDialog(this, "���û��Ѵ��ڣ�");
		} 
	}

	@Override
	public void focusGained(FocusEvent e) {
	}

}