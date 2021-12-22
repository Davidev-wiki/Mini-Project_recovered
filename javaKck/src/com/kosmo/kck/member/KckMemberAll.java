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
	
	
	//8/25 싱글톤 패턴 방식이 추가됨
	private static KckMemberAll kmemAll;	
	public static KckMemberAll getInstance() {
		if (kmemAll == null) {
			kmemAll = new KckMemberAll();
		}
		return kmemAll;
	}

	// 생성자
	@SuppressWarnings("unchecked")
	public KckMemberAll() {
		this.setTitle("회원검색하기");
		this.getContentPane().setLayout(new BorderLayout(10, 10));

		jpR = new JPanel[3];
		jlR = new JLabel("조회조건");
		jlR.setHorizontalAlignment(SwingConstants.CENTER);

		jcR = new JComboBox<String>(CodeUtil.combo_caption);
		jcR.setSelectedIndex(1);
		jcR.addActionListener(this);

		jtR = new JTextField();
		jtR.setVisible(false);
		jbR = new JButton("조회");
		jbR.addActionListener(this);

		for (int i = 0; i < jpR.length; i++) {
			jpR[i] = new JPanel();
			jpR[i].setLayout(new BorderLayout());
		}

		// ################################################
		// JTable 붙이는 규칙
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

		// JFrame 클로우즈
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				e.getWindow().setVisible(false);
				e.getWindow().dispose();
			}
		});
	}

	public void jtableRender() {
//			################################# 시작 	
		// 컬럼 사이즈 조절
		jt.getColumn("회원이름").setPreferredWidth(30);
		jt.getColumn("아이디").setPreferredWidth(30);
		jt.getColumn("패스워드").setPreferredWidth(30);
		jt.getColumn("성별").setPreferredWidth(30);
		jt.getColumn("취미").setPreferredWidth(30);
		jt.getColumn("사진").setPreferredWidth(30);
		jt.getColumn("특기").setPreferredWidth(30);
		jt.getColumn("직업").setPreferredWidth(30);
		jt.getColumn("Y/N").setPreferredWidth(10);
		jt.getColumn("수정/삭제").setPreferredWidth(60);
		// 셀 가운데 정렬
		DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();
		tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcmSchedule = jt.getColumnModel();
		for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
			tcmSchedule.getColumn(i).setCellRenderer(tScheduleCellRenderer);
		}
		// 테이블을 스크롤페인으로 크기 조절하기
		jsPain.setPreferredSize(new Dimension(1600, 400));
//			################################# 끝		

		// 수정/삭제 버튼 붙이기
		jt.getColumnModel().getColumn(17).setCellRenderer(new TableCell());
		jt.getColumnModel().getColumn(17).setCellEditor(new TableCell());
	}

	// ################################# 시작
	// 수정/삭제 버튼 붙이기
	class TableCell extends AbstractCellEditor implements TableCellEditor, TableCellRenderer, ActionListener {
		private static final long serialVersionUID = 1L;

		JButton jb;

		// 생성자
		public TableCell() {
			jb = new JButton("수정/삭제");
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
			System.out.println("TableCell actionPerformed() 함수 진입  : ");

			if (jb == e.getSource()) {
				String knum = String.valueOf(jt.getValueAt(jt.getSelectedRow(), 0));
				System.out.println("knum  : " + knum);
				KckMemberUpdate kmu = new KckMemberUpdate();
				kmu.kmemSelect(knum);
			}
		}
	} // end of TableCell
		// ################################# 끝

	public void kmemSelectAll() {
		System.out.println("KckMemberAll kmemSelectAll() 함수 진입  : ");

		KckMemberService kms = new KckMemberServiceImpl();
		ArrayList<KckMemberVO> aList = kms.kmemselectAll();

		int columnCnt = columnName.length;
		int rowCnt = aList.size();
		System.out.println("rowCnt  : " + rowCnt);
		this.jtablePrint(aList, rowCnt, columnCnt);
	}

	// 회원 이름 검색
	public void kmemSelectName(String searchStr) {
		System.out.println("KckMemberAll kmemSelectName() 함수 진입  : ");

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

	// 회원 아이디 검색
	public void kmemSelectId(String searchStr) {
		System.out.println("KckMemberAll kmemSelectId() 함수 진입  : ");

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

	// JTable 프린트
	public void jtablePrint(ArrayList<KckMemberVO> aList, int rowCnt, int columnCnt) {
		System.out.println("KckMemberAll jtablePrint() 함수 진입  : ");

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

		// ComboBox 이벤트 처리
		if (jcR == e.getSource()) {
			comboStr = (String) jcR.getSelectedItem();
			if ("전체".equals(comboStr)) {
				jtR.setVisible(false);
			}
			if ("아이디".equals(comboStr)) {
				jtR.setVisible(true);
			}
			if ("이름".equals(comboStr)) {
				jtR.setVisible(true);
			}
		}

		// 조회 버튼 이벤트
		if (jbR == e.getSource()) {
			String searchStr = jtR.getText();

			if ("전체".equals(comboStr)) {
				System.out.println("searchStr : " + searchStr);

				this.kmemSelectAll();
			}
			if ("이름".equals(comboStr)) {
				System.out.println("searchStr : " + searchStr);

				this.kmemSelectName(searchStr);
			}
			if ("아이디".equals(comboStr)) {
				System.out.println(" searchStr : " + searchStr);
				this.kmemSelectId(searchStr);
			}
		}

		// 버튼
		if (jtBtn[0] == e.getSource()) {
			System.out.println("회원 가입하기 : ");
			// 08/25 추가된 부분
			this.setVisible(false);
			this.dispose();
			// ---------------
			new KckMember();
		}
		if (jtBtn[1] == e.getSource()) {
			System.out.println("회원 목록보기 : ");
			
			// 08/25 싱글톤패턴과 함께 추가된 부분
			// jcR.setSelectedIndex(0);		
			// this.kmemSelectAll();
			// ------------------------
		}
		if (jtBtn[3] == e.getSource()) {
			System.out.println("수정하기 : ");
			new KckMemberUpdate();
		}
		if (jtBtn[4] == e.getSource()) {
			System.out.println("삭제하기 : ");
			String s = jtBtn[4].getText();
			System.out.println(s);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new KckMemberAll();
	}
}
