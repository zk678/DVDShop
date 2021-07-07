package com.bbm.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.bbm.entity.User;
import com.bbm.dao.UserDao;

public class UpdatePWD extends JFrame implements ActionListener, FocusListener {
	private JPanel update1;
	private JLabel lab1, lab2, lab3, lab4;
	private JTextField txt1;
	private JPasswordField pas1, pas2, pas3;
	private JButton but1, but2;

	public UpdatePWD() {
		setBounds(200, 200, 370, 240);
		setTitle("�޸�����");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		y();
		setVisible(true);
	}

	public void y() {
		JPanel jpanel1 = new JPanel();

		update1 = new JPanel();
		update1.setBorder(new EmptyBorder(5, 10, 5, 10));
		GridLayout gridLayout = new GridLayout(4, 2);
		gridLayout.setVgap(10);
		gridLayout.setHgap(10);
		update1.setLayout(gridLayout);

		lab1 = new JLabel("         �û�����");
		// lab1.setHorizontalAlignment(SwingConstants.NORTH);
		update1.add(lab1);

		txt1 = new JTextField();
		// txt1.setHorizontalAlignment(SwingConstants.NORTH);
		update1.add(txt1);

		lab2 = new JLabel("         ԭ���룺");
		// lab2.setHorizontalAlignment(SwingConstants.CENTER);
		update1.add(lab2);

		pas1 = new JPasswordField();
		update1.add(pas1);

		lab3 = new JLabel("         �����룺");
		// lab3.setHorizontalAlignment(SwingConstants.CENTER);
		update1.add(lab3);

		pas2 = new JPasswordField();
		update1.add(pas2);

		lab4 = new JLabel("     ȷ�������룺");
		update1.add(lab4);

		pas3 = new JPasswordField();
		update1.add(pas3);

		JPanel jpanel = new JPanel();

		but1 = new JButton("ȷ��");
		jpanel.add(but1);

		but2 = new JButton("ȡ��");
		jpanel.add(but2);

		add(update1, BorderLayout.CENTER);
		add(jpanel, BorderLayout.SOUTH);

		but1.addActionListener(this);
		but2.addActionListener(this);
		txt1.addFocusListener(this);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == but1) {
			String name = null, pwd1 = null, pwd2 = null, pwd3 = null;
			name = txt1.getText().intern();
			pwd1 = pas1.getText().intern();

			pwd2 = pas2.getText().intern();
			pwd3 = pas3.getText().intern();
			// String pwd =
			UserDao userDao = new UserDao();
			if (name == null || pwd1 == null || pwd2 == null || pwd3 == null || "".equals(name) || "".equals(pwd1)
					|| "".equals(pwd2) || "".equals(pwd3)) {
				JOptionPane.showMessageDialog(this, "������������Ϣ��");
				return;
			} else {
				if (!pwd2.equals(pwd3)) {
					pas1.setText(null);
					pas2.setText(null);
					pas3.setText(null);
					JOptionPane.showMessageDialog(this, "�������벻һ��");
					return;
				}
				User user = userDao.login(name, pwd1);
				if (user == null) {
					JOptionPane.showMessageDialog(this, "�û����������벻��ȷ");
					return;
				} else {
					int row = userDao.update(name, pwd2);
					if (row == 1) {
						pas1.setText(null);
						pas2.setText(null);
						pas3.setText(null);
						JOptionPane.showMessageDialog(this, "�޸ĳɹ���");
					} else {
						JOptionPane.showMessageDialog(this, "�޸�ʧ�ܣ�");
					}
				}

			}

		}
		if (e.getSource() == but2) {
			txt1.setText(null);
			pas1.setText(null);
			pas2.setText(null);
			pas3.setText(null);
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		
		String name = txt1.getText().intern();
		if("".equals(name)) {
			return;
		}
		UserDao userDao = new UserDao();
		User user  = userDao.findUserByName(name);
		if(user == null) {
			txt1.setText(null);
			JOptionPane.showMessageDialog(this, "���û�������");
		}				
	}

	@Override
	public void focusGained(FocusEvent e) {

	}

}