package hibernate.persistence.models;

import java.io.Serializable;

import javax.persistence.*;

import hibernate.persistence.dao.EmpleadoDao;

@Entity
@Table(name = "empleado")
public class Empleado extends AbstractEntity implements Serializable {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

	@Column(name = "dni", nullable = false, length = 10)
	private String dni;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_concesionario")
	private Concesionario concesionario;

	@Column(name = "nombre", nullable = false, length = 30)
	private String nombre;

	@Column(name = "apellido", length = 50)
	private String apellido;

	@Column(name = "direccion", length = 50)
	private String direccion;

	@Column(name = "telefono", nullable = false)
	private Integer telefono;

	@Column(name = "username", nullable = false, length = 20)
	private String username;

	@Column(name = "password", nullable = false)
	private String password;
	
	
	public Empleado() {
		super();
	}
	
	public Empleado(String dni, String nombre, Integer telefono, String username, String password) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.telefono = telefono;
		this.username = username;
		this.password = password;
	}

	public Empleado(int id, String dni, Concesionario concesionario, String nombre, String apellido, String direccion,
			Integer telefono, String username, String password) {
		super();
		this.id = id;
		this.dni = dni;
		this.concesionario = concesionario;
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
		this.telefono = telefono;
		this.username = username;
		this.password = password;
	}

	public Empleado(String dni, Concesionario concesionario, String nombre, String apellido, String direccion,
			int telefono, String username, String password) {
		this.dni = dni;
		this.concesionario = concesionario;
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
		this.telefono = telefono;
		this.username = username;
		this.password = password;
	}
	
	public Empleado(String dni, String nombre, String apellido, Integer telefono, String username, String password, String direccion) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.username = username;
		this.password = password;
		this.direccion = direccion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(String id) {
		this.dni = id;
	}

	public Concesionario getConcesionario() {
		return concesionario;
	}

	public void setConcesionario(Concesionario concesionario) {
		this.concesionario = concesionario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Integer getTelefono() {
		return telefono;
	}

	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}