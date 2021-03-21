package visao;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collections;

import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dados.Cidade;
import dao.CandidatoDAO;
import ordenacao.OrdenaPorLegenda;
import servicos.Servicos;

@SuppressWarnings("serial")
public class VisaoListarCandidatos extends JFrame {

	private JPanel contentPane;
	private JTable tabelaCadastros;

	public VisaoListarCandidatos(Cidade candidatos) {
		setTitle("ELEICOES 2020");
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
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setExtendedState(MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 71, 412, 151);
		contentPane.add(scrollPane);
		
		tabelaCadastros = new JTable();
		tabelaCadastros.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"NOME", "SIGLA", "LEGENDA"
			}
		));
		scrollPane.setViewportView(tabelaCadastros);
		
		candidatos.setCandidatosCadastrados(CandidatoDAO.listaDeCandidatos());
		OrdenaPorLegenda ordenacao = new OrdenaPorLegenda();
		Collections.sort(candidatos.getCandidatosCadastrados(), ordenacao);
			
		int aux;
		for(aux=0;aux<candidatos.getCandidatosCadastrados().size();aux++) {
			Object dados[]= {candidatos.getCandidatosCadastrados().get(aux).getNome().toString().toUpperCase(),candidatos.getCandidatosCadastrados().get(aux).getSigla(),candidatos.getCandidatosCadastrados().get(aux).getLegenda()};
				((DefaultTableModel) tabelaCadastros.getModel()).addRow(dados);
	
		}
		
		JLabel lblCandidatos = new JLabel("Candidatos");
		lblCandidatos.setHorizontalAlignment(SwingConstants.CENTER);
		lblCandidatos.setFont(new Font("Impact", Font.PLAIN, 25));
		lblCandidatos.setBounds(10, 11, 414, 32);
		contentPane.add(lblCandidatos);
		
		JButton btnVoltar = new JButton("");
		btnVoltar.setBackground(Color.LIGHT_GRAY);
		btnVoltar.setBounds(381, 11, 43, 41);
		btnVoltar.setToolTipText("Voltar");
		btnVoltar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				VisaoMenu frameMenu = new VisaoMenu(candidatos);
				frameMenu.setVisible(true);
				dispose();
			}
		});
		btnVoltar.setIcon(new ImageIcon(VisaoCadastroCandidato.class.getResource("/imagens/voltar.png")));
		contentPane.add(btnVoltar);
		
		JLabel lblCidade = new JLabel("Cidade: " + candidatos.getNomeCidade());
		lblCidade.setFont(new Font("Impact", Font.PLAIN, 15));
		lblCidade.setHorizontalAlignment(SwingConstants.LEFT);
		lblCidade.setBounds(12, 48, 359, 20);
		contentPane.add(lblCidade);
	}
}
