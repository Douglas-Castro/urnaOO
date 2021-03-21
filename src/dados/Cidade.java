package dados;

import java.util.ArrayList;

public class Cidade {
	// Declaracoes
	private StringBuilder nomeCidade;
	private ArrayList<Candidato> candidatosCadastrados;
	
	// Construtor
	public Cidade(StringBuilder nomeCidade) {
		this.nomeCidade = nomeCidade;
		this.candidatosCadastrados = new ArrayList<Candidato>();
	}
	
	//Assessores
	public StringBuilder getNomeCidade() {
		return nomeCidade;
	}
	public void setNomeCidade(StringBuilder nomeCidade) {
		this.nomeCidade = nomeCidade;
	}
	public ArrayList<Candidato> getCandidatosCadastrados() {
		return candidatosCadastrados;
	}
	public void setCandidatosCadastrados(ArrayList<Candidato> candidatosCadastrados) {
		this.candidatosCadastrados = candidatosCadastrados;
	}
}
