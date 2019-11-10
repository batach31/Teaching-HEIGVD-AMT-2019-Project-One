<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <title>Reservation</title>

    <!-- Google font -->
    <link href="https://fonts.googleapis.com/css?family=PT+Sans:400" rel="stylesheet">

    <!-- Bootstrap -->
    <link type="text/css" rel="stylesheet" href="./style/css/bootstrap.min.css" />

    <!-- Custom stlylesheet -->
    <link type="text/css" rel="stylesheet" href="./style/css/style.css" />

    <link href="./style/login.css" rel="stylesheet">


    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>
<div class="login-block">
    <a href="./profile">Voir vos réservations</a>
</div>
<div class="login-block">
    <a href="./logout">Déconnexion</a>
</div>
<div id="booking" class="section">
    <div class="section-center">
        <div class="container">
            <div class="row">
                <div class="booking-form">
                    <form action="./home" method="POST">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <span class="form-label">Ville de départ</span>
                                    <input class="form-control" id="departure" name="departure" type="text" placeholder="Départ">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <span class="form-label">Ville de destination</span>
                                    <input class="form-control" id="destination" name="destination" type="text" placeholder="Destination">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-3">
                                <div class="form-btn">
                                    <button class="submit-btn" type="submit">Voir les vols</button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
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
<c:if test="${page_no != 1}">
    <input type="hidden" name="currPage" value="${page_no}">
    <a class="button" href="/home/flights?page_no=${page_no - 1}"><</a>
</c:if>
<c:if test="${page_no lt numberOfFlights}">
    <a class="button" href="/home/flights?page_no=${page_no + 1}">></a>
</c:if>
</body><!-- This templates was made by Colorlib (https://colorlib.com) -->

</html>