<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="new-rooms-box my-5 p-5">
	<h4 class="text-center fa-"><em>중개사님의 지역을 기반으로 구단위 신규 등록 매물을 보여주고있어요!</em><br>현재 중개사님은 "${local2}"에 위치하고있습니다.</h4>
	<table class="table align-middle table-hover text-center">
		<thead>
			<tr>
				<th>#</th>
				<th>등록번호</th>
				<th>지번주소</th>
				<th>price(만원)</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${realEstateList eq ''}">
				신규매물이 없어요!
			</c:if>
			<c:if test="${realEstateList ne ''}">
				<c:forEach var="realEstate" items="${realEstateList}" varStatus="status">
					<div class="roomCard w-100">
						<tr>
							<th>${status.count}</th>
							<td>${realEstate.id}</td>
							<td>${realEstate.jibunAddress}</td>
							<c:choose>
								<c:when test="${realEstate.sales_type == '매매'}">
									<td>매매 : ${realEstate.sales_price}</td>
								</c:when>
								<c:when test="${realEstate.sales_type == '전세'}">
									<td>전세 : ${realEstate.deposit}</td>
								</c:when>
								<c:when test="${realEstate.sales_type == '월세'}">
									<td>월세 : ${realEstate.deposit}/${realEstate.monthRentPrice}</td>
								</c:when>
							</c:choose>
							<td><button class="btn btn-secondary" onclick="location.href='/myroom/items/${realEstate.id}'">자세히 보기</button></td>
							<td><button class="get-btn btn btn-secondary" data-target="${realEstate.id}">매물 접수하기</button></td>
						</tr>
					</div>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
</div>

<script>
	$(document).ready(function() {
		$('.get-btn').on('click', function() {
			let realEstateId = $(this).data('target');
			let realtorId = ${realtorId};
			
			$.ajax({
				type:"POST"
				, url:"/set_room_realtor"
				, data: {"realEstateId":realEstateId, "realtorId":realtorId}
			
				, success : function(data) {
					if (data.code == 1){
						alert("인수 완료");
						location.reload(true);
					}
				}
			});
		});
	}); 
</script>