package hibernate.persistence.models;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MecanicoEspecialidadId implements Serializable {
	
    private static final long serialVersionUID = 1892733665972165222L;
    @Column(name = "id_mecanico", nullable = false, length = 10)
    private String mecanico;

    @Column(name = "id_especialidad", nullable = false)
    private Integer idEspecialidad;

    public String getMecanico() {
        return mecanico;
    }

    public void setMecanico(String mecanico) {
        this.mecanico = mecanico;
    }

    public Integer getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(Integer idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MecanicoEspecialidadId entity = (MecanicoEspecialidadId) o;
        return Objects.equals(this.mecanico, entity.mecanico) &&
                Objects.equals(this.idEspecialidad, entity.idEspecialidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mecanico, idEspecialidad);
    }

}