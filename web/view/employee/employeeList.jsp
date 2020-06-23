<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Employees</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
    <style>
        <%@include file="/resources/list.css"%>
    </style>
    <script>
    </script>
</head>
<body>
<header>
    <div class="employee_header">
        <nav class="navbar navbar-expand-lg">
            <a class="navbar-brand">EMPLOYEES</a>
            <a class="nav-link" style="color: white"
               href="<%=request.getContextPath()%>/MainServlet?action=open_hotel_list">Hotel list</a>
        </nav>
    </div>
</header>
<a href="<%=request.getContextPath()%>/MainServlet?action=open_add_employee_tab">
    <button type="button" class="btn btn-outline-dark btn-md">Add Employee</button>
</a>
<h1 class="display-4">List</h1>
<table class="table table-bordered table-hover">
    <thead>
    <tr>
        <th scope="col" class="number_col">№</th>
        <th scope="col">NAME</th>
        <th scope="col">PROFESSION</th>
        <th scope="col">SALARY</th>
        <th scope="col">HOTEL №</th>
        <th scope="col">ACTION</th>
    </tr>
    </thead>
    <c:forEach items="${list}" var="employee">
    <tbody>
    <tr>
        <td scope="row">${employee.id}</td>
        <td>${employee.nameSurname}</td>
        <td>${employee.profession}</td>
        <td>${employee.salary}</td>
        <td>${employee.hotel}</td>
        <td><a title="Edit" href="MainServlet?action=edit_employee&id=<c:out value = "${employee.id}"/>"><img class="button"
                                                                                                      src=${pageContext.request.contextPath}/resources/img/edit.png></a>
            <a title="Delete" href="MainServlet?action=delete_employee&id=<c:out value = "${employee.id}"/>"><img class="button"
                                                                                                          src=${pageContext.request.contextPath}/resources/img/delete.png></a>
        </td>
    </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>

