package nemo.service.groupSearch;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nemo.dao.groupSearch.GroupSearchDAO;

public class GroupSearchService {
	GroupSearchDAO searchDAO;
	

	public GroupSearchService() {
		searchDAO = new GroupSearchDAO();
	}

	public List<Map> search(Map searchMap) {
		List<Map> resultList = new ArrayList<>();

		resultList = searchDAO.search(searchMap);
		
		return resultList;
	}

}
