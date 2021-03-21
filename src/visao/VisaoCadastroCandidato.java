package visao;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dados.Cidade;
import servicos.Servicos;
import validacao.Validacao;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class VisaoCadastroCandidato extends JFrame {

	private JPanel contentPane;
	private JTextField textNome;
	private JTextField textSigla;
	private JTextField textLegenda;

	public VisaoCadastroCandidato(Cidade candidatos) {
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
		label.setBounds(10, 11, 118, 159);
		label.setIcon(new ImageIcon(VisaoCadastroCandidato.class.getResource("/imagens/candidato.png")));
		contentPane.add(label);
		
		JLabel lblNome = new JLabel("NOME:");
		lblNome.setBackground(Color.WHITE);
		lblNome.setForeground(Color.BLACK);
		lblNome.setFont(new Font("Impact", Font.PLAIN, 20));
		lblNome.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNome.setBounds(124, 57, 86, 26);
		contentPane.add(lblNome);
		
		JLabel lblSigla = new JLabel("SIGLA:");
		lblSigla.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSigla.setForeground(Color.BLACK);
		lblSigla.setFont(new Font("Impact", Font.PLAIN, 20));
		lblSigla.setBackground(Color.WHITE);
		lblSigla.setBounds(124, 94, 86, 26);
		contentPane.add(lblSigla);
		
		JLabel lblLegenda = new JLabel("LEGENDA:");
		lblLegenda.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLegenda.setForeground(Color.BLACK);
		lblLegenda.setFont(new Font("Impact", Font.PLAIN, 20));
		lblLegenda.setBackground(Color.WHITE);
		lblLegenda.setBounds(124, 131, 86, 26);
		contentPane.add(lblLegenda);
		
		JLabel lblDados = new JLabel("DADOS");
		lblDados.setHorizontalAlignment(SwingConstants.CENTER);
		lblDados.setFont(new Font("Impact", Font.PLAIN, 20));
		lblDados.setBounds(138, 23, 286, 26);
		contentPane.add(lblDados);
		
		textNome = new JTextField();
		textNome.setBounds(220, 57, 204, 27);
		contentPane.add(textNome);
		textNome.setColumns(10);
		
		textSigla = new JTextField();
		textSigla.setColumns(10);
		textSigla.setBounds(220, 93, 204, 27);
		contentPane.add(textSigla);
		
		textLegenda = new JTextField();
		textLegenda.setColumns(10);
		textLegenda.setBounds(220, 130, 204, 27);
		textLegenda.setDocument(new SoNumeros());
		contentPane.add(textLegenda);
		
		JButton btnOk = new JButton("");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nome;
				String sigla;
				Integer legenda;
				
				if(textNome.getText().isEmpty() || textSigla.getText().isEmpty() || textLegenda.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Voce precisa preencher os campos obrigatorios!", "Campos vazios",JOptionPane.ERROR_MESSAGE);
				}else if(Validacao.validaNome(textNome.getText()) && Validacao.validaSigla(textSigla.getText()) && Validacao.validaLegenda(textLegenda.getText())) {
					nome = textNome.getText();
					sigla = textSigla.getText();
					legenda = Integer.parseInt(textLegenda.getText());
					
					Servicos.cadastrarBD(nome, sigla, legenda);
					
					textNome.setText("");
					textSigla.setText("");
					textLegenda.setText("");
					JOptionPane.showMessageDialog(null, "Cadastro salvo!", "Realizado",JOptionPane.DEFAULT_OPTION);
					
					VisaoMenu frameMenu = new VisaoMenu(candidatos);
					frameMenu.setVisible(true);
					setVisible(false);	
				}
			}
		});
		btnOk.setBackground(Color.LIGHT_GRAY);
		btnOk.setToolTipText("Salvar");
		btnOk.setBounds(344, 175, 80, 75);
		btnOk.setIcon(new ImageIcon(VisaoCadastroCandidato.class.getResource("/imagens/ok.png")));
		contentPane.add(btnOk);
		
		JLabel label_1 = new JLabel("*");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(422, 131, 12, 26);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("*");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(422, 57, 12, 26);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("*");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(422, 94, 12, 26);
		contentPane.add(label_3);
		
		JLabel lblObrigatorios = new JLabel("(*) Obrigatorios");
		lblObrigatorios.setBounds(10, 247, 118, 14);
		contentPane.add(lblObrigatorios);
		
		JButton btnVoltar = new JButton("");
		btnVoltar.setBackground(Color.LIGHT_GRAY);
		btnVoltar.setToolTipText("Voltar");
		btnVoltar.setIcon(new ImageIcon(VisaoCadastroCandidato.class.getResource("/imagens/voltar.png")));
		btnVoltar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				VisaoMenu frameMenu = new VisaoMenu(candidatos);
				frameMenu.setVisible(true);
				dispose();
			}
		});
		btnVoltar.setBounds(379, 11, 45, 41);
		contentPane.add(btnVoltar);
	}

}
