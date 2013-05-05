//load once document is ready
$(document).ready(function() {

	//Set Header tab
	$("#proyecto-header-li" ).removeClass("tab-active");
	$("#checkin-header-li").addClass("tab-active");

	//var checkins = jQuery.parseJSON(strJSPJson);
	
	createMap(checkins);
	
});


function createMap(checkins) {
	
	
	var myLatlng = new google.maps.LatLng(10.5, -66.9166667);
    
    var myOptions = {
        zoom: 12,
        center: myLatlng,
        mapTypeId: google.maps.MapTypeId.ROADMAP,
    }
    
    var map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);
    
   
 	var count = 1;
    
    $.each(checkins, function(i, item) {
                       
		var icoUrl = "http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld="+count+"|00CC99|000000";    
        
    	var listValue  =  count +"- "+this.user+","+this.time;
    	
        var markLatlng = new google.maps.LatLng(this.lat, this.lon);

    	count++;
    	
    	$("#checkin_list").append('<li>'+listValue+'</li>');

    	var marker = new google.maps.Marker({
        	position: markLatlng,
            draggable: true,
            map: map,
            icon: new google.maps.MarkerImage(icoUrl,
                      null, null, new google.maps.Point(0, 42))
        });
        
        marker.setMap(map); 

		    	

                   
    });
    
     $(function() {
    $( "#datepicker" ).datepicker();
  });


    
 }
