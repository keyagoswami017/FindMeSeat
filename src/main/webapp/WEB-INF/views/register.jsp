<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background-image: url('<c:url value="/resources/images/Library_Chairs_red_wood.jpg"/>');
            background-size: cover;
            background-repeat: no-repeat;
            background-attachment: fixed;
        }

        .card {
            background: rgba(255, 255, 255, 0.95);
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        .text-danger {
            font-size: 0.875rem;
        }

        .error-message {
            color: red;
            font-weight: bold;
            margin-bottom: 1rem;
        }
    </style>

    <script>
        function updateFormAction() {
            var form = document.getElementById("registerForm");
            form.action = "${pageContext.request.contextPath}/register/user";
        }
    </script>
</head>
<body>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card p-4">
                <h2 class="text-center mb-4">User Registration</h2>

                <c:if test="${not empty error}">
                    <div class="error-message text-center">${error}</div>
                </c:if>

                <form:form id="registerForm" method="post" modelAttribute="user" onsubmit="updateFormAction()">
                    <div class="mb-3">
                        <label for="firstName" class="form-label">First Name</label>
                        <form:input path="firstName" cssClass="form-control" id="firstName"/>
                        <form:errors path="firstName" cssClass="text-danger"/>
                    </div>

                    <div class="mb-3">
                        <label for="lastName" class="form-label">Last Name</label>
                        <form:input path="lastName" cssClass="form-control" id="lastName"/>
                        <form:errors path="lastName" cssClass="text-danger"/>
                    </div>

                    <div class="mb-3">
                        <label for="email" class="form-label">Email</label>
                        <form:input path="email" cssClass="form-control" id="email"/>
                        <form:errors path="email" cssClass="text-danger"/>
                    </div>

                    <div class="mb-3">
                        <label for="password" class="form-label">Password</label>
                        <form:password path="password" cssClass="form-control" id="password"/>
                        <form:errors path="password" cssClass="text-danger"/>
                    </div>

                    <div class="mb-3">
                        <label for="phone" class="form-label">Phone Number</label>
                        <form:input path="phone" cssClass="form-control" id="phone"/>
                        <form:errors path="phone" cssClass="text-danger"/>
                    </div>

                    <button type="submit" class="btn btn-primary w-100">Register</button>
                </form:form>

                <p class="text-center mt-3">
                    Already have an account? <a href="${pageContext.request.contextPath}/login">Login here</a>.
                </p>
            </div>
        </div>
    </div>
</div>
</body>
</html>