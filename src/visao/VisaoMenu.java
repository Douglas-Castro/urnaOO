package visao;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import dados.Cidade;
import dao.CandidatoDAO;
import servicos.Servicos;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@SuppressWarnings("serial")
public class VisaoMenu extends JFrame {

	public VisaoMenu(Cidade candidatos) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int resposta = Servicos.isValidaSair(1);
				if( resposta == 0) {
					dispose();
					VisaoCadastroCidade frameCidade = new VisaoCadastroCidade();
					frameCidade.setVisible(true);
				} else if( resposta == 1)
					System.exit(0);
			}
		});
		
		
		setTitle("ELEICOES 2020");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setExtendedState(MAXIMIZED_BOTH);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 0, 1366, 250);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblEleicoes = new JLabel("ELEI\u00C7\u00D5ES 2020");
		lblEleicoes.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEleicoes.setFont(new Font("Impact", Font.BOLD, 99));
		lblEleicoes.setForeground(Color.BLACK);
		lblEleicoes.setBounds(10, 11, 1346, 239);
		panel.add(lblEleicoes);
		
		JLabel labelLogoTSE = new JLabel("");
		labelLogoTSE.setHorizontalAlignment(SwingConstants.RIGHT);
		labelLogoTSE.setBounds(148, 0, 553, 250);
		panel.add(labelLogoTSE);
		labelLogoTSE.setIcon(new ImageIcon(VisaoMenu.class.getResource("/imagens/tse-logo-preto.png")));
		
		JPanel panelBotoes = new JPanel();
		panelBotoes.setBackground(Color.GRAY);
		panelBotoes.setBounds(0, 250, 1366, 456);
		getContentPane().add(panelBotoes);
		panelBotoes.setLayout(null);
		
		JButton btnCadastrar = new JButton("");
		btnCadastrar.setIcon(new ImageIcon(VisaoMenu.class.getResource("/imagens/cadastrar.png")));
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VisaoCadastroCandidato frameCadastro = new VisaoCadastroCandidato(candidatos);
				frameCadastro.setVisible(true);
				setVisible(false);
			}
		});
		btnCadastrar.setBackground(Color.LIGHT_GRAY);
		btnCadastrar.setBounds(20, 100, 200, 200);
		panelBotoes.add(btnCadastrar);
		
		JButton btnListar = new JButton("");
		btnListar.setIcon(new ImageIcon(VisaoMenu.class.getResource("/imagens/listar.png")));
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(CandidatoDAO.isBancoVazio()) {
					VisaoListarCandidatos frameLista = new VisaoListarCandidatos(candidatos);
					frameLista.setVisible(true);
					setVisible(false);
				}
				else
					JOptionPane.showMessageDialog(null, "Nenhum candidato foi cadastrado!");
			}
		});
		btnListar.setBackground(Color.LIGHT_GRAY);
		btnListar.setBounds(299, 100, 200, 200);
		panelBotoes.add(btnListar);
		
		JButton btnPesquisar = new JButton("");
		btnPesquisar.setBackground(Color.LIGHT_GRAY);
		btnPesquisar.setIcon(new ImageIcon(VisaoMenu.class.getResource("/imagens/pesquisar.png")));
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(CandidatoDAO.isBancoVazio()) {
					VisaoPesquisa framePesquisa = new VisaoPesquisa(candidatos);
					framePesquisa.setVisible(true);
					setVisible(false);
				}
				else
					JOptionPane.showMessageDialog(null, "Nenhum candidato foi cadastrado!");
			}
		});
		btnPesquisar.setBounds(578, 100, 200, 200);
		panelBotoes.add(btnPesquisar);
		
		JButton btnConsultar = new JButton("");
		btnConsultar.setIcon(new ImageIcon(VisaoMenu.class.getResource("/imagens/consultar.png")));
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!CandidatoDAO.isBancoVazio())
					JOptionPane.showMessageDialog(null, "Nenhum candidato cadastrado!", "Cadastro vazio", JOptionPane.OK_OPTION);
				else {
					VisaoConsulta frameConsulta = new VisaoConsulta(candidatos);
					frameConsulta.setVisible(true);
					setVisible(false);
				}	
			}
		});
		btnConsultar.setBackground(Color.LIGHT_GRAY);
		btnConsultar.setBounds(857, 100, 200, 200);
		panelBotoes.add(btnConsultar);
		
		JButton btnSair = new JButton("");
		btnSair.setIcon(new ImageIcon(VisaoMenu.class.getResource("/imagens/sair.png")));
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int resposta = Servicos.isValidaSair(1);
				if( resposta == 0) {
					dispose();
					VisaoCadastroCidade frameCidade = new VisaoCadastroCidade();
					frameCidade.setVisible(true);
				} else if( resposta == 1)
					System.exit(0);
			}
		});
		btnSair.setBackground(Color.LIGHT_GRAY);
		btnSair.setBounds(1136, 100, 200, 200);
		panelBotoes.add(btnSair);
		
		JLabel lblCadastrar = new JLabel("Cadastrar");
		lblCadastrar.setForeground(Color.BLACK);
		lblCadastrar.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastrar.setFont(new Font("Impact", Font.PLAIN, 20));
		lblCadastrar.setBounds(20, 311, 200, 32);
		panelBotoes.add(lblCadastrar);
		
		JLabel lblListar = new JLabel("Listar");
		lblListar.setHorizontalAlignment(SwingConstants.CENTER);
		lblListar.setForeground(Color.BLACK);
		lblListar.setFont(new Font("Impact", Font.PLAIN, 20));
		lblListar.setBounds(299, 311, 200, 32);
		panelBotoes.add(lblListar);
		
		JLabel lblPesquisar = new JLabel("Pesquisar");
		lblPesquisar.setHorizontalAlignment(SwingConstants.CENTER);
		lblPesquisar.setForeground(Color.BLACK);
		lblPesquisar.setFont(new Font("Impact", Font.PLAIN, 20));
		lblPesquisar.setBounds(578, 311, 200, 32);
		panelBotoes.add(lblPesquisar);
		
		JLabel lblConsultar = new JLabel("Consultar");
		lblConsultar.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsultar.setForeground(Color.BLACK);
		lblConsultar.setFont(new Font("Impact", Font.PLAIN, 20));
		lblConsultar.setBounds(857, 311, 200, 32);
		panelBotoes.add(lblConsultar);
		
		JLabel lblSair = new JLabel("Sair");
		lblSair.setHorizontalAlignment(SwingConstants.CENTER);
		lblSair.setForeground(Color.BLACK);
		lblSair.setFont(new Font("Impact", Font.PLAIN, 20));
		lblSair.setBounds(1136, 311, 200, 32);
		panelBotoes.add(lblSair);
		
	}
}
