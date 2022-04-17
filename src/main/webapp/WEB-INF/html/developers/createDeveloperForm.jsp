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

        <div class="container">
            <form action="/createDeveloper" method="post">
              <div class="form-group">
                 <label for="developerName">Name</label><br>
                 <input type="text" class="form-control" id="developerName" placeholder="Enter developer first name and last name" name="developerName"><br>

                 <label for="developerAge">Age</label><br>
                 <input type="number" class="form-control" id="developerAge" placeholder="Enter developer age" name="developerAge"><br>

                 <label for="developerGender">Select gender</label><br>
                    <select class="form-control" id="developerGender" name="developerGender">
                       <option value="male">male</option>
                       <option value="female">female</option>
                    </select>

                 <label for="developerEmail">Email</label><br>
                 <input type="text" class="form-control" id="developerEmail" placeholder="Enter developer email" name="developerEmail"><br>

                 <label for="companyId">Select company:</label>
                    <select class="form-control" id="companyId" name="companyId">
                       <c:forEach items="${companies}" var="company">
                          <option value="${company.id}"><c:out value="${company.name}"/></option>
                       </c:forEach>
                    </select>

                 <label for="developerSalary">Salary</label><br>
                 <input type="number" class="form-control" id="developerSalary" placeholder="Enter developer salary" name="developerSalary"><br>
               </div>
                 <input type="submit" value="Create">
            </form>
        </div>
    </body>
</html>
