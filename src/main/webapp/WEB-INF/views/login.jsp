<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-size: cover;
            background-repeat: no-repeat;
            background-attachment: fixed;
            font-family: Arial, sans-serif;
        }
        .card {
            background: rgba(255, 255, 255, 0.9);
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }
        .error-list {
            color: #dc3545;
            padding-left: 1rem;
        }
        .error {
            list-style: none;
        }
    </style>
    <%--  <script>--%>
    <%--    // Example: Set the error message dynamically--%>
    <%--    window.onload = function () {--%>
    <%--      const error = "${error}";--%>
    <%--      const errorDiv = document.getElementById("error-message");--%>

    <%--      if (error && error.trim() !== "") {--%>
    <%--        errorDiv.innerText = error;--%>
    <%--        errorDiv.style.display = "block";--%>
    <%--      } else {--%>
    <%--        errorDiv.style.display = "none";--%>
    <%--      }--%>
    <%--    };--%>
    <%--  </script>--%>
</head>
<body>
<div class="container d-flex justify-content-center align-items-center vh-100">
    <div class="col-md-6">
        <div class="card p-4">
            <h2 class="text-center">Login</h2>
            <%--          <div id="error-message" class="alert alert-danger mt-3" style="display: none;"></div>--%>
            <form:form action="${pageContext.request.contextPath}/doLogin" method="post" modelAttribute="user">
                <div class="mb-3">
                    <label for="email" class="form-label">Email</label>
                    <form:input path="email" class="form-control" id="email" />
                    <form:errors path="email" class="text-danger" />
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Password</label>
                    <form:input type="password" path="password" class="form-control" id="password" />
                    <form:errors path="password" class="text-danger" />
                </div>
                <div class="mb-3">
                    <label  class="form-label">Role</label><br/>
                    <form:radiobutton path="role" value="user" checked="true"/> User
                    <form:radiobutton path="role" value="admin"/> Admin
                    <form:errors path="role" class="text-danger" />
                </div>

                <c:if test="${not empty errorMessages}">
                    <ul class="error-list">
                        <c:forEach var="message" items="${errorMessages}">
                            <li class="error">${message}</li>
                        </c:forEach>
                    </ul>
                </c:if>

                <button type="submit" class="btn btn-primary w-100">Login</button>
            </form:form>
            <p class="text-center mt-3 text-muted">
                <strong>Note:</strong> Only <strong>users</strong> can register. <br/>
                Admin accounts must be created by the system.
                <br/>
                <a href="/register">Click here to register as a user</a>.
            </p>
        </div>
    </div>
</div>
</body>
</html>