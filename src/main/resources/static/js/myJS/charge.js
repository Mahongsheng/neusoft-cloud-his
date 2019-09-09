$(document).ready(function () {
    $("#searchPatient").click(function () {
        let medicalRecordID = $("input[name = 'medicalRecordID']").val();
        if (medicalRecordID.length != 0) {
            let sendJson = {};
            sendJson['medicalRecordID'] = medicalRecordID;
            $.ajax({
                url: "/getRegistrationInfo",
                type: "post",
                contentType: "application/json",
                dataType: "json",
                data: JSON.stringify(sendJson),
                success: function (RegistrationInfo) {
                    $("input[name = 'medicalRecordID']").val(RegistrationInfo.medicalRecordID);
                    if (true) {
                        $("input[name = 'name']").val(RegistrationInfo.patientName);
                        $("input[name = 'numID']").val(RegistrationInfo.numID);
                        $("input[name = 'address']").val(RegistrationInfo.address);
                    }
                }
            });
            $("#chargeInfoTable tbody").empty();
            $.ajax({
                url: "/getChargeInfo",
                type: "post",
                contentType: "application/json",
                dataType: "json",
                data: JSON.stringify(sendJson),
                success: function (ChargeInfo) {
                    let medicalRecordID = $("input[name = 'medicalRecordID']").val();
                    let name = $("input[name = 'name']").val();
                    for (let i = 0; i < ChargeInfo.length; i++) {
                        $("#chargeInfoTable tbody").append(
                            '<tr>' +
                            '<td><input type="checkbox" class="i-checks" name="drugPreDetail" id="' + ChargeInfo[i].drugPreDetailID + '"></td>' +
                            '<td>' + medicalRecordID + '</td>' +
                            '<td>' + name + '</td>' +
                            '<td>' + ChargeInfo[i].drugName + '</td>' +
                            '<td>' + ChargeInfo[i].drugUnitPrice + '</td>' +
                            '<td>' + ChargeInfo[i].amount + '</td>' +
                            '<td>' + ChargeInfo[i].createTime + '</td>' +
                            '<td>' + ChargeInfo[i].state + '</td>' +
                            '</tr>');
                    }
                    $("input[type = 'checkbox']").attr('checked', false);
                }
            })
        }
    });
});

$(document).ready(function () {
    $("#openInvoiceModal").click(function () {
        let shouldPayMoney;
        let medicalRecordID = $("input[name = 'medicalRecordID']").val();
        let name = $("input[name = 'name']").val();
        $("input[name = 'medicalRecordIDModal']").val(medicalRecordID);
        $("input[name = 'patientNameModal']").val(name);

        let drugPreDetailIDs = [];
        $("input:checkbox[name=drugPreDetail]:checked").each(function () {
            let drugPreDetailID = $(this).attr("id");
            drugPreDetailIDs.push(drugPreDetailID);
        });

        let sendJson = {};
        sendJson["drugPreDetailIDs"] = drugPreDetailIDs;
        $.ajax({
            url: "/getDrugPreDetailInfo",
            type: "post",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(sendJson),
            success: function (ShouldPayMoney) {
                $("input[name = 'shouldPayMoneyModal']").val(ShouldPayMoney.wholeMoney);
            }
        });

        $.ajax({
            url: "/findAvailableInvoiceID",
            type: "post",
            success: function (MaxID) {
                $("input[name = 'invoiceIDModal']").val(MaxID.invoiceID);
            }
        });
    });
});

$(document).ready(function () {
    $("input[name='paidMoneyModal']").change(function () {
        let exchange = $("input[name='paidMoneyModal']").val() - $("input[name = 'shouldPayMoneyModal']").val();
        $("input[name='exchangeModal']").val(exchange);
    });
});

$(document).ready(function () {
    $('input[name="selectAll"]').change(function () {
        if ($(this).is(":checked")){
            $(this).attr('checked',true);
            $("input[name='drugPreDetail']").attr({'checked': true});
        }else {
            $(this).attr('checked',false);
            $("input[name='drugPreDetail").attr({'checked': false});
        }
    });
});

