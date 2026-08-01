<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>

<html>
<!doctype html>
<html lang="es">
  <head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>DISTRIBUIDORA</title>

    <!-- Bootstrap 5 -->
    <link
      <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <link
      rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css"/>

    <!-- CSS Personalizado -->
    <link rel="stylesheet" href="css/style.css" />
  </head>
<body>
<header>

<nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid">
    <a class="navbar-brand" href="index.jsp">DISTRIBUIDORA</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link" href="productos.jsp">Productos</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="proveedores.jsp">Proveedores</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="categorias.jsp">Categorias</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="ingresos.jsp">Ingresos</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="egresos.jsp">Egresos</a>
        </li>
        <c:if test="${sessionScope.usuario != null}">
                <a class="btnLogin" href="login.jsp">CERRAR SESION</a>
                </c:if>
      </ul>
    </div>
  </div>
</nav>

</header>

    <main>
          <c:if test="${sessionScope.usuario == null}">
                <p>NO HAY DATOS,POR FAVOR INICIE SESIÓN</p>
          </c:if>
    </main>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>
