<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Loca Bike</title>
    </head>
    <body>
    <center>
        <h1>Gerenciamento de Locadoras</h1>
        <h2>
            <a href="locadora?action=cadastro">Adicione Nova Locadora</a>
            &nbsp;&nbsp;&nbsp;
            <a href="locadora?action=lista">Lista de Locadoras</a>
        </h2>
    </center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>Lista de Locadoras</h2></caption>
            <tr>
                <th>Nome</th>
                <th>Cidade</th>
                <th>Email</th>
                <th>CNPJ</th>
                <th>Senha</th>
                <th>Ações</th>
            </tr>
            <c:forEach var="locadora" items="${listaLocadora}">
                <tr>
                    <td><c:out value="${locadora.nome}" /></td>
                    <td><c:out value="${locadora.cidade}" /></td>
                    <td><c:out value="${locadora.email}" /></td>
                    <td><c:out value="${locadora.cnpj}" /></td>
                    <td><c:out value="${locadora.senha}" /></td>
                    <td>
                        <a href="locadora?action=edicao&id=<c:out value='${locadora.id}' />">Edição</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="locadora?action=remocao&id=<c:out value='${locadora.id}' />" onclick="return confirm('Tem certeza de que deseja excluir este item?');">
                            Remoção</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>