<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
    <head>
        <title>IT Structure</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    </head>

    <body>
        <c:import url="${contextPath}/WEB-INF/html/navbar.jsp"/>

        <h4 style="text-align: center">CREATE NEW CUSTOMER</h4>
        <div class="container">
            <form action="/createCustomer" method="post">
              <div class="form-group">
                 <label for="customerName">Name</label><br>
                 <input type="text" class="form-control" id="customerName" placeholder="Enter customer name" name="customerName"><br>

                 <label for="customerBusiness">Business</label><br>
                 <input type="text" class="form-control" id="customerBusiness" placeholder="Enter customer business" name="customerBusiness"><br>

               </div>
                 <input type="submit" value="Create">
            </form>
        </div>
    </body>
</html>
