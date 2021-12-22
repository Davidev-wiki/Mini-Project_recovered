package com.kosmo.kck.util.time;

import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Calendar;
 
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

// 나중에 공부하기 
// ---- 스레드 관련 클래스 
// java.lang.Runnable 인터페이스 클래스
// java.lang.Thread 클래스

// ---- GUI 관련 클래스 
// javax.swing.JFrame 제프레임 클래스
// java.awt.FlowLayout; 플로우레이아웃 클래스 
// java.awt.Font; 폰트 클래스 
// javax.swing.JLabel; 제이라벨 클래스 


public class Ex_DigitalClock extends JPanel implements Runnable{

	private static final long serialVersionUID = 1L;

	private Thread thread;
    private JLabel label;
 
    public Ex_DigitalClock(){
        this.setLayout(new FlowLayout());
 
        label = new JLabel();
        label.setFont(new Font("Serif",Font.PLAIN, 20));
 
        if(thread == null){
             
            //this의 의미는 Runnable이 구현된 객체를 뜻함(DigitalClock)
            thread = new Thread(this);
            thread.start();
        }
        
        this.add(label);
        this.setBounds(5,5,500,50);
        this.setVisible(true);
    }
    
    public void run(){
        while(true){
           
        	Calendar cal = Calendar.getInstance();
            String now = 	cal.get(Calendar.YEAR)+"년"+
		                    (cal.get(Calendar.MONTH)+1)+"월"+
		                    cal.get(Calendar.DATE)+"일"+
		                    cal.get(Calendar.HOUR)+"시"+
		                    cal.get(Calendar.MINUTE)+"분"+
		                    cal.get(Calendar.SECOND)+"초";
            
            label.setText(now);
           
            try{
                Thread.sleep(1000);
            }catch(InterruptedException e){               
            }
        }
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame.setDefaultLookAndFeelDecorated(true);
        new Ex_DigitalClock();
	}
}
