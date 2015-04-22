$(function() {
    $("#done").click(function() {
        var driverID = $("#driverID").val();
        if (driverID.length) {
           $("#myModal").modal('hide');
           $("#driverID").empty();
        } else {
            $("#msgCenter").html('<span class="alert alert-error"> Enter Valid Driver ID</span>');
        }
    });
});