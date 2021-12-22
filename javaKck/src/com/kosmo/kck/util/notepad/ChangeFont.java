package com.kosmo.kck.util.notepad;
//USER의 선택에 따라 원하는 부분의 FontStyle을 바꾸는 클래스

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.border.*;

public class ChangeFont extends JDialog implements ListSelectionListener, ActionListener{
	JPanel						pa1, pa2, pa3, pa4, pa5;
	JList							listF, listS;
	JTextField					textF, textS;
	JScrollPane				scrollF, scrollS;
	JButton						but1, but2, but3;
	JCheckBox					boldCB, italicCB;
	Object						obj1, obj2;

	GraphicsEnvironment	ge;
	String[]						fontNames;
	String[]						fontSizes;

	public ChangeFont(){
		//JDialog의 생성자의 마지막값 true는 모달을 설정해 주는 것으로,
		//폰트바꾸기 창이 뜨면, 부모창인 JEditor의 클릭이 불가하다.
		super(new JEditor(), "폰트바꾸기", true);

		pa1 = new JPanel();
		pa2 = new JPanel();
		pa3 = new JPanel();
		pa4 = new JPanel();
		pa5 = new JPanel();
		textF = new JTextField(" ", 20);
		textS = new JTextField(" " ,20);

		but1 = new JButton("확인");
		but2 = new JButton("취소");
		but3 = new JButton("적용");
		boldCB = new JCheckBox();
		italicCB = new JCheckBox();

		boldCB.setText("<html><body><p><B>BOLD</B></P></body></html>");
		italicCB.setText("<html><body><p><i>ITALIC</i></P></body></html>");

		//현재 시점에 사용 가능한 폰트 이름의 리스트를 보기 위해 GraphicsEnvironment에 질의한다.
		//리턴되는 값은 OS의 통신에 의한 String 배열값이다.
		ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		fontNames = ge.getAvailableFontFamilyNames();
		fontSizes = new String[]{"8", "9", "10", "11", "12", "14", "16", "18", "20", "22", "24", "26", "28", "36", "48", "72"};

		listF = new JList(fontNames);
		listS = new JList(fontSizes);
		//JList에서 처음에 보이는 행수를 지정해 준다.
		listF.setVisibleRowCount(4);
		listS.setVisibleRowCount(4);

		//JList을 JScrollPane에 붙여, JScrollPane을 붙여야 스크롤이 나온다.
		scrollF = new JScrollPane(listF);
		scrollS = new JScrollPane(listS);

		//두 개의 판넬에 border를 적용한다.
		pa1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.RAISED), "Name"));
		pa2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.RAISED), "Size"));
		pa3.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.RAISED), "Style"));

		display();
	}
	   
	public void display(){
		pa1.setLayout(new BorderLayout());
		pa2.setLayout(new BorderLayout());
		pa1.add(textF, "North");
		pa1.add(scrollF, "Center");
		pa2.add(textS, "North");
		pa2.add(scrollS, "Center");
		pa3.add(boldCB);
		pa3.add(italicCB);
		pa4.add(but1);
		pa4.add(but2);
		pa4.add(but3);
		pa5.setLayout(new BorderLayout());
		pa5.add(pa3, "North");
		pa5.add(pa4, "South");
		getContentPane().add(pa1, "West");
		getContentPane().add(pa2, "East");
		getContentPane().add(pa5, "South");

		listF.addListSelectionListener(this);
		listS.addListSelectionListener(this);
		but1.addActionListener(this);
		but2.addActionListener(this);
		but3.addActionListener(this);

		Dimension dim = getToolkit().getScreenSize();
		setLocation(dim.width/2 - 240, dim.height/2 - 130);

		setSize(480, 260);
		setVisible(true);
		setResizable(false);						   

		//프레임의 X버튼을 누르면 창이 종료되도록 한다.
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
	}

	//폰트와 사이즈를 선택하면 TextField에 선택내용을 보여줌
	//
	public void valueChanged(ListSelectionEvent e){
		if(e.getSource() == listF){
			obj1 = listF.getSelectedValue();	
			if(obj1 != null)
				textF.setText(obj1.toString());
		}else if(e.getSource() == listS){
			obj2 = listS.getSelectedValue();
			if(obj2 != null)
				textS.setText(obj2.toString());
		}		
	}

	//폰트 변경 버튼을 누르면 ListSelectionListener에 의해 선택값이 JTextField에 나오도록 한다.
	public void actionPerformed(ActionEvent e){
		//현재 TextArea 글자의 폰트이름과 크기를 가져온다.
		Font f = JEditor.ta.getFont();
		String defaultName = f.getFontName();
		int defaultSize = f.getSize();

		//Font 폴더에서 선택값이 없으면, 현재 Default값을 넣어준다.
		//사용자가 폰트이름, 사이즈를 선택 안 했을 경우, Default값을 넘겨 주어야
		//NullPointException이 발생하지 않는다.
		if(obj1 == null)
			obj1 =defaultName;
		if(obj2 == null)
			obj2=new Integer(defaultSize);

		if(e.getSource() == but1){
			fontChange();
			dispose();
			setVisible(false);
		}else if(e.getSource() == but2){
			dispose();
			setVisible(false);			
		}else if(e.getSource() == but3){
			fontChange();
		}
	}

	//폰트 스타일의 체크 여부에 따라 텍스트에 적용한다.
	//현재는 Bold와 Italic밖에 없기 때문에 이렇게 하나, 항목이 더 많은 경우 다른 코드가 필요하다.
	public void fontChange(){
		if(boldCB.isSelected() && italicCB.isSelected()){
			JEditor.ta.setFont(new Font(obj1.toString(), Font.BOLD | Font.ITALIC, Integer.parseInt(obj2.toString())));
		}else if(boldCB.isSelected() && !(italicCB.isSelected())){
			JEditor.ta.setFont(new Font(obj1.toString(), Font.BOLD, Integer.parseInt(obj2.toString())));				
		}else if(italicCB.isSelected() && !(boldCB.isSelected())){
			JEditor.ta.setFont(new Font(obj1.toString(), Font.ITALIC, Integer.parseInt(obj2.toString())));	
		}else{
			JEditor.ta.setFont(new Font(obj1.toString(), Font.PLAIN, Integer.parseInt(obj2.toString())));					
		}
	}
}
