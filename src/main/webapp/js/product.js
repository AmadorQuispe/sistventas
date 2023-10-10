$(document).ready(function(){
	$("#btnNew").click(function(e){
		
		e.preventDefault();	
		
		 $('#modalProduct').on('show.bs.modal', function (event) {
		      var button = $(event.relatedTarget)		      
		      var modal = $(this)			
			  modal.find('.modal-title').text('Nuevo producto')
			  modal.find('.modal-body #txtIdProduct').val("")
		 	  modal.find('.modal-body #txtDescripcion').val("")
		 	  modal.find('.modal-body #txtNombre').val("")
		 	  modal.find('.modal-body #txtUrlimage').val("huawei.webp")
		 	  modal.find('.modal-body #txtPrecio').val("")
		 	  modal.find('.modal-body #txtStock').val("")
		 	  modal.find('.modal-body #txtAccion').val("nuevo")
		 });
		 $('#modalProduct').modal('toggle');
	});
	
	
	
	$("#btnSave").click(function(e){
		e.preventDefault();
		//alert("save");
		//var url = "Producto?action=addProduct";	
		var id = $(".frmProduct #txtIdProduct").val();
		var nom = $(".frmProduct #txtNombre").val();
		var desc = $(".frmProduct #txtDescripcion").val();	
		var prec = $(".frmProduct #txtPrecio").val();	
		var stoc = $(".frmProduct #txtStock").val();	
		var urlimg = $(".frmProduct #txtUrlimage").val();
		var accion = $('.frmProduct #txtAccion').val()
		if(accion=="nuevo"){
			var url = "Producto?action=addProduct";	
			var dat = "nombre="+nom+"&descripcion="+desc+"&precio="+prec+"&urlImagen="+urlimg+"&stock="+stoc;
		}else if(accion=="editar"){
			var url = "Producto?action=editar";	
			var dat = "idp="+ id+"&nombre="+nom+"&descripcion="+desc+"&precio="+prec+"&urlImagen="+urlimg+"&stock="+stoc;
		}
		
		$.ajax({
			type:'POST',
			url: url,
			data: dat,
			success: function(data,textStatus,jqXHR){
				if(jqXHR.responseText=="true"){
					swal ( "OK" ,  "Operacion procesado correctamente" ,  "success" );
					location.href="Producto?action=mostrar";
					//alert(jqXHR.responseText)
				}else{
					swal ( "Oops" ,  "Ocurrio un error al procesar la solicitud" ,  "error" );
				}
			}
		})
	});
	
	$("#btnCancelar").click(function(e){
		e.preventDefault();
		console.log(e.target.id);

	});
	
	$(".btnDelete").click(function(e){
		e.preventDefault()
		var id = $(this).parent().parent().find(".idp").text();
				
		swal({
			title:"Estas seguro de eliminar?",
			text:"Se eliminara de la base de datos!",
			icon:"warning",
			buttons: true,
			dangerMode: true,
		  })
		  .then((willDelete) => {
			if (willDelete) {
			  eliminar(id);
			  swal("Producto eliminado correctamente", {
				icon: "success",
			  }).then((willDelete)=> {
				  if(willDelete){
					  parent.location.href="Producto?action=mostrar"
				  }
			  });
			} else {
			  swal("Your imaginary file is safe!");
			}
		  });
		
	});
	function eliminar(id){
		var url = "Producto?action=eliminar";
		$.ajax({
			type:'POST',
			url: url,			  
			data: "idp="+id,
			success: function(data,textStatus,jqXHR){
				  
		}
		});
	};
	

	
	$(".btnEdit").click(function(e){
		
		e.preventDefault();
		var id = $(this).parent().parent().find(".idp").text();
		var urlimg = $(this).parent().parent().find(".urlimg").text();
		var nom = $(this).parent().parent().find(".nom").text();
		var des = $(this).parent().parent().find(".des").text();
		var precio = $(this).parent().parent().find(".precio").text();
		var stock = $(this).parent().parent().find(".stock").text();
		
		 $('#modalProduct').on('show.bs.modal', function (event) {
		      var button = $(event.relatedTarget)
		      
		      var modal = $(this)
			
			  modal.find('.modal-title').text('Editar producto: ' + nom)
			  modal.find('.modal-body #txtIdProduct').val(id)
		 	  modal.find('.modal-body #txtDescripcion').val(des)
		 	  modal.find('.modal-body #txtNombre').val(nom)
		 	  modal.find('.modal-body #txtUrlimage').val(urlimg)
		 	  modal.find('.modal-body #txtPrecio').val(precio)
		 	  modal.find('.modal-body #txtStock').val(stock)
		 	  modal.find('.modal-body #txtAccion').val("editar")
		 });
		 $('#modalProduct').modal('toggle');
	});
	
});