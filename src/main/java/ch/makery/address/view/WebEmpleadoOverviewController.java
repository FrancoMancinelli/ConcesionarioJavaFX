package ch.makery.address.view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Session;

import ch.makery.address.TabletMecanicoApp;
import hibernate.persistence.dao.ClienteDao;
import hibernate.persistence.dao.EmpleadoDao;
import hibernate.persistence.dao.PropuestaDao;
import hibernate.persistence.dao.VendedorDao;
import hibernate.persistence.models.Cliente;
import hibernate.persistence.models.Empleado;
import hibernate.persistence.models.Propuesta;
import hibernate.persistence.models.PropuestaId;
import hibernate.persistence.models.Vehiculo;
import hibernate.persistence.models.Vendedor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import utils.CustomListaWebEmpleado;
import utils.HibernateUtil;

public class WebEmpleadoOverviewController {

	private Session session = HibernateUtil.getSessionFactory().openSession();

	private Vendedor vendedor = new Vendedor();
	private EmpleadoDao empleadoDao = new EmpleadoDao(session);
	private PropuestaDao propuestaDao = new PropuestaDao(session);
	private ClienteDao clienteDao = new ClienteDao(session);
	private VendedorDao vendedorDao = new VendedorDao(session);

	private List<Propuesta> listaPropuestas = new ArrayList<Propuesta>();
	private List<Cliente> listaClientes = new ArrayList<Cliente>();
	private Alert alert = new Alert(AlertType.WARNING);

	/*
	 * 
	 * Componentes generales (Barra superior)
	 * 
	 */
	@FXML
	private Button btnLogout;
	@FXML
	private Label tituloSuperior;

	/*
	 * 
	 * Panel Login
	 * 
	 */
	@FXML
	private Pane loginPane;
	@FXML
	private Button btnLogin;
	@FXML
	private TextField tflUsername;
	@FXML
	private PasswordField tflPassword;

	/*
	 * 
	 * Panel concesionarios
	 * 
	 */
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

	/*
	 * 
	 * Panel Lista Clientes
	 * 
	 */
	@FXML
	private Pane listaClientesPane;
	@FXML
	private GridPane listaClientesCabecera;
	@FXML
	private GridPane listaClientesGrid;
	@FXML
	private TextField listaClientesBuscadorClientes;
	@FXML
	private Button listaClientesAnadirBtt;
	@FXML
	private Button listaClientesBuscarBtt;
	@FXML
	private Button listaClientesOrdenarBtt;
	@FXML
	private Button listaClientesClientesBtt;
	@FXML
	private Button listaClientesPropuestasBtt;
	@FXML
	private ChoiceBox<String> listaClientesChoiceBFiltradorClientes;

	/*
	 * 
	 * Panel Registro
	 * 
	 */
	@FXML
	private Pane registroClientesPane;
	@FXML
	private Button btnRegistrar;
	@FXML
	private TextField registroTxfNombre;
	@FXML
	private TextField registroTxfApellidos;
	@FXML
	private TextField registroTxfCorreo;
	@FXML
	private TextField registroTxfDNI;
	@FXML
	private TextField registroTxfTelefono;
	@FXML
	private TextField registroTxfDireccion;

	/*
	 * 
	 * 
	 * Panel Propuestas
	 * 
	 */
	@FXML
	private Pane listaPropuestasPane;
	@FXML
	private ScrollPane scrollPane;
	@FXML
	private VBox vBox;
	@FXML
	private TextField listaPropuestasBuscadorPropuestas;
	@FXML
	private Button listaPropuestasBuscarBtt;
	@FXML
	private ChoiceBox<String> listaPropuestasChoiceBFiltradorPropuesta;
	@FXML
	private Button listaPropuestasFiltrarBtt;
	@FXML
	private Button listaPropuestasAnadirBtt;

	/*
	 * 
	 * Panel Registro Propuestas
	 * 
	 * 
	 */
	@FXML
	private Pane formularioPropuestaPane;
	@FXML
	private ChoiceBox<Cliente> registroPrpuestasChoiceBox = new ChoiceBox<Cliente>();
	@FXML
	private TextField formularioPropuestaModelo;
	@FXML
	private TextField formularioPropuestaColor;
	@FXML
	private TextField formularioPropuestaFecha;
	@FXML
	private TextField formularioPropuestaPrecio;
	@FXML
	private TextArea formularioPropuestaDetalles;
	@FXML
	private Button formularioPropuestaAnadirBtt;

	// Componentes CSS
	private Pane paneContenedorRegistroClientes, paneGrid, paneContenedorLogin, paneCorreo, paneCandado, paneContenedorCorreo, paneContenedorPassword, paneContenedorRegistroPropuesta;
	
	// Reference to the main application.
	private TabletMecanicoApp WebEmpleadoApp;

	/**
	 * Initializes the controller class. This method is automatically called after
	 * the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		listaClientes = clienteDao.listarTodos();
		listaPropuestas = propuestaDao.listarTodos();
		String[] opcionesChoiceBoxVentas = { "-", "Nombre", "Apellido" };
		listaClientesChoiceBFiltradorClientes.getItems().addAll(opcionesChoiceBoxVentas);
		listaClientesChoiceBFiltradorClientes.setValue("-");
		registroPrpuestasChoiceBox.getItems().addAll(listaClientes);

		// Rellenamos la cabecera grid ventas
		String[] cabeceraInfo = { "ID", "NOMBRE", "DIRECCION", "TELEFONO", "DNI" };

		for (int i = 0; i < cabeceraInfo.length; i++) {
			Label label = new Label(cabeceraInfo[i]);
			label.setFont(Font.font("System", FontWeight.BOLD, FontPosture.REGULAR, 16)); // Fuente en negrita
			listaClientesCabecera.add(label, i, 0);
			listaClientesCabecera.setHalignment(label, HPos.CENTER);

		}

	}

	/**
	 * Método que realiza la comprobación de datos para iniciar sesióon
	 * 
	 * @param event
	 */
	@FXML
	void singIn(ActionEvent event) {
		Empleado empleado = new Empleado();

		if (!tflUsername.getText().isEmpty() && !tflPassword.getText().isEmpty()) {
			empleado = empleadoDao.buscarPorUserAndPass(tflUsername.getText(), tflPassword.getText());
			
			
			if(empleado != null) {
				if(empleadoDao.esVendedor(empleado.getId())) {
					vendedor = vendedorDao.buscarPorId(empleado.getId());
					alert.setHeaderText("Usuario Correcto");
					alert.setContentText("Bienvenid@ " + empleado.getNombre() + " " + empleado.getApellido());
					alert.showAndWait();
					btnLogout.setVisible(true);
					tituloSuperior.setText("INICIO");
					concesionariosPane.setVisible(true);
				} else {
					alert.setHeaderText("ERROR");
					alert.setContentText("Solo vendedores tienen acceso a esta área");
					alert.showAndWait();
				}
			} else {
				alert.setHeaderText("Informaci�n Iconrrecta");
				alert.setContentText("Intentelo de nuevo...");
				alert.showAndWait();
			}

			limpiarLogin();
		} else {
			alert.setHeaderText("ERROR");
			alert.setContentText("Rellene todos los campos");
			alert.showAndWait();
		}

	}

	/**
	 * Método que realiza una acción al pulsar en Forgot Password
	 * @param event
	 */
	@FXML
	void forgotPassword(MouseEvent event) {
		errorConcesionario(event);
	}

	/**
	 * Método que realiza el deslogueo volviendo al Login
	 * @param event
	 */
	@FXML
	void logOut(ActionEvent event) {
		tituloSuperior.setText("LOGIN");

		btnLogout.setVisible(false);
		listaClientesClientesBtt.setVisible(false);
		listaClientesPropuestasBtt.setVisible(false);

		loginPane.setVisible(true);
		concesionariosPane.setVisible(false);
		listaClientesPane.setVisible(false);
		registroClientesPane.setVisible(false);
		listaPropuestasPane.setVisible(false);
		formularioPropuestaPane.setVisible(false);

		registroTxfNombre.clear();
		registroTxfApellidos.clear();
		registroTxfCorreo.clear();
		registroTxfDNI.clear();
		registroTxfTelefono.clear();
		registroTxfDireccion.clear();
	}

	/**
	 * Método que visualiza la vista del listado de clientes
	 * @param event
	 */
	@FXML
	void irListaClientes(MouseEvent event) {
		tituloSuperior.setText("CONCESIONARIOS");

		listaClientesPane.setVisible(true);
		registroClientesPane.setVisible(false);
		listaPropuestasPane.setVisible(false);
		formularioPropuestaPane.setVisible(false);

		listaClientesPane.setVisible(true);
		listaClientesClientesBtt.setVisible(true);
		listaClientesPropuestasBtt.setVisible(true);
		listaClientesRellenarGrid(sacarDatosClientes(listaClientes));

	}

	/**
	 * Método que visualiza la vista del listado de clientes
	 * @param event
	 */
	@FXML
	void showListaClientes(ActionEvent event) {
		tituloSuperior.setText("CONCESIONARIOS");

		listaClientesPane.setVisible(true);
		registroClientesPane.setVisible(false);
		listaPropuestasPane.setVisible(false);
		formularioPropuestaPane.setVisible(false);

		listaClientesPane.setVisible(true);
		listaClientesClientesBtt.setVisible(true);
		listaClientesPropuestasBtt.setVisible(true);
		listaClientesRellenarGrid(sacarDatosClientes(listaClientes));

	}

	/**
	 * Método que devuelve un error de concesionario
	 * @param event
	 */
	@FXML
	void errorConcesionario(MouseEvent event) {
		contactar();
	}

	/**
	 * Método que visualiza la lista de propuestas
	 * @param event
	 */
	@FXML
	void verListaDePropuestas(ActionEvent event) {
		tituloSuperior.setText("LISTA PROPUESTA");

		listaPropuestasPane.setVisible(true);
		formularioPropuestaPane.setVisible(false);

		vBox.getChildren().clear();
		crearListaPropuestas(listaPropuestas);

	}

	/**
	 * Método que lista los datos con las fichas de los clientes buscados
	 * @param event
	 */
	@FXML
	void listaClientesBuscar(ActionEvent event) {
		String busqueda = listaClientesBuscadorClientes.getText();
		if (busqueda != null) {
			List<Cliente> listaClientesBuscados = new ArrayList<Cliente>();
			List<String[]> listaDatos = new ArrayList<String[]>();

			for (Cliente c : listaClientes) {
				String[] datosCliente = { c.getId().toString().toUpperCase(), c.getNombre().toUpperCase(),
						c.getDireccion().toUpperCase(), c.getTelefono().toString().toUpperCase(),
						c.getDni().toUpperCase() };

				for (String s : datosCliente) {
					if (s.contains(busqueda.toUpperCase())) {
						listaClientesBuscados.add(c);
						break;
					}
				}
			}
			listaClientesRellenarGrid(sacarDatosClientes(listaClientesBuscados));
		}
	}

	/**
	 * Método que ordena los clientes según nombre o apellido
	 * @param event
	 */
	@FXML
	void listaClientesOrdenar(ActionEvent event) {
		if (listaClientesChoiceBFiltradorClientes.getValue().equals("-")) {
			return;
		}

		Comparator<Cliente> comparador = null;

		switch (listaClientesChoiceBFiltradorClientes.getValue()) {
		case "Nombre":
			comparador = Comparator.comparing(c -> c.getNombre());
			break;
		case "Apellido":
			comparador = Comparator.comparing(c -> c.getApellido());
			break;
		default:
			return;
		}

		List<String[]> listaDatos = listaClientes.stream().sorted(comparador)
				.map(c -> new String[] { c.getId().toString(), (c.getNombre() + ", " + c.getApellido()),
						c.getDireccion(), c.getTelefono().toString(), c.getDni() })
				.collect(Collectors.toList());

		listaClientesRellenarGrid(listaDatos);
	}

	/**
	 * Método que visualiza el panel de registro de clientes
	 * @param event
	 */
	@FXML
	void listaClientesAnadir(ActionEvent event) {
		tituloSuperior.setText("REGISTRO CLIENTE");
		registroClientesPane.setVisible(true);
	}

	/**
	 * Método que registra un nuevo cliente comprobando ciertos campos cumplan los requisitos
	 * @param event
	 */
	@FXML
	void registrarNuevoCliente(ActionEvent event) {
		if (!formularioRegistroVacio()) {
			if (telefonoValido()) {
				if (dniValido()) {

					Cliente c = new Cliente(registroTxfDNI.getText(), registroTxfCorreo.getText(),
							registroTxfNombre.getText(), registroTxfApellidos.getText(), registroTxfDireccion.getText(),
							Integer.parseInt(registroTxfTelefono.getText()));
					clienteDao.insert(c);
					alert.setHeaderText("Succes");
					alert.setContentText("Cliente registrado con exito");
					alert.showAndWait();
					limpiarRegistroCliente();

					// DNI Invalido
				} else {
					alert.setHeaderText("Error");
					alert.setContentText("DNI invalido");
					alert.showAndWait();
				}

				// Teléfono Invalido
			} else {
				alert.setHeaderText("Error");
				alert.setContentText("Tel�fono Invalido");
				alert.showAndWait();
			}

			// Algún campo esta vacio...
		} else {
			alert.setHeaderText("Error");
			alert.setContentText("Rellene todos los campos");
			alert.showAndWait();
		}

	}

	/**
	 * Método que enlista las propuestas según el filtro de busqueda
	 * @param event
	 */
	@FXML
	void listaPropuestasBuscar(ActionEvent event) {
		String busqueda = listaPropuestasBuscadorPropuestas.getText();
		List<Propuesta> listaPropuestasBuscados = new ArrayList<Propuesta>();

		if (busqueda != null) {

			for (Propuesta p : listaPropuestas) {
				String[] datosCliente = { p.getId().toString().toUpperCase(), p.getVehiculo().getModelo().toUpperCase(),
						p.getId().getFecha().toString().toUpperCase(), p.getDetalles().toUpperCase() };

				for (String s : datosCliente) {
					if (s.contains(busqueda.toUpperCase())) {
						listaPropuestasBuscados.add(p);
						break;
					}
				}
			}

		}

		crearListaPropuestas(listaPropuestasBuscados);

	}

	/**
	 * Método que enlista las propuestas según el filtro de ordenación
	 * @param event
	 */
	@FXML
	void listaPropuestasOrdenar(ActionEvent event) {

		contactar();
	}

	/**
	 * Método que visualiza el registro de Propuestas
	 * @param event
	 */
	@FXML
	void listaPropuestasAnadir(ActionEvent event) {
		tituloSuperior.setText("REGISTRO PROPUESTA");
		formularioPropuestaPane.setVisible(true);
		formularioPropuestaFecha.setText(LocalDate.now().toString());
	}

	/**
	 * Método que registra una nueva propuesta
	 * @param event
	 */
	@FXML
	void registrarNuevoPropuestas(ActionEvent event) {
		Cliente c = registroPrpuestasChoiceBox.getSelectionModel().getSelectedItem();

		Vehiculo vehiculo = new Vehiculo(formularioPropuestaModelo.getText(), formularioPropuestaColor.getText(),
				Double.parseDouble(formularioPropuestaPrecio.getText()));

		Propuesta propuesta = new Propuesta(vendedor, c, vehiculo,
				Double.parseDouble(formularioPropuestaPrecio.getText()), formularioPropuestaDetalles.getText());

		Empleado empleado = vendedor.getEmpleado();
		System.out.println(empleado.getId());
		System.out.println(c.getId());
		System.out.println(vehiculo.getId());

		PropuestaId p = new PropuestaId(empleado.getId(), c.getId(), vehiculo.getId());

		propuesta.setId(p);
		propuestaDao.insert(propuesta);

		alert.setHeaderText("Succes");
		alert.setContentText("Propuesta registrada con exito");
		alert.showAndWait();

		limpiarRegistroPresupuesto();

	}

	/**
	 * Método que rellena el gridPane con los datos de los clientes
	 * @param datos
	 */
	void listaClientesRellenarGrid(List<String[]> datos) {
	    listaClientesGrid.getChildren().clear();
	    // Recorre los elementos de la lista y agrega nodos a la grid.
	    int row = 0;
	    for (String[] fila : datos) {
	        for (int col = 0; col < 5; col++) {
	            Label label = new Label(fila[col]);
	            label.setFont(new Font(14));
			    label.setAlignment(Pos.CENTER); // Alinea el texto en el centro de la casilla
			    label.setMaxWidth(Double.MAX_VALUE); // La Label ocupa todo el espacio posible en la casilla
				
	            listaClientesGrid.add(label, col, row);
	        }
	        row++;
	        if (row > 15) {
	            break;
	        }
	    }
	}


	/**
	 * Método que extrae de la base de datos los datos de los clientes
	 * @param listaClientes
	 * @return
	 */
	private List<String[]> sacarDatosClientes(List<Cliente> listaClientes) {
		List<String[]> listaDatos = new ArrayList<String[]>();

		for (Cliente c : listaClientes) {
			String[] datosCliente = { c.getId().toString(), (c.getNombre() + ", " + c.getApellido()), c.getDireccion(),
					c.getTelefono().toString(), c.getDni() };

			listaDatos.add(datosCliente);
		}

		return listaDatos;
	}

	/**
	 * Método que comprueba si el formulario esta o no vacio
	 * @return
	 */
	private boolean formularioRegistroVacio() {
		if (registroTxfNombre.getText().isEmpty() || registroTxfApellidos.getText().isEmpty()
				|| registroTxfCorreo.getText().isEmpty() || registroTxfDNI.getText().isEmpty()
				|| registroTxfTelefono.getText().isEmpty() || registroTxfDireccion.getText().isEmpty()) {
			return true;
		}
		return false;
	}

	/**
	 * Método que comprueba si un telefono es valido
	 * @return
	 */
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

	/**
	 * Método que comprueba si un DNI es valido
	 * @return
	 */
	private boolean dniValido() {
		String dni = registroTxfDNI.getText();
		if (dni.length() == 9 && Character.isLetter(dni.charAt(dni.length() - 1))) {
			// Comprueba si los primeros 8 caracteres son todos dígitos.
			for (int i = 0; i < 9; i++) {
				if (!Character.isDigit(dni.charAt(i))) {
				}
			}
			// Si los primeros 9 caracteres son todos dígitos y el �ltimo es una letra,
			// devuelve verdadero.
			return true;
		}
		// Si la longitud del String no es igual a 9 o el último caracter no es una
		// letra, devuelve falso.
		return false;
	}

	void crearListaPropuestas(List<Propuesta> listaPropuestaCrear) {

		vBox.setMaxHeight(10000);

		for (Propuesta p : listaPropuestaCrear) {
			CustomListaWebEmpleado customPane = new CustomListaWebEmpleado(p);
			vBox.getChildren().add(customPane);
		}

	}

	/**
	 * Método que da un error de alert por falta de acceso
	 */
	private void contactar() {
		alert.setHeaderText("NO ACCESS");
		alert.setContentText("No tienes acceso suficiente para ingresar aquí");
		alert.showAndWait();
	}

	private void limpiarRegistroPresupuesto() {

	}

	/**
	 * Método que limpia el registro de clientes
	 */
	private void limpiarRegistroCliente() {
		registroTxfNombre.clear();
		registroTxfCorreo.clear();
		registroTxfDireccion.clear();
		registroTxfDNI.clear();
		registroTxfTelefono.clear();
		registroTxfApellidos.clear();
	}

	/**
	 * Método que limpia el login
	 */
	void limpiarLogin() {
		tflUsername.clear();
		tflPassword.clear();
	}

}
