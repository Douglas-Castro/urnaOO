package visao;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dados.Candidato;
import dados.Cidade;
import dao.CandidatoDAO;
import servicos.Servicos;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class VisaoConsulta extends JFrame {

	private JPanel contentPane;
	private JTextField textLegendaConsulta;

	public VisaoConsulta(Cidade candidatos) {
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
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(25, 56, 142, 139);
		label.setIcon(new ImageIcon(VisaoConsulta.class.getResource("/imagens/consultar.png")));
		contentPane.add(label);
		
		textLegendaConsulta = new JTextField();
		textLegendaConsulta.setBounds(177, 92, 171, 39);
		textLegendaConsulta.setDocument(new SoNumeros());
		contentPane.add(textLegendaConsulta);
		textLegendaConsulta.setColumns(10);
		
		JLabel btnLegenda = new JLabel("LEGENDA");
		btnLegenda.setHorizontalAlignment(SwingConstants.CENTER);
		btnLegenda.setFont(new Font("Impact", Font.PLAIN, 20));
		btnLegenda.setBounds(177, 131, 171, 26);
		contentPane.add(btnLegenda);
		
		JButton btnPesquisa = new JButton("");
		btnPesquisa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textLegendaConsulta.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Voce precisa preencher a legenda para consultar", "Campo vazio",JOptionPane.ERROR_MESSAGE);
				}else {
					Candidato candidato = CandidatoDAO.consultaBanco(Integer.parseInt(textLegendaConsulta.getText().trim()));
					if(candidato != null) {
						JOptionPane.showMessageDialog(null, "Nome: " + candidato.getNome() + "\nSigla: " + candidato.getSigla() + "\nLegenda: " + candidato.getLegenda(), candidatos.getNomeCidade().toString(), JOptionPane.DEFAULT_OPTION);
						textLegendaConsulta.setText("");
					} else {
						JOptionPane.showMessageDialog(null, "Nao encontrado!", candidatos.getNomeCidade().toString(), JOptionPane.DEFAULT_OPTION);
						textLegendaConsulta.setText("");
					}				
				}
			}
		});
		btnPesquisa.setBackground(Color.LIGHT_GRAY);
		btnPesquisa.setBounds(352, 92, 45, 41);
		btnPesquisa.setIcon(new ImageIcon(VisaoConsulta.class.getResource("/imagens/lupa.png")));
		contentPane.add(btnPesquisa);
		
		JLabel lblConsulta = new JLabel("Consulta");
		lblConsulta.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsulta.setFont(new Font("Impact", Font.PLAIN, 30));
		lblConsulta.setBounds(10, 19, 414, 39);
		contentPane.add(lblConsulta);
		
		JButton btnVoltar = new JButton("");
		btnVoltar.setBackground(Color.LIGHT_GRAY);
		btnVoltar.setBounds(368, 19, 45, 41);
		btnVoltar.setToolTipText("Voltar");
		btnVoltar.setIcon(new ImageIcon(VisaoCadastroCidade.class.getResource("/imagens/voltar.png")));
		btnVoltar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				VisaoMenu frameMenu = new VisaoMenu(candidatos);
				frameMenu.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnVoltar);
	}
}
