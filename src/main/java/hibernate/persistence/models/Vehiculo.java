package hibernate.persistence.models;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "vehiculo")
public class Vehiculo extends AbstractEntity implements Serializable {

	@Id
	@Column(name = "id", nullable = false)
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_concesionario")
	private Concesionario concesionario;

	@Column(name = "matricula", length = 15)
	private String matricula = "0";

	@Column(name = "modelo", length = 50)
	private String modelo;

	@Column(name = "color", length = 50)
	private String color;

	@Column(name = "precio")
	private Double precio;

	@Column(name = "tipo", length = 20)
	private String tipo;

	public Vehiculo() {
		super();
	}

	public Vehiculo(Cliente cliente, String matricula) {
		super();
		this.cliente = cliente;
		this.matricula = matricula;
	}

	public Vehiculo(int id, Cliente cliente, Concesionario concesionario, String matricula, String modelo, String color,
			Double precio, String tipo) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.concesionario = concesionario;
		this.matricula = matricula;
		this.modelo = modelo;
		this.color = color;
		this.precio = precio;
		this.tipo = tipo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Concesionario getIdConcesionario() {
		return concesionario;
	}

	public void setIdConcesionario(Concesionario idConcesionario) {
		this.concesionario = idConcesionario;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setCliente(Cliente Cliente) {
		this.cliente = Cliente;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Vehiculo [id=" + id + ", idCliente=" + cliente + ", idConcesionario=" + concesionario + ", matricula="
				+ matricula + ", modelo=" + modelo + ", color=" + color + ", precio=" + precio + ", tipo=" + tipo + "]";
	}

}