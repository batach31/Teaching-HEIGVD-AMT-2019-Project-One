<%--
  Created by IntelliJ IDEA.
  User: Baptiste
  Date: 10.11.2019
  Time: 11:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.min.css" rel="stylesheet"
          id="bootstrap-css">
    <script src="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <!------ Include the above in your HEAD tag ---------->
</head>
<body>
<div class="well">
    <!-- <ul class="nav nav-tabs">
        <li class="active"><a href="#home" data-toggle="tab">Profile</a></li>
         <li><a href="#profile" data-toggle="tab">Password</a></li>
    </ul> -->
    <div id="myTabContent" class="tab-content">
        <div class="tab-pane active in" id="home">
            <form id="tab">
                <label>Pseudo</label>
                <input type="text" id="pseudo" name="pseudo" class="input-xlarge">
                <label>Nom</label>
                <input type="text" id="lastname" name="lastname" class="input-xlarge">
                <label>Prenom</label>
                <input type="text" id="firstname" name="firstname" class="input-xlarge">
                <label>Age</label>
                <input type="text" id="age" name="age" class="input-xlarge">
                </textarea>
            </form>
        </div>
        <!-- <div class="tab-pane fade" id="profile">
            <form id="tab2">
                <label>New Password</label>
                <input type="password" class="input-xlarge">
                <div>
                    <button class="btn btn-primary">Update</button>
                </div>
            </form>
        </div> -->
    </div>
    <c:forEach var="flight" items="${flights}">
        <tr>
            <td>${flight.flight_id}</td>
            <td>${flight.name}</td>
            <td>${flight.departureTime}</td>
            <td>${flight.arrivalTime}</td>
            <td>${flight.startPoint}</td>
            <td>${flight.endPoint}</td>
            <td>${flight.price}</td>
        </tr>
    </c:forEach>
    </table>
    <c:if test="${pageNum != 1}">
        <a class="button" href="profile/flights?pageNum=${pageNum - 1}"><</a>
    </c:if>
    <c:if test="${pageNum lt totalNum}">
        <a class="button" href="profile/flights?pageNum=${pageNum + 1}">></a>
    </c:if>
</div>
</body>
</html>