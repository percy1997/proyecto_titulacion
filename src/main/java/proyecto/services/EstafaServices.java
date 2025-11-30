package proyecto.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.dao.EstafaRepository;
import proyecto.entity.Estafa;

@Service
public class EstafaServices {
	
	@Autowired
	private EstafaRepository estafaRepo;
	
	
	//listar
	public List<Estafa> listarEstafa(){
		return estafaRepo.findAll();
	}
	
	public Estafa registrarEstafa(Estafa p) {
	    return estafaRepo.save(p);
	}
	
    // Buscar estafa por ID
    public Estafa buscarPorId(Integer id) {
        return estafaRepo.findById(id).orElse(null);
    }
}
