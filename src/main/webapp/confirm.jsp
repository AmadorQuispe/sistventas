<%@page contentType="text/html" pageEncoding="utf-8" %>
<%@page import="com.amsoft.models.Order"%>
<%@page import="com.amsoft.models.OrderDetail"%>
<%@page import="com.amsoft.models.UserDetail" %>

<% Order order = (Order) session.getAttribute("cart"); %>
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
        <h1 class="carrito__h1">PEDIDO CONFIRMADO</h1>
        <div>
            <span>Gracias por su compra</span>
            <a href="<%=request.getContextPath()+"/product"%>">Continuar comprando</a> .
        </div>
    </main>
    <footer class="footer">
        <p class="footer__texto">Front End Store - Todos los derechos Reservados 2022.</p>
    </footer>
    

</body>

</html>