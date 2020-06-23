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
            if (document.addForm.roomType.value === "" || document.addForm.amountOfSleepingPlaces.value === "" || document.addForm.hotel.value === "") {
                alert("Enter all fields!");
                document.addForm.roomType.focus();
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
            <h1 class="card-title">Add Room</h1>
            <form action="${pageContext.request.contextPath}/MainServlet?action=add_room" method="post" name="addForm"
                  onsubmit="return validEnter();">
                <div class="form-group">
                    <label>Type</label>
                    <input type="text" class="form-control" name="roomType" placeholder="Enter room type">
                </div>
                <div class="form-group">
                    <label>Sleeping places</label>
                    <input type="number" class="form-control"
                           name="amountOfSleepingPlaces" placeholder="Enter amount of sleeping places">
                </div>
                <div class="form-group">
                    <label>Floor</label>
                    <input type="number" class="form-control"
                           name="floor" placeholder="Enter floor">
                </div>
                <div class="form-group">
                    <label>Available</label>
                    <input type="text" class="form-control"
                           name="available" placeholder="Yes/No">
                </div>
                <div class="form-group">
                    <label>Price</label>
                    <input type="number" class="form-control"
                           name="price" placeholder="Enter price">
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
