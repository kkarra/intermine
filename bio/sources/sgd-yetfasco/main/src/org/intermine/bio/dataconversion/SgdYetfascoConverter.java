package org.intermine.bio.dataconversion;

/*
 * Copyright (C) 2002-2011 FlyMine
 *
 * This code may be freely distributed and modified under the
 * terms of the GNU Lesser General Public Licence.  This should
 * be distributed with the code.  See the LICENSE file for more
 * information or http://www.gnu.org/copyleft/lesser.html.
 *
 */

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.io.BufferedReader;
import org.intermine.metadata.Model;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.intermine.objectstore.ObjectStoreException; 
import org.intermine.dataconversion.ItemWriter;
import org.intermine.util.FormattedTextParser;
import org.apache.tools.ant.BuildException;
import org.intermine.xml.full.Item;

/**
 * 
 * @author
 */
public class SgdYetfascoConverter extends BioFileConverter
{

	private final Map<String, Item> pubmedIdMap = new HashMap<String, Item>();
	private final Map<String, Item> geneIdMap = new HashMap<String, Item>();
	private final Map<String, Item> ecoMap = new HashMap<String, Item>();
	private static final String TAXON_ID = "4932";
	private Item organism;
	private File bindingSitesFile; 
	private File paragraphsFile;
	private File jasparFile;
	private File confirmedFile;
	private File logosFile;
	protected static final Logger LOG = Logger.getLogger(SgdYetfascoConverter.class);
	private static final String DATASET_TITLE = "SGD/YEASTRACT Regulation data";
	private static final String DATA_SOURCE_NAME = "Regulation";
	private static final String pvalue1 = "0.00001"; 
	private static final String pvalue2 = "0.0001"; 
	private static final String pvalue3 = "0.001"; 

	/**
	 * Constructor
	 * @param writer the ItemWriter used to handle the resultant items
	 * @param model the Model
	 */
	public SgdYetfascoConverter(ItemWriter writer, Model model) throws ObjectStoreException{
		super(writer, model, DATA_SOURCE_NAME, DATASET_TITLE);
		organism = createItem("Organism");
		organism.setAttribute("taxonId", TAXON_ID);
		store(organism);
	}

	public void setBindingSitesFile(File bindingSitesFile) {
		this.bindingSitesFile = bindingSitesFile;
	}

	public void setParagraphsFile(File paragraphsFile) {
		this.paragraphsFile = paragraphsFile;
	}
	public void setJasparFile(File jasparFile) {
		this.jasparFile = jasparFile;
	}
	public void setConfirmedFile(File confirmedFile) {
		this.confirmedFile = confirmedFile;
	}
	public void setLogosFile(File logosFile) {
		this.logosFile = logosFile;
	}

	/**
	 * 
	 *
	 * {@inheritDoc}
	 */
	public void process(Reader reader) throws Exception {     
		processBindingSitesFile(new FileReader(bindingSitesFile));            
		processParagraphsFile(new FileReader(paragraphsFile));
		processJasparFile(new FileReader(jasparFile));
		processLogosFile(new FileReader(logosFile));
		processConfirmedRegulationDataFile(new FileReader(confirmedFile)); 
		//processHTPRegulationDataFile(reader);     
		storeGenes();       
	}


	/**
	 * 
	 * @param reader
	 * @throws Exception
	 * @throws ObjectStoreException
	 */
	private void processHTPRegulationDataFile(Reader preader) throws Exception, ObjectStoreException {

		/* Regulator gene name
		 * Regulator feature name - 1 
		 * target gene name
		 * target feature name - 3
		 * evidence - empty column - 4
		 * ECO ID -5 
		 * condition - 6
		 * direction of regulation - 7
		 * PMID - 8
		 *  source - 9
		 *  strain background - 10 5/9-kk
		 */   	
		System.out.println("Processing HTP Regulation Data file....");    

		Iterator<?> tsvIter;
		try {
			tsvIter = FormattedTextParser.parseTabDelimitedReader(preader);
		} catch (Exception e) {
			throw new BuildException("cannot parse file: " + getCurrentFile(), e);
		}

		while (tsvIter.hasNext()) {

			String[] line = (String[]) tsvIter.next();

			String factorGene =  line[1].trim();     
			String targetGene = line[3].trim();

			System.out.println("factor gene "  + factorGene + " target gene " + targetGene);
			
			if (line.length < 10) {
				System.out.println("LessThan10:  factor gene "  + factorGene + " target gene " + targetGene);
				LOG.error("Couldn't process line. Expected 10 cols, but was " + line.length);				
				continue;
			}


			if(factorGene.indexOf("TEL")  >= 0 || factorGene.indexOf("delta") > 0  || factorGene.indexOf("omega") > 0  || factorGene.indexOf("Ty") > 0
					|| targetGene.indexOf("TEL") >= 0 || targetGene.indexOf("delta") > 0  || targetGene.indexOf("omega") > 0  || targetGene.indexOf("Ty") > 0	){
				System.out.println("TEL/delta/omega/Ty:  factor gene "  + factorGene + " target gene " + targetGene);
				continue;
			}

			//String strEvidence = line[4].trim();
			String evidenceCode =  line[5].trim();
			String condition =  line[6].trim(); 
			String regulationDirection =  line[7].trim();
			String pmid =  line[8].trim();
			String source =  line[9].trim();
			String strainBackground = "";
			if(line.length == 11){
				strainBackground =  line[10].trim();
			}

			newProduct(factorGene, targetGene, evidenceCode, condition,  regulationDirection, pmid, source, "", "", strainBackground, "");

		}
		preader.close();

	}

	/**
	 * 
	 * @param reader
	 * @throws Exception
	 * @throws ObjectStoreException
	 */
	private void processConfirmedRegulationDataFile(Reader preader) throws Exception, ObjectStoreException {

		/* regulator gene name 
		 * regulator feature name - 1
		 * target gene name 
		 * target feature name  - 3
		 * evidence string corresponding to ECOID
		 * ECO ID  - 5
		 * condition  - 6 
		 * direction of regulation  - 7
		 *  p-value  - 8 
		 *  FDR  - 9 
		 * PMID  - 10 
		 *  source  - 11
		 *  strain BACKGROUD - 12
		 *  strain - 13
		 */   	 
		System.out.println("Processing confirmedBindingSites Regulation Data file....");    

		Iterator<?> tsvIter;
		try {
			tsvIter = FormattedTextParser.parseTabDelimitedReader(preader);
		} catch (Exception e) {
			throw new BuildException("cannot parse file: " + getCurrentFile(), e);
		}

		while (tsvIter.hasNext()) {

			String[] line = (String[]) tsvIter.next();

			String factorGene =  line[1].trim();     
			String targetGene = line[3].trim();
			
			if (line.length < 13) {
				System.out.println("LessThan10:  factor gene "  + factorGene + " target gene " + targetGene);
				LOG.error("Couldn't process line. Expected 10 cols, but was " + line.length);
				continue;
			}

			if(factorGene.indexOf("TEL")  >= 0 || factorGene.indexOf("delta") > 0  || factorGene.indexOf("omega") > 0  || factorGene.indexOf("Ty") > 0
					|| targetGene.indexOf("TEL") >= 0 || targetGene.indexOf("delta") > 0  || targetGene.indexOf("omega") > 0  || targetGene.indexOf("Ty") > 0	){
				System.out.println("TEL/delta/omega/Ty:  factor gene "  + factorGene + " target gene " + targetGene);
				continue;
			}
			String strain = "";
			//String strEvidence = line[4].trim();
			String evidenceCode =  line[5].trim();
			String condition =  line[6].trim(); 
			String regulationDirection =  line[7].trim();
			String pvalue = line[8].trim();
			String fdr = line[9].trim();
			String pmid =  line[10].trim();
			String source =  line[11].trim();
			String strainBackground =  line[12].trim();
			if(line.length == 14){
				 strain = line[13].trim();
			}
			
			System.out.println("factor gene "  + factorGene + " target gene " + targetGene);
			
			newProduct(factorGene, targetGene,
					evidenceCode, condition,  regulationDirection, pmid, source, pvalue,fdr,strainBackground, strain);

		}
		//br.close();
		preader.close();

	}
	/**
	 * 
	 * @param reader
	 */
	private void processBindingSitesFile(Reader breader) throws Exception, ObjectStoreException {

		/* Columns in the file..
		 * Gene    
		 * Intergenic 0.00001 
		 * Intergenic 0.0001
		 * Intergenic 0.001
		 * Intragenic 0.00001
		 * Intragenic 0.0001
		 * Intragenic 0.001
		 */

		Iterator<?> tsvIter;
		try {
			tsvIter = FormattedTextParser.parseTabDelimitedReader(breader);
		} catch (Exception e) {
			throw new BuildException("cannot parse file: " + getCurrentFile(), e);
		}

		while (tsvIter.hasNext()) {
			String[] line = (String[]) tsvIter.next();

			if (line.length < 8) {
				LOG.error("Couldn't process line. Expected 8 cols, but was " + line.length);
				continue;
			}

			String geneName = line[0].trim();
			String geneId = line[1].trim();

			if(geneId.indexOf("TEL") >= 0 || geneId.indexOf("delta") >0  || geneId.indexOf("omega") > 0 || geneId.indexOf("Ty") > 0){
				continue;
			}

			String intergenicCount1 = line[2].trim();
			String intergenicCount2 = line[3].trim();
			String intergenicCount3 = line[4].trim();

			String intragenicCount1 = line[5].trim();
			String intragenicCount2 = line[6].trim();
			String intragenicCount3 = line[7].trim();

			Item pbs1 = createBindingSiteItem(intergenicCount1, intragenicCount1, pvalue1);
			Item pbs2 = createBindingSiteItem(intergenicCount2, intragenicCount2,  pvalue2);
			Item pbs3 = createBindingSiteItem(intergenicCount3, intragenicCount3, pvalue3);


			Item gene = getGeneItem(geneId);
			gene.addToCollection("predictedBindingSites", pbs1.getIdentifier());
			gene.addToCollection("predictedBindingSites", pbs2.getIdentifier());
			gene.addToCollection("predictedBindingSites", pbs3.getIdentifier());                

		}
		breader.close();

	}


	/** 
	 * 
	 * @param reader
	 */
	private void processParagraphsFile(Reader preader) throws Exception, ObjectStoreException{

		/* Columns in the file..
		 * Systematic Name 
		 * Gene Name       
		 * Paragraph       
		 * References
		 */

		Iterator<?> tsvIter;
		try {
			tsvIter = FormattedTextParser.parseTabDelimitedReader(preader);
		} catch (Exception e) {
			throw new BuildException("cannot parse file: " + getCurrentFile(), e);
		}

		while (tsvIter.hasNext()) {
			String[] line = (String[]) tsvIter.next();

			if (line.length != 4) {
				LOG.error("Couldn't process line. Expected 4 cols, but was " + line.length);
				continue;
			}

			String geneId = line[0].trim();

			if(geneId.indexOf("TEL") >= 0 || geneId.indexOf("delta") >0  || geneId.indexOf("omega") > 0 || geneId.indexOf("Ty") > 0){
				continue;
			}

			String geneName = line[1].trim();          
			String paragraph = line[2].trim();
			String references = line[3].trim();

			Item rs = createItemRegulationSummary(paragraph, references);

			Item gene = getGeneItem(geneId);
			gene.setReference("regulationSummary", rs.getIdentifier());


		}
		preader.close();



	}

	/**
	 * 
	 * @param reader
	 */
	private void processJasparFile(Reader breader) throws Exception, ObjectStoreException {

		/* Columns in the file..
		 * Gene name 
		 * Feature name
		 * Jaspar Family
		 * Jaspar Class
		 * Source
		 * pMID
		 */

		Iterator<?> tsvIter;
		try {
			tsvIter = FormattedTextParser.parseTabDelimitedReader(breader);
		} catch (Exception e) {
			throw new BuildException("cannot parse file: " + getCurrentFile(), e);
		}

		while (tsvIter.hasNext()) {
			String[] line = (String[]) tsvIter.next();

			if (line.length < 6) {
				LOG.error("Couldn't process line. Expected 8 cols, but was " + line.length);
				continue;
			}
			String jasparAccession = line[0].trim();;
			String geneName = line[1].trim();
			String geneId = line[2].trim();

			if(geneId.indexOf("TEL") >= 0 || geneId.indexOf("delta") >0  || geneId.indexOf("omega") > 0 || geneId.indexOf("Ty") > 0){
				continue;
			}           
			String jasparFamily = line[3].trim();
			String jasparClass = line[4].trim();

			Item gene = getGeneItem(geneId);

			gene.setAttribute("jasparFamily", jasparFamily);
			gene.setAttribute("jasparClass", jasparClass);
			gene.setAttribute("jasparAccession", jasparAccession);

		}
		breader.close();

	}
	

	/** 
	 * 
	 * @param reader
	 */
	private void processLogosFile(Reader preader) throws Exception, ObjectStoreException{

		/* Columns in the file..
		 * Systematic Name 
		 * Gene Name       
		 * Paragraph       
		 * References
		 */

		   BufferedReader br = new BufferedReader(preader);
	       String line = null;

		
		while ((line = br.readLine()) != null) {

			String fileName = line;
			
			System.out.println("line in logos is ... " + fileName);
			
			String[] temp = fileName.split("_");
			String geneId = temp[0];

			if(geneId.indexOf("TEL") >= 0 || geneId.indexOf("delta") >0  || geneId.indexOf("omega") > 0 || geneId.indexOf("Ty") > 0){
				continue;
			}   
			Item logo = createItemLogo(fileName);
			
			Item gene = getGeneItem(geneId);
			gene.addToCollection("logos", logo.getIdentifier());


		}
		preader.close();

	}
	
	/**
	 * 
	 */
	private Item createItemRegulationSummary (String summary, String references) throws ObjectStoreException {

		Item rs = createItem("RegulationSummary");
		rs.setAttribute("summaryParagraph", summary);	

		if(references.indexOf("|") != -1) {    			

			String[] refs = references.split("\\|");   

			for (int i = 0; i < refs.length; i++) {
				String pmid = refs[i];
				if (StringUtils.isNotEmpty(pmid)) {
					Item publication = pubmedIdMap.get(pmid);
					if(publication == null) {
						publication = createItem("Publication");
						pubmedIdMap.put(pmid, publication);
						publication.setAttribute("pubMedId", pmid);                      
						try {
							store(publication);
						} catch (ObjectStoreException e) {
							throw new ObjectStoreException(e);
						}			
					}
					rs.addToCollection("publications", publication);
				}
			}
		} else{
			Item publication = pubmedIdMap.get(references);
			if(publication == null) {
				publication = createItem("Publication");
				pubmedIdMap.put(references, publication);
				publication.setAttribute("pubMedId", references);                      
				try {
					store(publication);
				} catch (ObjectStoreException e) {
					throw new ObjectStoreException(e);
				}			
			}
			rs.addToCollection("publications", publication);
		}

		try {
			store(rs);
		} catch (ObjectStoreException e) {
			throw new ObjectStoreException(e);
		}	


		return rs;
	}



	/*
	 * 
	 */
	private Item createBindingSiteItem(String intergenicCount, String intragenicCount, String pvalue) throws ObjectStoreException {

		Item pbs = createItem("PredictedBindingSites");
		pbs.setAttribute("numIntergenicSites", intergenicCount);
		pbs.setAttribute("numIntragenicSites", intragenicCount);
		pbs.setAttribute("pValue", pvalue);

		try {
			store(pbs);
		} catch (ObjectStoreException e) {
			throw new ObjectStoreException(e);
		}	

		return pbs;

	}

	/*
	 * 
	 */
	private Item createItemLogo(String fileName) throws ObjectStoreException {

		Item logo = createItem("Logo");
		logo.setAttribute("fileName", fileName);

		try {
			store(logo);
		} catch (ObjectStoreException e) {
			throw new ObjectStoreException(e);
		}	

		return logo;

	}

	/**
	 * 
	 * @param regulatorGene
	 * @param targetGene
	 * @param strEvidence
	 * @param evidenceCode
	 * @param condition
	 * @param regulationDirection
	 * @param pmid
	 * @param source
	 * @return
	 * @throws ObjectStoreException
	 */
	private void newProduct(String factorGene, String targetGene, 
			String evidenceCode, String condition, String regulationDirection, String pmid, String source, String pvalue, String fdr, String strainBackground, String strain) 
					throws ObjectStoreException {

		Item rGene = getGeneItem(factorGene);
		Item tGene = getGeneItem(targetGene);

		if (rGene != null && tGene != null) {       	               	           			

			Item bindingSite = createItem("TFBindingSite");  
			String name = factorGene + "_binding_site";

			bindingSite.setAttribute("name", name);
			bindingSite.setReference("factor", rGene.getIdentifier());
			bindingSite.setReference("gene", tGene.getIdentifier());

			Item evidence = createItem("RegulationEvidence");

			if (StringUtils.isNotEmpty(evidenceCode)) {

				Item eco = ecoMap.get(evidenceCode);
				if(eco == null) {
					eco = createItem("ECOTerm");
					ecoMap.put(evidenceCode, eco);
					eco.setAttribute("identifier", evidenceCode); 
					try {
						store(eco);
					} catch (ObjectStoreException e) {
						throw new ObjectStoreException(e);
					}	
				}
				evidence.setReference("ontologyTerm", eco.getIdentifier());
			}


			try {
				store(evidence);
			} catch (ObjectStoreException e) {
				throw new ObjectStoreException(e);
			}			

			bindingSite.setReference("regEvidence", evidence.getIdentifier());                  	

			if (StringUtils.isNotEmpty(condition)) {
				
				String[] t = condition.split(";");
				//if(t.length == 4){
				String newcond = t[0]+";"+t[3];
				bindingSite.setAttribute("experimentCondition", newcond);
				bindingSite.setAttribute("assay", t[1]);
				//}else if (t.length == 3){
				//	String newcond = t[0]+";"+t[2];
				//	bindingSite.setAttribute("experimentCondition", newcond);
					//bindingSite.setAttribute("assay", t[1]);				
				//}
			}
			if (StringUtils.isNotEmpty(regulationDirection)) {
				bindingSite.setAttribute("regulationDirection", regulationDirection);
			}
			if (StringUtils.isNotEmpty(source)) { 
				bindingSite.setAttribute("datasource", source);
			}
			if (StringUtils.isNotEmpty(pvalue)) {
				bindingSite.setAttribute("pvalue", pvalue);
			}	
			if (StringUtils.isNotEmpty(fdr)) {
				bindingSite.setAttribute("FDR", fdr);
			}
			if (StringUtils.isNotEmpty(strain)) {
				bindingSite.setAttribute("construct", strain);
			}
			if (StringUtils.isNotEmpty(strainBackground)) {
				bindingSite.setAttribute("strainBackground", strainBackground);
			}

			Item publication = pubmedIdMap.get(pmid);

			if(publication == null) {
				publication = createItem("Publication");
				pubmedIdMap.put(pmid, publication);
				publication.setAttribute("pubMedId", pmid);                      
				try {
					store(publication);
				} catch (ObjectStoreException e) {
					throw new ObjectStoreException(e);
				}			
			}

			bindingSite.addToCollection("publications", publication);      

			try {
				store(bindingSite);
			} catch (ObjectStoreException e) {
				throw new ObjectStoreException(e);
			}	


		}


	}

	/**
	 * 
	 * @param geneId
	 * @return
	 * @throws ObjectStoreException
	 */

	private Item getGeneItem(String geneId) throws ObjectStoreException{

		Item gene = geneIdMap.get(geneId);

		if (gene == null) {      	
			gene = createItem("Gene");
			geneIdMap.put(geneId, gene);
			gene.setAttribute("secondaryIdentifier", geneId);
			gene.setReference("organism", organism);                    
			/*try {
				store(gene);
			} catch (ObjectStoreException e) {
				throw new ObjectStoreException(e);
			}*/			
		}

		return gene;

	}

	/**
	 * 
	 * @throws ObjectStoreException
	 */

	private void storeGenes() throws ObjectStoreException {
		for (Item gene : geneIdMap.values()) {
			try {
				store(gene);
			} catch (ObjectStoreException e) {
				throw new ObjectStoreException(e);
			}
		}
	}

}
