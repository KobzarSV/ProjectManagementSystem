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
            <form action="/createProject" method="post">
              <div class="form-group">
                 <label for="projectName">Name</label><br>
                 <input type="text" class="form-control" id="projectName" placeholder="Enter project name" name="projectName"><br>

                 <label for="projectDescription">Description</label><br>
                 <input type="text" class="form-control" id="projectDescription" placeholder="Enter project description" name="projectDescription"><br>

                 <label for="companyId">Select company:</label>
                    <select class="form-control" id="companyId" name="companyId">
                       <c:forEach items="${companies}" var="company">
                          <option value="${company.id}"><c:out value="${company.name}"/></option>
                       </c:forEach>
                    </select>

                 <label for="customerId">Select customer:</label>
                    <select class="form-control" id="customerId" name="customerId">
                       <c:forEach items="${customers}" var="customer">
                          <option value="${customer.id}"><c:out value="${customer.name}"/></option>
                       </c:forEach>
                    </select>

                 <label for="projectDate">Date</label><br>
                 <input type="text" class="form-control" id="projectDate" placeholder="Enter customer date. Example 2022-02-02" name="projectDate"><br>

                 <label for="developerId">Select developer:</label><br>
                     <c:forEach items="${developers}" var="developer">
                         <input type="checkbox" name="developerId" value="${developer.id}"> <c:out value="${developer.name}"/>
                             <c:forEach items="${developer.skills}" var="skills">
                               <c:out value="${skills.industry}"/> <c:out value="${skills.skillLevel}"/> </a>
                             </c:forEach>
                       <br>
                    </c:forEach>
               </div>
                 <input type="submit" value="Create">
            </form>
        </div>
    </body>
</html>
