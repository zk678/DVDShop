package com.bbm.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.bbm.dao.DVDDao;
import com.bbm.entity.DVD;
import com.bbm.dao.DVDTypeDao;

/*
 * 图书信息添加
 * */
public class DVDAdd extends JFrame implements ActionListener, FocusListener {
	private static final long serialVersionUID = 1L;

	private JPanel bookAddJP, buttonJP;
	private ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel DVDIDJL, lbieJL, bknJL, wrtnJL, cbsnJL;
	private JTextField DVDIDJT,bknJT, wrtnJT, cbsnJT;
	private JComboBox lbieJCB;
	private JButton addJB, closeJB;

	public DVDAdd() {
		setBounds(200, 200, 300, 300);
		setTitle("光盘信息添加");
		// 登录取消按钮面板
		buttonJP = new JPanel();

		// 读者信息添加面板设计
		bookAddJP = new JPanel();
		bookAddJP.setBorder(new EmptyBorder(5, 10, 5, 10));
		final GridLayout gridLayout = new GridLayout(6, 2);
		gridLayout.setVgap(10);
		gridLayout.setHgap(10);
		bookAddJP.setLayout(gridLayout);
		getContentPane().add(bookAddJP);

		DVDIDJL = new JLabel("光盘ID：");
		DVDIDJL.setHorizontalAlignment(SwingConstants.CENTER);
		bookAddJP.add(DVDIDJL);
		DVDIDJT = new JTextField();
		bookAddJP.add(DVDIDJT);

		lbieJL = new JLabel("类别：");
		lbieJL.setHorizontalAlignment(SwingConstants.CENTER);
		bookAddJP.add(lbieJL);
		// 下拉列表
		lbieJCB = new JComboBox();
		bookAddJP.add(lbieJCB);
		List<String> data = new ArrayList<String>();
		DVDTypeDao DVDTypeDao = new DVDTypeDao();
		data = DVDTypeDao.findNameAll();
		Iterator<String> it = data.iterator();
		while (it.hasNext()) {
			lbieJCB.addItem(it.next());
		}

		bknJL = new JLabel("片名：");
		bknJL.setHorizontalAlignment(SwingConstants.CENTER);
		bookAddJP.add(bknJL);
		bknJT = new JTextField();
		bookAddJP.add(bknJT);

		wrtnJL = new JLabel("导演：");
		wrtnJL.setHorizontalAlignment(SwingConstants.CENTER);
		bookAddJP.add(wrtnJL);
		wrtnJT = new JTextField();
		bookAddJP.add(wrtnJT);

		cbsnJL = new JLabel("制片商：");
		cbsnJL.setHorizontalAlignment(SwingConstants.CENTER);
		bookAddJP.add(cbsnJL);
		cbsnJT = new JTextField();
		bookAddJP.add(cbsnJT);

		// 登录取消按钮面板设计
		addJB = new JButton("添加");
		closeJB = new JButton("关闭");
		buttonJP.add(addJB);
		buttonJP.add(closeJB);

		addJB.addActionListener(this);
		DVDIDJT.addFocusListener(this);
		DVDIDJT.addActionListener(this);
		closeJB.addActionListener(this);
		this.add(bookAddJP, BorderLayout.CENTER);
		this.add(buttonJP, BorderLayout.SOUTH);

		setVisible(true);// 设置窗体显示，否则不显示。
	}

	@Override
	public void focusGained(FocusEvent e) {

	}

	@Override
	public void focusLost(FocusEvent e) {
		if("".equals(DVDIDJT.getText()))return ;
		DVDDao DVDDao = new DVDDao();
		DVD DVD1 = DVDDao.findDVDById(DVDIDJT.getText());
		if(DVD1 !=null) {
			DVDIDJT.setText(null);
			JOptionPane.showMessageDialog(this, "该光盘已经存在,如未有查询到，请在光盘信息修改处修改光盘信息！");
			return;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addJB) {
			DVD DVD = new DVD();
			DVD.setDVDID(DVDIDJT.getText());
			if("".equals(DVD.getDVDID())) {
				JOptionPane.showMessageDialog(this, "ID不能为空");
				return ;
			}
			String typename = (String) lbieJCB.getSelectedItem();
			DVDTypeDao DVDTypeDao = new DVDTypeDao();
			DVD.setTypeId(DVDTypeDao.findIdByName(typename));
			DVD.setDvdName(bknJT.getText());
			DVD.setAuthor(wrtnJT.getText());
			DVD.setPublish(cbsnJT.getText());

			DVDDao DVDDao = new DVDDao();

			int r = DVDDao.save(DVD);
			if (r == 1) {
				JOptionPane.showMessageDialog(this, "添加成功");
			} else {
				JOptionPane.showMessageDialog(this, "添加失败");
			}
		}
		if (e.getSource() == DVDIDJT) {
			JOptionPane.showMessageDialog(this, "输入完成！");
		}
		if (e.getSource() == closeJB) {
			dispose();
		}
	}
}
