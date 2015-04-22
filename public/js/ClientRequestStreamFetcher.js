$(function(){
    //alert("ClientRequestStreamFetcher.js");
    if (!!window.EventSource) {
        var clientRequestStream = new EventSource("/dashboardClientStream");
        clientRequestStream.addEventListener('message', function(e) {
            var json = e.data;
            //alert(json);
            var clientRequest = JSON.parse(json);
            $("#clientsTable > tbody:last").append('<tr>' + '<th>' + clientRequest.idno + '</th>' +
            '<th>' + clientRequest.source + '</th>' + '<th>' + clientRequest.destination + '</th>' + '<th>' +
            clientRequest.id + '</th>' + '</tr>');
            console.log(json);
        });
    } else {
        $("#clients").html("Sorry, your browser does not support event source.")
    }
});