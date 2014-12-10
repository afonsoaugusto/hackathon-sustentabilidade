

var map;
var posicaoAtual;

function initialize() {
  var mapOptions = {
    zoom: 17
  };
  map = new google.maps.Map(document.getElementById('maps'),
      mapOptions);

  // Try HTML5 geolocation
  if(navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(function(position) {
      var pos = new google.maps.LatLng(position.coords.latitude,
                                       position.coords.longitude);
      
      var marker = new google.maps.Marker({
      position: pos,
      map: map,
      draggable: true,
      title: 'Hello World!'
  });
      google.maps.event.addListener(marker, 'dragend', function() { geocode(marker.position)} );
      posicaoAtual = pos;
      map.setCenter(pos);
    }, function() {
      handleNoGeolocation(true);
    });
  } else {
    // Browser doesn't support Geolocation
    handleNoGeolocation(false);
  }
}

function handleNoGeolocation(errorFlag) {
  if (errorFlag) {
    var content = 'Error: The Geolocation service failed.';
  } else {
    var content = 'Error: Your browser doesn\'t support geolocation.';
  }

  var options = {
    map: map,
    position: new google.maps.LatLng(60, 105),
    content: content
  };

  var infowindow = new google.maps.InfoWindow(options);
  map.setCenter(options.position);
  map.setZoom(17);
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