package hibernate.persistence.models;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.*;

@Entity
@Table(name = "propuesta")
public class Propuesta extends AbstractEntity implements Serializable {

	@EmbeddedId
	private PropuestaId id;

	@MapsId("idVendedor")
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_vendedor", nullable = false)
	private Vendedor vendedor;

	@MapsId("dniCliente")
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_cliente", nullable = false)
	private Cliente cliente;

	@MapsId("idVehiculo")
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_vehiculo", nullable = false)
	private Vehiculo vehiculo;

	@Column(name = "precio", nullable = false)
	private Double precio;

	@Column(name = "vendido", nullable = false)
	private Boolean vendido = false;

	@Column(name = "detalles")
    private String detalles;

	public String getDetalles() {
		return detalles;
	}


	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}


	public Propuesta() {
		super();
	}

	
	public PropuestaId getId() {
		return id;
	}

	public void setId(PropuestaId id) {
		this.id = id;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor dniVendedor) {
		this.vendedor = dniVendedor;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Boolean getVendido() {
		return vendido;
	}

	public void setVendido(Boolean vendido) {
		this.vendido = vendido;
	}
	


}