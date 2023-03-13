<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<div class="rooms my-5 p-5">
	<table class="table align-middle table-hover text-center">
		<thead>
			<tr>
				<th>#</th>
				<th>등록번호</th>
				<th>address</th>
				<th>price</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="realEstate" items="${realEstateList}" varStatus="status">
				<div class="roomCard w-100">
					<tr>
						<th>${status.count}</th>
						<td>${realEstate.id}</td>
						<td>${realEstate.address}</td>
						<c:choose>
							<c:when test="${realEstate.sales_type == '매매'}">
								<td>매매가 : ${realEstate.sales_price}</td>
							</c:when>
							<c:when test="${realEstate.sales_type == '전세'}">
								<td>보증금 : ${realEstate.deposit}</td>
							</c:when>
							<c:when test="${realEstate.sales_type == '월세'}">
								<td>월세 : ${realEstate.deposit}/${realEstate.monthRentPrice}</td>
							</c:when>
						</c:choose>
						<td><button class="btn btn-secondary" onclick="location.href='/myroom/items/${realEstate.id}'">자세히 보기</button></td>
						<td><button class="del-btn btn btn-secondary" data-delete-target="${realEstate.id}">매물 내리기</button></td>
					</tr>
				</div>
			</c:forEach>
		</tbody>
	</table>
</div>

<script>
	$(document).ready(function() {
		$('.del-btn').on('click', function() {
			let realEstateId = $(this).data("delete-target");

			$.ajax({
				type:"POST"
				, url:"/delete_room"
				, data: {"realEstateId":realEstateId}
			
				, success : function(data) {
					if (data.code == 1){
						alert("삭제 완료");
						location.reload(true);
					}
				}
			});
		});
	});
</script>