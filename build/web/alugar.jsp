<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Loca Bike</title>
    </head>
    <body>
        <center>
            <h1>Ola <c:out value="${cliente.nome}"/></h1>
            <h4>Você esta prestes a lugar uma bike com a <c:out value="${locadora.nome}"/></h4>
            <h5>Escolha uma data para retirar</h5>
            <form action='' method='POST'>
                <input name='cliente' type="text"  hidden value="<c:out value="${cliente.id}"/>"/>                      
                <input name='locadora' type="text"  hidden value="<c:out value="${locadora.id}"/>"/>
                <input type="date" name="data"/>
                <input type="submit" value='alugar'/>
            </form>
        </center>
    </body>
</html>