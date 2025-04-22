<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Manage Seats</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%@ include file="/WEB-INF/views/admin-Header.jsp" %>

<div class="container mt-5">
    <h2 class="text-center">Seat Management</h2>
    <p class="text-center text-muted">Use the options below to manage library seats.</p>
    <div class="row justify-content-center mt-4">
        <div class="col-md-4">
            <div class="card shadow">
                <div class="card-body text-center">
                    <h5 class="card-title">Create Seat</h5>
                    <p class="card-text">Add a new seat to the system.</p>
                    <a href="/seat/admin/add" class="btn btn-success">Add Seat</a>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card shadow">
                <div class="card-body text-center">
                    <h5 class="card-title">Edit/Delete Seats</h5>
                    <p class="card-text">Update or remove existing seats.</p>
                    <a href="/seat/admin/all" class="btn btn-warning">Manage Seats</a>
                </div>
            </div>
        </div>
    </div>
    <div class="text-center mt-4">
        <a href="/admin-Home" class="btn btn-secondary">Back to Home</a>
    </div>
</div>

<%@ include file="/WEB-INF/views/footer.jsp" %>
</body>
</html>
