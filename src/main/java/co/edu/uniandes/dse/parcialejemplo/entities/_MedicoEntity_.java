package co.edu.uniandes.dse.parcialejemplo.entities;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity

public class _MedicoEntity_ extends BaseEntity
{
    private String nombre;
    private String Apellido;
    private String RegistroMedico;
    private String especialidad;
    private Long id;
    
    
}
