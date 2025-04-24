<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Home</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2 class="text-center">Welcome, <c:out value="${user.firstName}" />!</h2>
    <p class="text-center text-muted">You are logged in as a Admin.</p>
    <div class="row justify-content-center mt-4">
        <div class="col-md-4">
            <div class="card">
                <div class="card-body text-center">
                    <h5 class="card-title">My Profile</h5>
                    <p class="card-text">View or edit your personal information.</p>
                    <a href="/profile" class="btn btn-primary">Profile</a>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card">
                <div class="card-body text-center">
                    <h5 class="card-title">Manage Reservations</h5>
                    <p class="card-text">Add, edit, or delete reservations.</p>
                    <a href="/reservations/admin" class="btn btn-primary">Reservations</a>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card">
                <div class="card-body text-center">
                    <h5 class="card-title">Manage Seats</h5>
                    <p class="card-text">Add, edit, or delete seats.</p>
                    <a href="/seat/dashboard" class="btn btn-primary">Seats</a>
                </div>
            </div>
        </div>
    </div>
    <div class="text-center mt-4">
        <a href="/logout" class="btn btn-danger">Logout</a>
    </div>
</div>
</body>
</html>

