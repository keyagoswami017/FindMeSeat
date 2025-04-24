<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Select Seat Type</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<%@ include file="/WEB-INF/views/user-Header.jsp" %>

<div class="container mt-5">
    <h2 class="text-center mb-4">Select Seat Type</h2>

    <form action="${pageContext.request.contextPath}/reservations/user/seats-by-type" method="post" class="shadow p-4 rounded bg-light">

        <div class="mb-3">
            <label for="seatType" class="form-label">Seat Type</label>
            <select class="form-select" id="seatType" name="seatType" required>
                <option value="">-- Select Seat Type --</option>
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

        <div class="d-grid">
            <button type="submit" class="btn btn-success">Show Available Seats</button>
        </div>
    </form>
</div>

<%@ include file="/WEB-INF/views/footer.jsp" %>
</body>
</html>
