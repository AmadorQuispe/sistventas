<%@page contentType="text/html" pageEncoding="UTF-8" %>
<% String messageError = (String) request.getAttribute("message");%>
<% String destination = (String) request.getAttribute("destination");%>
<%
    String actionForm = request.getContextPath()+"/login";
    if(destination!=null){
        actionForm = request.getContextPath()+"/login?destination="+destination;
    }

%>
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
        <div>
            

            <form 
                class="formulario__login" 
                method="post" 
                action="<%=actionForm%>"
            >
                <h2>Inicia sesión </h2>
                <input type="text" placeholder="Email" name="email"  class="formulario__campo" autocomplete="off" />

                <input type="password" placeholder="Password" name="password" class="formulario__campo" />


                <button type="submit" class="formulario__submit ">
                    Login
                </button>


                <div>
                    Aun no estas registrado? 
                    <a href="<%=request.getContextPath()+"/register"%>" class="link">Registrarse</a>
                </div>

                <%if(messageError!=null){%>
                    <div><%=messageError%></div>
                <%}%>

            </form>

        </div>
    </main>
    <footer class="footer">
        <p class="footer__texto">Front End Store - Todos los derechos Reservados 2022.</p>
    </footer>
    <script>
        /*const formLogin = document.getElementById("form__login");
        formLogin.addEventListener("submit",(e)=>{
            e.preventDefault();
            const url = location.href;
            const data = new FormData(formLogin);
            fetch(url,{
                method: 'POST',
                body : data,
            })
            .then((res)=>res.text())
            .catch(err=>console.log(err))
            .then(res=>{
                location.reload();
            })
        })*/
    </script>
</body>

</html>