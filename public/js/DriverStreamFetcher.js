$(function(){
    //alert("DriverStreamFetcher.js");
    if (!!window.EventSource) {
        var driverStream = new EventSource("/dashboardDriverStream");
        //alert("hello");
        driverStream.addEventListener('message', function(e) {
            var json = e.data;
            //alert(json);
            var driver = JSON.parse(json);
            if ($("#driversTable #body").find('#' + driver.idno).length) {
                console.log("Driver with ID " + driver.idno + " already exists.");
            } else {
                $('#driversTable > tbody:last').append('<tr>' + '<th id=' + driver.idno + ' > ' + driver.idno + '</th>' + '<th>' + driver.rating + '</th>'
                            + '<th>' + driver.desc + '</th>' + '<th>' + '<button class="btn">Remove</button>' + '</th>' + '</tr>');
            }

            console.log(json);
        });
    } else {
        alert("not done");
        $("#drivers").html("Sorry, your browser does not support event source.");
    }
});