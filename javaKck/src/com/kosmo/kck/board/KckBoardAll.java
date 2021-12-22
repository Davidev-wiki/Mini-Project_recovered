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
	
	// 상수
	private static final long serialVersionUID = -7773980409866362226L;

	// 멤버변수
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
	

	 //8/25 싱글톤 패턴 방식이 추가됨
	 private static KckBoardAll kboardAll;	
	 public static KckBoardAll getInstance() {
		if (kboardAll == null) {
			kboardAll = new KckBoardAll();
		}
		return kboardAll;
	 }

	
	
	// 생성자
	@SuppressWarnings("unchecked")
	public KckBoardAll() {
		this.setTitle("게시글 검색하기");
		this.getContentPane().setLayout(new BorderLayout(10, 10));

		jpR = new JPanel[3];
		jlR = new JLabel("조회조건");
		jlR.setHorizontalAlignment(SwingConstants.CENTER);

		jcR = new JComboBox<String>(CodeUtil.board_combo_caption);
		jcR.setSelectedIndex(1);
		jcR.addActionListener(this);

		jtR = new JTextField();
		jtR.setVisible(false);
		jbR = new JButton("조회");
		jbR.addActionListener(this);

		for (int i=0; i < jpR.length; i++ ){
			jpR[i] = new JPanel();
			jpR[i].setLayout(new BorderLayout());
		}
		
//################################################
		// JTable 붙이는 규칙 
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
		
		// JFrame 클로우즈 
		this.addWindowListener(new WindowAdapter() { 
			public void windowClosing(WindowEvent e) { 
				e.getWindow().setVisible(false);
				e.getWindow().dispose();
			}
		});
	}
	// 함수
	public void jtableRender() {
//		################################# 시작 	
		// 컬럼 사이즈 조절
		//jt.getColumn("글제목").setPreferredWidth(30);
		//jt.getColumn("작성자").setPreferredWidth(30);
		//jt.getColumn("비밀번호").setPreferredWidth(30);
		jt.getColumn("글내용").setPreferredWidth(100);
		jt.getColumn("Y/N").setPreferredWidth(10);
		//jt.getColumn("등록일").setPreferredWidth(30);
		//jt.getColumn("수정일").setPreferredWidth(30);		
		//jt.getColumn("수정/삭제").setPreferredWidth(60);		
		// 셀 가운데 정렬
		DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();
		tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcmSchedule = jt.getColumnModel();
		for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
			tcmSchedule.getColumn(i).setCellRenderer(tScheduleCellRenderer);
		}				
		// 테이블을 스크롤페인으로 크기 조절하기 
		jsPain.setPreferredSize(new Dimension(760, 350));
//		################################# 끝		
		
		// 수정/삭제 버튼 붙이기
		jt.getColumnModel().getColumn(8).setCellRenderer(new TableCell());
		jt.getColumnModel().getColumn(8).setCellEditor(new TableCell());
	}

	
	//################################# 시작 
		// 수정/삭제 버튼 붙이기
		class TableCell extends AbstractCellEditor implements TableCellEditor, TableCellRenderer,  ActionListener{		
			private static final long serialVersionUID = 1L;
			
			JButton jb;
			
			// 생성자 
			public TableCell(){			
				jb = new JButton("수정/삭제");			
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
				System.out.println("TableCell actionPerformed() 함수 진입 >>> : ");
				
				if (jb == e.getSource()) {
					String bnum = String.valueOf(jt.getValueAt(jt.getSelectedRow(), 0));
					System.out.println("bnum >>> : " + bnum);
					KckBoardUpdate kbu = new KckBoardUpdate();
					kbu.kboardSelect(bnum);
				}
			}
		} // end of TableCell 	
	//################################# 끝	
	
	// 전체 조회 함수
	public void kboardSelectAll() {
		System.out.println("KckBoardAll.kboardSelectAll()함수 진입 : ");
		
		KckBoardService kbs = new KckBoardServiceImpl();
		ArrayList<KckBoardVO> aList = kbs.kboardSelectAll();
		// 배열마다 VO가 들어있다. VO는 한 줄의 행 데이터로 이루어짐.
		int columnCnt = columnName.length;
		int rowCnt = aList.size();
		System.out.println("rowCnt : " + rowCnt);
		
		this.jtablePrint(aList, rowCnt, columnCnt);
	}
	
	// 글 제목 검색
	public void kboardSelectSubject(String searchStr) {
		System.out.println("KckBoardAll.kboardSelectSubject()함수 진입 : ");
		
		KckBoardService kbs = new KckBoardServiceImpl();	
		KckBoardVO kvo = new KckBoardVO();
		
		//kvo.setHname(searchStr);
		ArrayList<KckBoardVO> aList =  kbs.kboardSelectAll();
	
		int columnCnt = columnName.length;
		int rowCnt = aList.size();
		System.out.println("rowCnt >>> : " + rowCnt);		
		this.jtablePrint(aList, rowCnt, columnCnt);	
	}
	
	// 작성자 검색
	public void kboardSelectWriter(String searchStr) {
		System.out.println("KckBoardAll.kboardSelectAll()함수 진입 : ");
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
	
	// jTable 프린트
	public void jtablePrint(ArrayList<KckBoardVO> aList, int rowCnt, int columnCnt) {
		System.out.println("KckBoardAll.kboardSelectAll()함수 진입 : ");
		
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
	
	// @ActionListener 구현 함수 
	@Override
	public void actionPerformed(ActionEvent e) {
		// ComboBox 이벤트 처리
		if (jcR == e.getSource()) {
			comboStr = (String)jcR.getSelectedItem();
			if ("전체".equals(comboStr)){
				jtR.setVisible(false);				
			}
			if ("글제목".equals(comboStr)){
				jtR.setVisible(true);
			}
			if ("작성자".equals(comboStr)){
				jtR.setVisible(true);
			}
		}
		
		// 조회 버튼 이벤트 
		if (jbR == e.getSource()) {
			String searchStr = jtR.getText();

			if ("전체".equals(comboStr)){
				System.out.println("searchStr >>> : " + searchStr);
				
				this.kboardSelectAll();	
			}
			if ("글제목".equals(comboStr)){				
				System.out.println("searchStr >>> : " + searchStr);
								
				this.kboardSelectSubject(searchStr);
			}
			if ("작성자".equals(comboStr)){
				System.out.println(" searchStr >>> : " + searchStr);
				this.kboardSelectWriter(searchStr);				
			}
		}
		
		// 버튼 
		if (jtBtn[0] == e.getSource()) {
			System.out.println("글 작성하기  >>> : ");
			/*
			   08/25 내용 추가됨 
			   new KckBoard();
			 */
			
		}	
		if (jtBtn[1] == e.getSource()) {
			System.out.println("글 목록보기 >>> : ");
			
			/*
			 * 
			   08/25 내용 추가됨
			jcR.setSelectedIndex(0);
			this.kboardSelectAll();
			 
			 */
			
		}
	}
	
	// main 함수
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// 생성자로 세팅
		new KckBoardAll();
		
		// 08/25 아래 싱글톤 패턴으로 업데이트 됨
		// KckBoardAll.getInstance();
	}

}
