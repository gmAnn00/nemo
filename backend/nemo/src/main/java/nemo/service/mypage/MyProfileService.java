package nemo.service.mypage;

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
