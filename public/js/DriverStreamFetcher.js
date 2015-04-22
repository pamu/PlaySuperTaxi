$(function(){
    //alert("DriverStreamFetcher.js")
    if (!!window.EventSource) {
        var driverStream = new EventSource("/dashboardDriverStream");
        driverStream.addEventListener('message', function(e) {
            var json = e.data;
            console.log(json);
        });
    } else {
        $("#drivers").html("Sorry, your browser does not support event source.");
    }
});