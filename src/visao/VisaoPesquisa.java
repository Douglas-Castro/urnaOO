package visao;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dados.Candidato;
import dados.Cidade;
import dao.CandidatoDAO;
import ordenacao.OrdenaPorNome;
import servicos.Servicos;

import java.awt.Color;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class VisaoPesquisa extends JFrame {

	private JPanel contentPane;
	private JTextField textPesquisa;
	private JTable tabelaCadastros;

	public VisaoPesquisa(Cidade candidatos) {
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
		scrollPane.setBounds(12, 54, 411, 144);
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
				
		JLabel lblPesquisa = new JLabel("Pesquisa");
		lblPesquisa.setHorizontalAlignment(SwingConstants.CENTER);
		lblPesquisa.setFont(new Font("Impact", Font.PLAIN, 25));
		lblPesquisa.setBounds(10, 11, 414, 24);
		contentPane.add(lblPesquisa);
		
		textPesquisa = new JTextField();
		textPesquisa.setBounds(10, 209, 360, 41);
		contentPane.add(textPesquisa);
		textPesquisa.setColumns(10);
		
		JButton btnPesquisar = new JButton("");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!textPesquisa.getText().isEmpty()) {
					ArrayList<Candidato> listaPesquisa = new ArrayList<Candidato>();
					
					((DefaultTableModel) tabelaCadastros.getModel()).setRowCount(0);
					listaPesquisa = CandidatoDAO.pesquisaBanco(textPesquisa.getText().trim());
					OrdenaPorNome ordenacao = new OrdenaPorNome();
					Collections.sort(listaPesquisa, ordenacao);
					
					int aux;
					for(aux = 0; aux < listaPesquisa.size(); aux++) {
						Object dados[]= {listaPesquisa.get(aux).getNome().toString().toUpperCase(),listaPesquisa.get(aux).getSigla(),listaPesquisa.get(aux).getLegenda()};
							((DefaultTableModel) tabelaCadastros.getModel()).addRow(dados);
					}
				}else {
					((DefaultTableModel) tabelaCadastros.getModel()).setRowCount(0);
				}
			}
		});
		btnPesquisar.setBackground(Color.LIGHT_GRAY);
		btnPesquisar.setBounds(380, 209, 43, 41);
		btnPesquisar.setIcon(new ImageIcon(VisaoPesquisa.class.getResource("/imagens/lupinha.png")));
		contentPane.add(btnPesquisar);
		
		JButton btnVoltar = new JButton("");
		btnVoltar.setBackground(Color.LIGHT_GRAY);
		btnVoltar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				VisaoMenu frameMenu = new VisaoMenu(candidatos);
				frameMenu.setVisible(true);
				dispose();
			}
		});
		btnVoltar.setIcon(new ImageIcon(VisaoCadastroCandidato.class.getResource("/imagens/voltar.png")));
		btnVoltar.setBounds(381, 11, 43, 41);
		contentPane.add(btnVoltar);
	}
}
