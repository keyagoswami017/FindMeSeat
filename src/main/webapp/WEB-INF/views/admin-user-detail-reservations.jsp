<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Reservations</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%@ include file="/WEB-INF/views/admin-Header.jsp" %>

<div class="container mt-5">
    <h2 class="text-center mb-4">Reservations for ${user.firstName} ${user.lastName}</h2>

    <c:choose>
        <c:when test="${not empty reservations}">
            <div class="row">
                <c:forEach var="r" items="${reservations}">
                    <div class="col-md-6 mb-4">
                        <div class="card shadow-sm p-3">
                            <p><strong>Seat:</strong> ${r.seat.seatNumber} (${r.seat.seatType}, Floor ${r.seat.floorNumber})</p>
                            <p><strong>From:</strong> ${r.startDateTime}</p>
                            <p><strong>To:</strong> ${r.endDateTime}</p>
                            <form action="${pageContext.request.contextPath}/reservations/admin/cancel/${r.id}/user/${user.id}" method="get" class="mt-2">
                                <button type="submit" class="btn btn-danger btn-sm">Cancel</button>
                            </form>

                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:when>
        <c:otherwise>
            <p class="text-muted text-center">No reservations found for this user.</p>
        </c:otherwise>
    </c:choose>

    <div class="text-center mt-3">
        <a href="${pageContext.request.contextPath}/reservations/admin/users" class="btn btn-secondary">Back to All Users</a>
    </div>
</div>

<%@ include file="/WEB-INF/views/footer.jsp" %>
</body>
</html>
