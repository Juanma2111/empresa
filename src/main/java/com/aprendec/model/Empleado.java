package com.aprendec.model;
  
public class Empleado {
 
 private int id;
 private String DNI;
 private String nombre;
 private String sexo;
 private int categoria;
 private int anyos;
 
 public Empleado(int id, String DNI, String nombre, String sexo, int categoria, int anyos) {
  this.id = id;
  this.DNI = DNI;
  this.nombre = nombre;
  this.sexo = sexo;
  this.categoria = categoria;
  this.anyos = anyos;
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
 
 public int getAnyo() {
	 return anyos;
 }
 
 public void setAnyo(int anyos) {
	 this.anyos = anyos;
 }
 
 @Override
 public String toString() {
  return "Producto [id=" + id + ", nombre=" + nombre + ", DNI=" + DNI + ", sexo=" + sexo 
		  + ", categoria=" + categoria + ", años=" + anyos + "]";
 }
 
}