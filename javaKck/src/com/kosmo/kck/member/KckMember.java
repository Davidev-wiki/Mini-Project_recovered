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
	public KckMember() {

		// JFrame Ÿ��Ʋ ����
		this.setTitle("ȸ�� ����");

		// JFrame�� ���̾ƿ� �Ŵ��� : 'Border'�� ����
		this.getContentPane().setLayout(null);
		jp = new JPanel[2];
		jp[0] = new JPanel();
		jp[0].setBorder(new EtchedBorder());
		jp[0].setBounds(0, 0, 620, 880);
		jp[0].setBackground(Color.orange);
		jp[0].setLayout(null);

		// �޺��ڽ�, ���̿���ư, �ؽ�Ʈ�ʵ�, �� �ʱ�ȭ
		// �޺��ڽ�1 : 5��, ������Ͽ��� �⵵, ��ȭ��ȣ, �޴�����ȣ, ���, ���� �뵵
		jc = new JComboBox[5];

		// �޺��ڽ�2 : ������Ͽ��� ��, �� �뵵 (5��-> 2���� ���� ������� �� �ؼ� �ٲ�)
		jcBirth = new JComboBox[2];

		// ���̿���ư : ����, ����
		jr = new JRadioButton[2];

		// �ؽ�Ʈ�ʵ�
		jtField = new JTextField[6];
		jt = new JTextField[17];
		
		// y�� ��ġ ���� ���� 
		int ty = 80;
		
		// �迭�� ������ 17��ŭ �ݺ�
		for (int i = 0; i < jt.length; i++) {
			
			//
			if (4 == i || 5 == i || 6 == i || 7 == i || 8 == i || 10 == i || 13 == i) {
				System.out.println("KckMember.�ؽ�Ʈ�ʵ� ���� �ʱ�ȭ�� : " + i);
				// ������� : �޺��ڽ�
				if (4 == i) {
					// ����
					String[] years = DateUtil.comboYear();
					jc[0] = new JComboBox<String>(years);
					jp[0].add(jc[0]);
					jc[0].setBackground(Color.white);
					jc[0].setSelectedIndex(0);
					jc[0].setBounds(130, 240, 80, 30);
					
					// ��
					String[] months = DateUtil.comboMonth();
					jcBirth[0] = new JComboBox<String>(months);
					jp[0].add(jcBirth[0]);
					jcBirth[0].setBackground(Color.white);
					jcBirth[0].setSelectedIndex(0);
					jcBirth[0].setBounds(220, 240, 65, 30);

					// ��
					String[] days = DateUtil.comboDay();
					jcBirth[1] = new JComboBox<String>(days);
					jp[0].add(jcBirth[1]);
					jcBirth[1].setBackground(Color.white);
					jcBirth[1].setSelectedIndex(0);
					jcBirth[1].setBounds(295, 240, 65, 30);
				}
				// ���� : ������ư
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
				// ��ȭ��ȣ
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
				// �޴�����ȣ
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
				// �̸���
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
			// y�� ��ġ ���� ���� 
			ty += 40;
		}

		// �� �ʱ�ȭ
		jl = new JLabel[17];
		// y�� ��ġ ���� ���� 
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
		jb = new JButton[5];
		for (int i = 0; i < jb.length; i++) {
			jb[i] = new JButton();
			jb[i].addActionListener(this);
			jp[0].add(jb[i]);
		}

		jb[0].setText("�ߺ�Ȯ��");
		jb[0].setBounds(280, 160, 110, 30);
		jb[0].setFont(new Font("�������", Font.BOLD, 15));

		jb[1].setText("�����ϱ�");
		jb[1].setBounds(20, 780, 100, 30);
		jb[1].setFont(new Font("�������", Font.BOLD, 15));

		jb[2].setText("�ʱ�ȭ");
		jb[2].setBounds(180, 780, 100, 30);
		jb[2].setFont(new Font("�������", Font.BOLD, 15));

		jb[3].setText("�����ϱ�");
		jb[3].setBounds(340, 780, 100, 30);
		jb[3].setFont(new Font("�������", Font.BOLD, 15));

		jb[4].setText("�����ϱ�");
		jb[4].setBounds(490, 780, 100, 30);
		jb[4].setFont(new Font("�������", Font.BOLD, 15));

		// JTextFiled disable : ��������, �����, ������
		jt[0].setEditable(false);
		jt[14].setEditable(false);
		jt[15].setEditable(false);
		jt[16].setEditable(false);

		// JPanel JFrame ���̱�
		this.getContentPane().add(jp[0]);

		this.setSize(620, 870);
		this.setLocation(200, 100);
		this.setResizable(true);
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
		System.out.println("KckMember.kmemIdCheck()���� (�ߺ�üũ) : ");

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
				jt[2].setEditable(true);
			}

		} catch (Exception e) {
			System.out.println("�����߻�! : " + e.getMessage());
		}
	}

	// ȸ�� ��� ���
	public void kmemInsert(String kname, String kid, String kpw, String kbirth, String kgender, String ktel, String khp,
			String kemail, String kaddr, String khobby, String kphoto, String kskill, String kjob) {

		System.out.println("KckMember.kmemInsert()����");

		// VO Ŭ������ ȸ������ �����ؼ� ���񽺷� ������
		// ȸ���ѹ��� ä���Լ��� �̿��ؼ� �����Ѵ�.
		try {
			KckMemberService kms = new KckMemberServiceImpl();
			KckMemberVO kvo = new KckMemberVO();

			String knum = KckMemberChabun.ymdNum();
			System.out.println("������ ȸ����ȣ : " + knum);
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

	// ȸ�� ��ȸ ���
	public void kmemSelect(String knum) {
		
		KckMemberService kms = new KckMemberServiceImpl();
		KckMemberVO kvo = new KckMemberVO();
		kvo.setKnum(knum);
		
		ArrayList<KckMemberVO> aList = kms.kmemselect(kvo);
		
		// �����Ͱ� �� ���Գ� üũ
		if(aList != null && aList.size() > 0) {
			
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
			
			if("01".equals(kgender)) {
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

	// ȸ�� ���� ��� => ���� ���� ������ ���� �����ؾ� �ϴµ�.. �ð��� �����ϱ� �н� 
	public void kmemUpdate(String knum, String kemail, String kaddr, String khobby, String kjob) {
		new KckMemberAll();
	}

	// ȸ�� ���� ��� => �Խ��� �� â���� �����ϱ� ==> �����ߴ� �̸��� �Է½� �����ǵ��� �ϱ�
	public void kmemDelete(String kemail) {
		new KckMemberAll();
	}

	// �ؽ�Ʈ�ʵ��� �� �ʱ�ȭ
	public void valueClear() {

		System.out.println("KckMember.valueClear() ����");
		// jt.length : jt�迭�� ����. ��ܿ� [17] �� ����.
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

	// �����ε��� �Լ�
	@Override
	public void actionPerformed(ActionEvent e) {

		System.out.println("KckMember.actionPerformed() ����");

		// �̺�Ʈ�� �����ͼ� ������Ʈ�� �ʱ�ȭ��.
		Object obj = e.getSource();
		Object jbCaption = e.getActionCommand();

		// ������ �̺�Ʈ�� ��ư�� 0��°(='�ߺ�Ȯ��')�� ���
		if (jb[0] == obj) {
			System.out.println("�ߺ�Ȯ�� ��ư�� ���õǾ����ϴ�. : " + jbCaption);
			// ���̵� �� �Է��� �� �ִ� â ��ȯ
			// �Էµ� ���� �����ͼ� ���������
			// ȸ�� ���̵��ߺ�Ȯ�� �Լ��� ������.
			String kid = jt[2].getText();
			this.kmemIdCheck(kid);
		}
		// ������ �̺�Ʈ�� ��ư�� 1��°(='�����ϱ�')�� ���
		if (jb[1] == obj) {
			System.out.println("�����ϱ� ��ư�� ���õǾ����ϴ�. : " + jbCaption);
			// ���� â ��ȯ
			// �ؽ�Ʈ�ʵ忡 �Էµ� ���ڿ��� ������ ��´�.
			String kname = jt[1].getText();
			String kid = jt[2].getText();
			String kpw = jt[3].getText();

			// ������� (���ڼ��ڸ� �����ϴ� �۾�)
			String kbirth = "";
			kbirth = String.valueOf(jc[0].getSelectedItem());
			kbirth = kbirth.concat(String.valueOf(jcBirth[0].getSelectedItem()));
			kbirth = kbirth.concat(String.valueOf(jcBirth[1].getSelectedItem()));

			// ���� (������ư ���ÿ� ���� ��� �ο�)
			String kgender = "";
			if (jr[0].isSelected()) {
				System.out.println("���õ� ���� : " + jr[0].getText());
				kgender = "01";
			} else {
				System.out.println("���õ� ���� : " + jr[1].getText());
				kgender = "02";
			}

			// ��ȭ��ȣ (�ؽ�Ʈ�ʵ� �Է°��� ����)
			String ktel = "";
			ktel = String.valueOf(jc[1].getSelectedItem());
			ktel = ktel.concat(jtField[0].getText());
			ktel = ktel.concat(jtField[1].getText());

			// �ڵ��� ��ȣ (�ؽ�Ʈ�ʵ� �Է°��� ����)
			String khp = "";
			khp = String.valueOf(jc[2].getSelectedItem());
			khp = khp.concat(jtField[2].getText());
			khp = khp.concat(jtField[3].getText());

			// �̸��� (�ؽ�Ʈ�ʵ� �Է°��� @���ڷ� ����)
			String kemail = "";
			kemail = jtField[4].getText();
			kemail = kemail.concat("@");
			kemail = kemail.concat(jtField[5].getText());

			// �ּ�
			String kaddr = jt[9].getText();

			// ��� 
			// �迭�� ��� ��� �����ϱ� : setHobby �Լ� ȣ��. 
			// '���ڿ�'�� ������ ���� '���� ����'�� �ٲ۴�.
			String khobby = "";
			khobby = CodeUtil.setHobby(String.valueOf(jc[3].getSelectedItem()));

			// ����
			String kphoto = jt[11].getText();

			// Ư��
			String kskill = jt[12].getText();

			// ���� 
			// �迭�� ��� ��� �����ϱ� : setJob �Լ� ȣ��. 
			// '���ڿ�'�� ������ ���� '���� ����'�� �ٲ۴�.
			String kjob = "";
			kjob = CodeUtil.setJob(String.valueOf(jc[4].getSelectedItem()));

			// ����غ���
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

			// kmemInsert()�Լ� �����ϱ�.
			this.kmemInsert(kname, kid, kpw, kbirth, kgender, ktel, khp, kemail, kaddr, khobby, kphoto, kskill, kjob);
		}

		// ������ �̺�Ʈ�� ��ư�� 2��°(='�ٽ�')�� ���
		if (jb[2] == obj) {
			System.out.println("�ٽ� ��ư�� ���õǾ����ϴ�. : " + jbCaption);
			// �ؽ�Ʈ�ʵ� �ʱ�ȭ �Լ� ����
			this.valueClear();
		}

		// ������ �̺�Ʈ�� ��ư�� 3��°(='�����ϱ�')�� ���
		if (jb[3] == obj) {
			System.out.println("�����ϱ� ��ư�� ���õǾ����ϴ�. : " + jbCaption);
			// ��ü��ȸ â���� '����/����' ��ư�� ������ �����ϴ� ����.
			// ��ü ��ȸ â �˾�
			new KckMemberAll();
		}

		// ������ �̺�Ʈ�� ��ư�� 4��°(='�����ϱ�')�� ���
		if (jb[4] == obj) {
			System.out.println("�����ϱ� ��ư�� ���õǾ����ϴ�. : " + jbCaption);
			// ��ü��ȸ â���� '����/����' ��ư�� ������ �����ϴ� ����.
			// ��ü ��ȸ â �˾�
			new KckMemberAll();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("KckMember.main()�Լ� ����");
		// ȸ�� ���� â �˾�
		new KckMember();
	}

}
