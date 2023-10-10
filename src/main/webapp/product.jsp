<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.amsoft.models.Product"%>
<%@page import="com.amsoft.models.UserDetail" %>

<%  Product product = (Product)request.getAttribute("product");%>
<% Integer cartQuantity = (Integer) session.getAttribute("cartQuantity");%>
<% UserDetail userDetail = (UserDetail) session.getAttribute("user");%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><%=product.getName()%></title>
    <link rel="stylesheet" href="css/normalize.css">
    <link href="https://fonts.googleapis.com/css2?family=Staatliches&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <header class="header">
        <a href="<%=request.getContextPath()%>">
            <img class="header__logo" src="img/logo.png" alt="Logotipo">
        </a>
    </header>

    <nav class="navegacion">        
        <div>
            <a class="navegacion__enlace navegacion__enlace--activo" href="<%=request.getContextPath()%>">Tienda</a>
            <a class="navegacion__enlace" href="about.jsp">Nosotros</a>  
        </div>      
        <div class="navegacion__usuario">
            <%if(userDetail==null){%>
                <a href="<%=request.getContextPath()+"/login"%>">Iniciar sesión</a>
            <%}else{%>
                <span><%=userDetail.getCompleteName()%></span>
                <a href="<%=request.getContextPath()+"/logout"%>">Cerrar sesión</a>
            <%}%>
        </div>        
    </nav>

    <main class="contenedor">
        <div class="carrito">
            <a class="carrito__button" href="<%=request.getContextPath()+"/cart"%>">
                <svg fill="none" stroke="currentColor" stroke-width="2" style="margin: auto"
                    viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg" class="h-6 w-6">
                    <path d="M16 11V7a4 4 0 00-8 0v4M5 9h14l1 12H4L5 9z" stroke-linecap="round"
                        stroke-linejoin="round"></path>
                </svg>
                <span>Cesta</span>
                <%if(cartQuantity!=null){%>
                <div class="carrito__cantidad">
                    <span><%=cartQuantity%></span>
                </div>
                <%}%>
            </a>
        </div>

        <div class="detalle">
            
            <img class="detalle__imagen" src="<%=product.getUrlImage()%>" alt="Imagen del Producto">

            <div class="detalle__contenido">
                <h2><%=product.getName()%></h2>
                <p><%=product.getDescription()%></p>
                <p>Precio $: <%=product.getPrice()%></p>
                <div class="feedback" id="feedback">
                    <p>Añadido al carrito 
                        <a href="<%=request.getContextPath()+"/cart"%>">Ver carrito</a> o 
                        <a href="<%=request.getContextPath()+"/product"%>">Continuar comprando</a> .
                    </p>
                </div>

                <form class="formulario" id="form_add">
                    <select class="formulario__campo" name="size">
                        <option disabled selected>-- Seleccionar Talla --</option>
                        <option value="S">Chica</option>
                        <option value="M">Mediana</option>
                        <option value="X">Grande</option>
                    </select>
                    <input type='hidden' name='slug' value='<%=product.getSlug()%>'/>
                    <input class="formulario__campo" name="quantity" type="number" value="1" placeholder="Cantidad" min="1">
                    
                    <input class="formulario__submit" type="submit" value="Agregar al Carrito">
                </form>
                
            </div>
        </div>
    </main>

    <footer class="footer">
        <p class="footer__texto">Front End Store - Todos los derechos Reservados 2022.</p>
    </footer>
    <script>
        const form = document.getElementById("form_add");
        form.addEventListener("submit",(e)=>{
            e.preventDefault();
            let data = new FormData(form);
            if(data.get("size")==null || data.get("size")==undefined ){
                window.alert("Seleccione talla")
                return;
            }
            if(data.get("quantity")<=0){
                window.alert("Ingrese cantidad correcta > 1")
                return;
            }
            fetch("/sistventas/cart",{
                method:'POST',
                body:data
            })
            .then((res) => res.text())
            .catch((error) => console.error("Error:", error))
            .then((res) => {
                const feedback = document.getElementById("feedback");
                feedback.classList.toggle("show");
                setTimeout(()=>{
                    feedback.classList.toggle("show");
                    window.location.reload()
                },3000)
            })
            .finally(()=>{
                form.reset();
            })
        })
    </script>
    
</body>
</html>