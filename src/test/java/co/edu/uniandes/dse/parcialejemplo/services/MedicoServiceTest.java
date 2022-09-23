package co.edu.uniandes.dse.parcialejemplo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.h2.command.dml.Insert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.uniandes.dse.parcialejemplo.entities._MedicoEntity_;
import co.edu.uniandes.dse.parcialejemplo.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialejemplo.exceptions.IllegalOperationException;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;



@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
@Import(_MedicoService_.class)


class MedicoServiceTest
{
    @Autowired
    private _MedicoService_ medicoService;

    @Autowired
    private TestEntityManager entityManager;

    private PodamFactory factory = new PodamFactoryImpl();

    private List<_MedicoEntity_> medicoList = new ArrayList<>();
    
    @BeforeEach
    void setUp()
    {
        clearData();
        insertData();
    }

    private void clearData()
    {
        entityManager.getEntityManager().createQuery("delete from _MedicoEntity_");

    }

    private void insertData()
    {
        for (int i = 0; i < 3; i++)
            {
                _MedicoEntity_ medicoEntity = factory.manufacturePojo(_MedicoEntity_.class);
                entityManager.persist(medicoEntity);
                medicoList.add(medicoEntity);
            }
        
    }   
            
    
    @Test
    void testCreateMedico() throws EntityNotFoundException, IllegalOperationException
    {
        System.out.println("ENTRA");
        _MedicoEntity_ newEntity = factory.manufacturePojo(_MedicoEntity_.class);
        newEntity.setId((long) 1);
        _MedicoEntity_ result = medicoService.createMedico(newEntity);
        assertNotNull(result);
        _MedicoEntity_ entity = entityManager.find(_MedicoEntity_.class, result.getId());
        assertEquals(newEntity.getId(), entity.getId());
        assertEquals(newEntity.getNombre(), entity.getNombre());
        assertEquals(newEntity.getApellido(), entity.getApellido());
        assertEquals(newEntity.getEspecialidad(), entity.getEspecialidad());
        assertEquals(newEntity.getRegistroMedico(), entity.getRegistroMedico());
    }

    @Test
    void testGetMedicos()
    {
        List<_MedicoEntity_> list = medicoService.getMedicos();
        assertEquals(medicoList.size(), list.size());
        for (_MedicoEntity_ entity : list)
        {
            boolean found = false;
            for (_MedicoEntity_ storedEntity : medicoList)
            {
                if (entity.getId().equals(storedEntity.getId()))
                {
                    found = true;
                }

            }
            assertTrue(found);
        }
    }
    /* 
    @Test
    void testGetMedico() throws EntityNotFoundException
    {
        _MedicoEntity_ entity = medicoList.get(0);
        _MedicoEntity_ resultEntity = medicoService.getMedico(entity.getId());
        assertNotNull(resultEntity);
        assertEquals(entity.getId(), resultEntity.getId());
        assertEquals(entity.getNombre(), resultEntity.getNombre());
        assertEquals(entity.getApellido(), resultEntity.getApellido());
        assertEquals(entity.getEspecialidad(), resultEntity.getEspecialidad());
        assertEquals(entity.getRegistroMedico(), resultEntity.getRegistroMedico());
    }

    @Test
    void testUpdateMedico() throws EntityNotFoundException, IllegalOperationException
    {
        _MedicoEntity_ entity = medicoList.get(0);
        _MedicoEntity_ pojoEntity = factory.manufacturePojo(_MedicoEntity_.class);
        pojoEntity.setId(entity.getId());
        medicoService.updateMedico(entity.getId(), pojoEntity);

        _MedicoEntity_ resp = entityManager.find(_MedicoEntity_.class, entity.getId());
        assertEquals(pojoEntity.getId(), resp.getId());
        assertEquals(pojoEntity.getNombre(), resp.getNombre());
        assertEquals(pojoEntity.getApellido(), resp.getApellido());
        assertEquals(pojoEntity.getEspecialidad(), resp.getEspecialidad());
        assertEquals(pojoEntity.getRegistroMedico(), resp.getRegistroMedico());

    }

    @Test
    void testDeleteMedico() throws EntityNotFoundException, IllegalOperationException
    {
        _MedicoEntity_ entity = medicoList.get(1);
        medicoService.deleteMedico(entity.getId());
        _MedicoEntity_ deleted = entityManager.find(_MedicoEntity_.class, entity.getId());
        assertNull(deleted);
    }
    */
}
