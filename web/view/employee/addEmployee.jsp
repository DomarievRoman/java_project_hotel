<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Add</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
    <style>
        <%@include file="/resources/update.css"%>
    </style>
    <script>
        function validEnter() {
            if (document.addForm.nameSurname.value === "" || document.addForm.profession.value === "" || document.addForm.hotel.value === "") {
                alert("Enter all fields!");
                document.addForm.nameSurname.focus();
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
            <h1 class="card-title">Add Employee</h1>
            <form action="${pageContext.request.contextPath}/MainServlet?action=add_employee" method="post" name="addForm"
                  onsubmit="return validEnter();">
                <div class="form-group">
                    <label>Name</label>
                    <input type="text" class="form-control" name="nameSurname" placeholder="Enter name">
                </div>
                <div class="form-group">
                    <label>Profession</label>
                    <input type="text" class="form-control"
                           name="profession" placeholder="Enter profession">
                </div>
                <div class="form-group">
                    <label>Salary</label>
                    <input type="number" class="form-control"
                           name="salary" placeholder="Enter salary">
                </div>
                <div class="form-group">
                    <label>Hotel</label>
                    <input type="text" class="form-control"
                           name="hotel" placeholder="Hotel number">
                </div>
                <button type="submit" class="btn btn-outline-success">Submit</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
