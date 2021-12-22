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

	// ���
	private static final long serialVersionUID = 1L;

	// ��� ����
	private JLabel jl[];
	private JTextField jt[];
	private JTextArea jta;
	private JPasswordField jpf;
	private JButton jb[];
	private JPanel jp[];

	// ������
	public KckBoard() {

		// JFrame Ÿ��Ʋ �����ϱ�
		this.setTitle("�Խ���");

		// JFrame ���̾ƿ� �Ŵ��� : ���� ���̾ƿ� ����
		this.getContentPane().setLayout(null);

		// �г� 2�� ����
		jp = new JPanel[2];
		jp[0] = new JPanel();
		jp[0].setBorder(new EtchedBorder());
		jp[0].setBounds(0, 0, 465, 480);
		jp[0].setBackground(Color.orange);
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

		// �ؽ�Ʈ �ʵ�
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

		// �г� ����
		jp[1] = new JPanel();
		jp[1].setLayout(new BorderLayout(5, 5));
		jp[1].setBounds(130, 240, 300, 140);
		jp[1].setBackground(Color.red);
		jp[0].add(jp[1]);

		// �гο� �ؽ�Ʈ ������߰�
		jta = new JTextArea(10, 10);
		jp[1].add(new JScrollPane(jta));

		// ��ư
		jb = new JButton[2];
		for (int i = 0; i < jb.length; i++) {
			jb[i] = new JButton();
			jb[i].addActionListener(this);
			jp[0].add(jb[i]);
		}

		jb[0].setText("�ۼ��ϱ�");
		jb[0].setBounds(20, 420, 250, 30);
		jb[0].setFont(new Font("�������", Font.BOLD, 15));

		jb[1].setText("�ٽ�");
		jb[1].setBounds(280, 420, 150, 30);
		jb[1].setFont(new Font("�������", Font.BOLD, 15));

		// jTextField ��Ȱ��ȭ : ��������, �����, ������
		jt[0].setEditable(false);

		// JFrame�� JPanel ���̱�
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

	// �Խ��� ��� �Լ�
	public void kboardInsert(String bsubject, String bwriter, String bpw, String bcontents) {
		System.out.println("KckBoard.kboardInsert()�Լ� ����");

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
			System.out.println("�Խù� ����� �Ϸ�Ǿ����ϴ�!" + nCnt);
			JOptionPane.showMessageDialog(this, "�Խñ� ��� ���� >>> :  ");
			// �Խù� ��ü ��ȸ â �˾�
			new KckBoardAll();
		} else {
			System.out.println("�Խù� ��� ����" + nCnt);
		}

	}

	// �ؽ�Ʈ �ʵ� �ʱ�ȭ �Լ�
	public void valueClear() {
		System.out.println("KckBoard.valueClear()�Լ� ����");
		for (int i = 0; i < jt.length; i++) {
			jt[i].setText("");
		}
		// �н������ʵ�
		jpf.setText("");
		// �ؽ�Ʈ�����
		jta.setText("");
	}

	// ActionListener ���� �Լ�
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("KckBoard.actionPerformed()�Լ� ����");

		Object obj = e.getSource();
		Object jbCaption = e.getActionCommand();

		if (jb[0] == obj) {
			System.out.println("�ۼ��ϱ� ��ư�� Ŭ���� : " + jbCaption);

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
			System.out.println("�ٽ� ��ư�� Ŭ����");
			this.valueClear();
		}
	}

	// main �Լ�
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// �����ڷ� ����
		new KckBoard();
	}

}
