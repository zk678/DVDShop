package com.bbm.view;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import com.bbm.dao.UserDao;


public class UserDel extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;

	private JPanel dataPanel ,buttonPanel;
	
	private JScrollPane jscrollPane;

	private JTable jtable;
	private JButton selectJB, updateJB, closeJB;
	private String[] readersearch ;
	private String[][] results;
	public UserDel() {
		setBounds(200, 200, 500, 300);
		setTitle("ɾ���û�");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		dataPanel = new JPanel();
		dataPanel.setBorder(new EmptyBorder(5, 10, 5, 10));
        jscrollPane = new JScrollPane();
        jscrollPane.setPreferredSize(new Dimension(400, 200));       
        
        readersearch = new String[]{ "�û����","�û���","����"};
        results=new String[][]{};
        UserDao userDao = new UserDao();
        results=userDao.getArrayData(userDao.findAll());
        jtable = new JTable(results, readersearch);
        jtable.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);        
        jscrollPane.setViewportView(jtable);
        
        dataPanel.add(jscrollPane);
        add(dataPanel,BorderLayout.CENTER);

		// ��¼ȡ����ť������
        buttonPanel = new JPanel();// �޸İ�ť���
		updateJB = new JButton("ɾ��");
		closeJB = new JButton("�˳�");
		buttonPanel.add(updateJB);
		buttonPanel.add(closeJB);

		this.add(dataPanel, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.SOUTH);
		updateJB.addActionListener(this);
		closeJB.addActionListener(this);
		this.setVisible(true);// ���ô�����ʾ��������ʾ��
		setResizable(false);// ȡ�����
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==updateJB){
			UserDao userDao = new UserDao();
			int row = jtable.getSelectedRow();
			if(row==-1){
				JOptionPane.showMessageDialog(this, "����ѡ���û�������");
			}else {
				int userId = Integer.valueOf(results[row][0]);
				int r = userDao.delete(userId);
				if(r!=0){
					results=userDao.getArrayData(userDao.findAll());
			        jtable = new JTable(results, readersearch);
			        jtable.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);        
			        jscrollPane.setViewportView(jtable);
					JOptionPane.showMessageDialog(this, "ɾ���ɹ���");
				}else {
					JOptionPane.showMessageDialog(this, "ɾ��ʧ�ܣ�");
				}
			}
		}
		if(e.getSource()==closeJB){
			dispose();
		}
	}
}