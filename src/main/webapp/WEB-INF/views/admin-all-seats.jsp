<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>All Seats</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<%@ include file="/WEB-INF/views/admin-Header.jsp" %>

<div class="container mt-5">
    <h2 class="text-center mb-4">Library Seats Overview</h2>

    <c:choose>
        <c:when test="${not empty seats}">
            <table class="table table-striped table-bordered shadow">
                <thead class="table-dark">
                <tr>
                    <th scope="col">Seat Number</th>
                    <th scope="col">Seat Type</th>
                    <th scope="col">Floor</th>
                    <th scope="col">Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="seat" items="${seats}">
                    <tr>
                        <td>${seat.seatNumber}</td>
                        <td>${seat.seatType}</td>
                        <td>${seat.floorNumber}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/reservations/admin/seat/view/${seat.id}" class="btn btn-sm btn-outline-primary">
                                View Reservations
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <p class="text-center text-muted">No seat data available.</p>
        </c:otherwise>
    </c:choose>

    <div class="text-center mt-4">
        <a href="${pageContext.request.contextPath}/reservations/admin" class="btn btn-secondary">Back to Dashboard</a>
    </div>
</div>

<%@ include file="/WEB-INF/views/footer.jsp" %>
</body>
</html>
