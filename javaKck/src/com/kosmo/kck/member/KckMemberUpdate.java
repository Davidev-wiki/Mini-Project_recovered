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

public class KckMemberUpdate extends JFrame implements ActionListener {
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
	public KckMemberUpdate() {

		// JFrame 타이틀 세팅
		this.setTitle("수정/삭제");

		// JFrame의 레이아웃 매니저 : 'Border'로 설정
		this.getContentPane().setLayout(null);
		jp = new JPanel[2];
		jp[0] = new JPanel();
		jp[0].setBorder(new EtchedBorder());
		jp[0].setBounds(0, 0, 420, 880);
		jp[0].setBackground(Color.cyan);
		jp[0].setLayout(null);

		// 콤보박스, 라디오버튼, 텍스트필드, 라벨 초기화
		// 콤보박스 초기화 : why 5?
		jc = new JComboBox[5];

		// 콤보박스 : 생년월일에서 월, 일
		jcBirth = new JComboBox[5];

		// 라이오버튼 : 여자, 남자
		jr = new JRadioButton[2];

		// 텍스트필드
		jtField = new JTextField[6];
		jt = new JTextField[17];
		int ty = 80;
		for (int i = 0; i < jt.length; i++) {

			if (8 == i || 10 == i || 13 == i) {
				System.out.println(">>>>>>>> " + i);
				// 이메일
				if (8 == i) {

					jtField[4] = new JTextField(20);
					jtField[4].setBackground(Color.white);
					jtField[4].setBounds(130, 400, 100, 30);

					JLabel jla = new JLabel("@");
					jla.setOpaque(true);
					jla.setBackground(Color.cyan);
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
			ty += 40;
		}

		// 라벨 초기화
		jl = new JLabel[17];
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
		jb = new JButton[3];
		for (int i = 0; i < jb.length; i++) {
			jb[i] = new JButton();
			jb[i].addActionListener(this);
			jp[0].add(jb[i]);
		}

		jb[0].setText("수정"); // -----------------------------------------------------updated
		jb[0].setBounds(100, 780, 100, 30);
		jb[0].setFont(new Font("맑은고딕", Font.BOLD, 15));

		jb[1].setText("삭제"); // -----------------------------------------------------updated
		jb[1].setBounds(220, 780, 100, 30);
		jb[1].setFont(new Font("맑은고딕", Font.BOLD, 15));

		// JTextFiled disable : 삭제여부, 등록일, 수정일
		// //-----------------------------------------------------updated
		// 8, 10, 13번 텍스트필드는 위에서 별도 처리.
		jt[0].setEditable(false); // 회원번호
		jt[1].setEditable(true); // 회원이름
		jt[2].setEditable(true); // 아이디
		jt[3].setEditable(true); // 패스워드
		jt[4].setEditable(true); // 생년월일
		jt[5].setEditable(true); // 성별
		jt[6].setEditable(true); // 전화번호
		jt[7].setEditable(true); // 휴대폰번호

		jt[11].setEditable(true); // 이메일
		jt[12].setEditable(true); // 주소

		jt[14].setEditable(false); // 삭제여부
		jt[15].setEditable(false); // 등록일
		jt[16].setEditable(false); // 수정일

		// JPanel JFrame 붙이기
		this.getContentPane().add(jp[0]);

		this.setSize(420, 880);
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

	// 함수
	// 회원 아이디 중복 체크기능
	public void kmemIdCheck(String kid) {
		System.out.println("KckMemberUpdate.kmemIdCheck()시작 (중복체크) : ");

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
				jt[2].setEditable(false);
			}

		} catch (Exception e) {
			System.out.println("에러발생! : " + e.getMessage());
		}
	}

	// 회원 등록 기능 : 현재 프로그램에 있으나 마나한 기능
	public void kmemInsert(String kname, String kid, String kpw, String kbirth, String kgender, String ktel, String khp,
			String kemail, String kaddr, String khobby, String kphoto, String kskill, String kjob) {

		System.out.println("KckMemberUpdate.kmemInsert()진입");

		// VO 클래스에 회원정보 세팅해서 서비스로 보내기
		// 회원넘버는 채번함수를 이용해서 세팅한다.
		try {
			KckMemberService kms = new KckMemberServiceImpl();
			KckMemberVO kvo = new KckMemberVO();

			String knum = KckMemberChabun.ymdNum();
			System.out.println("생성된 회원번호 : " + knum);
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

	// 회원 조회 기능 : 현재 프로그램에 있으나 마나한 기능
	public void kmemSelect(String knum) {

		System.out.println("KckMemberUpdate.kmemSelect() 진입");

		KckMemberService kms = new KckMemberServiceImpl();
		KckMemberVO kvo = new KckMemberVO();
		kvo.setKnum(knum);

		ArrayList<KckMemberVO> aList = kms.kmemselect(kvo);

		// 데이터가 잘 들어왔나 체크
		if (aList != null && aList.size() > 0) {

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

			if ("01".equals(kgender)) {
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

	// 회원 수정 기능
	public void kmemUpdate(String kname, String kid, String kpw, String kbirth, String kgender, String ktel, String khp,
			String kemail, String kaddr, String khobby, String kjob) {

		System.out.println("KckMemberUpdate.kmemUpdate() 진입");

		/*
		 * 수정 가능하게 해줄 필드
		 * 
		 * jt[1].setEditable(true); // 회원이름 jt[2].setEditable(true); // 아이디
		 * jt[3].setEditable(true); // 패스워드 jt[4].setEditable(true); // 생년월일
		 * jt[5].setEditable(true); // 성별 jt[6].setEditable(true); // 전화번호
		 * jt[7].setEditable(true); // 휴대폰번호
		 * 
		 * jt[11].setEditable(true); // 이메일 jt[12].setEditable(true); // 주소
		 * 
		 */

		KckMemberService kms = new KckMemberServiceImpl();
		KckMemberVO kvo = new KckMemberVO();

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
		kvo.setKjob(kjob);

		boolean bool = kms.kmemUpdate(kvo);

		if (bool) {
			System.out.println("회원 수정 성공!" + bool);
			JOptionPane.showMessageDialog(this, "회원정보 수정 성공! ");
			new KckMemberAll();
		} else {
			System.out.println("회원 수정 실패했어요.." + bool);
		}
	}

	// 회원 삭제 기능
	public void kmemDelete(String kid) {
		System.out.println("KckMemberUpdate.kmemDelete() 진입");

		KckMemberService kms = new KckMemberServiceImpl();
		KckMemberVO kvo = new KckMemberVO();

		kvo.setKid(kid);

		boolean bool = kms.kmemDelete(kvo);

		if (bool) {
			System.out.println("회원 삭제 성공!" + bool);
			JOptionPane.showMessageDialog(this, "회원 삭제 성공! ");
			new KckMemberAll();
		} else {
			System.out.println("회원 삭제에 실패했어요.." + bool);
		}
	}

	// 텍스트필드의 값 초기화
	public void valueClear() {

		System.out.println("KckMemberUpdate.valueClear() 진입");

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

		System.out.println("KckMemberUpdate.actionPerformed() 진입");

		String jbCaption = e.getActionCommand();
		// 가져온 이벤트가 버튼의 0번째(='중복확인')인 경우
		if (jb[0] == e.getSource()) {
			System.out.println("수정 버튼 클릭 >>> : " + jbCaption);
			// 회원번호

			String kname = jt[1].getText();
			String kid = jt[2].getText();
			String kpw = jt[3].getText();
			String kbirth = jt[4].getText();
			String kgender = jt[5].getText();
			String ktel = jt[6].getText();
			String khp = jt[7].getText();
			// 이메일
			String kemail = "";
			kemail = jtField[4].getText();
			kemail = kemail.concat("@");
			kemail = kemail.concat(jtField[5].getText());
			// 주소
			String kaddr = jt[9].getText();
			// 취미
			String khobby = "";
			khobby = CodeUtil.setHobby(String.valueOf(jc[3].getSelectedItem()));
			// 직업
			String kjob = "";
			kjob = CodeUtil.setJob(String.valueOf(jc[4].getSelectedItem()));

			this.kmemUpdate(kname, kid, kpw, kbirth, kgender, ktel, khp, kemail, kaddr, khobby, kjob);
		}
		if (jb[1] == e.getSource()) {
			System.out.println("삭제 버튼 클릭 >>> : " + jbCaption);

			// 회원번호
			String kid = jt[2].getText();
			System.out.println("kid >>> : " + kid);

			this.kmemDelete(kid);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("KckMemberUpdate.main()함수 시작");
		new KckMemberUpdate();
	}

}
