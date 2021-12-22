package com.kosmo.kck.member;

import com.kosmo.kck.common.CodeUtil;

public class KckMemberVO {

	private String knum;    	// 1
	private String kname;    	// 2
	private String kid;    		// 3
	private String kpw;    		// 4
	private String kbirth;      // 5
	private String kgender;     // 6
	private String ktel;    	// 7
	private String khp;    		// 8
	private String kemail;    	// 9
	private String kaddr;    	// 10
	private String khobby;    	// 11
	private String kphoto;    	// 12
	private String kskill;    	// 13
	private String kjob;    	// 14
	private String deleteyn;    // 15
	private String insertdate;  // 16
	private String updatedate;  // 17

	// 생성자
	public KckMemberVO() {

	}

	// 생성자
	public KckMemberVO(String knum, String kname, String kid, String kpw, String kbirth, String kgender, String ktel,
			String khp, String kemail, String kaddr, String khobby, String kphoto, String kskill, String kjob,
			String deleteyn, String insertdate, String updatedate) {

		this.knum = knum;
		this.kname = kname;
		this.kid = kid;
		this.kpw = kpw;
		this.kbirth = kbirth;
		this.kgender = kgender;
		this.ktel = ktel;
		this.khp = khp;
		this.kemail = kemail;
		this.kaddr = kaddr;
		this.khobby = khobby;
		this.kphoto = kphoto;
		this.kskill = kskill;
		this.kjob = kjob;
		this.deleteyn = deleteyn;
		this.insertdate = insertdate;
		this.updatedate = updatedate;
	}

	// get() 함수
	public String getKnum() {
		return knum;
	}

	public String getKname() {
		return kname;
	}

	public String getKid() {
		return kid;
	}

	public String getKpw() {
		return kpw;
	}

	public String getKbirth() {
		return kbirth;
	}

	public String getKgender() {
		return kgender;
	}

	public String getKtel() {
		return ktel;
	}

	public String getKhp() {
		return khp;
	}

	public String getKemail() {
		return kemail;
	}

	public String getKaddr() {
		return kaddr;
	}

	public String getKhobby() {
		return khobby;
	}

	public String getKphoto() {
		return kphoto;
	}

	public String getKskill() {
		return kskill;
	}

	public String getKjob() {
		return kjob;
	}

	public String getDeleteyn() {
		return deleteyn;
	}

	public String getInsertdate() {
		return insertdate;
	}

	public String getUpdatedate() {
		return updatedate;
	}

	// set()함수
	public void setKnum(String knum) {
		this.knum = knum;
	}

	public void setKname(String kname) {
		this.kname = kname;
	}

	public void setKid(String kid) {
		this.kid = kid;
	}

	public void setKpw(String kpw) {
		this.kpw = kpw;
	}

	public void setKbirth(String kbirth) {
		this.kbirth = kbirth;
	}

	public void setKgender(String kgender) {
		this.kgender = kgender;
	}

	public void setKtel(String ktel) {
		this.ktel = ktel;
	}

	public void setKhp(String khp) {
		this.khp = khp;
	}

	public void setKemail(String kemail) {
		this.kemail = kemail;
	}

	public void setKaddr(String kaddr) {
		this.kaddr = kaddr;
	}

	public void setKhobby(String khobby) {
		this.khobby = khobby;
	}

	public void setKphoto(String kphoto) {
		this.kphoto = kphoto;
	}

	public void setKskill(String kskill) {
		this.kskill = kskill;
	}

	public void setKjob(String kjob) {
		this.kjob = kjob;
	}

	public void setDeleteyn(String deleteyn) {
		this.deleteyn = deleteyn;
	}

	public void setInsertdate(String insertdate) {
		this.insertdate = insertdate;
	}

	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}

	// KckMemberVO print()함수
	public static void printKckMemberVO(KckMemberVO kvo) {
		System.out.println("KckMemberVO.printKckMemberVO()함수 진입");
		
		System.out.print(kvo.getKnum() + ",");
		System.out.print(kvo.getKname() + ",");
		System.out.print(kvo.getKid() + ",");
		System.out.print(kvo.getKpw() + ",");
		System.out.print(CodeUtil.birth(kvo.getKbirth()) + ",");
		System.out.print(CodeUtil.gender(kvo.getKgender()) + ",");
		System.out.print(CodeUtil.tel(kvo.getKtel()) + ",");
		System.out.print(CodeUtil.hp(kvo.getKhp()) + ",");
		System.out.print(kvo.getKemail() + ",");
		System.out.print(kvo.getKaddr() + ",");
		System.out.print(CodeUtil.hobby(kvo.getKgender()) + ",");
		System.out.print(kvo.getKphoto() + ",");
		System.out.print(kvo.getKskill() + ",");
		System.out.print(CodeUtil.job(kvo.getKgender()) + ",");
		System.out.print(kvo.getDeleteyn() + ",");
		System.out.print(kvo.getInsertdate() + ",");
		System.out.println(kvo.getUpdatedate());

	}

	// KckMemberVO println()함수
	public static void printlnKckMemberVO(KckMemberVO kvo) {
		System.out.println("KckMemberVO.printlnKckMemberVO()함수 진입");

		System.out.println("kvo.getKnum : " + kvo.getKnum());
		System.out.println("kvo.getKname : " + kvo.getKname());
		System.out.println("kvo.getKid : " + kvo.getKid());
		System.out.println("kvo.getKpw : " + kvo.getKpw());
		System.out.println("kvo.getKbirth : " + CodeUtil.birth(kvo.getKbirth()));
		System.out.println("kvo.getKgender : " + CodeUtil.gender(kvo.getKgender()));
		System.out.println("kvo.getKtel : " + CodeUtil.tel(kvo.getKtel()));
		System.out.println("kvo.getKhp : " + CodeUtil.hp(kvo.getKhp()));
		System.out.println("kvo.getKemail : " + kvo.getKemail());
		System.out.println("kvo.getKaddr : " + kvo.getKaddr());
		System.out.println("kvo.getKhobby : " + CodeUtil.hobby(kvo.getKhobby()));
		System.out.println("kvo.getKphoto : " + kvo.getKphoto());
		System.out.println("kvo.getKskill : " + kvo.getKskill());
		System.out.println("kvo.getKjob : " + CodeUtil.job(kvo.getKjob()));
		System.out.println("kvo.getDeleteyn : " + kvo.getDeleteyn());
		System.out.println("kvo.getInsertdate : " + kvo.getInsertdate());
		System.out.println("kvo.getUpdatedate : " + kvo.getUpdatedate());

	}
}
