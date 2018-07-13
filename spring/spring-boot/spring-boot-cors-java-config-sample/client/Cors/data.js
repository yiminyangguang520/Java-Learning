$(document).ready(function() {
	$.ajax({
        <!--8081为后端端口号-->
		url: "http://localhost:8081/customers"
	}).then(function(data) {
	  var items = [];
	  $.each( data, function( key, val ) {
		items.push("Id: "+val.id +", Name: "+val.name+"<br/>");
	  });
          $('.result').append(items);
	});
}); 