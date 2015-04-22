$(function(){
    //alert("ClientRequestStreamFetcher.js");
    if (!!window.EventSource) {
        var clientRequestStream = new EventSource("/dashboardClientStream");
        clientRequestStream.addEventListener('message', function(e) {
            var json = e.data;
            //alert(json);
            var clientRequest = JSON.parse(json);
            var button = "btn" + clientRequest.id;

            $("#clientsTable > tbody:last").append('<tr>' + '<th>' + clientRequest.idno + '</th>' +
            '<th>' + clientRequest.source + '</th>' + '<th>' + clientRequest.destination + '</th>' + '<th>' +
            '<button class="btn" id=' + button + '>Serve</button>' + '</th>' + '</tr>');

            $("#"+button).click(function() {
                $("#myModal").modal('show');
            });

            console.log(json);
        });
    } else {
        $("#clients").html("Sorry, your browser does not support event source.")
    }
});