<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Seat</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%@ include file="/WEB-INF/views/admin-Header.jsp" %>

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card shadow">
                <div class="card-body">
                    <h2 class="text-center mb-4">Add New Seat</h2>
                    <form:form action="${pageContext.request.contextPath}/seat/admin/create" method="post" modelAttribute="seat">

                        <div class="mb-3">
                            <label for="seatNumber" class="form-label">Seat Number</label>
                            <form:input path="seatNumber" cssClass="form-control" id="seatNumber"/>
                            <form:errors path="seatNumber" cssClass="text-danger"/>
                        </div>

                        <div class="mb-3">
                            <label for="floorNumber" class="form-label">Floor Number</label>
                            <form:input path="floorNumber" cssClass="form-control" id="floorNumber" />
                            <form:errors path="floorNumber" cssClass="text-danger"/>
                        </div>

                        <div class="mb-3">
                            <label for="seatType" class="form-label">Seat Type</label>
                            <form:input path="seatType" cssClass="form-control" id="seatType" />
                            <form:errors path="seatType" cssClass="text-danger"/>
                        </div>

                        <div class="mb-3">
                            <label class="form-label d-block">Availability</label>
                            <form:radiobutton path="available" value="true" cssClass="form-check-input" id="availableYes"/>
                            <label class="form-check-label me-3" for="availableYes">Available</label>

                            <form:radiobutton path="available" value="false" cssClass="form-check-input" id="availableNo"/>
                            <label class="form-check-label" for="availableNo">Not Available</label>
                            <form:errors path="available" cssClass="text-danger"/>
                        </div>

                        <div class="d-grid">
                            <button type="submit" class="btn btn-success">Create Seat</button>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/views/footer.jsp" %>
</body>
</html>
