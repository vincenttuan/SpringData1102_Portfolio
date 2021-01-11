<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://unpkg.com/purecss@1.0.1/build/pure-min.css">
        <title>Verify Page</title>
        <style>
            td {
                padding-bottom: 50px;
                padding-top: 50px;
                padding-left: 150px;
                padding-right: 150px;
                border-width:1px;
                border-style:solid;
                border-color:#CCCCCC;
            }
        </style>
    </head>
    <body style="padding:15px" bgcolor="#DDDDDD">
    <center>
        <table bgcolor="#FFFFFF">
            <td>
                <form class="pure-form" action="login.jsp">
                    <fieldset>
                        <legend><h1><img src="${pageContext.request.contextPath}/images/user.png" width="40" valign="middle">Email認證碼: ${ sessionScope.message }</h1></legend>
                        <c:if test="${sessionScope.message == 'SUCCESS'}">
                            <button type="submit" class="pure-button pure-button-primary">按我進入登入畫面</button>
                        </c:if>
                    </fieldset>
                </form>
            </td>
        </table>
    </center>
</body>
</html>
