package com.bbm.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.bbm.dao.DVDDao;
import com.bbm.entity.DVD;
import com.bbm.dao.DVDTypeDao;

public class DVDSelectAndUpdate extends JFrame implements ActionListener, FocusListener {

	JTabbedPane jtabbedPane;
	JPanel selectPanel, updatgePanel,resultPanel, buttonPanel, infoPanel, buttonPanel1;
	private JScrollPane jscrollPane;
	private JTable jtable;
	private JButton  button2, deleteJB;

	private JTextField DVDIDJT, dvdNameJT,wrtnJT, cbsnJT;
	private JLabel DVDIDJL, dvdNameJL, lbieJL, wrtnJL, cbsnJL;
	private JButton moJB, closeJB;
	private JComboBox lbieJCB;
	private String[][] results;
	private String[] readersearch;

	public DVDSelectAndUpdate() {
		setTitle("���̲�ѯ���޸�");
		setBounds(200, 200, 480, 400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		jtabbedPane = new JTabbedPane();
		selectPanel = new JPanel();
		updatgePanel = new JPanel();

		jtabbedPane.addTab("������Ϣ��ѯ", selectPanel);
		jtabbedPane.addTab("������Ϣ�޸�", updatgePanel);

		resultPanel = new JPanel();
		jscrollPane = new JScrollPane();
		jscrollPane.setPreferredSize(new Dimension(460, 200));
		readersearch = new String[] { "���", "����", "Ƭ��", "����", "��Ƭ��"};
		DVDDao DVDDao = new DVDDao();
		results = DVDDao.getArrayData(DVDDao.findAll());
		jtable = new JTable(results, readersearch);
		jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		jscrollPane.setViewportView(jtable);

		resultPanel.add(jscrollPane);
		selectPanel.add(resultPanel, BorderLayout.CENTER);

		buttonPanel = new JPanel();
//		button1 = new JButton("��ѯ");
		button2 = new JButton("�˳�");
		deleteJB = new JButton("ɾ��");
//		buttonPanel.add(button1);
		buttonPanel.add(deleteJB);
		buttonPanel.add(button2);
		selectPanel.add(buttonPanel, BorderLayout.SOUTH);

		updatgePanel.setLayout(new BorderLayout());
		infoPanel = new JPanel();
		infoPanel.setBorder(new EmptyBorder(5, 10, 5, 10));
		final GridLayout gridLayout = new GridLayout(8, 2);
		gridLayout.setVgap(10);
		gridLayout.setHgap(10);
		infoPanel.setLayout(gridLayout);

		DVDIDJL = new JLabel("����ID:");
		DVDIDJL.setHorizontalAlignment(SwingConstants.CENTER);
		infoPanel.add(DVDIDJL);
		DVDIDJT = new JTextField();
		infoPanel.add(DVDIDJT);

		dvdNameJL = new JLabel("Ƭ����");
		dvdNameJL.setHorizontalAlignment(SwingConstants.CENTER);
		infoPanel.add(dvdNameJL);
		dvdNameJT = new JTextField();
		infoPanel.add(dvdNameJT);
		
		lbieJL = new JLabel("ӰƬ���ͣ�");
		lbieJL.setHorizontalAlignment(SwingConstants.CENTER);
		infoPanel.add(lbieJL);
		// �����б�
		lbieJCB = new JComboBox();
		infoPanel.add(lbieJCB);
		List<String> data = new ArrayList<String>();
		DVDTypeDao DVDTypeDao = new DVDTypeDao();
		data = DVDTypeDao.findNameAll();
		Iterator<String> it = data.iterator();
		while (it.hasNext()) {
			lbieJCB.addItem(it.next());
		}

		wrtnJL = new JLabel("���ݣ�");
		wrtnJL.setHorizontalAlignment(SwingConstants.CENTER);
		infoPanel.add(wrtnJL);
		wrtnJT = new JTextField();
		infoPanel.add(wrtnJT);

		cbsnJL = new JLabel("��Ƭ�̣�");
		cbsnJL.setHorizontalAlignment(SwingConstants.CENTER);
		infoPanel.add(cbsnJL);
		cbsnJT = new JTextField();
		infoPanel.add(cbsnJT);


		updatgePanel.add(infoPanel, BorderLayout.CENTER);

		buttonPanel1 = new JPanel();
		moJB = new JButton("�޸�");
		closeJB = new JButton("�ر�");

		buttonPanel1.add(moJB);
		buttonPanel1.add(closeJB);

		updatgePanel.add(buttonPanel1, BorderLayout.SOUTH);
		add(jtabbedPane);

//		button1.addActionListener(this);
		button2.addActionListener(this);
		deleteJB.addActionListener(this);
		DVDIDJT.addFocusListener(this);
		moJB.addActionListener(this);
		closeJB.addActionListener(this);

		this.setVisible(true);// ���ô�����ʾ��������ʾ��
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == moJB) {
			DVDDao DVDDao = new DVDDao();
			DVDTypeDao DVDTypeDao = new DVDTypeDao();
			DVD DVD = new DVD();
			DVD.setDVDID(DVDIDJT.getText());
			if (DVD.getDVDID() == null || "".equals(DVD.getDVDID())) {
				JOptionPane.showMessageDialog(this, "ISBN����Ϊ�գ�");
				return;
			}
			DVD.setDvdName(dvdNameJT.getText());
			//System.out.println(DVD.getBookName());
			DVD.setTypeId(DVDTypeDao.findIdByName((String) lbieJCB.getSelectedItem()));
			DVD.setAuthor(wrtnJT.getText());
			DVD.setPublish(cbsnJT.getText());



			int r = DVDDao.update(DVD);

			if (r == 1) {
				JOptionPane.showMessageDialog(this, "�޸ĳɹ�");
			} else {
				JOptionPane.showMessageDialog(this, "�޸�ʧ��");
			}
		}
		if (e.getSource() == closeJB || e.getSource() == button2) {
			dispose();
		}

		// ɾ��ѡ���е�ͼ��
		if (e.getSource() == deleteJB) {
			int row = jtable.getSelectedRow();
			// System.out.println(row);
			String ISBN = results[row][0];
			System.out.println(ISBN);
			DVDDao DVDDao = new DVDDao();
			row = DVDDao.delete(ISBN);
			if (row == 1) {
				results = DVDDao.getArrayData(DVDDao.findAll());
				jtable = new JTable(results, readersearch);
				jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				jscrollPane.setViewportView(jtable);

				JOptionPane.showMessageDialog(this, "ɾ���ɹ�!");
			} else {
				JOptionPane.showMessageDialog(this, "ɾ��ʧ��!");
			}

		}
	}

	@Override
	public void focusGained(FocusEvent e) {

	}

	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource() == DVDIDJT) {
			DVDDao DVDDao = new DVDDao();
			DVD DVD = new DVD();
			if ("".equals(DVDIDJT.getText().intern()))
				return;
			DVD = DVDDao.findDVDById(DVDIDJT.getText());
			if (DVD == null) {
				DVDIDJT.setText(null);
				JOptionPane.showMessageDialog(this, "��");
				return;
			}
			DVDTypeDao DVDTypeDao = new DVDTypeDao();
			dvdNameJT.setText(DVD.getDvdName());
			lbieJCB.setSelectedItem(DVDTypeDao.findNameById(DVD.getTypeId()));
			wrtnJT.setText(DVD.getAuthor());
			cbsnJT.setText(DVD.getPublish());
		}
	}
}