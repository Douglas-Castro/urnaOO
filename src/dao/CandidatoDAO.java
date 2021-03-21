package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexao.Conexao;
import dados.Candidato;

public class CandidatoDAO {
	private static Connection con;

	public void cadastrar(Candidato candidato) {
		con = Conexao.getConnection();
		String sql = "INSERT INTO javaVerao.candidato (nome,sigla,legenda) VALUES (?, ?, ?)";
		try {
			PreparedStatement preparador = con.prepareStatement(sql); 
			preparador.setString(1, candidato.getNome());
			preparador.setString(2, candidato.getSigla());
			preparador.setInt(3, candidato.getLegenda());
			preparador.execute();
			preparador.close();
		} catch (SQLException e) {
				e.printStackTrace();
		} finally{
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					System.out.print("Falha ao fechar a conexao.");
					System.out.println("Causa: " + e.getMessage());
				}
		}
	}

	public static ArrayList<Candidato> listaDeCandidatos() {
		ArrayList<Candidato> lista = new ArrayList<Candidato>();
		con = Conexao.getConnection();
		
		String sql = "SELECT * FROM javaVerao.candidato";
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet rs = preparador.executeQuery();
			
			while (rs.next()) {
				lista.add(new Candidato(rs.getString(1), rs.getString(2), rs.getInt(3)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					System.out.print("Falha ao fechar a conexao.");
					System.out.println("Causa: " + e.getMessage());
				}
		}
		
		
		return lista;
	}

	public static Candidato consultaBanco(int legenda) {
		con = Conexao.getConnection();
		Candidato candidato = null;
		
		String sql = "select * from javaVerao.candidato where legenda LIKE ?";
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, legenda);
			ResultSet rs = preparador.executeQuery();
			if(rs.next())
				candidato = new Candidato(rs.getString(1), rs.getString(2), rs.getInt(3));
			else return null;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					System.out.print("Falha ao fechar a conexao.");
					System.out.println("Causa: " + e.getMessage());
				}
		}
		
		
		return candidato;
	}

	public static ArrayList<Candidato> pesquisaBanco(String strPesquisa) {
		ArrayList<Candidato> listaPesquisa = new ArrayList<Candidato>();
		con = Conexao.getConnection();
		
		String sql = "select * from javaVerao.candidato where nome LIKE ?";
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, "%" + strPesquisa + "%");
			ResultSet rs = preparador.executeQuery();
			while (rs.next()) {
				listaPesquisa.add(new Candidato(rs.getString(1), rs.getString(2), rs.getInt(3)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					System.out.print("Falha ao fechar a conexao.");
					System.out.println("Causa: " + e.getMessage());
				}
		}
		
		
		return listaPesquisa;
	}

	public static void limpaBanco() {
		con = Conexao.getConnection();
		
		String sql = "TRUNCATE TABLE javaVerao.candidato";
		try {
			Statement preparador = con.createStatement();
			preparador.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					System.out.print("Falha ao fechar a conexao.");
					System.out.println("Causa: " + e.getMessage());
				}
		}
	}

	public static boolean siglaExiste(String sigla) {
		con = Conexao.getConnection();
		
		String sql = "	SELECT * FROM javaVerao.candidato WHERE sigla LIKE ?";
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, sigla);
			ResultSet rs = preparador.executeQuery();
			
			if(rs.next())
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					System.out.print("Falha ao fechar a conexao.");
					System.out.println("Causa: " + e.getMessage());
				}
		}
		return false;
	}

	public static boolean legendaExiste(int legenda) {
		con = Conexao.getConnection();
		
		String sql = "SELECT * FROM javaVerao.candidato WHERE legenda LIKE ?";
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, legenda);
			ResultSet rs = preparador.executeQuery();
			if(rs.next())
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					System.out.print("Falha ao fechar a conexao.");
					System.out.println("Causa: " + e.getMessage());
				}
		}
		
		
		return false;
	}
	
	public static boolean isBancoVazio() {
		con = Conexao.getConnection();
		
		String sql = "SELECT * FROM javaVerao.candidato";
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet rs = preparador.executeQuery();
			
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					System.out.print("Falha ao fechar a conexao.");
					System.out.println("Causa: " + e.getMessage());
				}
		}
		
		
		return false;
	}
}