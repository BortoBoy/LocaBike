<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Loca Bike</title>
    </head>
    <body>
    <div align="center">
        <h1><c:out value="${cliente.nome}" /></h1>
        <table border="1" cellpadding="5">
            <caption><h2>Lista de Locações</h2></caption>
            <tr>
                <th>Data</th>
                <th>Locadora</th>
            </tr>
            <c:forEach var="container" items="${listContainer}">
                <tr>
                    <td><c:out value="${container.data}" /></td>
                    <td><c:out value="${container.nome_locadora}" /></td>
                </tr>
            </c:forEach>
        </table>
        <a href="<%= request.getContextPath() %>/"><h1>Continuar</h1></a>
    </div>
</body>
</html>