<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Hotels</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
    <style>
        <%@include file="/resources/hotelList.css"%>
    </style>
</head>
<body>
<header>
    <nav class="navbar navbar-expand-lg">
        <a class="navbar-brand">HOTEL MANAGER</a>
    </nav>
</header>
<a href="<%=request.getContextPath()%>/MainServlet?action=open_add_hotel_tab"><button type="button" class="btn btn-outline-dark btn-md">Add Hotel</button></a>
<h1 class="display-4">List</h1>
<table class="table table-bordered table-hover">
    <thead>
    <tr>
        <th scope="col" class="number_col">№</th>
        <th scope="col">NAME</th>
        <th scope="col">ADDRESS</th>
        <th scope="col">STARS</th>
        <th scope="col">DIRECTOR</th>
        <th scope="col">ACTION</th>
    </tr>
    </thead>
    <c:forEach items="${list}" var="hotel">
    <tbody>
    <tr>
        <td scope="row">${hotel.id}</td>
        <td>${hotel.name}</td>
        <td>${hotel.address}</td>
        <td>${hotel.rating}</td>
        <td>${hotel.ownerName}</td>
        <td><a href="MainServlet?action=edit_list&id=<c:out value = "${hotel.id}"/>"><img class="button"
                                                                                          src=${pageContext.request.contextPath}/resources/img/edit.png></a>
            <a href="MainServlet?action=delete_list&id=<c:out value = "${hotel.id}"/>"><img class="button"
                                                                                            src=${pageContext.request.contextPath}/resources/img/delete.png></a>
        </td>
    </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>

