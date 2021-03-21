package servicos;

import javax.swing.JOptionPane;

import dados.Candidato;
import dao.CandidatoDAO;
import validacao.Validacao;

public class Servicos {
	public static boolean isValidaString(String str) {
		return (str.trim().length() > 2) ? true : false;
	}
	
	public static boolean isValidaSigla(String str) {
		return (str.trim().length() > 1) ? true : false;
	}
	
	public static boolean isValidaLegenda(int legenda) {
		final int MINIMO = 10;
		
		return (legenda > MINIMO && legenda < 100) ? true : false;
	}

	public static void cadastrarBD(String nome, String sigla, int legenda) {
		Candidato candidato = new Candidato(nome, sigla, legenda);
		
		CandidatoDAO candidatoDao = new CandidatoDAO();
		
		candidatoDao.cadastrar(candidato);
	}
	
	public static int isValidaSair(int opcao) {
		if(opcao == 0) {
			if(!CandidatoDAO.isBancoVazio())
				JOptionPane.showMessageDialog(null, "Voce precisa cadastrar o nome da cidade!", "Campo vazio",JOptionPane.ERROR_MESSAGE);
		} else if(opcao == 1) {
			if(!CandidatoDAO.isBancoVazio()) {
				JOptionPane.showMessageDialog(null, "Voce precisa cadastrar pelo menos um candidato!", "Cadastro vazio",JOptionPane.ERROR_MESSAGE);
			}else {
				if(Validacao.validaSair() == 0) {
					CandidatoDAO.limpaBanco();
					
					if(Validacao.validaContinua()) {
						return 0;
					} else
						return 1;
				}
			}
		}
		
		return -1;
	}
}
