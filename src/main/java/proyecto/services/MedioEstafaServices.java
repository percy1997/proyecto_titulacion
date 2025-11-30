package proyecto.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.dao.MedioEstafaRepository;
import proyecto.dao.ModalidadEstafaRepository;
import proyecto.entity.MedioEstafa;
import proyecto.entity.ModalidadEstafa;

@Service
public class MedioEstafaServices {
	
	@Autowired
	private MedioEstafaRepository medEstRepo;
	
	//listar
	public List<MedioEstafa> listaMedioEstafas(){
		return medEstRepo.findAll();
	}
}
