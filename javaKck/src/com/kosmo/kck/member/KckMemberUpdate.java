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
	// ���
	// �ҹ��ڷ� �����ص� ����ΰ�? �ƴϸ� �ٸ� ����?
	private static final long serialVersionUID = 9072198881429763467L;

	// ��� ����
	private JLabel jl[];
	private JTextField jt[];
	private JTextField jtField[];
	private JButton jb[];
	private JPanel jp[];
	private JComboBox<String> jc[];
	private JComboBox<String> jcBirth[];
	private JRadioButton jr[];

	// ������ : �⺻ ������. ������̼� = ?
	@SuppressWarnings("Unchecked")
	public KckMemberUpdate() {

		// JFrame Ÿ��Ʋ ����
		this.setTitle("����/����");

		// JFrame�� ���̾ƿ� �Ŵ��� : 'Border'�� ����
		this.getContentPane().setLayout(null);
		jp = new JPanel[2];
		jp[0] = new JPanel();
		jp[0].setBorder(new EtchedBorder());
		jp[0].setBounds(0, 0, 420, 880);
		jp[0].setBackground(Color.cyan);
		jp[0].setLayout(null);

		// �޺��ڽ�, ������ư, �ؽ�Ʈ�ʵ�, �� �ʱ�ȭ
		// �޺��ڽ� �ʱ�ȭ : why 5?
		jc = new JComboBox[5];

		// �޺��ڽ� : ������Ͽ��� ��, ��
		jcBirth = new JComboBox[5];

		// ���̿���ư : ����, ����
		jr = new JRadioButton[2];

		// �ؽ�Ʈ�ʵ�
		jtField = new JTextField[6];
		jt = new JTextField[17];
		int ty = 80;
		for (int i = 0; i < jt.length; i++) {

			if (8 == i || 10 == i || 13 == i) {
				System.out.println(">>>>>>>> " + i);
				// �̸���
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
				// ���
				if (10 == i) {
					jc[3] = new JComboBox<String>(CodeUtil.hobby_label);
					jp[0].add(jc[3]);
					jc[3].setBackground(Color.white);
					jc[3].setSelectedIndex(0);
					jc[3].setBounds(130, 480, 80, 30);
				}
				// ����
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

		// �� �ʱ�ȭ
		jl = new JLabel[17];
		int ly = 80;
		for (int i = 0; i < jl.length; i++) {
			jl[i] = new JLabel();
			jl[i].setOpaque(true);
			jl[i].setText(CodeUtil.member_value[i]);
			jl[i].setHorizontalAlignment(SwingConstants.CENTER);
			jl[i].setFont(new Font("�������", Font.BOLD, 15));
			jl[i].setBounds(20, ly, 100, 30);
			ly += 40;
			jp[0].add(jl[i]);
		}

		// ȸ������ ��
		JLabel jlM = new JLabel();
		jlM.setText("ȸ������");
		jlM.setHorizontalAlignment(SwingConstants.CENTER);
		jlM.setFont(new Font("�������", Font.BOLD, 20));
		jlM.setBounds(20, 20, 362, 40);
		jp[0].add(jlM);

		// ��ư
		jb = new JButton[3];
		for (int i = 0; i < jb.length; i++) {
			jb[i] = new JButton();
			jb[i].addActionListener(this);
			jp[0].add(jb[i]);
		}

		jb[0].setText("����"); // -----------------------------------------------------updated
		jb[0].setBounds(100, 780, 100, 30);
		jb[0].setFont(new Font("�������", Font.BOLD, 15));

		jb[1].setText("����"); // -----------------------------------------------------updated
		jb[1].setBounds(220, 780, 100, 30);
		jb[1].setFont(new Font("�������", Font.BOLD, 15));

		// JTextFiled disable : ��������, �����, ������
		// //-----------------------------------------------------updated
		// 8, 10, 13�� �ؽ�Ʈ�ʵ�� ������ ���� ó��.
		jt[0].setEditable(false); // ȸ����ȣ
		jt[1].setEditable(true); // ȸ���̸�
		jt[2].setEditable(true); // ���̵�
		jt[3].setEditable(true); // �н�����
		jt[4].setEditable(true); // �������
		jt[5].setEditable(true); // ����
		jt[6].setEditable(true); // ��ȭ��ȣ
		jt[7].setEditable(true); // �޴�����ȣ

		jt[11].setEditable(true); // �̸���
		jt[12].setEditable(true); // �ּ�

		jt[14].setEditable(false); // ��������
		jt[15].setEditable(false); // �����
		jt[16].setEditable(false); // ������

		// JPanel JFrame ���̱�
		this.getContentPane().add(jp[0]);

		this.setSize(420, 880);
		this.setLocation(200, 100);
		this.setResizable(false);
		this.setVisible(true);

		// JFrame �ݱ�
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				e.getWindow().setVisible(false);
				e.getWindow().dispose();
			}
		});
	}

	// �Լ�
	// ȸ�� ���̵� �ߺ� üũ���
	public void kmemIdCheck(String kid) {
		System.out.println("KckMemberUpdate.kmemIdCheck()���� (�ߺ�üũ) : ");

		try {
			// �Է°��� �ް� (String kid)
			// ��񿡼� ��ȸ (VOŬ������ ����)
			// �Է°��� ��񿡼� ��ȸ�� ���� ������ �����߻�
			// ��񿡼� ��ȸ�� ����� ������ ��밡��

			KckMemberService kms = new KckMemberServiceImpl();
			KckMemberVO kvo = new KckMemberVO();
			kvo.setKid(kid);

			boolean bool = kms.kmemIdCheck(kvo);

			if (bool) {
				System.out.println("�ߺ��� ���̵��Դϴ�!" + bool);
				JOptionPane.showMessageDialog(this, "���� ���̵� �ֽ��ϴ�  >>> :  ");
				jt[2].setText("");
			} else {
				System.out.println("��� ������ ���̵��Դϴ�!" + bool);
				JOptionPane.showMessageDialog(this, "��밡���� ���̵� �Դϴ� >>> :  ");
				jt[2].setText(kvo.getKid());
				jt[2].setEditable(false);
			}

		} catch (Exception e) {
			System.out.println("�����߻�! : " + e.getMessage());
		}
	}

	// ȸ�� ��� ��� : ���� ���α׷��� ������ ������ ���
	public void kmemInsert(String kname, String kid, String kpw, String kbirth, String kgender, String ktel, String khp,
			String kemail, String kaddr, String khobby, String kphoto, String kskill, String kjob) {

		System.out.println("KckMemberUpdate.kmemInsert()����");

		// VO Ŭ������ ȸ������ �����ؼ� ���񽺷� ������
		// ȸ���ѹ��� ä���Լ��� �̿��ؼ� �����Ѵ�.
		try {
			KckMemberService kms = new KckMemberServiceImpl();
			KckMemberVO kvo = new KckMemberVO();

			String knum = KckMemberChabun.ymdNum();
			System.out.println("������ ȸ����ȣ : " + knum);
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
				System.out.println("ȸ������ ��ϵǾ����ϴ�!" + bool);
				JOptionPane.showMessageDialog(this, "ȸ������ ��� ���� >>> :  ");
				new KckMemberAll();
			} else {
				System.out.println("ȸ�� ��Ͽ� �����߽��ϴ�.." + bool);
			}
		} catch (Exception e) {
			System.out.println("���� �߻�! : " + e.getMessage());
		}

	}

	// ȸ�� ��ȸ ��� : ���� ���α׷��� ������ ������ ���
	public void kmemSelect(String knum) {

		System.out.println("KckMemberUpdate.kmemSelect() ����");

		KckMemberService kms = new KckMemberServiceImpl();
		KckMemberVO kvo = new KckMemberVO();
		kvo.setKnum(knum);

		ArrayList<KckMemberVO> aList = kms.kmemselect(kvo);

		// �����Ͱ� �� ���Գ� üũ
		if (aList != null && aList.size() > 0) {

			// ������ ����
			KckMemberVO _kvo = aList.get(0);

			// ��ȸ�ؼ� VO�� �̿��� ������ �����͸�
			// �ؽ�Ʈ�ʵ忡 �����ϱ�.
			jt[0].setText(_kvo.getKnum());
			jt[1].setText(_kvo.getKname());
			jt[2].setText(_kvo.getKid());
			jt[3].setText(_kvo.getKpw());
			jt[4].setText(_kvo.getKbirth());

			// ����
			String kgender = _kvo.getKgender();

			if ("01".equals(kgender)) {
				kgender = "����";
			} else {
				kgender = "����";
			}
			jt[5].setText(_kvo.getKgender());

			// ��ȭ��ȣ, �޴��� ��ȣ
			jt[6].setText(_kvo.getKtel());
			jt[7].setText(_kvo.getKhp());

			// �̸���, split���� �յڷ� �߶�
			// email �迭�� ��Ƴ��� �ؽ�Ʈ�ʵ忡 ����
			String kemail = _kvo.getKemail();
			String email[] = kemail.split("@");
			jtField[0].setText(email[0]);
			jtField[1].setText(email[1]);

			// �ּ�
			jt[9].setText(_kvo.getKaddr());

			// ���
			jc[3].setSelectedIndex(CodeUtil.getComboIndex(_kvo.getKhobby()));

			// ����
			jt[11].setText(_kvo.getKphoto());

			// Ư��
			jt[12].setText(_kvo.getKskill());

			// ����
			jc[4].setSelectedIndex(CodeUtil.getComboIndex(_kvo.getKjob()));

			// ��������, ������, ������Ʈ��
			jt[14].setText(_kvo.getDeleteyn());
			jt[15].setText(_kvo.getInsertdate());
			jt[16].setText(_kvo.getUpdatedate());
		}
	}

	// ȸ�� ���� ���
	public void kmemUpdate(String kname, String kid, String kpw, String kbirth, String kgender, String ktel, String khp,
			String kemail, String kaddr, String khobby, String kjob) {

		System.out.println("KckMemberUpdate.kmemUpdate() ����");

		/*
		 * ���� �����ϰ� ���� �ʵ�
		 * 
		 * jt[1].setEditable(true); // ȸ���̸� jt[2].setEditable(true); // ���̵�
		 * jt[3].setEditable(true); // �н����� jt[4].setEditable(true); // �������
		 * jt[5].setEditable(true); // ���� jt[6].setEditable(true); // ��ȭ��ȣ
		 * jt[7].setEditable(true); // �޴�����ȣ
		 * 
		 * jt[11].setEditable(true); // �̸��� jt[12].setEditable(true); // �ּ�
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
			System.out.println("ȸ�� ���� ����!" + bool);
			JOptionPane.showMessageDialog(this, "ȸ������ ���� ����! ");
			new KckMemberAll();
		} else {
			System.out.println("ȸ�� ���� �����߾��.." + bool);
		}
	}

	// ȸ�� ���� ���
	public void kmemDelete(String kid) {
		System.out.println("KckMemberUpdate.kmemDelete() ����");

		KckMemberService kms = new KckMemberServiceImpl();
		KckMemberVO kvo = new KckMemberVO();

		kvo.setKid(kid);

		boolean bool = kms.kmemDelete(kvo);

		if (bool) {
			System.out.println("ȸ�� ���� ����!" + bool);
			JOptionPane.showMessageDialog(this, "ȸ�� ���� ����! ");
			new KckMemberAll();
		} else {
			System.out.println("ȸ�� ������ �����߾��.." + bool);
		}
	}

	// �ؽ�Ʈ�ʵ��� �� �ʱ�ȭ
	public void valueClear() {

		System.out.println("KckMemberUpdate.valueClear() ����");

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

	// �����ε��� �Լ�
	@Override
	public void actionPerformed(ActionEvent e) {

		System.out.println("KckMemberUpdate.actionPerformed() ����");

		String jbCaption = e.getActionCommand();
		// ������ �̺�Ʈ�� ��ư�� 0��°(='�ߺ�Ȯ��')�� ���
		if (jb[0] == e.getSource()) {
			System.out.println("���� ��ư Ŭ�� >>> : " + jbCaption);
			// ȸ����ȣ

			String kname = jt[1].getText();
			String kid = jt[2].getText();
			String kpw = jt[3].getText();
			String kbirth = jt[4].getText();
			String kgender = jt[5].getText();
			String ktel = jt[6].getText();
			String khp = jt[7].getText();
			// �̸���
			String kemail = "";
			kemail = jtField[4].getText();
			kemail = kemail.concat("@");
			kemail = kemail.concat(jtField[5].getText());
			// �ּ�
			String kaddr = jt[9].getText();
			// ���
			String khobby = "";
			khobby = CodeUtil.setHobby(String.valueOf(jc[3].getSelectedItem()));
			// ����
			String kjob = "";
			kjob = CodeUtil.setJob(String.valueOf(jc[4].getSelectedItem()));

			this.kmemUpdate(kname, kid, kpw, kbirth, kgender, ktel, khp, kemail, kaddr, khobby, kjob);
		}
		if (jb[1] == e.getSource()) {
			System.out.println("���� ��ư Ŭ�� >>> : " + jbCaption);

			// ȸ����ȣ
			String kid = jt[2].getText();
			System.out.println("kid >>> : " + kid);

			this.kmemDelete(kid);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("KckMemberUpdate.main()�Լ� ����");
		new KckMemberUpdate();
	}

}
