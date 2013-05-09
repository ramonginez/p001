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
  
});