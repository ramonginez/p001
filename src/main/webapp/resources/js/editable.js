$(document).ready(function()

{

$(".edit_td").click(function()

{

var ID=$(this).attr('id');

$("#first_"+ID).hide();


$("#first_input_"+ID).show();


}).change(function()

{

var ID=$(this).attr('id');

var first=$("#first_input_"+ID).val();


if(first.length>0)

{

    var oldValue = $("#first_"+ID).html();
	
	$("#first_"+ID).html('<img src="/inventario/static/img/load.gif" />');

	var answer = confirm("Esta seguro de modificar el nombre del proyecto?")

	if (answer){

		$.ajax({ 
			  url: "/inventario/rename/"+oldValue+"/"+first,
		    success: function(response)
		    {
		        var msg = $.parseJSON(response);

		        if(msg.msg != 'ok')
		        {
		        	alert(msg.msg);
		        	$("#first_"+ID).html(oldValue);
		    		$("#first_input_"+ID).val(oldValue);

		        }
		        else
		        {
		        	alert("Modificacion exitosa!");

		    		$("#first_"+ID).html(first);
		    	
		    		$("#link_"+ID).attr("href",_editLink+first);

		    		$("#remove_"+ID).attr("href",_removeLink+first);

		        }
		    }
		});
		
	}
	else{
		
		$("#first_"+ID).html(oldValue);
		$("#first_input_"+ID).val(oldValue);

	}



}

else

{

alert('Este campo no puede ser vacio');

}



});



$(".editbox").mouseup(function() 

{

return false

});



$(document).mouseup(function()

{

$(".editbox").hide();

$(".text").show();



});



});