package ch.makery.address.view;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import ch.makery.address.TabletMecanicoApp;
import hibernate.persistence.dao.ClienteDao;
import hibernate.persistence.dao.ConcesionarioDao;
import hibernate.persistence.dao.EmpleadoDao;
import hibernate.persistence.dao.PropuestaDao;
import hibernate.persistence.dao.ReparacionDao;
import hibernate.persistence.dao.VehiculoDao;
import hibernate.persistence.models.Cliente;
import hibernate.persistence.models.Empleado;
import hibernate.persistence.models.Propuesta;
import hibernate.persistence.models.Reparacion;
import hibernate.persistence.models.Vehiculo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import utils.HibernateUtil;

public class WebEmpleadoOverviewController {

	private Session session = HibernateUtil.getSessionFactory().openSession();

	private EmpleadoDao empleadoDao = new EmpleadoDao(session);
	private PropuestaDao propuestaDao = new PropuestaDao(session);
	private ConcesionarioDao concesionarioDao = new ConcesionarioDao(session);
	private VehiculoDao vehiculoDao = new VehiculoDao(session);
	private ReparacionDao reparacionDao = new ReparacionDao(session);
	private ClienteDao clienteDao = new ClienteDao(session);

	private List<Empleado> listaEmpleados = new ArrayList<Empleado>();
	private List<Propuesta> listaPropuestas = new ArrayList<Propuesta>();
	private List<Cliente> listaClientes = new ArrayList<Cliente>();
	private List<Vehiculo> listaVehiculos = new ArrayList<Vehiculo>();
	private List<Reparacion> listaReparaciones = new ArrayList<Reparacion>();
	private Alert alert = new Alert(AlertType.WARNING);

	// Componentes generales (Barra superior)
	@FXML
	private Button btnLogout;
	@FXML
	private Button btnRetroceder;
	@FXML
	private Label tituloSuperior;

	// Panel Login
	@FXML
	private Pane loginPane;
	@FXML
	private Button btnLogin;
	@FXML
	private TextField tflUsername;
	@FXML
	private PasswordField tflPassword;

	// Panel concesionarios
	@FXML
	private Pane concesionariosPane;
	@FXML
	private Pane concesionarioBarcelona;
	@FXML
	private Pane concesionarioJerez;
	@FXML
	private Pane concesionarioMarbella;
	@FXML
	private Pane concesionarioMalaga;

	// Panel Lista Clientes
	@FXML
	private Pane listaClientesPane;
	@FXML
	private TextField listaClientesBuscadorClientes;
	@FXML
	private Button listaClientesAnadirBtt;
	@FXML
	private Button listaClientesBuscarBtt;
	@FXML
	private ChoiceBox<String> listaClientesChoiceBFiltradorClientes;

	// Panel Registro
	@FXML
	private Pane registroPane;
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

	@FXML
	private Pane resumenVentasPane;

	// Panel Resumen Ventas
	@FXML
	private GridPane listaClientesCabecera;

	@FXML
	private GridPane listaClientesGrid;

	@FXML
	private Button btnGananciasAnual;

	@FXML
	private Button btnGananciasMensual;

	@FXML
	private Button btnGananciasSemanal;

	@FXML
	private TextField buscadorVentas;

	@FXML
	private Button btnBuscarVentas;

	@FXML
	private Button btnFiltrarVentas;

	// Panel Stock
	@FXML
	private Pane stockPane;
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

	// Panel Resumen Taller
	@FXML
	private Pane resumenTallerPane;
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

	// Reference to the main application.
	private TabletMecanicoApp WebEmpleadoApp;

	/**
	 * Initializes the controller class. This method is automatically called after
	 * the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		listaEmpleados = empleadoDao.listarTodos();
		listaClientes = clienteDao.listarTodos();
		String[] opcionesChoiceBoxVentas = { "-", "M�s antigua", "M�s reciente", "M�s costoso", "M�s barato" };
		listaClientesChoiceBFiltradorClientes.getItems().addAll(opcionesChoiceBoxVentas);
		listaClientesChoiceBFiltradorClientes.setValue("-");

//		filtradorStock.getItems().addAll(opcionesChoiceBoxStock);

		// Rellenamos la cabecera grid ventas
		String[] cabeceraInfo = { "ID", "NOMBRE", "DIRECCION", "TELEFONO", "DNI" };

		for (int i = 0; i < cabeceraInfo.length; i++) {
			Label label = new Label(cabeceraInfo[i]);
			label.setFont(Font.font("System", FontWeight.BOLD, FontPosture.REGULAR, 12)); // Fuente en negrita
			listaClientesCabecera.add(label, i, 0);
		}

	}

	/**
	 * M�todo que realiza la comprobaci�n de datos para iniciar sesi�on
	 * 
	 * @param event
	 */
	@FXML
	void singIn(ActionEvent event) {
		Empleado empleado;

		if (!tflUsername.getText().isEmpty() && !tflPassword.getText().isEmpty()) {
			empleado = empleadoDao.buscarPorUserAndPass(tflUsername.getText(), tflPassword.getText());

			if (empleado.equals(null)) {

				alert.setHeaderText("Informaci�n Iconrrecta");
				alert.setContentText("Intentelo de nuevo...");
				alert.showAndWait();

			} else {

				alert.setHeaderText("Usuario Correcto");
				alert.setContentText("Bienvenid@ " + empleado.getNombre() + " " + empleado.getApellido());
				alert.showAndWait();

				concesionariosPane.setVisible(true);
			}
			resetearCamposLogIn();
		}

	}

	/**
	 * 
	 * @param event
	 */
	@FXML
	void forgotPassword(MouseEvent event) {
		alert.setTitle("CONTACTE CON SOPORTE");
		alert.setHeaderText("N�MERO:      657029888 \n EMAIL: Mancinelly@soporteapp.com");
		alert.showAndWait();
	}

	@FXML
	void inicio(ActionEvent event) {
		registroPane.setVisible(false);
		resumenTallerPane.setVisible(false);
		stockPane.setVisible(false);
		resumenVentasPane.setVisible(false);
		tituloSuperior.setText("Inicio");
		btnRetroceder.setVisible(false);
		btnLogout.setVisible(true);
	}

	@FXML
	void logOut(ActionEvent event) {
		registroPane.setVisible(false);
		resumenTallerPane.setVisible(false);
		stockPane.setVisible(false);
		resumenVentasPane.setVisible(false);
		loginPane.setVisible(true);
		btnRetroceder.setVisible(false);
		btnLogout.setVisible(false);

		tituloSuperior.setText("Login");

		tflUsername.clear();
		tflPassword.clear();
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

			listaVentasRellenarGrid(listaDatos);

		}
	}

	void listaVentasRellenarGrid(List<String[]> datos) {

		listaClientesGrid.getChildren().clear();
		// Recorre los elementos de la lista y agrega nodos a la grid.
		int row = 0;
		for (String[] fila : datos) {
			for (int col = 0; col < 5; col++) {
				Label label = new Label(fila[col]);
				listaClientesGrid.add(label, col, row);
			}
			row++;
			if (row > 15) {
				break;
			}
		}
	}

	/**
	 * 
	 * @param event
	 */
	@FXML
	void errorConcesionario(MouseEvent event) {
		alert.setHeaderText("ERROR");
		alert.setContentText("N�MERO:      657029888 \n EMAIL: Mancinelly@soporteapp.com");
		alert.showAndWait();
	}

	/**
	 * 
	 * @param event
	 */
	@FXML
	void showConcesionario(MouseEvent event) {
		List<String[]> listaDatos = new ArrayList<String[]>();
		listaClientesPane.setVisible(true);
		for (Cliente c : listaClientes) {
			String[] datosPropuesta = { c.getId().toString(), c.getNombre(),
					c.getDireccion(), c.getTelefono().toString(), c.getDni() };

			listaDatos.add(datosPropuesta);
		}
		listaVentasRellenarGrid(listaDatos);

	}

	/**
	 * 
	 */
	void resetearCamposLogIn() {
		tflUsername.clear();
		tflPassword.clear();
	}

}
