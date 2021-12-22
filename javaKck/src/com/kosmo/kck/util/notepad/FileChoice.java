package com.kosmo.kck.util.notepad;
//filechooser에서의 FileFilter 구현 클래스

import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.*;

//FileFilter는 abstract클래스이기 때문에 반드시 오버라이딩 해줄 메서드가 있다.
//자손클래스가 abstract가 아닌 이상.....
public class FileChoice extends FileFilter {
	String tmp="";
	String tmp1="";
	
	//여러개의 파일필터를 하기 위해, 생성자의 매개변수로 값을 전달받는다.
	//이 값을 통해, 필터항목에 넣을 String값을 받고,
	//lastIndex와 substring를 통해 확장자를 얻어낸다.
	FileChoice(String tmp){
		this.tmp = tmp;
	    int j = tmp.lastIndexOf('.');
		this.tmp1 = tmp.substring(j+1).toLowerCase();
	}

	public boolean accept(File f) {
		if (f.isDirectory()) {
            return true;
     }

	String extension = getExtension(f);

	if (extension != null) {
	    if (extension.equals(tmp1)){
		    return true;
		}else {
            return false;
        }
  	}
		return false;
    }

	//반드시 구현해야 하는 메서드로, 파일추저의 아래 파일필터 명으로 들어가 문자열이다.
	 public String getDescription() {
        return tmp;
     }

	//폴더 안에 있는 파일중, 원하는 확장자의 파일만을 골라내는 메서드(리턴값이 파일명이다.)
    public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
	    }
		    return ext;
	    }
}
