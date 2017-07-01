function mostrarMensagem(mensagem){
	if(mensagem != null && mensagem != ''){
		var dialog = "<div id='modal_dialog_v01' title='Mensagem' style='display:none'>"+
					 "	<p>"+mensagem+"</p>"+
					 "</div>";
		$("body").append(dialog);
			
		$("#modal_dialog_v01").dialog({
			modal: true,
			buttons: {
				Ok: function() {
			     $( this ).dialog("close");
			     $("#modal_dialog_v01").remove();
			   }
			},
			close: function(){
				$("#modal_dialog_v01").remove();
			}
		});
	}
	
}