package com.kosmo.kck.board;

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

public class KckBoardAll extends JFrame implements ActionListener {
	
	// ���
	private static final long serialVersionUID = -7773980409866362226L;

	// �������
	DefaultTableModel dtm;
	JTable             jt;
	Object[][] fieldValue;
	String[]   columnName;
	
	JPanel[]    jpR;
	JLabel      jlR;
	JComboBox<String>  jcR;
	JTextField  jtR;
	JButton     jbR;
	JScrollPane jsPain;
	JButton     jtBtn[];
	JCheckBox   jcb[];
	
	String comboStr;
	String searchStr;
	

	 //8/25 �̱��� ���� ����� �߰���
	 private static KckBoardAll kboardAll;	
	 public static KckBoardAll getInstance() {
		if (kboardAll == null) {
			kboardAll = new KckBoardAll();
		}
		return kboardAll;
	 }

	
	
	// ������
	@SuppressWarnings("unchecked")
	public KckBoardAll() {
		this.setTitle("�Խñ� �˻��ϱ�");
		this.getContentPane().setLayout(new BorderLayout(10, 10));

		jpR = new JPanel[3];
		jlR = new JLabel("��ȸ����");
		jlR.setHorizontalAlignment(SwingConstants.CENTER);

		jcR = new JComboBox<String>(CodeUtil.board_combo_caption);
		jcR.setSelectedIndex(1);
		jcR.addActionListener(this);

		jtR = new JTextField();
		jtR.setVisible(false);
		jbR = new JButton("��ȸ");
		jbR.addActionListener(this);

		for (int i=0; i < jpR.length; i++ ){
			jpR[i] = new JPanel();
			jpR[i].setLayout(new BorderLayout());
		}
		
//################################################
		// JTable ���̴� ��Ģ 
		columnName = CodeUtil.board_selectall_label;			
		
		this.kboardSelectAll();		
		
		dtm = new DefaultTableModel(fieldValue, columnName);
		jt = new JTable(dtm);
		jt.setEnabled(true);
		jsPain = new JScrollPane(jt);		
		
		this.jtableRender();
//################################################
		
		
		jpR[0].add(jlR);
		jpR[0].add(jcR);		
		jpR[0].add(jtR);
		jpR[0].add(jbR);
		jpR[0].setLayout(new GridLayout(1, 4, 5, 5));
		jpR[1].add(jsPain);
		
		jpR[2] = new JPanel();
		jpR[2].setLayout(new FlowLayout(FlowLayout.CENTER));
		
		jtBtn = new JButton[2];		
		for (int i=0; i < jtBtn.length; i++) {
			jtBtn[i] = new JButton(CodeUtil.board_jbtn_caption[i]);
			jtBtn[i].addActionListener(this);
			jpR[2].add(jtBtn[i]);
		}

		this.getContentPane().add(jpR[0], BorderLayout.NORTH);
		this.getContentPane().add(jpR[1], BorderLayout.CENTER);
		this.getContentPane().add(jpR[2], BorderLayout.SOUTH);

		this.setLocation(100, 100);
		this.setSize(800,400);
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
	// �Լ�
	public void jtableRender() {
//		################################# ���� 	
		// �÷� ������ ����
		//jt.getColumn("������").setPreferredWidth(30);
		//jt.getColumn("�ۼ���").setPreferredWidth(30);
		//jt.getColumn("��й�ȣ").setPreferredWidth(30);
		jt.getColumn("�۳���").setPreferredWidth(100);
		jt.getColumn("Y/N").setPreferredWidth(10);
		//jt.getColumn("�����").setPreferredWidth(30);
		//jt.getColumn("������").setPreferredWidth(30);		
		//jt.getColumn("����/����").setPreferredWidth(60);		
		// �� ��� ����
		DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();
		tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcmSchedule = jt.getColumnModel();
		for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
			tcmSchedule.getColumn(i).setCellRenderer(tScheduleCellRenderer);
		}				
		// ���̺��� ��ũ���������� ũ�� �����ϱ� 
		jsPain.setPreferredSize(new Dimension(760, 350));
//		################################# ��		
		
		// ����/���� ��ư ���̱�
		jt.getColumnModel().getColumn(8).setCellRenderer(new TableCell());
		jt.getColumnModel().getColumn(8).setCellEditor(new TableCell());
	}

	
	//################################# ���� 
		// ����/���� ��ư ���̱�
		class TableCell extends AbstractCellEditor implements TableCellEditor, TableCellRenderer,  ActionListener{		
			private static final long serialVersionUID = 1L;
			
			JButton jb;
			
			// ������ 
			public TableCell(){			
				jb = new JButton("����/����");			
		//		jc.addActionListener(e -> {
		//			System.out.println(jt.getValueAt(jt.getSelectedRow(), 2));
		//		});				
				jb.addActionListener(this);			
			}
			
			@Override
			public Object getCellEditorValue() {
				// TODO Auto-generated method stub
				return null;
			}
		
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, 
					                                       boolean isSelected, boolean hasFocus,
					                                       int row, int column) {
				// TODO Auto-generated method stub
				return jb;
			}
		
			@Override
			public Component getTableCellEditorComponent(JTable table, Object value, 
					                                     boolean isSelected, int row, int column) {
				// TODO Auto-generated method stub
				return jb;
			}	
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("TableCell actionPerformed() �Լ� ���� >>> : ");
				
				if (jb == e.getSource()) {
					String bnum = String.valueOf(jt.getValueAt(jt.getSelectedRow(), 0));
					System.out.println("bnum >>> : " + bnum);
					KckBoardUpdate kbu = new KckBoardUpdate();
					kbu.kboardSelect(bnum);
				}
			}
		} // end of TableCell 	
	//################################# ��	
	
	// ��ü ��ȸ �Լ�
	public void kboardSelectAll() {
		System.out.println("KckBoardAll.kboardSelectAll()�Լ� ���� : ");
		
		KckBoardService kbs = new KckBoardServiceImpl();
		ArrayList<KckBoardVO> aList = kbs.kboardSelectAll();
		// �迭���� VO�� ����ִ�. VO�� �� ���� �� �����ͷ� �̷����.
		int columnCnt = columnName.length;
		int rowCnt = aList.size();
		System.out.println("rowCnt : " + rowCnt);
		
		this.jtablePrint(aList, rowCnt, columnCnt);
	}
	
	// �� ���� �˻�
	public void kboardSelectSubject(String searchStr) {
		System.out.println("KckBoardAll.kboardSelectSubject()�Լ� ���� : ");
		
		KckBoardService kbs = new KckBoardServiceImpl();	
		KckBoardVO kvo = new KckBoardVO();
		
		//kvo.setHname(searchStr);
		ArrayList<KckBoardVO> aList =  kbs.kboardSelectAll();
	
		int columnCnt = columnName.length;
		int rowCnt = aList.size();
		System.out.println("rowCnt >>> : " + rowCnt);		
		this.jtablePrint(aList, rowCnt, columnCnt);	
	}
	
	// �ۼ��� �˻�
	public void kboardSelectWriter(String searchStr) {
		System.out.println("KckBoardAll.kboardSelectAll()�Լ� ���� : ");
		KckBoardService ks = new KckBoardServiceImpl();	
		KckBoardVO kvo = null;
		kvo = new KckBoardVO();
		//kvo.setHname(searckstr);
		ArrayList<KckBoardVO> aList =  ks.kboardSelectAll();
	
		int columnCnt = columnName.length;
		int rowCnt = aList.size();
		System.out.println("rowCnt >>> : " + rowCnt);		
		this.jtablePrint(aList, rowCnt, columnCnt);		
	}	
	
	// jTable ����Ʈ
	public void jtablePrint(ArrayList<KckBoardVO> aList, int rowCnt, int columnCnt) {
		System.out.println("KckBoardAll.kboardSelectAll()�Լ� ���� : ");
		
		fieldValue = new Object[rowCnt][columnCnt];

		for (int i=0; i < rowCnt; i++ ){
			
			KckBoardVO _kvo = aList.get(i);
			fieldValue[i][0] = _kvo.getBnum();
			fieldValue[i][1] = _kvo.getBsubject();
			fieldValue[i][2] = _kvo.getBwriter();
			fieldValue[i][3] = _kvo.getBpw();
			fieldValue[i][4] = _kvo.getBcontents();
			fieldValue[i][5] = _kvo.getDeleteyn();
			fieldValue[i][6] = _kvo.getInsertdate();
			fieldValue[i][7] = _kvo.getUpdatedate();	
		}
	}
	
	// @ActionListener ���� �Լ� 
	@Override
	public void actionPerformed(ActionEvent e) {
		// ComboBox �̺�Ʈ ó��
		if (jcR == e.getSource()) {
			comboStr = (String)jcR.getSelectedItem();
			if ("��ü".equals(comboStr)){
				jtR.setVisible(false);				
			}
			if ("������".equals(comboStr)){
				jtR.setVisible(true);
			}
			if ("�ۼ���".equals(comboStr)){
				jtR.setVisible(true);
			}
		}
		
		// ��ȸ ��ư �̺�Ʈ 
		if (jbR == e.getSource()) {
			String searchStr = jtR.getText();

			if ("��ü".equals(comboStr)){
				System.out.println("searchStr >>> : " + searchStr);
				
				this.kboardSelectAll();	
			}
			if ("������".equals(comboStr)){				
				System.out.println("searchStr >>> : " + searchStr);
								
				this.kboardSelectSubject(searchStr);
			}
			if ("�ۼ���".equals(comboStr)){
				System.out.println(" searchStr >>> : " + searchStr);
				this.kboardSelectWriter(searchStr);				
			}
		}
		
		// ��ư 
		if (jtBtn[0] == e.getSource()) {
			System.out.println("�� �ۼ��ϱ�  >>> : ");
			/*
			   08/25 ���� �߰��� 
			   new KckBoard();
			 */
			
		}	
		if (jtBtn[1] == e.getSource()) {
			System.out.println("�� ��Ϻ��� >>> : ");
			
			/*
			 * 
			   08/25 ���� �߰���
			jcR.setSelectedIndex(0);
			this.kboardSelectAll();
			 
			 */
			
		}
	}
	
	// main �Լ�
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// �����ڷ� ����
		new KckBoardAll();
		
		// 08/25 �Ʒ� �̱��� �������� ������Ʈ ��
		// KckBoardAll.getInstance();
	}

}
