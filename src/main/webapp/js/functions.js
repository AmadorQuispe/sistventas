$(document).ready(function(){
	$(".btnDelete").click(function(e){
		e.preventDefault()
		var id = $(this).parent().find(".idp").val();
		
		swal({
			title:"Estas seguro de eliminar?",
			text:"Se eliminara del carrito de compras!",
			icon:"warning",
			buttons: true,
			dangerMode: true,
		  })
		  .then((willDelete) => {
			if (willDelete) {
			  eliminar(id);
			  swal("Producto eliminado correctamente del carrito", {
				icon: "success",
			  }).then((willDelete)=> {
				  if(willDelete){
					  parent.location.href="Compra?action=cart"
				  }
			  });
			} else {
			  swal("Your imaginary file is safe!");
			}
		  });
		
	});
	function eliminar(id){
		var url = "Compra?action=delete";
		$.ajax({
			type:'POST',
			url: url,			  
			data: "idp="+id,
			success: function(data,textStatus,jqXHR){
				  
		}
		});
	};

	$(".cantidad").click(function(){
		var idp = $(this).parent().find(".idPro").val();
		var cant = $(this).parent().find(".cantidad").val();
		var url = "Compra?action=updateCantidad";
		$.ajax({
			type:'POST',
			url:url,
			data: "idp="+idp+"&cantidad="+cant,
			success: function(data,textStatus,jqXHR){
				location.href="Compra?action=cart";
			}
		})
	});
	
	$("#enviarSes").click(function(e){
		e.preventDefault();
		var url = "Cliente?action=validate";		
		var email = $(".formIniciar #txtEmailSes").val();
		var password = $(".formIniciar #txtPassSes").val();		
		
		$.ajax({
			type:'POST',
			url: url,
			data: "email="+email+"&password="+password,
			success: function(data,textStatus,jqXHR){
				if(jqXHR.responseText =="true"){
					location.href="home";
				}else{
					swal ( "Oops" ,  "Cuenta no registrada, registrate para disfrutar de las compras" ,  "error" );
				}				
				document.getElementById("formIniciar").reset();
			}
		})
	});
	
	/*$(".btn-detail").click(function(){
		var idp = $(this).parent().find(".codPro").val();
		var precio = $(this).parent().find(".precio").text();
		var descrip = $(this).parent().find(".descrip").text();
		var nombre = $(this).parent().find(".nombre").text();
		var urli = $(this).parent().parent().find(".urlimage").attr('src');
		
		alert(" "+idp+"/ "+precio+"/ "+descrip+"/ "+urli+"/ "+nombre)
		console.log(" "+idp+"/ "+precio+"/ "+descrip+"/ "+urli+"/ "+nombre);
		
		var url = "ProductoController?action=detail";
		$.ajax({
			type:'POST',
			url:url,
			data: "idp="+idp+"&precio="+precio+"&descrip="+descrip+"&nombre="+nombre+"&url="+url,
			success: function(data,textStatus,jqXHR){
				location.href="ProductoController?action=detail";				
			}
		})
	})*/
	
});