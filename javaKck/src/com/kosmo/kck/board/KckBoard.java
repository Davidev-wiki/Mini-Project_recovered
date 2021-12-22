package com.kosmo.kck.board;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import com.kosmo.kck.common.CodeUtil;
import com.kosmo.kck.common.KckBoardChabun;

public class KckBoard extends JFrame implements ActionListener {

	// 상수
	private static final long serialVersionUID = 1L;

	// 멤버 변수
	private JLabel jl[];
	private JTextField jt[];
	private JTextArea jta;
	private JPasswordField jpf;
	private JButton jb[];
	private JPanel jp[];

	// 생성자
	public KckBoard() {

		// JFrame 타이틀 세팅하기
		this.setTitle("게시판");

		// JFrame 레이아웃 매니저 : 보더 레이아웃 설정
		this.getContentPane().setLayout(null);

		// 패널 2개 생성
		jp = new JPanel[2];
		jp[0] = new JPanel();
		jp[0].setBorder(new EtchedBorder());
		jp[0].setBounds(0, 0, 465, 480);
		jp[0].setBackground(Color.orange);
		jp[0].setLayout(null);

		// 게시판 라벨
		JLabel jlM = new JLabel();
		jlM.setText("게시판");
		jlM.setHorizontalAlignment(SwingConstants.CENTER);
		jlM.setFont(new Font("맑은고딕", Font.BOLD, 20));
		jlM.setBounds(20, 20, 362, 40);
		jp[0].add(jlM);

		// 라벨
		jl = new JLabel[5];
		int ly = 80;
		for (int i = 0; i < jl.length; i++) {
			jl[i] = new JLabel();
			jl[i].setOpaque(true);
			jl[i].setText(CodeUtil.board_label[i]);
			jl[i].setHorizontalAlignment(SwingConstants.CENTER);
			jl[i].setFont(new Font("맑은고딕", Font.BOLD, 15));
			jl[i].setBounds(20, ly, 100, 30);
			ly += 40;
			jp[0].add(jl[i]);
		}

		// 텍스트 필드
		jt = new JTextField[3];
		int ty = 80;
		for (int i = 0; i < jt.length; i++) {
			jt[i] = new JTextField(200);
			jt[i].setBounds(130, ty, 300, 30);
			jp[0].add(jt[i]);
			ty += 40;
		}

		// 비밀번호
		jpf = new JPasswordField();
		jpf.setBounds(130, 200, 100, 30);
		jpf.setEchoChar('*');
		jp[0].add(jpf);

		// 패널 생성
		jp[1] = new JPanel();
		jp[1].setLayout(new BorderLayout(5, 5));
		jp[1].setBounds(130, 240, 300, 140);
		jp[1].setBackground(Color.red);
		jp[0].add(jp[1]);

		// 패널에 텍스트 에어리어추가
		jta = new JTextArea(10, 10);
		jp[1].add(new JScrollPane(jta));

		// 버튼
		jb = new JButton[2];
		for (int i = 0; i < jb.length; i++) {
			jb[i] = new JButton();
			jb[i].addActionListener(this);
			jp[0].add(jb[i]);
		}

		jb[0].setText("작성하기");
		jb[0].setBounds(20, 420, 250, 30);
		jb[0].setFont(new Font("맑은고딕", Font.BOLD, 15));

		jb[1].setText("다시");
		jb[1].setBounds(280, 420, 150, 30);
		jb[1].setFont(new Font("맑은고딕", Font.BOLD, 15));

		// jTextField 비활성화 : 삭제여부, 등록일, 수정일
		jt[0].setEditable(false);

		// JFrame에 JPanel 붙이기
		this.getContentPane().add(jp[0]);

		this.setSize(465, 520);
		this.setLocation(200, 100);
		this.setResizable(false);
		this.setVisible(true);

		// JFrame 닫기
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				e.getWindow().setVisible(false);
				e.getWindow().dispose();
			}
		});
	}

	// 게시판 등록 함수
	public void kboardInsert(String bsubject, String bwriter, String bpw, String bcontents) {
		System.out.println("KckBoard.kboardInsert()함수 진입");

		KckBoardService kbs = new KckBoardServiceImpl();
		KckBoardVO kvo = new KckBoardVO();

		kvo.setBnum(KckBoardChabun.gubunNum());
		kvo.setBsubject(bsubject);
		kvo.setBwriter(bwriter);
		kvo.setBpw(bpw);
		kvo.setBcontents(bcontents);

		// *nCnt
		int nCnt = kbs.kboardInsert(kvo);

		if (nCnt == 1) {
			System.out.println("게시물 등록이 완료되었습니다!" + nCnt);
			JOptionPane.showMessageDialog(this, "게시글 등록 성공 >>> :  ");
			// 게시물 전체 조회 창 팝업
			new KckBoardAll();
		} else {
			System.out.println("게시물 등록 실패" + nCnt);
		}

	}

	// 텍스트 필드 초기화 함수
	public void valueClear() {
		System.out.println("KckBoard.valueClear()함수 진입");
		for (int i = 0; i < jt.length; i++) {
			jt[i].setText("");
		}
		// 패스워드필드
		jpf.setText("");
		// 텍스트에어리어
		jta.setText("");
	}

	// ActionListener 구현 함수
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("KckBoard.actionPerformed()함수 진입");

		Object obj = e.getSource();
		Object jbCaption = e.getActionCommand();

		if (jb[0] == obj) {
			System.out.println("작성하기 버튼이 클릭됨 : " + jbCaption);

			String bsubject = "";
			String bwriter = "";
			String bpw = "";
			String bcontents = "";

			bsubject = jt[1].getText();
			bwriter = jt[2].getText();
			bpw = jpf.getText();
			bcontents = jta.getText();

			System.out.println("bsubject : " + bsubject);
			System.out.println("bwriter : " + bwriter);
			System.out.println("bpw : " + bpw);
			System.out.println("bcontents : " + bcontents);

			this.kboardInsert(bsubject, bwriter, bpw, bcontents);
		}

		if (jb[1] == obj) {
			System.out.println("다시 버튼이 클릭됨");
			this.valueClear();
		}
	}

	// main 함수
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// 생성자로 세팅
		new KckBoard();
	}

}
