//加载挂号界面
$(document).ready(function () {
    $("#register").click(function () {
        $.ajax({
            url: "register.html", //这里是静态页的地址
            type: "GET", //静态页用get方法，否则服务器会抛出405错误
            success: function (register) {
                $("#playPicture").hide();
                $("#hideElement").html(register);
            }
        });
    });
});
//加载退号界面
$(document).ready(function () {
    $("#registerBack").click(function () {
        $.ajax({
            url: "registerBack.html", //这里是静态页的地址
            type: "GET", //静态页用get方法，否则服务器会抛出405错误
            success: function (registerBack) {
                $("#playPicture").hide();
                $("#hideElement").html(registerBack);
            }
        });
    });
});
//加载收费界面
$(document).ready(function () {
    $("#charge").click(function () {
        $.ajax({
            url: "charge.html", //这里是静态页的地址
            type: "GET", //静态页用get方法，否则服务器会抛出405错误
            success: function (charge) {
                $("#playPicture").hide();
                $("#hideElement").html(charge);
            }
        });
    });
});
//加载发药界面
$(document).ready(function () {
    $("#prescribe").click(function () {
        $.ajax({
            url: "prescribe.html", //这里是静态页的地址
            type: "GET", //静态页用get方法，否则服务器会抛出405错误
            success: function (prescribe) {
                $("#playPicture").hide();
                $("#hideElement").html(prescribe);
            }
        });
    });
});
//加载门诊病历界面
$(document).ready(function () {
    $("#medicalRecord").click(function () {
        $.ajax({
            url: "medicalRecord.html", //这里是静态页的地址
            type: "GET", //静态页用get方法，否则服务器会抛出405错误
            success: function (medicalRecord) {
                $("#playPicture").hide();
                $("#hideElement").html(medicalRecord);
            }
        });
    });
});