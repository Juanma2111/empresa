package com.aprendec.model;
 
import java.sql.Date;
 
public class Empleado {
 
 private int id;
 private String DNI;
 private String nombre;
 private String sexo;
 private int categoria;
 private int años;
 
 public Empleado(int id, String DNI, String nombre, String sexo, int categoria, int años) {
  super();
  this.id = id;
  this.DNI = DNI;
  this.nombre = nombre;
  this.sexo = sexo;
  this.categoria = categoria;
  this.años = años;
 }
 
 public Empleado() {
  // TODO Auto-generated constructor stub
 }
 
 public int getId() {
  return id;
 }
 
 public void setId(int id) {
  this.id = id;
 }
 
 public String getDNI() {
  return DNI;
 }
 
 public void setDNI(String DNI) {
  this.DNI = DNI;
 }
 
 public String getNombre() {
  return nombre;
 }
 
 public void setNombre(String nombre) {
  this.nombre = nombre;
 }
 
 
 
 public String getSexo() {
  return sexo;
 }
 
 public void setSexo(String sexo) {
  this.sexo = sexo;
 }
 
 public int getCategoria() {
	 return categoria;
 }
 
 public void setCategoria(int categoria) {
	 this.categoria = categoria;
 }
 
 public int getAño() {
	 return años;
 }
 
 public void setAño(int años) {
	 this.años = años;
 }
 
 @Override
 public String toString() {
  return "Producto [id=" + id + ", nombre=" + nombre + ", DNI=" + DNI + ", sexo=" + sexo 
		  + ", categoria=" + categoria + ", años=" + años + "]";
 }
 
}