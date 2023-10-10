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
        <h1 class="carrito__h1">CARRITO</h1>
        <div class="carrito__contenedor ">

            <div>
                <%if(order == null || order.getOrderDetails().isEmpty()){%>
                    <div>
                        <span>Su carrito está vacío.</span>
                        <a href="<%=request.getContextPath()+"/product"%>">Continuar comprando</a> .
                    </div>
                    
                <%} else {%>
                    <%for(OrderDetail item : order.getOrderDetails()){%>
                    <article class="producto__elegido">
                        <div class="producto__elegido__img">
                            <img src="<%=item.getProduct().getUrlImage()%>" alt="">
                        </div>
                        <div class="producto__elegido__detalle">
                            <div class="linea__flex__sup">
                                <div class="linea__flex__detalle">
                                    <h4><%=item.getProduct().getName()%></h4>
                                    <p id="size"><%=item.getSize()%></p>
                                </div>
                                <div class="linea__flex__precio">
                                    $<%=item.getTotal()%>
                                </div>
                            </div>
                            <div class="linea__flex__inf">
                                <div>
                                    <label>$<%=item.getProduct().getPrice()%> x <%=item.getQuantity()%></label>
                                    <button data-minus='<%=item.getProduct().getSlug() + "|"+item.getSize()%>'>-</button>
                                    <button data-plus='<%=item.getProduct().getSlug() + "|"+item.getSize()%>'>+</button>
                                </div>
                                <button 
                                    data-remove='<%=item.getProduct().getSlug() + "|"+item.getSize()%>' 
                                    class="linea__flex__inf__btn"
                                >
                                    Eliminar
                                </button>
                            </div>
                        </div>
                    </article>
                    <%}%>
                <%}%>
            </div>
            <div>
                <%if(order!=null){%>
                <div class="carrito__resumen">
                    <h5>Resumen del pedido</h5>
                    <div class="carrito__conceptos">
                        <div class="carrito__concepto">
                            <p>Subtotal</p>
                            <p>$ <%=order.getTotal()%></p>
                        </div>
                        <div class="carrito__concepto">
                            <p>Envio</p>
                            <p>$ 0.00</p>
                        </div>
                        <div class="carrito__concepto">
                            <p>Total</p>
                            <p>$ <%=order.getTotal()%></p>
                        </div>                        
                    </div>
                    <a class="carrito__btn__pagar" href="<%=request.getContextPath()+"/checkout"%>">
                        Finalizar compra
                    </a>
                </div>
                <%}%>
            </div>
        </div>
    </main>
    <footer class="footer">
        <p class="footer__texto">Front End Store - Todos los derechos Reservados 2022.</p>
    </footer>
    <script>
        //const btnPlus = document.getElementById("btn-plus");
        const btnMinus =  document.querySelectorAll("[data-minus]");
        const btnRemoves = document.querySelectorAll("[data-remove]");
        const btnPlus = document.querySelectorAll('[data-plus]');

        btnPlus.forEach(m=>{
            m.addEventListener("click",()=>{
                const size = m.dataset.plus.split("|")[1];
                const slug  = m.dataset.plus.split("|")[0];
                let data = new FormData();
                data.append("slug",slug);
                data.append("size",size);
                data.append("quantity",1)
                fetch("./cart/manage?action=update",{
                    method: 'POST',
                    body : data,
                })
                .then((res)=>res.text())
                .catch(err=>console.log(err))
                .then(res=>{
                    location.reload()
                })
            })
        });

         btnMinus.forEach(m=>{
            m.addEventListener("click",()=>{
                const size = m.dataset.minus.split("|")[1];
                const slug  = m.dataset.minus.split("|")[0];
                let data = new FormData();
                data.append("slug",slug);
                data.append("size",size);
                data.append("quantity",-1)
                fetch("./cart/manage?action=update",{
                    method: 'POST',
                    body : data,
                })
                .then((res)=>res.text())
                .catch(err=>console.log(err))
                .then(res=>{
                    location.reload();
                })
            })
        });

         btnRemoves.forEach(m=>{
            m.addEventListener("click",()=>{
                const size = m.dataset.remove.split("|")[1];
                const slug  = m.dataset.remove.split("|")[0];
                let data = new FormData();
                data.append("slug",slug);
                data.append("size",size);
                fetch("./cart/manage?action=remove",{
                    method: 'POST',
                    body : data,
                })
                .then((res)=>res.text())
                .catch(err=>console.log(err))
                .then(res=>{
                    location.reload()
                })
            })
        })



    </script>

</body>

</html>