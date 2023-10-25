package com.aprendec.dao;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
 
import com.aprendec.conexion.Conexion;
import com.aprendec.model.Empleado;
 
public class EmpleadoDAO {
 private Connection connection;
 private PreparedStatement statement;
 private boolean estadoOperacion;
 
 // guardar producto
 public boolean guardar(Empleado empleado) throws SQLException {
  String sql = null;
  estadoOperacion = false;
  connection = obtenerConexion();
 
  try {
   connection.setAutoCommit(false);
   sql = "INSERT INTO empleados (id, dni, nombre, sexo, categoria, años) VALUES(?,?,?,?,?,?)";
   statement = connection.prepareStatement(sql);
 
   statement.setString(1, null);
   statement.setString(2, empleado.getDNI());
   statement.setString(3, empleado.getNombre());
   statement.setString(4, empleado.getSexo());
   statement.setInt(5, empleado.getCategoria());
   statement.setInt(6, empleado.getAño());
   
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
   sql = "UPDATE empleados SET dni=?, nombre=?, sexo=?, categoria=?, años=? WHERE id=?";
   statement = connection.prepareStatement(sql);
 
   statement.setString(1, empleado.getDNI());
   statement.setString(2, empleado.getNombre());
   statement.setString(3, empleado.getSexo());
   statement.setInt(4, empleado.getCategoria());
   statement.setInt(5, empleado.getAño());
 
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
    emp.setAño(resultSet.getInt(6));
    
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
    emp.setAño(resultSet.getInt(6));
    
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