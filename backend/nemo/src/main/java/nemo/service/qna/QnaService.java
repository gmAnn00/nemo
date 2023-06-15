package nemo.service.qna;

import java.util.List;

import nemo.dao.qna.QnaDAO;
import nemo.vo.qna.QnaVO;

public class QnaService {
	QnaDAO qnaDAO;
	
	public QnaService() {
		qnaDAO=new QnaDAO();
	}
	
	public List<QnaVO> listArticles() {
		List<QnaVO> articlesList = qnaDAO.selectAllQnas();
		return articlesList;
	}
}
