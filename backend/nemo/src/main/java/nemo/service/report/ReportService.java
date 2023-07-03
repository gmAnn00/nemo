package nemo.service.report;

import nemo.dao.report.ReportDAO;

public class ReportService {
	ReportDAO reportDAO;
	
	public ReportService() {
		reportDAO = new ReportDAO();
	}

	// 이미 신고한 소모임인지 확인
	public Boolean isGReport(int group_id, String user_id) {
		return reportDAO.isGReport(group_id, user_id);
	}
	
	// 소모임 신고
	public void groupReport(int group_id, String user_id) {
		reportDAO.groupReport(group_id, user_id);
	}

	
	// 이미 신고한 소모임 멤버인지 확인
	public Boolean isMReport(String user_id, String accused_id) {
		return reportDAO.isMReport(user_id, accused_id);
	}

	// 소모임 멤버
	public void memberReport(String user_id, String accused_id) {
		reportDAO.memberReport(user_id, accused_id);
		
	}


}
