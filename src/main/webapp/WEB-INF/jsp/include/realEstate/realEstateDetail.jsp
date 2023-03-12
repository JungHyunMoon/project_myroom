<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${title}</title>
<!-- jquery -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>

<!-- bootstarp5 -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
	crossorigin="anonymous"></script>

<!-- 카카오맵 API -->
<!-- <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=a12e90f6a3acfbdf4b442ae4229a9790"></script> -->
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=a12e90f6a3acfbdf4b442ae4229a9790&libraries=services"></script>

<!-- fa -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css">
<!-- toggle -->
<link
	href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css"
	rel="stylesheet">
<script
	src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>

<!-- css -->
<link rel="stylesheet" type="text/css" href="/static/css/myroom.css">
</head>
<body>
	<div id="wrap">
		<header>
			<jsp:include page="../${type}/header.jsp"></jsp:include>
		</header>
		<section class="d-flex">
			<div class="contents d-flex justify-content-between">
				<div class="content">
					<!-- Button trigger modal -->
					<button type="button" class="no-realtor btn btn-primary d-none"
						data-bs-toggle="modal" data-bs-target="#modalNoRealtor"></button>

					<!-- Modal -->
					<div class="modal fade" id="modalNoRealtor" tabindex="-1"
						aria-labelledby="exampleModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<h1 class="modal-title fs-5" id="exampleModalLabel">이런!</h1>
									<button type="button" class="btn-close" data-bs-dismiss="modal"
										aria-label="Close"></button>
								</div>
								<div class="modal-body">
									이 매물은 아직 중개사에게 배정되지 않았습니다.<br> 찜 하시려면 하단의 찜하기 버튼을 눌러주세요.
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary"
										data-bs-dismiss="modal">닫기</button>
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
							<c:if
								test="${realEstate.realtor_comment == '' || realEstate.realtor_comment == null}">
								메모없음
							</c:if>
						</div>
						<form id="makeReservation" method="POST" action="/add_reservation">
							<div class="form-group d-none">
								<input type="text" id="realEstateId" class=""
									name="realEstateId" value="${realEstate.id}"> <input
									type="text" id="userId" class="" name="userId"
									value="${userId}"> <input type="text" id="realtorId"
									class="" name="realtorId" value="${realEstate.realtor_id}">
							</div>
							<button type="submit" class="btn btn-info w-100 mt-2">중개사와예약
								잡기</button>
						</form>
					</div>
				</div>
				<div class="content">
					<div class="review">
						<div class="text-center my-3">
							<h3 class="">후기</h3>
						</div>
						<c:forEach var="comment" items="${commentList}" varStatus="status">
							<div class="box-border  my-3">
								<div class="reviews">
									<div class="review-card">
										<b>${comment.name}</b>
										<div>
											<c:set var="date"><fmt:formatDate value="${comment.createdAt}" pattern="MM월-dd일" /></c:set> 
											<c:out value="${date}" />
										</div>
										<span>${comment.content}</span>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</section>
	</div>

	<script>
		$(document)
				.ready(
						function() {
							// 내 매물이 아닌 공인중개사 미지정 매물은 modal 노출
							if (($('#loginUserId').val() != $('#userId').val())
									&& ($('#realtorId').val() == null || $(
											'#realtorId').val() == 0)) {
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

								$.post(url, params) // request
								.done(function(data) { // response
									if (data.code == 1) { // 성공
										alert("예약이 성공적으로 등록되었습니다!")
									} else { // 실패
										alert("예약 실패.");
									}
								});
							});
						});
	</script>
</body>
</html>