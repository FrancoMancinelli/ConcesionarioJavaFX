package hibernate.persistence.models;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class PropuestaId extends AbstractEntity implements Serializable {
	


	private static final long serialVersionUID = -6236438910310596597L;
    
    @Column(name = "id_vendedor", nullable = false, length = 10)
    private int idVendedor;

    @Column(name = "id_cliente", nullable = false, length = 10)
    private int idCliente;

    @Column(name = "id_vehiculo", nullable = false)
    private int idVehiculo;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    public PropuestaId(int idVendedor, int idCliente, int idVehiculo) {
		super();
		this.idVendedor = idVendedor;
		this.idCliente = idCliente;
		this.idVehiculo = idVehiculo;
		this.fecha = LocalDate.now();
	}
    
    
    
    public PropuestaId() {
		super();
	}

	public int getDniVendedor() {
        return idVendedor;
    }

    public void setDniVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PropuestaId entity = (PropuestaId) o;
        return Objects.equals(this.idVendedor, entity.idVendedor) &&
                Objects.equals(this.fecha, entity.fecha) &&
                Objects.equals(this.idVehiculo, entity.idVehiculo) &&
                Objects.equals(this.idCliente, entity.idCliente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVendedor, fecha, idVehiculo, idCliente);
    }

}