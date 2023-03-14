<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="rooms my-5 p-5">
	<table class="table align-middle table-hover text-center">
		<thead>
			<tr>
				<th>등록번호</th>
				<th>address</th>
				<th>상태</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="reservation" items="${reservationList}" varStatus="status">
				<div class="roomCard w-100">
					<tr>
						<th>${reservation.id}</th>
						<td>${reservation.jibunAddress}</td>
						<td>${reservation.status}</td>
						<td><button class="expose-comment btn btn-secondary" 
						data-real-estate-id="${reservation.realEstateId}" data-reservation-id="${reservation.id}">후기 작성</button></td>
					</tr>
				</div>
			</c:forEach>
		</tbody>
	</table>
	<div id="commentBox" class="d-none">
		<div class="">
			<input id="realEstateId">
			<input id="reservationId">
		</div>
		<textarea class="form-control" rows="" cols=""></textarea>
		<button class="add-comment form-control">후기 작성</button>
	</div>
</div>

<script>
	$(document).ready(function() {
		$('.expose-comment').on('click', function() {
			if ($('#commentBox').hasClass("d-none")) {
				$('#commentBox').removeClass("d-none")
			} else {
				$('#commentBox').addClass("d-none")
			}
			let realEstateId = $(this).data('realEstateId');
			let reservationId = $(this).data('reservationId');
			
			$('#realEstateId').val(realEstateId);
			$('#reservationId').val(reservationId);
			

		});
		
		$('.add-comment').on('click', function() {
			let realEstateId = $('#realEstateId').val();
			let reservationId = $('#reservationId').val();
			let content = $(this).prev().val();
			
			$.ajax({
				type:"POST"
				, url:"/add_comment"
				, data: {
					"realEstateId":realEstateId,
					"reservationId":reservationId,
					"content":content
				}
			
				, success : function(data) {
					if (data.code == 1) {
						alert("소중한 후기 감사합니다.")
						$('#commentBox').addClass("d-none")
						location.reload(true);
					} else {
						alert("error")
						$('#commentBox').addClass("d-none")
					}
				}
			});
		});
	});
</script>