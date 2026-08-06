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
    <link rel="stylesheet" href="css/styleProductos.css" />
  </head>
<body>
<header>

<nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid">
    <a class="navbar-brand" href="DashboardServlet">DISTRIBUIDORA</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link" href="ProductoServlet">PRODUCTOS</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="ProveedorServlet">PROVEEDORES</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="CategoriaServlet">CATEGORIAS</a>
        </li>
      </ul>
      <c:if test="${sessionScope.usuario != null}">
                               <div class="ms-lg-auto mt-3 mt-lg-0">
                                           <a class="btnLogin" href="login.jsp">
                                               CERRAR SESION
                                           </a>
                                       </div>
                </c:if>
    </div>
  </div>
</nav>

</header>

    <main>

          <div class="container mt-4">

              <div class="d-flex flex-column flex-md-row justify-content-between align-items-center gap-3 mb-3">

                  <h2>Productos</h2>

                  <div class="botonesIngresoEgreso">
                      <a href="IngresoServlet" class="btn btn-success">

                                            <i class="bi bi-plus-circle"></i>
                                            NUEVO INGRESO

                                        </a>
                                        <a href="EgresoServlet"
                                                             class="btn btn-success">

                                                              <i class="bi bi-plus-circle"></i>
                                                              NUEVO EGRESO

                                                          </a>
                  </div>


              </div>
              <div class="table-responsive">
              <table class="table table-striped table-hover table-bordered align-middle">

                  <thead class="table-dark">

                  <tr>

                      <th>Nombre</th>
                      <th>Categoría</th>
                      <th>Proveedor</th>
                      <th>Precio Compra</th>
                      <th>Precio Venta</th>
                      <th>Stock</th>
                      <th>   </th>

                  </tr>

                  </thead>

                  <tbody>

                  <c:forEach var="producto" items="${listaProductos}">

                      <tr>

                          <td>${producto.nombre}</td>

                          <td>${producto.categoriaProducto.nombre}</td>

                          <td>${producto.proveedorProducto.nombre}</td>

                          <td>${producto.precioCompra}</td>

                          <td>${producto.precioVenta}</td>

                          <td>${producto.stock}</td>

                          <td>

                              <a href="ProductoServlet?operacion=editar&id=${producto.idProducto}"
                                 class="btn btn-warning btn-sm">

                                  Editar

                              </a>

                          </td>

                      </tr>

                  </c:forEach>

                  <c:if test="${empty listaProductos}">

                      <tr>

                          <td colspan="8" class="text-center">

                              No hay productos registrados.

                          </td>

                      </tr>

                  </c:if>

                  </tbody>

              </table>
              </div>
          </div>

    </main>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>
