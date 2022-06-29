package it.polito.tdp.genes.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.genes.model.Arco;
import it.polito.tdp.genes.model.Classification;
import it.polito.tdp.genes.model.Genes;
import it.polito.tdp.genes.model.Interactions;
import it.polito.tdp.genes.model.Localization;


public class GenesDao {
	
	public List<Genes> getAllGenes(){
		String sql = "SELECT DISTINCT GeneID, Essential, Chromosome FROM Genes";
		List<Genes> result = new ArrayList<Genes>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				Genes genes = new Genes(res.getString("GeneID"), 
						res.getString("Essential"), 
						res.getInt("Chromosome"));
				result.add(genes);
			}
			res.close();
			st.close();
			conn.close();
			return result;
			
		} catch (SQLException e) {
			throw new RuntimeException("Database getAllGenes error", e) ;
		}
	}
	
	public List<Classification> getVertices()
	{
		String sql = "SELECT * FROM classification GROUP BY localization";
		List<Classification> result = new ArrayList<Classification>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				Classification classifications = new Classification(res.getString("Localization"));
				result.add(classifications);
			}
			res.close();
			st.close();
			conn.close();
			return result;
			
		} catch (SQLException e) {
			throw new RuntimeException("Database getVertices error", e) ;
		}
	}

	public List<Arco> getEdges()
	{
		String sql = "SELECT c.Localization, c2.Localization, i.Type "
				+ "FROM interactions i, classification c, classification c2 "
				+ "WHERE i.GeneID1=c.GeneID AND i.GeneID2=c2.GeneID AND c.Localization!=c2.Localization "
				+ "GROUP BY c.Localization, c2.Localization, i.Type";
		List<Arco> result = new ArrayList<Arco>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				Arco archi = new Arco((new Classification(res.getString("c.Localization"))), (new Classification(res.getString("c2.Localization"))), (res.getString("Type")));
				result.add(archi);
			}
			res.close();
			st.close();
			conn.close();
			return result;
			
		} catch (SQLException e) {
			throw new RuntimeException("Database getEdges error", e) ;
		}
	}
	
	
}
