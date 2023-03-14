package com.myroom.comment.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.myroom.comment.model.CommentCard;

@Repository
public interface CommentDAO {

	public void insertComment(
			@Param("userId") int userId, 
			@Param("realEstateId") int realEstateId, 
			@Param("content") String content);
	
	public void deleteCommentByRealEstateId(int realEstateId);
	
	public List<CommentCard> selectCommentCardListByRealEstateId(int realEstateId);
}
