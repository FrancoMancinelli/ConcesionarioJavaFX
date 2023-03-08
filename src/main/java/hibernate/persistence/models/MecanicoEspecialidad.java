package hibernate.persistence.models;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "mecanico_especialidad")
public class MecanicoEspecialidad extends AbstractEntity implements Serializable {
	
    @EmbeddedId
    private MecanicoEspecialidadId id;

    @MapsId("idMecanico")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_mecanico", nullable = false)
    private Mecanico dniMecanico;

    @MapsId("idEspecialidad")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_especialidad", nullable = false)
    private Especialidad idEspecialidad;

    public MecanicoEspecialidadId getId() {
        return id;
    }

    public void setId(MecanicoEspecialidadId id) {
        this.id = id;
    }

    public Mecanico getDniMecanico() {
        return dniMecanico;
    }

    public void setDniMecanico(Mecanico dniMecanico) {
        this.dniMecanico = dniMecanico;
    }

    public Especialidad getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(Especialidad idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

}