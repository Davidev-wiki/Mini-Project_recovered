package com.kosmo.kck.util.notepad;
//Menu, ToolBar, Popup의 이벤트 처리 클래스

import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.awt.*;
import javax.swing.border.*;

public class MenuEvent extends JFrame implements ActionListener{
	public void actionPerformed(ActionEvent e){

		// File의 MenuItem과 ToolBar의 이벤트를 구별
		// || 를 이용하여, 동일한 기능의 이벤트를 함께 처리한다.
		// 메서드의 재사용을 용이하게 하기 위해 아랫동네에 사용자 메서드를 구현한다.
		if(e.getSource() == JEditor.mNew || e.getSource() == JEditor.toolButNew){
			newFile();
		}else if(e.getSource() == JEditor.mOpen || e.getSource() == JEditor.toolButOpen){
			openFile();
		}else if(e.getSource() == JEditor.mSave || e.getSource() == JEditor.toolButSave){
			saveFile();
		}else if(e.getSource() == JEditor.mQuit){
			quitFile();
		}
	
		// Edit의 MenuItem 과 ToolBar의 이벤트 구별
		//	start와 end는 JTextArea의 블럭 선택 부분의 첫지점과 끝지점의 값을 저장한다.
		int start = JEditor.ta.getSelectionStart();
		int end = JEditor.ta.getSelectionEnd();

		if(e.getSource() == JEditor.mCut || e.getSource() == JEditor.toolButCut || e.getSource() == JEditor.pCut){
			selectedDelete(start, end);				
		}else if(e.getSource() == JEditor.mCopy || e.getSource() == JEditor.toolButCopy || e.getSource() == JEditor.pCopy){
			selectedCopy();
		}else if(e.getSource() == JEditor.mPaste || e.getSource() == JEditor.toolButPaste || e.getSource() == JEditor.pPaste){
			selectedPaste(start, end);	
		}else if(e.getSource() == JEditor.mDelete || e.getSource() == JEditor.pDelete){
			selectedDelete(start, end);
			JEditor.selection ="";	//Delete기능이기 때문에 삭제 후, selection값을 초기화시킨다.
		}

		// Form과 Net, Help의 MenuItem 구별
		if(e.getSource() == JEditor.mFont || e.getSource() == JEditor.pFont){
				//폰트를 바꾸기 위한 기능은 FontTest클래스에 구현되어 있다.
				new ChangeFont();

		}else if(e.getSource() == JEditor.mBackColor || e.getSource() == JEditor.pBackColor){
				//ColorChooser에서 선택한 Color의 값을 가져오는 방법
				Color c = JEditor.cc.showDialog(new JEditor(), "Background Color", Color.white ); 			   
				if(c!=null)
					JEditor.ta.setBackground(c);										 
				
		}else if(e.getSource() == JEditor.mHost){
				//호스트의 정보를 가져오는 기능은 WantKnowHost에 구현되어 있다.
				new WantKnowHost();

		}else if(e.getSource() == JEditor.mInfo){
				helpInfo();			
		}
	}
	
	//새파일(JTextArea에 내용이 있으면, 저장 여부를 물러본다.)
	//showConfirmDialog가 버튼 클릭에 따라 int값을 리턴함으로 이 값으로 if문을 구현한다.
	public void newFile(){
		if(JEditor.ta.getText().length() > 0){
			int i = JOptionPane.showConfirmDialog(this, "내용을 저장하시겠습니까?", "새글 확인", 
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
			if(i==JOptionPane.OK_OPTION){
				saveFile();	
			}else if(i==JOptionPane.NO_OPTION){
				JEditor.ta.setText("");	
			}
		}else{
			JEditor.ta.setText("");
		}
	}

	//파일열기(showOpenDialog() 이용)
	public void openFile(){
		if(JEditor.fc.showOpenDialog(new JEditor()) == JFileChooser.APPROVE_OPTION){
			File file = JEditor.fc.getSelectedFile();
			String s = "";
			String total = "";

		try{
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			while((s=br.readLine()) != null){
				total += (s+"\n");
			}
			JEditor.ta.setText(total);
			br.close();
			fr.close();
		}catch(FileNotFoundException e){
		}catch(IOException e){}
 	}
	}

	//파일저장(showSaveDialog() 이용)
	public void saveFile(){
		if(JEditor.fc.showSaveDialog(new JEditor()) == JFileChooser.APPROVE_OPTION){		
			File file = JEditor.fc.getSelectedFile();
			String total = "";
			total = JEditor.ta.getText();

			try{
				FileWriter fw = new FileWriter(file);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(total);
				bw.close();
				fw.close();
			}catch(FileNotFoundException e){
			}catch(IOException e){}
		}	
	}

	//파일닫기
	public void quitFile(){
		Object[] options = {"Quit with Saving", "Quit without Saving", "Cancel"};

		//파일을 닫기 전에 내용이 있으면, 저장여부를 물어본다.
		//사용자 정의의 Dialog를 작성한다. Object배열을 이용하여 Dialog의 버튼명을 설정한다.
		if(JEditor.ta.getText().length() > 1){
			int i = JOptionPane.showOptionDialog(this, "정말 종료하시겠습니까?", "종료 확인", 
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null,   options,   options[2]);

			if(i==JOptionPane.OK_OPTION){
				saveFile();	
				dispose();
				System.exit(0);				
			}else if(i==JOptionPane.NO_OPTION){
				dispose();
				System.exit(0);										
			}		
		}else{
			 int i = JOptionPane.showConfirmDialog(this, "정말 종료하시겠습니까?", "종료 확인", 
					JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE );
			if(i==JOptionPane.OK_OPTION){
				dispose();
				System.exit(0);	
			}
		}
	}

	//Cut, Delete
	public void selectedDelete(int start, int end){
		JEditor.selection = JEditor.ta.getSelectedText();
		if(JEditor.selection == null)
			return;
		JEditor.ta.replaceRange("",start, end);
	}

	//Copy
	public void selectedCopy(){
		JEditor.selection = JEditor.ta.getSelectedText();
	}

	//Paste
	public void selectedPaste(int start, int end){
		if(JEditor.selection == null)
			return;
		JEditor.ta.replaceRange(JEditor.selection, start, end);
	}

	public void helpInfo(){
		//Label에 html코드를 이용하여 붙인다. 
		//Label과 Button에는 html을 이용하여 사용할 수 있다.
		JDialog helpFrame = new JDialog(new JEditor(), "Information", true);
		String initial = "<html>\n" + "<body ><p align=center><font face=Monotype Corsiva><font size=+10>SejongDaewang</font>" +
							"<font face=서울가을바람M>&nbsp;&nbsp;\nv1.3.1</font></font></p>\n" +
							"<p align=center>" +	"<font face=서울가을바람M>&nbsp;</font></p>" + 
							"<p>&nbsp;</p>" +	"<p align=center>" +	
							"<font face=서울흘기>Copyright (c) &nbsp;2002 ~ 2004 &nbsp;Nam &amp; Moo</font></p>" +
							"<p align=center><font face=서울흘기>" +	"All Right Reserved</font></p></body></html>";
		JLabel infoLabel = new JLabel(initial, JLabel.CENTER);
		JLabel mailAddr = new JLabel("<html><font face=Comic Sans MS><font size=+1 color=blue>Freeware for Everyone</font></font></html>", JLabel.CENTER);
		mailAddr.setBorder(new TitledBorder(new TitledBorder("unkwon0858@hotmail.com"), "sindosiin@hotmail.com",
										TitledBorder.RIGHT, TitledBorder.BOTTOM));
		helpFrame.getContentPane().add(mailAddr, "South");
		helpFrame.getContentPane().add(infoLabel, "Center");

		Dimension dim = getToolkit().getScreenSize();
		helpFrame.setLocation(dim.width/2 - 150, dim.height/2 - 150);

		helpFrame.setSize(300,300);
		helpFrame.setVisible(true);
		helpFrame.setResizable(false);
	}
}
