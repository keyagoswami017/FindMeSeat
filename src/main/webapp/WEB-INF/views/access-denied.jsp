<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Access Denied</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
            font-family: Arial, sans-serif;
        }
        .error-container {
            margin-top: 100px;
        }
    </style>
</head>
<body>

<div class="container text-center error-container">
    <div class="card shadow p-5">
        <h1 class="display-4 text-danger">Access Denied</h1>
        <p class="lead">You do not have permission to view this page or perform this action.</p>
        <a href="${pageContext.request.contextPath}/login" class="btn btn-primary mt-3">Back to Login</a>
    </div>
</div>

</body>
</html>
