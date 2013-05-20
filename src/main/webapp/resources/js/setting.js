//load once document is ready
$(document).ready(function() {

	//Set Header tab
	$("#proyecto-header-li").removeClass("tab-active");
	$("#setting-header-li").addClass("tab-active");


	$('.autocomple-values').on('click', '.autocomplete-remove',function() {

		var id = this.id;
		
		var keys = id.split("--");
        
		findAndRemove(jsonCampos.list, "asset_name", keys[0],"type_name", keys[1]);

		$('#tr--' +id ).remove();
	});

	
	$(".autocomplete-add").click(function() {

		var id = this.id;
		
		var inputValue = $("#add-" + id).val();

		var table = $("#table-" + id);

		var campoId = id + "--" + trim(inputValue);
		
		if (inputValue == null || trim(inputValue) == "" || !isAllowedPname(inputValue)) {
					
			alert("Valor invalido!");
			
			return;


		}
		
		if ($('#'+campoId).length) {
			
			alert('El valor ya existe!');
			
			return;
		}
		

		var row = '<tr id="tr--' + campoId + '" bgcolor="#f2f2f2">' + '<td>' + inputValue + '</td>' + '<td><a> <img src="/vasa/resources/img/remove.png" alt="Delete" class="autocomplete-remove" id="' + campoId + '"></a></td></tr>';

		table.append(row);
		
		jsonCampos.list.push( { "asset_name":id, "type_name": inputValue} );
		
		$("#add-" + id).val("");
	});
	
	
	$('.cancel').click(function(event) {
	    event.preventDefault();
	    var r=confirm("Seguro que desea cancelar los cambios realizados");
	    if (r==true)   {  
	        location.reload();
	    }

	});

	$('#btn-salvar-campos').click(function(event) {
		saveCampos()
	});
	
	
$('.autocomple-values >  tbody tr').each(function() {
        
        var id = this.id;
        
        var keys = id.split("--");
        
        jsonCampos.list.push( { "asset_name":keys[1], "type_name": keys[2]} );


	});

});


function findAndRemove(array, property1, value1,property2, value2) {
   $.each(array, function(index, result) {
      if(this[property1] == value1 && this[property2] == value2) {
          //Remove from array
          array.splice(index, 1);
      }    
   });
}
