package com.kosmo.kck.board;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

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

public class KckBoardUpdate extends JFrame implements ActionListener {

	// ���
	private static final long serialVersionUID = 1L;

	// �������
	private JLabel jl[];
	private JTextField jt[];
	private JTextArea jta;
	private JPasswordField jpf;
	private JButton jb[];
	private JPanel jp[];

	// ������
	public KckBoardUpdate() {
		// JFrame Ÿ��Ʋ �����ϱ�
		this.setTitle(":::�Խ���:::");

		// JFrame ���̾ƿ� �Ŵ��� ���� ���̾ƿ����� �����ϱ�
		this.getContentPane().setLayout(null);
		jp = new JPanel[2];
		jp[0] = new JPanel();
		jp[0].setBorder(new EtchedBorder());
		jp[0].setBounds(0, 0, 465, 480);
		jp[0].setBackground(Color.cyan);
		jp[0].setLayout(null);

		// �Խ��� ��
		JLabel jlM = new JLabel();
		jlM.setText("�Խ���");
		jlM.setHorizontalAlignment(SwingConstants.CENTER);
		jlM.setFont(new Font("�������", Font.BOLD, 20));
		jlM.setBounds(20, 20, 362, 40);
		jp[0].add(jlM);

		// ��
		jl = new JLabel[5];
		int ly = 80;
		for (int i = 0; i < jl.length; i++) {
			jl[i] = new JLabel();
			jl[i].setOpaque(true);
			jl[i].setText(CodeUtil.board_label[i]);
			jl[i].setHorizontalAlignment(SwingConstants.CENTER);
			jl[i].setFont(new Font("�������", Font.BOLD, 15));
			jl[i].setBounds(20, ly, 100, 30);
			ly += 40;
			jp[0].add(jl[i]);
		}

		// �ؽ�Ʈ�ʵ�
		jt = new JTextField[3];
		int ty = 80;
		for (int i = 0; i < jt.length; i++) {
			jt[i] = new JTextField(200);
			jt[i].setBounds(130, ty, 300, 30);
			jp[0].add(jt[i]);
			ty += 40;
		}

		// ��й�ȣ
		jpf = new JPasswordField();
		jpf.setBounds(130, 200, 100, 30);
		jpf.setEchoChar('*');
		jp[0].add(jpf);

		// �ؽ�Ʈ����� ���̱� ���ؼ�
		jp[1] = new JPanel();
		jp[1].setLayout(new BorderLayout(5, 5));
		jp[1].setBounds(130, 240, 300, 140);
		jp[1].setBackground(Color.red);
		jp[0].add(jp[1]);

		// �ؽ�Ʈ�����
		jta = new JTextArea(10, 10);
		jp[1].add(new JScrollPane(jta));

		// ��ư
		jb = new JButton[2];
		for (int i = 0; i < jb.length; i++) {
			jb[i] = new JButton();
			jb[i].addActionListener(this);
			jp[0].add(jb[i]);
		}

		jb[0].setText("����");
		jb[0].setBounds(20, 420, 250, 30);
		jb[0].setFont(new Font("�������", Font.BOLD, 15));

		jb[1].setText("����");
		jb[1].setBounds(280, 420, 150, 30);
		jb[1].setFont(new Font("�������", Font.BOLD, 15));

		// JTextFiled disable
		jt[0].setEditable(true);
		jt[2].setEditable(false);

		// JPanel JFrame ���̱�
		this.getContentPane().add(jp[0]);

		this.setSize(465, 520);
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
	// �Խù� ��ȸ�ϱ�
	public void kboardSelect(String bnum) {

		System.out.println("KckBoardUpdate.kboardSelect()�Լ� ����");

		KckBoardService kbs = new KckBoardServiceImpl();
		KckBoardVO kvo = new KckBoardVO();
		kvo.setBnum(bnum);

		ArrayList<KckBoardVO> aList = kbs.kboardSelect(kvo);
		System.out.println("aList : " + aList);

		if (aList != null && aList.size() > 0) {

			KckBoardVO _kvo = aList.get(0);

			jt[0].setText(_kvo.getBnum());
			jt[1].setText(_kvo.getBsubject());
			jt[2].setText(_kvo.getBwriter());
			jpf.setText(_kvo.getBpw());
			jta.setText(_kvo.getBcontents());
		}
	}

	// �Խù� �����ϱ�
	public void kboardUpdate(String bnum, String bsubject, String bcontents) {
		System.out.println("KckBoardUpdate.kboardUpdate()�Լ� ����");

		KckBoardService kbs = new KckBoardServiceImpl();

		KckBoardVO kvo = new KckBoardVO();

		kvo.setBsubject(bsubject);
		kvo.setBcontents(bcontents);
		kvo.setBnum(bnum);

		int nCnt = kbs.kboardUpdate(kvo);

		if (nCnt > 0) {
			System.out.println("�� �ñ� ���� ����  >>> : " + nCnt);
			JOptionPane.showMessageDialog(this, "�Խñ� ���� ���� >>> :  ");
			new KckBoardAll();
		} else {
			System.out.println("�Խñ� ���� ����  >>> : " + nCnt);
		}
	}

	// �Խù� �����ϱ�
	public void kboardDelete(String bnum) {
		System.out.println("KckBoardUpdate.kboardDelete()�Լ� ����");

		KckBoardService kbs = new KckBoardServiceImpl();

		KckBoardVO kvo = new KckBoardVO();
		kvo.setBnum(bnum);

		int nCnt = kbs.kboardDelete(kvo);

		if (nCnt > 0) {
			System.out.println("�� �ñ� ���� ����  >>> : " + nCnt);
			JOptionPane.showMessageDialog(this, "�Խñ� ���� ���� >>> :  ");
			new KckBoardAll();
		} else {
			System.out.println("�Խñ� ���� ����  >>> : " + nCnt);
		}
	}

	// ActionListener �������̵� �Լ�
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("KckBoard.actionPerformed() �Լ� ����  >>> : ");

		Object obj = e.getSource();
		Object jbCaption = e.getActionCommand();

		if (jb[0] == obj) {
			System.out.println("�����ϱ� ��ư Ŭ�� >>> : " + jbCaption);

			String bnum = "";
			String bsubject = "";
			String bcontents = "";

			bnum = jt[0].getText();
			bsubject = jt[1].getText();
			bcontents = jta.getText();

			System.out.println("bnum >>> : " + bnum);
			System.out.println("bsubject >>> : " + bsubject);
			System.out.println("bcontents >>> : " + bcontents);

			this.kboardUpdate(bnum, bsubject, bcontents);
		}

		if (jb[1] == obj) {
			System.out.println("�����ϱ� ��ư Ŭ�� >>> : ");
			String bnum = "";
			bnum = jt[0].getText();
			System.out.println("bnum >>> : " + bnum);
			this.kboardDelete(bnum);
		}

	}

	// �����Լ�
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// �����ڷ� ����
		new KckBoardUpdate();
	}

}
