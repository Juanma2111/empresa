package com.aprendec.model;
 
public class Nomina {
 
 private String DNI;
 private int sueldo;
 
 public Nomina(String DNI, int sueldo) {
  this.DNI = DNI;
  this.sueldo = sueldo;
 }
 
 public Nomina() {
  // TODO Auto-generated constructor stub
 }
 
 public String getDNI() {
  return DNI;
 }
 
 public void setDNI(String DNI) {
  this.DNI = DNI;
 }
 
 public int getSueldo() {
	 return sueldo;
 }
 
 public void setSueldo(int sueldo) {
	 this.sueldo = sueldo;
 }
 
 @Override
 public String toString() {
  return "Producto [" + "DNI=" + DNI + ", sueldo=" + sueldo + "]";
 }
 
}