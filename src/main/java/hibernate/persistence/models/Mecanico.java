package hibernate.persistence.models;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "mecanico")
public class Mecanico extends AbstractEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, length = 10)
	private int id;

	@MapsId
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id", nullable = false)
	private Empleado empleado;

	@Column(name = "jefe_id", nullable = false, length = 10)
	private String idJefe;

	public Mecanico() {
		super();
	}

	public Mecanico(Integer id, String dni, Empleado empleado, String jefeId) {
		super();
		this.id = id;
		this.empleado = empleado;
		this.idJefe = jefeId;
	}

	public int getId() {
		return id;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public String getIdJefe() {
		return idJefe;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setIdJefe(String idJefe) {
		this.idJefe = idJefe;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	@Override
	public String toString() {
		return id + " - " + empleado.getNombre() + " " + empleado.getApellido();
	}

}