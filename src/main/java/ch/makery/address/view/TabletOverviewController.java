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
	 * 
	 * @param event
	 */
	@FXML
	void singIn(ActionEvent event) {
		Empleado empleado;
		if (!tflUsername.getText().isEmpty() && !tflPassword.getText().isEmpty()) {
			empleado = empleadoDao.buscarPorUserAndPass(tflUsername.getText(), tflPassword.getText());

			if (empleado.equals(null)) {
				alert.setHeaderText("Informacion Iconrrecta");
				alert.setContentText("Intentelo de nuevo...");
				alert.showAndWait();
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

				resetearCamposLogIn();

				if (empleadoDao.esDirector(empleado.getId())) {
					inicioDirectorPane.setVisible(true);
					labelHiUsernameJefe.setText("Hola " + empleado.getNombre() + "!");
				} else {
					inicioEmpleadoPane.setVisible(true);
					labelHiUsernameEmpleado.setText("Hola " + empleado.getNombre() + "!");
				}
			}

		} else {
			alert.setHeaderText("Informacion Insuficiente");
			alert.setContentText("Rellene todos los datos...");
			alert.showAndWait();
		}
	}

	/**
	 * 
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

		inicioBtt.setStyle("-fx-background-color: #AB755F;");
		trabajoPendienteBtt.setStyle("-fx-background-color: #F1E7CA;");
		listaClientesBtt.setStyle("-fx-background-color: #F1E7CA;");
	}

	/**
	 * 
	 * @param event
	 */
	@FXML
	void goToInicio(MouseEvent event) {
		registroVehiculosPane.setVisible(false);
		fichaClientesPane.setVisible(false);
		trabajoPendientePane.setVisible(false);
		listaClientesPane.setVisible(false);
		textoIndicador.setText("INICIO");

		inicioBtt.setStyle("-fx-background-color: #AB755F;");
		trabajoPendienteBtt.setStyle("-fx-background-color: #F1E7CA;");
		listaClientesBtt.setStyle("-fx-background-color: #F1E7CA;");
	}

	/**
	 * 
	 * @param event
	 */
	@FXML
	void goToTrabajoPendiente(MouseEvent event) {
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
	 * 
	 * @param event
	 */
	@FXML
	void goToListaClientes(MouseEvent event) {
		crearListaClientes(listaClientes);
		fichaClientesPane.setVisible(false);
		listaClientesPane.setVisible(true);
		textoIndicador.setText("CLIENTES");

		inicioBtt.setStyle("-fx-background-color: #F1E7CA;");
		trabajoPendienteBtt.setStyle("-fx-background-color: #F1E7CA;");
		listaClientesBtt.setStyle("-fx-background-color: #AB755F;");
	}

	/**
	 * 
	 * @param event
	 */
	@FXML
	void showListaFichaClientesPane(ActionEvent event) {

		textoIndicador.setText("CLIENTES");

		listaClientesPane.setVisible(true);
		inicioBtt.setStyle("-fx-background-color: #F1E7CA;");
		trabajoPendienteBtt.setStyle("-fx-background-color: #F1E7CA;");
		listaClientesBtt.setStyle("-fx-background-color: #AB755F;");

		crearListaClientes(listaClientes);
	}

	/**
	 * 
	 * @param event
	 */
	@FXML
	void showTrabajoPendientePane(ActionEvent event) {
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
	 * 
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
	 * 
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
	 * 
	 * @param event
	 */
	@FXML
	void showClienteByTrabajoPendiente(MouseEvent event) {
		int idCliente = Integer.parseInt(fichaTrabajoPClienteId.getText());
		Cliente cliente = clienteDao.buscarPorId(idCliente);
		showFichaClienteEspecificoPane(cliente);
	}

	/**
	 * 
	 * @param event
	 */
	@FXML
	void fichaTrabajoPIniciarTrabajo(ActionEvent event) {
		Reparacion r = listaReparacionesPendientes.get(index);
		r.setFechaInicio(LocalDate.now());
		fichaTrabajoPFechaInicio.setText(r.getFechaInicio().toString());
	}

	/**
	 * 
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
	 * 
	 * @param event
	 */
	@FXML
	void forgotPassword(MouseEvent event) {
		alert.setHeaderText("CONTACTE CON SOPORTE");
		alert.setContentText("NUMERO: 657029888 \nEMAIL: mancinelly@soporteapp.com");
		alert.showAndWait();
	}

	/**
	 * 
	 * @param cliente
	 */
	void showFichaClienteEspecificoPane(Cliente cliente) {
		fichaClientesPane.setVisible(true);

		textoIndicador.setText("DATOS DEL CLIENTE");
		labelNombreApellidos.setText(cliente.getNombre() + " " + cliente.getApellido());
		labelDNI.setText(cliente.getDni());
		labelDireccion.setText(cliente.getDireccion());
		labelTelefono.setText(cliente.getTelefono().toString());

		inicioBtt.setStyle("-fx-background-color: #F1E7CA;");
		trabajoPendienteBtt.setStyle("-fx-background-color: #F1E7CA;");
		listaClientesBtt.setStyle("-fx-background-color: #AB755F;");
	}

	/**
	 * 
	 * @param event
	 */
	@FXML
	void showRegistroVehiculosPane(ActionEvent event) {
		textoIndicador.setText("REGISTRO");

		registroVehiculosPane.setVisible(true);
		inicioBtt.setStyle("-fx-background-color: #AB755F;");
		trabajoPendienteBtt.setStyle("-fx-background-color: #F1E7CA;");
		listaClientesBtt.setStyle("-fx-background-color: #F1E7CA;");
	}
	
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
	 * 
	 * @param event
	 */
	@FXML
	void addReparacion(ActionEvent event) {
		
		if(registroTrabajosVacio()) {
			if(tiempoValido()) {
				if(presupuestoValido(registroVehiculoPresupuestoCliente) && presupuestoValido(registroVehiculoPresupuestoTaller)) {

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

		reparacion = new Reparacion(vehiculo, mecanico, presupuesto_cliente, presupuesto_taller, dias, diagnostico);
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
				alert.setContentText("El tiempo debe ser un valor númerico de máximo 2 digitos");
				alert.showAndWait();
			}
		} else {
			alert.setHeaderText("ERROR");
			alert.setContentText("Rellena todos los campos");
			alert.showAndWait();
		}
	}

	private boolean registroTrabajosVacio() {
		return !registroVehiculoMatricula.getText().isEmpty() &&
			!registroVehiculoTiempo.getText().isEmpty() &&
			!registroVehiculoPresupuestoCliente.getText().isEmpty() &&
			!registroVehiculoPresupuestoTaller.getText().isEmpty() &&
			!registroVehiculoDiagnostico.getText().isEmpty() &&
			registroVehiculoChoiceBoxMecanico.getValue() != null &&
			registroVehiculoChoiceBoxCliente.getValue() != null;
	}

	/**
	 * 
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
	 * 
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
	 * 
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
	 * 
	 */
	void resetearCamposLogIn() {
		tflUsername.clear();
		tflPassword.clear();
	}

}
