package ordenacao;

import java.util.Comparator;

import dados.Candidato;

public class OrdenaPorNome implements Comparator<Candidato>{

	@Override
	public int compare(Candidato cadastro1, Candidato cadastro2) {
		return cadastro1.getNome().compareTo(cadastro2.getNome());
	}
}
