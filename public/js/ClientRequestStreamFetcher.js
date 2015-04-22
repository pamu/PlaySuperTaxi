$(function(){
    //alert("ClientRequestStreamFetcher.js");
    if (!!window.EventSource) {
        var clientRequestStream = new EventStream("/dashboardClientStream");
        clientRequestStream.addEventListener('message', function(e) {
            var json = e.data;
            console.log(json);
        });
    } else {
        $("#clients").html("Sorry, your browser does not support event source.")
    }
});