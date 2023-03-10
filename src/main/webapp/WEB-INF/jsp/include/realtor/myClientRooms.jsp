<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${reservationCount != null && reservationCount != ''}">
	<small class="text-center fa-"><em>중개사님의 매물을 보고싶은 고객이 <b class="text-danger">${reservationCount}</b>명이 있습니다. 확인해보세요!</em></small>
</c:if>
<div class="my-client-room">
	<table class="table align-middle table-hover text-center">
		<thead>
			<tr>
				<th>상태</th>
				<th>지번주소</th>
				<th>price<br>(만원)</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${realEstateList eq ''}">
				중개사님에게 배정된 부동산 매물이 존재하지 않아요!
			</c:if>
			<c:if test="${realEstateList ne ''}">
				<c:forEach var="realEstate" items="${realEstateList}" varStatus="status">
					<div class="roomCard w-100">
						<tr>
							<td>예약중</td>
							<td>${realEstate.jibunAddress}</td>
							<c:choose>
								<c:when test="${realEstate.sales_type == '매매'}">
									<td>매매 : ${realEstate.sales_price}</td>
								</c:when>
								<c:when test="${realEstate.sales_type == '전세'}">
									<td>전세 : ${realEstate.deposit}</td>
								</c:when>
								<c:when test="${realEstate.sales_type == '월세'}">
									<td>월세 : <br> ${realEstate.deposit}/${realEstate.monthRentPrice}</td>
								</c:when>
							</c:choose>
							<td><button id="${realEstate.id}" class="btn-detail btn btn-secondary" data-id="${realEstate.id}">자세히</button></td>
						</tr>
					</div>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
	<div>${test}</div>
</div>

<script>
	$(document).ready(function() {
		$('.btn-detail').on('click', function() {
			let realEstateId = $(this).data("id");
			$('#update').data('update', realEstateId);
			$('#deleteRealtorId').data('delete', realEstateId);
			let parent = document.getElementById('temp');
			parent.replaceChildren();

			console.log(realEstateId);
			$.ajax({
				type:"POST"
				, url:"/get_room"
				, data: {
					"realEstateId":realEstateId
				}
				
				, success : function(data) {
					$('#realtorComment').text(data.realEstate.realtor_comment);
					// append로 td 추가하기
					
					var list = data.reservatedUserList;
					console.log(list[0])
					for (var i = 0; i < list.length; i++){
						$('.client-room-detail > table > tbody').append(
								'<tr>' +
									'<td>' + list[i].user.name + '</td>' +
									'<td>' + list[i].user.phoneNumber + '</td>' +
									'<td><button type="button" class="update-status btn" data-id=' + list[i].user.id + ' value=' + list[i].status + '>' + list[i].status + '</button></td>' +
								'</tr>'
								);
					}
				}
			});
		});
	});
</script>