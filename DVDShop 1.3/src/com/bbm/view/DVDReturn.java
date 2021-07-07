package com.bbm.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

public class DVDReturn extends JFrame implements ActionListener, MouseListener, FocusListener {

	/**
	 * 归还窗口
	 */
	private JPanel selectJP, conditionJP, resultJP, updateJP, buttonJP;
	private JLabel readeridJL, readernameJL, DVDIDJL, typeJL, dvdnameJL, authorJL, pubJL, dateJL,adminJL;
	private JTextField readeridJTF, readernameJTF, DVDIDJTF, typeJTF, dvdnameJTF, authorJTF,pubJTF,dateJTF,adminJTF;
	private ButtonGroup buttonGroup = new ButtonGroup();
	private JScrollPane jscrollPane;
	private JTable jtable;
	private JButton borrowJB, closeJB;
	private String[][] results = null;
	private String[] readersearch = null;

	public DVDReturn() {
		setBounds(200, 200, 700, 650);
		setTitle("光盘归还");
		// 借阅者借阅信息查询面板设计
		selectJP = new JPanel();
		selectJP.setLayout(new BorderLayout());
		selectJP.setBorder(new TitledBorder(null, "光盘借阅信息", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
		jscrollPane.setPreferredSize(new Dimension(500, 150));

		readersearch = new String[] { "光盘编号", "片名", "借阅日期" };
		results = new String[][] {};
		jtable = new JTable(results, readersearch);
		jtable.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		jscrollPane.setViewportView(jtable);

		resultJP.add(jscrollPane);
		selectJP.add(resultJP, BorderLayout.CENTER);

		// 借阅者信息修改面板设计
		updateJP = new JPanel();
		updateJP.setBorder(new EmptyBorder(5, 10, 5, 10));
		updateJP.setBorder(new TitledBorder(null, "归还光盘", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridLayout gridLayout = new GridLayout(8, 2);
		gridLayout.setVgap(10);
		gridLayout.setHgap(10);
		updateJP.setLayout(gridLayout);

		DVDIDJL = new JLabel("光盘ID：");
		DVDIDJL.setHorizontalAlignment(SwingConstants.CENTER);
		updateJP.add(DVDIDJL);
		DVDIDJTF = new JTextField();
		DVDIDJTF.setEditable(false);
		updateJP.add(DVDIDJTF);

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
		dateJTF.setEnabled(false);
		updateJP.add(dateJTF);
		adminJL = new JLabel("操作人员：");
		adminJL.setHorizontalAlignment(SwingConstants.CENTER);
		updateJP.add(adminJL);
		adminJTF = new JTextField("admin");
		adminJTF.setEditable(false);
		updateJP.add(adminJTF);

		// 登录取消按钮面板设计
		buttonJP = new JPanel();// 修改按钮面板
		borrowJB = new JButton("归还");
		closeJB = new JButton("关闭");
		buttonJP.add(borrowJB);
		buttonJP.add(closeJB);

		this.add(selectJP, BorderLayout.NORTH);
		this.add(updateJP, BorderLayout.CENTER);
		this.add(buttonJP, BorderLayout.SOUTH);

		borrowJB.addActionListener(this);
		closeJB.addActionListener(this);
		readeridJTF.addFocusListener(this);
		jtable.addMouseListener(this);

		this.setVisible(true);// 设置窗体显示，否则不显示。
		setResizable(true);// 最大化
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//System.out.println("-------------------");
		DVDDao DVDDao = new DVDDao();
		DVDTypeDao DVDTypeDao = new DVDTypeDao();
		if (e.getSource() == jtable) {
			//System.out.println("++++++++++++++++");
			int row = jtable.getSelectedRow();
			DVDIDJTF.setText(results[row][0]);
			dvdnameJTF.setText(results[row][1]);
			String DVDID = null;
			DVDID = DVDIDJTF.getText();
			DVD DVD = new DVD();
			DVD = DVDDao.findDVDById(DVDID);
			if (DVD == null) {
				JOptionPane.showMessageDialog(this, "无！");
			} else {
				typeJTF.setText(DVDTypeDao.findNameById(DVD.getTypeId()));
				dvdnameJTF.setText(DVD.getDvdName());
				authorJTF.setText(DVD.getAuthor());
				pubJTF.setText(DVD.getPublish());
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		BorrowDVDDao borrowDVDDao = new BorrowDVDDao();

		if (e.getSource() == borrowJB) {
			String readerid = readeridJTF.getText().intern();
			String DVDID = DVDIDJTF.getText().intern();
			if ("".equals(readerid) || "".equals(DVDID)) {
				JOptionPane.showMessageDialog(this, "请输入借阅者编号和光盘id");
				return;
			}
			int row = borrowDVDDao.update(readerid, DVDID);
			if (row == 1) {
				results = borrowDVDDao.getArrayData(borrowDVDDao.findBorrowByReaderId(readeridJTF.getText().intern()));
				jtable = new JTable(results, readersearch);
				jtable.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
				jscrollPane.setViewportView(jtable);
				jtable.addMouseListener(this);
				DVDIDJTF.setText(null);
				dvdnameJTF.setText(null);
				authorJTF.setText(null);
				JOptionPane.showMessageDialog(this, "归还成功！");
			} else {
				DVDIDJTF.setText(null);
				JOptionPane.showMessageDialog(this, "归还失败！");
			}
		}
		if (e.getSource() == closeJB) {
			dispose();
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		ReaderDao readerDao = new ReaderDao();
//		ReaderTypeDao readerTypeDao = new ReaderTypeDao();
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
				jtable.addMouseListener(this);
			}
		}
	}

	@Override
	public void focusGained(FocusEvent e) {

	}

}