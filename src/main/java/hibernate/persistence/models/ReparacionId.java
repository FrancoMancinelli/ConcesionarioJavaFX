package hibernate.persistence.models;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class ReparacionId implements Serializable {

	@Column(name = "id_vehiculo", nullable = false)
	private int idVehiculo;

	@Column(name = "id_mecanico", nullable = false)
	private int idMecanico;

	@Column(name = "fecha_registro", nullable = false)
	private LocalDate fechaRegistro = LocalDate.now();

	public ReparacionId() {
		super();
	}

	public ReparacionId(int idVehiculo, int idMecanico) {
		this.idMecanico = idMecanico;
		this.idVehiculo = idVehiculo;

	}

	public int getIdVehiculo() {
		return idVehiculo;
	}

	public int getIdMecanico() {
		return idMecanico;
	}

	public void setIdVehiculo(int idVehiculo) {
		this.idVehiculo = idVehiculo;
	}

	public void setIdMecanico(int idMecanico) {
		this.idMecanico = idMecanico;
	}

	public LocalDate getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(LocalDate fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
			return false;
		ReparacionId entity = (ReparacionId) o;
		return Objects.equals(getIdVehiculo(), entity.getIdVehiculo())
				&& Objects.equals(getIdMecanico(), entity.getIdMecanico())
				&& Objects.equals(getFechaRegistro(), entity.getFechaRegistro());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getIdVehiculo(), getIdMecanico(), getFechaRegistro());
	}

	@Override
	public String toString() {
		return "ReparacionId {\n" + "idVehiculo=" + idVehiculo + "\nidMecanico=" + idMecanico + "\nfechaInicio="
				+ fechaRegistro + "\n}";
	}

}