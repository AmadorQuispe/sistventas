<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.List" %>
<%@page import="com.amsoft.models.Product" %>
<%@page import="com.amsoft.models.UserDetail" %>
<% List<Product> products = (List<Product>)request.getAttribute("products");%>
<% Integer cartQuantity = (Integer) session.getAttribute("cartQuantity");%>
<% UserDetail userDetail = (UserDetail) session.getAttribute("user");%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FrontEnd Store</title>
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
        <h1>Nuestros Productos</h1>
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

        <div class="grid">
            <%for(Product product : products){%>
            <div class="producto">
                <a href="<%=request.getContextPath()+"/product?slug="+product.getSlug()%>">
                    <img class="producto__imagen" src="<%=product.getUrlImage()%>"
                        alt="<%=product.getName()%>">
                    <div class="producto__informacion">
                        <p class="producto__nombre">
                            <%=product.getName()%>
                        </p>
                        <p class="producto__precio">$<%=product.getPrice()%>
                        </p>
                    </div>
                </a>
            </div>
            <%}%>
            <div class="grafico grafico--camisas"></div>
            <div class="grafico grafico--node"></div>
        </div>
    </main>

    <footer class="footer">
        <p class="footer__texto">Front End Store - Todos los derechos Reservados 2022.</p>
    </footer>

</body>

</html>