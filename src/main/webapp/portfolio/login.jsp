<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://unpkg.com/purecss@1.0.1/build/pure-min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/portfolio/js/util.js"></script>
        <title>Login/Register</title>
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
        <script>
            $(document).ready(function () {
                $("#add").on("click", function () {
                    var jsonObj = $('#myform').serializeObject(); // 將表單資料封裝序列
                    var jsonStr = JSON.stringify(jsonObj); // 將物件資料轉為 Json 字串
                    $.ajax({
                        url: "${pageContext.request.contextPath}/mvc/portfolio/investor/",
                        type: "POST",
                        contentType: "application/json; charset=utf-8",
                        data: jsonStr,
                        async: true,
                        cache: false,
                        processData: false, // 傳送至 Server 的資料，會自動轉為 query string 的型式，
                        // 如果是 GET 請求還會幫你附加到 URL。
                        // 可用 processData 選項禁止此自動轉換。
                        success: function (resposeJsonObject) {
                            console.log(JSON.stringify(resposeJsonObject));
                            alert('註冊成功！請收信~');
                            $('#myform').get(0).reset(); // 表單重置
                        },
                        error: function (e, textStatus) {
                            console.log(e);
                            console.log(e.status);
                            console.log(e.responseText);
                            console.log(stripHTML(e.responseText));
                            console.log(textStatus);
                            alert('註冊失敗！請查看 log~');
                        }
                    });
                });

            });
            
        </script>

    </head>
    <body style="padding:15px" bgcolor="#DDDDDD">
    <center>
        <table bgcolor="#FFFFFF">
            <td valign="top">
                <form class="pure-form" method="post" action="${pageContext.request.contextPath}/mvc/portfolio/login">
                    <fieldset>
                        <legend><h1><img src="${pageContext.request.contextPath}/images/user.png" width="40" valign="middle"> Login Form</h1></legend>

                        <input type="text" name="username" placeholder="Username"><p />
                        <input type="password" name="password" placeholder="Password"><p />

                        <button type="submit" class="pure-button pure-button-primary">Sign in</button>
                    </fieldset>
                </form>
            </td>
            <td valign="top">
                <form id="myform" class="pure-form" method="post" action="${pageContext.request.contextPath}/mvc/portfolio/login">
                    <fieldset>
                        <legend><h1><img src="${pageContext.request.contextPath}/images/user.png" width="40" valign="middle"> Register Form</h1></legend>
                        <input type="text" name="username" placeholder="Username"><p />
                        <input type="password" name="password" placeholder="Password"><p />
                        <input id="email" name="email" placeholder="Email"/><p />
                        <input id="balance" name="balance" placeholder="Balance" type="number"/><p />
                        <button id="add" type="button" class="pure-button pure-button-primary">Register</button>
                    </fieldset>
                </form>
            </td>
        </table>
    </center>
</body>
</html>
