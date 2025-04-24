<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Seat Reservation Details</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<%@ include file="/WEB-INF/views/admin-Header.jsp" %>

<div class="container mt-5">
    <h2 class="text-center mb-4">Reservations for Seat: ${seat.seatNumber} (${seat.seatType}, Floor ${seat.floorNumber})</h2>

    <c:choose>
        <c:when test="${not empty reservations}">
            <div class="row">
                <c:forEach var="r" items="${reservations}">
                    <div class="col-md-6 mb-4">
                        <div class="card p-3 shadow-sm">
                            <p><strong>User:</strong> ${r.user.firstName} ${r.user.lastName}</p>
                            <p><strong>From:</strong> ${r.startDateTime}</p>
                            <p><strong>To:</strong> ${r.endDateTime}</p>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:when>
        <c:otherwise>
            <p class="text-center text-muted">No reservations found for this seat.</p>
        </c:otherwise>
    </c:choose>

    <div class="text-center mt-4">
        <a href="${pageContext.request.contextPath}/reservations/admin/seats" class="btn btn-secondary">Back to All Seats</a>
    </div>
</div>

<%@ include file="/WEB-INF/views/footer.jsp" %>
</body>
</html>
