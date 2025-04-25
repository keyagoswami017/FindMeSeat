<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>My Reservations</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%@ include file="/WEB-INF/views/user-Header.jsp" %>

<div class="container mt-5">
    <h2 class="text-center mb-4">My Seat Reservations</h2>

    <c:if test="${not empty success}">
        <div class="alert alert-success text-center">${success}</div>
    </c:if>
    <c:if test="${not empty error}">
        <div class="alert alert-danger text-center">${error}</div>
    </c:if>

    <div class="text-center mb-4">
        <a href="${pageContext.request.contextPath}/reservations/user" class="btn btn-outline-primary me-2">Current Reservations</a>
        <a href="${pageContext.request.contextPath}/reservations/user/past" class="btn btn-outline-secondary">Past Reservations (Last 7 Days)</a>
    </div>

    <c:choose>
        <c:when test="${not empty reservations}">
            <div class="row">
                <c:forEach var="r" items="${reservations}">
                    <div class="col-md-6 mb-4">
                        <div class="card p-3 shadow-sm">
                            <p><strong>Seat:</strong> ${r.seat.seatNumber} (${r.seat.seatType}, Floor ${r.seat.floorNumber})</p>
                            <p><strong>From:</strong> ${r.startDateTime}</p>
                            <p><strong>To:</strong> ${r.endDateTime}</p>
                            <div class="d-flex justify-content-between">
                                <a href="${pageContext.request.contextPath}/reservations/user/edit/${r.id}" class="btn btn-sm btn-outline-primary">Edit</a>
                                <a href="${pageContext.request.contextPath}/reservations/user/cancel/${r.id}" class="btn btn-sm btn-outline-danger">Cancel</a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:when>
        <c:otherwise>
            <p class="text-center text-muted">No current reservations found.</p>
        </c:otherwise>
    </c:choose>

    <div class="text-center mt-4">
        <a href="${pageContext.request.contextPath}/reservations/user/search" class="btn btn-success">Book a Seat</a>
    </div>
</div>

<%@ include file="/WEB-INF/views/footer.jsp" %>
</body>
</html>
