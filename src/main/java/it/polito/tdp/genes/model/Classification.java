package it.polito.tdp.genes.model;

import java.util.List;
import java.util.Objects;

public class Classification {
	
	private List<String> geneId;
	private String localization;
	
	
	public Classification(String localization) {
		super();
		this.localization = localization;
	}


	public List<String> getGeneId() {
		return geneId;
	}


	public void setGeneId(List<String> geneId) {
		this.geneId = geneId;
	}


	public String getLocalization() {
		return localization;
	}


	public void setLocalization(String localization) {
		this.localization = localization;
	}


	@Override
	public int hashCode() {
		return Objects.hash(localization);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Classification other = (Classification) obj;
		return Objects.equals(localization, other.localization);
	}
	
	
	
}