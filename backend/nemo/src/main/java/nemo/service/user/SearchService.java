package nemo.service.user;

import nemo.dao.user.SearchDAO;
import nemo.vo.user.UserVO;

public class SearchService {
	SearchDAO  dao;
	
	 public SearchService() {
	dao = new SearchDAO();
	}
	 //아이디 찾기 확인 서비스
	public String findidCheck(UserVO userVO) {
		return dao.findidCheck(userVO);
	}
	//비밀번호 찾기 확인 여부 서비스
	public Boolean findpassCheck(UserVO userVO) {
		 return dao.findpassCheck(userVO);
	}
	//비밀번호 찾기 수정 서비스
	public void findpassinfo(UserVO userVO) {
		 dao.findpassinfo(userVO);
	}	
}
