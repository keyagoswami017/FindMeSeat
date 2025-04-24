<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin - Manage Reservations</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<%@ include file="/WEB-INF/views/admin-Header.jsp" %>

<div class="container mt-5">
    <h2 class="text-center mb-4">All Reservations (Today & Tomorrow)</h2>

    <!-- Filter Form -->
    <form action="${pageContext.request.contextPath}/reservations/admin/filter" method="post" class="row g-3 mb-4 shadow-sm p-3 bg-light rounded">
        <div class="col-md-3">
            <label for="seatType" class="form-label">Seat Type</label>
            <select class="form-select" name="seatType" id="seatType">
                <option value="">All</option>
                <option>1-Seater</option>
                <option>2-Seater</option>
                <option>3-Seater</option>
                <option>4-Seater</option>
                <option>5-Seater</option>
                <option>6-Seater</option>
                <option>1-Seater-Aisle</option>
                <option>1-Seater-Window</option>
                <option>2-Seater-Aisle</option>
                <option>2-Seater-Window</option>
            </select>
        </div>

        <div class="col-md-2">
            <label for="floorNumber" class="form-label">Floor</label>
            <input type="number" class="form-control" name="floorNumber" id="floorNumber" min="1">
        </div>

        <div class="col-md-3">
            <label for="startDateTime" class="form-label">Start Date & Time</label>
            <input type="datetime-local" class="form-control" name="startDateTime" id="startDateTime" required>
        </div>

        <div class="col-md-3">
            <label for="endDateTime" class="form-label">End Date & Time</label>
            <input type="datetime-local" class="form-control" name="endDateTime" id="endDateTime" required>
        </div>

        <div class="col-md-1 d-flex align-items-end">
            <button type="submit" class="btn btn-primary w-100">Filter</button>
        </div>
        <div class="col-auto">
            <a href="${pageContext.request.contextPath}/reservations/admin" class="btn btn-secondary w-100">Reset</a>
        </div>
    </form>

    <!-- Reservation Cards -->
    <c:choose>
        <c:when test="${not empty reservations}">
            <div class="row">
                <c:forEach var="r" items="${reservations}">
                    <div class="col-md-6 mb-4">
                        <div class="card shadow-sm p-3">
                            <p><strong>User:</strong> ${r.user.firstName} ${r.user.lastName}  (${r.user.email})</p>
                            <p><strong>Seat:</strong> ${r.seat.seatNumber} (${r.seat.seatType}, Floor ${r.seat.floorNumber})</p>
                            <p><strong>From:</strong> ${r.startDateTime}</p>
                            <p><strong>To:</strong> ${r.endDateTime}</p>
                            <a href="${pageContext.request.contextPath}/reservations/admin/cancel/${r.id}" class="btn btn-sm btn-outline-danger">Cancel</a>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:when>
        <c:otherwise>
            <p class="text-muted text-center">No reservations found for selected criteria.</p>
        </c:otherwise>
    </c:choose>

    <!-- Navigation Cards -->
    <div class="text-center mt-5">
        <a href="${pageContext.request.contextPath}/reservations/admin/seats" class="btn btn-outline-dark mx-2">View All Seats</a>
        <a href="${pageContext.request.contextPath}/reservations/admin/users" class="btn btn-outline-dark mx-2">View All Users</a>
    </div>
</div>

<%@ include file="/WEB-INF/views/footer.jsp" %>
</body>
</html>
