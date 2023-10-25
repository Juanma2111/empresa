package com.aprendec.controller;
 
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import com.aprendec.dao.EmpleadoDAO;
import com.aprendec.model.Empleado;
 
/**
 * Servlet implementation class EmpleadoController
 */
@WebServlet(description = "administra peticiones para la tabla empleados", urlPatterns = { "/empleados" })
public class EmpleadoController extends HttpServlet {
 private static final long serialVersionUID = 1L;
 
 /**
  * @see HttpServlet#HttpServlet()
  */
 public EmpleadoController() {
  super();
  // TODO Auto-generated constructor stub
 }
 
 /**
  * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
  *      response)
  */
 protected void doGet(HttpServletRequest request, HttpServletResponse response)
   throws ServletException, IOException {
  // TODO Auto-generated method stub
 
  String opcion = request.getParameter("opcion");
 
  if (opcion.equals("crear")) {
   System.out.println("Usted a presionado la opcion crear");
   RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/crear.jsp");
   requestDispatcher.forward(request, response);
  } else if (opcion.equals("listar")) {
 
   EmpleadoDAO empleadoDAO = new EmpleadoDAO();
   List<Empleado> lista = new ArrayList<>();
   try {
    lista = empleadoDAO.obtenerEmpleado();
    for (Empleado empleado : lista) {
     System.out.println(empleado);
    }
 
    request.setAttribute("lista", lista);
    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/listar.jsp");
    requestDispatcher.forward(request, response);
 
   } catch (SQLException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
   }
 
   System.out.println("Usted a presionado la opcion listar");
  } else if (opcion.equals("meditar")) {
   int id = Integer.parseInt(request.getParameter("id"));
   System.out.println("Editar id: " + id);
   EmpleadoDAO empleadoDAO = new EmpleadoDAO();
   Empleado emp = new Empleado();
   try {
    emp = empleadoDAO.obtenerEmpleado(id);
    System.out.println(emp);
    request.setAttribute("empleado", emp);
    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/editar.jsp");
    requestDispatcher.forward(request, response);
 
   } catch (SQLException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
   }
 
  } else if (opcion.equals("eliminar")) {
   EmpleadoDAO empleadoDAO = new EmpleadoDAO();
   int id = Integer.parseInt(request.getParameter("id"));
   try {
    empleadoDAO.eliminar(id);
    System.out.println("Registro eliminado satisfactoriamente...");
    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
    requestDispatcher.forward(request, response);
   } catch (SQLException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
   }
 
  }
  // response.getWriter().append("Served at: ").append(request.getContextPath());
 }
 
 /**
  * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
  *      response)
  */
 protected void doPost(HttpServletRequest request, HttpServletResponse response)
   throws ServletException, IOException {
  // TODO Auto-generated method stub
  String opcion = request.getParameter("opcion");
  Date fechaActual = new Date();
 
  if (opcion.equals("guardar")) {
   EmpleadoDAO empleadoDAO = new EmpleadoDAO();
   Empleado empleado = new Empleado();
   empleado.setDNI(request.getParameter("DNI"));
   empleado.setNombre(request.getParameter("nombre"));
   empleado.setSexo(request.getParameter("sexo"));
   empleado.setCategoria(Integer.parseInt(request.getParameter("categoria")));
   empleado.setAño(Integer.parseInt(request.getParameter("años")));
   try {
    empleadoDAO.guardar(empleado);
    System.out.println("Registro guardado satisfactoriamente...");
    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
    requestDispatcher.forward(request, response);
 
   } catch (SQLException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
   }
  } else if (opcion.equals("editar")) {
   Empleado empleado = new Empleado();
   EmpleadoDAO empleadoDAO = new EmpleadoDAO();
 
   empleado.setId(Integer.parseInt(request.getParameter("id")));
   empleado.setDNI(request.getParameter("DNI"));
   empleado.setNombre(request.getParameter("nombre"));
   empleado.setSexo(request.getParameter("sexo"));
   empleado.setCategoria(Integer.parseInt(request.getParameter("categoria")));
   empleado.setAño(Integer.parseInt(request.getParameter("años")));
   try {
    empleadoDAO.editar(empleado);
    System.out.println("Registro editado satisfactoriamente...");
    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
    requestDispatcher.forward(request, response);
   } catch (SQLException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
   }
  }
 
  // doGet(request, response);
 }
 
}