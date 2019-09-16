// 全选框的实现
$(document).ready(function () {
    $('input[name="selectAll"]').change(function () {
        if ($(this).is(":checked")) {
            $(this).attr('checked', true);
            $("input[name='drugPreDetailID']").attr({'checked': true});
        } else {
            $(this).attr('checked', false);
            $("input[name='drugPreDetailID").attr({'checked': false});
        }
    });
});
//搜索应当进行发药的处方明细信息
$(document).ready(function () {
    $("#searchPrescribeInfo").click(function () {
        let medicalRecordID = $("input[name = 'medicalRecordID']").val();
        let drugPreTime = $("input[name = 'drugPreTime']").val();

        if (medicalRecordID.length != 0) {
            let sendJson = {};
            sendJson['medicalRecordID'] = medicalRecordID;
            sendJson['drugPreTime'] = drugPreTime;
            $.ajax({
                url: "/getPrescribeInfo",
                type: "post",
                contentType: "application/json",
                dataType: "json",
                data: JSON.stringify(sendJson),
                success: function (PrescribeInfo) {
                    $("#prescribeInfoTable tbody").empty();
                    for (let i = 0; i < PrescribeInfo.length; i++) {
                        $("#prescribeInfoTable tbody").append(
                            '<tr>' +
                            '<td><input type="checkbox" class="i-checks" name="drugPreDetailID" value="' + PrescribeInfo[i].drugPreDetailID + '"></td>' +
                            '<td>' + PrescribeInfo[i].drugName + '</td>' +
                            '<td>' + PrescribeInfo[i].drugUnitPrice + '</td>' +
                            '<td>' + PrescribeInfo[i].amount + '</td>' +
                            '<td>' + PrescribeInfo[i].doctorName + '</td>' +
                            '<td>' + PrescribeInfo[i].preName + '</td>' +
                            '<td>' + PrescribeInfo[i].createTime + '</td>' +
                            '</tr>');
                    }
                }
            });
        }
    });
});
//发药操作
$(document).ready(function () {
    $("#prescribeNow").click(function () {
        let sendJson = {};
        let preDetailIDs = [];
        $("input:checkbox[name=drugPreDetailID]:checked").each(function () {
            preDetailIDs.push($(this).val());
        });
        sendJson["drugPreIDs"] = preDetailIDs;
        $.ajax({
            url: "/prescribe",
            type: "post",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(sendJson),
            success: function (returnJson) {
                $('.alert').html('开药成功').addClass('alert-success').show().delay(1400).fadeOut();
                let medicalRecordID = $("input[name = 'medicalRecordID']").val();
                let drugPreTime = $("input[name = 'drugPreTime']").val();

                if (medicalRecordID.length != 0) {
                    let sendJson = {};
                    sendJson['medicalRecordID'] = medicalRecordID;
                    sendJson['drugPreTime'] = drugPreTime;
                    $.ajax({
                        url: "/getPrescribeInfo",
                        type: "post",
                        contentType: "application/json",
                        dataType: "json",
                        data: JSON.stringify(sendJson),
                        success: function (PrescribeInfo) {
                            $("#prescribeInfoTable tbody").empty();
                            for (let i = 0; i < PrescribeInfo.length; i++) {
                                $("#prescribeInfoTable tbody").append(
                                    '<tr>' +
                                    '<td><input type="checkbox" class="i-checks" name="drugPreDetailID" value="' + PrescribeInfo[i].drugPreDetailID + '"></td>' +
                                    '<td>' + PrescribeInfo[i].drugName + '</td>' +
                                    '<td>' + PrescribeInfo[i].drugUnitPrice + '</td>' +
                                    '<td>' + PrescribeInfo[i].amount + '</td>' +
                                    '<td>' + PrescribeInfo[i].doctorName + '</td>' +
                                    '<td>' + PrescribeInfo[i].preName + '</td>' +
                                    '<td>' + PrescribeInfo[i].createTime + '</td>' +
                                    '</tr>');
                            }
                        }
                    });
                }
            }
        });
    });
});