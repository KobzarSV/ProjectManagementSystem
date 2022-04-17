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

                  <table class="table table-striped", style="text-align: center">
                      <thead>
                      <tr>
                          <th style="text-align: center">Developer id</th>
                          <th style="text-align: center">Name</th>
                          <th style="text-align: center">Age</th>
                          <th style="text-align: center">Gender</th>
                          <th style="text-align: center">Mail</th>
                          <th style="text-align: center">Company</th>
                          <th style="text-align: center">Salary</th>
                          <th style="text-align: center">Skills and level</th>
                      </tr>
                      </thead>
                      <tbody>
                              <tr>
                                  <td>
                                      <c:out value="${developer.id}"/>
                                  </td>
                                  <td>
                                      <c:out value="${developer.name}"/>
                                  </td>
                                  <td>
                                      <c:out value="${developer.age}"/>
                                  </td>
                                  <td>
                                      <c:out value="${developer.gender}"/>
                                  </td>
                                  <td>
                                      <c:out value="${developer.mail}"/>
                                  </td>
                                  <td>
                                      <a href="/findCompany?companyId=${developer.companyId}">
                                      <c:out value="${developer.companyId}"/>
                                  </td>
                                  <td>
                                      <c:out value="${developer.salary}"/>
                                  </td>
                                  <td>
                                      <c:out value="${industry}"/>
                                  </td>
                              </tr>
                      </tbody>
                  </table>
    </body>
</html>
