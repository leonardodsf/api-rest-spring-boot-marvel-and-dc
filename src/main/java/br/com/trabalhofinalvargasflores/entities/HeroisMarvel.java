package br.com.trabalhofinalvargasflores.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.GenerationType;


import lombok.Getter;
import lombok.Setter;

@Entity
public class HeroisMarvel {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="id_heroi_marvel")
	@Getter
	private Long id;
	
	@Getter
	@Setter
	private String nome;
	
	@Getter
	@Setter
	private String tipo;
	
	@Getter
	@Setter
	private int vida;
	
	@Getter
	@Setter
	private int habilidade;
	
	@Getter
	@Setter
	private int forca;
	
	
}
