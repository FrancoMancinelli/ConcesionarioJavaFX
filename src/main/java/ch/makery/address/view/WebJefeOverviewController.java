package ch.makery.address.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Session;

import ch.makery.address.WebJefeApp;
import hibernate.persistence.dao.ConcesionarioDao;
import hibernate.persistence.dao.EmpleadoDao;
import hibernate.persistence.dao.PropuestaDao;
import hibernate.persistence.dao.ReparacionDao;
import hibernate.persistence.dao.VehiculoDao;
import hibernate.persistence.models.Concesionario;
import hibernate.persistence.models.Empleado;
import hibernate.persistence.models.Propuesta;
import hibernate.persistence.models.Reparacion;
import hibernate.persistence.models.Vehiculo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import utils.CustomListaWebJefe;
import utils.HibernateUtil;

public class WebJefeOverviewController {

	private Session session = HibernateUtil.getSessionFactory().openSession();

	private EmpleadoDao empleadoDao = new EmpleadoDao(session);
	private PropuestaDao propuestaDao = new PropuestaDao(session);
	private ConcesionarioDao concesionarioDao = new ConcesionarioDao(session);
	private VehiculoDao vehiculoDao = new VehiculoDao(session);
	private ReparacionDao reparacionDao = new ReparacionDao(session);

	private List<Propuesta> listaPropuestas = new ArrayList<Propuesta>();
	private List<Vehiculo> listaVehiculos = new ArrayList<Vehiculo>();
	private List<Reparacion> listaReparaciones = new ArrayList<Reparacion>();

	// Paneles
	@FXML
	private Pane loginPane;
	@FXML
	private Pane inicioPane;
	@FXML
	private Pane registroPane;
	@FXML
	private Pane resumenVentasPane;
	@FXML
	private Pane stockPane;
	@FXML
	private Pane resumenTallerPane;

	// Componentes Panel Login
	@FXML
	private Button btnLogin;
	@FXML
	private TextField username;
	@FXML
	private PasswordField password;

	// Componentes generales (Barra superior)
	@FXML
	private Button btnLogout;
	@FXML
	private Button btnRetroceder;
	@FXML
	private Label tituloSuperior;

	// Botones Panel Inicio
	@FXML
	private Button btnVerResumenVentas;
	@FXML
	private Button btnVerResumenTaller;
	@FXML
	private Button btnVerStock;
	@FXML
	private Button btnVerRegistro;

	// Componentes Panel Registro
	@FXML
	private Button btnSignup;
	@FXML
	private TextField registroTxfNombre;
	@FXML
	private TextField registroTxfApellidos;
	@FXML
	private TextField registroTxfPassword;
	@FXML
	private TextField registroTxfDNI;
	@FXML
	private TextField registroTxfTelefono;
	@FXML
	private TextField registroTxfDireccion;

	// Componentes Panel Resumen Ventas
	@FXML
	private GridPane cabeceraResumenVentas;

	@FXML
	private GridPane gridResumenVentasData;

	@FXML
	private TextField txfGananciasAnual;

	@FXML
	private TextField txfGananciasMensual;

	@FXML
	private TextField txfGananciasSemanal;

	@FXML
	private TextField buscadorVentas;

	@FXML
	private Button btnBuscarVentas;

	@FXML
	private ChoiceBox<String> filtradorVentas;

	@FXML
	private Button btnFiltrarVentas;

	// Componentes Panel Stock
	@FXML
	private GridPane cabeceraStock;

	@FXML
	private GridPane gridStockData;

	@FXML
	private Button btnBuscarStock;

	@FXML
	private TextField buscadorStock;

	@FXML
	private ChoiceBox<String> filtradorStock;

	@FXML
	private Button btnFiltrarStock;

	// Componentes Panel Resumen Taller
	@FXML
	private Button btnGananciasAnualTaller;

	@FXML
	private Button btnGananciasMensualTaller;

	@FXML
	private Button btnGananciasSemanalTaller;

	@FXML
	private ScrollPane scrollPane;

	@FXML
	private VBox vBox;
	
	@FXML
	private TextField txfGananciasAnual1;

	@FXML
	private TextField txfGananciasMensual1;

	@FXML
	private TextField txfGananciasSemanal1;

	// Referencia la MainApp.
	private WebJefeApp mainAppWebJefes;
	
	// Referencias para CSS
	private Pane contenedorLogin, paneCorreo, paneCandado, panePasswordLogin,
				 paneContenedorRegistro, panelContenedorGridStock;
	
	private Button tituloTotalGananciasAnualVentas, tituloTotalGananciasMensualVentas, 
					tituloTotalGananciasSemanalVentas, tituloTotalGananciasAnuales2,
					tituloTotalGananciasMensuales2, tituloTotalGananciasSemanales2;

	/**
	 * Initializes the controller class. This method is automatically called after
	 * the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		String[] opcionesChoiceBoxVentas = { "-", "Más antigua", "Más reciente", "Más costoso", "Más barato" };
		filtradorVentas.getItems().addAll(opcionesChoiceBoxVentas);

		String[] opcionesChoiceBoxStock = { "-", "Más costoso", "Más barato", "Solo Motos", "Solo Coches",
				"Solo Ciclomotor" };
		filtradorStock.getItems().addAll(opcionesChoiceBoxStock);

		// Rellenamos la cabecera grid ventas
		String[] cabeceraInfo = { "ID", "FECHA", "AMOUNT", "CLIENTE", "VEHICULO" };

		for (int i = 0; i < cabeceraInfo.length; i++) {
			Label label = new Label(cabeceraInfo[i]);
			label.setFont(Font.font("System", FontWeight.BOLD, FontPosture.REGULAR, 16)); // Fuente en negrita
		    label.setAlignment(Pos.CENTER); // Alinea el texto en el centro de la casilla
		    label.setMaxWidth(Double.MAX_VALUE); // La Label ocupa todo el espacio posible en la casilla
			cabeceraResumenVentas.add(label, i, 0);
		}

		// Rellenamos la cabeceras stock
		String[] cabeceraInfoStock = { "ID", "VEHICULO", "MODELO", "CALOR", "VALOR" };

		for (int i = 0; i < cabeceraInfoStock.length; i++) {
		    Label label = new Label(cabeceraInfoStock[i]);
		    label.setFont(Font.font("System", FontWeight.BOLD, FontPosture.REGULAR, 16)); // Fuente en negrita
		    label.setAlignment(Pos.CENTER); // Alinea el texto en el centro de la casilla
		    label.setMaxWidth(Double.MAX_VALUE); // La Label ocupa todo el espacio posible en la casilla
		    cabeceraStock.add(label, i, 0);
		}
		
		vBox.setStyle("-fx-background-color: rgba(0, 0, 0, 0);");
		scrollPane.setStyle("-fx-background-color: transparent;");


	}

	/**
	 * Metodo que realiza la comprobacion de datos para iniciar sesion
	 * 
	 * @param event
	 */
	@FXML
	void singIn(ActionEvent event) {

		if (!username.getText().isEmpty() && !password.getText().isEmpty()) {
			Empleado empleado = empleadoDao.buscarPorUserAndPass(username.getText(), password.getText());
			
			if(empleado != null) {
				if(empleadoDao.esDirector(empleado.getId())) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Usuario Correcto");
					alert.setHeaderText(
							"Bienvenid@ " + empleado.getNombre() + " " + empleado.getApellido());
					alert.showAndWait();

					inicio(event);
				} else {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Permisos Insuficientes");
					alert.setHeaderText(
							"¡Ups! Parece que este usuario no tiene permsiso suficientes para ingresar.\n\nSi crees que se trata de un error, contacta a soporte");
					alert.showAndWait();
				}
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Error");
				alert.setHeaderText("Usuario o password incorrectos\nIntentelo de nuevo...");
				alert.showAndWait();
			}

		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Error");
			alert.setHeaderText("Rellene todos los campos");

			alert.showAndWait();
		}
	}

	@FXML
	void logOut(ActionEvent event) {
		registroPane.setVisible(false);
		resumenTallerPane.setVisible(false);
		stockPane.setVisible(false);
		resumenVentasPane.setVisible(false);
		inicioPane.setVisible(false);
		loginPane.setVisible(true);
		btnRetroceder.setVisible(false);
		btnLogout.setVisible(false);

		tituloSuperior.setText("LOGIN");

		username.clear();
		password.clear();
		buscadorStock.clear();
		buscadorVentas.clear();
		registroTxfNombre.clear();
		registroTxfApellidos.clear();
		registroTxfPassword.clear();
		registroTxfDNI.clear();
		registroTxfTelefono.clear();
		registroTxfDireccion.clear();
	}

	@FXML
	void verResumenVentasPane(ActionEvent event) {
		inicioPane.setVisible(false);
		resumenVentasPane.setVisible(true);
		btnRetroceder.setVisible(true);

		filtradorVentas.setValue("-");

		listaPropuestas.clear();
		listaPropuestas = propuestaDao.listarVendidos();
		List<String[]> listaDatos = new ArrayList<String[]>();

		for (Propuesta p : listaPropuestas) {
			String[] datosPropuesta = { p.getCliente().getId().toString(), p.getId().getFecha().toString(),
					p.getPrecio().toString(), p.getCliente().getNombre(), p.getVehiculo().getModelo() };

			listaDatos.add(datosPropuesta);
		}

		rellenarGridResumenVentas(listaDatos);
		
		List<Propuesta> listaAnual = propuestaDao.listarVendidosUltimoAno();
		List<Propuesta> listaMensual = propuestaDao.listarVendidosMesAnterior();
		List<Propuesta> listaSemanal = propuestaDao.listarVendidosSemanaPasada();

		double gananciasAnual = 0;
		double gananciasMensual= 0;
		double gananciasSemanal = 0;

		for (Propuesta p : listaAnual) {
			gananciasAnual += p.getPrecio();
		}
		for (Propuesta p : listaMensual) {
			gananciasMensual += p.getPrecio();
		}
		for (Propuesta p : listaSemanal) {
			gananciasSemanal += p.getPrecio();
		}
		txfGananciasAnual.setText(String.valueOf(gananciasAnual+"$"));
		txfGananciasMensual.setText(String.valueOf(gananciasMensual+"$"));
		txfGananciasSemanal.setText(String.valueOf(gananciasSemanal+"$"));

	}

	@FXML
	void verResumenVentasPaneBuscados(ActionEvent event) {
		if (!buscadorVentas.getText().isEmpty()) {
			listaPropuestas.clear();
			listaPropuestas = propuestaDao.listarVendidos();
			List<String[]> listaDatos = new ArrayList<String[]>();

			for (Propuesta p : listaPropuestas) {
				String[] datosPropuesta = { p.getCliente().getId().toString(), p.getId().getFecha().toString(),
						p.getPrecio().toString(), p.getCliente().getNombre(), p.getVehiculo().getModelo() };

				for (String s : datosPropuesta) {
					if (s.contains(buscadorVentas.getText())) {
						listaDatos.add(datosPropuesta);
						break;
					}
				}
			}

			rellenarGridResumenVentas(listaDatos);

		}
	}

	@FXML
	void verResumenVentasPaneFiltrados(ActionEvent event) {
		if (filtradorVentas.getValue().equals("-")) {
			return;
		}

		Comparator<Propuesta> comparador = null;

		switch (filtradorVentas.getValue()) {
		case "Mas antigua":
			comparador = Comparator.comparing(p -> p.getId().getFecha());
			break;
		case "Mas reciente":
			comparador = Comparator.comparing(p -> p.getId().getFecha(), Comparator.reverseOrder());
			break;
		case "Mas costoso":
			comparador = Comparator.comparing(Propuesta::getPrecio, Comparator.reverseOrder());
			break;
		case "Mas barato":
			comparador = Comparator.comparing(Propuesta::getPrecio);
			break;
		default:
			return;
		}

		List<String[]> listaDatos = listaPropuestas.stream().sorted(comparador)
				.map(p -> new String[] { p.getCliente().getId().toString(), p.getId().getFecha().toString(),
						p.getPrecio().toString(), p.getCliente().getNombre(), p.getVehiculo().getModelo() })
				.collect(Collectors.toList());

		rellenarGridResumenVentas(listaDatos);
	}

	@FXML
	void verStockFiltrados(ActionEvent event) {
		if (filtradorStock.getValue().equals("-")) {
			return;
		}

		List<Vehiculo> aux = new ArrayList<>(listaVehiculos);
		List<String[]> listaDatos = new ArrayList<>();

		Comparator<Vehiculo> comparador = null;

		switch (filtradorStock.getValue()) {
		case "Mas barato":
			comparador = Comparator.comparing(Vehiculo::getPrecio);
			break;
		case "Mas costoso":
			comparador = Comparator.comparing(Vehiculo::getPrecio, Comparator.reverseOrder());
			break;
		case "Solo Motos":
			aux.removeIf(v -> !v.getTipo().equals("Moto"));
			break;
		case "Solo Coches":
			aux.removeIf(v -> !v.getTipo().equals("Coche"));
			break;
		case "Solo Ciclomotor":
			aux.removeIf(v -> !v.getTipo().equals("Ciclomotor"));
			break;
		default:
			return;
		}

		if (comparador != null) {
			Collections.sort(aux, comparador);
		}

		for (Vehiculo v : aux) {
			String[] datosStock = { String.valueOf(v.getId()), v.getTipo(), v.getModelo(), v.getColor(),
					v.getPrecio().toString() };
			listaDatos.add(datosStock);
		}

		rellenarGridStock(listaDatos);
	}

	@FXML
	void verStockPane(ActionEvent event) {
		inicioPane.setVisible(false);
		stockPane.setVisible(true);
		btnRetroceder.setVisible(true);
		tituloSuperior.setText("STOCK");

		filtradorStock.setValue("-");

		listaVehiculos.clear();
		listaVehiculos = vehiculoDao.listarTodos();

		List<String[]> listaDatos = new ArrayList<String[]>();

		for (Vehiculo v : listaVehiculos) {
			String[] datosStock = { String.valueOf(v.getId()), v.getTipo(), v.getModelo(), v.getColor(),
					v.getPrecio().toString() };
			listaDatos.add(datosStock);
		}

		rellenarGridStock(listaDatos);

	}

	@FXML
	void verStockBuscados(ActionEvent event) {
		if (!buscadorStock.getText().isEmpty()) {
			listaVehiculos.clear();
			listaVehiculos = vehiculoDao.listarTodos();

			List<String[]> listaDatos = new ArrayList<String[]>();

			for (Vehiculo v : listaVehiculos) {
				String[] datosStock = { String.valueOf(v.getId()), v.getTipo(), v.getModelo(), v.getColor(),
						v.getPrecio().toString() };
				for (String s : datosStock) {
					if (s.contains(buscadorStock.getText())) {
						listaDatos.add(datosStock);
						break;
					}
				}
			}
			rellenarGridStock(listaDatos);
		}
	}

	@FXML
	void verResumenTallerPane(ActionEvent event) {
		inicioPane.setVisible(false);
		resumenTallerPane.setVisible(true);
		btnRetroceder.setVisible(true);
		tituloSuperior.setText("RESUMEN TALLER");

		vBox.getChildren().clear();

		listaReparaciones = reparacionDao.listarTodos();
		vBox.setMaxHeight(10000);

		for (Reparacion r : listaReparaciones) {
				CustomListaWebJefe customPane = new CustomListaWebJefe(r, "-fx-background-color: #FDFBF6;");
				vBox.getChildren().add(customPane);

		}
		
		List<Propuesta> listaAnual = propuestaDao.listarVendidosUltimoAno();
		List<Propuesta> listaMensual = propuestaDao.listarVendidosMesAnterior();
		List<Propuesta> listaSemanal = propuestaDao.listarVendidosSemanaPasada();

		double gananciasAnual = 0;
		double gananciasMensual= 0;
		double gananciasSemanal = 0;

		for (Propuesta p : listaAnual) {
			gananciasAnual += p.getPrecio();
		}
		for (Propuesta p : listaMensual) {
			gananciasMensual += p.getPrecio();
		}
		for (Propuesta p : listaSemanal) {
			gananciasSemanal += p.getPrecio();
		}
		txfGananciasAnual1.setText(String.valueOf(gananciasAnual+"$"));
		txfGananciasMensual1.setText(String.valueOf(gananciasMensual+"$"));
		txfGananciasSemanal1.setText(String.valueOf(gananciasSemanal+"$"));

	}

	@FXML
	void verRegistroPane(ActionEvent event) {
		inicioPane.setVisible(false);
		registroPane.setVisible(true);
		btnRetroceder.setVisible(true);
		tituloSuperior.setText("REGISTRO");
	}

	@FXML
	void inicio(ActionEvent event) {
		registroPane.setVisible(false);
		resumenTallerPane.setVisible(false);
		stockPane.setVisible(false);
		resumenVentasPane.setVisible(false);
		inicioPane.setVisible(true);
		tituloSuperior.setText("INICIO");
		btnRetroceder.setVisible(false);
		btnLogout.setVisible(true);
	}

	@FXML
	void registrarNuevoEmpleado(ActionEvent event) {
		if (!formularioRegistroVacio()) {
			if (telefonoValido()) {
				if (dniValido()) {
					Concesionario conce = concesionarioDao.buscarPorId(1);
					// Registra empleado, informa del exito, limpia fields
					empleadoDao.registrarEmpleado(registroTxfNombre.getText(), registroTxfApellidos.getText(),
							registroTxfPassword.getText(), registroTxfDNI.getText(),
							Integer.parseInt(registroTxfTelefono.getText()), registroTxfDireccion.getText(), conce);

					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Succes");
					alert.setHeaderText("Empleado registrado con exito\nUsername asignado: "
							+ registroTxfNombre.getText() + registroTxfApellidos.getText().substring(0, 1));
					alert.showAndWait();

					registroTxfNombre.clear();
					registroTxfPassword.clear();
					registroTxfDireccion.clear();
					registroTxfDNI.clear();
					registroTxfTelefono.clear();
					registroTxfApellidos.clear();

					// DNI Invalido
				} else {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Error");
					alert.setHeaderText("DNI invalido");
					alert.showAndWait();
				}

				// Telefono Invalido
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Error");
				alert.setHeaderText("Telefono Invalido");
				alert.showAndWait();
			}

			// Algun campo esta vacio...
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Error");
			alert.setHeaderText("Rellene todos los campos");
			alert.showAndWait();
		}

	}

	private boolean formularioRegistroVacio() {
		if (registroTxfNombre.getText().isEmpty() || registroTxfApellidos.getText().isEmpty()
				|| registroTxfPassword.getText().isEmpty() || registroTxfDNI.getText().isEmpty()
				|| registroTxfTelefono.getText().isEmpty() || registroTxfDireccion.getText().isEmpty()) {
			return true;
		}
		return false;
	}

	private boolean telefonoValido() {
		String telefono = registroTxfTelefono.getText();
		if (telefono.length() != 9) {
			return false;
		}
		for (int i = 0; i < telefono.length(); i++) {
			if (!Character.isDigit(telefono.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	private boolean dniValido() {
		String dni = registroTxfDNI.getText();
		if (dni.length() == 9 && Character.isLetter(dni.charAt(dni.length() - 1))) {
			// Comprueba si los primeros 8 caracteres son todos digitos.
			for (int i = 0; i < 9; i++) {
				if (!Character.isDigit(dni.charAt(i))) {
				}
			}
			// Si los primeros 9 caracteres son todos digitos y el ultimo es una letra,
			// devuelve verdadero.
			return true;
		}
		// Si la longitud del String no es igual a 9 o el iltimo caracter no es una
		// letra, devuelve falso.
		return false;
	}

	void rellenarGridResumenVentas(List<String[]> datos) {

		gridResumenVentasData.getChildren().clear();
		// Recorre los elementos de la lista y agrega nodos a la grid.
		int row = 0;
		for (String[] fila : datos) {
			for (int col = 0; col < 5; col++) {
				Label label = new Label(fila[col]);
				label.setFont(Font.font("System", FontPosture.REGULAR, 14)); 
			    label.setAlignment(Pos.CENTER); // Alinea el texto en el centro de la casilla
			    label.setMaxWidth(Double.MAX_VALUE); // La Label ocupa todo el espacio posible en la casilla
				gridResumenVentasData.add(label, col, row);
			}
			row++;
			if (row > 15) {
				break;
			}
		}
	}

	void rellenarGridStock(List<String[]> datos) {

		gridStockData.getChildren().clear();

		// Recorre los elementos de la lista y agrega nodos a la grid.
		int row = 0;
		for (String[] fila : datos) {
			for (int col = 0; col < 5; col++) {
				Label label = new Label(fila[col]);
				label.setFont(Font.font("System", FontPosture.REGULAR, 14)); 
			    label.setAlignment(Pos.CENTER); // Alinea el texto en el centro de la casilla
			    label.setMaxWidth(Double.MAX_VALUE); // La Label ocupa todo el espacio posible en la casilla
				gridStockData.add(label, col, row);

			}
			row++;
			if (row > 15) {
				break;
			}
		}
	}


}