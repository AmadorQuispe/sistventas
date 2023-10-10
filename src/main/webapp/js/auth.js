	$("#sesion").click(function(e){
		e.preventDefault();
		var url = "Cliente?action=validate";		
		var email = $(".frmClienteLog #txtEmailLog").val();
		var password = $(".frmClienteLog #idPassLog").val();		
		
		$.ajax({
			type:'POST',
			url: url,
			data: "email="+email+"&password="+password,
			success: function(data,textStatus,jqXHR){
				if(jqXHR.responseText =="true"){
					location.href="Compra?action=cart";
				}else{
					swal ( "Oops" ,  "Cuenta no registrada, registrate para disfrutar de las compras" ,  "error" );
				}				
				document.getElementById("frmClienteLog").reset();
			}
		})
	});