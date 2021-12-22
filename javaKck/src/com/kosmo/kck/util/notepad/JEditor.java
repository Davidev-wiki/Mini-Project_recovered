package com.kosmo.kck.util.notepad;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.awt.*;  

public class JEditor extends JFrame
{

	private static final long serialVersionUID = 1L;

	private static final String IMG_PAGH = "C:\\00.KOSMO93\\10.JExam\\el_java_work\\javaKosmo\\src\\img_notepad\\";
	
	protected MenuEvent me;
	protected static JTextArea			ta;
	protected JScrollPane				sp;
	protected JMenuBar					mb;
	protected JMenu						mFile, mEdit, mForm, mNet, mHelp;
	protected static JMenuItem			mNew, mOpen, mSave, mQuit;
	protected static JMenuItem			mCut, mCopy, mPaste, mDelete, pCut, pCopy, pPaste, pDelete;
	protected static JMenuItem			mFont, mBackColor, mHost, mInfo, pFont, pBackColor;
	protected static JFileChooser		fc;
	protected static JColorChooser	    cc;
	
	// EDIT을 위한 보조변수(Copy한 텍스트를 저장한다.)
	protected static String				selection = "";		
	
	//Toolbar를 위한 변수
	protected JToolBar					toolbar;
	protected static JButton 			toolButNew, toolButOpen, toolButSave, toolButCut, toolButCopy, toolButPaste;
	protected ImageIcon					toolIMG1, toolIMG2, toolIMG3, toolIMG4, toolIMG5, toolIMG6;
	
	//PopupMenu를 위한 변수
	protected JPopupMenu					popup;
	
	//마우스 따라다니는 이미지
	final ImageIcon icon = new ImageIcon(IMG_PAGH + "navi.gif");
	JLabel mouseLabel;
	
	public JEditor(){		
		this.setTitle("세종대왕 v1.3.1");

		//TextArea를 ScrollPane에 붙여 이를 프레임에 붙인다.
		ta = new JTextArea();
		sp = new JScrollPane(ta);

		//메뉴바 만들고, 단축키 지정하기
		mb = new JMenuBar();

		/*
			메뉴를 생성하고, 각각에 Mnemonic를 설정한다.
			Mnemonic를 설정하면 메뉴의 이름중 해당 알파벳에 밑줄이 그어진다.
			메뉴에 접근하기 위해서는 ALT키와 해당 단축키를 누르면 된다.
		*/
		mFile = new JMenu("파일(F)" );
		mEdit = new JMenu("편집(E)");
		mForm = new JMenu("서식(O)");
		mNet = new JMenu("도구(T)");
		mHelp = new JMenu("도움말(H)");

		mFile.setMnemonic('f');
		mEdit.setMnemonic('e');
		mForm.setMnemonic('o');
		mNet.setMnemonic('t');
		mHelp.setMnemonic('h');

		/*
			메뉴아이템을 생성하고, 자주 쓰는 아이템에 단축키를 설정한다.
			setAccelerator는 메뉴아이템명의 오른편에 "CTRL+(알파벳)" 이 입력된다.
			문서 편집 상태에서 해당 설정 단축키값을 입력하면, 메뉴아이템을 직접 선택할 수 있다.
			Mnemonic과 setAccelerator의 차이점은 EditPlus를 보면 알 수 있다.
		*/
		mNew = new JMenuItem("새글(N)", 'n');
		mNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_MASK));
		mNew.setIcon(new ImageIcon(IMG_PAGH + "mnew.gif"));
		mOpen = new JMenuItem("열기(O)", 'o');
		mOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_MASK));
		mOpen.setIcon(new ImageIcon(IMG_PAGH + "mopen.gif"));
		mSave = new JMenuItem("저장(S)", 's');
		mSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_MASK));
		mSave.setIcon(new ImageIcon(IMG_PAGH + "msave.gif"));
		mQuit = new JMenuItem("닫기(Q)", 'q');
		mQuit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_MASK));
		mQuit.setIcon(new ImageIcon(IMG_PAGH + "mquit.gif"));

		//각 메뉴아이템의 앞에 그림을 넣기 위해 setIcon()을 통해 이미지를 넣어준다.
		mCut = new JMenuItem("잘라내기(X)", 'x');
		mCut.setIcon(new ImageIcon(IMG_PAGH + "mcut.gif"));
		mCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_MASK));
		mCopy = new JMenuItem("복사(C)", 'c');
		mCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_MASK));
		mCopy.setIcon(new ImageIcon(IMG_PAGH + "mcopy.gif"));
		mPaste = new JMenuItem("붙여넣기(V)", 'v');
		mPaste.setIcon(new ImageIcon(IMG_PAGH + "mPaste.gif"));
		mPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_MASK));
		mDelete = new JMenuItem("지우기(D)", 'd');
		mDelete.setIcon(new ImageIcon(IMG_PAGH + "mDelete.gif"));
		mDelete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.CTRL_MASK));

		mFont = new JMenuItem("글꼴(F)",'f');
		mFont.setIcon(new ImageIcon(IMG_PAGH + "mfont.gif"));
		mBackColor = new JMenuItem("바탕색(B)", 'b');
		mBackColor.setIcon(new ImageIcon("mbackcolor.gif"));
		mHost = new JMenuItem("호스트 정보(H)", 'h');
		mHost.setIcon(new ImageIcon(IMG_PAGH + "mhost.gif"));
		mInfo = new JMenuItem("세종대왕 정보(A)", 'a');
		mInfo.setIcon(new ImageIcon(IMG_PAGH + "minfo.gif"));

		/*
			FileChooser를 부를 때, FileFilter를 이용해 선택적으로 보여준다.
			여기서는 자바화일과 텍스트 화일만을 필터링한다.
			필터링 기능은 FileChoice.java 화일에 구현되어 있다.
			여기서 넘겨주는 생성자의 매개변수는 FileChoice 클래스에서 긴요하게 이용.
		*/
		fc = new JFileChooser();
		fc.addChoosableFileFilter(new FileChoice("텍스트 파일  *.txt"));
		fc.addChoosableFileFilter(new FileChoice("자바 파일  *.java"));
		fc.setCurrentDirectory(new File("."));

		cc = new JColorChooser();

		//ToolBar를 위한 변수 초기화하기
		toolbar = new JToolBar();
		toolIMG1 = new ImageIcon(IMG_PAGH + "new.gif");
		toolIMG2 = new ImageIcon(IMG_PAGH + "open.gif");
		toolIMG3 = new ImageIcon(IMG_PAGH + "save.gif");
		toolIMG4 = new ImageIcon(IMG_PAGH + "cut.gif");
		toolIMG5 = new ImageIcon(IMG_PAGH + "copy.gif");
		toolIMG6 = new ImageIcon(IMG_PAGH + "paste.gif");

		//ToolBar의 버튼에 위에서 생성한 이미지를 덮는다.
		toolButNew = new JButton(toolIMG1);
		toolButOpen = new JButton(toolIMG2);
		toolButSave = new JButton(toolIMG3);
		toolButCut = new JButton(toolIMG4);
		toolButCopy = new JButton(toolIMG5);
		toolButPaste = new JButton(toolIMG6);

		//PopupMenu 초기화
		//여기에도 단축키를 설정한다.
		pCopy = new JMenuItem("복사하기");		
		pCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_MASK));
		pCut = new JMenuItem("잘라내기");
		pCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_MASK));
		pPaste = new JMenuItem("붙여넣기");
		pPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_MASK));
		pDelete = new JMenuItem("지우기");
		pFont = new JMenuItem("폰트바꾸기");
		pBackColor = new JMenuItem("배경색바꾸기");
		popup = new JPopupMenu();

		//마우스를 따라다니는 이미지를 위한 작업
		//Label에 이미지를 붙여 Label이 마우스를 따라 다닌다(MouseMotionEvent 이용)
		//setBounds()는 크기를 정해주는 메서드이다.
		mouseLabel = new JLabel(icon);
		mouseLabel.setBounds(2000, 2000, icon.getIconWidth(), icon.getIconHeight());
		ta.add(mouseLabel);
		
		this.display();
	}

	public void display()
	{
		//이벤트를 맡아서 처리해 주는 클래스는 MenuEvent 클래스이다.
		//이벤트 처리를 하는 클래스의 객체를 생성
		me = new MenuEvent();

		//메뉴에서 File이벤트 연결하기
		mNew.addActionListener(me);
		mOpen.addActionListener(me);
		mSave.addActionListener(me);
		mQuit.addActionListener(me);

		//메뉴에서 Edit이벤트 연결하기
		mCut.addActionListener(me);
		mCopy.addActionListener(me);
		mPaste.addActionListener(me);
		mDelete.addActionListener(me);

		//메뉴에서 Form이벤트 연결하기
		mFont.addActionListener(me);
		mBackColor.addActionListener(me);

		//메뉴에서 Net, Help이벤트 연결하기
		mHost.addActionListener(me);
		mInfo.addActionListener(me);

		//PopupMenu에 이벤트 연결하기
		pCut.addActionListener(me);
		pCopy.addActionListener(me);
		pPaste.addActionListener(me);
		pDelete.addActionListener(me);
		pFont.addActionListener(me)	;
		pBackColor.addActionListener(me);

		//ToolBar에 이벤트 연결하기
		toolButNew.addActionListener(me);
		toolButOpen.addActionListener(me);
		toolButSave.addActionListener(me);
		toolButCut.addActionListener(me);
		toolButCopy.addActionListener(me);
		toolButPaste.addActionListener(me);

		/*
			TextPopupMenu 이벤트 처리하기
			if문을 통해 마우스 오른쪽 클릭 여부를 가려낸다.
			e.getModifiers()는 USER의 클릭값을 받아오고, 이는 기존 상수값인 BUTTON3_MASK와 비교한다.
			e.getX(), e.getY()를 통해 마우스의 클릭 좌표를 받아온다.
		*/
		ta.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				if((e.getModifiers() & MouseEvent.BUTTON3_MASK) != 0)
				popup.show(ta,e.getX(), e.getY());
			}
		});

		/*
			마우스를 따라다니는 이미지를 위한 이벤트 처리하기 
			mouseMoved()를 통해 마우스가 움직일때마다 Label이 마우스 좌표값을 따라 위치가 바뀌도록 한다.
		*/
		ta.addMouseMotionListener(new MouseMotionAdapter()
		{
			public void mouseMoved(MouseEvent e)
			{
				mouseLabel.setLocation(e.getX()-10, e.getY()-20);
			}
		});

		/*
			JFrame에 JScrollPane 붙이기
			JScrollPane에는 JTextArea가 add()되어 있다.
		*/
		getContentPane().add(sp);

		//메뉴바에 모두 붙이기
		setJMenuBar(mb);

		mb.add(mFile);
		mFile.add(mNew);
		mFile.addSeparator();
		mFile.add(mOpen);
		mFile.add(mSave);
		mFile.addSeparator();
		mFile.add(mQuit);

		mb.add(mEdit);
		mEdit.add(mCut);
		mEdit.add(mCopy);
		mEdit.add(mPaste);
		mEdit.add(mDelete);

		mb.add(mForm);
		mForm.add(mFont);
		mForm.add(mBackColor);

		mb.add(mNet);
		mb.add(mHelp);
		mNet.add(mHost);
		mHelp.add(mInfo);

		//ToolBar에 모두 붙이기
		toolbar.add(toolButNew);
		toolbar.add( toolButOpen);
		toolbar.add( toolButSave);
		toolbar.addSeparator();
		toolbar.add( toolButCut);
		toolbar.add( toolButCopy);
		toolbar.add( toolButPaste);
		toolbar.addSeparator();

		//툴바의 버�v에 풍선도움말 달기
		toolButNew.setToolTipText("New");
		toolButOpen.setToolTipText("Open");
		toolButSave.setToolTipText("Save");
		toolButCut.setToolTipText("Cut");
		toolButCopy.setToolTipText("Copy");
		toolButPaste.setToolTipText("Paste");

		//PopupMenu 만들기
		popup.add(pCopy);
		popup.add(pCut);
		popup.add(pPaste);
		popup.add(pDelete);
		popup.add(pBackColor);
		popup.add(pFont);
		popup.setInvoker(ta);

		//툴바를 프레임에 붙인다.
		getContentPane().add(toolbar, "North");

		//JFrame을 위한 소스
		setSize(1000,600);
		setVisible(true);
		
		/*
			JFrame이 모니터의 중앙에 위치하도록 하기 위해
			Window클래스의 getToolkit()으로 OS와의 통신을 통해 화면크기를 질의.
			getScreenSize()으로 Dimension의 width와 height의 필드값을 얻는다.
		*/
		Dimension dim = getToolkit().getScreenSize();
		setLocation(dim.width/2 - 600, dim.height/2 - 400);

		/*
			프레임의 X버튼의 기능에 창을 닫지 않도록 설정한다.
			윈도우 리스너의 메서드에서 quitFile()를 호출한다.
			quitFile()은 메뉴아이템의 QUIT와 함께 쓰는 메서드이다.
		*/
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				me.quitFile();
			}
		});
	}
	
	public static void main(String[] args) {
		new JEditor();		
	}
}

 