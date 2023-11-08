<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Buscar Nomina</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
  <h1>Buscar Empleado por DNI</h1>
    <form action="empleados" method="post">
        DNI: <input type="text" name="dni" required>
        <input type="text" hidden name="opcion" value="buscarNomina">
        <input type="submit" value="Buscar">
    </form>
    <c:if test="${not empty nomina}">
    <table border="1">
    	<tr>
    		<td>DNI</td>
    		<td>Salario</td>
    	</tr>
    	<c:set var="nomina" value="${nomina}"></c:set>
    	<tr>
    		<td><input type="text" name="DNI" size="9" value="${nomina.DNI}" readonly></td>
    		<td><input type="text" name="sueldo" size="15" value="${nomina.sueldo}" readonly></td>
    	</tr>
    </table>
    </c:if>
    
    <form action="index.jsp">
  <input type="submit" value="Volver">
  </form>
</body>
</html>