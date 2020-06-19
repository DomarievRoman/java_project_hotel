<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Rooms</title>
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
    <div class="room_header">
        <nav class="navbar navbar-expand-lg">
            <a class="navbar-brand">ROOMS</a>
            <a class="nav-link" style="color: white"
               href="<%=request.getContextPath()%>/MainServlet?action=open_hotel_list">Hotel list</a>
        </nav>
    </div>
</header>
<a href="<%=request.getContextPath()%>/MainServlet?action=open_add_room_tab">
    <button type="button" class="btn btn-outline-dark btn-md">Add Room</button>
</a>
<h1 class="display-4">List</h1>
<table class="table table-bordered table-hover">
    <thead>
    <tr>
        <th scope="col" class="number_col">№</th>
        <th scope="col">TYPE</th>
        <th scope="col">SLEEPING PLACES</th>
        <th scope="col">FLOOR</th>
        <th scope="col">AVAILABLE</th>
        <th scope="col">PRICE</th>
        <th scope="col">HOTEL №</th>
        <th scope="col">ACTION</th>
    </tr>
    </thead>
    <c:forEach items="${list}" var="room">
    <tbody>
    <tr>
        <td scope="row">${room.id}</td>
        <td>${room.roomType}</td>
        <td>${room.amountOfSleepingPlaces}</td>
        <td>${room.floor}</td>
        <td>${room.available ? 'Yes' : 'No'}</td>
        <td>${room.price}</td>
        <td>${room.hotel}</td>
        <td><a title="Edit" href="MainServlet?action=edit_room&id=<c:out value = "${room.id}"/>"><img class="button"
                                                                                         src=${pageContext.request.contextPath}/resources/img/edit.png></a>
            <a title="Delete" href="MainServlet?action=delete_room&id=<c:out value = "${room.id}"/>"><img class="button"
                                                                                           src=${pageContext.request.contextPath}/resources/img/delete.png></a>
        </td>
    </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>

