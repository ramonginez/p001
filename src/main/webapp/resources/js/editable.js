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

    var oldValue = $("#first_"+ID).html();

		renameProyecto(oldValue,first);



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