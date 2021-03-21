package ordenacao;

import java.util.Comparator;

import dados.Candidato;

public class OrdenaPorLegenda implements Comparator<Candidato> {
	@Override
	public int compare(Candidato candidato1, Candidato candidato2) {
		if(candidato1.getLegenda() > candidato2.getLegenda())
			return 1;
		if(candidato1.getLegenda() == candidato2.getLegenda())
			return 0;
		if(candidato1.getLegenda() < candidato2.getLegenda())
			return -1;
		return 0;
	}
}
