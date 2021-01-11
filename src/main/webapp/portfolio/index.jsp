<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html>
    <head>
        <title>我的投資組合</title>
        <!-- head -->
        <%@include file="/portfolio/include/head.jspf"  %>
    </head>
    <body>
        <div id="layout">
            <!-- Menu toggle -->
            <%@include file="/portfolio/include/toggle.jspf"  %>

            <!-- Menu -->
            <%@include file="/portfolio/include/menu.jspf"  %>
            
            <div id="main">
                <div class="header">
                    <h1>我的投資組合</h1>
                    <h2>Portfolio Tables</h2>
                </div>
                
                <img src="${pageContext.request.contextPath}/portfolio/images/portfolio.png" width="100%">
                
            </div>
        </div>
        <!-- Foot -->
        <%@include file="/portfolio/include/foot.jspf"  %>   
    </body>
</html>
