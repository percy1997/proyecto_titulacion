package proyecto.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "modalidad_estafa")
public class ModalidadEstafa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_modalidad_estafa")
	private Integer codigoModalidadEstafa;
		
	@Column(name = "nombre_modalidad_estafa")
	private String nombreModalidadEstafa;
	
	@OneToMany(mappedBy = "modalidadEstafa")
	@JsonIgnore
	List<Estafa> listaEstafas;
}
