package hibernate.persistence.models;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "reparacion")
public class Reparacion extends AbstractEntity implements Serializable {

	@EmbeddedId
	private ReparacionId id;

	@MapsId("idVehiculo")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_vehiculo", nullable = false)
	private Vehiculo vehiculo;

	@MapsId("idMecanico")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_mecanico", nullable = false)
	private Mecanico mecanico;

	@Column(name = "tiempo_estimado", nullable = false)
	private int tiempoEstimado;

	@Column(name = "fecha_inicio")
	private LocalDate fechaInicio;

	@Column(name = "fecha_fin")
	private LocalDate fechaFin;

	@Column(name = "presupuesto_cliente")
	private Double presupuesto_cliente;

	@Column(name = "presupuesto_taller")
	private Double presupuesto_taller;

	@Column(name = "diagnostico", length = 100)
	private String diagnostico;

	public Reparacion() {
		super();
	}

	public Reparacion(Vehiculo vehiculo, Mecanico mecanico,  Double presupuesto_cliente,
			Double presupuesto_taller,  int tiempoEstimado, String diagnostico) {
		super();
		this.vehiculo = vehiculo;
		this.mecanico = mecanico;
		this.presupuesto_cliente = presupuesto_cliente;
		this.presupuesto_taller = presupuesto_taller;
		this.tiempoEstimado = tiempoEstimado;
		this.diagnostico = diagnostico;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public ReparacionId getId() {
		return id;
	}

	public void setId(ReparacionId id) {
		this.id = id;
	}

	public Mecanico getMecanico() {
		return mecanico;
	}

	public void setMecanico(Mecanico mecanico) {
		this.mecanico = mecanico;
	}

	public int getTiempoEstimado() {
		return tiempoEstimado;
	}

	public void setTiempoEstimado(int tiempoEstimado) {
		this.tiempoEstimado = tiempoEstimado;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public Double getPresupuesto_cliente() {
		return presupuesto_cliente;
	}

	public Double getPresupuesto_taller() {
		return presupuesto_taller;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public void setPresupuesto_cliente(Double presupuesto_cliente) {
		this.presupuesto_cliente = presupuesto_cliente;
	}

	public void setPresupuesto_taller(Double presupuesto_taller) {
		this.presupuesto_taller = presupuesto_taller;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	@Override
	public String toString() {
		return "Reparacion [id=" + id + ", mecanico=" + mecanico + ", vehiculo=" + vehiculo + ", tiempoEstimado="
				+ tiempoEstimado + ", fechaFin=" + fechaFin + ", precio=" + presupuesto_cliente + ", diagnostico="
				+ diagnostico + "]";
	}

}