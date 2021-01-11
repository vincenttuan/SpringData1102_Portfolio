String.format = function () {
    if (arguments.length == 0)
        return null;
    var str = arguments[0];
    for (var i = 1; i < arguments.length; i++) {
        var re = new RegExp('\\{' + (i - 1) + '\\}', 'gm');
        str = str.replace(re, arguments[i]);
    }
    return str;
};
$.fn.serializeObject = function () {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function () {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};
function getYMDHMS(time) {
    var myDate = new Date(time);
    var YMD = addZero(myDate.getFullYear()) + "/" + addZero((myDate.getMonth() + 1)) + "/" + addZero(myDate.getDate());
    var HMS = addZero(myDate.getHours()) + ":" + addZero(myDate.getMinutes()) + ":" + addZero(myDate.getSeconds());
    var YMDHMS = YMD + " " + HMS;
    return YMDHMS;
}
function getYMD(time) {
    var myDate = new Date(time);
    var YMD = addZero(myDate.getFullYear()) + "/" + addZero((myDate.getMonth() + 1)) + "/" + addZero(myDate.getDate());
    return YMD;
}
function getMD(time) {
    var myDate = new Date(time);
    var MD = addZero((myDate.getMonth() + 1)) + "/" + addZero(myDate.getDate());
    return MD;
}
function getHMS(time) {
    var myDate = new Date(time);
    var HMS = addZero(myDate.getHours()) + ":" + addZero(myDate.getMinutes()) + ":" + addZero(myDate.getSeconds());
    return HMS;
}
function addZero(n) {
    return (n < 10) ? ("0" + n) : n;
}

function sortJson(datas, prop, asc) {
    datas.sort(function (a, b) {
        if (asc) {
            return (a[prop] > b[prop]) ? 1 : ((a[prop] < b[prop]) ? -1 : 0);
        } else {
            return (b[prop] > a[prop]) ? 1 : ((b[prop] < a[prop]) ? -1 : 0);
        }
    });
}

function numberFormat(nStr) {
    nStr += '';
    x = nStr.split('.');
    x1 = x[0];
    x2 = x.length > 1 ? '.' + x[1] : '';
    var rgx = /(\d+)(\d{3})/;
    while (rgx.test(x1)) {
        x1 = x1.replace(rgx, '$1' + ',' + '$2');
    }
    return x1 + x2;
}

function stripHTML(input) {
    var output = '';
    if (typeof (input) == 'string') {
        var output = input.replace(/(<([^>]+)>)/ig, "");
    }
    return output;
}