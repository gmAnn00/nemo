package nemo.service.common;

import java.util.List;

import nemo.dao.common.IndexDAO;
import nemo.vo.group.GroupVO;
import nemo.vo.user.InterestsVO;
import nemo.vo.user.UserVO;

public class IndexService {
	IndexDAO indexDAO;
	
	public IndexService() {
		indexDAO = new IndexDAO();
	}

	public UserVO findUserById(String user_id) {
		return indexDAO.findUserById(user_id);
	}

	// 랜덤 소모임
	public List<GroupVO> findRandomGroup() {
		return indexDAO.findRandomGroup();
	}

	// 유저의 관심사 가져옴
	public List<InterestsVO> findInterests(String user_id) {
		return indexDAO.findInterests(user_id);
	}

	// 유저의 관심사 소모임 가져옴
	public List<GroupVO> findInterestGroups(List<InterestsVO> interestsList) {
		return indexDAO.findInterestGroups(interestsList);
	}

	// 가까운 소모임
	public List<GroupVO> findNearGroups(String user_addr1) {
		return indexDAO.findNearGroups(user_addr1);
	}
	
	
	
}
