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
$(".phone").mask("+375(99)99-99-999");
function Go() {

    document.getElementById('block-1').style.display=(document.getElementById('1').checked)? 'block': 'none';
    document.getElementById('block-2').style.display=(document.getElementById('2').checked)? 'block': 'none';

}
var str = $("input:radio[class=test]").serialize();

