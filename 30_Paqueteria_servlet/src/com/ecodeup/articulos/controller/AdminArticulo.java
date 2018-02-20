package com.ecodeup.articulos.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Request;

import com.ecodeup.articulos.dao.ArticuloDAO;
import com.ecodeup.articulos.model.Articulo;

/**
 * Servlet implementation class AdminArticulo
 */
@WebServlet("/adminArticulo")
public class AdminArticulo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArticuloDAO articuloDAO;
	
	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		try {

			articuloDAO = new ArticuloDAO(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (Exception e)	 {
			// TODO: handle exception
		}
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminArticulo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Hola Servlet..");
		String action = request.getParameter("action");
		System.out.println(action);
		try {
			switch (action) {
			case "index":
				response.sendRedirect("index.jsp");
				break;
			case "register":
				registrar(request, response);
				break;
			case "mostrar":
				mostrar(request, response);
				break;
			case "buscar":
				buscar(request, response);
				break;
			default:
				response.sendRedirect("vista/"+action+".jsp");
				break;
			}			
		} catch (SQLException | ParseException e) {
			e.getStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doGet(request, response);
	}
	
	private void index (HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		RequestDispatcher dispatcher= request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	private void registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ParseException {
		System.out.println("Metodo Registrar");
		String origen = request.getParameter("origen");
		String destino= request.getParameter("destino");
		String paquete = request.getParameter("paquete");
		String fecha = request.getParameter("fecha");
		String remitente = request.getParameter("remitente");
		String transportista = request.getParameter("transportista");
		double precio= Float.parseFloat(request.getParameter("precio"));
		Articulo articulo = new Articulo(0, origen, destino, paquete, fecha, remitente, transportista, precio);
		System.out.println(articuloDAO.insertar(articulo));
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}
	
	private void mostrar(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException , ServletException, ParseException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/mostrar.jsp");
		List<Articulo> listaArticulos= articuloDAO.listarArticulos(request.getParameter("origen"), request.getParameter("destino"));
		request.setAttribute("lista", listaArticulos);
		dispatcher.forward(request, response);
	}	
	
	private void buscar(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException , ServletException, ParseException{
		response.sendRedirect("vista/buscar.jsp");
	}	
}