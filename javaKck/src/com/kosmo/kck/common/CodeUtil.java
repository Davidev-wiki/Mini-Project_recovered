package com.kosmo.kck.common;

public abstract class CodeUtil {

	public static final String[] member_value = { "ȸ����ȣ", "ȸ���̸�", "���̵�", "�н�����", "�������", "����", "��ȭ��ȣ", "�޴�����ȣ", "�̸���",
			"�ּ�", "���", "����", "Ư��", "����", "��������", "�����", "������" };

	public static final String[] member_selectall_value = { "ȸ����ȣ", "ȸ���̸�", "���̵�", "�н�����", "�������", "����", "��ȭ��ȣ",
			"�޴�����ȣ", "�̸���", "�ּ�", "���", "����", "Ư��", "����", "Y/N", "�����", "������", "����/����" };

	// ���� �ɼ�
	public static final String[] gender_value = { "����", "����" };

	// ��ȭ ��ȣ �ɼ�
	public static final String[] tel_value = { "02", "031", "032" };

	// �޴�����ȣ �ɼ�
	public static final String[] hp_value = { "010", "112", "119" };

	// ��� �󺧿� �ش��ϴ� Ű & ���
	public static final String[] hobby_label = { "����", "���ǰ���", "�", "����", "��ȭ", "���" };
	public static final String[] hobby_value = { "01", "02", "03", "04", "05", "06" };

	// ���� �󺧿� �ش��ϴ� Ű & ���
	public static final String[] job_label = { "ȸ���", "�ڿ���", "�л�", "�ֺ�", "����", "����" };
	public static final String[] job_value = { "01", "02", "03", "04", "05", "06" };

	// ----------- ȸ�� ��ü ��ȸ ----------
	// ȸ�� ��ȸ ��
	public static final String[] combo_caption = { "----------", "��ü", "�̸�", "���̵�" };
	public static final String[] jbtn_caption = { "ȸ�����", "ȸ�����" };

	// ---------- �Խ��� ----------
	// �Խ��� ���� ��
	public static final String[] board_label = {"�۹�ȣ", "������", "�ۼ���", "��й�ȣ", "�۳���"};
	public static final String[] board_selectall_label = {"�۹�ȣ", "������", "�ۼ���", "��й�ȣ", "�۳���", 
														  "Y/N", "�����", "������", "����/����"};
	
	public static final String[] board_combo_caption = {"----------", "��ü", "������", "�ۼ���"};
	public static final String[] board_jbtn_caption = {"�ۼ��ϱ�", "�۸��"};	
	
	
	// ---------- �Լ� ----------
	// ���� ������ �Ʒ� ���·� ��ȯ�ϱ�.
	// �ԷµǴ� ���� : 19881215
	// ������ ���� : 1988-12-15
	public static String birth(String s) {

		String s0 = "";
		String s1 = "";
		String s2 = "";
		String s3 = "";

		// null & �� üũ
		// ���ڸ� �ɰ� ���� �ٸ� ������ ��´�.
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

	// �Էµ� ���ڰ� '01'�� ��� '����', '02'�� ��� ���ڰ� ���.
	public static String gender(String s) {

		String gender = "";

		if (s != null & s.length() > 0) {
			if ("01".equals(s)) {
				gender = "����";
			} else if ("02".equals(s)) {
				gender = "����";
			}
		}

		return gender;
	}

	// ��ȭ��ȣ ���� ���� ��ȯ�ϱ�
	// �ԷµǴ� ���� : 0226038636 or 03112345678
	// ��ȯ �� ���� : 02-2603-8636 / 031-1234-5678
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
	

	// �Էµ� �� s �� ���� ������ ����('01', '02', '03' ..�� ������ ��)
	public static String hobby(String s) {

		String h = "";

		// hobby_label ������� ���鼭
		// ����� ���� 01, 02, 03, 04 ...�� ������
		// �ش��ϴ� Ű�� ����´�.(�迭�� 0��° ���� ���۵ǹǷ� 'i-1')
		if (s != null && s.length() > 0) {
			for (int i = 1; i < hobby_label.length; i++) {
				if (("0" + i).equals(s)) {
					h = CodeUtil.hobby_label[i - 1];
				}
			}
		}
		return h;
	}

	// ���ڷ� �Էµ� ���(����, ���ǰ���...)��
	// ��ȣ('01', '02'...)�� �ٲٴ� �Լ�.
	public static String setHobby(String s) {

		String sh = "";
		for (int i = 0; i < CodeUtil.hobby_label.length; i++) {
			if (CodeUtil.hobby_label[i].equals(s)) {
				sh = CodeUtil.hobby_value[i];
			}
		}
		return sh;
	}

	// ���, ���� ��� ����
	// �ѹ��� ���� �׿� �´� ����� �ε��� ���� �ʱ�ȭ�Ѵ�.
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

	// ����
	// ���� ���ڷ� �Էµ� ��(1, 2, 3...)
	// ���� ���� �ε����� �����Ѵ�.
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

	// ���� ��� ����
	// ���� ������ ���� ���� : "�ǻ�", "������" ..
	// �迭�� �ִ� ��ȣ ��{01, 02, 03..} ��Ī���ֱ�
	public static String setJob(String s) {

		String j = "";

		for (int i = 0; i < CodeUtil.job_label.length; i++) {
			if (CodeUtil.job_label[i].equals(s)) {
				j = CodeUtil.job_value[i];
			}
		}

		return j;
	}

	// ��ȣ ���� ����� �Լ�. '0001', '0002'...
	// ���� ���ظ��� �����غ���.
	public static String numPad(String s) {

		int sLen = s.length();
		for (int i = sLen; i < 4; i++) {
			s = "0" + s;
		}
		return s;
	}

	public static void main(String args[]) {

		// ������� �׽�Ʈ ���
		String b = CodeUtil.birth("20210824");
		System.out.println("CodeUtil.birth()�׽�Ʈ : " + b);

		// ��ȭ��ȣ �׽�Ʈ ���
		String t = CodeUtil.tel("0274101900");
		System.out.println("CodeUtil.tel(��ȭ��ȣ)�׽�Ʈ : " + t);

		// �޴�����ȣ �׽�Ʈ ���
		String hp = CodeUtil.tel("01074101900");
		System.out.println("CodeUtil.tel(�޴�����ȣ)�׽�Ʈ : " + hp);

		// ���� �׽�Ʈ ���
		String g = CodeUtil.gender("02");
		System.out.println("CodeUtil.gender()�׽�Ʈ : " + g);

		// ��� �׽�Ʈ ���
		String hb = CodeUtil.hobby("02");
		System.out.println("CodeUtil.hobby()�׽�Ʈ : " + hb);

		// ���� �׽�Ʈ ���
		String job = CodeUtil.job("02");
		System.out.println("CodeUtil.job()�׽�Ʈ : " + job);

	}
}
