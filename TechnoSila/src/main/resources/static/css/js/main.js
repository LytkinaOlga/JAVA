$(function(){

});
/*
menu.onclick = function myFunction(){
    var x = document.getElementById('myTopnav');

    if (x.className ==="nav__link"){
        x.className += " responsive";
    } else {
        x.className="nav__link";
    }
}*/
// Initialize and add the map
function initMap() {
    // The location of Uluru
    var uluru = {lat: 53.1350389, lng: 29.2258773};
    // The map, centered at Uluru
    var map = new google.maps.Map(
        document.getElementById('map'), {zoom: 16.53, center: uluru});
    // The marker, positioned at Uluru
    var marker = new google.maps.Marker({position: uluru, map: map});
}
