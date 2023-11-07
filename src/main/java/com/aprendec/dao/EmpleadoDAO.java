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
    	 String sql = String.format("SELECT * FROM nominas WHERE dni_empleado = ?");
    	 statement = connection.prepareStatement(sql);
    	 statement.setString(1, dni);
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
 
 public Empleado buscarEmpleado(String filtro, String valorFiltro) throws SQLException {
	 Empleado empleado = null;
     try {
    	 connection = obtenerConexion();
    	 String sql = String.format("SELECT * FROM empleados WHERE ? = ?");
    	 statement = connection.prepareStatement(sql);
    	 statement.setString(1, filtro);
    	 statement.setString(2, valorFiltro);
    	 rs = statement.executeQuery();
    	 if (rs.next()) {
    		 empleado = new Empleado(rs.getInt(1),rs .getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6));
    	 }
     
    	 statement.close();
    	 connection.close();
    	 
     } catch (SQLException e) {
    	 e.printStackTrace();
     }
     return empleado;
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
  ResultSet resultSet = null;
  List<Empleado> listaEmpleados = new ArrayList<>();
 
  String sql = null;
  estadoOperacion = false;
  connection = obtenerConexion();
 
  try {
   sql = "SELECT * FROM empleados";
   statement = connection.prepareStatement(sql);
   resultSet = statement.executeQuery(sql);
   while (resultSet.next()) {
    Empleado emp = new Empleado();
    emp.setId(resultSet.getInt(1));
    emp.setDNI(resultSet.getString(2));
    emp.setNombre(resultSet.getString(3));
    emp.setSexo(resultSet.getString(4));
    emp.setCategoria(resultSet.getInt(5));
    emp.setAnyo(resultSet.getInt(6));
    
    listaEmpleados.add(emp);
   }
 
  } catch (SQLException e) {
   e.printStackTrace();
  }
 
  return listaEmpleados;
 }
 
 // obtener producto
 public Empleado obtenerEmpleado(int idEmpleado) throws SQLException {
  ResultSet resultSet = null;
  Empleado emp = new Empleado();
 
  String sql = null;
  estadoOperacion = false;
  connection = obtenerConexion();
 
  try {
   sql = "SELECT * FROM empleados WHERE id =?";
   statement = connection.prepareStatement(sql);
   statement.setInt(1, idEmpleado);
 
   resultSet = statement.executeQuery();
 
   if (resultSet.next()) {
    emp.setId(resultSet.getInt(1));
    emp.setDNI(resultSet.getString(2));
    emp.setNombre(resultSet.getString(3));
    emp.setSexo(resultSet.getString(4));
    emp.setCategoria(resultSet.getInt(5));
    emp.setAnyo(resultSet.getInt(6));
    
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