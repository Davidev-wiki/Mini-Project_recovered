package com.kosmo.kck.common;

public abstract class CodeUtil {

	public static final String[] member_value = { "회원번호", "회원이름", "아이디", "패스워드", "생년월일", "성별", "전화번호", "휴대폰번호", "이메일",
			"주소", "취미", "사진", "특기", "직업", "삭제여부", "등록일", "수정일" };

	public static final String[] member_selectall_value = { "회원번호", "회원이름", "아이디", "패스워드", "생년월일", "성별", "전화번호",
			"휴대폰번호", "이메일", "주소", "취미", "사진", "특기", "직업", "Y/N", "등록일", "수정일", "수정/삭제" };

	// 성별 옵션
	public static final String[] gender_value = { "여자", "남자" };

	// 전화 번호 옵션
	public static final String[] tel_value = { "02", "031", "032" };

	// 휴대폰번호 옵션
	public static final String[] hp_value = { "010", "112", "119" };

	// 취미 라벨에 해당하는 키 & 밸류
	public static final String[] hobby_label = { "게임", "음악감상", "운동", "독서", "영화", "명상" };
	public static final String[] hobby_value = { "01", "02", "03", "04", "05", "06" };

	// 직업 라벨에 해당하는 키 & 밸류
	public static final String[] job_label = { "회사원", "자영업", "학생", "주부", "무직", "시인" };
	public static final String[] job_value = { "01", "02", "03", "04", "05", "06" };

	// ----------- 회원 전체 조회 ----------
	// 회원 조회 라벨
	public static final String[] combo_caption = { "----------", "전체", "이름", "아이디" };
	public static final String[] jbtn_caption = { "회원등록", "회원목록" };

	// ---------- 게시판 ----------
	// 게시판 정보 라벨
	public static final String[] board_label = {"글번호", "글제목", "작성자", "비밀번호", "글내용"};
	public static final String[] board_selectall_label = {"글번호", "글제목", "작성자", "비밀번호", "글내용", 
														  "Y/N", "등록일", "수정일", "수정/삭제"};
	
	public static final String[] board_combo_caption = {"----------", "전체", "글제목", "작성자"};
	public static final String[] board_jbtn_caption = {"작성하기", "글목록"};	
	
	
	// ---------- 함수 ----------
	// 생년 월일을 아래 형태로 변환하기.
	// 입력되는 문자 : 19881215
	// 변경할 형태 : 1988-12-15
	public static String birth(String s) {

		String s0 = "";
		String s1 = "";
		String s2 = "";
		String s3 = "";

		// null & 값 체크
		// 문자를 쪼개 각각 다른 변수에 담는다.
		if (s != null && s.length() > 0) {
			int sLen = s.length();
			if (8 == sLen) {
				s0 = s.substring(0, 4);
				s1 = s.substring(4, 6);
				s2 = s.substring(6);
				s3 = s0 + "-" + s1 + "-" + s2;
			}
		}

		return s3;
	}

	// 입력된 문자가 '01'인 경우 '여자', '02'인 경우 남자가 출력.
	public static String gender(String s) {

		String gender = "";

		if (s != null & s.length() > 0) {
			if ("01".equals(s)) {
				gender = "여자";
			} else if ("02".equals(s)) {
				gender = "남자";
			}
		}

		return gender;
	}

	// 전화번호 문자 형태 변환하기
	// 입력되는 문자 : 0226038636 or 03112345678
	// 변환 후 문자 : 02-2603-8636 / 031-1234-5678
	public static String tel(String s) {

		String s0 = "";
		String s1 = "";
		String s2 = "";
		String s3 = "";

		if (s != null && s.length() > 0) {

			int sLen = s.length();

			if (sLen == 10) {
				s0 = s.substring(0, 2);
				s1 = s.substring(2, 6);
				s2 = s.substring(6);
				s3 = s0 + "-" + s1 + "-" + s2;
			} else if (sLen == 11) {
				s0 = s.substring(0, 3);
				s1 = s.substring(3, 7);
				s2 = s.substring(7);
				s3 = s0 + "-" + s1 + "-" + s2;
			}
		}

		return s3;
	}
	
	public static String hp(String s) {
		
		String s0 = "";
		String s1 = "";
		String s2 = "";
		String s3 = "";
		
		if(s != null && s.length() > 0) {
			int sLen = s.length();
			
			if(11 == sLen) {
				s0 = s.substring(0, 4);
				s1 = s.substring(4, 8);
				s2 = s.substring(8);
				s3 = s0 + "-" + s1 + "-" + s2;
			}
		}
		
		return s3;
	}
	

	// 입력된 값 s 는 문자 숫자의 형태('01', '02', '03' ..의 형태일 것)
	public static String hobby(String s) {

		String h = "";

		// hobby_label 순서대로 돌면서
		// 밸류가 각각 01, 02, 03, 04 ...로 나오면
		// 해당하는 키를 갖고온다.(배열이 0번째 부터 시작되므로 'i-1')
		if (s != null && s.length() > 0) {
			for (int i = 1; i < hobby_label.length; i++) {
				if (("0" + i).equals(s)) {
					h = CodeUtil.hobby_label[i - 1];
				}
			}
		}
		return h;
	}

	// 문자로 입력된 취미(게임, 음악감상...)를
	// 번호('01', '02'...)로 바꾸는 함수.
	public static String setHobby(String s) {

		String sh = "";
		for (int i = 0; i < CodeUtil.hobby_label.length; i++) {
			if (CodeUtil.hobby_label[i].equals(s)) {
				sh = CodeUtil.hobby_value[i];
			}
		}
		return sh;
	}

	// 취미, 직업 밸류 세팅
	// 넘버에 따라 그에 맞는 밸류의 인덱스 값을 초기화한다.
	public static int getComboIndex(String s) {

		int ci = 0;
		if ("01".equals(s)) {
			ci = 0;
		}
		if ("02".equals(s)) {
			ci = 1;
		}
		if ("03".equals(s)) {
			ci = 2;
		}
		if ("04".equals(s)) {
			ci = 3;
		}
		if ("05".equals(s)) {
			ci = 4;
		}
		if ("06".equals(s)) {
			ci = 5;
		}

		return ci;
	}

	// 직업
	// 문자 숫자로 입력된 값(1, 2, 3...)
	// 직업 라벨의 인덱스와 매핑한다.
	public static String job(String s) {

		String j = "";
		if (s != null && s.length() > 0) {
			for (int i = 1; i < job_label.length; i++) {
				if (("0" + i).equals(s)) {
					j = CodeUtil.job_label[i - 1];
				}
			}
		}
		return j;
	}

	// 직업 밸류 세팅
	// 값이 들어오는 문자 형태 : "의사", "선생님" ..
	// 배열에 있는 번호 값{01, 02, 03..} 매칭해주기
	public static String setJob(String s) {

		String j = "";

		for (int i = 0; i < CodeUtil.job_label.length; i++) {
			if (CodeUtil.job_label[i].equals(s)) {
				j = CodeUtil.job_value[i];
			}
		}

		return j;
	}

	// 번호 형태 만드는 함수. '0001', '0002'...
	// 아직 이해못함 실행해보기.
	public static String numPad(String s) {

		int sLen = s.length();
		for (int i = sLen; i < 4; i++) {
			s = "0" + s;
		}
		return s;
	}

	public static void main(String args[]) {

		// 생년월일 테스트 출력
		String b = CodeUtil.birth("20210824");
		System.out.println("CodeUtil.birth()테스트 : " + b);

		// 전화번호 테스트 출력
		String t = CodeUtil.tel("0274101900");
		System.out.println("CodeUtil.tel(전화번호)테스트 : " + t);

		// 휴대폰번호 테스트 출력
		String hp = CodeUtil.tel("01074101900");
		System.out.println("CodeUtil.tel(휴대폰번호)테스트 : " + hp);

		// 성별 테스트 출력
		String g = CodeUtil.gender("02");
		System.out.println("CodeUtil.gender()테스트 : " + g);

		// 취미 테스트 출력
		String hb = CodeUtil.hobby("02");
		System.out.println("CodeUtil.hobby()테스트 : " + hb);

		// 직업 테스트 출력
		String job = CodeUtil.job("02");
		System.out.println("CodeUtil.job()테스트 : " + job);

	}
}
