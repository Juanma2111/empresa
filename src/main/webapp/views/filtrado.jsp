<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Filtrar empleados</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
  <h1>Buscar Empleado</h1>
  
  <form action="empleados">
  	<select name="filtro">
  		<option value="nombre">Nombre</option>
  		<option value="dni">DNI</option>
  		<option value="sexo">Sexo</option>
  		<option value="categoria">Categoria</option>
  		<option value="anyos">Anyos</option>
  	</select>
  	<input type="text" name="valorFiltro" required>
    <input type="text" hidden name="opcion" value="filtrado">
    <input type="submit" value="Buscar">
  </form>
  
   <table border="1">
  <tr>
   <td>Id</td>
   <td>DNI</td>
   <td>Nombre</td>
   <td>Sexo</td>
   <td>Categoria</td>
   <td>Años</td>
  </tr>
  <c:forEach var="empleado" items="${lista}">
  <tr>
    <td>
      <c:out value="${ empleado.id}"></c:out>
    </td>
    <td><c:out value="${ empleado.DNI}"></c:out></td>
    <td><c:out value="${ empleado.nombre}"></c:out></td>
    <td><c:out value="${ empleado.sexo}"></c:out></td>
    <td><c:out value="${ empleado.categoria}"></c:out></td>
    <td><c:out value="${ empleado.getAnyo()}"></c:out></td>
  </tr>
  </c:forEach>
 </table>
  
    
    <form action="javascript:history.go(-1)">
  <input type="submit" value="Volver">
  </form>
</body>
</html>