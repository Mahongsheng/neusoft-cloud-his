//初始化挂号界面的病历号、挂号科室
$(document).ready(function () {
    var medicalRecordID = $("input[name = 'medicalRecordID']").val();
    var medicalIDJson = {};
    medicalIDJson['medicalRecordID'] = medicalRecordID;
    getMedicalRecordID(medicalRecordID, medicalIDJson);
    $.ajax({
        url: "/getAllDepartmentName",
        type: "post",
        contentType: "application/json",
        dataType: "json",
        success: function (DepartmentName) {
            for (let i = 0; i < DepartmentName.length; i++) {
                $("select[name = 'department']").append("<option value = '" + DepartmentName[i].departmentName + "'>" + DepartmentName[i].departmentName + "</option>");
            }
        }
    })
    $.ajax({
        url: "/findAvailableInvoiceID",
        type: "post",
        success: function (MaxID) {
            $("input[name = 'invoiceID']").val(MaxID.invoiceID);
        }
    });
});


function getMedicalRecordID(medicalRecordID, medicalIDJson) {
    if (medicalRecordID.length == 0) {
        $.ajax({
            url: "/getRegistrationInfo",
            type: "post",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(medicalIDJson),
            success: function (RegistrationInfo) {
                $("input[name = 'medicalRecordID']").val(RegistrationInfo.medicalRecordID);
            }
        });
    }
}


//根据病历号更新病人信息
$(document).ready(function () {
    $("input[name = 'medicalRecordID']").change(function () {
        let medicalRecordID = $("input[name = 'medicalRecordID']").val();
        let sendJson = {};
        sendJson['medicalRecordID'] = medicalRecordID;

        if (medicalRecordID.length == 0) {
            getMedicalRecordID(medicalRecordID, sendJson);
        } else {
            $.ajax({
                url: "/getRegistrationInfo",
                type: "post",
                contentType: "application/json",
                dataType: "json",
                data: JSON.stringify(sendJson),
                success: function (RegistrationInfo) {
                    if (true) {
                        $("input[name = 'name']").val(RegistrationInfo.patientName);
                        $("select[name = 'gender']").val(RegistrationInfo.gender);
                        $("input[name = 'age']").val(RegistrationInfo.age);
                        $("input[name = 'birthday']").attr('value', RegistrationInfo.birthday);
                        $("input[name = 'numID']").val(RegistrationInfo.numID);
                        $("input[name = 'address']").val(RegistrationInfo.address);
                    }
                }
            });
        }
    });
});
//根据挂号级别更新挂号金额
$(document).ready(function () {
    //根据挂号级别更新挂号金额
    $("select[name = 'registerLevel']").change(function () {
        let registerLevel = $("select[name = 'registerLevel']").val();
        let sendJson = {};
        sendJson["registerLevel"] = registerLevel;
        $.ajax({
            url: "/getRegisterLevelMoney",
            type: "post",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(sendJson),
            success: function (result) {
                $("input[name='money']").val(result.invoiceID);
            }
        });
        refreshDoctor();
    });
});

//根据科室和挂号级别更新医生
function refreshDoctor() {
    let deptNameJson = {};
    deptNameJson["departmentName"] = $("select[name = 'department']").val();
    deptNameJson["doctorLevel"] = $("select[name = 'registerLevel']").val();
    $.ajax({
        url: "/getDoctorNameByDept",
        type: "post",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(deptNameJson),
        success: function (DoctorName) {
            $("select[name = 'doctorID']").empty();
            $("select[name='doctorID']").append("<option disabled selected hidden value=\"请选择\">请选择</option>");
            for (let i = 0; i < DoctorName.length; i++) {
                $("select[name = 'doctorID']").append("<option value = '" + DoctorName[i].doctorID + "'>" + DoctorName[i].doctorName + "</option>");
            }
        }
    });
}

$(document).ready(function () {
    $($("select[name = 'department']")).change(function () {
        refreshDoctor();
    });
});
$(document).ready(function () {
    $($("select[name = 'doctorID']")).change(function () {
        let docIDJson = {};
        docIDJson["doctorID"] = $("select[name = 'doctorID']").val();
        $.ajax({
            url: "/getRegistrationNum",
            type: "post",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(docIDJson),
            success: function (DoctorRegistrationNum) {
                $("input[name = 'registerNum']").val(DoctorRegistrationNum.doctorRegistrationNum);
                $("input[name = 'registerUsedNum']").val(DoctorRegistrationNum.doctorUsedRegistrationNum);
            }
        });
    });
});
