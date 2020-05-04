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
        <%@include file="/resources/editHotel.css"%>
    </style>
</head>
<body>
<header>
    <nav class="navbar navbar-expand-lg ">
        <a class="navbar-brand">HOTEL MANAGER</a>
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" style="color: white"
                   href="<%=request.getContextPath()%>/MainServlet?action=open_hotel_list">Hotel list</a>
            </li>
        </ul>
    </nav>
</header>
<br>
<div class="container col-md-4">
    <div class="card">
        <div class="card-body">
            <h1 class="card-title">Add Hotel</h1>
            <form action="${pageContext.request.contextPath}/MainServlet?action=add_hotel" method="post">
                <div class="form-group">
                    <label>Name</label>
                    <input type="text" class="form-control" name="name" placeholder="Enter hotel name">
                </div>
                <div class="form-group">
                    <label>Address</label>
                    <input type="text" class="form-control"
                           name="address" placeholder="Enter hotel address">
                </div>
                <div class="form-group">
                    <label>Amount of stars</label>
                    <select class="form-control" name="rating">
                        <option selected disabled>Stars</option>
                        <option id="1">1</option>
                        <option id="2">2</option>
                        <option id="3">3</option>
                        <option id="4">4</option>
                        <option id="5">5</option>
                    </select>
                </div>
                <div class="form-group">
                    <label>Director</label>
                    <input type="text" class="form-control"
                           name="ownerName" placeholder="Director name">
                </div>
                <button type="submit" class="btn btn-outline-success">Submit</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
