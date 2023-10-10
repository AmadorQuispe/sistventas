<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">

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
        <a class="navegacion__enlace navegacion__enlace--activo" href="<%=request.getContextPath()%>">Tienda</a>
        <a class="navegacion__enlace" href="about.jsp">Nosotros</a>
    </nav>
    <main class="contenedor">
        <form class="formulario__login" method="post" action="<%=request.getContextPath()+"/register"%>" >
            <h2>Registrarse</h2>
            <input type="text" placeholder="DOI" name="doi" class="formulario__campo" autocomplete="off" />

            <input type="text" placeholder="Nombres" name="first_name" class="formulario__campo" autocomplete="off" />

            <input type="text" placeholder="Apellidos" name="last_name" class="formulario__campo" autocomplete="off" />

            <input type="text" placeholder="Dirección" name="address" class="formulario__campo" autocomplete="off" />

            <input type="text" placeholder="Email" name="email" class="formulario__campo" autocomplete="off" />

            <input type="password" placeholder="Password" name="password" class="formulario__campo" />


            <button type="submit" class="formulario__submit ">
                Registrarse
            </button>


            <div>
                Ya estas registrado? 
                <a href="<%=request.getContextPath()+"/login"%>" class="link">Iniciar sesión</a>
            </div>
        </form>
    </main>
    <footer class="footer">
        <p class="footer__texto">Front End Store - Todos los derechos Reservados 2022.</p>
    </footer>
</body>

</html>