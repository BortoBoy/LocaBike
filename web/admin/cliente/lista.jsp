<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Loca Bike</title>
    </head>
    <body>
    <center>
        <h1>Gerenciamento de Clientes</h1>
        <h2>
            <a href="cliente?action=cadastro">Adicionar Cliente</a>
            &nbsp;&nbsp;&nbsp;
            <a href="cliente?action=lista">Lista de Clientes</a>
        </h2>
    </center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>Lista de Clientes</h2></caption>
            <tr>
                <th>Nome</th>
                <th>Email</th>
                <th>CPF</th>
                <th>Tel</th>
                <th>Sexo</th>
                <th>Date</th>
                <th>Ações</th>
            </tr>
            <c:forEach var="cliente" items="${listaCliente}">
                <tr>
                    <td><c:out value="${cliente.nome}" /></td>
                    <td><c:out value="${cliente.email}" /></td>
                    <td><c:out value="${cliente.cpf}" /></td>
                    <td><c:out value="${cliente.tel}" /></td>
                    <td><c:out value="${cliente.sexo}" /></td>
                    <td><c:out value="${cliente.data_nasc}" /></td>
                    <td>
                        <a href="cliente?action=edicao&id=<c:out value='${cliente.id}' />">Edição</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="cliente?action=remocao&id=<c:out value='${cliente.id}' />" onclick="return confirm('Tem certeza de que deseja excluir este item?');">
                            Remoção</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>