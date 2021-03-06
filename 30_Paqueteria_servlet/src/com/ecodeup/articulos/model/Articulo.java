package com.ecodeup.articulos.model;

import java.util.Date;

public class Articulo {
	
	private int id;
	private String origen;
	private String destino;
	private String paquete;
	private String fecha;
	private String remitente;
	private String transportista;
	private double precio;
	
	public Articulo(int id, String origen, String destino, String paquete, String fecha, String remitente,
			String transportista, double precio) {
		this.id = id;
		this.origen = origen;
		this.destino = destino;
		this.paquete = paquete;
		this.fecha = fecha;
		this.remitente = remitente;
		this.transportista = transportista;
		this.precio = precio;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrigen() {
		return origen;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public String getPaquete() {
		return paquete;
	}
	public void setPaquete(String paquete) {
		this.paquete = paquete;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getRemitente() {
		return remitente;
	}
	public void setRemitente(String remitente) {
		this.remitente = remitente;
	}
	public String getTransportista() {
		return transportista;
	}
	public void setTransportista(String transportista) {
		this.transportista = transportista;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
}