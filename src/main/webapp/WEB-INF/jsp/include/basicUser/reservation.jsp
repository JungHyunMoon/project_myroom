<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
			<c:forEach var="reservation" items="${reservationList}" varStatus="status">
				<div class="roomCard w-100">
					<tr>
						<th>${status.count}</th>
						<td>${reservation.id}</td>
						<td>${reservation.status}</td>
						<td><button class="add-comment btn btn-secondary" data-delete-target="${realEstate.id}">후기 작성</button></td>
					</tr>
				</div>
			</c:forEach>
		</tbody>
	</table>
</div>