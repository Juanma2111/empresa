<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Editar Empleado</title>
</head>
<body>
 <h1>Editar Empleado</h1>
 <form action="empleados" method="post">
  <c:set var="empleado" value="${empleado}"></c:set>
  <input type="hidden" name="opcion" value="editar">
  <input type="hidden" name="id" value="${empleado.id}">
  <table border="1">
  <tr>
    <td>DNI:</td>
    <td><input type="text" name="nombre" size="9" value="${empleado.DNI}"></td>
   </tr>
   <tr>
    <td>Nombre:</td>
    <td><input type="text" name="nombre" size="50" value="${empleado.nombre}"></td>
   </tr>
   <tr>
    <td>Sexo:</td>
    <td><input type="text" name="cantidad" size="1" value="${empleado.sexo}"></td>
   </tr>
   <tr>
    <td>Categoria:</td>
    <td><input type="text" name="precio" size="50" value="${empleado.categoria}"></td>
   </tr>
   <tr>
    <td>Años:</td>
    <td><input type="text" name="precio" size="50" value="${empleado.años}"></td>
   </tr>
  </table>
  <input type="submit" value="Guardar">
 </form>
</body>
</html>