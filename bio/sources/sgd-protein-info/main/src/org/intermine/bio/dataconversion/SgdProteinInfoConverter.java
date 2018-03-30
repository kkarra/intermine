package org.intermine.bio.dataconversion;

/*
 * Copyright (C) 2002-2015 FlyMine
 *
 * This code may be freely distributed and modified under the
 * terms of the GNU Lesser General Public Licence.  This should
 * be distributed with the code.  See the LICENSE file for more
 * information or http://www.gnu.org/copyleft/lesser.html.
 *
 */
import java.io.Reader;
import java.util.Iterator;

import org.apache.tools.ant.BuildException;
import org.intermine.dataconversion.ItemWriter;
import org.intermine.metadata.Model;
import org.intermine.objectstore.ObjectStoreException;
import org.intermine.util.FormattedTextParser;
import org.intermine.xml.full.Item;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


/**
 * 
 * @author
 */
public class SgdProteinInfoConverter extends BioFileConverter
{
	//
	private static final String DATASET_TITLE = "Protein Properties File";
	private static final String DATA_SOURCE_NAME = "SGD";
	private final Map<String, Item> proteinIdMap = new HashMap<String, Item>();

	/**
	 * Constructor
	 * @param writer the ItemWriter used to handle the resultant items
	 * @param model the Model
	 */
	public SgdProteinInfoConverter(ItemWriter writer, Model model) {
		super(writer, model, DATA_SOURCE_NAME, DATASET_TITLE);
	}

	/**
	 * 
	 *
	 * {@inheritDoc}
	 */
	public void process(Reader reader) throws Exception {
		processProteinDataFile(reader); 
		storeProteins();

	}

	/**
	 * 
	 * @param reader
	 * @throws Exception
	 * @throws ObjectStoreException
	 */
	private void processProteinDataFile(Reader preader) throws Exception, ObjectStoreException {

		/*
		 * Sample line 
		 * Mw (Molecular Weight)
		 PI (Isoelectric Point)
		 Protein Length
		 N_term_seq
		 C_term_seq
		 GRAVY Score (Hydropathicity of Protein)
		 Aromaticity Score (Frequency of aromatic amino acids: Phe, Tyr, Trp)
		 CAI (Codon Adaptation Index)
		 Codon Bias
		 FOP Score (Frequency of Optimal Codons)>
		Ala
		Cys
		Asp
		Glu
		Phe
		Gly
		His
		Ile
		Lys
		Leu
		Met
		Asn
		Pro
		Gln
		Arg
		Ser
		Thr
		Val
		Trp
		Tyr
		CARBON
		HYDROGEN
		NITROGEN
		OXYGEN
		SULPHUR
		INSTABILITY INDEX (II)
		ASSUMING ALL CYS RESIDUES APPEAR AS HALF CYSTINES
		ASSUMING NO CYS RESIDUES APPEAR AS HALF CYSTINES
		ALIPHATIC INDEX
		 */   	 
		System.out.println("Processing Protien Properties Data file....");    

		Iterator<?> tsvIter;
		try {
			tsvIter = FormattedTextParser.parseTabDelimitedReader(preader);
		} catch (Exception e) {
			throw new BuildException("cannot parse file: " + getCurrentFile(), e);
		}
		
		int count = 0;

		while (tsvIter.hasNext()) {

			String[] line = (String[]) tsvIter.next();
			
			if(count == 0){
				count++;
				continue;
			}

			if (line.length < 39) {
				System.out.println("Couldn't process line. Expected 39 cols, but was " + line.length);
				continue;
			}
		
			
			String orfname = line[0].trim();  //orf systematic name
			String molwt = line[1].trim();
			String pi = line[2].trim();
			
			String ntermseq = line[4].trim();
			String ctermseq = line[5].trim();
			String gravyScore = line[6].trim();
			String aromaticityScore = line[7].trim();
			String cai = line[8].trim();
			String codonBias = line[9].trim();
			String fopScore = line[10].trim();
		
			String ala = line[11].trim();
			String cys = line[12].trim();
			String asp =line[13].trim();
			String glu = line[14].trim();
			String phe = line[15].trim();
			String gly = line[16].trim();
			String his = line[17].trim();
			
			String ile = line[18].trim();
			String lys = line[19].trim();
			String leu = line[20].trim();
			String met = line[21].trim();
			String asn = line[22].trim();
			String pro = line[23].trim();			
			String gln = line[24].trim();
			String arg = line[25].trim();
			String ser = line[26].trim();			
			String thr = line[27].trim();
			String val = line[28].trim();			
			String trp = line[29].trim();
			String tyr = line[30].trim();
			
			String carbon = line[31].trim();
			String hydrogen = line[32].trim();
			String nitrogen = line[33].trim();
			String oxygen = line[34].trim();
			String sulphur = line[35].trim();
			String instabilityIndex = line[36].trim();
			String allCysHalf = line[37].trim();
			String noCysHalf = line[38].trim();
			String aliphaticIndex = line[39].trim();

			Item protein = getProteinItem(orfname);
		
			if (molwt != null) {                  
	           protein.setAttribute("molecularWeight", molwt);		       				
			}
			if (pi != null) {
				protein.setAttribute("pI", pi);
			}
			if (fopScore != null) {
				protein.setAttribute("fopScore", fopScore);
			}
			if (gravyScore != null) {
				protein.setAttribute("gravyScore", gravyScore);
			}
			if (aromaticityScore != null) {
				protein.setAttribute("aromaticityScore", aromaticityScore);
			}
			if (cys != null) {
				protein.setAttribute("cys", cys);
			}
			if (gln != null) {
				protein.setAttribute("gln", gln);
			}
			if (glu != null) {
				protein.setAttribute("glu", glu);
			}
			if (gly != null) {
				protein.setAttribute("gly", gly);
			}
			if (his != null) {
				protein.setAttribute("his", his);
			}
			if (ile != null) {
				protein.setAttribute("ile", ile);
			}
			if (leu != null) {
				protein.setAttribute("leu", leu);
			}
			if (lys != null) {
				protein.setAttribute("lys", lys);
			}
			if (met != null) {
				protein.setAttribute("met", met);
			}
			if (phe != null) {
				protein.setAttribute("phe", phe);
			}
			if (pro != null) {
				protein.setAttribute("pro", pro);
			}
			if (ser != null) {
				protein.setAttribute("ser", ser);
			}
			if (thr != null) {
				protein.setAttribute("thr", thr);
			}
			if (trp != null) {
				protein.setAttribute("trp", trp);
			}
			if (val != null) {
				protein.setAttribute("val", val);
			}
			if (ala != null) {
				protein.setAttribute("ala", ala);
			}
			if (arg != null) {
				protein.setAttribute("arg", arg);
			}
			if (asn != null) {
				protein.setAttribute("asn", asn);
			}
			if (asp != null) {
				protein.setAttribute("asp", asp);
			}
			if (ntermseq != null) {
				protein.setAttribute("ntermseq", ntermseq);
			}
			if (ctermseq != null) {
				protein.setAttribute("ctermseq", ctermseq);
			}
			if (cai != null) {
				protein.setAttribute("cai", cai);
			}
			if (codonBias != null) {
				protein.setAttribute("codonBias", codonBias);
			}			
			if (carbon != null) {
				protein.setAttribute("carbon", carbon);
			}
			if (hydrogen != null) {
				protein.setAttribute("hydrogen", hydrogen);
			}
			if (nitrogen != null) {
				protein.setAttribute("nitrogen", nitrogen);
			}
			if (oxygen != null) {
				protein.setAttribute("oxygen", oxygen);
			}
			if (sulphur != null) {
				protein.setAttribute("sulphur", sulphur);
			}
			if (instabilityIndex != null) {
				protein.setAttribute("instabilityIndex", instabilityIndex);
			}
			if (allCysHalf != null) {
				protein.setAttribute("allCysHalf", allCysHalf);
			}
			if (noCysHalf != null) {
				protein.setAttribute("noCysHalf", noCysHalf);
			}
			if (aliphaticIndex != null) {
				protein.setAttribute("aliphaticIndex", aliphaticIndex);
			}

		}

		preader.close();

	}

	/**
	 * 
	 * @param geneId
	 * @return
	 * @throws ObjectStoreException
	 */

	private Item getProteinItem(String proteinId) throws ObjectStoreException{

		Item protein = proteinIdMap.get(proteinId);

		if (protein == null) {      	
			protein = createItem("Protein");
			proteinIdMap.put(proteinId, protein);
			protein.setAttribute("secondaryIdentifier", proteinId);                  		
		}

		return protein;

	}

	/**
	 * 
	 * @throws Exception
	 */
	private void storeProteins() throws Exception{

		for (Item protein : proteinIdMap.values()) {
			try {
				store(protein);
			} catch (ObjectStoreException e) {
				throw new Exception(e);
			}
		}

	}

}
