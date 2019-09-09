$(document).ready(function () {
    refreshPatientInfo();
});

function diagnoseNow(obj) {
    let registrationID = $(obj).attr("value");
    let sendJson = {};
    sendJson["registrationID"] = registrationID;

    $.ajax({
        url: "/getSpecificPatientInfo",
        type: "post",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(sendJson),
        success: function (PatientInfo) {
            let diagnosingPatient = "患者姓名：";
            diagnosingPatient += PatientInfo.patientName;
            diagnosingPatient += " 病历号：";
            diagnosingPatient += PatientInfo.medicalRecordID;
            diagnosingPatient += " 年龄：";
            diagnosingPatient += PatientInfo.age;
            diagnosingPatient += " 性别：";
            diagnosingPatient += PatientInfo.gender;

            $("#diagnosingPatient").text(diagnosingPatient);
            $("#diagnosingPatientRegistrationID").attr("value", registrationID);
        }
    });
}

$(document).ready(function () {
    $("input[name='searchDiseaseCategory']").bind("input propertychange", function () {
        let sendJson = {};
        sendJson["pattern"] = $("input[name='searchDiseaseCategory']").val();
        $.ajax({
            url: "/findDiseaseCategory",
            type: "post",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(sendJson),
            success: function (DiseaseCategoryInfo) {
                $("#diseaseCategoryList").empty();
                for (let i = 0; i < DiseaseCategoryInfo.length; i++) {
                    $("#diseaseCategoryList").append('<option name="' + DiseaseCategoryInfo[i].diseaseCateId + '">' + DiseaseCategoryInfo[i].diseaseCateName + '</option>');
                }
            }
        });
    });
});

var pageNum = 1;
var wholePageNum = 0;

function findDiseaseNextPage() {
    if (pageNum < wholePageNum) {
        pageNum++;
        let diseaseCategorySearch = $("input[name='searchDiseaseCategory']").val();
        let diseaseSearch = $("input[name='searchDisease']").val();
        let sendJson = {};
        sendJson["diseaseCategory"] = diseaseCategorySearch;
        sendJson["pattern"] = diseaseSearch;
        sendJson["pageNum"] = pageNum;
        sendJson["pageSize"] = 8;

        $.ajax({
            url: "/findDisease",
            type: "post",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(sendJson),
            success: function (DiseaseInfo) {
                $("#searchResult").empty();
                for (let i = 0; i < DiseaseInfo.length; i++) {
                    $("#searchResult").append('<h4><button type="button" class="btn btn-link" onclick="addDisease(this)" id="' + DiseaseInfo[i].diseaseId + '">' + DiseaseInfo[i].diseaseIcd + ' ' + '</button></h4>');
                    $("#searchResult").append('<p id="' + DiseaseInfo[i].diseaseId + '">' + DiseaseInfo[i].diseaseName + ' ' + '</p>');
                }
                $("#pageNum" + pageNum).attr("class", "btn btn-white active");
                $("#pageNum" + (pageNum - 1)).attr("class", "btn btn-white");
            }
        });
    }
}

function findDiseaseLastPage() {
    console.log(pageNum);
    if (pageNum > 1) {
        pageNum--;
        let diseaseCategorySearch = $("input[name='searchDiseaseCategory']").val();
        let diseaseSearch = $("input[name='searchDisease']").val();
        let sendJson = {};
        sendJson["diseaseCategory"] = diseaseCategorySearch;
        sendJson["pattern"] = diseaseSearch;
        sendJson["pageNum"] = pageNum;
        sendJson["pageSize"] = 8;

        $.ajax({
            url: "/findDisease",
            type: "post",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(sendJson),
            success: function (DiseaseInfo) {
                $("#searchResult").empty();
                for (let i = 0; i < DiseaseInfo.length; i++) {
                    $("#searchResult").append('<h4><button type="button" class="btn btn-link" onclick="addDisease(this)" id="' + DiseaseInfo[i].diseaseId + '">' + DiseaseInfo[i].diseaseIcd + ' ' + '</button></h4>');
                    $("#searchResult").append('<p id="' + DiseaseInfo[i].diseaseId + '">' + DiseaseInfo[i].diseaseName + ' ' + '</p>');
                }
                $("#pageNum" + pageNum).attr("class", "btn btn-white active");
                $("#pageNum" + (pageNum + 1)).attr("class", "btn btn-white");
            }
        });
    }
}

function findDiseaseThisPage(obj) {
    $("#pageNum" + pageNum).attr("class", "btn btn-white");
    pageNum = $(obj).text();
    let diseaseCategorySearch = $("input[name='searchDiseaseCategory']").val();
    let diseaseSearch = $("input[name='searchDisease']").val();
    let sendJson = {};
    sendJson["diseaseCategory"] = diseaseCategorySearch;
    sendJson["pattern"] = diseaseSearch;
    sendJson["pageNum"] = pageNum;
    sendJson["pageSize"] = 8;
    $.ajax({
        url: "/findDisease",
        type: "post",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(sendJson),
        success: function (DiseaseInfo) {
            $("#searchResult").empty();
            for (let i = 0; i < DiseaseInfo.length; i++) {
                $("#searchResult").append('<h4><button type="button" class="btn btn-link" onclick="addDisease(this)" id="' + DiseaseInfo[i].diseaseId + '">' + DiseaseInfo[i].diseaseIcd + ' ' + '</button></h4>');
                $("#searchResult").append('<p id="' + DiseaseInfo[i].diseaseId + '">' + DiseaseInfo[i].diseaseName + ' ' + '</p>');
            }
            $(obj).attr("class", "btn btn-white active");
        }
    });
}

$(document).ready(function () {
    $("#searchNow").click(function () {
        pageNum = 1;
        let diseaseCategorySearch = $("input[name='searchDiseaseCategory']").val();
        let diseaseSearch = $("input[name='searchDisease']").val();
        let sendJson = {};
        sendJson["diseaseCategory"] = diseaseCategorySearch;
        sendJson["pattern"] = diseaseSearch;
        sendJson["pageNum"] = pageNum;
        sendJson["pageSize"] = 8;
        $.ajax({
            url: "/findDisease",
            type: "post",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(sendJson),
            success: function (DiseaseInfo) {
                $("#searchResult").empty();
                wholePageNum = DiseaseInfo[0].wholePage;
                for (let i = 0; i < DiseaseInfo.length; i++) {
                    $("#searchResult").append('<h4><button type="button" class="btn btn-link" onclick="addDisease(this)" id="' + DiseaseInfo[i].diseaseId + '">' + DiseaseInfo[i].diseaseIcd + '</button></h4>');
                    $("#searchResult").append('<p id="' + DiseaseInfo[i].diseaseId + '">' + DiseaseInfo[i].diseaseName + ' ' + '</p>');
                }
                $("#firstPageNum").empty();
                $("#firstPageNum").append('<button class="btn btn-white" type="button" onclick="findDiseaseLastPage()"><i class="fa fa-chevron-left"></i></button>');
                for (let i = 0; i < DiseaseInfo[0].wholePage; i++) {
                    if (i == 0) {
                        $("#firstPageNum").append('<button class="btn btn-white active" onclick="findDiseaseThisPage(this)" id="pageNum' + (i + 1) + '">' + (i + 1) + '</button>');
                    } else {
                        $("#firstPageNum").append('<button class="btn btn-white" onclick="findDiseaseThisPage(this)" id="pageNum' + (i + 1) + '">' + (i + 1) + '</button>');
                    }
                }
                $("#firstPageNum").append('<button class="btn btn-white" type="button"><i class="fa fa-chevron-right" onclick="findDiseaseNextPage()"></i></button>\n');
            }
        });
    });
});

function addDisease(obj) {
    let diseaseID = $(obj).attr("id");
    let diseaseName = $("p[id=" + diseaseID + "]").text();
    let diseaseICD = $(obj).text();
    $("#confirmInfoTable tbody").append(
        '<tr>' +
        '<td><input type="checkbox" class="i-checks" name="diseaseID" value="' + diseaseID + '"></td>' +
        '<td>' + diseaseICD + '</td>' +
        '<td>' + diseaseName + '</td>' +
        '<td></td>' +
        '<td><input class="form-control input-sm" type="date" name="' + diseaseID + '"></td>' +
        '</tr>');
}

$(document).ready(function () {
    $("#diagnosingPatientRegistrationID").click(function () {
        if ($("#patientSelectDiv").is(':hidden')) {
            $("#patientSelectDiv").fadeIn();
            $("#patientFunctionDiv").attr("class", "col-md-8");
        } else {
            $("#patientSelectDiv").hide();
            $("#patientFunctionDiv").attr("class", "col-md-12");
        }
    });
});

var drugPageNum = 1;
var drugWholePage = 0;

$(document).ready(function () {
    $("#searchDrugNow").click(function () {
        drugPageNum = 1;
        let drugSearch = $("input[name='searchDrug']").val();
        let sendJson = {};
        sendJson["pattern"] = drugSearch;
        sendJson["pageNum"] = drugPageNum;
        sendJson["pageSize"] = 8;
        $.ajax({
            url: "/findDrug",
            type: "post",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(sendJson),
            success: function (DrugInfo) {
                console.log(DrugInfo);
                $("#searchDrugResult").empty();
                drugWholePage = DrugInfo[0].wholePage;
                for (let i = 0; i < DrugInfo.length; i++) {
                    $("#searchDrugResult").append('<h3><button type="button" class="btn btn-link" onclick="addDrug(this)" id="' + DrugInfo[i].drugId + '">' + DrugInfo[i].drugName + '</button></h3>');
                    $("#searchDrugResult").append('<p>规格：' + DrugInfo[i].drugSpecif + ' ' +
                        '生产厂家：' + DrugInfo[i].drugManufacturer + ' ' +
                        '剂型：' + DrugInfo[i].drugsDosage + ' ' +
                        '类型：' + DrugInfo[i].drugsType + ' ' +
                        '包装单位：' + DrugInfo[i].drugUnit + ' ' +
                        '单价：' + DrugInfo[i].drugUnitPrice +
                        '</p>');
                }
                $("#firstDrugPageNum").empty();
                $("#firstDrugPageNum").append('<button class="btn btn-white" type="button" onclick="findDrugLastPage()"><i class="fa fa-chevron-left"></i></button>');
                for (let i = 0; i < DrugInfo[0].wholePage; i++) {
                    if (i == 0) {
                        $("#firstDrugPageNum").append('<button class="btn btn-white active" onclick="findDrugThisPage(this)" id="drugPageNum' + (i + 1) + '">' + (i + 1) + '</button>');
                    } else {
                        $("#firstDrugPageNum").append('<button class="btn btn-white" onclick="findDrugThisPage(this)" id="drugPageNum' + (i + 1) + '">' + (i + 1) + '</button>');
                    }
                }
                $("#firstDrugPageNum").append('<button class="btn btn-white" type="button"><i class="fa fa-chevron-right" onclick="findDrugNextPage()"></i></button>\n');
            }
        });
    });
});

function addDrug(obj) {
    let drugID = $(obj).attr("id");
    let sendJson = {};
    sendJson["drugID"] = drugID;

    $.ajax({
        url: "/findSpecifDrug",
        type: "post",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(sendJson),
        success: function (DrugInfo) {
            $("#preDetailInfo tbody").append(
                '<tr>' +
                '<td><input type="checkbox" class="i-checks" name="drugID" value="' + drugID + '"></td>' +
                '<td>' + DrugInfo.drugName + '</td>' +
                '<td>' + DrugInfo.drugSpecif + '</td>' +
                '<td>' + DrugInfo.drugUnitPrice + '</td>' +
                '<td><input class="form-control col-sm-8" type="text" name="drugUsage' + drugID + '"></td>' +
                '<td><input class="form-control col-sm-8" type="text" name="drugUsageNum' + drugID + '"></td>' +
                '<td><input class="form-control col-sm-8" type="text" name="drugFreq' + drugID + '"></td>' +
                '<td><input class="form-control col-sm-8" type="number" name="drugAmount' + drugID + '"></td>' +
                '</tr>');
        }
    });
}

function findDrugNextPage() {
    if (drugPageNum < drugWholePage) {
        drugPageNum++;

        let drugSearch = $("input[name='searchDrug']").val();
        let sendJson = {};
        sendJson["pattern"] = drugSearch;
        sendJson["pageNum"] = drugPageNum;
        sendJson["pageSize"] = 8;
        $.ajax({
            url: "/findDrug",
            type: "post",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(sendJson),
            success: function (DrugInfo) {
                console.log(DrugInfo);
                $("#searchDrugResult").empty();
                drugWholePage = DrugInfo[0].wholePage;
                for (let i = 0; i < DrugInfo.length; i++) {
                    $("#searchDrugResult").append('<h3><button type="button" class="btn btn-link" onclick="addDrug(this)" id="' + DrugInfo[i].drugId + '">' + DrugInfo[i].drugName + '</button></h3>');
                    $("#searchDrugResult").append('<p>规格：' + DrugInfo[i].drugSpecif + ' ' +
                        '生产厂家：' + DrugInfo[i].drugManufacturer + ' ' +
                        '剂型：' + DrugInfo[i].drugsDosage + ' ' +
                        '类型：' + DrugInfo[i].drugsType + ' ' +
                        '包装单位：' + DrugInfo[i].drugUnit + ' ' +
                        '单价：' + DrugInfo[i].drugUnitPrice +
                        '</p>');
                }
                $("#drugPageNum" + drugPageNum).attr("class", "btn btn-white active");
                $("#drugPageNum" + (drugPageNum - 1)).attr("class", "btn btn-white");
            }
        });
    }
}

function findDrugLastPage() {
    if (drugPageNum > 1) {
        drugPageNum--;

        let drugSearch = $("input[name='searchDrug']").val();
        let sendJson = {};
        sendJson["pattern"] = drugSearch;
        sendJson["pageNum"] = drugPageNum;
        sendJson["pageSize"] = 8;
        $.ajax({
            url: "/findDrug",
            type: "post",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(sendJson),
            success: function (DrugInfo) {
                console.log(DrugInfo);
                $("#searchDrugResult").empty();
                drugWholePage = DrugInfo[0].wholePage;
                for (let i = 0; i < DrugInfo.length; i++) {
                    $("#searchDrugResult").append('<h3><button type="button" class="btn btn-link" onclick="addDrug(this)" id="' + DrugInfo[i].drugId + '">' + DrugInfo[i].drugName + '</button></h3>');
                    $("#searchDrugResult").append('<p>规格：' + DrugInfo[i].drugSpecif + ' ' +
                        '生产厂家：' + DrugInfo[i].drugManufacturer + ' ' +
                        '剂型：' + DrugInfo[i].drugsDosage + ' ' +
                        '类型：' + DrugInfo[i].drugsType + ' ' +
                        '包装单位：' + DrugInfo[i].drugUnit + ' ' +
                        '单价：' + DrugInfo[i].drugUnitPrice +
                        '</p>');
                }
                $("#drugPageNum" + drugPageNum).attr("class", "btn btn-white active");
                $("#drugPageNum" + (drugPageNum + 1)).attr("class", "btn btn-white");
            }
        });
    }
}

function findDrugThisPage(obj) {
    $("#drugPageNum" + drugPageNum).attr("class", "btn btn-white");
    drugPageNum = $(obj).text();
    let drugSearch = $("input[name='searchDrug']").val();
    let sendJson = {};
    sendJson["pattern"] = drugSearch;
    sendJson["pageNum"] = drugPageNum;
    sendJson["pageSize"] = 8;
    $.ajax({
        url: "/findDrug",
        type: "post",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(sendJson),
        success: function (DrugInfo) {
            console.log(DrugInfo);
            $("#searchDrugResult").empty();
            drugWholePage = DrugInfo[0].wholePage;
            for (let i = 0; i < DrugInfo.length; i++) {
                $("#searchDrugResult").append('<h3><button type="button" class="btn btn-link" onclick="addDrug(this)" id="' + DrugInfo[i].drugId + '">' + DrugInfo[i].drugName + '</button></h3>');
                $("#searchDrugResult").append('<p>规格：' + DrugInfo[i].drugSpecif + ' ' +
                    '生产厂家：' + DrugInfo[i].drugManufacturer + ' ' +
                    '剂型：' + DrugInfo[i].drugsDosage + ' ' +
                    '类型：' + DrugInfo[i].drugsType + ' ' +
                    '包装单位：' + DrugInfo[i].drugUnit + ' ' +
                    '单价：' + DrugInfo[i].drugUnitPrice +
                    '</p>');
            }
            $(obj).attr("class", "btn btn-white active");
        }
    });
}

function addPreName() {
    let preName = $("input[name=preName]").val();
    $("#preInfo tbody").append('<tr>' +
        '<td><button class="btn btn-primary btn-xs active" id="' + preName + '"></button></td>' +
        '<td>' + preName + '</td>' +
        '<td>暂存</td>' +
        '</tr>');
}

