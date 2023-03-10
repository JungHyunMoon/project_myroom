<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Button trigger modal -->
<button type="button" class="no-realtor btn btn-primary d-none" data-bs-toggle="modal" data-bs-target="#modalNoRealtor"></button>

<!-- Modal -->
<div class="modal fade" id="modalNoRealtor" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="exampleModalLabel">이런!</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        이 매물은 아직 중개사에게 배정되지 않았습니다.<br>
        찜 하시려면 하단의 찜하기 버튼을 눌러주세요.
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
        <button type="button" class="btn btn-primary">찜하기</button>
      </div>
    </div>
  </div>
</div>
<div class="detail-box">
	<div class="d-flex justify-content-around align-items-center">
		<div>
			<img alt="임시 이미지" src="/static/img/noImage.jpg">
		</div>
		<div>
			<c:choose>
				<c:when test="${realEstate.sales_type == '매매'}">
					<div>매매</div>
					<div>매매가 : ${realEstate.sales_price}</div>
				</c:when>

				<c:when test="${realEstate.sales_type == '전세'}">
					<div>전세</div>
					<div>보증금 : ${realEstate.deposit}</div>
				</c:when>

				<c:when test="${realEstate.sales_type == '월세'}">
					<div>월세</div>
					<div>${realEstate.deposit}/${realEstate.monthRentPrice}</div>
				</c:when>
			</c:choose>
			<div>${realEstate.area}평</div>
		</div>
	</div>
	<div class="box-border mt-3">
		<div>
			<p class="fw-bolder">제목</p>
		</div>
		${realEstate.title}
	</div>
	<div class="box-border mt-3">
		<div>
			<p class="fw-bolder">설명</p>
		</div>
		${realEstate.description}
	</div>
	<div class="box-border mt-3">
		<div>
			<p class="fw-bolder">중개사 메모</p>
		</div>
		${realEstate.realtor_comment}
		<c:if test="${realEstate.realtor_comment == '' || realEstate.realtor_comment == null}">
			메모없음
		</c:if>
	</div>
	<form id="makeReservation" method="POST" action="/add_reservation">
		<div class="form-group">
			<input type="text" id="realEstateId" class="" name="realEstateId" value="${realEstate.id}">
			<input type="text" id="userId" class="" name="userId" value="${userId}">
			<input type="text" id="realtorId" class="" name="realtorId" value="${realEstate.realtor_id}">
		</div>
		<button type="submit" class="btn btn-info w-100 mt-2">중개사와예약 잡기</button>
	</form>
</div>

<script>
	$(document).ready(function() {
		// 내 매물이 아닌 공인중개사 미지정 매물은 modal 노출
		if (($('#loginUserId').val() != $('#userId').val()) && ($('#realtorId').val() == null || $('#realtorId').val() == 0)) {
			console.log($('#realtorId').val());
			$('.no-realtor').click();
			$('#makeReservation').addClass("d-none")
		}
		console.log($('#realtorId').val());
		
		$('#makeReservation').on('submit', function(e) {
			e.preventDefault();
			let realEstateId = $('#realEstateId');
			let userId = $('#userId');
			
			let url = $(this).attr('action');
			let params = $(this).serialize();
			
			console.log(url);
			console.log(params);
			
			$.post(url, params)		// request
			.done(function(data) {	// response
				if (data.code == 1) {	// 성공
					alert("예약이 성공적으로 등록되었습니다!")
				} else {	// 실패
					alert("예약 실패.");
				}
			});
		});
	});
</script>