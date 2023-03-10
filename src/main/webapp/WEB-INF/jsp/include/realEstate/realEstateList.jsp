<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="search-box">
	<form class="d-flex" action="/myroom/user/map">
	    <input class="form-control" name="local" type="search" placeholder="동 단위로 검색 가능합니다 ex) 역삼동, 삼성동" aria-label="Search">
		<button class="btn btn-outline-success btn_search" type="submit">Search</button>
	</form>	
</div>
<div class="map-list mt-5">
	<c:forEach var="realEstate" items="${realEstateList}">
		<div class="realEstate-card my-3">
			<p class="id" id="${realEstate.zIndex}" data-id="${realEstate.zIndex}"></p>
			<div class="d-flex justify-content-between align-items-center my-2">
				<div>
					<img alt="임시 이미지" src="/static/img/noImage.jpg">
				</div>
				<div>
					<div>${realEstate.residencetype}</div>
					<div>${realEstate.salestype}</div>
					<div>${realEstate.area}평</div>
					<a href="/myroom/items/${realEstate.zIndex}">
						<button type="button" class="btn-detail btn btn-info" value="자세히 보기">자세히 보기</button>
					</a>
				</div>
				<div>
				</div>
			</div>
		</div>
	</c:forEach>
</div>
