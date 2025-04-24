<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Reservation</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<%@ include file="/WEB-INF/views/user-Header.jsp" %>

<div class="container mt-5">
    <h2 class="text-center mb-4">Edit Your Reservation</h2>

    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card shadow">
                <div class="card-body">
                    <c:if test="${not empty error}">
                        <div class="alert alert-danger">${error}</div>
                    </c:if>
                    <c:if test="${not empty success}">
                        <div class="alert alert-success">${success}</div>
                    </c:if>

                    <form:form method="post" action="${pageContext.request.contextPath}/reservations/user/update/${reservationId}" modelAttribute="reservation">
                        <form:hidden path="sid" />
                        <form:hidden path="uid" />

                        <div class="mb-3">
                            <label for="startDateTime" class="form-label">Start Date & Time</label>
                            <form:input path="startDateTime" id="startDateTime" type="datetime-local" cssClass="form-control" required="true" />
                        </div>

                        <div class="mb-3">
                            <label for="endDateTime" class="form-label">End Date & Time</label>
                            <form:input path="endDateTime" id="endDateTime" type="datetime-local" cssClass="form-control" required="true" />
                        </div>

                        <div class="d-grid">
                            <button type="submit" class="btn btn-primary">Update Reservation</button>
                        </div>
                    </form:form>

                    <div class="mt-3 text-center">
                        <a href="${pageContext.request.contextPath}/reservations/user" class="btn btn-secondary">Back to Reservations</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/views/footer.jsp" %>
</body>
</html>
