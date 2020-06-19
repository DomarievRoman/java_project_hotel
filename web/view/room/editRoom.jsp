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
            if (document.editForm.roomType.value === "" || document.editForm.amountOfSleepingPlaces.value === "" || document.editForm.hotelId.value === "") {
                alert("Enter all fields!");
                document.editForm.name.focus();
                return false;
            }
        }
    </script>
</head>
<body>
<header>
    <div class="room_header">
        <nav class="navbar navbar-expand-lg ">
            <a class="navbar-brand">ROOMS</a>
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" style="color: white"
                       href="<%=request.getContextPath()%>/MainServlet?action=open_room_list">Room list</a>
                </li>
            </ul>
        </nav>
    </div>
</header>
<br>
<div class="container col-md-4">
    <div class="card">
        <div class="card-body">
            <h1 class="card-title">Edit Room</h1>
            <form action="${pageContext.request.contextPath}/MainServlet?action=update_room" method="post"
                  name="editForm" onsubmit="return validEnter();">
                <input type="hidden" name="id" value="<c:out value='${room.id}'/>"/>
                <div class="form-group">
                    <label>Type</label>
                    <input type="text" value="<c:out value='${room.roomType}'/>" placeholder="Enter room type"
                           class="form-control"
                           name="roomType">
                </div>
                <div class="form-group">
                    <label>Sleeping places</label>
                    <input type="number" value="<c:out value='${room.amountOfSleepingPlaces}'/>"
                           placeholder="Enter amount of sleeping places"
                           class="form-control"
                           name="amountOfSleepingPlaces"/>
                </div>
                <div class="form-group">
                    <label>Floor</label>
                    <input type="number" value="<c:out value='${room.floor}'/>" class="form-control"
                           name="floor" placeholder="Enter floor">
                </div>
                <div class="form-group">
                    <label>Available</label>
                    <input type="text" value="<c:out value='${room.available}' />" placeholder="Available"
                           class="form-control"
                           name="available">
                </div>
                <div class="form-group">
                    <label>Price</label>
                    <input type="text" value="<c:out value='${room.price}' />" placeholder="Enter Price"
                           class="form-control"
                           name="price">
                </div>
                <div class="form-group">
                    <label>Hotel</label>
                    <input type="text" value="<c:out value='${room.hotel}' />" placeholder="Hotel"
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
