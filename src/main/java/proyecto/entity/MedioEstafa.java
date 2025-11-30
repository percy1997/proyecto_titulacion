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
@Table(name = "medio_estafa")
public class MedioEstafa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_medio_estafa")
	private Integer codigoMedioEstafa;
		
	@Column(name = "nombre_medio_estafa")
	private String nombreMedioEstafa;
	
	@OneToMany(mappedBy = "medioEstafa")
	@JsonIgnore
	List<Estafa> listaEstafas;
}
