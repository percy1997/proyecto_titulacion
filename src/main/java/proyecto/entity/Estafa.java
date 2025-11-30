package proyecto.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "estafa")
public class Estafa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_estafa")
	private Integer codigoEstafa;
	
	@Column(name = "titulo_caso")
	private String tituloCaso;
	
	@Column(name = "descripcion_estafa")
	private String descripcionEstafa;
	
	@Column(name = "imagen_estafa")
	private String imagenEstafa;

	@Column(name = "ciberdelincuente")
	private String ciberdelincuente;
	
	@ManyToOne
	@JoinColumn(name="id_medio_estafa")
	private MedioEstafa medioEstafa;
	
	@ManyToOne
	@JoinColumn(name="id_modalidad_estafa")
	private ModalidadEstafa modalidadEstafa;
}
