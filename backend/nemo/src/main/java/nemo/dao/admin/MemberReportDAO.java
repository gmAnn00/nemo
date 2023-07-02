package nemo.dao.admin;

public class MemberReportDAO {
	//"select u.*, A.report_cnt from user_tbl u left outer join (select accused_id, count(*) as report_cnt from mreport_tbl group by accused_id) A on A.accused_id = u.user_id"

}
