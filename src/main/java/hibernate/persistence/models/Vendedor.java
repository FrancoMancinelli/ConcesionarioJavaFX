package hibernate.persistence.models;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "vendedor")
public class Vendedor extends AbstractEntity implements Serializable {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, length = 10)
    private int id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id", nullable = false)
    private Empleado empleado;
    
	public Vendedor() {
		super();
	}
	
    public Vendedor(Integer id, String dni, Empleado empleado) {
		super();
		this.id = id;
		this.empleado = empleado;
	}

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

}