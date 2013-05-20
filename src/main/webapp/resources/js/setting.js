//load once document is ready
$(document).ready(function() {

	//Set Header tab
	$("#proyecto-header-li").removeClass("tab-active");
	$("#setting-header-li").addClass("tab-active");


	$('.autocomple-values').on('click', '.autocomplete-remove',function() {

   		var asset = $(this).attr("asset");
                
        var assetType = $(this).attr("atype");
        
		findAndRemove(jsonCampos.list, "asset_id", asset,"type_name", assetType);

		$('#tr--' +this.id).remove();
	});

	
	$(".autocomplete-add").click(function() {

		var id = this.id;
		
		var inputValue = $("#add-" + id).val();

		var table = $("#table-" + id);

		var campoId = id + "--" + inputValue.replace(/\s/g, "");
		
		if (inputValue == null || $.trim(inputValue) == "" ) {
					
			alert("Valor invalido!");
			
			return;


		}
		
		if ($('#'+campoId).length) {
			
			alert('El valor ya existe!');
			
			return;
		}
		

		var row = '<tr id="tr--' + campoId + '" asset="'+id+'" atype="'+inputValue+'" bgcolor="#f2f2f2">' 
		+ '<td>'+ inputValue + '</td>'
		+ '<td><a> <img src="/vasa/resources/img/remove.png" alt="Delete" class="autocomplete-remove" asset="'+id+'" atype="'+inputValue+'"  id="' + campoId + '"></a></td></tr>';

		table.append(row);
		
		jsonCampos.list.push( { "asset_id":id, "type_name": inputValue} );
		
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
        
        var asset = $(this).attr("asset");
                
        var assetType = $(this).attr("atype");

       
        jsonCampos.list.push( { "asset_id":asset, "type_name": assetType} );

 
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
