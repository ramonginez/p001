//load once document is ready
$(document).ready(function() {

  $('a.confirm').click(function(event) {

    event.preventDefault()

    var url = $(this).attr('href');

    var confirm_box = confirm('Confirmar eliminar?');

    if (confirm_box) {
       window.location = url;
     
    }
  });
  
  //Agregar proyecto
  
  $('a.create').click(function(event) {

	    event.preventDefault()

	    var name = $("#create_proyect").val();
	    
	    if(name.length>0){
	    	
	    	var url = _createLink+ name;

	    	window.location = url;

	    }else{
	    	
	    	alert('Este campo no puede ser vacio');

	    }
	    	
	    
	  });
});