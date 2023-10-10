$(document).ready(function(){
	$("#enviar").click(function(e){
		e.preventDefault();
		var url = "Cliente?action=register";
		var dni = $(".formCliente #txtDni").val();
		var nombre = $(".formCliente #txtNombres").val();
		var apellidos = $(".formCliente #txtApellidos").val();
		var direccion = $(".formCliente #txtDireccion").val();
		var email = $(".formCliente #txtEmail").val();
		var password = $(".formCliente #txtPassword").val();		
		
		$.ajax({
			type:'POST',
			url: url,
			data: "nombre="+nombre+"&apellidos="+apellidos+"&dni="+dni+"&direccion="+direccion+"&email="+email+"&password="+password,
			success: function(data,textStatus,jqXHR){
				swal("Cuenta registrada", jqXHR.responseText);
				document.getElementById("formCliente").reset();
			}
		})
	});
		
});