$(document).ready(function() {$(".pages_menu_btn").hover(function() {$(this).find(".pages_menu_div").show();},function() {$(this).find(".pages_menu_div").hide();});

 $("#show-hide-menu").click(function(e) {e.preventDefault(); $("#top-menu").toggleClass("resp");});
});
var map;

	function initializeMapID(ID, lat, lng, zom) {
		var latlng = new google.maps.LatLng(lat, lng);
		var settings = {
			zoom: zom,
			center: latlng,
			mapTypeControl: true,
			fullscreenControl: true,
			mapTypeControlOptions: {style: google.maps.MapTypeControlStyle.DROPDOWN_MENU},
			navigationControl: true,
			navigationControlOptions: {style: google.maps.NavigationControlStyle.SMALL},
			mapTypeId: google.maps.MapTypeId.ROADMAP
    	};

		map = new google.maps.Map(document.getElementById(ID), settings);
		var companyPos = new google.maps.LatLng(lat, lng);
		var companyMarker = new google.maps.Marker({
			  position: companyPos,
			  map: map
		  });
	}