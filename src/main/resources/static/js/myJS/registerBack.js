function registerBack(obj) {
    let medicalRecordJson = {};
    let registrationJson = {};
    let medicalRecordID = $("input[name = 'medicalRecordID']").val();
    medicalRecordJson['medicalRecordID'] = medicalRecordID;
    registrationJson["registrationID"] = obj.id;
    $.ajax({
        url: "/registerBack",
        type: "post",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(registrationJson),
        success: function (ReturnState) {
            $('.alert').html('退号成功').addClass('alert-success').show().delay(1400).fadeOut();
            $("#RegistrationRecordInfo tbody").empty();

            if (medicalRecordID.length != 0) {
                let sendJson = {};
                sendJson["medicalRecordID"] = medicalRecordID;
                sendJson["pageNum"] = registerBackPageNum;
                sendJson["pageSize"] = 5;
                $("#RegistrationRecordInfo tbody").empty();
                $.ajax({
                    url: "/getRegistrationRecord",
                    type: "post",
                    contentType: "application/json",
                    dataType: "json",
                    data: JSON.stringify(sendJson),
                    success: function (PatientRegistrationRecord) {
                        let medicalRecordID = $("input[name = 'medicalRecordID']").val();
                        let name = $("input[name = 'name']").val();
                        let numID = $("input[name = 'numID']").val();

                        for (let i = 0; i < PatientRegistrationRecord.length; i++) {
                            if (PatientRegistrationRecord[i].registrationState == "待诊") {
                                $("#RegistrationRecordInfo tbody").append(
                                    '<tr>' +
                                    '<td>' + medicalRecordID + '</td>' +
                                    '<td>' + name + '</td>' +
                                    '<td>' + numID + '</td>' +
                                    '<td>' + PatientRegistrationRecord[i].registrationDate + '</td>' +
                                    '<td>' + PatientRegistrationRecord[i].registrationNoon + '</td>' +
                                    '<td>' + PatientRegistrationRecord[i].department + '</td>' +
                                    '<td>' + PatientRegistrationRecord[i].registrationState + '</td>' +
                                    '<td><button class="btn btn-xs btn-primary" onclick="registerBack(this)" id="' + PatientRegistrationRecord[i].registrationID + '">' + '退号' + '</button></td>' +
                                    '</tr>');
                            } else {
                                $("#RegistrationRecordInfo tbody").append(
                                    '<tr>' +
                                    '<td>' + medicalRecordID + '</td>' +
                                    '<td>' + name + '</td>' +
                                    '<td>' + numID + '</td>' +
                                    '<td>' + PatientRegistrationRecord[i].registrationDate + '</td>' +
                                    '<td>' + PatientRegistrationRecord[i].registrationNoon + '</td>' +
                                    '<td>' + PatientRegistrationRecord[i].department + '</td>' +
                                    '<td>' + PatientRegistrationRecord[i].registrationState + '</td>' +
                                    '<td><button class="btn btn-xs btn-primary" disabled="disabled" id="' + PatientRegistrationRecord[i].registrationID + '">' + '退号' + '</button></td>' +
                                    '</tr>');
                            }
                        }
                        wholeRegisterBackPageNum = PatientRegistrationRecord[0].wholePage;

                        $("#firstPageNum").empty();
                        $("#firstPageNum").append('<button class="btn btn-white" type="button" onclick="findRegisterLastPage()"><i class="fa fa-chevron-left"></i></button>');
                        for (let i = 0; i < PatientRegistrationRecord[0].wholePage; i++) {
                            if (i == 0) {
                                $("#firstPageNum").append('<button class="btn btn-white active" onclick="findRegisterThisPage(this)" id="pageNum' + (i + 1) + '">' + (i + 1) + '</button>');
                            } else {
                                $("#firstPageNum").append('<button class="btn btn-white" onclick="findRegisterThisPage(this)" id="pageNum' + (i + 1) + '">' + (i + 1) + '</button>');
                            }
                        }
                        $("#firstPageNum").append('<button class="btn btn-white" type="button"><i class="fa fa-chevron-right" onclick="findRegisterNextPage()"></i></button>\n');
                    }
                });
            }
        }
    });
}

var registerBackPageNum = 1;
var wholeRegisterBackPageNum = 0;

$(document).ready(function () {
    $("#searchPatient").click(function () {
        registerBackPageNum = 1;

        let medicalRecordID = $("input[name = 'medicalRecordID']").val();
        if (medicalRecordID.length != 0) {
            let sendJson = {};
            sendJson["medicalRecordID"] = medicalRecordID;
            sendJson["pageNum"] = registerBackPageNum;
            sendJson["pageSize"] = 5;
            $.ajax({
                url: "/getRegistrationInfo",
                type: "post",
                contentType: "application/json",
                dataType: "json",
                data: JSON.stringify(sendJson),
                success: function (RegistrationInfo) {
                    if (RegistrationInfo === undefined){
                        $('.alert').html('病历号不存在').addClass('alert-info').show().delay(1400).fadeOut();
                    }else {
                        $("input[name = 'medicalRecordID']").val(RegistrationInfo.medicalRecordID);
                        if (true) {
                            $("input[name = 'name']").val(RegistrationInfo.patientName);
                            $("input[name = 'numID']").val(RegistrationInfo.numID);
                            $("input[name = 'address']").val(RegistrationInfo.address);
                        }
                    }
                }
            });
            $("#RegistrationRecordInfo tbody").empty();
            $.ajax({
                url: "/getRegistrationRecord",
                type: "post",
                contentType: "application/json",
                dataType: "json",
                data: JSON.stringify(sendJson),
                success: function (PatientRegistrationRecord) {
                    let medicalRecordID = $("input[name = 'medicalRecordID']").val();
                    let name = $("input[name = 'name']").val();
                    let numID = $("input[name = 'numID']").val();

                    for (let i = 0; i < PatientRegistrationRecord.length; i++) {
                        if (PatientRegistrationRecord[i].registrationState == "待诊") {
                            $("#RegistrationRecordInfo tbody").append(
                                '<tr>' +
                                '<td>' + medicalRecordID + '</td>' +
                                '<td>' + name + '</td>' +
                                '<td>' + numID + '</td>' +
                                '<td>' + PatientRegistrationRecord[i].registrationDate + '</td>' +
                                '<td>' + PatientRegistrationRecord[i].registrationNoon + '</td>' +
                                '<td>' + PatientRegistrationRecord[i].department + '</td>' +
                                '<td>' + PatientRegistrationRecord[i].registrationState + '</td>' +
                                '<td><button class="btn btn-xs btn-primary" onclick="registerBack(this)" id="' + PatientRegistrationRecord[i].registrationID + '">' + '退号' + '</button></td>' +
                                '</tr>');
                        } else {
                            $("#RegistrationRecordInfo tbody").append(
                                '<tr>' +
                                '<td>' + medicalRecordID + '</td>' +
                                '<td>' + name + '</td>' +
                                '<td>' + numID + '</td>' +
                                '<td>' + PatientRegistrationRecord[i].registrationDate + '</td>' +
                                '<td>' + PatientRegistrationRecord[i].registrationNoon + '</td>' +
                                '<td>' + PatientRegistrationRecord[i].department + '</td>' +
                                '<td>' + PatientRegistrationRecord[i].registrationState + '</td>' +
                                '<td><button class="btn btn-xs btn-primary" disabled="disabled" id="' + PatientRegistrationRecord[i].registrationID + '">' + '退号' + '</button></td>' +
                                '</tr>');
                        }

                    }
                    wholeRegisterBackPageNum = PatientRegistrationRecord[0].wholePage;

                    $("#firstPageNum").empty();
                    $("#firstPageNum").append('<button class="btn btn-white" type="button" onclick="findRegisterLastPage()"><i class="fa fa-chevron-left"></i></button>');
                    for (let i = 0; i < PatientRegistrationRecord[0].wholePage; i++) {
                        if (i == 0) {
                            $("#firstPageNum").append('<button class="btn btn-white active" onclick="findRegisterThisPage(this)" id="pageNum' + (i + 1) + '">' + (i + 1) + '</button>');
                        } else {
                            $("#firstPageNum").append('<button class="btn btn-white" onclick="findRegisterThisPage(this)" id="pageNum' + (i + 1) + '">' + (i + 1) + '</button>');
                        }
                    }
                    $("#firstPageNum").append('<button class="btn btn-white" type="button"><i class="fa fa-chevron-right" onclick="findRegisterNextPage()"></i></button>\n');
                }
            });
        }
    });
});

function findRegisterNextPage() {
    if (registerBackPageNum < wholeRegisterBackPageNum) {
        registerBackPageNum++;

        let medicalRecordID = $("input[name = 'medicalRecordID']").val();
        if (medicalRecordID.length != 0) {
            let sendJson = {};
            sendJson['pageNum'] = registerBackPageNum;
            sendJson['pageSize'] = 5;
            sendJson['medicalRecordID'] = medicalRecordID;
            $("#RegistrationRecordInfo tbody").empty();
            $.ajax({
                url: "/getRegistrationRecord",
                type: "post",
                contentType: "application/json",
                dataType: "json",
                data: JSON.stringify(sendJson),
                success: function (PatientRegistrationRecord) {
                    let medicalRecordID = $("input[name = 'medicalRecordID']").val();
                    let name = $("input[name = 'name']").val();
                    let numID = $("input[name = 'numID']").val();

                    for (let i = 0; i < PatientRegistrationRecord.length; i++) {
                        $("#RegistrationRecordInfo tbody").append(
                            '<tr>' +
                            '<td>' + medicalRecordID + '</td>' +
                            '<td>' + name + '</td>' +
                            '<td>' + numID + '</td>' +
                            '<td>' + PatientRegistrationRecord[i].registrationDate + '</td>' +
                            '<td>' + PatientRegistrationRecord[i].registrationNoon + '</td>' +
                            '<td>' + PatientRegistrationRecord[i].department + '</td>' +
                            '<td>' + PatientRegistrationRecord[i].registrationState + '</td>' +
                            '<td><button class="btn btn-sm btn-primary" onclick="registerBack(this)" id="' + PatientRegistrationRecord[i].registrationID + '">' + '退号' + '</button></td>' +
                            '</tr>');
                    }
                    $("#pageNum" + registerBackPageNum).attr("class", "btn btn-white active");
                    $("#pageNum" + (registerBackPageNum - 1)).attr("class", "btn btn-white");
                }
            });
        }
    }
}

function findRegisterLastPage() {
    console.log(registerBackPageNum);
    if (registerBackPageNum > 1) {
        registerBackPageNum--;

        let medicalRecordID = $("input[name = 'medicalRecordID']").val();
        if (medicalRecordID.length != 0) {
            let sendJson = {};
            sendJson['pageNum'] = registerBackPageNum;
            sendJson['pageSize'] = 5;
            sendJson['medicalRecordID'] = medicalRecordID;
            $("#RegistrationRecordInfo tbody").empty();
            $.ajax({
                url: "/getRegistrationRecord",
                type: "post",
                contentType: "application/json",
                dataType: "json",
                data: JSON.stringify(sendJson),
                success: function (PatientRegistrationRecord) {
                    let medicalRecordID = $("input[name = 'medicalRecordID']").val();
                    let name = $("input[name = 'name']").val();
                    let numID = $("input[name = 'numID']").val();

                    for (let i = 0; i < PatientRegistrationRecord.length; i++) {
                        $("#RegistrationRecordInfo tbody").append(
                            '<tr>' +
                            '<td>' + medicalRecordID + '</td>' +
                            '<td>' + name + '</td>' +
                            '<td>' + numID + '</td>' +
                            '<td>' + PatientRegistrationRecord[i].registrationDate + '</td>' +
                            '<td>' + PatientRegistrationRecord[i].registrationNoon + '</td>' +
                            '<td>' + PatientRegistrationRecord[i].department + '</td>' +
                            '<td>' + PatientRegistrationRecord[i].registrationState + '</td>' +
                            '<td><button class="btn btn-sm btn-primary" onclick="registerBack(this)" id="' + PatientRegistrationRecord[i].registrationID + '">' + '退号' + '</button></td>' +
                            '</tr>');
                    }
                    $("#pageNum" + registerBackPageNum).attr("class", "btn btn-white active");
                    $("#pageNum" + (registerBackPageNum + 1)).attr("class", "btn btn-white");
                }
            });
        }
    }
}

function findRegisterThisPage(obj) {
    $("#pageNum" + registerBackPageNum).attr("class", "btn btn-white");
    registerBackPageNum = $(obj).text();

    let medicalRecordID = $("input[name = 'medicalRecordID']").val();
    if (medicalRecordID.length != 0) {
        let sendJson = {};
        sendJson['pageNum'] = registerBackPageNum;
        sendJson['pageSize'] = 5;
        sendJson['medicalRecordID'] = medicalRecordID;
        $("#RegistrationRecordInfo tbody").empty();
        $.ajax({
            url: "/getRegistrationRecord",
            type: "post",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(sendJson),
            success: function (PatientRegistrationRecord) {
                let medicalRecordID = $("input[name = 'medicalRecordID']").val();
                let name = $("input[name = 'name']").val();
                let numID = $("input[name = 'numID']").val();

                for (let i = 0; i < PatientRegistrationRecord.length; i++) {
                    $("#RegistrationRecordInfo tbody").append(
                        '<tr>' +
                        '<td>' + medicalRecordID + '</td>' +
                        '<td>' + name + '</td>' +
                        '<td>' + numID + '</td>' +
                        '<td>' + PatientRegistrationRecord[i].registrationDate + '</td>' +
                        '<td>' + PatientRegistrationRecord[i].registrationNoon + '</td>' +
                        '<td>' + PatientRegistrationRecord[i].department + '</td>' +
                        '<td>' + PatientRegistrationRecord[i].registrationState + '</td>' +
                        '<td><button class="btn btn-sm btn-primary" onclick="registerBack(this)" id="' + PatientRegistrationRecord[i].registrationID + '">' + '退号' + '</button></td>' +
                        '</tr>');
                }
                $(obj).attr("class", "btn btn-white active");
            }
        });
    }
}