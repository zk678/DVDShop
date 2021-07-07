package com.bbm.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.bbm.entity.Reader;

import com.bbm.dao.ReaderDao;


//-------------ʵ�ֽӿ�-----------------//
public class ReaderSelectAndModify extends JFrame implements MouseListener, ActionListener, FocusListener {
	private static final long serialVersionUID = 1L;

	private JPanel selectJP,select_resultJP, sexJP, updateJP, buttonJP;
	private ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel IDJL, readerNameJL, sexJL, phoneJL,regJL;
	private JTextField  IDJTF, readerNameJTF, phoneJTF, regJTF;
	private JScrollPane jscrollPane;

	private JRadioButton JRB1, JRB2;
	private JTable jtable;
	private JButton updateJB, closeJB, deleteJB;
	private String[] readersearch;
	String[][] results = null;

	public ReaderSelectAndModify() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 500, 500);
		setTitle("��������Ϣ��ѯ���޸�");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		// ��������Ϣ��ѯ������
		selectJP = new JPanel();
		selectJP.setLayout(new BorderLayout());

		// ��ѯ������
		select_resultJP = new JPanel();
		jscrollPane = new JScrollPane();
		jscrollPane.setPreferredSize(new Dimension(300, 150));
		readersearch = new String[] { "���", "����", "�绰", "�Ա�", "ע������" };

		ReaderDao readerDao = new ReaderDao();
		results = readerDao.getArrayData(readerDao.findAll());
		jtable = new JTable(results, readersearch);
		jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		jscrollPane.setViewportView(jtable);

		select_resultJP.add(jscrollPane);
		selectJP.add(select_resultJP, BorderLayout.CENTER);

		// ��������Ϣ�޸�������
		updateJP = new JPanel();
		updateJP.setBorder(new EmptyBorder(5, 10, 5, 10));
		GridLayout gridLayout = new GridLayout(5, 2);
		gridLayout.setVgap(10);
		gridLayout.setHgap(10);
		updateJP.setLayout(gridLayout);

		IDJL = new JLabel("��ţ�");
		IDJL.setHorizontalAlignment(SwingConstants.CENTER);
		updateJP.add(IDJL);
		IDJTF = new JTextField();
		// IDJTF.setEditable(false);
		updateJP.add(IDJTF);

		readerNameJL = new JLabel("������");
		readerNameJL.setHorizontalAlignment(SwingConstants.CENTER);
		updateJP.add(readerNameJL);
		readerNameJTF = new JTextField();
		updateJP.add(readerNameJTF);

		sexJL = new JLabel("�Ա�");
		sexJL.setHorizontalAlignment(SwingConstants.CENTER);
		updateJP.add(sexJL);
		sexJP = new JPanel();
		final FlowLayout flowLayout = new FlowLayout();
		flowLayout.setHgap(0);
		flowLayout.setVgap(0);
		sexJP.setLayout(flowLayout);

		JRB1 = new JRadioButton();
		sexJP.add(JRB1);
		JRB1.setSelected(true);
		buttonGroup.add(JRB1);
		JRB1.setText("��");

		JRB2 = new JRadioButton();
		sexJP.add(JRB2);
		buttonGroup.add(JRB2);
		JRB2.setText("Ů");
		updateJP.add(sexJP);

		phoneJL = new JLabel("�绰��");
		phoneJL.setHorizontalAlignment(SwingConstants.CENTER);
		updateJP.add(phoneJL);
		phoneJTF = new JTextField();
		updateJP.add(phoneJTF);


		regJL = new JLabel("ע�����ڣ�");
		regJL.setHorizontalAlignment(SwingConstants.CENTER);
		updateJP.add(regJL);
		regJTF = new JTextField();
		regJTF.setEditable(false);
		updateJP.add(regJTF);
		// ��¼ȡ����ť������
		buttonJP = new JPanel();// �޸İ�ť���
		updateJB = new JButton("�޸�");
		closeJB = new JButton("�ر�");
		deleteJB = new JButton("ɾ��");
		buttonJP.add(updateJB);
		buttonJP.add(deleteJB);
		buttonJP.add(closeJB);

		updateJB.addActionListener(this);
		closeJB.addActionListener(this);
		deleteJB.addActionListener(this);
		IDJTF.addFocusListener(this);
		this.add(selectJP, BorderLayout.NORTH);
		this.add(updateJP, BorderLayout.CENTER);
		this.add(buttonJP, BorderLayout.SOUTH);

		this.setVisible(true);// ���ô�����ʾ��������ʾ��

		// -------------��Ӽ�����---------------
		jtable.addMouseListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ReaderDao readerDao = new ReaderDao();
		if (e.getSource() == deleteJB) {
			String readerId = IDJTF.getText();
			if (readerId == null || "".equals(readerId)) {
				JOptionPane.showMessageDialog(this, "��ѡ������Ҫɾ���Ľ����ߣ�");
				return;
			}
			int row = readerDao.delete(readerId);
			if (row == 1) {
				results = readerDao.getArrayData(readerDao.findAll());
				jtable = new JTable(results, readersearch);
				jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				jscrollPane.setViewportView(jtable);
				jtable.addMouseListener(this);
				JOptionPane.showMessageDialog(this, "ɾ���ɹ���");
			} else {
				IDJTF.setText(null);
				JOptionPane.showMessageDialog(this, "ɾ��ʧ�ܣ�");
			}
		}
		if (e.getSource() == closeJB) {
			dispose();
		}
		if (e.getSource() == updateJB) {
			Reader reader = new Reader();
			reader.setReaderId(IDJTF.getText());
			if (reader.getReaderId() == null || "".equals(reader.getReaderId())) {
				JOptionPane.showMessageDialog(this, "��Ų���Ϊ�գ�");
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
			int row = readerDao.update(reader);
			if (row != 0) {
				results = readerDao.getArrayData(readerDao.findAll());
				jtable = new JTable(results, readersearch);
				jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				jscrollPane.setViewportView(jtable);
				jtable.addMouseListener(this);
				JOptionPane.showMessageDialog(this, "�޸ĳɹ���");
			} else {
				JOptionPane.showMessageDialog(this, "�޸�ʧ��!");
			}
		}

	}

	// ------------��д�¼��������-----------
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == jtable) {
			int row = jtable.getSelectedRow();
			IDJTF.setText(results[row][0]);
			readerNameJTF.setText(results[row][1]);
			if ("��".equals(results[row][3])) {
				JRB1.setSelected(true);
				JRB2.setSelected(false);
			} else {
				JRB1.setSelected(false);
				JRB2.setSelected(true);
			}
			phoneJTF.setText(results[row][2]);
			regJTF.setText(results[row][4]);
		}

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mousePressed(MouseEvent arg0) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void focusLost(FocusEvent e) {
		// System.out.println("+++++++++++++++++++++");
		String readerid = IDJTF.getText().intern();
		if ("".equals(readerid)) {
			JOptionPane.showMessageDialog(this, "�����߱�Ų���Ϊ��");
			return;
		}
		ReaderDao readerDao = new ReaderDao();
//		ReaderTypeDao readerTypeDao = new ReaderTypeDao();
		Reader reader = readerDao.findReaderById(readerid);
		if (reader == null) {
			IDJTF.setText(null);
			JOptionPane.showMessageDialog(this, "û�иý����ߣ�");
			return;
		}
		IDJTF.setText(reader.getReaderId());
		readerNameJTF.setText(reader.getName());
		if ("��".equals(reader.getSex())) {
			JRB1.setSelected(true);
			JRB2.setSelected(false);
		} else {
			JRB1.setSelected(false);
			JRB2.setSelected(true);
		}
		phoneJTF.setText(reader.getPhone());
		regJTF.setText(reader.getRegDate());
	}
}
