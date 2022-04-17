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
                          <th style="text-align: center">Id</th>
                          <th style="text-align: center">Name</th>
                          <th style="text-align: center">Business</th>
                      </tr>
                      </thead>
                      <tbody>
                              <tr>
                                  <td>
                                      <c:out value="${customer.id}"/>
                                  </td>
                                  <td>
                                      <c:out value="${customer.name}"/>
                                  </td>
                                  <td>
                                      <c:out value="${customer.business}"/>
                                  </td>
                              </tr>
                      </tbody>
                  </table>
    </body>
</html>
