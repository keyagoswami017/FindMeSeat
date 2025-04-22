<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>All Seats</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%@ include file="/WEB-INF/views/admin-Header.jsp" %>

<div class="container mt-5">
    <h2 class="text-center mb-4">All Seats</h2>

    <div class="text-end mb-3">
        <a href="${pageContext.request.contextPath}/seat/admin/add" class="btn btn-primary">Add New Seat</a>
    </div>

    <c:if test="${empty seats}">
        <div class="alert alert-warning text-center">No seats found.</div>
    </c:if>

    <c:if test="${not empty seats}">
        <table class="table table-bordered table-hover text-center">
            <thead class="table-dark">
            <tr>
                <th>ID</th>
                <th>Seat Number</th>
                <th>Floor Number</th>
                <th>Seat Type</th>
                <th>Available</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="seat" items="${seats}">
                <tr>
                    <td>${seat.id}</td>
                    <td>${seat.seatNumber}</td>
                    <td>${seat.floorNumber}</td>
                    <td>${seat.seatType}</td>
                    <td>
                        <c:choose>
                            <c:when test="${seat.available}">Yes</c:when>
                            <c:otherwise>No</c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/seat/admin/edit/${seat.id}" class="btn btn-sm btn-warning">Edit</a>
                        <a href="${pageContext.request.contextPath}/seat/admin/delete/${seat.id}" class="btn btn-sm btn-danger" onclick="return confirm('Are you sure you want to delete this seat?');">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>

<%@ include file="/WEB-INF/views/footer.jsp" %>
</body>
</html>
