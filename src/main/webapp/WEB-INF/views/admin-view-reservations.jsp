<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>All Reservations</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<%@ include file="/WEB-INF/views/admin-Header.jsp" %>

<div class="container mt-5">
    <h2 class="text-center mb-4">All Reservations</h2>

    <c:if test="${empty reservations}">
        <div class="alert alert-info text-center">No reservations found.</div>
    </c:if>

    <div class="row justify-content-center">
        <c:forEach var="r" items="${reservations}">
            <div class="col-md-6">
                <div class="card shadow mb-4">
                    <div class="card-body">
                        <h5 class="card-title">Reservation ID: ${r.id}</h5>
                        <p><strong>User:</strong> ${r.user.firstName} ${r.user.lastName} (ID: ${r.user.id})</p>
                        <p><strong>Seat ID:</strong> ${r.seat.id}</p>
                        <p><strong>Seat Type:</strong> ${r.seat.seatType}</p>
                        <p><strong>Floor Number:</strong> ${r.seat.floorNumber}</p>
                        <p><strong>Start Time:</strong> ${r.startDateTime}</p>
                        <p><strong>End Time:</strong> ${r.endDateTime}</p>
                        <div class="text-end">
                            <a href="/reservations/admin/cancel/${r.id}" class="btn btn-sm btn-danger">Cancel Reservation</a>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<%@ include file="/WEB-INF/views/footer.jsp" %>
</body>
</html>
