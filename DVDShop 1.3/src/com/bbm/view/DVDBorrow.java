package com.bbm.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.SimpleDateFormat;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.bbm.dao.DVDDao;
import com.bbm.entity.DVD;
import com.bbm.entity.Reader;
import com.bbm.dao.DVDTypeDao;
import com.bbm.dao.BorrowDVDDao;
import com.bbm.dao.ReaderDao;

public class DVDBorrow extends JFrame implements ActionListener, FocusListener {

	/**
	 * 图书借阅窗口
	 */
	private static final long serialVersionUID = 1L;

	private JPanel selectJP, conditionJP, resultJP,  updateJP, buttonJP;
	private JLabel readeridJL, readernameJL,  ISBNJL, typeJL, dvdnameJL, authorJL, pubJL, dateJL, adminJL;
	private JTextField readeridJTF, readernameJTF,DVDJTF, typeJTF, dvdnameJTF, authorJTF, pubJTF, dateJTF,adminJTF;
	private JScrollPane jscrollPane;

	private JTable jtable;
	private JButton borrowJB, closeJB;
	private String[][] results = null;
	private String[] readersearch = null;

	public DVDBorrow() {
		setBounds(200, 200, 800, 650);
		setTitle("光盘借阅");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		// 读者借阅信息查询面板设计
		selectJP = new JPanel();
		selectJP.setLayout(new BorderLayout());
		selectJP.setBorder(new TitledBorder(null, "借阅信息", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		// 查询条件面板
		// 查询条件下拉列表框
		conditionJP = new JPanel();

		readeridJL = new JLabel("借阅者编号：");
		readeridJTF = new JTextField(8);

		readernameJL = new JLabel("姓名：");
		readernameJTF = new JTextField(8);
		conditionJP.add(readeridJL);
		conditionJP.add(readeridJTF);
		conditionJP.add(readernameJL);
		conditionJP.add(readernameJTF);

		selectJP.add(conditionJP, BorderLayout.NORTH);

		// 查询结果面板
		resultJP = new JPanel();
		jscrollPane = new JScrollPane();
		jscrollPane.setPreferredSize(new Dimension(450, 150));

		readersearch = new String[] { "光盘编号", "片名", "租借日期" };
		results = new String[][] {};
		jtable = new JTable(results, readersearch);
		jtable.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		jscrollPane.setViewportView(jtable);

		resultJP.add(jscrollPane);
		selectJP.add(resultJP, BorderLayout.CENTER);

		// 读者信息修改面板设计
		updateJP = new JPanel();
		updateJP.setBorder(new EmptyBorder(5, 10, 5, 10));
		updateJP.setBorder(new TitledBorder(null, "光盘借阅信息", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridLayout gridLayout = new GridLayout(8, 2);
		gridLayout.setVgap(10);
		gridLayout.setHgap(10);
		updateJP.setLayout(gridLayout);

		ISBNJL = new JLabel("光盘编号");
		ISBNJL.setHorizontalAlignment(SwingConstants.CENTER);
		updateJP.add(ISBNJL);
		DVDJTF = new JTextField();
		updateJP.add(DVDJTF);

		typeJL = new JLabel("类别：");
		typeJL.setHorizontalAlignment(SwingConstants.CENTER);
		updateJP.add(typeJL);
		typeJTF = new JTextField();
		updateJP.add(typeJTF);

		dvdnameJL = new JLabel("片名：");
		dvdnameJL.setHorizontalAlignment(SwingConstants.CENTER);
		updateJP.add(dvdnameJL);
		dvdnameJTF = new JTextField();
		updateJP.add(dvdnameJTF);

		authorJL = new JLabel("导演：");
		authorJL.setHorizontalAlignment(SwingConstants.CENTER);
		updateJP.add(authorJL);
		authorJTF = new JTextField();
		updateJP.add(authorJTF);

		pubJL = new JLabel("制片商：");
		pubJL.setHorizontalAlignment(SwingConstants.CENTER);
		updateJP.add(pubJL);
		pubJTF = new JTextField();
		updateJP.add(pubJTF);

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String str = df.format(new java.util.Date());

		dateJL = new JLabel("当前日期：");
		dateJL.setHorizontalAlignment(SwingConstants.CENTER);
		updateJP.add(dateJL);
		dateJTF = new JTextField(str);
		dateJTF.setEditable(false);
		updateJP.add(dateJTF);

		adminJL = new JLabel("操作人员：");
		adminJL.setHorizontalAlignment(SwingConstants.CENTER);
		updateJP.add(adminJL);
		adminJTF = new JTextField();
		adminJTF = new JTextField("admin");
		adminJTF.setEditable(false);
		updateJP.add(adminJTF);

		// 登录取消按钮面板设计
		buttonJP = new JPanel();// 修改按钮面板
		borrowJB = new JButton("借阅");
		closeJB = new JButton("关闭");
		buttonJP.add(borrowJB);
		buttonJP.add(closeJB);

		this.add(selectJP, BorderLayout.NORTH);
		this.add(updateJP, BorderLayout.CENTER);
		this.add(buttonJP, BorderLayout.SOUTH);

		borrowJB.addActionListener(this);
		closeJB.addActionListener(this);
		DVDJTF.addFocusListener(this);
		readeridJTF.addFocusListener(this);
		this.setVisible(true);// 设置窗体显示，否则不显示。
		setResizable(true);// 取消最大化

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		BorrowDVDDao borrowDVDDao = new BorrowDVDDao();

		if (e.getSource() == borrowJB) {
			String readerid = readeridJTF.getText().intern();
			String ISBN = DVDJTF.getText().intern();
			if ("".equals(readerid) || "".equals(ISBN)) {
				JOptionPane.showMessageDialog(this, "请输入数据");
				return;
			}
			int row = borrowDVDDao.save(readerid, ISBN);
			if (row == 1) {
				results = borrowDVDDao.getArrayData(borrowDVDDao.findBorrowByReaderId(readeridJTF.getText().intern()));
				jtable = new JTable(results, readersearch);
				jtable.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
				jscrollPane.setViewportView(jtable);
				JOptionPane.showMessageDialog(this, "添加成功！");
			} else {
				JOptionPane.showMessageDialog(this, "添加失败！");
			}
		}
		if (e.getSource() == closeJB) {
			dispose();
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		ReaderDao readerDao = new ReaderDao();
		DVDDao DVDDao = new DVDDao();
		DVDTypeDao DVDTypeDao = new DVDTypeDao();
		BorrowDVDDao borrowDVDDao = new BorrowDVDDao();
		if (e.getSource() == readeridJTF) {
			if ("".equals(readeridJTF.getText().intern()))
				return;
			Reader reader = readerDao.findReaderById(readeridJTF.getText().intern());
			if (reader == null) {
				readeridJTF.setText(null);
				JOptionPane.showMessageDialog(this, "没该用户！");
			} else {
				readernameJTF.setText(reader.getName());
				results = borrowDVDDao.getArrayData(borrowDVDDao.findBorrowByReaderId(reader.getReaderId()));
				jtable = new JTable(results, readersearch);
				jtable.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
				jscrollPane.setViewportView(jtable);
			}

		}
		if (e.getSource() == DVDJTF) {
			if ("".equals(DVDJTF.getText().intern()))
				return;
			DVD DVD = DVDDao.findDVDById(DVDJTF.getText().intern());
			if (DVD == null) {
				DVDJTF.setText(null);
				JOptionPane.showMessageDialog(this, "无");
			} else {
				typeJTF.setText(DVDTypeDao.findNameById(DVD.getTypeId()));
				dvdnameJTF.setText(DVD.getDvdName());
				authorJTF.setText(DVD.getAuthor());
				pubJTF.setText(DVD.getPublish());
			}
		}
	}

	@Override
	public void focusGained(FocusEvent e) {
	}


}