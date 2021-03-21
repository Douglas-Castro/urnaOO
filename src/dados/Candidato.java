package dados;

public class Candidato {
	// Declaracoes
	private String nome;
	private String sigla;
	private Integer legenda;

	// Construtor
	public Candidato(String nome, String sigla, Integer legenda) {
		this.nome = nome;
		this.sigla = sigla;
		this.legenda = legenda;
	}

	// Assessores
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public Integer getLegenda() {
		return legenda;
	}

	public void setLegenda(Integer legenda) {
		this.legenda = legenda;
	}
}
