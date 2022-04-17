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

        <h4 style="text-align: center">UPDATE COMPANY</h4>

        <div class="container">
            <form action="/updateCompany" method="post">
              <div class="form-group">
                 <label for="companyId">Id</label><br>
                 <input type="number" class="form-control" id="companyId" placeholder="Enter company id" name="companyId"><br>

                 <label for="companyName">Name</label><br>
                 <input type="text" class="form-control" id="companyName" placeholder="Enter company name" name="companyName"><br>

                 <label for="companyDescription">Description</label><br>
                 <input type="text" class="form-control" id="companyDescription" placeholder="Enter company description" name="companyDescription"><br>

                 <label for="numberOfEmployees">Number of employees</label><br>
                 <input type="number" class="form-control" id="numberOfEmployees" placeholder="Enter number of employees" name="numberOfEmployees"><br>
               </div>
                 <input type="submit" value="Update">
            </form>
        </div>
    </body>
</html>
