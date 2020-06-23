<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
    <style>
        <%@include file="/resources/update.css"%>
    </style>
    <script>
        function validEnter() {
            if (document.editForm.nameSurname.value === "" || document.editForm.profession.value === "" || document.editForm.hotel.value === "") {
                alert("Enter all fields!");
                document.editForm.nameSurname.focus();
                return false;
            }
        }
    </script>
</head>
<body>
<header>
    <div class="employee_header">
        <nav class="navbar navbar-expand-lg ">
            <a class="navbar-brand">EMPLOYEES</a>
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" style="color: white"
                       href="<%=request.getContextPath()%>/MainServlet?action=open_employee_list">Employee list</a>
                </li>
            </ul>
        </nav>
    </div>
</header>
<br>
<div class="container col-md-4">
    <div class="card">
        <div class="card-body">
            <h1 class="card-title">Edit Employee</h1>
            <form action="${pageContext.request.contextPath}/MainServlet?action=update_employee" method="post"
                  name="editForm" onsubmit="return validEnter();">
                <input type="hidden" name="id" value="<c:out value='${employee.id}'/>"/>
                <div class="form-group">
                    <label>Name</label>
                    <input type="text" value="<c:out value='${employee.nameSurname}'/>" placeholder="Enter name"
                           class="form-control"
                           name="nameSurname">
                </div>
                <div class="form-group">
                    <label>Profession</label>
                    <input type="text" value="<c:out value='${employee.profession}'/>"
                           placeholder="Enter profession"
                           class="form-control"
                           name="profession"/>
                </div>
                <div class="form-group">
                    <label>Salary</label>
                    <input type="number" value="<c:out value='${employee.salary}'/>" class="form-control"
                           name="salary" placeholder="Enter salary">
                </div>
                <div class="form-group">
                    <label>Hotel</label>
                    <input type="text" value="<c:out value='${employee.hotel}' />" placeholder="Hotel"
                           class="form-control"
                           name="hotel">
                </div>
                <button type="submit" class="btn btn-outline-success">Submit</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
