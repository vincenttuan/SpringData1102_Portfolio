<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html>
    <head>
        <!-- head -->
        <%@include file="/portfolio/include/head.jspf"  %>
        
        <!-- Asset -->
        <script>
            $(document).ready(function () {
                // 更新asset列表
                update();
                // 賣出
                $("#myTable").on("click", "tr td:nth-child(10)", function () {
                    if (confirm("是否要賣出？")) {
                        var po_id = $(this).attr('po_id');
                        var po_amount = $(this).attr('po_amount');
                        amount = prompt("請輸入賣出數量？", po_amount);
                        if (amount == null)
                            return;
                        if (parseInt(amount) > po_amount) {
                            alert('賣出數量不可大於 > ' + po_amount);
                            return;
                        }
                        $.ajax({
                            url: "${pageContext.request.contextPath}/mvc/portfolio/order/sell/" + po_id + "/" + amount,
                            type: "GET",
                            async: true,
                            cache: false,
                            processData: false, //To avoid making query String instead of JSON
                            success: function (resposeJsonObject) {
                                $.get("${pageContext.request.contextPath}/mvc/portfolio/investor/" + investor_id, function (data, status) {
                                    update();
                                });
                                alert('成交回報: ' + resposeJsonObject);
                                chart();
                            }
                        });
                    }
                });
                
                // 加碼
                $("#myTable").on("click", "tr td:nth-child(11)", function () {
                    var tstock_id = $(this).attr('tstock_id');
                    if (tstock_id == '')
                        return;
                    if (confirm("是否要加碼買進？")) {
                        amount = prompt("請輸入購買股數(請以1000股為單位)？", "1000");
                        if (amount == null)
                            return;
                        if (parseInt(amount) % 1000 != 0) {
                            alert('請輸入1000的倍數(1張=1000股)');
                            return;
                        }
                        $.ajax({
                            url: "${pageContext.request.contextPath}/mvc/portfolio/order/buy/" + tstock_id + "/" + amount,
                            type: "GET",
                            async: true,
                            cache: false,
                            processData: false, //To avoid making query String instead of JSON
                            success: function (resposeJsonObject) {
                                $.get("${pageContext.request.contextPath}/mvc/portfolio/investor/" + investor_id, function (data, status) {
                                    update();
                                });
                                alert('成交回報: ' + resposeJsonObject);
                                chart();
                            }
                        });
                    }
                });

            });
            
            // 更新asset列表
            function update() {
                $.get("${pageContext.request.contextPath}/mvc/portfolio/investor/" + investor_id, function (data, status) {
                    //console.log(JSON.stringify(data));
                    $("#balance").text(numberFormat(data.balance));
                    table_list(data.portfolios);
                    console.log(data.portfolios);
                    
                    // 
                });
            }
            
            // asset 列表
            function table_list(datas) {
                $("#myTable tbody > tr").remove();
                var profit_sum = 0;
                var asset_sum = 0;
                var foot_html = '<tr><td colspan="7" align="right">total</td><td nowrap="nowrap">{0}</td><td colspan="3"> </td></tr>';
                sortJson(datas, 'id', true);
                
                $.each(datas, function (i, item) {
                    var html = '<tr><td nowrap="nowrap">{0}</td><td nowrap="nowrap">{1}</td><td nowrap="nowrap">{2}</td><td nowrap="nowrap">{3}</td><td nowrap="nowrap" align="right">{4}</td><td nowrap="nowrap" align="right">{5}</td><td nowrap="nowrap" align="right">{6}</td><td nowrap="nowrap" align="right">{7}</td><td nowrap="nowrap">{8}</td><td po_id="{9}" po_amount="{10}">{11}</td><td po_id="{12}" tstock_id="{13}">{14}</td></tr>';
                    var profit = 0;
                    profit = parseInt((item.tStock.price - item.cost) * item.amount)
                    profit_sum += profit;
                    asset_sum += item.tStock.price * item.amount;
                    sellbtn_html = '<button type="button" class="pure-button pure-button-primary">賣出</button>';
                    buylbtn_html = '<button type="button" class="pure-button pure-button-primary">加碼</button>';
                    $('#myTable').append(String.format(html,
                            item.id,
                            item.tStock.classify.name,
                            item.tStock.symbol,
                            item.tStock.name,
                            item.tStock.price,
                            item.cost,
                            numberFormat(item.amount),
                            numberFormat(profit),
                            getYMDHMS(item.date),
                            item.id,
                            item.amount,
                            sellbtn_html,
                            item.id,
                            item.tStock.id,
                            buylbtn_html
                            ));
                });
                $('#myTable').append(String.format(foot_html, numberFormat(profit_sum)));
                $("#asset").text(numberFormat(asset_sum));
            }

        </script>

        <!--  繪圖 Chart -->
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <script>
            google.charts.load('current', {'packages': ['corechart']});
            google.charts.setOnLoadCallback(chart);
            
            function chart() {
                $("#piechart_asset").empty();
                $("#barchart_profit").empty();
                $.ajax({
                    url: "${pageContext.request.contextPath}/mvc/portfolio/chart/asset/" + investor_id,
                    type: "GET",
                    contentType: "application/json; charset=utf-8",
                    async: true,
                    cache: false,
                    processData: false,
                    success: function (datas) {
                        console.log(JSON.stringify(datas));
                        if (datas != 0 && datas.length > 0) {
                            drawAssetChart(datas);
                        }
                    }
                });
                $.ajax({
                    url: "${pageContext.request.contextPath}/mvc/portfolio/chart/profit/" + investor_id,
                    type: "GET",
                    contentType: "application/json; charset=utf-8",
                    async: true,
                    cache: false,
                    processData: false,
                    success: function (datas) {
                        console.log(datas);
                        if (datas != 0 && datas.length > 0) {
                            drawProfitChart(datas);
                        }
                    }
                });
            }
            
            function drawAssetChart(datas) {
                var data = [];
                var Header = ['Classify', 'AssetValue'];
                data.push(Header);
                alert(JSON.stringify(datas));
                for (var i = 0; i < datas.length; i++) {
                    var temp = [];
                    temp.push(datas[i].name);
                    temp.push(datas[i].subtotal);
                    data.push(temp);
                }

                var chartdata = new google.visualization.arrayToDataTable(data);
                var options = {
                    title: '資產配置',
                    chartArea: {left: 0}
                };

                var chart = new google.visualization.PieChart(document.getElementById('piechart_asset'));

                chart.draw(chartdata, options);
            }

            function drawProfitChart(datas) {
                var data = [];
                var Header = ['Classify', 'Balance(NTD)'];
                data.push(Header);
                for (var i = 0; i < datas.length; i++) {
                    var temp = [];
                    temp.push(datas[i].name);
                    temp.push(datas[i].subtotal);
                    data.push(temp);
                }

                var chartdata = new google.visualization.arrayToDataTable(data);
                var options = {
                    title: '資產損益',
                    chartArea: {left: 0}
                };

                var chart = new google.visualization.BarChart(document.getElementById('barchart_profit'));

                chart.draw(chartdata, options);
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
                    <h1>Asset $<span id="asset">0</span></h1>
                    <h2>Balance $<span id="balance">0</span></h2>
                </div>
                <table>
                    <tr>
                        <td valign="top">
                            <div class="content">
                                <form class="pure-form">
                                    <fieldset>
                                        <legend><h2 class="content-subhead">投資組合</h2></legend>
                                        <table id="myTable" class="pure-table pure-table-bordered">
                                            <thead>
                                                <tr>
                                                    <th>id</th>
                                                    <th>classify</th>
                                                    <th>symbol</th>
                                                    <th>name</th>
                                                    <th>price</th>
                                                    <th>cost</th>
                                                    <th>amount</th>
                                                    <th>profit</th>
                                                    <th>date</th>
                                                    <th>sell</th>
                                                    <th>buy</th>
                                                </tr>
                                            </thead>

                                            <tbody>

                                            </tbody>
                                        </table> 
                                    </fieldset>
                                </form>
                                <!-- Chart -->
                                <table border="0">
                                    <tr>
                                        <td>
                                            <div id="piechart_asset" style="width: auto; height: auto;"></div>
                                        </td>
                                        <td>
                                            <div id="barchart_profit" style="width: auto; height: auto;"></div>
                                        </td>
                                    </tr>
                                </table>

                            </div>
                        </td>
                    </tr>
                </table>

            </div>
        </div>
        <!-- Foot -->
        <%@include file="/portfolio/include/foot.jspf"  %>   
    </body>
</html>
