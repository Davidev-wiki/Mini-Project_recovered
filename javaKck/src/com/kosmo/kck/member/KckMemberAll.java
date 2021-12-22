package com.kosmo.kck.member;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import com.kosmo.kck.common.CodeUtil;

public class KckMemberAll extends JFrame implements ActionListener {

	private static final long serialVersionUID = -7773980409866362226L;

	DefaultTableModel dtm;
	JTable jt;
	Object[][] fieldValue;
	String[] columnName;

	JPanel[] jpR;
	JLabel jlR;
	JComboBox<String> jcR;
	JTextField jtR;
	JButton jbR;
	JScrollPane jsPain;
	JButton jtBtn[];
	JCheckBox jcb[];

	String comboStr;
	String searchStr;
	
	
	//8/25 �̱��� ���� ����� �߰���
	private static KckMemberAll kmemAll;	
	public static KckMemberAll getInstance() {
		if (kmemAll == null) {
			kmemAll = new KckMemberAll();
		}
		return kmemAll;
	}

	// ������
	@SuppressWarnings("unchecked")
	public KckMemberAll() {
		this.setTitle("ȸ���˻��ϱ�");
		this.getContentPane().setLayout(new BorderLayout(10, 10));

		jpR = new JPanel[3];
		jlR = new JLabel("��ȸ����");
		jlR.setHorizontalAlignment(SwingConstants.CENTER);

		jcR = new JComboBox<String>(CodeUtil.combo_caption);
		jcR.setSelectedIndex(1);
		jcR.addActionListener(this);

		jtR = new JTextField();
		jtR.setVisible(false);
		jbR = new JButton("��ȸ");
		jbR.addActionListener(this);

		for (int i = 0; i < jpR.length; i++) {
			jpR[i] = new JPanel();
			jpR[i].setLayout(new BorderLayout());
		}

		// ################################################
		// JTable ���̴� ��Ģ
		columnName = CodeUtil.member_selectall_value;

		this.kmemSelectAll();

		dtm = new DefaultTableModel(fieldValue, columnName);
		jt = new JTable(dtm);
		jt.setEnabled(true);
		jsPain = new JScrollPane(jt);

		this.jtableRender();
		// ################################################

		jpR[0].add(jlR);
		jpR[0].add(jcR);
		jpR[0].add(jtR);
		jpR[0].add(jbR);
		jpR[0].setLayout(new GridLayout(1, 4, 5, 5));
		jpR[1].add(jsPain);

		jpR[2] = new JPanel();
		jpR[2].setLayout(new FlowLayout(FlowLayout.CENTER));

		jtBtn = new JButton[2];
		for (int i = 0; i < jtBtn.length; i++) {
			jtBtn[i] = new JButton(CodeUtil.jbtn_caption[i]);
			jtBtn[i].addActionListener(this);
			jpR[2].add(jtBtn[i]);
		}

		this.getContentPane().add(jpR[0], BorderLayout.NORTH);
		this.getContentPane().add(jpR[1], BorderLayout.CENTER);
		this.getContentPane().add(jpR[2], BorderLayout.SOUTH);

		this.setLocation(100, 100);
		this.setSize(1600, 540);
		this.setResizable(false);
		this.setVisible(true);

		// JFrame Ŭ�ο���
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				e.getWindow().setVisible(false);
				e.getWindow().dispose();
			}
		});
	}

	public void jtableRender() {
//			################################# ���� 	
		// �÷� ������ ����
		jt.getColumn("ȸ���̸�").setPreferredWidth(30);
		jt.getColumn("���̵�").setPreferredWidth(30);
		jt.getColumn("�н�����").setPreferredWidth(30);
		jt.getColumn("����").setPreferredWidth(30);
		jt.getColumn("���").setPreferredWidth(30);
		jt.getColumn("����").setPreferredWidth(30);
		jt.getColumn("Ư��").setPreferredWidth(30);
		jt.getColumn("����").setPreferredWidth(30);
		jt.getColumn("Y/N").setPreferredWidth(10);
		jt.getColumn("����/����").setPreferredWidth(60);
		// �� ��� ����
		DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();
		tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcmSchedule = jt.getColumnModel();
		for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
			tcmSchedule.getColumn(i).setCellRenderer(tScheduleCellRenderer);
		}
		// ���̺��� ��ũ���������� ũ�� �����ϱ�
		jsPain.setPreferredSize(new Dimension(1600, 400));
//			################################# ��		

		// ����/���� ��ư ���̱�
		jt.getColumnModel().getColumn(17).setCellRenderer(new TableCell());
		jt.getColumnModel().getColumn(17).setCellEditor(new TableCell());
	}

	// ################################# ����
	// ����/���� ��ư ���̱�
	class TableCell extends AbstractCellEditor implements TableCellEditor, TableCellRenderer, ActionListener {
		private static final long serialVersionUID = 1L;

		JButton jb;

		// ������
		public TableCell() {
			jb = new JButton("����/����");
			// jc.addActionListener(e -> {
			// System.out.println(jt.getValueAt(jt.getSelectedRow(), 2));
			// });
			jb.addActionListener(this);
		}

		@Override
		public Object getCellEditorValue() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			// TODO Auto-generated method stub
			return jb;
		}

		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			// TODO Auto-generated method stub
			return jb;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.out.println("TableCell actionPerformed() �Լ� ����  : ");

			if (jb == e.getSource()) {
				String knum = String.valueOf(jt.getValueAt(jt.getSelectedRow(), 0));
				System.out.println("knum  : " + knum);
				KckMemberUpdate kmu = new KckMemberUpdate();
				kmu.kmemSelect(knum);
			}
		}
	} // end of TableCell
		// ################################# ��

	public void kmemSelectAll() {
		System.out.println("KckMemberAll kmemSelectAll() �Լ� ����  : ");

		KckMemberService kms = new KckMemberServiceImpl();
		ArrayList<KckMemberVO> aList = kms.kmemselectAll();

		int columnCnt = columnName.length;
		int rowCnt = aList.size();
		System.out.println("rowCnt  : " + rowCnt);
		this.jtablePrint(aList, rowCnt, columnCnt);
	}

	// ȸ�� �̸� �˻�
	public void kmemSelectName(String searchStr) {
		System.out.println("KckMemberAll kmemSelectName() �Լ� ����  : ");

		KckMemberService kms = new KckMemberServiceImpl();
		KckMemberVO kvo = null;
		kvo = new KckMemberVO();
		kvo.setKname(searchStr);
		ArrayList<KckMemberVO> aList = kms.kmemSelectName(kvo);

		int columnCnt = columnName.length;
		int rowCnt = aList.size();
		System.out.println("rowCnt  : " + rowCnt);
		this.jtablePrint(aList, rowCnt, columnCnt);
	}

	// ȸ�� ���̵� �˻�
	public void kmemSelectId(String searchStr) {
		System.out.println("KckMemberAll kmemSelectId() �Լ� ����  : ");

		KckMemberService kms = new KckMemberServiceImpl();
		KckMemberVO kvo = null;
		kvo = new KckMemberVO();
		kvo.setKid(searchStr);
		ArrayList<KckMemberVO> aList = kms.kmemSelectId(kvo);

		int columnCnt = columnName.length;
		int rowCnt = aList.size();
		System.out.println("rowCnt  : " + rowCnt);
		this.jtablePrint(aList, rowCnt, columnCnt);
	}

	// JTable ����Ʈ
	public void jtablePrint(ArrayList<KckMemberVO> aList, int rowCnt, int columnCnt) {
		System.out.println("KckMemberAll jtablePrint() �Լ� ����  : ");

		fieldValue = new Object[rowCnt][columnCnt];

		for (int i = 0; i < rowCnt; i++) {

			KckMemberVO _kvo = aList.get(i);
			fieldValue[i][0] = _kvo.getKnum();
			fieldValue[i][1] = _kvo.getKname();
			fieldValue[i][2] = _kvo.getKid();
			fieldValue[i][3] = _kvo.getKpw();
			fieldValue[i][4] = _kvo.getKbirth();
			fieldValue[i][5] = _kvo.getKgender();
			fieldValue[i][6] = _kvo.getKtel();
			fieldValue[i][7] = _kvo.getKhp();
			fieldValue[i][8] = _kvo.getKemail();
			fieldValue[i][9] = _kvo.getKaddr();
			fieldValue[i][10] = CodeUtil.hobby(_kvo.getKhobby());
			fieldValue[i][11] = _kvo.getKphoto();
			fieldValue[i][12] = _kvo.getKskill();
			fieldValue[i][13] = CodeUtil.job(_kvo.getKjob());
			fieldValue[i][14] = _kvo.getDeleteyn();
			fieldValue[i][15] = _kvo.getInsertdate();
			fieldValue[i][16] = _kvo.getUpdatedate();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		// ComboBox �̺�Ʈ ó��
		if (jcR == e.getSource()) {
			comboStr = (String) jcR.getSelectedItem();
			if ("��ü".equals(comboStr)) {
				jtR.setVisible(false);
			}
			if ("���̵�".equals(comboStr)) {
				jtR.setVisible(true);
			}
			if ("�̸�".equals(comboStr)) {
				jtR.setVisible(true);
			}
		}

		// ��ȸ ��ư �̺�Ʈ
		if (jbR == e.getSource()) {
			String searchStr = jtR.getText();

			if ("��ü".equals(comboStr)) {
				System.out.println("searchStr : " + searchStr);

				this.kmemSelectAll();
			}
			if ("�̸�".equals(comboStr)) {
				System.out.println("searchStr : " + searchStr);

				this.kmemSelectName(searchStr);
			}
			if ("���̵�".equals(comboStr)) {
				System.out.println(" searchStr : " + searchStr);
				this.kmemSelectId(searchStr);
			}
		}

		// ��ư
		if (jtBtn[0] == e.getSource()) {
			System.out.println("ȸ�� �����ϱ� : ");
			// 08/25 �߰��� �κ�
			this.setVisible(false);
			this.dispose();
			// ---------------
			new KckMember();
		}
		if (jtBtn[1] == e.getSource()) {
			System.out.println("ȸ�� ��Ϻ��� : ");
			
			// 08/25 �̱������ϰ� �Բ� �߰��� �κ�
			// jcR.setSelectedIndex(0);		
			// this.kmemSelectAll();
			// ------------------------
		}
		if (jtBtn[3] == e.getSource()) {
			System.out.println("�����ϱ� : ");
			new KckMemberUpdate();
		}
		if (jtBtn[4] == e.getSource()) {
			System.out.println("�����ϱ� : ");
			String s = jtBtn[4].getText();
			System.out.println(s);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new KckMemberAll();
	}
}