package com.kosmo.kck.scr;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import com.kosmo.kck.login.KckLogin_Panel;
import com.kosmo.kck.util.cal.CalculatorGUI;
import com.kosmo.kck.util.chat.SwingChatClient;
import com.kosmo.kck.util.chat.SwingChatServer;
import com.kosmo.kck.util.notepad.JEditor;
import com.kosmo.kck.util.time.Ex_DigitalClock;

public class KosmoScr extends JFrame implements ActionListener{
	private static final long serialVersionUID = 6514790051744812839L;
	
	private CardLayout 	card;
	private JPanel 		contain;
	private JPanel      jpM;
	private JLabel      jl[];
	private JPanel    	jp[];
	private JButton   	jb[];
	private JButton     jbChat[];
	
	KckLogin_Panel		kLogin;
	
	private Color[] jpbk_color = {Color.orange,
			                      Color.orange, Color.orange, Color.orange, 
			                      Color.orange, Color.orange, Color.orange,
			                      Color.orange, Color.orange, Color.orange};
	private String[] jp_label = {"Hello world!", 
								 "회원 가입", "회원 수정", "회원 조회",
					             "글쓰기 등록", "글 수정", "글 조회", 
					             "계산기", "채팅", "편집기"};
	private String[] jb_caption = {"홈", 
			                      "회원가입", "회원수정", "회원조회",
			                      "글등록", "글수정", "글조회", 
			                      "계산기", "채팅", "편집기"};
	
	private String[] jbChat_caption = {"서버시작하기", "서버종료하기", "채팅입장"};
	
	private static String IMG_PATH = "/Users/home/WorkSpace/git/mini/Java-Mini-Project-JFrame-Swing/Mini_Project_Kosmo/javaKck/img_kosmo";
	
	// 생성자 
	public KosmoScr (){
		this.setTitle("KOSMO MAIN");
		
		contain = new JPanel();
		contain.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(contain);
		contain.setLayout(null);
		card = new CardLayout(10, 10);

		jpM = new JPanel();		
		jpM.setLayout(card);	
		
		// 타이머
		JPanel jpT = new JPanel();
		jpT.setBackground(Color.orange);
		jpT.setBounds(30, 20, 670, 50);		
		contain.add(jpT);
		
		Ex_DigitalClock edc = new Ex_DigitalClock();
		edc.setBackground(Color.orange);
		jpT.add(edc);
		
		// 판넬
		jp = new JPanel[10];
		jl = new JLabel[10];
		for (int i=0; i < jp.length; i++) {
			jp[i] = new JPanel();
			
			// 라벨 
			jp[i].setLayout(null);
			jl[i] = new JLabel(jp_label[i]);
			jl[i].setBackground(Color.WHITE);	
			jl[i].setBorder(new EtchedBorder());	
			jl[i].setHorizontalAlignment(SwingConstants.CENTER);
			jl[i].setFont(new Font("맑은고딕", Font.BOLD, 30));
			jl[i].setBounds(230, 20, 200, 50);
			
			jp[i].add(jl[i]);
			jp[i].setBackground(jpbk_color[i]);	
			jpM.add(jp[i]);
		}
		
		// 홈 대문 이미지
		ImageIcon im = new ImageIcon(IMG_PATH + "/" + "1_(3).png" );
		JLabel jlImg = new JLabel(im);
		jlImg.setBackground(Color.WHITE);	
		jlImg.setBorder(new EtchedBorder());	
		jlImg.setHorizontalAlignment(SwingConstants.CENTER);
		jlImg.setFont(new Font("맑은고딕", Font.BOLD, 30));
		jlImg.setBounds(180, 90, 290, 260);
		jp[0].add(jlImg);
		

		// 채팅 판텔
		JPanel chatJp = new JPanel();
		chatJp.setBackground(Color.YELLOW);
		chatJp.setBounds(130, 90, 400, 260);
		jp[8].add(chatJp);
		jbChat = new JButton[3];
		for (int i=0; i < jbChat.length; i++) {
			jbChat[i] = new JButton(jbChat_caption[i]);
			jbChat[i].addActionListener(this);
			chatJp.add(jbChat[i]);
		}
		
		// 컨테이너에 판넬 붙이기
		jpM.add(jb_caption[0], jp[0]);
		jpM.add(jb_caption[1], jp[1]);
		jpM.add(jb_caption[2], jp[2]);
		jpM.add(jb_caption[3], jp[3]);
		jpM.add(jb_caption[4], jp[4]);
		jpM.add(jb_caption[5], jp[5]);
		jpM.add(jb_caption[6], jp[6]);
		jpM.add(jb_caption[7], jp[7]);
		jpM.add(jb_caption[8], jp[8]);
		jpM.add(jb_caption[9], jp[9]);
		card.show(jpM, "홈");

		jpM.setBounds(20, 80, 690, 540);
		contain.add(jpM);
		
		// 로그인 판넬				
		JPanel jp00 = new JPanel();
		jp00.setBounds(720, 20, 320, 290);
		jp00.setBackground(Color.orange);
		contain.add(jp00);
		jp00.setLayout(null);
		
		kLogin = new KckLogin_Panel();
		kLogin.setLayout(null);
		kLogin.setBounds(5, 5, 310, 280);
		jp00.add(kLogin);
		// KckLogin_Panel이 JFrame을 상속하는 경우 add가 불가능.
		// 현재 JPanel을 상속하도록 변경하였음.
		
		// 메뉴 버튼
		jb = new JButton[10];
		for (int i=0; i < jb.length; i++) {
			jb[i] = new JButton(jb_caption[i]);
			jb[i].addActionListener(this);	
			contain.add(jb[i]);
		}
		jb[0].setBounds(720, 340, 100, 30); // 홈	
		jb[1].setBounds(720, 380, 100, 30); // 회원가입
		jb[2].setBounds(830, 380, 100, 30); // 회원수정
		jb[3].setBounds(940, 380, 100, 30);	// 회원조회
		jb[4].setBounds(720, 420, 100, 30); // 게시판 글쓰기 등록
		jb[5].setBounds(830, 420, 100, 30); // 게시판 글쓰기 수정
		jb[6].setBounds(940, 420, 100, 30);	// 게시판 조회
		jb[7].setBounds(720, 460, 100, 30); // 계산기
		jb[8].setBounds(830, 460, 100, 30); // 채팅
		jb[9].setBounds(940, 460, 100, 30);	// 노트패드
		
		for (int i=0; i < 8; i++) {
			jb[i+2].setEnabled(false);
		}
		
		// 본 윈도우(프로그램)의 세팅
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(200, 150, 1090, 680);
		this.setResizable(false);
		this.setVisible(true);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		String jbCaption = e.getActionCommand();
		
	
		if (jb[0] == obj){
			System.out.println("jbCaption >>> : " + jbCaption);
			card.show(jpM, "홈");
		}
		if (jb[1] == obj){
			System.out.println("jbCaption >>> : " + jbCaption);
			card.show(jpM, "회원가입");
			
			if (true) {
				for (int i=0; i < 8; i++) {
					jb[i+2].setEnabled(true);
				}
			}
		}
		if (jb[2] == obj){
			System.out.println("jbCaption >>> : " + jbCaption);
			card.show(jpM, "회원수정");
		}	
		if (jb[3] == obj){
			System.out.println("jbCaption >>> : " + jbCaption);
			card.show(jpM, "회원조회");		
		}	
		if (jb[4] == obj){
			System.out.println("jbCaption >>> : " + jbCaption);
			card.show(jpM, "글등록");		
		}		
		if (jb[5] == obj){
			System.out.println("jbCaption >>> : " + jbCaption);
			card.show(jpM, "글수정");
		}
		if (jb[6] == obj){
			System.out.println("jbCaption >>> : " + jbCaption);
			card.show(jpM, "글조회");
		}	
		if (jb[7] == obj){
			System.out.println("jbCaption >>> : " + jbCaption);
			card.show(jpM, "계산기");
			new CalculatorGUI();
		}
		if (jb[8] == obj){
			System.out.println("jbCaption >>> : " + jbCaption);
			card.show(jpM, "채팅");
		}
		if (jb[9] == obj){
			System.out.println("jbCaption >>> : " + jbCaption);
			card.show(jpM, "편집기");
//			new JEditor();

		}			
		
		// 채팅 버튼
		if (jbChat[0] == obj){
			System.out.println("jbCaption >>> : " + jbCaption);				
		}
		if (jbChat[1] == obj){
			System.out.println("jbCaption >>> : " + jbCaption);		
		}
		if (jbChat[2] == obj){
			System.out.println("jbCaption >>> : " + jbCaption);					
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new KosmoScr();
	}
}
