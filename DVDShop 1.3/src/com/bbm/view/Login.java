package com.bbm.view;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.bbm.entity.User;
import com.bbm.dao.UserDao;

public class Login extends JFrame implements ActionListener {
	JPanel jpanel,jpanel2,jp;
	JPasswordField jpasswordfield;
	JButton jbutton ,jbutton1;
	JTextField jtextfield;
	public static String USER_NAME;
	
	public Login() {
		setBounds(200, 200, 420, 200);
		setTitle("���̽��Ĺ���ϵͳ��¼����");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		jpanel = new JPanel();
		JLabel jlabel = new JLabel("���̽��Ĺ���ϵͳ");
		Font font = new Font("����", Font.BOLD, 30);
		jlabel.setFont(font);
		jlabel.setHorizontalAlignment(SwingConstants.CENTER);
		jpanel.setLayout(new FlowLayout());
		jpanel.add(jlabel);

		jpanel2 = new JPanel();
		jpanel2.setBorder(new EmptyBorder(5, 10, 5, 10));
		GridLayout gridLayout = new GridLayout(2, 2);
		gridLayout.setVgap(10);
		gridLayout.setHgap(10);
		jpanel2.setLayout(gridLayout);

		JLabel jlabel1 = new JLabel("�û��� ��");
		jlabel1.setHorizontalAlignment(SwingConstants.CENTER);
		jtextfield = new JTextField();
		jpanel2.add(jlabel1);
		jpanel2.add(jtextfield);

		JLabel jlabel2 = new JLabel("���� : ");
		jlabel2.setHorizontalAlignment(SwingConstants.CENTER);
		jpasswordfield = new JPasswordField();

		jpanel2.add(jlabel2);
		jpanel2.add(jpasswordfield);

		jp = new JPanel();

		jbutton = new JButton("��¼");
		jbutton1 = new JButton("����");
		jbutton.addActionListener(this);
		jbutton1.addActionListener(this);
		jp.add(jbutton);
		jp.add(jbutton1);

		add(jpanel, BorderLayout.NORTH);
		add(jpanel2, BorderLayout.CENTER);
		add(jp, BorderLayout.SOUTH);

		this.setVisible(true);// ���ô�����ʾ��������ʾ��
		setResizable(false);// ȡ�����

	}
				
	public static void main(String[] args) {
		new Login();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==jbutton) {
			String name =null,pwd=null;
			name=jtextfield.getText();
			pwd=jpasswordfield.getText();
			UserDao userDao = new UserDao();
			User user = userDao.login(name, pwd);
			if(user==null) {
				jtextfield.setText(null);
				jpasswordfield.setText(null);
				JOptionPane.showMessageDialog(this, "�û������������");
			}else {
				new DVDShop();
				dispose();
			}
		}
		if(e.getSource()==jbutton1){
			jtextfield.setText(null);
			jpasswordfield.setText(null);
		}
	}
}