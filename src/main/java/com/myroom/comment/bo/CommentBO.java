package com.myroom.comment.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myroom.comment.dao.CommentDAO;
import com.myroom.comment.model.CommentCard;

@Service
public class CommentBO {

	@Autowired
	private CommentDAO commentDAO;
	
	public void addComment(int userId, int realEstateId, String content) {
		commentDAO.insertComment(userId, realEstateId, content);
	}
	
	public void deleteCommentByRealEstateId(int realEstateId) {
		commentDAO.deleteCommentByRealEstateId(realEstateId);
	}
	
	public List<CommentCard> getCommentCardListByRealEstateId(int realEstateId) {
		return commentDAO.selectCommentCardListByRealEstateId(realEstateId);
	}
}
