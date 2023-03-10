<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="client-room-detail">
	<p class="fw-bolder">중개사 메모 수정하기</p>
	<textarea id="realtorComment" class="form-control" rows="6" placeholder="아직 중개사님의 메모가 없습니다."></textarea>	
	<button id="update" class="mt-1 btn btn-info form-control" type="button" data-update="">수정하기</button>
	<p class="fw-bolder mt-2">예약 현황</p>
	<table class="table-wrap table align-middle text-center">
		<thead>
			<tr>
				<th>이름</th>
				<th>연락처</th>
				<th>처리상태</th>
			</tr>
		</thead>
		<tbody id="temp">
<%-- 			<c:forEach var="reservatedUser" items="${reservatedUserList}" varStatus="status"> --%>
<!-- 				<tr> -->
<%-- 					<td>${status.index}</td> --%>
<!-- 					<td>reservatedUser.name</td> -->
<!-- 					<td>01027103319</td> -->
<!-- 					<td>처리중</td> -->
<!-- 				</tr> -->
<%-- 			</c:forEach> --%>
		</tbody>
	</table>
	<button id="deleteRealtorId" class="mt-1 btn btn-danger form-control" type="button" data-delete="">매물 포기</button>
</div>

<script>
	$(document).ready(function() {
		$('#update').on('click', function() {
			let comment = $('#realtorComment').val();
			let realEstateId = $('#update').data('update');
			
			$.ajax({
				type:"PUT"
				, url:"/set_realtor_comment"
				, data: {
					"comment":comment,
					"realEstateId":realEstateId
				}
			
				, success : function(data) {
					if (data.code == 1) {
						alert("수정완료");
						location.reload(true);
					}
				}
			});
		});
		
		$('#deleteRealtorId').on('click', function() {
			let realEstateId = $('#deleteRealtorId').data('delete');
			alert(realEstateId);
			
			$.ajax({
				type:"POST"
				, url:"/drop_realEstate"
				, data: {
					"realEstateId":realEstateId
				}
			
				, success : function(data) {
					if (data.code == 1) {
						alert("해당 매물을 포기하였습니다.");
						location.reload(true);
					}
				}
			});
		});
	});
	
	// append 한 버튼을 위해 새로운 document.on
	$(document).on("click", ".update-status", function(){
		let realEstateId = $('#update').data('update');
		let userId = $(this).data('id');
		let status = $(this).val();
		
		if (status == "대기중") {
			status = "예약중";
			
			$.ajax({
				type:"POST"
				, url:"/update_reservation"
				, data: {
					"realEstateId":realEstateId
					, "userId":userId
					, "status":status
				}
			
				, success : function(data) {
					if (data.code == 1) {
						alert("예약 상태가 변경되었습니다");
						window.location.reload(true);
					}
				}
			});
		} else if (status == "예약중") {
			$.ajax({
				type:"POST"
				, url:"/delete_reservation"
				, data: {
					"realEstateId":realEstateId
				}
			
				, success : function(data) {
					if (data.code == 1) {
						alert("매물이 삭제되었습니다.");
						window.location.reload(true);
					}
				}
			});
		}
		
		
	});
</script>