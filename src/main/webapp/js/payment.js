$(document).ready(function(){


	/*$(".cantidad").click(function(){
		var idp = $(this).parent().find(".idPro").val();
		var cant = $(this).parent().find(".cantidad").val();
		var url = "ProductoController?action=refresCantidad";
		$.ajax({
			type:'POST',
			url:url,
			data: "idp="+idp+"&cant="+cant,
			success: function(data,textStatus,jqXHR){
				location.href="ProductoController?action=car";
			}
		})
	});*/
	
	$("#btnFinalizar").click(function(e){
		e.preventDefault();
		var url = "Pago?action=pay";		
		var pagtot = $(".resumen #txtPagTot").val();
		var metodo = "Tarjeta";		
		
		$.ajax({
			type:'POST',
			url: url,
			data: "monto="+pagtot+"&metodo="+metodo,
			success: function(data,textStatus,jqXHR){
				//alert("RPta "+jqXHR.responseText +" " + pagtot)
				var idpag = jqXHR.responseText
				if(idpag>0){
					location.href="Compra?action=registrar&idPago="+idpag;
				}
				
				
					//$("#idPago").val(jqXHR.responseText)
				
				/*if(jqXHR.responseText =="true"){
					location.href="home";
				}else{
					swal ( "Oops" ,  "Cuenta no registrada, registrate para disfrutar de las compras" ,  "error" );
				}				
				document.getElementById("formIniciar").reset();*/
			}
		})
	});
	
	function registrarCompra(idPag){
		var url = "Compra?action=registrar";
		$.ajax({
			type:'POST',
			url: url,			  
			data: "idPago="+idPag,
			success: function(data,textStatus,jqXHR){
				  alert("Compra registrada")
				  //location.href="Compra?action=registrar?idPago="+idPag;
			}
		});
	}
	

	
});