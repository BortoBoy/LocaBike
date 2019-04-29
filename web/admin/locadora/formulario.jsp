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
        <c:if test="${locadora != null}">
        <form action="locadora?action=atualizacao" method="post">
        </c:if>
        <c:if test="${locadora == null}">
        <form action="locadora?action=insercao" method="post">
        </c:if>
            <table border="1" cellpadding="5">
                <caption>
                    <h2>
                        <c:if test="${locadora != null}">
                            Edição
                        </c:if>
                        <c:if test="${locadora == null}">
                            Cadastro
                        </c:if>
                    </h2>
                </caption>
                <c:if test="${locadora != null}">
                    <input type="hidden" name="id" value="<c:out value='${locadora.id}' />" />
                </c:if>
                <tr>
                    <th>NOME: </th>
                    <td>
                        <input type="text" name="nome" size="100"
                               value="<c:out value='${locadora.nome}' />"required/>
                    </td>
                </tr>
                <tr>
                    <th>CNPJ: </th>
                    <td>
                        <input type="number" name="cnpj" size="15"
                               value="<c:out value='${locadora.cnpj}' />" required/>
                    </td>
                </tr>
                <tr>
                    <th>CIDADE: </th>
                    <td>
                        <input type="text" name="cidade" size="100"
                               value="<c:out value='${locadora.cidade}' />" required/>
                    </td>
                </tr>
                <tr>
                    <th>EMAIL: </th>
                    <td>
                        <input type="email" name="email" size="30"
                               value="<c:out value='${locadora.email}' />" required/>
                    </td>
                </tr>
                <tr>
                    <th>SENHA: </th>
                    <td>
                        <input type="password" name="senha" size="15"
                               value="<c:out value='${locadora.senha}' />" required/>
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