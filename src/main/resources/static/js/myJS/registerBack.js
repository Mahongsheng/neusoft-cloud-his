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
            alert(ReturnState.state + "  " + ReturnState.detail);
            $("#RegistrationRecordInfo tbody").empty();
            $.ajax({
                url: "/getRegistrationRecord",
                type: "post",
                contentType: "application/json",
                dataType: "json",
                data: JSON.stringify(medicalRecordJson),
                success: function (PatientRegistrationRecord) {
                    let medicalRecordID = $("input[name = 'medicalRecordID']").val();
                    let name = $("input[name = 'name']").val();
                    let numID = $("input[name = 'numID']").val();
                    let address = $("input[name = 'address']").val();

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
                }
            });
        }
    });
}

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
                    let address = $("input[name = 'address']").val();

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
                }
            });
        }
    });
});