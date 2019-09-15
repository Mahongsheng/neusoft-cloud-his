//界面加载完毕后刷新患者列表
$(document).ready(function () {
    refreshPatientInfo();
});

//点击某患者按钮后开始诊断该患者
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

//坚持疾病类别是否输入内容，如果输入内容则触发。
//在数据库中搜索用户输入的疾病类别，实时返回到前端并展示出来
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

//定义当前疾病页数和疾病总页数以便分页
var pageNum = 1;
var wholePageNum = 0;

//切换到下一页搜索到的疾病
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
                    $("#searchResult").append('<h3><button type="button" class="btn btn-link" onclick="addDisease(this)" id="' + DiseaseInfo[i].diseaseId + '">' + DiseaseInfo[i].diseaseIcd + ' ' + '</button></h3>');
                    $("#searchResult").append('<p id="' + DiseaseInfo[i].diseaseId + '">' + DiseaseInfo[i].diseaseName + ' ' + '</p>');
                }
                $("#pageNum" + pageNum).attr("class", "btn btn-white active");
                $("#pageNum" + (pageNum - 1)).attr("class", "btn btn-white");
            }
        });
    }
}

//切换到上一页搜索到的疾病
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
                    $("#searchResult").append('<h3><button type="button" class="btn btn-link" onclick="addDisease(this)" id="' + DiseaseInfo[i].diseaseId + '">' + DiseaseInfo[i].diseaseIcd + ' ' + '</button></h3>');
                    $("#searchResult").append('<p id="' + DiseaseInfo[i].diseaseId + '">' + DiseaseInfo[i].diseaseName + ' ' + '</p>');
                }
                $("#pageNum" + pageNum).attr("class", "btn btn-white active");
                $("#pageNum" + (pageNum + 1)).attr("class", "btn btn-white");
            }
        });
    }
}

//切换到某一特定页搜索到的疾病
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
                $("#searchResult").append('<h3><button type="button" class="btn btn-link" onclick="addDisease(this)" id="' + DiseaseInfo[i].diseaseId + '">' + DiseaseInfo[i].diseaseIcd + ' ' + '</button></h3>');
                $("#searchResult").append('<p id="' + DiseaseInfo[i].diseaseId + '">' + DiseaseInfo[i].diseaseName + ' ' + '</p>');
            }
            $(obj).attr("class", "btn btn-white active");
        }
    });
}

//检测搜索按钮，从数据库中搜索疾病并分页展示
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
                    $("#searchResult").append('<h3><button type="button" class="btn btn-link" onclick="addDisease(this)" id="' + DiseaseInfo[i].diseaseId + '">' + DiseaseInfo[i].diseaseIcd + '</button></h3>');
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

//添加疾病
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
        '<td><input class="form-control input-sm" type="date" data-rule="required:true|validate_beforeIncludeToday:true" name="' + diseaseID + '"><div style="color: red;display: none" id="' + diseaseID + '-input-error">该项不能为空，且日期应小于等于今日</div></td>' +
        '</tr>');
    getInputsByFunc();
}

//隐藏患者列表
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

//定义药品当前页码和总页数
var drugPageNum = 1;
var drugWholePage = 0;

//采用模糊匹配搜索药品并展示
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

//添加药品
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
                '<td><input class="form-control col-sm-8" type="text" data-rule="required:true" name="drugUsage' + drugID + '"><div style="color: red;display: none" id="drugUsage' + drugID + '-input-error">该项不能为空</div></td>' +
                '<td><input class="form-control col-sm-8" type="text" data-rule="required:true" name="drugUsageNum' + drugID + '"><div style="color: red;display: none" id="drugUsageNum' + drugID + '-input-error">该项不能为空</div></td>' +
                '<td><input class="form-control col-sm-8" type="text" data-rule="required:true" name="drugFreq' + drugID + '"><div style="color: red;display: none" id="drugFreq' + drugID + '-input-error">该项不能为空</div></td>' +
                '<td><input class="form-control col-sm-8" type="number" min="1" data-rule="required:true" name="drugAmount' + drugID + '"><div style="color: red;display: none" id="drugAmount' + drugID + '-input-error">该项不能为空</div></td>' +
                '</tr>');
            getInputsByFunc();
        }
    });
}

//找到下一页药品并展示
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

//找到上一页药品并展示
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

//找到特定页药品并展示
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

//添加处方名称
function addPreName() {
    let preName = $("input[name=preName]").val();
    $("#preInfo tbody").append('<tr>' +
        '<td><button class="btn btn-primary btn-xs active" id="' + preName + '"></button></td>' +
        '<td>' + preName + '</td>' +
        '<td>暂存</td>' +
        '</tr>');
}

