package proyecto.controller;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import proyecto.entity.Estafa;
import proyecto.entity.MedioEstafa;
import proyecto.entity.ModalidadEstafa;
import proyecto.services.EstafaServices;
import proyecto.services.MedioEstafaServices;
import proyecto.services.ModalidadEstafaServices;

@Controller
@RequestMapping("dev")
public class AdminController {

	@Autowired
	private EstafaServices estafaServices;
	
	@Autowired
	private ModalidadEstafaServices modEstServices;

	@Autowired
	private MedioEstafaServices medEstServices;
	
	@GetMapping("index")
	public String index() {
		return "index";
	}
	
	@GetMapping("registrarse")
	public String registrarse() {
	    return "registrarse";
	}

	@GetMapping("registrarCiberdelito")
	public String registrarCiberdelito(Model model) {
		model.addAttribute("listadoMedio",medEstServices.listaMedioEstafas());
		model.addAttribute("listadoModalidad",modEstServices.listaModalidadEstafas());
	    return "registrarCiberdelito";
	}
	
	@PostMapping("/registrar")
	public ResponseEntity<String> registrar(
	        @RequestParam("titulo") String titulo,
	        @RequestParam("descripcion") String descripcion,
	        @RequestParam("imagen") MultipartFile archivo,  // <--- CAMBIO IMPORTANTE
	        @RequestParam("ciberdelincuente") String ciberdelincuente,
	        @RequestParam("codigoMedio") int medio,
	        @RequestParam("codigoModalidad") int modalidad
	) {

	    try {
	    	//SUBIR IMAGEN
	        String apiKey = "3d28d03bd2f3ba67b9febc7928ff0c6a";

	        // Convertir imagen a Base64
	        String imgBase64 = Base64.getEncoder().encodeToString(archivo.getBytes());

	        // URL de ImgBB
	        String url = "https://api.imgbb.com/1/upload?key=" + apiKey;

	        RestTemplate rest = new RestTemplate();

	        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
	        body.add("image", imgBase64);

	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

	        HttpEntity<MultiValueMap<String, String>> req = new HttpEntity<>(body, headers);

	        String respuesta = rest.postForObject(url, req, String.class);

	        ObjectMapper mapper = new ObjectMapper();
	        Map<String, Object> jsonMap = mapper.readValue(respuesta, Map.class);

	        Map<String, Object> data = (Map<String, Object>) jsonMap.get("data");
	        String urlImagen = data.get("url").toString();
	        //GUARDAMOS REGISTRO
	        Estafa obj = new Estafa();
	        obj.setTituloCaso(titulo);
	        obj.setDescripcionEstafa(descripcion);
	        obj.setImagenEstafa(urlImagen);
	        obj.setCiberdelincuente(ciberdelincuente);

	        MedioEstafa m = new MedioEstafa();
	        m.setCodigoMedioEstafa(medio);
	        obj.setMedioEstafa(m);

	        ModalidadEstafa mod = new ModalidadEstafa();
	        mod.setCodigoModalidadEstafa(modalidad);
	        obj.setModalidadEstafa(mod);

	        estafaServices.registrarEstafa(obj);

	        return ResponseEntity.ok("El registro se realizó correctamente");

	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(500).body("Error al registrar la estafa");
	    }
	}

	
	@GetMapping("listado")
	public String personajes(Model model) {
		model.addAttribute("listadoEstafas", estafaServices.listarEstafa());
		return "listadoCasos";
	}
	
	@GetMapping("/estafa/{id}")
	@ResponseBody
	public Estafa obtenerEstafa(@PathVariable Integer id) {
	    return estafaServices.buscarPorId(id);
	}

}