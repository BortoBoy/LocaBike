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
        <h1>Bem vindo ADMIN</h1>
        <h3>Este é o seu sistema de gerenciamento para locação de biciletas</h3>
        <a href="<%= request.getContextPath() %>/admin/cliente?action=lista"> <h1>Gerenciar Clientes</h1></a>
        <a href="<%= request.getContextPath() %>/admin/locadora?action=lista"> <h1> Gerenciar Locadoras</h1></a>
        <a href="<%= request.getContextPath() %>/admin/">  <h4> Voltar </h4> </a>
        <a href="<%= request.getContextPath() %>/login?logout=true"> <h4> Logout </h4> </a>
    </center>
    </body>
</html>
