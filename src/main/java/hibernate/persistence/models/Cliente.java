package hibernate.persistence.models;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "cliente")
public class Cliente extends AbstractEntity implements Serializable {
	
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

	public Cliente() {
		super();
	}
	
    public Cliente(String dni, String nombre, Integer telefono) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.telefono = telefono;
	}


	public Cliente(String dni, Concesionario concesionario, String nombre, String apellido, String direccion,
			Integer telefono) {
		super();
		this.dni = dni;
		this.concesionario = concesionario;
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
		this.telefono = telefono;
	}

	public Integer getId() {
		return id;
	}

	public Concesionario getConcesionario() {
		return concesionario;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setConcesionario(Concesionario concesionario) {
		this.concesionario = concesionario;
	}

	public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Concesionario getIdConcesionario() {
        return concesionario;
    }

    public void setIdConcesionario(Concesionario idConcesionario) {
        this.concesionario = idConcesionario;
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
    
	@Override
	public String toString() {
		return id + " - " + nombre + " " + apellido;
	}

}