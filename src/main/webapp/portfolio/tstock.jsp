<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html>
    <head>
        <!-- head -->
        <%@include file="/portfolio/include/head.jspf"  %>

        <script>
            $(document).ready(function () {
                $("#myTable").on("click", "tr", function () {
                    var id = $(this).find('td').eq(0).text().trim();
                    //console.log(id);
                    $.get("${pageContext.request.contextPath}/mvc/portfolio/tstock/" + id, function (data, status) {
                        console.log(JSON.stringify(data));
                        $("#myform").find("#id").val(data.id);
                        $("#myform").find("#name").val(data.name);
                        $("#myform").find("#symbol").val(data.symbol);
                        $("#myform").find("#classify_id").val(data.classify.id);
                    });
                });
                $("#add").on("click", function () {
                    var jsonObj = $('#myform').serializeObject();
                    var jsonStr = JSON.stringify(jsonObj);
                    $.ajax({
                        url: "${pageContext.request.contextPath}/mvc/portfolio/tstock/",
                        type: "POST",
                        contentType: "application/json; charset=utf-8",
                        data: jsonStr, //Stringified Json Object
                        async: false, //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
                        cache: false, //This will force requested pages not to be cached by the browser  
                        processData: false, //To avoid making query String instead of JSON
                        success: function (resposeJsonObject) {
                            //alert(JSON.stringify(resposeJsonObject));
                            table_list();
                        }
                    });
                });
                $("#upt").on("click", function () {
                    var jsonObj = $('#myform').serializeObject();
                    var jsonStr = JSON.stringify(jsonObj);
                    $.ajax({
                        url: "${pageContext.request.contextPath}/mvc/portfolio/tstock/",
                        type: "PUT",
                        contentType: "application/json; charset=utf-8",
                        data: jsonStr, //Stringified Json Object
                        async: false, //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
                        cache: false, //This will force requested pages not to be cached by the browser  
                        processData: false, //To avoid making query String instead of JSON
                        success: function (resposeJsonObject) {
                            //alert(JSON.stringify(resposeJsonObject));
                            table_list();
                        }
                    });
                });
                $("#del").on("click", function () {
                    var id = $("#myform").find("#id").val();
                    $.ajax({
                        url: "${pageContext.request.contextPath}/mvc/portfolio/tstock/" + id,
                        type: "DELETE",
                        async: false, //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
                        cache: false, //This will force requested pages not to be cached by the browser  
                        processData: false, //To avoid making query String instead of JSON
                        success: function (resposeJsonObject) {
                            //alert(resposeJsonObject);
                            table_list();
                        }
                    });
                });
                
                // Classify 下拉選單
                classify_list();
                
                // 資料列表
                table_list();
            });

            function classify_list() {
                $.get("${pageContext.request.contextPath}/mvc/portfolio/classify/", function (datas, status) {
                    console.log("Datas: " + datas);
                    datas.map(function (data) {
                        $('#classify_id').append('<option value="' + data.id + '">' + data.name + '</option>');
                    });
                });
            }

            function table_list() {
                $.get("${pageContext.request.contextPath}/mvc/portfolio/tstock/", function (datas, status) {
                    console.log("Datas: " + datas);
                    $("#myTable tbody > tr").remove();
                    $.each(datas, function (i, item) {
                        var html = '<tr><td>{0}</td><td>{1}</td><td>{2}</td><td>{3}</td></tr>';
                        $('#myTable').append(String.format(html,
                                item.id,
                                item.name,
                                item.symbol,
                                item.classify.name
                                ));
                    });
                });
            }
        </script>
    </head>
    <body>
        <div id="layout">
            <!-- Menu toggle -->
            <%@include file="/portfolio/include/toggle.jspf"  %>

            <!-- Menu -->
            <%@include file="/portfolio/include/menu.jspf"  %>

            <div id="main">
                <div class="header">
                    <h1>TStock</h1>
                    <h2>股票、指數、匯率</h2>
                </div>
                <table>
                    <td valign="top">
                        <div class="content">
                            <form id="myform" class="pure-form">
                                <fieldset>
                                    <legend> <h2 class="content-subhead">資料維護</h2></legend>

                                    <input id="id" vslue="0"   name="id" placeholder="ID" readonly="true"/><p />
                                    <input id="name" name="name" placeholder="商品名稱"/><p />
                                    <input id="symbol" name="symbol" placeholder="商品代號"/><p />
                                    商品分類：<select id="classify_id" name="classify_id"></select><p />  
                                    <button id="add" type="button" class="pure-button pure-button-primary">新增</button>
                                    <button id="upt" type="button" class="pure-button pure-button-primary">修改</button>
                                    <button id="del" type="button" class="pure-button pure-button-primary">刪除</button>
                                </fieldset>
                            </form>
                        </div>
                    </td>    
                    <td valign="top">    
                        <div class="content">
                            <form class="pure-form">
                                <fieldset>
                                    <legend><h2 class="content-subhead">資料列表</h2></legend>
                                    <table id="myTable" class="pure-table pure-table-bordered">
                                        <thead>
                                            <tr>
                                                <th>id</th>
                                                <th>name</th>
                                                <th>symbol</th>
                                                <th>classify name</th>
                                            </tr>
                                        </thead>

                                        <tbody>

                                        </tbody>
                                    </table> 
                                </fieldset>
                            </form>
                        </div>    
                    </td>
                </table>


            </div>
        </div>
        <!-- Foot -->
        <%@include file="/portfolio/include/foot.jspf"  %>   
    </body>
</html>
