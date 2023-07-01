package nemo.service.qna;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nemo.dao.qna.QnaDAO;
import nemo.vo.qna.QnaVO;

public class QnaService {
	QnaDAO qnaDAO;
	
	public QnaService() {
		qnaDAO=new QnaDAO();
	}
	
	
	//페이징 부분  >> DAO에서 추가 안함
	public Map helpQnA(Map<String, Integer> paginMap, String user_id, boolean isAdmin) {
		Map atricleMap =new HashMap<>();
		List<QnaVO> articlesList = qnaDAO.selectQnas(paginMap, user_id, isAdmin);
		int totArticles=qnaDAO.selectToArticles();
		atricleMap.put("articlesList", articlesList);
		atricleMap.put("totArticles", totArticles);
		return atricleMap;
		
	}
	
	//리스트 부분
	public List<QnaVO> helpQnA() {
		List<QnaVO> helpQnA = qnaDAO.selectAllQna();
		return helpQnA;
	}
	
	//추가 부분
	public void addArticle(QnaVO qna_id) {
		qnaDAO.insertNewArticle(qna_id);
	}
	
	//내용 보는 부분
	public QnaVO viewArticle(int qna_id) {
		QnaVO qnaVO=null;
		qnaVO=qnaDAO.selectArticle(qna_id);
		return qnaVO;
	}
	
	//내용을 수정하는 부분
	public void modArticle(QnaVO qnaVO) {
		qnaDAO.updateArticle(qnaVO);
	}
	
	//삭제하는 부분
	public List<Integer> removeArticle(int qna_id) {
		List<Integer> qna_idList=qnaDAO.selectRemovedArticles(qna_id);
		qnaDAO.deleteArticle(qna_id);
		return qna_idList;
	}
	
	//답변 달기
	public void addReply(QnaVO qnaVO) {
		qnaDAO.insertNewArticle(qnaVO);
	}
	
	//새로운 글번호 받는 메소드
	public int getNewArticleNo() {
		//int article_no;
		
		return qnaDAO.getNewArticleNo();
	}
}
