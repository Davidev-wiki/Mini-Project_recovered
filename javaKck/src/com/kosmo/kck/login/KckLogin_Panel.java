package com.kosmo.kck.login;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import com.kosmo.kck.member.KckMember;
import com.kosmo.kck.member.KckMemberVO;

public class KckLogin_Panel extends JPanel implements ActionListener {

	
	private static final long serialVersionUID = -152326483017758029L;

	JPanel 			jp;
	JLabel[]        jlM;
	JTextField      jtM;
	JPasswordField  jpfM;
	JButton[]       jbM;
	JButton         jbMem;
	
	// 생성자 
	public KckLogin_Panel() {
				
		jp = new JPanel();
		jp.setBackground(Color.orange);
		jp.setBorder(new EtchedBorder());
		jp.setBounds(0, 0, 310, 280);
		
		jp.setLayout(null);
		
		JPanel jpM = new JPanel();
		jpM.setLayout(null);
		jpM.setBounds(10, 30, 275, 180);
		jpM.setBackground(Color.orange);
		jpM.setBorder(new TitledBorder("로그인"));
		jp.add(jpM);
	
		jlM  = new JLabel[2];
		jtM  = new JTextField();
		jpfM = new JPasswordField();
		jbM  = new JButton[2];

		for (int j=0; j < jlM.length; j++ ){
			jlM[j] = new JLabel();
			jlM[j].setHorizontalAlignment(SwingConstants.CENTER);
			jbM[j] = new JButton();
			jbM[j].addActionListener(this);
			jpM.add(jlM[j]);
			jpM.add(jbM[j]);
		}
		jlM[0].setText("아이디");
		jlM[1].setText("패스워드");
		
		jtM  = new JTextField(10);			
		jpfM = new JPasswordField(10);
		jpM.add(jtM);
		jpM.add(jpfM);

		jbM[0].setText("로그인");
		jbM[1].setText("로그아웃");
		
		jbMem = new JButton();
		jbMem.addActionListener(this);
		jbMem.setText("회원가입");
		jpM.add(jbMem);

		jlM[0].setBounds(10, 20, 100, 30);
		jtM.setBounds(110, 20, 150, 30);
		
		jlM[1].setBounds(10, 55, 100, 30);
		jpfM.setBounds(110, 55, 150, 30);
		
		jbM[0].setBounds(40, 92, 100, 30);
		jbM[1].setBounds(150, 92, 100, 30);
		jbMem.setBounds(40, 130, 210, 30);
	
		this.add(jp);
	
		this.setSize(310, 280);	
		this.setVisible(true);
	}
		
	// 로그인 체크
	public void kLoginCheck(String kid, String kpw) {
		System.out.println("KckLogin :: kLoginCheck() 시작 >>> : ");
		
		try {
			
			KckLoginService kls = new KckLoginServiceImpl();
			KckMemberVO kvo = null;
			kvo = new KckMemberVO();
			kvo.setKid(kid);
			kvo.setKpw(kpw);
			
			int nCnt = kls.kLoginCheck(kvo);
			
			if (nCnt == 1) {
				System.out.println("아이디 :: " + kvo.getKid() + " 님 로그인 성공 !! ");
				
				JOptionPane.showMessageDialog(this, kvo.getKid() + " 님 로그인 성공  !! ");
				
				this.jtMSetText();
			}else {
				System.out.println("아이디 :: " + kvo.getKid() + " 님 로그인 실패 !! ");
				JOptionPane.showMessageDialog(this, kvo.getKid() + " 님 로그인 실패  !! ");
				this.jtMSetText();
			}
		}catch(Exception ex) {
			System.out.println("로그인처리 중 에러가 >>> : " + ex.getMessage());
		}	
	}	
	
	// 로그아웃 체크
	public void hLogOutCheck() {
		System.out.println("KckLogin :: hLogOutCheck() 시작 >>> : ");
		
		String message = "로그아웃 하시겠습니까 ? ";
		int conFirm = 0;
		try {
			
			conFirm = JOptionPane.showConfirmDialog(this, message);
			
			if (conFirm == JOptionPane.YES_OPTION) {
				this.setVisible(false);			
			} else if (conFirm == JOptionPane.NO_OPTION) {			
			}			
		}catch(Exception ex) {
			System.out.println("로그아웃 처리 중 에러가 >>> : " + ex.getMessage());
		}	
	}
	
	// 회원가입 호출
	public void kmMember() {
		System.out.println("SwingMemberLogin :: smMember() 시작 >>> : ");
		
		try {			
			new KckMember();
		}catch(Exception ex) {
			System.out.println("로그아웃 처리 중 에러가 >>> : " + ex.getMessage());
		}	
	}
	
	// 로그인 JTextField, JPasswordField 초기화 
	public void jtMSetText(){
		jtM.setText("");
		jpfM.setText("");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton jbnt = (JButton)e.getSource();
		String loginCmd = jbnt.getText();

		if ("로그인".equals(loginCmd)){
			System.out.println("loginCmd >>> : " + loginCmd + " 시작 >>> : ");
			
			String kid = "";
			String kpw = "";
			
			kid = jtM.getText();
			//kpw = jpfM.getText();
			char[] kpwChar = jpfM.getPassword();
			kpw = new String(kpwChar);
			System.out.println("kid >>> : " + kid);
			System.out.println("kpw >>> : " + kpw);
			
			this.kLoginCheck(kid, kpw);
		}
		if ("로그아웃".equals(loginCmd)){
			System.out.println("loginCmd >>> : " + loginCmd + " 시작 >>> : ");	
			this.hLogOutCheck();
		}
		if ("회원가입".equals(loginCmd)){
			System.out.println("loginCmd >>> : " + loginCmd + " 시작 >>> : ");	
			this.kmMember();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new KckLogin_Panel();

	}

}
