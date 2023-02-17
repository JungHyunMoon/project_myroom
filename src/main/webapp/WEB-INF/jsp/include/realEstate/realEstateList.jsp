<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="search-box">
	<form class="d-flex" action="/myroom/user/map">
	    <input class="form-control" name="local" type="search" placeholder="Search" aria-label="Search">
		<button class="btn btn-outline-success btn_search" type="submit">Search</button>
	</form>	
</div>
<div class="map-list mt-5">
	<c:forEach var="realEstate" items="${realEstateList}">
		<div class="realEstate-card my-3">
			<div class="d-flex justify-content-between align-items-center my-2">
				<div>
					<img alt="임시 이미지" src="/static/img/noImage.jpg">
				</div>
				<div>
					<div>${realEstate.residencetype}</div>
					<div>${realEstate.salestype}</div>
					<div>${realEstate.area}평</div>
					<div class="id" data-id="${realEstate.zIndex}"></div>
					<form>
						<button type="button" class="btn-detail btn btn-info" value="자세히 보기">자세히 보기</button>
					</form>
				</div>
				<div>
				</div>
			</div>
		</div>
	</c:forEach>
</div>
<script>
	$(document).ready(function() {
		$('.btn-detail').on('click', function(e) {
			e.preventDefault();
			let realEstateId = $(this).parent().siblings("div[class=id]").data("id");
			console.log(realEstateId);
			$.ajax({
				type:"GET"
				, url:"/myroom/item" + realEstateId
				data
			});
		});
	});
</script>