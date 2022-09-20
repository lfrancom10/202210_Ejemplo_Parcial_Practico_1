package co.edu.uniandes.dse.parcialejemplo.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcialejemplo.entities._MedicoEntity_;
import co.edu.uniandes.dse.parcialejemplo.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialejemplo.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialejemplo.repositories._MedicoRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class _MedicoService_ 
{
    @Autowired
    _MedicoRepository medicoRepository;

    @Transactional
    public _MedicoEntity_ createMedico(_MedicoEntity_ medicoEntity) throws EntityNotFoundException, IllegalOperationException
    {
        if (medicoEntity.getId() == null)
            throw new IllegalOperationException("El Id del médico no es válido.");
            return medicoRepository.save(medicoEntity);

    }

    @Transactional
    public List<_MedicoEntity_> getMedicos()
    {
		return medicoRepository.findAll();
    }  


    @Transactional
    public _MedicoEntity_ getMedico(Long idMedico) throws EntityNotFoundException
    {
        Optional<_MedicoEntity_> medicoEntity = medicoRepository.findById(idMedico);
        if (medicoEntity.isEmpty())
            throw new EntityNotFoundException("Medico no encontrado");
        return medicoEntity.get();

    }
}
