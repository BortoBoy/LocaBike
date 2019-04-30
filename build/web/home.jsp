<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Loca Bike</title>
        <script src="js/script.js"></script>
    </head>
    <body>
    <center>
        <h1>Loca Bike</h1>
        <table border="1" cellpadding="5">
            <h2>Locadoras Disponíves</h2>
            <form id='form_cidade' action='' method='GET'>
                <table>
                    <tr>
                        <td>
                            <select id='cidade' name="cidade" form='form_cidade'>
                            <c:forEach var="cidade" items="${listCidades}">
                                <option value="<c:out value="${cidade}" />"><c:out value="${cidade}" /></option>
                            </c:forEach>
                            </select>
                        </td>
                        <td>
                            <input type='submit' value='Filtrar'>
                        </td>
                    </tr>
            </form>
        </table>     
        <table border="1" cellpadding="5">
            <tr>
                <th>Nome</th>
                <th>Cidade</th>
                <th>Email</th>
                <th>Alugar</th>
            </tr>
            <c:forEach var="locadora" items="${list}">
            <tr>
                <td><c:out value="${locadora.nome}" /></td>
                <td><c:out value="${locadora.cidade}" /></td>
                <td><c:out value="${locadora.email}" /></td>
                <td><a href="alugar?locadora=${locadora.id}">Alugar</a></td>
            </tr>
            </c:forEach>
        </table>    
    </center>
    </body>
</html>
