package com.ecodeup.articulos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ecodeup.articulos.model.Articulo;
import com.ecodeup.articulos.model.Conexion;

/*
 * @autor: Elivar Largo
 * @web: www.ecodeup.com
 */

public class ArticuloDAO {
	private Conexion con;
	private Connection connection;

	public ArticuloDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException {
		System.out.println(jdbcURL);
		con = new Conexion(jdbcURL, jdbcUsername, jdbcPassword);
	}

	// insertar artículo
	public boolean insertar(Articulo articulo) throws SQLException, ParseException {
		String sql = "INSERT INTO articulos (origen, destino, paquete, fecha, remitente, transportista, precio) VALUES (?,?,?,?,?,?,?)";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, articulo.getOrigen());
		statement.setString(2, articulo.getDestino());
		statement.setString(3, articulo.getPaquete());
		statement.setString(4, articulo.getFecha());
		statement.setString(5, articulo.getRemitente());
		statement.setString(6, articulo.getTransportista());
		statement.setDouble(7, articulo.getPrecio());

		boolean rowInserted = statement.executeUpdate() > 0;
		System.out.println(rowInserted);
		statement.close();
		con.desconectar();
		return rowInserted;
	}

	// listar todos los productos
	public List<Articulo> listarArticulos(String origenSQL, String destinoSQL) throws SQLException, ParseException {

		System.out.println("listarArticulos()");
		
		List<Articulo> listaArticulos = new ArrayList<Articulo>();
	String sql = "SELECT * FROM articulos WHERE origen='"+origenSQL+"' AND destino='"+destinoSQL+"'";
		con.conectar();
		connection = con.getJdbcConnection();
		Statement statement = connection.createStatement();
		ResultSet resulSet = statement.executeQuery(sql);

		System.out.println("conectado a BDD");
		
		while (resulSet.next()) {
			
			int id = resulSet.getInt("id");
			String origen = resulSet.getString("origen");
			String destino= resulSet.getString("destino");
			String paquete = resulSet.getString("paquete");
			String fecha = resulSet.getString("fecha");
			String remitente = resulSet.getString("remitente");
			String transportista = resulSet.getString("transportista");
			double precio= Float.parseFloat(resulSet.getString("precio"));
			Articulo articulo = new Articulo(id, origen, destino, paquete, fecha, remitente, transportista, precio);
			listaArticulos.add(articulo);
		}
		
		System.out.println("BDD leida");
		
		con.desconectar();
		return listaArticulos;
	}

	// obtener por id
	public Articulo obtenerPorId(int id) throws SQLException {
		Articulo articulo = null;

		String sql = "SELECT * FROM articulos WHERE id= ? ";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, id);

		ResultSet res = statement.executeQuery();
		if (res.next()) {
			int miId = res.getInt("id");
			String origen = res.getString("origen");
			String destino = res.getString("destino");
			String paquete = res.getString("paquete");
			String fecha = res.getString("fecha");
			String remitente = res.getString("remitente");
			String transportista = res.getString("transportista");
			double precio = res.getFloat("precio");
			
			articulo = new Articulo(miId, origen, destino, paquete, fecha, remitente, transportista, precio);
		}
		res.close();
		con.desconectar();

		return articulo;
	}
	
	//eliminar
	public boolean eliminar(Articulo articulo) throws SQLException {
		boolean rowEliminar = false;
		String sql = "DELETE FROM articulos WHERE ID=?";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, articulo.getId());

		rowEliminar = statement.executeUpdate() > 0;
		statement.close();
		con.desconectar();

		return rowEliminar;
	}
}