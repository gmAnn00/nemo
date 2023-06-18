package nemo.service.mypage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import nemo.dao.mypage.MyProfileDAO;
import nemo.vo.mypage.UserVO;

public class MyProfileService {
	MyProfileDAO dao;	
	
	public MyProfileService() {
		dao = new MyProfileDAO();	
	}	
	
	//마이페이지 프로필
	public UserVO searchProfileById(String user_id) {
		UserVO userVO = dao.searchMyProfileById(user_id);
		return userVO;
	}
	
	//마이페이지 관심사 조회
	public List<String> searchProInterestById(String user_id) {
		List<String> interestList = dao.searchProInterestById(user_id);
		return interestList;
	}
	
	//회원정보 수정
	public void modProfile(UserVO userVO) {
		dao.modProfile(userVO);		
	}
	//이미지 수정
	public void modUserImg(UserVO userVO) {
		dao.modUserimg(userVO);
	}

	
	//회원탈퇴
	public void delMember(String user_id, String delpassword) {
		dao.delMember(user_id, delpassword);	
	}
	
}
