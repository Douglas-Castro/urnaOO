package validacao;

import javax.swing.JOptionPane;

import dao.CandidatoDAO;
import servicos.Servicos;

public class Validacao {
	
	public static boolean validaNome(String nome) {
		if(!Servicos.isValidaString(nome)) {
			JOptionPane.showMessageDialog(null, "Nome invalido!", "Dados incorretos", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		else
			return true;
	}
	
	public static StringBuilder nomeCidade(String nome) {
		StringBuilder nomeCidade = new StringBuilder();
		
		return nomeCidade.append(nome);
	}
	
	public static boolean validaSigla(String sigla) {
		if(!Servicos.isValidaSigla(sigla)) {
			JOptionPane.showMessageDialog(null, "Sigla invalida!", "Dado incorretos", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		else {
			if (CandidatoDAO.siglaExiste(sigla)) {
				JOptionPane.showMessageDialog(null, "Essa sigla ja foi cadastrada!", "Dado repetido", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		return true;
	}
	
	public static boolean validaLegenda(String stringLegenda) {
			int legenda = 	Integer.parseInt(stringLegenda);
			
			if(!Servicos.isValidaLegenda(legenda)) {
				JOptionPane.showMessageDialog(null, "Legenda invalida!", "Dado incorretos", JOptionPane.ERROR_MESSAGE);
				return false;
			}else {
				if (CandidatoDAO.legendaExiste(legenda)) {
					JOptionPane.showMessageDialog(null, "Essa legenda ja foi cadastrada!", "Dado repetido", JOptionPane.ERROR_MESSAGE);
					return false;
				}
			}
		
		return true;
	}
	
	public static int validaSair() {
		int resposta;
		
		do {
			 resposta = JOptionPane.showConfirmDialog(null, "Todo o cadastro sera perdido! Deseja realmente sair?", "ATENCAO", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		} while (resposta != 0 && resposta != 1);
		
		return resposta;
	}
	
	public static boolean validaContinua() {
		int resposta;
		
		do {
			 resposta = JOptionPane.showConfirmDialog(null, "Deseja cadastrar outra cidade?", "Sistema Informa", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
		} while (resposta != 0 && resposta != 1);
		
		if(resposta == 0)
			return true;
		else 
			return false;
	}
}
