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
                          <th style="text-align: center">Project id</th>
                          <th style="text-align: center">Project name</th>
                          <th style="text-align: center">Description</th>
                          <th style="text-align: center">Company</th>
                          <th style="text-align: center">Customer</th>
                          <th style="text-align: center">Date</th>
                      </tr>
                      </thead>
                      <tbody>
                              <tr>
                                  <td>
                                      <c:out value="${project.id}"/>
                                  </td>
                                  <td>
                                      <c:out value="${project.name}"/>
                                  </td>
                                  <td>
                                      <c:out value="${project.description}"/>
                                  </td>
                                  <td>
                                      <a href="/findCompany?companyId=${project.companyId}">
                                      <c:out value="${project.companyId}"/>
                                  </td>
                                  <td>
                                      <a href="/findCustomer?customerId=${project.customerId}">
                                      <c:out value="${project.customerId}"/>
                                  </td>
                                  <td>
                                      <c:out value="${project.date}"/>
                                  </td>
                              </tr>
                      </tbody>
                  </table>
    </body>
</html>
