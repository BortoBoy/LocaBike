<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Loca Bike</title>
    </head>
    <body>
    <center>
        <h1>Gerenciamento de Clintes</h1>
        <h2>
            <a href="cliente?action=cadastro">Adicione Novo Cliente</a>
            &nbsp;&nbsp;&nbsp;
            <a href="cliente?action=lista">Lista de Clientes</a>
        </h2>
    </center>
    <div align="center">
        <c:if test="${cliente != null}">
        <form action="cliente?action=atualizacao" method="post">
        </c:if>
        <c:if test="${cliente == null}">
        <form action="cliente?action=insercao" method="post">
        </c:if>
            <table border="1" cellpadding="5">
                <caption>
                    <h2>
                        <c:if test="${cliente != null}">
                            Edição
                        </c:if>
                        <c:if test="${cliente == null}">
                            Cadastro
                        </c:if>
                    </h2>
                </caption>
                <c:if test="${cliente != null}">
                    <input type="hidden" name="id" value="<c:out value='${cliente.id}' />" />
                </c:if>
                <tr>
                    <th>NOME: </th>
                    <td>
                        <input type="text" name="nome" size="100"
                               value="<c:out value='${cliente.nome}' />"required/>
                    </td>
                </tr>
                <tr>
                    <th>CPF: </th>
                    <td>
                        <input type="number" name="cpf" size="15"
                               value="<c:out value='${cliente.cpf}' />" required/>
                    </td>
                </tr>
                <tr>
                    <th>TEL: </th>
                    <td>
                        <input type="number" name="tel" size="15"
                               value="<c:out value='${cliente.tel}' />" required/>
                    </td>
                </tr>
                <tr>
                    <th>SEXO: </th>
                    <td>
                        <select name="sexo" required>
                            <option value="masculino">Masculino</option>
                            <option value="feminino">Feminino</option>
                            <option value="outro">Outro</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>BORN DATE: </th>
                    <td>
                        <input type="date" name="data_nasc" size="15"
                               value="<c:out value='${cliente.data_nasc}' />" required/>
                    </td>
                </tr>
                <tr>
                    <th>EMAIL: </th>
                    <td>
                        <input type="email" name="email" size="30"
                               value="<c:out value='${cliente.email}' />" required/>
                    </td>
                </tr>
                <tr>
                    <th>SENHA: </th>
                    <td>
                        <input type="password" name="senha" size="15"
                               value="<c:out value='${cliente.senha}' />" required/>
                    </td>
                </tr>   
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Salva" />
                    </td>
                </tr>
            </table>
            </form>
    </div>
</body>
</html>