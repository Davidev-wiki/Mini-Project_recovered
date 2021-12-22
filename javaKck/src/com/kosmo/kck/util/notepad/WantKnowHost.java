package com.kosmo.kck.util.notepad;
// 프레임에 IP와 URL를 입력하여 HOST정보와 페이지 소스를 가져오는 클래스

import java.awt.*;
import java.awt.event.*;
import java.net.*;
import javax.swing.*;
import java.io.*;

public class WantKnowHost extends JDialog implements ActionListener{

	Dimension dim;
	//Host, URL 프레임 변수
	JTextField			hoText, urlText;
	JLabel				hoLabel, urlLabel;
	JButton 			okButt, canButt;
	JPanel				pa1, pa2, pa3;
	InetAddress[]		inet=null;
	URL					urlName;
	InputStream		is;
	BufferedReader	br;
	String				ho="", url="";

	public WantKnowHost(){
		super(new JEditor(), "Host를 알려줘요~~" , true);

		hoText = new JTextField(20);
		urlText = new JTextField(20);
		hoLabel = new JLabel("HOST Name ");
		urlLabel = new JLabel("URL Address ");
		okButt = new JButton("확인");
		canButt = new JButton("취소");
		pa1 = 	new JPanel();
		pa2 = new JPanel();
		pa3 = new JPanel();
		pa1.setLayout(new BorderLayout());
		pa2.setLayout(new BorderLayout());

		display();
	}

	public void display(){
		pa1.add(hoLabel, "North");
		pa1.add(urlLabel, "South");
		pa2.add(hoText, "North");
		pa2.add(urlText, "South");
		pa3.add(okButt);
		pa3.add(canButt);

		getContentPane().add(pa1, "West");
		getContentPane().add(pa2, "Center");
		getContentPane().add(pa3, "South");
	
		okButt.addActionListener(this);
		canButt.addActionListener(this);

		Dimension dim = getToolkit().getScreenSize();
		setLocation(dim.width/2 - 155, dim.height/2 - 55);

		setSize(310, 125);
		setVisible(true);
		setResizable(false);

		setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e){
		ho = hoText.getText();
		url = urlText.getText();
		
		if(e.getSource() == okButt){
			//Host이름만을 입력했을 경우~~
			if(ho.length()>0 && url.length() == 0){
				try{
					//호스트 이름에 연결되 모든 IP를 보여준다.
					inet = InetAddress.getAllByName(ho);				
					JEditor.ta.append("HostName : " + inet[0].getHostName() + "\n");
					for(int i=0 ; i<inet.length ; i++){
						JEditor.ta.append("IP : " + inet[i].getHostAddress() + "\n");
					}
					JEditor.ta.append("\n");
				}catch(UnknownHostException uhe){
					//에러가 발생할 경우, USER에게 Dialog를 통해 알려준다.
					 JOptionPane.showMessageDialog(this, "URL이 존재하지 않습니다.", "에러발생", JOptionPane.ERROR_MESSAGE );
				}
			}else if(ho.length() == 0 && url.length()>0){
				try{
					urlName = new URL(url);				
					is = urlName.openStream();
					br = new BufferedReader(new InputStreamReader(is));
					String data;
					while((data = br.readLine()) != null){
						JEditor.ta.append(data + "\n");
					}
				}catch(MalformedURLException ea){
				}catch(IOException ea){
				}catch(Exception ea){
				}
			}else{
				//USER가 아무것도 입력하지 않았을때, USER에게 안내창을 보여준다.
				JOptionPane.showMessageDialog(this, "IP와 URL 중 하나를 입력하세요.", "Information~", JOptionPane.INFORMATION_MESSAGE );
			}	
		}else if(e.getSource() == canButt){
				setVisible(false);
				dispose();
		}
	}
}
