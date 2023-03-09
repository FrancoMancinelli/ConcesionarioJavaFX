package ch.makery.address.view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import ch.makery.address.TabletMecanicoApp;
import hibernate.persistence.dao.ClienteDao;
import hibernate.persistence.dao.EmpleadoDao;
import hibernate.persistence.dao.MecanicoDao;
import hibernate.persistence.dao.ReparacionDao;
import hibernate.persistence.dao.VehiculoDao;
import hibernate.persistence.models.Cliente;
import hibernate.persistence.models.Empleado;
import hibernate.persistence.models.Mecanico;
import hibernate.persistence.models.Reparacion;
import hibernate.persistence.models.ReparacionId;
import hibernate.persistence.models.Vehiculo;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import utils.CustomListaClientes;
import utils.HibernateUtil;

/**
 * CONTROLADOR DE TABLET OVERVIEW | TABLET
 * 
 * @author ysuar / fmancinelli
 *
 */
public class TabletOverviewController {

	@FXML
	private Pane padrePane, loginPane, inicioEmpleadoPane, inicioDirectorPane, registroVehiculosPane, fichaClientesPane,
			trabajoPendientePane, listaClientesPane, barraInferiorPane, barraSuperiorPane, inicioBtt,
			trabajoPendienteBtt, listaClientesBtt, fichaTrabajoPClienteIdBtt;
	@FXML
	private Button singInBtt, logOutBtt, fichasClientesDireBtt, trabajoPendienteDireBtt, registrarVehiculoDireBtt,
			fichasClientesEmpBtt, TrabajoPendienteEmpBtt, registroVehiculoBotonAnadir, fichaTrabajoPIniciarBtt,
			fichaTrabajoPTerminarBtt, fichaTrabajoPSiguienteBtt, fichaTrabajoPAnteriorBtt, listaClientesBuscarBtt;
	@FXML
	private TextField tflUsername, registroVehiculoDiagnostico, registroVehiculoMatricula, registroVehiculoTiempo,
			registroVehiculoPresupuestoCliente, registroVehiculoPresupuestoTaller, fichaTrabajoPMatricula,
			fichaTrabajoPTiempo, fichaTrabajoPPresupuestoCliente, fichaTrabajoPPresupuestoTaller, fichaTrabajoPMecanico,
			fichaTrabajoPDiagnostico, fichaTrabajoPFechaFin, fichaTrabajoPFechaInicio, listaClientesBuscar;
	@FXML
	private ChoiceBox<Cliente> registroVehiculoChoiceBoxCliente = new ChoiceBox<Cliente>();
	@FXML
	private ChoiceBox<Mecanico> registroVehiculoChoiceBoxMecanico = new ChoiceBox<Mecanico>();
	@FXML
	private PasswordField tflPassword;
	@FXML
	private Label textoIndicador, labelNombreApellidos, labelDNI, labelDireccion, labelTelefono, labelVehiculo,
			labelPaginaReparaciones, labelHiUsernameEmpleado, labelHiUsernameJefe;
	@FXML
	private ImageView imageFicha;
	@FXML
	private Text fichaTrabajoPClienteId;

	@FXML
	private VBox vBoxListaClientes;

	// Componentes CSS
	private Pane paneContieneInicio2, paneCandado, paneUsuario, paneCorreo, panePass, paneContenedorInicio;

	private Label labelGoodMorning;

	// Reference to the main application.
	private TabletMecanicoApp mainApp;

	private Alert alert = new Alert(AlertType.WARNING);

	final Session session = HibernateUtil.getSessionFactory().openSession();

	private EmpleadoDao empleadoDao = new EmpleadoDao(session);
	private ClienteDao clienteDao = new ClienteDao(session);
	private MecanicoDao mecanicoDao = new MecanicoDao(session);
	private ReparacionDao reparacionDao = new ReparacionDao(session);
	private VehiculoDao vehiculoDao = new VehiculoDao(session);
	private int index = 0;

	private List<Cliente> listaClientes = new ArrayList<Cliente>();
	private List<Mecanico> listaMecanicos = new ArrayList<Mecanico>();
	private List<Reparacion> listaReparacionesPendientes = new ArrayList<Reparacion>();

	/**
	 * Initializes the controller class. This method is automatically called after
	 * the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		loginPane.setVisible(true);
		listaClientes = clienteDao.traerListaClientes();
		listaMecanicos = mecanicoDao.listarTodos();
		listaReparacionesPendientes = reparacionDao.getReparacionesPendientes();
		registroVehiculoChoiceBoxCliente.setItems(FXCollections.observableArrayList(listaClientes));
		registroVehiculoChoiceBoxMecanico.setItems(FXCollections.observableArrayList(listaMecanicos));
	}

	/**
	 * Método que controla el inicio de sesión
	 * 
	 * @param event
	 */
	@FXML
	void singIn(ActionEvent event) {
		Empleado empleado;
		// Si los campos no estan vacios...
		if (!tflUsername.getText().isEmpty() && !tflPassword.getText().isEmpty()) {
			// busca el empleado con dicho usuario y cotrasela
			empleado = empleadoDao.buscarPorUserAndPass(tflUsername.getText(), tflPassword.getText());

			// Si no existe ninguno, avisa
			if (empleado.equals(null)) {
				alert.setHeaderText("Informacion Iconrrecta");
				alert.setContentText("Intentelo de nuevo...");
				alert.showAndWait();
			// Si no existe nos da la bienvenida y se nos hacen visibles distintas opciones
			} else {
				alert.setHeaderText("Usuario Correcto");
				alert.setContentText("Bienvenid@ " + empleado.getNombre() + " " + empleado.getApellido());
				alert.showAndWait();

				loginPane.setVisible(false);
				logOutBtt.setVisible(true);
				inicioBtt.setVisible(true);
				trabajoPendienteBtt.setVisible(true);
				listaClientesBtt.setVisible(true);
				textoIndicador.setVisible(true);
				inicioBtt.setVisible(true);
				trabajoPendienteBtt.setVisible(true);
				listaClientesBtt.setVisible(true);

				inicioBtt.setStyle("-fx-background-color: #AB755F;");
				trabajoPendienteBtt.setStyle("-fx-background-color: #F1E7CA;");
				listaClientesBtt.setStyle("-fx-background-color: #F1E7CA;");

				//Vacia los campos del login para cuando se vuelva a empezar
				resetearCamposLogIn();

				//Se comprueba si el empleado logeado es un direcctor, en ese caso tienen distintos menus
				if (empleadoDao.esDirector(empleado.getId())) {
					inicioDirectorPane.setVisible(true);
					labelHiUsernameJefe.setText("Hola " + empleado.getNombre() + "!");
				} else {
					inicioEmpleadoPane.setVisible(true);
					labelHiUsernameEmpleado.setText("Hola " + empleado.getNombre() + "!");
				}
			}

		//Aviso que faltan datos
		} else {
			alert.setHeaderText("Informacion Insuficiente");
			alert.setContentText("Rellene todos los datos...");
			alert.showAndWait();
		}
	}

	/**
	 * Metodo para cerrar sesion
	 * @param event
	 */
	@FXML
	void logOut(ActionEvent event) {

		inicioEmpleadoPane.setVisible(false);
		inicioDirectorPane.setVisible(false);
		registroVehiculosPane.setVisible(false);
		fichaClientesPane.setVisible(false);
		trabajoPendientePane.setVisible(false);
		listaClientesPane.setVisible(false);
		loginPane.setVisible(true);
		textoIndicador.setVisible(false);
		inicioBtt.setVisible(false);
		trabajoPendienteBtt.setVisible(false);
		listaClientesBtt.setVisible(false);
		inicioBtt.setVisible(false);
		trabajoPendienteBtt.setVisible(false);
		listaClientesBtt.setVisible(false);
		logOutBtt.setVisible(false);
		
		//Cambiamos de color los accesos directos
		inicioBtt.setStyle("-fx-background-color: #AB755F;");
		trabajoPendienteBtt.setStyle("-fx-background-color: #F1E7CA;");
		listaClientesBtt.setStyle("-fx-background-color: #F1E7CA;");
	}

	/**
	 * Lleva al Pane de inicio
	 * @param event
	 */
	@FXML
	void goToInicio(MouseEvent event) {
		registroVehiculosPane.setVisible(false);
		fichaClientesPane.setVisible(false);
		trabajoPendientePane.setVisible(false);
		listaClientesPane.setVisible(false);
		textoIndicador.setText("INICIO");

		//Cambiamos de color los accesos directos

		inicioBtt.setStyle("-fx-background-color: #AB755F;");
		trabajoPendienteBtt.setStyle("-fx-background-color: #F1E7CA;");
		listaClientesBtt.setStyle("-fx-background-color: #F1E7CA;");
	}

	/**
	 * Lleva al pane de trabajo pendiente
	 * @param event
	 */
	@FXML
	void goToTrabajoPendiente(MouseEvent event) {
		//Un pane dinamico que trae desde la bbdd todas las reparaciones que no estan finalizadas y las muestra
		if (listaReparacionesPendientes.size() > 0) {
			labelPaginaReparaciones.setText((index + 1) + " - " + listaReparacionesPendientes.size());
			textoIndicador.setText("TRABAJO PENDIENTE");

			Reparacion r = listaReparacionesPendientes.get(index);
			LocalDate fechaInicio = r.getFechaInicio();
			LocalDate fechaFin = r.getFechaFin();

			fichaTrabajoPMatricula.setText(r.getVehiculo().getMatricula());
			fichaTrabajoPPresupuestoCliente.setText(r.getPresupuesto_cliente().toString());
			fichaTrabajoPTiempo.setText(String.valueOf(r.getTiempoEstimado()));
			fichaTrabajoPPresupuestoTaller.setText(r.getPresupuesto_taller().toString());
			fichaTrabajoPMecanico.setText(r.getMecanico().toString());
			fichaTrabajoPDiagnostico.setText(r.getDiagnostico());

			//La fecha inicio y fecha fin, tienen su propio controlador
			// No se puede finalizar un trabajo que no se ha iniciado
			if (fechaInicio != null) {
				fichaTrabajoPFechaInicio.setText(fechaInicio.toString());
				if (fechaFin != null) {
					fichaTrabajoPFechaInicio.setText(fechaFin.toString());
				}
			} else {
				fichaTrabajoPFechaInicio.setText("POR DETERMINAR");
				fichaTrabajoPFechaFin.setText("POR DETERMINAR");
			}

			trabajoPendientePane.setVisible(true);
			fichaClientesPane.setVisible(false);
			listaClientesPane.setVisible(false);

			//Cambiamos de color los accesos directos
			inicioBtt.setStyle("-fx-background-color: #F1E7CA;");
			trabajoPendienteBtt.setStyle("-fx-background-color: #AB755F;");
			listaClientesBtt.setStyle("-fx-background-color: #F1E7CA;");
		} else {
			alert.setHeaderText("ERROR");
			alert.setContentText("No hay trabajo pendiente...\n Buen trabajo!");
			alert.showAndWait();
		}

	}

	/**
	 * Lleva al pane de lista de clienes
	 * @param event
	 */
	@FXML
	void goToListaClientes(MouseEvent event) {
		crearListaClientes(listaClientes);
		fichaClientesPane.setVisible(false);
		listaClientesPane.setVisible(true);
		textoIndicador.setText("CLIENTES");

		//Cambiamos de color los accesos directos
		inicioBtt.setStyle("-fx-background-color: #F1E7CA;");
		trabajoPendienteBtt.setStyle("-fx-background-color: #F1E7CA;");
		listaClientesBtt.setStyle("-fx-background-color: #AB755F;");
	}

	/**
	 * Muestra el pane de lista de clientes
	 * @param event
	 */
	@FXML
	void showListaFichaClientesPane(ActionEvent event) {

		textoIndicador.setText("CLIENTES");

		listaClientesPane.setVisible(true);
		
		//Cambiamos de color los accesos directos
		inicioBtt.setStyle("-fx-background-color: #F1E7CA;");
		trabajoPendienteBtt.setStyle("-fx-background-color: #F1E7CA;");
		listaClientesBtt.setStyle("-fx-background-color: #AB755F;");

		crearListaClientes(listaClientes);
	}

	/**
	 * Muestra el pane trabajo pendiente
	 * @param event
	 */
	@FXML
	void showTrabajoPendientePane(ActionEvent event) {
		//Si existen reparaciones no finalizadas para a mostrar la vista
		if (listaReparacionesPendientes.size() > 0) {
			//Mediante unos text field dinamicos mostramos los datos de cada reparacion gracias al index
			//Y los botones < >, (lista circular)
			labelPaginaReparaciones.setText((index + 1) + " - " + listaReparacionesPendientes.size());
			textoIndicador.setText("TRABAJO PENDIENTE");
			
			Reparacion r = listaReparacionesPendientes.get(index);
			LocalDate fechaInicio = r.getFechaInicio();
			LocalDate fechaFin = r.getFechaFin();

			fichaTrabajoPMatricula.setText(r.getVehiculo().getMatricula());
			fichaTrabajoPPresupuestoCliente.setText(r.getPresupuesto_cliente().toString());
			fichaTrabajoPTiempo.setText(String.valueOf(r.getTiempoEstimado()));
			fichaTrabajoPPresupuestoTaller.setText(r.getPresupuesto_taller().toString());
			fichaTrabajoPMecanico.setText(r.getMecanico().toString());
			fichaTrabajoPDiagnostico.setText(r.getDiagnostico());
			fichaTrabajoPClienteId.setText(String.valueOf(r.getVehiculo().getCliente().getId()));

			if (fechaInicio != null) {
				fichaTrabajoPFechaInicio.setText(fechaInicio.toString());
				if (fechaFin != null) {
					fichaTrabajoPFechaInicio.setText(fechaFin.toString());
				}
			} else {
				fichaTrabajoPFechaInicio.setText("POR DETERMINAR");
				fichaTrabajoPFechaFin.setText("POR DETERMINAR");
			}

			trabajoPendientePane.setVisible(true);
			fichaClientesPane.setVisible(false);
			listaClientesPane.setVisible(false);

			//Cambiamos de color los accesos directos
			inicioBtt.setStyle("-fx-background-color: #F1E7CA;");
			trabajoPendienteBtt.setStyle("-fx-background-color: #AB755F;");
			listaClientesBtt.setStyle("-fx-background-color: #F1E7CA;");
		//En el caso contrario, señana que no hay reparaciones pendientes
		} else {
			
			alert.setHeaderText("ERROR");
			alert.setContentText("No hay trabajo pendiente...\n Buen trabajo!");
			alert.showAndWait();
		}
	}

	/**
	 * Pasa al siguiente en trabajo pendiente
	 * @param event
	 */
	@FXML
	void siguienteTrabajoPendiente(ActionEvent event) {

		if (index == listaReparacionesPendientes.size() - 1) {
			index = 0;
		} else {
			index++;
		}
		showTrabajoPendientePane(event);
	}

	/**
	 * Pasa al anterior en trabajo pendiente
	 * @param event
	 */
	@FXML
	void anteriorTrabajoPendiente(ActionEvent event) {

		if (index != 0) {
			index--;
		} else {
			index = listaReparacionesPendientes.size() - 1;
		}
		showTrabajoPendientePane(event);

	}

	/**
	 * Pertenece a los botones individuales de la lista de clientes, da paso a la ficha especifica de
	 * cada cliente
	 * @param event
	 */
	@FXML
	void showClienteByTrabajoPendiente(MouseEvent event) {
		int idCliente = Integer.parseInt(fichaTrabajoPClienteId.getText());
		Cliente cliente = clienteDao.buscarPorId(idCliente);
		showFichaClienteEspecificoPane(cliente);
	}

	/**
	 * Setea la fecha inicio de la reparacion 
	 * @param event
	 */
	@FXML
	void fichaTrabajoPIniciarTrabajo(ActionEvent event) {
		Reparacion r = listaReparacionesPendientes.get(index);
		r.setFechaInicio(LocalDate.now());
		fichaTrabajoPFechaInicio.setText(r.getFechaInicio().toString());
	}

	/**
	 * Setea la fecha fin de la reparacion 
	 * @param event
	 */
	@FXML
	void fichaTrabajoPTerminarTrabajo(ActionEvent event) {

		Reparacion r = listaReparacionesPendientes.get(index);
		if (r.getFechaInicio() != null) {
			r.setFechaFin(LocalDate.now());
			fichaTrabajoPFechaFin.setText(r.getFechaFin().toString());
		} else {
			alert.setHeaderText("ERROR");
			alert.setContentText("No se puede terminar un trabajo no empezado...");
			alert.showAndWait();
		}

	}

	/**
	 * Metodo que informa donde puedes cambiar tu contraseña
	 * @param event
	 */
	@FXML
	void forgotPassword(MouseEvent event) {
		alert.setHeaderText("ERROR - CONTACTE CON SOPORTE");
		alert.setContentText("NUMERO: 657029888 \nEMAIL: mancinelly@soporteapp.com");
		alert.showAndWait();
	}

	/**
	 * Ficha personal de cada cliente
	 * @param cliente
	 */
	void showFichaClienteEspecificoPane(Cliente cliente) {
		fichaClientesPane.setVisible(true);

		textoIndicador.setText("DATOS DEL CLIENTE");
		labelNombreApellidos.setText(cliente.getNombre() + " " + cliente.getApellido());
		labelDNI.setText(cliente.getDni());
		labelDireccion.setText(cliente.getDireccion());
		labelTelefono.setText(cliente.getTelefono().toString());

		//Cambiamos de color los accesos directos
		inicioBtt.setStyle("-fx-background-color: #F1E7CA;");
		trabajoPendienteBtt.setStyle("-fx-background-color: #F1E7CA;");
		listaClientesBtt.setStyle("-fx-background-color: #AB755F;");
	}

	/**
	 * Muestra el pane del registro de vehiculos
	 * @param event
	 */
	@FXML
	void showRegistroVehiculosPane(ActionEvent event) {
		textoIndicador.setText("REGISTRO");

		registroVehiculosPane.setVisible(true);
		
		//Cambiamos de color los accesos directos
		inicioBtt.setStyle("-fx-background-color: #AB755F;");
		trabajoPendienteBtt.setStyle("-fx-background-color: #F1E7CA;");
		listaClientesBtt.setStyle("-fx-background-color: #F1E7CA;");
	}

	/**
	 * Controlador del campo tiempo
	 * @return
	 */
	private boolean tiempoValido() {
		String tiempo = registroVehiculoTiempo.getText();
		if (tiempo.length() > 2) {
			return false;
		}
		for (int i = 0; i < tiempo.length(); i++) {
			if (!Character.isDigit(tiempo.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Controlador del presupuesto
	 * @param txf
	 * @return
	 */
	private boolean presupuestoValido(TextField txf) {
		String presupuesto = txf.getText();

		for (int i = 0; i < presupuesto.length(); i++) {
			if (!Character.isDigit(presupuesto.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Añade la reparacion a la bbdd con los distintos controles de campos y sus respectivos alerts
	 * @param event
	 */
	@FXML
	void addReparacion(ActionEvent event) {

		if (registroTrabajosVacio()) {
			if (tiempoValido()) {
				if (presupuestoValido(registroVehiculoPresupuestoCliente)
						&& presupuestoValido(registroVehiculoPresupuestoTaller)) {

					Vehiculo vehiculo;
					Mecanico mecanico = new Mecanico();
					Cliente cliente = new Cliente();
					Reparacion reparacion = new Reparacion();
					ReparacionId r = new ReparacionId();

					String matricula = registroVehiculoMatricula.getText();
					int dias = Integer.parseInt(registroVehiculoTiempo.getText());
					double presupuesto_cliente = Double.parseDouble(registroVehiculoPresupuestoCliente.getText());
					double presupuesto_taller = Double.parseDouble(registroVehiculoPresupuestoTaller.getText());
					String diagnostico = registroVehiculoDiagnostico.getText();

					mecanico = registroVehiculoChoiceBoxMecanico.getSelectionModel().getSelectedItem();
					cliente = registroVehiculoChoiceBoxCliente.getSelectionModel().getSelectedItem();
					vehiculo = new Vehiculo(cliente, matricula);
					vehiculoDao.insert(vehiculo);
					vehiculo = vehiculoDao.traerUltimoVehiculo();

					reparacion = new Reparacion(vehiculo, mecanico, presupuesto_cliente, presupuesto_taller, dias,
							diagnostico);
					r = new ReparacionId(vehiculo.getId(), mecanico.getId());
					reparacion.setId(r);
					reparacionDao.insert(reparacion);

					resetearCamposRegistroVehiculosPane();

					registroVehiculosPane.setVisible(false);
					listaReparacionesPendientes = reparacionDao.getReparacionesPendientes();
					showTrabajoPendientePane(event);
				} else {
					alert.setHeaderText("ERROR");
					alert.setContentText("Los presupuestos deben ser valores numericos");
					alert.showAndWait();
				}
			} else {
				alert.setHeaderText("ERROR");
				alert.setContentText("El tiempo debe ser un valor nÃºmerico de mÃ¡ximo 2 digitos");
				alert.showAndWait();
			}
		} else {
			alert.setHeaderText("ERROR");
			alert.setContentText("Rellena todos los campos");
			alert.showAndWait();
		}
	}

	/**
	 * Comprueba que lso campos no esten vacios
	 * @return
	 */
	private boolean registroTrabajosVacio() {
		return !registroVehiculoMatricula.getText().isEmpty() && !registroVehiculoTiempo.getText().isEmpty()
				&& !registroVehiculoPresupuestoCliente.getText().isEmpty()
				&& !registroVehiculoPresupuestoTaller.getText().isEmpty()
				&& !registroVehiculoDiagnostico.getText().isEmpty()
				&& registroVehiculoChoiceBoxMecanico.getValue() != null
				&& registroVehiculoChoiceBoxCliente.getValue() != null;
	}

	/**
	 * Barra de buscar Clientes
	 * @param event
	 */
	@FXML
	void listaClientesBuscar(ActionEvent event) {
		String busqueda = listaClientesBuscar.getText();
		List<Cliente> listaClientesBuscado = new ArrayList<Cliente>();
		for (Cliente cliente : listaClientes) {
			if (cliente.getNombre().toUpperCase().contains(busqueda.toUpperCase())) {
				listaClientesBuscado.add(cliente);
			}
		}

		crearListaClientes(listaClientesBuscado);
	}

	/**
	 * Crea la lista de los clientes con sus label y botones
	 */
	void crearListaClientes(List<Cliente> listaClientes) {

		vBoxListaClientes.getChildren().clear();

		if (listaClientes.size() > 10) {
			vBoxListaClientes.setPrefHeight(75 * listaClientes.size());
		}

		for (Cliente cliente : listaClientes) {

			CustomListaClientes customLista = new CustomListaClientes(cliente);
			vBoxListaClientes.getChildren().add(customLista);
			Button btn = customLista.getButton();

			btn.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					showFichaClienteEspecificoPane(cliente);
				}
			});
		}
	}

	/**
	 * Vacia campos
	 */
	void resetearCamposRegistroVehiculosPane() {
		registroVehiculoMatricula.clear();
		registroVehiculoMatricula.clear();
		registroVehiculoTiempo.clear();
		registroVehiculoPresupuestoCliente.clear();
		registroVehiculoPresupuestoTaller.clear();
		registroVehiculoDiagnostico.clear();
		registroVehiculoChoiceBoxCliente.setValue(null);
		registroVehiculoChoiceBoxMecanico.setValue(null);

	}

	/**
	 * 	 * Vacia campos

	 */
	void resetearCamposLogIn() {
		tflUsername.clear();
		tflPassword.clear();
	}

}
