<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Loca Bike</title>
    </head>
    <body>
    <center>
        <h1> Login </h1>
        <h3>Faça seu login para começar a gerenciar seu negócio</h3>
        <c:if test="${message != null}">
            <li style="color: red"><c:out value="${message}" /></li>
        </c:if>
        <table border="1" cellpadding="5">
            <form method="POST" action="">
                <tr>
                    <th>
                       Usuário 
                    </th>
                    <td>
                        <input type="text" name="user" size="20">
                    </td>
                </tr>
                <tr>
                    <th>
                       Senha 
                    </th>
                    <td>
                        <input type="password" name="passwd" size="20">
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Login">
                    </td>
                </tr>
            </form>
        </table>
    </center>
    </body>
</html>
