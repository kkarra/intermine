package org.intermine.bio.dataconversion;

/*
 * Copyright (C) 2002-2013 FlyMine
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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


import java.io.BufferedReader;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.tools.ant.BuildException;
import org.intermine.bio.dataconversion.IdResolver;
import org.intermine.bio.dataconversion.IdResolverService;
import org.intermine.dataconversion.ItemWriter;
import org.intermine.metadata.Model;
import org.intermine.objectstore.ObjectStoreException;
import org.intermine.util.FormattedTextParser;
import org.intermine.xml.full.Item;


/**
 * Converter to parse modENCODE expression data.
 *
 * @author Julie Sullivan
 */
public class TropsRnaSeqConverter extends BioFileConverter
{
	private static final Logger LOG = Logger.getLogger(TropsRnaSeqConverter.class);
	private static final String DATASET_TITLE = "Tropicalis RNA-Seq expression experiments.";
	private static final String DATA_SOURCE_NAME = "Baker Lab, GEO etc";
	private Item organism;
	private Map<String, Item> organisms = new HashMap();
	private static final String TAXON_ID = "8364";
	private Map<String, String> genes = new HashMap<String, String>();
	private Map<String, String> terms = new HashMap<String, String>();
	private Map<String, Item> devstages = new HashMap();
	private Map<String, Item> tissues = new HashMap();
	private Map<String, Item> publications = new HashMap();
	private Map<String, Item> datasets = new HashMap();
	private Map<Integer, String> header = new HashMap<Integer, String>();
	private static HashMap  <String, Integer> orderStages = new HashMap();

	/**
	 * Constructor
	 * @param writer the ItemWriter used to handle the resultant items
	 * @param model the Model
	 */
	public TropsRnaSeqConverter(ItemWriter writer, Model model)  throws ObjectStoreException {

		super(writer, model, DATA_SOURCE_NAME, DATASET_TITLE);
		organism = createItem("Organism");
		organism.setAttribute("taxonId", TAXON_ID);
		organisms.put("X. tropicalis", organism);
		store(organism);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void process(Reader reader) throws Exception {
		loadStages();
		processScoreFile(reader);
	}

	private void processScoreFile(Reader reader) throws Exception, ObjectStoreException {

		BufferedReader br = new BufferedReader(reader);

		String metadata[] = new String[9]; 
		int j = 0;
		Item exp = null;
		String line = null;

		while ((line = br.readLine()) != null) {

			if (line.startsWith("!")) { 
				metadata[j] = line;
				j++;
				continue;
			}

			if(line.startsWith("#")) {
				exp = processMetaData(metadata);
				readHeaderLine(line);
				continue;
			}

			String[] vals = line.split("\t", -1);

			if (vals.length < 5 && StringUtils.isNotEmpty(vals.toString())) {
				throw new RuntimeException("Invalid line, should be at least 5 columns but is '"
						+ vals.length + "' instead");
			}

			String geneId = vals[0].trim()+".g";	
			String gene = getGene(geneId);

			for(int i=1; i<vals.length; i++){

				String rpkm = vals[i];	
				String stagecond = header.get(i);

				Item result = createItem("ExpressionScore");

				if (StringUtils.isNotEmpty(rpkm)) {

					try {
						Float.valueOf(rpkm);
						result.setAttribute("FPKM", rpkm);

					} catch (NumberFormatException e) {
						LOG.warn("bad score: " + rpkm, e);
					}					
					String expressionLevel = " "; //bin the score
					if(Float.valueOf(rpkm) <= 0.0){
						expressionLevel = "No expression";
					}else if(Float.valueOf(rpkm) >=1.0 && Float.valueOf(rpkm)<=10.0){
						expressionLevel = "Extremely low expression";
					}else if(Float.valueOf(rpkm) >=11.0 && Float.valueOf(rpkm) <=100.0){
						expressionLevel = "Very low expression";
					}else if(Float.valueOf(rpkm) >=101.0 && Float.valueOf(rpkm)<=400.0){
						expressionLevel = "Low expression";
					}else if(Float.valueOf(rpkm) >=401.0 && Float.valueOf(rpkm)<=1400.0){
						expressionLevel = "Moderate expression";
					}else if(Float.valueOf(rpkm) >=1401.0 && Float.valueOf(rpkm) <=4000.0){
						expressionLevel = "Moderately high expression";
					}else if(Float.valueOf(rpkm) >=4001.0 && Float.valueOf(rpkm) <=10000.0){
						expressionLevel = "High expression";
					}else if(Float.valueOf(rpkm) >=10001.0 && Float.valueOf(rpkm)<=100000.0){
						expressionLevel = "Very high expression";
					}else if(Float.valueOf(rpkm) > 100000.0){
						expressionLevel = "Extremely high expression";
					}

					String cond  = " ";
					String stage = "";
					String condition = " ";
					Integer order = null;

					if(stagecond.indexOf("_") > 0){
						String[] tmp = stagecond.split("_");
						stage = tmp[0];
						for(int k=1; k< tmp.length; k++){
							cond += tmp[k]+"_";
						}	
					    condition = cond.substring(0,cond.length()-1);
					}else{
						stage = stagecond;
					}
										

					if(stage.equalsIgnoreCase("Brain") || stage.equalsIgnoreCase("Heart") || 
							stage.equalsIgnoreCase("Kidney") || stage.equalsIgnoreCase("Liver") || 
							stage.equalsIgnoreCase("SkeletalMuscle")){
						Item item = getTissue(stage);
						result.setReference("tissue", item.getIdentifier());
					}  else {
						Item item = getDevStage(stage);
						result.setReference("devstage", item.getIdentifier());
						Integer stageOrder = orderStages.get(stagecond);
						String sorder =String.valueOf(stageOrder.intValue());
						result.setAttribute("order", sorder);
					}
					result.setAttribute("expressionLevel", expressionLevel);
					result.addToCollection("experiments", exp);
					result.setAttribute("stage", stagecond);  //was stage
					result.setAttribute("experimentDetail", condition);
				}

				if (StringUtils.isNotEmpty(gene)) {
					result.setReference("gene", gene);
					try {
						store(result);
					} catch (ObjectStoreException e) {
						throw new ObjectStoreException(e);
					}	
				}

			}  		

		}
	}



	/**
	 * 
	 * @param stage
	 * @return
	 * @throws ObjectStoreException
	 */
	private Item getTissue(String stage) throws ObjectStoreException {

		Item tissue = tissues.get(stage);
		if(tissue == null){
			tissue = createItem("Tissue");
			tissue.setAttribute("name", stage);
			try {
				store(tissue);
			} catch (ObjectStoreException e) {
				e.printStackTrace();
				throw new ObjectStoreException(e);
			}
			tissues.put(stage, tissue);
		}
		return tissue;

	}

	/**
	 * 
	 * @param stage
	 * @return
	 * @throws ObjectStoreException
	 */
	private Item getDevStage(String stage) throws ObjectStoreException {

		Item devstage = devstages.get(stage);
		if(devstage == null){
			devstage = createItem("DevelopmentalStage");
			devstage.setAttribute("name", stage);
			try {
				store(devstage);
			} catch (ObjectStoreException e) {
				e.printStackTrace();
				throw new ObjectStoreException(e);
			}
			devstages.put(stage, devstage);
		}
		return devstage;

	}
	/**
	 * 
	 * @param line
	 * @throws Exception
	 */
	private void readHeaderLine(String line) throws ObjectStoreException {         

		String[] vals = line.split("\t");

		//String stage;

		for(int i=1; i < vals.length; i++){

			//String cond  =" ";
			/*if(vals[i].indexOf("_") > 0){

				String[] tmp = vals[i].split("_");
				stage = tmp[0];
				//for(int j=1; j < tmp.length; j++){
					//cond += "_"+tmp[j];
				//}				
			}else{
				stage = vals[i];
			}

			//if tissue
			if(stage.equalsIgnoreCase("Brain") || stage.equalsIgnoreCase("Heart") || 
					stage.equalsIgnoreCase("Kidney") || stage.equalsIgnoreCase("Liver") || 
					stage.equalsIgnoreCase("SkeletalMuscle")){

				Item tissue = tissues.get(stage);
				if(tissue == null) {
					tissue = createItem("Tissue");
					tissue.setAttribute("name", stage);
					//tissue.setAttribute("condition", cond);
				}
				try {
					store(tissue);
				} catch (ObjectStoreException e) {
					e.printStackTrace();
					throw new ObjectStoreException(e);
				}
				tissues.put(stage, tissue);
			}  else {
				Item devstage = devstages.get(stage);
				if(devstage == null){
					devstage = createItem("DevelopmentalStage");
					devstage.setAttribute("name", stage);
					//devstage.setAttribute("condition", cond);
					try {
						store(devstage);
					} catch (ObjectStoreException e) {
						e.printStackTrace();
						throw new ObjectStoreException(e);
					}
					devstages.put(stage, devstage);
				}
			}*/

			header.put(new Integer(i), vals[i]); //stage              
		}       

	}


	private Item processMetaData(String[] md) throws ObjectStoreException {

		Item experiment = null;
		String experimentName = "";
		String experimentCategory = "";
		String experimentType = "";
		String experimentTitle = "";
		String experimentDescription = "";
		String experimentDate = "";
		String organism = "";
		String pmid = "";
		String dataset = "";


		for(int i=0; i< md.length; i++) {

			String[] line = md[i].split("\\:"); 

			String name = line[0].trim();
			String value = line[1].trim();

			if(name.equalsIgnoreCase("!Experiment Name")){
				experimentName = value;
			}else if (name.equalsIgnoreCase("!Experiment Category")){
				experimentCategory = value;
			}else if (name.equalsIgnoreCase("!Experiment Type")){
				experimentType = value;
			}else if (name.equalsIgnoreCase("!Experiment Title")){
				experimentTitle = value;
			}else if (name.equalsIgnoreCase("!Experiment Description")){
				experimentDescription = value;
			}else if (name.equalsIgnoreCase("!Experiment Date")){
				experimentDate = value;
			}else if (name.equalsIgnoreCase("!Organism")){
				organism = value;
			}else if (name.equalsIgnoreCase("!Experiment PMID")){
				pmid = value;
			}else if (name.equalsIgnoreCase("!Experiment DataSet")){
				dataset = value;
			}

		}

		experiment = getExperiment(experimentName, experimentCategory,  experimentType, experimentTitle, experimentDescription, 
				experimentDate, pmid, dataset);

		return experiment;

	}

	private Item getExperiment(String experimentName, String experimentCategory,  String experimentType, String experimentTitle, String experimentDescription, 
			String experimentDate, String pmid, String dataset) throws ObjectStoreException {

		Item pub = publications.get(pmid);
		if(pub == null){
			pub = createItem("Publication");
			pub.setAttribute("pubMedId", pmid);
			try {
				store(pub);
			} catch (ObjectStoreException e) {
				e.printStackTrace();
				throw new ObjectStoreException(e);
			}
			publications.put(pmid, pub);
		}

		Item ds = datasets.get(dataset);
		if(ds == null){
			ds = createItem("DataSet");
			ds.setAttribute("name", dataset);
			try {
				store(ds);
			} catch (ObjectStoreException e) {
				e.printStackTrace();
				throw new ObjectStoreException(e);
			}
			datasets.put(dataset, ds);
		}

		Item exp = createItem("Experiment");
		exp.setAttribute("name", experimentName);
		exp.setAttribute("category", experimentCategory);
		exp.setAttribute("experimentType", experimentType);
		exp.setAttribute("title", experimentTitle);
		exp.setAttribute("description", experimentDescription);
		exp.setAttribute("experimentDate", experimentDate);
		exp.setReference("organism", organism);
		exp.setReference("publication", pub.getIdentifier());
		exp.setReference("dataset", ds.getIdentifier());

		try {
			store(exp);
		} catch (ObjectStoreException e) {
			e.printStackTrace();
			throw new ObjectStoreException(e);
		}

		return exp;

	}

	/**
	 * 
	 * @param ens
	 * @return
	 * @throws ObjectStoreException
	 */
	private String getGene(String ens) throws ObjectStoreException {

		String identifier = ens;
		if (StringUtils.isEmpty(identifier)) {
			return null;
		}
		if (genes.containsKey(identifier)) {
			return genes.get(identifier);
		}
		Item gene = createItem("Gene");
		gene.setAttribute("primaryIdentifier", identifier);
		gene.setReference("organism", organism);
		String refId = gene.getIdentifier();
		genes.put(identifier, refId);
		store(gene);
		return refId;
	}
	
    /*private void loadStages() throws Exception {
            
    	orderStages.put("Oocyte", new Integer(0));
    	orderStages.put("2cell", new Integer(1));
    	orderStages.put("4cell", new Integer(2));
    	orderStages.put("8cell", new Integer(3));
    	orderStages.put("16cell", new Integer(4));
    	orderStages.put("Stage1", new Integer(5));
    	orderStages.put("Stage2", new Integer(6));
    	orderStages.put("Stage3", new Integer(7));
    	orderStages.put("Stage6", new Integer(8));
    	orderStages.put("Stage8", new Integer(9));
    	orderStages.put("Stage9", new Integer(10));
    	orderStages.put("Stage10.5", new Integer(11));
    	orderStages.put("Stage11-12", new Integer(12));
    	orderStages.put("Stage12", new Integer(13));
    	orderStages.put("Stage13-14", new Integer(14));
    	orderStages.put("Stage15", new Integer(15));
    	orderStages.put("Stage16", new Integer(16));
    	orderStages.put("Stage16-18", new Integer(17));
    	orderStages.put("Stage19", new Integer(18));
      	orderStages.put("Stage20", new Integer(19));
    	orderStages.put("Stage20-21", new Integer(20));
    	orderStages.put("Stage22-23", new Integer(21));
    	orderStages.put("Stage24-26", new Integer(22));
    	orderStages.put("Stage28", new Integer(23));
    	orderStages.put("Stage30", new Integer(24));
    	orderStages.put("Stage31-32", new Integer(25));
    	orderStages.put("Stage32", new Integer(26));
    	orderStages.put("Stage33-34", new Integer(27));
    	orderStages.put("Stage38-39", new Integer(28));
    	orderStages.put("Stage40", new Integer(29));
    	orderStages.put("Stage41", new Integer(30));
    	orderStages.put("Stage41-42", new Integer(31));
    	orderStages.put("Stage44-45", new Integer(32));
    	orderStages.put("SkeletalMuscle", new Integer(33));
    	    
    }*/      
 private void loadStages() throws Exception {
          	
    	orderStages.put("2cell_rep1", new Integer(1));
    	orderStages.put("4cell_rep1", new Integer(2));
    	orderStages.put("8cell_rep1", new Integer(3));
    	orderStages.put("16cell_rep1", new Integer(4));
    	orderStages.put("Stage6_rep1", new Integer(5));
    	orderStages.put("Stage8_rep1", new Integer(6));
    	orderStages.put("Stage9_rep1", new Integer(7));
    	orderStages.put("Stage9_rep2", new Integer(8));	
    	orderStages.put("Stage10.5_rep1", new Integer(9));
    	orderStages.put("Stage10.5_rep2", new Integer(10));
    	orderStages.put("Stage11-12_rep1", new Integer(11));
    	orderStages.put("Stage11-12_rep2", new Integer(12));
    	orderStages.put("Stage11-12_rep3", new Integer(13));
    	orderStages.put("Stage13-14_rep1", new Integer(14));
    	orderStages.put("Stage13-14_rep2", new Integer(15));
    	orderStages.put("Stage15_rep1", new Integer(16));
    	orderStages.put("Stage16-18_rep1", new Integer(17));
    	orderStages.put("Stage16-18_rep2", new Integer(18));
    	orderStages.put("Stage19_rep1", new Integer(19));
    	orderStages.put("Stage19_rep2", new Integer(20));
    	orderStages.put("Stage19_rep3", new Integer(21));
    	orderStages.put("Stage20-21_rep1", new Integer(22));
    	orderStages.put("Stage20-21_rep2", new Integer(23));   	
    	orderStages.put("Stage22-23_rep1", new Integer(24));
    	orderStages.put("Stage22-23_rep2", new Integer(25));
    	orderStages.put("Stage24-26_rep1", new Integer(26));
    	orderStages.put("Stage24-26_rep2", new Integer(27));
    	orderStages.put("Stage24-26_rep3", new Integer(28));
    	orderStages.put("Stage28_rep1", new Integer(29));
    	orderStages.put("Stage28_rep2", new Integer(30));  
    	orderStages.put("Stage31-32_rep1", new Integer(31));
    	orderStages.put("Stage31-32_rep2", new Integer(32));
    	orderStages.put("Stage33-34_rep1", new Integer(33));
    	orderStages.put("Stage33-34_rep2", new Integer(34));
    	orderStages.put("Stage38-39_rep1", new Integer(35));
    	orderStages.put("Stage40_rep1", new Integer(36));
     	orderStages.put("Stage40_rep2", new Integer(37));
    	orderStages.put("Stage41-42_rep1", new Integer(38));
    	orderStages.put("Stage41-42_rep2", new Integer(39));
    	orderStages.put("Stage44-45_rep1", new Integer(40));
    	
    	orderStages.put("Stage20", new Integer(1));
    	orderStages.put("Stage32_BB30KD_rep1", new Integer(2));
    	orderStages.put("Stage32_BB30KD_rep2", new Integer(3));
    	orderStages.put("Stage32_BB30KD_rep3", new Integer(4));
    	orderStages.put("Stage32_ctrl_rep1", new Integer(5));
    	orderStages.put("Stage32_ctrl_rep2", new Integer(6));
    	orderStages.put("Stage32_ctrl_rep3", new Integer(7));
    	
    	orderStages.put("Stage10.5_FoxH1_MO", new Integer(1));
    	orderStages.put("Stage10.5_SB431542_treated", new Integer(2));
    	orderStages.put("Stage10.5_solvent_only_control", new Integer(3));
    	orderStages.put("Stage10.5_uninjected_control", new Integer(4));
    	
    	
    	orderStages.put("Oocyte_polyA", new Integer(1));
    	orderStages.put("Oocyte_RiboZero", new Integer(2));
    	orderStages.put("SkeletalMuscle_polyA", new Integer(3));
    	orderStages.put("Stage6_polyA", new Integer(4));
    	orderStages.put("Stage6_RiboZero", new Integer(5));
    	orderStages.put("Stage9_polyA", new Integer(6));
    	orderStages.put("Stage9_RiboZero", new Integer(7)); 	
    	orderStages.put("Stage12_polyA", new Integer(8));
    	orderStages.put("Stage16_polyA", new Integer(9));
    	orderStages.put("Stage30_polyA", new Integer(10));
    	
    	orderStages.put("Stage1_00_0.0_polyA", new Integer(1));
    	orderStages.put("Stage1_01_0.5_polyA", new Integer(2));
    	orderStages.put("Stage1_02_1.0_polyA",  new Integer(3));	
    	orderStages.put("Stage1_03_1.5_polyA",  new Integer(4));	
    	orderStages.put("Stage1_04_2.0_polyA",  new Integer(5));	
    	orderStages.put("Stage1_05_2.5_polyA",  new Integer(6));
    	orderStages.put("Stage1_06_3.0_polyA", new Integer(7));	
    	orderStages.put("Stage2_00_2.5_polyA", new Integer(8));	
    	orderStages.put("Stage2_01_3.0_polyA", new Integer(9));	
    	orderStages.put("Stage2_02_3.5_polyA", new Integer(10));	
    	orderStages.put("Stage2_03_4.0_polyA", new Integer(11));	
    	orderStages.put("Stage2_04_4.5_polyA", new Integer(12));	
    	orderStages.put("Stage2_05_5.0_polyA", new Integer(13));	
    	orderStages.put("Stage2_06_5.5_polyA", new Integer(14));	
    	orderStages.put("Stage2_07_6.0_polyA", new Integer(15));		
    	orderStages.put("Stage2_08_6.5_polyA",new Integer(16));		
    	orderStages.put("Stage2_09_7.0_polyA", new Integer(17));		
    	orderStages.put("Stage2_10_7.5_polyA", new Integer(18));		
    	orderStages.put("Stage2_11_8.0_polyA", new Integer(19));		
    	orderStages.put("Stage2_12_8.5_polyA", new Integer(20));		
    	orderStages.put("Stage2_13_9.0_polyA", new Integer(21));		
    	orderStages.put("Stage2_14_9.5_polyA", new Integer(22));		
    	orderStages.put("Stage2_15_4.5_polyA", new Integer(23));		
    	orderStages.put("Stage2_16_6.5_polyA", new Integer(24));		
    	orderStages.put("Stage3_00_0.0_polyA", new Integer(25));		
    	orderStages.put("Stage3_01_0.5_polyA", new Integer(26));		
    	orderStages.put("Stage3_02_1.0_polyA", new Integer(27));	
    	orderStages.put("Stage3_03_1.5_polyA", new Integer(28));		
    	orderStages.put("Stage3_04_2.0_polyA", new Integer(29));		
    	orderStages.put("Stage3_05_2.5_polyA", new Integer(30));		
    	orderStages.put("Stage3_06_3.0_polyA", new Integer(31));		
    	orderStages.put("Stage3_07_3.5_polyA", new Integer(32));		
    	orderStages.put("Stage3_08_4.0_polyA", new Integer(33));		
    	orderStages.put("Stage3_09_4.5_polyA", new Integer(34));		
    	orderStages.put("Stage3_10_5.0_polyA", new Integer(35));		
    	orderStages.put("Stage3_11_5.5_polyA", new Integer(36));		
    	orderStages.put("Stage3_12_6.0_polyA", new Integer(37));		
    	orderStages.put("Stage3_13_6.5_polyA", new Integer(38));		
    	orderStages.put("Stage3_14_7.0_polyA", new Integer(39));		
    	orderStages.put("Stage3_15_7.5_polyA", new Integer(40));		
    	orderStages.put("Stage3_16_8.0_polyA", new Integer(41));		
    	orderStages.put("Stage3_17_8.5_polyA", new Integer(42));		
    	orderStages.put("Stage3_18_9.0_polyA", new Integer(43));		
    	orderStages.put("Stage3_19_0.0_rz", new Integer(44));		
    	orderStages.put("Stage3_20_1.0_rz", new Integer(45));		
    	orderStages.put("Stage3_21_2.0_rz", new Integer(46));		
    	orderStages.put("Stage3_22_3.0_rz", new Integer(47));		
    	orderStages.put("Stage3_23_4.0_rz", new Integer(48));		
    	orderStages.put("Stage3_24_5.0_rz", new Integer(49));		
    	orderStages.put("Stage3_25_6.0_rz", new Integer(50));		
    	orderStages.put("Stage3_26_7.0_rz", new Integer(51));		
    	orderStages.put("Stage3_27_8.0_rz", new Integer(52));	
    	
    	orderStages.put("Oocyte", new Integer(53));
    	orderStages.put("SkeletalMuscle", new Integer(54));
    	    
    }


	/**
	 * 
	 * @author kkarra
	 *
	 */
	class Stage {
		protected String name;
		protected String category;

		public Stage(String name, String category) {
			this.name = name;
			this.category = category;
		}
	}
}
