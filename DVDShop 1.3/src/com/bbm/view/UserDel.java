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
		setTitle("删除用户");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		dataPanel = new JPanel();
		dataPanel.setBorder(new EmptyBorder(5, 10, 5, 10));
        jscrollPane = new JScrollPane();
        jscrollPane.setPreferredSize(new Dimension(400, 200));       
        
        readersearch = new String[]{ "用户编号","用户名","密码"};
        results=new String[][]{};
        UserDao userDao = new UserDao();
        results=userDao.getArrayData(userDao.findAll());
        jtable = new JTable(results, readersearch);
        jtable.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);        
        jscrollPane.setViewportView(jtable);
        
        dataPanel.add(jscrollPane);
        add(dataPanel,BorderLayout.CENTER);

		// 登录取消按钮面板设计
        buttonPanel = new JPanel();// 修改按钮面板
		updateJB = new JButton("删除");
		closeJB = new JButton("退出");
		buttonPanel.add(updateJB);
		buttonPanel.add(closeJB);

		this.add(dataPanel, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.SOUTH);
		updateJB.addActionListener(this);
		closeJB.addActionListener(this);
		this.setVisible(true);// 设置窗体显示，否则不显示。
		setResizable(false);// 取消最大化
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==updateJB){
			UserDao userDao = new UserDao();
			int row = jtable.getSelectedRow();
			if(row==-1){
				JOptionPane.showMessageDialog(this, "请先选中用户！！！");
			}else {
				int userId = Integer.valueOf(results[row][0]);
				int r = userDao.delete(userId);
				if(r!=0){
					results=userDao.getArrayData(userDao.findAll());
			        jtable = new JTable(results, readersearch);
			        jtable.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);        
			        jscrollPane.setViewportView(jtable);
					JOptionPane.showMessageDialog(this, "删除成功！");
				}else {
					JOptionPane.showMessageDialog(this, "删除失败！");
				}
			}
		}
		if(e.getSource()==closeJB){
			dispose();
		}
	}
}