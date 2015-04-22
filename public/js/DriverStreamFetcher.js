$(function(){
    //alert("DriverStreamFetcher.js");
    if (!!window.EventSource) {
        var driverStream = new EventSource("/dashboardDriverStream");
        alert("hello");
        driverStream.addEventListener('message', function(e) {
            var json = e.data;
            //alert(json);
            console.log(json);
        });
    } else {
        alert("not done");
        $("#drivers").html("Sorry, your browser does not support event source.");
    }
});