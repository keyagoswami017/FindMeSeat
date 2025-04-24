<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Available Seats</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%@ include file="/WEB-INF/views/user-Header.jsp" %>

<div class="container mt-5">
    <h2 class="text-center mb-4">Available Seats for Selected Type</h2>

    <c:if test="${empty availableSeats}">
        <div class="alert alert-warning text-center">No available seats found for the selected type.</div>
    </c:if>

    <c:if test="${not empty availableSeats}">
        <div class="row row-cols-1 row-cols-md-2 g-4">
            <c:forEach var="seat" items="${availableSeats}">
                <div class="col">
                    <div class="card shadow-sm">
                        <div class="card-body">
                            <h5 class="card-title">Seat Number: ${seat.seatNumber}</h5>
                            <p class="card-text">
                                <strong>Floor:</strong> ${seat.floorNumber} <br>
                                <strong>Type:</strong> ${seat.seatType} <br>
                                <strong>Available:</strong> <c:out value="${seat.available ? 'Yes' : 'No'}"/>
                            </p>
                            <a href="${pageContext.request.contextPath}/reservations/user/create/${seat.id}" class="btn btn-primary">Reserve This Seat</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </c:if>
</div>

<%@ include file="/WEB-INF/views/footer.jsp" %>
</body>
</html>
