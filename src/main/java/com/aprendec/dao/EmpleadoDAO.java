package com.aprendec.dao;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
 
import com.aprendec.conexion.Conexion;
import com.aprendec.model.Empleado;
import com.aprendec.model.Nomina;
 
public class EmpleadoDAO {
 private Connection connection;
 private PreparedStatement statement;
 private ResultSet rs;
 private boolean estadoOperacion;
 
//buscarSalario
 public Nomina buscarNomina(String dni) throws SQLException {
	 Nomina nomina = null;
     try {
    	 connection = obtenerConexion();
    	 String sql = String.format("SELECT * FROM nominas WHERE dni_empleado = '%s'", dni);
    	 statement = connection.prepareStatement(sql);
    	 rs = statement.executeQuery();
    	 if (rs.next()) {
    		 nomina = new Nomina(rs.getString(1), rs.getInt(2));
    	 }
     
    	 statement.close();
    	 connection.close();
    	 
     } catch (SQLException e) {
    	 e.printStackTrace();
     }
     return nomina;
 }
 
 public List<Empleado> buscarEmpleado(String filtro, String valorFiltro) throws SQLException {
	 List<Empleado> listaEmpleados = new ArrayList<>();
     try {
    	 connection = obtenerConexion();
    	 String sql = String.format("SELECT * FROM empleados WHERE %s like '%s'", filtro, valorFiltro);
    	 statement = connection.prepareStatement(sql);
    	 rs = statement.executeQuery(sql);
    	 while (rs.next()) { 
    		 Empleado emp = new Empleado();
    		    emp.setId(rs.getInt(1));
    		    emp.setDNI(rs.getString(2));
    		    emp.setNombre(rs.getString(3));
    		    emp.setSexo(rs.getString(4));
    		    emp.setCategoria(rs.getInt(5));
    		    emp.setAnyo(rs.getInt(6));
    		 listaEmpleados.add(emp);
    	 }
     
    	 statement.close();
    	 connection.close();
    	 
     } catch (SQLException e) {
    	 e.printStackTrace();
     }
     return listaEmpleados;
 }
 
 // guardar producto
 public boolean guardar(Empleado empleado) throws SQLException {
  String sql = null;
  estadoOperacion = false;
  connection = obtenerConexion();
 
  try {
   connection.setAutoCommit(false);
   sql = "INSERT INTO empleados (id, dni, nombre, sexo, categoria, anyos) VALUES(?,?,?,?,?,?)";
   statement = connection.prepareStatement(sql);
 
   statement.setString(1, null);
   statement.setString(2, empleado.getDNI());
   statement.setString(3, empleado.getNombre());
   statement.setString(4, empleado.getSexo());
   statement.setInt(5, empleado.getCategoria());
   statement.setInt(6, empleado.getAnyo());
   
   estadoOperacion = statement.executeUpdate() > 0;
 
   connection.commit();
   statement.close();
   connection.close();
  } catch (SQLException e) {
   connection.rollback();
   e.printStackTrace();
  }
 
  return estadoOperacion;
 }
 
 // editar producto
 public boolean editar(Empleado empleado) throws SQLException {
  String sql = null;
  estadoOperacion = false;
  connection = obtenerConexion();
  try {
   connection.setAutoCommit(false);
   sql = "UPDATE empleados SET dni=?, nombre=?, sexo=?, categoria=?, anyos=? WHERE id=?";
   statement = connection.prepareStatement(sql);
 
   statement.setString(1, empleado.getDNI());
   statement.setString(2, empleado.getNombre());
   statement.setString(3, empleado.getSexo());
   statement.setInt(4, empleado.getCategoria());
   statement.setInt(5, empleado.getAnyo());
 
   estadoOperacion = statement.executeUpdate() > 0;
   connection.commit();
   statement.close();
   connection.close();
 
  } catch (SQLException e) {
   connection.rollback();
   e.printStackTrace();
  }
 
  return estadoOperacion;
 }
 
 // eliminar producto
 public boolean eliminar(int idEmpleado) throws SQLException {
  String sql = null;
  estadoOperacion = false;
  connection = obtenerConexion();
  try {
   connection.setAutoCommit(false);
   sql = "DELETE FROM empleados WHERE id=?";
   statement = connection.prepareStatement(sql);
   statement.setInt(1, idEmpleado);
 
   estadoOperacion = statement.executeUpdate() > 0;
   connection.commit();
   statement.close();
   connection.close();
 
  } catch (SQLException e) {
   connection.rollback();
   e.printStackTrace();
  }
 
  return estadoOperacion;
 }
 
 // obtener lista de empleados
 public List<Empleado> obtenerEmpleado() throws SQLException {
  List<Empleado> listaEmpleados = new ArrayList<>();
 
  String sql = null;
  estadoOperacion = false;
  connection = obtenerConexion();
 
  try {
   sql = "SELECT * FROM empleados";
   statement = connection.prepareStatement(sql);
   rs = statement.executeQuery(sql);
   while (rs.next()) {
    Empleado emp = new Empleado();
    emp.setId(rs.getInt(1));
    emp.setDNI(rs.getString(2));
    emp.setNombre(rs.getString(3));
    emp.setSexo(rs.getString(4));
    emp.setCategoria(rs.getInt(5));
    emp.setAnyo(rs.getInt(6));
    
    listaEmpleados.add(emp);
   }
 
  } catch (SQLException e) {
   e.printStackTrace();
  }
 
  return listaEmpleados;
 }
 
 // obtener producto
 public Empleado obtenerEmpleado(int idEmpleado) throws SQLException {
  Empleado emp = new Empleado();
 
  String sql = null;
  estadoOperacion = false;
  connection = obtenerConexion();
 
  try {
   sql = "SELECT * FROM empleados WHERE id =?";
   statement = connection.prepareStatement(sql);
   statement.setInt(1, idEmpleado);
 
   rs = statement.executeQuery();
 
   if (rs.next()) {
    emp.setId(rs.getInt(1));
    emp.setDNI(rs.getString(2));
    emp.setNombre(rs.getString(3));
    emp.setSexo(rs.getString(4));
    emp.setCategoria(rs.getInt(5));
    emp.setAnyo(rs.getInt(6));
    
   }
 
  } catch (SQLException e) {
   e.printStackTrace();
  }
 
  return emp;
 }
 
 // obtener conexion pool
 private Connection obtenerConexion() throws SQLException {
  return Conexion.getConnection();
 }
 
}