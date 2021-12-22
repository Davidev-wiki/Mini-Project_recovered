package com.kosmo.kck.member;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import com.kosmo.kck.common.CodeUtil;
import com.kosmo.kck.common.DateUtil;
import com.kosmo.kck.common.KckMemberChabun;

public class KckMember extends JFrame implements ActionListener {

	// 상수
	// 소문자로 시작해도 상수인가? 아니면 다른 종류?
	private static final long serialVersionUID = 9072198881429763467L;

	// 멤버 변수
	private JLabel jl[];
	private JTextField jt[];
	private JTextField jtField[];
	private JButton jb[];
	private JPanel jp[];
	private JComboBox<String> jc[];
	private JComboBox<String> jcBirth[];
	private JRadioButton jr[];

	// 생성자 : 기본 생성자. 어노테이션 = ?
	@SuppressWarnings("Unchecked")
	public KckMember() {

		// JFrame 타이틀 세팅
		this.setTitle("회원 가입");

		// JFrame의 레이아웃 매니저 : 'Border'로 설정
		this.getContentPane().setLayout(null);
		jp = new JPanel[2];
		jp[0] = new JPanel();
		jp[0].setBorder(new EtchedBorder());
		jp[0].setBounds(0, 0, 620, 880);
		jp[0].setBackground(Color.orange);
		jp[0].setLayout(null);

		// 콤보박스, 라이오버튼, 텍스트필드, 라벨 초기화
		// 콤보박스1 : 5개, 생년월일에서 년도, 전화번호, 휴대폰번호, 취미, 직업 용도
		jc = new JComboBox[5];

		// 콤보박스2 : 생년월일에서 월, 일 용도 (5개-> 2개만 만들어도 상관없는 듯 해서 바꿈)
		jcBirth = new JComboBox[2];

		// 라이오버튼 : 여자, 남자
		jr = new JRadioButton[2];

		// 텍스트필드
		jtField = new JTextField[6];
		jt = new JTextField[17];
		
		// y축 위치 조절 변수 
		int ty = 80;
		
		// 배열의 길이인 17만큼 반복
		for (int i = 0; i < jt.length; i++) {
			
			//
			if (4 == i || 5 == i || 6 == i || 7 == i || 8 == i || 10 == i || 13 == i) {
				System.out.println("KckMember.텍스트필드 세팅 초기화중 : " + i);
				// 생년월일 : 콤보박스
				if (4 == i) {
					// 연도
					String[] years = DateUtil.comboYear();
					jc[0] = new JComboBox<String>(years);
					jp[0].add(jc[0]);
					jc[0].setBackground(Color.white);
					jc[0].setSelectedIndex(0);
					jc[0].setBounds(130, 240, 80, 30);
					
					// 월
					String[] months = DateUtil.comboMonth();
					jcBirth[0] = new JComboBox<String>(months);
					jp[0].add(jcBirth[0]);
					jcBirth[0].setBackground(Color.white);
					jcBirth[0].setSelectedIndex(0);
					jcBirth[0].setBounds(220, 240, 65, 30);

					// 일
					String[] days = DateUtil.comboDay();
					jcBirth[1] = new JComboBox<String>(days);
					jp[0].add(jcBirth[1]);
					jcBirth[1].setBackground(Color.white);
					jcBirth[1].setSelectedIndex(0);
					jcBirth[1].setBounds(295, 240, 65, 30);
				}
				// 성별 : 라디오버튼
				if (5 == i) {
					ButtonGroup bg = new ButtonGroup();
					
					JPanel jpGender = new JPanel();
					jpGender.setLayout(new FlowLayout(FlowLayout.LEADING));
					jpGender.setBackground(Color.white);
					jpGender.setBounds(130, 280, 140, 31);
					for (int j = 0; j < jr.length; j++) {
						jr[j] = new JRadioButton(CodeUtil.gender_value[j]);
						jr[j].addActionListener(this);
						jr[j].setBackground(Color.white);
						bg.add(jr[j]);
						jpGender.add(jr[j]);
						jp[0].add(jpGender);
					}
					jr[0].setSelected(true);
					jr[1].setSelected(false);
				}
				// 전화번호
				if (6 == i) {
					jc[1] = new JComboBox<String>(CodeUtil.tel_value);
					jp[0].add(jc[1]);
					jc[1].setBackground(Color.white);
					jc[1].setSelectedIndex(0);
					jc[1].setBounds(130, 320, 60, 30);

					jtField[0] = new JTextField(6);
					jtField[0].setBackground(Color.white);
					jtField[0].setBounds(200, 320, 60, 30);
					jtField[1] = new JTextField(6);
					jtField[1].setBackground(Color.white);
					jtField[1].setBounds(270, 320, 60, 30);

					jp[0].add(jtField[0]);
					jp[0].add(jtField[1]);
				}
				// 휴대폰번호
				if (7 == i) {
					jc[2] = new JComboBox<String>(CodeUtil.hp_value);
					jp[0].add(jc[2]);
					jc[2].setBackground(Color.white);
					jc[2].setSelectedIndex(0);
					jc[2].setBounds(130, 360, 60, 30);

					jtField[2] = new JTextField(6);
					jtField[2].setBackground(Color.white);
					jtField[2].setBounds(200, 360, 60, 30);
					jtField[3] = new JTextField(6);
					jtField[3].setBackground(Color.white);
					jtField[3].setBounds(270, 360, 60, 30);

					jp[0].add(jtField[2]);
					jp[0].add(jtField[3]);
				}
				// 이메일
				if (8 == i) {

					jtField[4] = new JTextField(20);
					jtField[4].setBackground(Color.white);
					jtField[4].setBounds(130, 400, 100, 30);

					JLabel jla = new JLabel("@");
					jla.setOpaque(true);
					jla.setBackground(Color.white);
					jla.setBounds(235, 400, 20, 30);

					jtField[5] = new JTextField(6);
					jtField[5].setBackground(Color.white);
					jtField[5].setBounds(260, 400, 100, 30);

					jp[0].add(jtField[4]);
					jp[0].add(jla);
					jp[0].add(jtField[5]);
				}
				// 취미
				if (10 == i) {
					jc[3] = new JComboBox<String>(CodeUtil.hobby_label);
					jp[0].add(jc[3]);
					jc[3].setBackground(Color.white);
					jc[3].setSelectedIndex(0);
					jc[3].setBounds(130, 480, 80, 30);
				}
				// 직업
				if (13 == i) {
					jc[4] = new JComboBox<String>(CodeUtil.job_label);
					jp[0].add(jc[4]);
					jc[4].setBackground(Color.white);
					jc[4].setSelectedIndex(0);
					jc[4].setBounds(130, 600, 80, 30);
				}
			} else {
				jt[i] = new JTextField(20);
				jt[i].setBounds(130, ty, 140, 30);
				jp[0].add(jt[i]);
			}
			// y축 위치 조절 변수 
			ty += 40;
		}

		// 라벨 초기화
		jl = new JLabel[17];
		// y축 위치 조절 변수 
		int ly = 80;
		for (int i = 0; i < jl.length; i++) {
			jl[i] = new JLabel();
			jl[i].setOpaque(true);
			jl[i].setText(CodeUtil.member_value[i]);
			jl[i].setHorizontalAlignment(SwingConstants.CENTER);
			jl[i].setFont(new Font("맑은고딕", Font.BOLD, 15));
			jl[i].setBounds(20, ly, 100, 30);
			ly += 40;
			jp[0].add(jl[i]);
		}

		// 회원가입 라벨
		JLabel jlM = new JLabel();
		jlM.setText("회원가입");
		jlM.setHorizontalAlignment(SwingConstants.CENTER);
		jlM.setFont(new Font("맑은고딕", Font.BOLD, 20));
		jlM.setBounds(20, 20, 362, 40);
		jp[0].add(jlM);

		// 버튼
		jb = new JButton[5];
		for (int i = 0; i < jb.length; i++) {
			jb[i] = new JButton();
			jb[i].addActionListener(this);
			jp[0].add(jb[i]);
		}

		jb[0].setText("중복확인");
		jb[0].setBounds(280, 160, 110, 30);
		jb[0].setFont(new Font("맑은고딕", Font.BOLD, 15));

		jb[1].setText("가입하기");
		jb[1].setBounds(20, 780, 100, 30);
		jb[1].setFont(new Font("맑은고딕", Font.BOLD, 15));

		jb[2].setText("초기화");
		jb[2].setBounds(180, 780, 100, 30);
		jb[2].setFont(new Font("맑은고딕", Font.BOLD, 15));

		jb[3].setText("수정하기");
		jb[3].setBounds(340, 780, 100, 30);
		jb[3].setFont(new Font("맑은고딕", Font.BOLD, 15));

		jb[4].setText("삭제하기");
		jb[4].setBounds(490, 780, 100, 30);
		jb[4].setFont(new Font("맑은고딕", Font.BOLD, 15));

		// JTextFiled disable : 삭제여부, 등록일, 수정일
		jt[0].setEditable(false);
		jt[14].setEditable(false);
		jt[15].setEditable(false);
		jt[16].setEditable(false);

		// JPanel JFrame 붙이기
		this.getContentPane().add(jp[0]);

		this.setSize(620, 870);
		this.setLocation(200, 100);
		this.setResizable(true);
		this.setVisible(true);

		// JFrame 닫기
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				e.getWindow().setVisible(false);
				e.getWindow().dispose();
			}
		});
	}

	// 함수
	// 회원 아이디 중복 체크기능
	public void kmemIdCheck(String kid) {
		System.out.println("KckMember.kmemIdCheck()시작 (중복체크) : ");

		try {
			// 입력값을 받고 (String kid)
			// 디비에서 조회 (VO클래스로 전달)
			// 입력값이 디비에서 조회된 값이 있으면 에러발생
			// 디비에서 조회한 결과가 없으면 사용가능

			KckMemberService kms = new KckMemberServiceImpl();
			KckMemberVO kvo = new KckMemberVO();
			kvo.setKid(kid);

			boolean bool = kms.kmemIdCheck(kvo);

			if (bool) {
				System.out.println("중복된 아이디입니다!" + bool);
				JOptionPane.showMessageDialog(this, "같은 아이디가 있습니다  >>> :  ");
				jt[2].setText("");
			} else {
				System.out.println("사용 가능한 아이디입니다!" + bool);
				JOptionPane.showMessageDialog(this, "사용가능한 아이디 입니다 >>> :  ");
				jt[2].setText(kvo.getKid());
				jt[2].setEditable(true);
			}

		} catch (Exception e) {
			System.out.println("에러발생! : " + e.getMessage());
		}
	}

	// 회원 등록 기능
	public void kmemInsert(String kname, String kid, String kpw, String kbirth, String kgender, String ktel, String khp,
			String kemail, String kaddr, String khobby, String kphoto, String kskill, String kjob) {

		System.out.println("KckMember.kmemInsert()진입");

		// VO 클래스에 회원정보 세팅해서 서비스로 보내기
		// 회원넘버는 채번함수를 이용해서 세팅한다.
		try {
			KckMemberService kms = new KckMemberServiceImpl();
			KckMemberVO kvo = new KckMemberVO();

			String knum = KckMemberChabun.ymdNum();
			System.out.println("생성된 회원번호 : " + knum);
			kvo.setKnum(knum);
			kvo.setKname(kname);
			kvo.setKid(kid);
			kvo.setKpw(kpw);
			kvo.setKbirth(kbirth);
			kvo.setKgender(kgender);
			kvo.setKtel(ktel);
			kvo.setKhp(khp);
			kvo.setKemail(kemail);
			kvo.setKaddr(kaddr);
			kvo.setKhobby(khobby);
			kvo.setKphoto(kphoto);
			kvo.setKskill(kskill);
			kvo.setKjob(kjob);
			kvo.printlnKckMemberVO(kvo);
			
			boolean bool = kms.kmemInsert(kvo);

			if (bool) {
				System.out.println("회원으로 등록되었습니다!" + bool);
				JOptionPane.showMessageDialog(this, "회원정보 등록 성공 >>> :  ");
				new KckMemberAll();
			} else {
				System.out.println("회원 등록에 실패했습니다.." + bool);
			}
		} catch (Exception e) {
			System.out.println("에러 발생! : " + e.getMessage());
		}

	}

	// 회원 조회 기능
	public void kmemSelect(String knum) {
		
		KckMemberService kms = new KckMemberServiceImpl();
		KckMemberVO kvo = new KckMemberVO();
		kvo.setKnum(knum);
		
		ArrayList<KckMemberVO> aList = kms.kmemselect(kvo);
		
		// 데이터가 잘 들어왔나 체크
		if(aList != null && aList.size() > 0) {
			
			// 데이터 전이
			KckMemberVO _kvo = aList.get(0);
			
			// 조회해서 VO를 이용해 가져온 데이터를
			// 텍스트필드에 세팅하기.
			jt[0].setText(_kvo.getKnum());
			jt[1].setText(_kvo.getKname());
			jt[2].setText(_kvo.getKid());
			jt[3].setText(_kvo.getKpw());
			jt[4].setText(_kvo.getKbirth());
			
			// 성별
			String kgender = _kvo.getKgender();
			
			if("01".equals(kgender)) {
				kgender = "여자";
			} else {
				kgender = "남자";
			}
			jt[5].setText(_kvo.getKgender());
			
			// 전화번호, 휴대폰 번호
			jt[6].setText(_kvo.getKtel());
			jt[7].setText(_kvo.getKhp());
			
			// 이메일, split으로 앞뒤로 잘라서
			// email 배열에 담아놓고 텍스트필드에 세팅
			String kemail = _kvo.getKemail();
		    String email[] = kemail.split("@");
		    jtField[0].setText(email[0]);
		    jtField[1].setText(email[1]);
			
		    // 주소
			jt[9].setText(_kvo.getKaddr());
			
			// 취미
			jc[3].setSelectedIndex(CodeUtil.getComboIndex(_kvo.getKhobby()));
			
			// 사진
			jt[11].setText(_kvo.getKphoto());
			
			// 특기
			jt[12].setText(_kvo.getKskill());

			// 직업
			jc[4].setSelectedIndex(CodeUtil.getComboIndex(_kvo.getKjob()));
		    
			// 삭제여부, 수정일, 업데이트일
			jt[14].setText(_kvo.getDeleteyn());
			jt[15].setText(_kvo.getInsertdate());
			jt[16].setText(_kvo.getUpdatedate());			
		}
		
	}

	// 회원 수정 기능 => 수정 가능 범위를 먼저 설정해야 하는데.. 시간이 없으니까 패스 
	public void kmemUpdate(String knum, String kemail, String kaddr, String khobby, String kjob) {
		new KckMemberAll();
	}

	// 회원 삭제 기능 => 게시판 새 창으로 연결하기 ==> 가입했던 이메일 입력시 삭제되도록 하기
	public void kmemDelete(String kemail) {
		new KckMemberAll();
	}

	// 텍스트필드의 값 초기화
	public void valueClear() {

		System.out.println("KckMember.valueClear() 진입");
		// jt.length : jt배열의 길이. 상단에 [17] 로 생성.
		// 
		for (int i = 0; i < jt.length; i++) {
			if (4 == i || 5 == i || 6 == i || 7 == i || 8 == i || 10 == i || 13 == i) {
				jc[0].setSelectedIndex(0);
				jcBirth[0].setSelectedIndex(0);
				jcBirth[1].setSelectedIndex(0);
				jr[0].setSelected(true);
				jr[1].setSelected(false);
				jc[1].setSelectedIndex(0);
				jtField[0].setText("");
				jtField[1].setText("");
				jc[2].setSelectedIndex(0);
				jtField[2].setText("");
				jtField[3].setText("");
				jtField[4].setText("");
				jtField[5].setText("");
				jc[3].setSelectedIndex(0);
				jc[4].setSelectedIndex(0);
			} else {
				jt[i].setText("");
			}
			// Deleted "jt[2].setEditable(true);" by updating. 
		}
	}

	// 오버로딩한 함수
	@Override
	public void actionPerformed(ActionEvent e) {

		System.out.println("KckMember.actionPerformed() 진입");

		// 이벤트를 가져와서 오브젝트에 초기화함.
		Object obj = e.getSource();
		Object jbCaption = e.getActionCommand();

		// 가져온 이벤트가 버튼의 0번째(='중복확인')인 경우
		if (jb[0] == obj) {
			System.out.println("중복확인 버튼이 선택되었습니다. : " + jbCaption);
			// 아이디 값 입력할 수 있는 창 소환
			// 입력된 값을 가져와서 변수에담아
			// 회원 아이디중복확인 함수로 보낸다.
			String kid = jt[2].getText();
			this.kmemIdCheck(kid);
		}
		// 가져온 이벤트가 버튼의 1번째(='가입하기')인 경우
		if (jb[1] == obj) {
			System.out.println("가입하기 버튼이 선택되었습니다. : " + jbCaption);
			// 가입 창 소환
			// 텍스트필드에 입력된 문자열을 변수에 담는다.
			String kname = jt[1].getText();
			String kid = jt[2].getText();
			String kpw = jt[3].getText();

			// 생년월일 (문자숫자를 연결하는 작업)
			String kbirth = "";
			kbirth = String.valueOf(jc[0].getSelectedItem());
			kbirth = kbirth.concat(String.valueOf(jcBirth[0].getSelectedItem()));
			kbirth = kbirth.concat(String.valueOf(jcBirth[1].getSelectedItem()));

			// 성별 (라디오버튼 선택에 따라 밸류 부여)
			String kgender = "";
			if (jr[0].isSelected()) {
				System.out.println("선택된 성별 : " + jr[0].getText());
				kgender = "01";
			} else {
				System.out.println("선택된 성별 : " + jr[1].getText());
				kgender = "02";
			}

			// 전화번호 (텍스트필드 입력값을 연결)
			String ktel = "";
			ktel = String.valueOf(jc[1].getSelectedItem());
			ktel = ktel.concat(jtField[0].getText());
			ktel = ktel.concat(jtField[1].getText());

			// 핸드폰 번호 (텍스트필드 입력값을 연결)
			String khp = "";
			khp = String.valueOf(jc[2].getSelectedItem());
			khp = khp.concat(jtField[2].getText());
			khp = khp.concat(jtField[3].getText());

			// 이메일 (텍스트필드 입력값을 @문자로 연결)
			String kemail = "";
			kemail = jtField[4].getText();
			kemail = kemail.concat("@");
			kemail = kemail.concat(jtField[5].getText());

			// 주소
			String kaddr = jt[9].getText();

			// 취미 
			// 배열에 담긴 밸류 세팅하기 : setHobby 함수 호출. 
			// '문자열'을 로직에 따라 '문자 숫자'로 바꾼다.
			String khobby = "";
			khobby = CodeUtil.setHobby(String.valueOf(jc[3].getSelectedItem()));

			// 사진
			String kphoto = jt[11].getText();

			// 특기
			String kskill = jt[12].getText();

			// 직업 
			// 배열에 담긴 밸류 세팅하기 : setJob 함수 호출. 
			// '문자열'을 로직에 따라 '문자 숫자'로 바꾼다.
			String kjob = "";
			kjob = CodeUtil.setJob(String.valueOf(jc[4].getSelectedItem()));

			// 출력해보기
			System.out.println("kname : " + kname);
			System.out.println("kid : " + kid);
			System.out.println("kpw : " + kpw);
			System.out.println("kbirth : " + kbirth);
			System.out.println("kgender : " + kgender);
			System.out.println("ktel : " + ktel);
			System.out.println("khp : " + khp);
			System.out.println("kemail : " + kemail);
			System.out.println("kaddr : " + kaddr);
			System.out.println("khobby : " + khobby);
			System.out.println("kphoto : " + kphoto);
			System.out.println("kskill : " + kskill);
			System.out.println("kjob : " + kjob);

			// kmemInsert()함수 실행하기.
			this.kmemInsert(kname, kid, kpw, kbirth, kgender, ktel, khp, kemail, kaddr, khobby, kphoto, kskill, kjob);
		}

		// 가져온 이벤트가 버튼의 2번째(='다시')인 경우
		if (jb[2] == obj) {
			System.out.println("다시 버튼이 선택되었습니다. : " + jbCaption);
			// 텍스트필드 초기화 함수 실행
			this.valueClear();
		}

		// 가져온 이벤트가 버튼의 3번째(='수정하기')인 경우
		if (jb[3] == obj) {
			System.out.println("수정하기 버튼이 선택되었습니다. : " + jbCaption);
			// 전체조회 창에서 '수정/삭제' 버튼을 눌러서 수정하는 로직.
			// 전체 조회 창 팝업
			new KckMemberAll();
		}

		// 가져온 이벤트가 버튼의 4번째(='삭제하기')인 경우
		if (jb[4] == obj) {
			System.out.println("삭제하기 버튼이 선택되었습니다. : " + jbCaption);
			// 전체조회 창에서 '수정/삭제' 버튼을 눌러서 삭제하는 로직.
			// 전체 조회 창 팝업
			new KckMemberAll();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("KckMember.main()함수 시작");
		// 회원 가입 창 팝업
		new KckMember();
	}

}
