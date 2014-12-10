var map;
var posicaoAtual;


function initialize() {
  var mapOptions = {
    zoom: 17
  };
  map = new google.maps.Map(document.getElementById('maps'),
      mapOptions);

    $.ajax({
      type : "GET",
      url : "/risk/getAll/",
      data : {}
    }).done(function(data) {
      processAjax(data, function() {
        
        var i = 0;
        for (i = 0; i< data.length; i++) {
          var pinColor = "2F76EE";
          if(data[i].riskType == "LOW"){
            pinColor = "2F76EE";
          }
          if(data[i].riskType == "MEDIUM"){
            pinColor = "2DDDDE";
          }
          if(data[i].riskType == "HIGH"){
            pinColor = "AAAA34";
          }if(data[i].riskType == "HIGHER"){
            pinColor = "CCCCCC";
          }
          
           // a random blue color that i picked
          var pinImage = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|" + pinColor,
                      new google.maps.Size(21, 34),
                      new google.maps.Point(0,0),
                      new google.maps.Point(10, 34));
        
          
          var marker = new google.maps.Marker({
            position: new google.maps.LatLng(data[i].latitude, data[i].longitude),
            map: map,
            title:"Hello World!",
            icon:pinImage
          });
        } 

        map.setCenter(new google.maps.LatLng(data[0].latitude, data[0].longitude));
        console.log(data)

      });

    });
}

geocoder = new google.maps.Geocoder();
function geocode(position){ 
  geocoder.geocode({'latLng': position}, function(results, status) {
      if (status == google.maps.GeocoderStatus.OK) {
        if (results[1]) {
          alert('marker dragged:' + results[1].formatted_address);
        }
        posicaoAtual = position;
        
      } else {
        alert('Geocoder failed due to: ' + status);
      }
    });
}



google.maps.event.addDomListener(window, 'load', initialize);