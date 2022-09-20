package co.edu.uniandes.dse.parcialejemplo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.uniandes.dse.parcialejemplo.entities._MedicoEntity_;


@Repository
public interface _MedicoRepository extends JpaRepository<_MedicoEntity_, Long>
{
    List<_MedicoEntity_> FindByIdMedico (Long idMedico);
}
