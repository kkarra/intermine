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

import java.io.Reader;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import org.intermine.dataconversion.ItemWriter;
import org.intermine.metadata.Model;
import org.intermine.xml.full.Item;
import org.intermine.dataconversion.ItemWriter;
import org.intermine.objectstore.ObjectStoreException;
import org.apache.commons.lang.StringUtils; 
import org.apache.log4j.Logger;
import org.intermine.util.FormattedTextParser;
import org.intermine.xml.full.ReferenceList;


/**
 * 
 * @author
 */
public class XenmineConverter extends BioDirectoryConverter
{
	//
	private static final String DATASET_TITLE = "XenBase ftp site files";
	private static final String DATA_SOURCE_NAME = "XenBase";
	private static final Logger LOG = Logger.getLogger(XenmineConverter.class);
	private Map<String, String> chromosomes = new HashMap();
	private Map<String, Item> genes = new HashMap();
	private Map<String, Item> organisms = new HashMap();
	private Map<String, HashSet> genesPageName = new HashMap();
	private Map<String, String> genesAliases = new HashMap();
	private Map<String, String> synonyms = new HashMap();
	private Map<String, String> datasources = new HashMap();
	private Map<String, Item> publications = new HashMap();
	private Map<String, String> pmid_xbartid = new HashMap();
	private Map<String, Item> terms = new HashMap(); //anatomy terms
	private Map<String, Item> imgs = new HashMap<String, Item>();
	private static final String URL
	= "http://www.fruitfly.org/insituimages/insitu_images/thumbnails/";

	private static final String TAXON_ID = "8364";
	private static final String GENOME_BUILD = "JGI 8.0";
	private static final String LAEVIS_BUILD = "JGI 7.1";

	//private Item organism;
	private Map<String, String> dataSets = new LinkedHashMap<String, String>();

	private static final String SCAFFOLD_MAPPING_FILE = "GenePageToJgiTropicalisScaffoldMapping_8.0.txt";
	private static final String LEAVIS_SCAFFOLD_MAPPING_FILE = "GenePageToJgiLaevisScaffoldMapping_7.1.txt";

	private static final String NAMES_FILE = "JgiToXenbaseGenePage_8.0.txt";

	private static final String SYNONYMS_FILE = "GenePageGeneralInfo_ManuallyCurated.txt";
	private static final String GENEPAGE_FILE = "XenbaseGenepageToGeneIdMapping.txt";

	private static final String INTERACTIONS_FILE = "GenePageInteractants.txt";
	private static final String LITERATURE_FILE = "LiteratureMatchedGenesByPaper.txt";
	private static final String GO_FILE = "GenePageGoTerms.txt";
	private static final String ENSEMBL_FILE = "GenePageEnsemblModelMapping.txt";

	private static final String MOUSE_HOMOLOG_FILE = "XenbaseGeneMouseOrthologMapping.txt";
	private static final String HUMAN_HOMOLOG_FILE = "XenbaseGeneHumanOrthologMapping.txt";
	private static final String ZEBRAFISH_HOMOLOG_FILE = "XenbaseGeneZebrafishOrthologMapping.txt";
	private static final String NON_ENTREZ_HOMOLOG_FILE = "XenbaseGeneNonEntrezOrthologMapping.txt";

	private static final String EXPRESSION_FILE = "GeneExpression_ALL.txt";

	private static final String NCBI_PROTEIN_FILE = "NcbiProteinXenbaseGene_ALL.txt";

	private static final String NCBI_MRNA_FILE = "NcbiMrnaXenbaseGene_ALL.txt";
	private static final String ENTREZ_UNIGENE_FILE = "GenePage_ALL_EntrezGeneUnigeneMapping.txt";

	private static final String ANATOMY_MAPPING_FILE = "GenePageAnatomyOntologyMapping.txt";


	protected String termClassName = "GOTerm";
	protected String termCollectionName = "goAnnotation";
	protected String annotationClassName = "GOAnnotation";


	private Map<String, String> featureMap = new HashMap();
	/**
	 * Constructor
	 * @param writer the ItemWriter used to handle the resultant items
	 * @param model the Model
	 */
	public XenmineConverter(ItemWriter writer, Model model) throws ObjectStoreException{
		super(writer, model, DATA_SOURCE_NAME, DATASET_TITLE);
	}

	/**
	 * 
	 *
	 * {@inheritDoc}
	 */
	public void process(File dataDir) throws Exception {


		Map<String, File> files = readFilesInDir(dataDir);

		String[] requiredFiles = new String[] {SCAFFOLD_MAPPING_FILE, LEAVIS_SCAFFOLD_MAPPING_FILE, 
				NAMES_FILE, SYNONYMS_FILE, GENEPAGE_FILE, INTERACTIONS_FILE, LITERATURE_FILE, GO_FILE, 
				ENSEMBL_FILE, MOUSE_HOMOLOG_FILE,HUMAN_HOMOLOG_FILE, ZEBRAFISH_HOMOLOG_FILE, NON_ENTREZ_HOMOLOG_FILE, 
				EXPRESSION_FILE, NCBI_PROTEIN_FILE, NCBI_MRNA_FILE, ENTREZ_UNIGENE_FILE, ANATOMY_MAPPING_FILE};

		Set<String> missingFiles = new HashSet<String>();
		for (String requiredFile : requiredFiles) {
			if (!files.containsKey(requiredFile)) {
				missingFiles.add(requiredFile);
			}
		}

		if (!missingFiles.isEmpty()) {
			throw new RuntimeException("Not all required files for the xenmine sources were found in: "
					+ dataDir.getAbsolutePath() + ", was missing " + missingFiles);
		}

		//IDs and symbols
		processScaffoldMappingFile(new FileReader(files.get(SCAFFOLD_MAPPING_FILE)), GENOME_BUILD);
			
		processLaevisScaffoldMappingFile(new FileReader(files.get(LEAVIS_SCAFFOLD_MAPPING_FILE)), LAEVIS_BUILD);

		processNameFile(new FileReader(files.get(NAMES_FILE)), GENOME_BUILD); 

		processGenePageFile(new FileReader(files.get(GENEPAGE_FILE)));  
		processSynFile(new FileReader(files.get(SYNONYMS_FILE)));  

		//annotation
		processLiteratureFile(new FileReader(files.get(LITERATURE_FILE)));		
	    processInteractionsFile(new FileReader(files.get(INTERACTIONS_FILE)));  
		
		processGoFile(new FileReader(files.get(GO_FILE))); 

		//cross-refs
		processEnsemblFile(new FileReader(files.get(ENSEMBL_FILE)));

		//homolog files
		processMouseOrthologFile(new FileReader(files.get(MOUSE_HOMOLOG_FILE)));
		processHumanOrthologFile(new FileReader(files.get(HUMAN_HOMOLOG_FILE)));
		processZebrafishOrthologFile(new FileReader(files.get(ZEBRAFISH_HOMOLOG_FILE)));	
		processNonEntrezOrthologFile(new FileReader(files.get(NON_ENTREZ_HOMOLOG_FILE)));

		//expression files
		processExpressionFile(new FileReader(files.get(EXPRESSION_FILE)));

		//cross-refs
		processNcbiProteinFile(new FileReader(files.get(NCBI_PROTEIN_FILE)));
		processNcbiMrnaFile(new FileReader(files.get(NCBI_MRNA_FILE)));
		processUnigeneEntrezFile(new FileReader(files.get(ENTREZ_UNIGENE_FILE)));

		processAnatomyMappingFile(new FileReader(files.get(ANATOMY_MAPPING_FILE)));

		storeGenes();  

	}

	/**
	 * 
	 * @param reader
	 * @throws Exception
	 * @throws ObjectStoreException
	 */
	private void processScaffoldMappingFile(Reader preader, String genomeBuild) throws Exception, ObjectStoreException {

		/* 	Xenbase gene ID	    
		 * gene symbol    
		 *  JGI Model Name    
		 *  JGI Scaffold Name   
		 *  JGI Scaffold Start Position	
		 *  JGI Scaffold End Position	
		 *  JGI Scaffold Strand 	
		 *  GenePageToJgiTropicalisScaffoldMapping_4.1.txt:XB-GENE-1021745	c1orf228	e_gw1.1.454.1	scaffold_1	4235	14986	-1
		 */   	 
		System.out.println("Processing scaffold file....");    

		Iterator<?> tsvIter;
		try {
			tsvIter = FormattedTextParser.parseTabDelimitedReader(preader);
		} catch (Exception e) {
			throw new Exception("cannot parse file: " + preader.toString(), e);
		}		

		Item organism = createItem("Organism");
		organism.setAttribute("taxonId", TAXON_ID);
		organism.setAttribute("genus", "Xenopus");
		organism.setAttribute("species", "tropicalis");
		organism.setAttribute("name", "Xenopus tropicalis");
		organism.setAttribute("shortName", "X. tropicalis");
		store(organism);

		while (tsvIter.hasNext()) {

			String[] line = (String[]) tsvIter.next();

			if (line.length < 7) {
				LOG.error("Couldn't process line. Expected 7 cols, but was " + line.length);
				continue;
			}

			String xbGeneId =  line[0].trim();     
			String symbol = line[1].trim();
			String jgimodelName =  line[2].trim();
			String chromosome =  line[3].trim(); 
			String start =  line[4].trim();
			String end = line[5].trim();
			String strand = line[6].trim();
			String length = getLength(start, end);

			Item gene = createItem("Gene");
			gene.setAttribute("primaryIdentifier", jgimodelName);
			gene.setAttribute("secondaryIdentifier", xbGeneId);
			gene.setAttribute("symbol", symbol);
			gene.setAttribute("genomeBuild", genomeBuild);

			String chrRefId = getChromosome(chromosome, organism.getIdentifier());
			gene.setReference("chromosome", chrRefId);
			String locationRefId = getLocation(gene, chrRefId, start, end, strand); 
			gene.setReference("chromosomeLocation", locationRefId);

			gene.setAttribute("jgiModelName", jgimodelName);
			gene.setReference("organism", organism);

			genes.put(xbGeneId, gene); 

		}

		preader.close();

	}


	/**
	 * 
	 * @param reader
	 * @throws Exception
	 * @throws ObjectStoreException
	 */
	private void processLaevisScaffoldMappingFile(Reader preader, String genomeBuild) throws Exception, ObjectStoreException {

		/* 	Xenbase gene ID	    
		 * gene symbol    
		 *  JGI Model Name    
		 *  JGI Scaffold Name   
		 *  JGI Scaffold Start Position	
		 *  JGI Scaffold End Position	
		 *  JGI Scaffold Strand 	
		 *  XB-GENE-478732  impdh2  XeXenL6RMv10052779m.g   Scaffold87688   113849  139524  -1
		 */   	 
		System.out.println("Processing laevis scaffold file....");    

		Iterator<?> tsvIter;
		try {
			tsvIter = FormattedTextParser.parseTabDelimitedReader(preader);
		} catch (Exception e) {
			throw new Exception("cannot parse file: " + preader.toString(), e);
		}

		Item organism = createItem("Organism");
		organism.setAttribute("taxonId", "8355");
		organism.setAttribute("genus", "Xenopus");
		organism.setAttribute("species", "laevis");
		organism.setAttribute("name", "Xenopus laevis");
		organism.setAttribute("shortName", "X. laevis");
		store(organism);

		while (tsvIter.hasNext()) {

			String[] line = (String[]) tsvIter.next();

			if (line.length < 7) {
				LOG.error("Couldn't process line. Expected 7 cols, but was " + line.length);
				continue;
			}

			String xbGeneId =  line[0].trim();     
			String symbol = line[1].trim();
			String jgimodelName =  line[2].trim();
			String chromosome =  line[3].trim(); 
			String start =  line[4].trim();
			String end = line[5].trim();
			String strand = line[6].trim();
			String length = getLength(start, end);

			Item gene = createItem("Gene");
			gene.setAttribute("primaryIdentifier", jgimodelName);
			gene.setAttribute("secondaryIdentifier", xbGeneId);
			if(! symbol.equalsIgnoreCase("unnamed")) gene.setAttribute("symbol", symbol);
			gene.setAttribute("genomeBuild", genomeBuild);

			String chrRefId = getChromosome(chromosome, organism.getIdentifier());
			gene.setReference("chromosome", chrRefId);

			String locationRefId = getLocation(gene, chrRefId, start, end, strand); 
			gene.setReference("chromosomeLocation", locationRefId);

			gene.setAttribute("jgiModelName", jgimodelName);
			gene.setReference("organism", organism);

			genes.put(xbGeneId, gene);

		}

		preader.close();

	}


	private Map<String, File> readFilesInDir(File dir) {
		Map<String, File> files = new HashMap<String, File>();
		for (File file : dir.listFiles()) {
			files.put(file.getName(), file);
		}
		return files;
	}

	/**
	 * 
	 * @param reader
	 * @throws Exception
	 * @throws ObjectStoreException
	 */
	private void processNameFile(Reader preader, String genomeBuild) throws Exception, ObjectStoreException {

		/* model name     
		 * Xenbase tropicalis gene ID     
		 * gene symbol     
		 * gene name	
		 */   	 
		System.out.println("Processing Names file....");    

		Iterator<?> tsvIter;
		try {
			tsvIter = FormattedTextParser.parseTabDelimitedReader(preader);
		} catch (Exception e) {
			throw new Exception("cannot parse file: " + preader.toString(), e);
		}

		while (tsvIter.hasNext()) {

			String[] line = (String[]) tsvIter.next();

			if (line.length < 4) {
				LOG.error("Couldn't process line. Expected 4 cols, but was " + line.length);
				continue;
			}

			String jgimodelname =  line[0].trim();     
			String primaryIdentifier = line[1].trim();
			String symbol =  line[2].trim();
			String name =  line[3].trim(); 

			Item gene = genes.get(primaryIdentifier);

			if(gene != null){
				gene.setAttribute("name", name);				
			}

		}

		preader.close();

	}

	/**
	 * 
	 * @param reader
	 * @throws Exception
	 * @throws ObjectStoreException
	 */
	private void processNcbiProteinFile(Reader preader) throws Exception, ObjectStoreException {

		/* gi    
		 * accession   
		 * Xenbase gene ID     
		 * gene symbol
		 * 1000735 AAA84444        XB-GENE-865674  gsk3b
		 */   	 
		System.out.println("Processing NCBI-PROTEIN file....");    

		Iterator<?> tsvIter;
		try {
			tsvIter = FormattedTextParser.parseTabDelimitedReader(preader);
		} catch (Exception e) {
			throw new Exception("cannot parse file: " + preader.toString(), e);
		}

		while (tsvIter.hasNext()) {

			String[] line = (String[]) tsvIter.next();

			if (line.length < 4) {
				LOG.error("Couldn't process line. Expected 4 cols, but was " + line.length);
				continue;
			}

			String gi =  line[0].trim();     
			String accession = line[1].trim();
			String xenbaseGeneId =  line[2].trim();
			String symbol =  line[3].trim(); 

			//System.out.println("identifier.." + gi + " accession    " + accession);
			Item gene = genes.get(xenbaseGeneId);

			if(gene != null){
				getCrossReference(gene.getIdentifier(), gi, accession, "NCBI Protein");				
			}

		}

		preader.close();

	}
	/**
	 * 
	 * @param reader
	 * @throws Exception
	 * @throws ObjectStoreException
	 */
	private void processUnigeneEntrezFile(Reader preader) throws Exception, ObjectStoreException {

		/* Xenbase gene ID     
		 * gene symbol
		 * Entrez ID
		 * Unigene ID
		 * XB-GENE-478054  trnt1   394602  Str.7616
		 */   	 
		System.out.println("Processing UNIGENE ENTREZ file....");    

		Iterator<?> tsvIter;
		try {
			tsvIter = FormattedTextParser.parseTabDelimitedReader(preader);
		} catch (Exception e) {
			throw new Exception("cannot parse file: " + preader.toString(), e);
		}

		while (tsvIter.hasNext()) {

			String[] line = (String[]) tsvIter.next();

			if (line.length < 4) {
				LOG.error("Couldn't process line. Expected 4 cols, but was " + line.length);
				continue;
			}

			String xenbaseGeneId =  line[0].trim();     
			String symbol = line[1].trim();
			String entrezId =  line[2].trim();
			String unigeneId =  line[3].trim(); 

			Item gene = genes.get(xenbaseGeneId);

			if(gene != null){
				if(StringUtils.isNotEmpty(entrezId)) getCrossReference(gene.getIdentifier(), entrezId, "", "Entrez Gene ID");
				if(StringUtils.isNotEmpty(unigeneId)) getCrossReference(gene.getIdentifier(), unigeneId, "", "Unigene ID");
			}

		}

		preader.close();

	}

	private String getCrossReference(String subjectId, String id, String accession, String source)
			throws ObjectStoreException {

		String refId = "";
		Item crf = createItem("CrossReference");
		crf.setReference("subject", subjectId);
		crf.setAttribute("identifier", id);
		if(StringUtils.isNotEmpty(accession)){ crf.setAttribute("accession", accession); }

		String dsId = datasources.get(source);
		if (dsId == null) {
			Item ds = createItem("DataSource");
			ds.setAttribute("name", source);
			ds.setAttribute("url", "http://www.ncbi.nlm.nih.gov/entrez/");
			try {
				store(ds);
			} catch (ObjectStoreException e) {
				throw new ObjectStoreException(e);
			}

			crf.setReference("source", ds.getIdentifier());
			datasources.put(source, ds.getIdentifier());
		} else {
			crf.setReference("source", dsId);
		}

		try {
			store(crf);
		} catch (ObjectStoreException e) {
			throw new ObjectStoreException(e);
		}

		refId = crf.getIdentifier();
		return refId;

	}

	/**
	 * 
	 * @param reader
	 * @throws Exception
	 * @throws ObjectStoreException
	 */
	private void processNcbiMrnaFile(Reader preader) throws Exception, ObjectStoreException {


		/* gi    
		 * accession   
		 * Xenbase gene ID     
		 * gene symbol
		 * 1000735 AAA84444        XB-GENE-865674  gsk3b
		 */   	 
		System.out.println("Processing NCBI-MRNA file....");    

		Iterator<?> tsvIter;
		try {
			tsvIter = FormattedTextParser.parseTabDelimitedReader(preader);
		} catch (Exception e) {
			throw new Exception("cannot parse file: " + preader.toString(), e);
		}

		while (tsvIter.hasNext()) {

			String[] line = (String[]) tsvIter.next();

			if (line.length < 4) {
				LOG.error("Couldn't process line. Expected 4 cols, but was " + line.length);
				continue;
			}

			String gi =  line[0].trim();     
			String accession = line[1].trim();
			String xenbaseGeneId =  line[2].trim();
			String symbol =  line[3].trim(); 

			Item gene = genes.get(xenbaseGeneId);

			if(gene != null){
				getCrossReference(gene.getIdentifier(), gi, accession, "NCBI mRNA");				
			}

		}

		preader.close();

	}


	/**
	 * 
	 * @param reader
	 * @throws Exception
	 * @throws ObjectStoreException
	 */
	private void processInteractionsFile(Reader preader) throws Exception, ObjectStoreException {

		/* Xenbase gene ID	   
		 * gene symbol	  
		 *  interactant ID:interactant symbol:co-citation occurrence
		 */   	 
		System.out.println("Processing Interactants file....");    

		Iterator<?> tsvIter;
		try {
			tsvIter = FormattedTextParser.parseTabDelimitedReader(preader);
		} catch (Exception e) {
			throw new Exception("cannot parse file: " + preader.toString(), e);
		}

		while (tsvIter.hasNext()) {

			String[] line = (String[]) tsvIter.next();

			if (line.length < 3) {
				LOG.error("Couldn't process line. Expected 3 cols, but was " + line.length);
				continue;
			}

			String genePageId =  line[0].trim().substring(12);     
			String symbol = line[1].trim();
			String interactants = line[2].trim(); //genePageId-PMID:symbol:someNumber	

			if(interactants == null || interactants.length()==0 || interactants.equals("")){
				continue;
			}
			String[] interactingGenes = interactants.split(","); 

			//System.out.println("Gene Page.."+ genePageId + "  Length of inG.." + interactingGenes.length);

			HashSet geneIds = genesPageName.get(genePageId);
			Iterator it = geneIds.iterator();

			while(it.hasNext()){

				String geneId = (String) it.next();				
				Item gene = genes.get(geneId); 

				if(interactingGenes.length != 0) {

					for (int i = 0; i < interactingGenes.length; i++) {

						String gpid[] =  interactingGenes[i].split("\\:");
						String genePageIdInteractant = gpid[0];

						//System.out.println("gene interactant.." + genePageIdInteractant);

						HashSet interactingGeneIds = genesPageName.get(genePageIdInteractant);
						if(interactingGeneIds == null){
							continue;
						}
						Iterator itt = interactingGeneIds.iterator();

						while(itt.hasNext()){

							String interactingGeneId = (String) itt.next();				
							Item interactingGene = genes.get(interactingGeneId);

							if(gene != null && interactingGene != null) {
								getInteraction(gene.getIdentifier(), interactingGene.getIdentifier());
							}
						}

					}

				} 
			}

		}

		preader.close();

	}

	private void getInteraction(String refId, String gene2RefId) throws ObjectStoreException {

		//MultiKey key = new MultiKey(refId, gene2RefId);
		//Item interaction = interactionsnew.get(key);
		//if (interaction == null) {
		Item interaction = createItem("Interaction");
		interaction.setReference("gene1", refId);
		interaction.setReference("gene2", gene2RefId);
		//interactionsnew.put(key, interaction);
		store(interaction);
		//}
		//return interaction;
	}


	/**
	 * 
	 * @param reader
	 * @throws Exception
	 * @throws ObjectStoreException
	 */

	private void processExpressionFile(Reader preader) throws Exception, ObjectStoreException {

		/* Xenbase Gene ID - XB-GENE-865139	
		 * Symbol -  nr5a2-a	
		 * Genotype - wild type	
		 * Anatomical Tissue - XAO:0000132 liver and biliary system,XAO:0000467 dorsal pancreatic bud,XAO:0001103 ventral pancreatic bud,XAO:0003266 liver primordium	
		 * Start Stage - XAO:1000049 NF stage 35 and 36	
		 * End Stge - XAO:1000053 NF stage 41	
		 * assay - in situ hybridization	
		 * EvidenceID - Image - XB-IMG-26168	
		 * Expreriment ID - XB-EXP-6382	
		 * Source - Published	
		 * Literature ID - XB-ART-39030	
		 * Curation Status - Complete manual curation
		 */

		System.out.println("Processing Expression file....");    

		Iterator<?> tsvIter;
		try {
			tsvIter = FormattedTextParser.parseTabDelimitedReader(preader);
		} catch (Exception e) {
			throw new Exception("cannot parse file: " + preader.toString(), e);  //getCurrentFile()
		}

		while (tsvIter.hasNext()) {

			String[] line = (String[]) tsvIter.next();

			if (line.length < 12) {
				LOG.error("Couldn't process line. Expected 12 cols, but was " + line.length);
				continue;
			}

			String xenbaseGeneId =  line[0].trim();     
			String symbol = line[1].trim();
			String genotype = line[2].trim(); 

			String anatomicalTissue = line[3].trim();  
			String startStageFull = line[4].trim(); //XAO:1000049 NF stage 35 and 36
			String[] t = startStageFull.split("\\s");
			String startStage = t[0];

			String endStageFull = line[5].trim(); 
			String[] te = endStageFull.split("\\s");
			String endStage = te[0];

			String assay = line[6].trim(); 
			String imageId = line[7].trim(); 



			String experimentId = line[8].trim(); 
			String source = line[9].trim(); 

			String literatureId = line[10].trim();  //XB-ART-ID figure out how to merge based on PMID
			String pmid = pmid_xbartid.get(literatureId);
			String refId = getPub(pmid,literatureId);


			String status = line[11].trim(); 

			//figure out how to store this stuff
			Item expression = createItem("ExpressionResult");

			if(StringUtils.isNotEmpty(genotype)){expression.setAttribute("genotype", genotype);}
			if(StringUtils.isNotEmpty(assay)){expression.setAttribute("assay", assay);}
			if(StringUtils.isNotEmpty(experimentId)){expression.setAttribute("experimentId", experimentId);}
			if(StringUtils.isNotEmpty(source)){expression.setAttribute("source", source);}
			if(StringUtils.isNotEmpty(status)){expression.setAttribute("curationStatus", status);}

			Item sterm = getTerm(startStage);
			Item eterm = getTerm(endStage);
			expression.setReference("startStage", sterm.getIdentifier());
			expression.setReference("endStage", eterm.getIdentifier());

			expression.setReference("publication", refId);

			setImage(expression, URL + imageId+".jpg");

			setAnatomicalTissues(expression, anatomicalTissue, "anatomicalStages");

			Item gene = genes.get(xenbaseGeneId);
			if(gene != null) { //XB-GENE-478125 is not in other files
				expression.setReference("gene", gene.getIdentifier());
				store(expression); //don't store all other items created if there is no gene to associate with..?
			}

		}

		preader.close();

	}


	/**
	 * 
	 * @param reader
	 * @throws Exception
	 * @throws ObjectStoreException
	 */

	private void processAnatomyMappingFile(Reader preader) throws Exception, ObjectStoreException {

		/* Xenbase Gene ID
		 * gene symbol
		 * start stage
		 * end stage
		 * tissues
		 * XB-GENEPAGE-478053      trnt1   XAO:1000031 NF stage 10.5       XAO:1000009 frog       
		 *  XAO:0000001 ectoderm,XAO:0003024 head,XAO:0000129 intestine,XAO:0000133 liver,XAO:0000256 oocyte,
		 *  XAO:0000258 ovary,XAO:0000157 testis,XAO:0003004 whole organism
		 */

		System.out.println("Processing Anatomy Mapping file....");    

		Iterator<?> tsvIter;
		try {
			tsvIter = FormattedTextParser.parseTabDelimitedReader(preader);
		} catch (Exception e) {
			throw new Exception("cannot parse file: " + preader.toString(), e);  //getCurrentFile()
		}

		while (tsvIter.hasNext()) {

			String[] line = (String[]) tsvIter.next();

			String xenbaseGeneId =  line[0].trim().substring(12);     
			String symbol = line[1].trim();
			String startStageFull = line[2].trim(); //XAO:1000049 NF stage 35 and 36
			String[] t = startStageFull.split("\\s");
			String startStage = t[0];
			String endStageFull = line[3].trim(); 
			String[] te = endStageFull.split("\\s");
			String endStage = te[0];			
			String anatomicalTissue = line[4].trim();  
			System.out.println(xenbaseGeneId);
			HashSet geneIds =  genesPageName.get(xenbaseGeneId); //check this stuff with what is in genes..a thorough once over
			Iterator it = geneIds.iterator();
			while(it.hasNext()){

				String geneId = (String) it.next();				
				Item gene = genes.get(geneId); 
				if(gene != null){
					//figure out how to store this stuff
					Item anatomy = createItem("AnatomyMapping");
					Item sterm = getTerm(startStage);
					Item eterm = getTerm(endStage);
					anatomy.setReference("startStage", sterm.getIdentifier());
					anatomy.setReference("endStage", eterm.getIdentifier());
					setAnatomicalTissues(anatomy, anatomicalTissue, "tissues");								
					anatomy.setReference("gene", gene.getIdentifier());
					store(anatomy);     //don't store all other items created if there is no gene to associate with..?
				}
			}

		}

		preader.close();

	}


	private void setImage(Item result, String img) throws ObjectStoreException {

		Item item = imgs.get(img);

		if (item == null) {
			item = createItem("Image");
			item.setAttribute("url", img);
			imgs.put(img, item);
			store(item);
			result.setReference("image", item.getIdentifier());

		}else{
			result.setReference("image", item.getIdentifier());
		}

	}


	private void setAnatomicalTissues(Item result, String at, String collectionName) throws ObjectStoreException {

		//XAO:0000132 liver and biliary system, XAO:0000467 
		//dorsal pancreatic bud, XAO:0001103 ventral pancreatic bud, XAO:0003266 liver primordium

		String[] temp = at.split(",");
		for(int i=0; i < temp.length; i++ ){

			String[] t2 = temp[i].split("\\s");

			String term = t2[0];
			Item aterm = getTerm(term);

			if(aterm != null){
				result.addToCollection(collectionName, aterm.getIdentifier());
			}
		}

	}

	/**
	 * 
	 * @param term
	 * @return
	 * @throws ObjectStoreException
	 */

	private Item getTerm(String term) throws ObjectStoreException {

		Item storedRef = terms.get(term);

		if (storedRef == null) {

			storedRef = createItem("XenopusAnatomyTerm");

			if (StringUtils.isNotEmpty(term)) {
				storedRef.setAttribute("identifier", term);
			}
			store(storedRef);

			terms.put(term, storedRef);
		}

		return storedRef;
	}

	/**
	 * 
	 * @param reader
	 * @throws Exception
	 * @throws ObjectStoreException
	 */

	private void processLiteratureFile(Reader preader) throws Exception, ObjectStoreException {

		/* Xenbase Literature ID	   
		 * PMID	  
		 *  Xenbase genePage IDs associated with the PMID above
		 */

		System.out.println("Processing Literature file....");    

		Iterator<?> tsvIter;
		try {
			tsvIter = FormattedTextParser.parseTabDelimitedReader(preader);
		} catch (Exception e) {
			throw new Exception("cannot parse file: " + preader.toString(), e);  //getCurrentFile()
		}

		while (tsvIter.hasNext()) {

			String[] line = (String[]) tsvIter.next();

			if (line.length < 3) {
				LOG.error("Couldn't process line. Expected 3 cols, but was " + line.length);
				continue;
			}

			String literatureId =  line[0].trim().substring(7);     
			String pmid = line[1].trim();
			String litGenes = line[2].trim(); //XB-GENEPAGE-491748 elavl2,XB-GENEPAGE-481418 gdf1,XB-GENEPAGE-481799 elavl1		

			if(litGenes.isEmpty() || litGenes == null){
				System.out.println("empty 3rd column.");
				continue;

			}

			String storedRefId = getPub(pmid, literatureId);		

			String[] gids = litGenes.split(","); 

			if(gids.length != 0) {
				for (int i = 0; i < gids.length; i++) {

					String gpid[] =  gids[i].split("\\s");
					String genePageId = gpid[0].substring(12);

					HashSet geneIds =  genesPageName.get(genePageId); //check this stuff with what is in genes..a thorough once over
					Iterator it = geneIds.iterator();
					while(it.hasNext()){

						String geneId = (String) it.next();				
						Item gene = genes.get(geneId); 
						if(gene != null){
							gene.addToCollection("publications", storedRefId);
						}
					}

				}
			} else{ 

				String gpid[] =  litGenes.split("\\s");
				String genePageId = gpid[0].substring(12);
				HashSet geneIds = genesPageName.get(genePageId); //check this stuff with what is in genes..a thorough once over
				Iterator it = geneIds.iterator();
				while(it.hasNext()){

					String geneId = (String) it.next();				
					Item gene = genes.get(geneId); 
					if(gene != null){
						gene.addToCollection("publications", storedRefId);
					}
				}
			}


		}

		preader.close();

	}

	/**
	 * 
	 * @param reader
	 * @throws Exception
	 * @throws ObjectStoreException
	 */

	private void processGoFile(Reader preader) throws Exception, ObjectStoreException {

		/* Xenbase genepage ID     
		 * gene symbol     
		 * GO Ids  (comma separated)
		 */

		System.out.println("Processing GO file....");    

		Iterator<?> tsvIter;
		try {
			tsvIter = FormattedTextParser.parseTabDelimitedReader(preader);
		} catch (Exception e) {
			throw new Exception("cannot parse file: " + preader.toString(), e);  //getCurrentFile()
		}

		while (tsvIter.hasNext()) {

			String[] line = (String[]) tsvIter.next();

			if (line.length < 3) {
				LOG.error("Couldn't process line. Expected 3 cols, but was " + line.length);
				continue;
			}

			String genePageId =  line[0].trim().substring(12);     
			String symbol = line[1].trim();
			String goids = line[2].trim(); //GO:0005525,GO:0007264,GO:0015031

			HashSet geneIds = genesPageName.get(genePageId); 
			if(geneIds == null){
				System.out.println("genePageId: null for genes..");
				continue;
			}
			Iterator it = geneIds.iterator();
			while(it.hasNext()){

				String geneId = (String) it.next();				
				Item gene = genes.get(geneId); 
				//System.out.println("genePageId: " + genePageId + " geneId " + geneId);
				String[] gids = goids.split(","); 
				for (int i = 0; i < gids.length; i++) {
					String goTermIdentifier = gids[i];
					if(gene != null){
						createGoAnnotation(gene.getIdentifier(), gene, goTermIdentifier, "XenBase", "XenBase");
					}
				}

			}
		}

		preader.close();

	}

	/**
	 * 
	 * @param reader
	 * @throws Exception
	 * @throws ObjectStoreException
	 */
	private void processEnsemblFile(Reader preader) throws Exception, ObjectStoreException {

		/* Xenbase gene ID     
		 * gene symbol     
		 * description
		 * ENSXETG00000xxxx - ensembl ID
		 */   	 
		System.out.println("Processing Ensembl file....");    

		Iterator<?> tsvIter;
		try {
			tsvIter = FormattedTextParser.parseTabDelimitedReader(preader);
		} catch (Exception e) {
			throw new Exception("cannot parse file: " + preader.toString(), e);  //getCurrentFile()
		}

		while (tsvIter.hasNext()) {

			String[] line = (String[]) tsvIter.next();

			if (line.length < 4) {
				LOG.error("Couldn't process line. Expected 4 cols, but was " + line.length);
				continue;
			}

			String geneId =  line[0].trim();     
			String symbol = line[1].trim();
			String name = line[2].trim();
			String ensemblId = line[3].trim();

			Item gene = genes.get(geneId); 
			if(gene != null && ensemblId != null) {
				gene.setAttribute("ensemblIdentifier", ensemblId);
			}

		}

		preader.close();

	}


	/**
	 * 
	 * @param reader
	 * @throws Exception
	 * @throws ObjectStoreException
	 */
	private void processNonEntrezOrthologFile(Reader preader) throws Exception, ObjectStoreException {

		/* Xenbase 
		 * OMIM    
		 * MGI     
		 * ZFIN
		 */   	 
		System.out.println("Processing  Non Entrze Orthologs.. XenbaseGeneNonEntrezOrthologMapping.txt file....");    

		Iterator<?> tsvIter;
		try {
			tsvIter = FormattedTextParser.parseTabDelimitedReader(preader);
		} catch (Exception e) {
			throw new Exception("cannot parse file: " + preader.toString(), e);
		}

		String prevXenopusId = "";

		while (tsvIter.hasNext()) {

			String[] line = (String[]) tsvIter.next();

			if (line.length < 4) {
				LOG.error("Couldn't process line. Expected 4 cols, but was " + line.length);
				continue;
			}
			System.out.println(line[0].trim());

			String xenopusId =  line[0].trim().substring(8);    //it doesn;t say GENEPAGE!!
			String omimId = line[1].trim();
			String mgiId = line[2].trim();
			String zfinId = line[3].trim();
			String chickenId = line[4].trim();

			if(prevXenopusId == xenopusId){

				HashSet geneIds =  genesPageName.get(xenopusId);
				Iterator it = geneIds.iterator();

				while(it.hasNext()){

					String geneId = (String) it.next();				
					Item gene0 = genes.get(geneId); 
					if(gene0 != null){  

						if(!StringUtils.isEmpty(zfinId)){
							String gene3 = getGene(zfinId, "7955");
							if(gene3 !=null) processHomologues(gene0.getIdentifier(), gene3);
						}	

					}

				}	

			}else{

				HashSet geneIds =  genesPageName.get(xenopusId);
				Iterator it = geneIds.iterator();

				while(it.hasNext()){

					String geneId = (String) it.next();				
					Item gene0 = genes.get(geneId); 
					if(gene0 != null){  

						if (!StringUtils.isEmpty(omimId)){
							String gene1 = getGene(omimId, "9606");
							if(gene1 !=null) processHomologues(gene0.getIdentifier(), gene1);
						}

						if(!StringUtils.isEmpty(mgiId)){		   
							String gene2 = getGene(mgiId, "10090");
							if(gene2!= null) processHomologues(gene0.getIdentifier(), gene2);
						}

						if(!StringUtils.isEmpty(zfinId)){
							String gene3 = getGene(zfinId, "7955");
							if(gene3 !=null) processHomologues(gene0.getIdentifier(), gene3);
						}	
						if(!StringUtils.isEmpty(chickenId)){
							String gene4 = getGene(chickenId, "9031");
							if(gene4 !=null) processHomologues(gene0.getIdentifier(), gene4);
						}	


					}

				}	
			}

			prevXenopusId = xenopusId;

		}

		preader.close();

	}


	/**
	 * 
	 * @param reader
	 * @throws Exception
	 * @throws ObjectStoreException
	 */
	private void processZebrafishOrthologFile(Reader preader) throws Exception, ObjectStoreException {

		/* entrez/NCBI gene ID     
		 * Xenbase GenePage     
		 * symbol
		 * name
		 */   	 
		System.out.println("Processing ZebraFish Ortholog file....");    

		Iterator<?> tsvIter;
		try {
			tsvIter = FormattedTextParser.parseTabDelimitedReader(preader);
		} catch (Exception e) {
			throw new Exception("cannot parse file: " + preader.toString(), e); 
		}

		while (tsvIter.hasNext()) {

			String[] line = (String[]) tsvIter.next();

			if (line.length < 4) {
				LOG.error("Couldn't process line. Expected 4 cols, but was " + line.length);
				continue;
			}

			String humanIdentifier =  line[0].trim(); 
			String xenopusIdentifier = line[1].trim().substring(12);

			if (StringUtils.isEmpty(xenopusIdentifier)
					|| StringUtils.isEmpty(humanIdentifier)) {
				continue;
			}

			String gene2 = getGene(humanIdentifier, "7955");

			HashSet geneIds =  genesPageName.get(xenopusIdentifier);
			Iterator it = geneIds.iterator();

			while(it.hasNext()){

				String geneId = (String) it.next();				
				Item gene1 = genes.get(geneId); 
				if(gene1 != null && gene2 !=null){  //lot of trouble..why should this check be required..does not make sense..spend time debug
					processHomologues(gene1.getIdentifier(), gene2);
				}

			}

		}

		preader.close();

	}

	/**
	 * 
	 * @param reader
	 * @throws Exception
	 * @throws ObjectStoreException
	 */
	private void processHumanOrthologFile(Reader preader) throws Exception, ObjectStoreException {


		/* entrez/NCBI gene ID     
		 * Xenbase GenePage     
		 * symbol
		 * name
		 */   	 
		System.out.println("Processing Human Ortholog file....");    

		Iterator<?> tsvIter;
		try {
			tsvIter = FormattedTextParser.parseTabDelimitedReader(preader);
		} catch (Exception e) {
			throw new Exception("cannot parse file: " + preader.toString(), e); 
		}

		while (tsvIter.hasNext()) {

			String[] line = (String[]) tsvIter.next();

			if (line.length < 4) {
				LOG.error("Couldn't process line. Expected 4 cols, but was " + line.length);
				continue;
			}

			String humanIdentifier =  line[0].trim(); 
			String xenopusIdentifier = line[1].trim().substring(12);

			if (StringUtils.isEmpty(xenopusIdentifier)
					|| StringUtils.isEmpty(humanIdentifier)) {
				continue;
			}

			String gene2 = getGene(humanIdentifier, "9606");		

			HashSet geneIds =  genesPageName.get(xenopusIdentifier);
			Iterator it = geneIds.iterator();

			while(it.hasNext()){

				String geneId = (String) it.next();				
				Item gene1 = genes.get(geneId); 
				if(gene1 != null && gene2 !=null){  //lot of trouble..why should this check be required..does not make sense..spend time debug
					processHomologues(gene1.getIdentifier(), gene2);
				}

			}

		}

		preader.close();

	}


	/**
	 * 
	 * @param reader
	 * @throws Exception
	 * @throws ObjectStoreException
	 */

	private void processMouseOrthologFile(Reader preader) throws Exception, ObjectStoreException {

		/* entrez/NCBI gene ID     
		 * Xenbase GenePage     
		 * symbol
		 * name
		 */

		System.out.println("Processing Mouse Ortholog file....");    

		Iterator<?> tsvIter;
		try {
			tsvIter = FormattedTextParser.parseTabDelimitedReader(preader);
		} catch (Exception e) {
			throw new Exception("cannot parse file: " + preader.toString(), e); 
		}

		while (tsvIter.hasNext()) {

			String[] line = (String[]) tsvIter.next();

			if (line.length < 4) {
				LOG.error("Couldn't process line. Expected 4 cols, but was " + line.length);
				continue;
			}

			String mouseIdentifier =  line[0].trim(); 
			String xenopusIdentifier = line[1].trim().substring(12);

			if (StringUtils.isEmpty(xenopusIdentifier)
					|| StringUtils.isEmpty(mouseIdentifier)) {
				continue;
			}

			String gene2 = getGene(mouseIdentifier, "10090");

			HashSet geneIds =  genesPageName.get(xenopusIdentifier);
			Iterator it = geneIds.iterator();

			while(it.hasNext()){

				String geneId = (String) it.next();				
				Item gene1 = genes.get(geneId); 
				if(gene1 != null && gene2 !=null){  //lot of trouble..why should this check be required..does not make sense..spend time debug
					processHomologues(gene1.getIdentifier(), gene2);
				}

			}

		}

		preader.close();

	}

	/**
	 * 
	 * @param productIdentifier
	 * @param productType
	 * @param termIdentifier
	 * @param organism
	 * @param dataSource
	 * @param dataSourceCode
	 * @throws ObjectStoreException
	 */
	private void createGoAnnotation(String productIdentifier, Item product,
			String termIdentifier, String dataSource,
			String dataSourceCode) throws ObjectStoreException {

		Item goTerm = createItem(termClassName);
		goTerm.setAttribute("identifier", termIdentifier);
		store(goTerm);

		Item goAnnotation = createItem(annotationClassName);
		goAnnotation.setReference("subject", productIdentifier);
		//goAnnotation.setReference("ontologyTerm", termIdentifier);
		goAnnotation.setReference("ontologyTerm", goTerm.getIdentifier());

		product.addToCollection("goAnnotation", goAnnotation.getIdentifier());

		store(goAnnotation);

	}	

	/**
	 * 
	 * @param dataSource
	 * @param code
	 * @return
	 * @throws ObjectStoreException
	 */
	private String getDataset(String dataSource, String code)
			throws ObjectStoreException {
		String dataSetIdentifier = dataSets.get(code);
		if (dataSetIdentifier == null) {               
			String title = "GO Annotation from " + dataSource;
			Item item = createItem("DataSet");
			item.setAttribute("name", title);
			item.setReference("dataSource", dataSource);
			dataSetIdentifier = item.getIdentifier();
			dataSets.put(code, dataSetIdentifier);
			store(item);
		}
		return dataSetIdentifier;
	}

	/**
	 * 
	 * @param reader
	 * @throws Exception
	 * @throws ObjectStoreException
	 */
	private void processGenePageFile(Reader preader) throws Exception, ObjectStoreException {

		/* XB-GENEPAGE ID     
		 * XB-GENE IDs for tropicalis and laevis	
		 * The XB-GENE IDs are comma-separated
		 * It seems like first one is tropicals, rest are laevis (a & b) 
		 */   	 
		System.out.println("Processing Gene Page file....");    

		Iterator<?> tsvIter;
		try {
			tsvIter = FormattedTextParser.parseTabDelimitedReader(preader);
		} catch (Exception e) {
			throw new Exception("cannot parse file: " + preader.toString(), e);
		}

		while (tsvIter.hasNext()) {

			String[] line = (String[]) tsvIter.next();

			if (line.length < 2) {
				LOG.error("Couldn't process line. Expected 2 cols, but was " + line.length);
				continue;
			}

			String genePageName =  line[0].trim().substring(12);     
			String geneIds = line[1].trim();		

			if(geneIds.indexOf(",") != -1) {
				String[] refs = geneIds.split(",");
				for(int i=0; i<refs.length; i++){
					addGenePageId(refs[i], genePageName);
				}				

			}else{
				addGenePageId(geneIds, genePageName);
			}

		}
		preader.close();
		
		//print out contents
		for(Map.Entry<String, HashSet> entry : genesPageName.entrySet()){
			System.out.printf("Key : %s and Value: %s %n", entry.getKey(), entry.getValue());
		}

	}


	/**
	 * 
	 * @param Id
	 * @throws Exception
	 * @throws ObjectStoreException
	 */
	private void addGenePageId(String Id, String genePageName) throws Exception, ObjectStoreException {

		Item gene = genes.get(Id);
		if(gene != null){
			gene.setAttribute("xenbaseGeneId", genePageName);				
		}

		if (genesPageName.containsKey(new String(genePageName))) {
			HashSet old = (HashSet) genesPageName.get(new String(genePageName));
			old.add(new String(Id));
			genesPageName.put(new String(genePageName), old);

		} else {
			HashSet set = new HashSet();
			set.add(new String(Id));
			genesPageName.put(new String(genePageName), set);
		}

	}

	/**
	 * 
	 * @param reader
	 * @throws Exception
	 * @throws ObjectStoreException
	 */

	private void processSynFile(Reader preader) throws Exception, ObjectStoreException {

		/* Xenbase gene ID     
		 * gene symbol    
		 *  gene name     
		 *  gene function -- will store as description    
		 *  gene synonyms 	
		 *  JGI ID  -- missing from the file --not required
		 */

		System.out.println("Processing Synonym file....");    

		Iterator<?> tsvIter;
		try {
			tsvIter = FormattedTextParser.parseTabDelimitedReader(preader);
		} catch (Exception e) {
			throw new Exception("cannot parse file: " + preader.toString(), e);
		}

		while (tsvIter.hasNext()) {

			String[] line = (String[]) tsvIter.next();

			if (line.length < 5) {
				LOG.error("Couldn't process line. Expected 5 cols, but was " + line.length);
				continue;
			}

			String genePageId =  line[0].trim().substring(12);     
			String symbol = line[1].trim();
			String name =  line[2].trim();
			String desc =  line[3].trim(); 
			String synonyms =  line[4].trim(); 


			HashSet geneIds =  genesPageName.get(genePageId);
			Iterator it = geneIds.iterator();

			while(it.hasNext()){

				String geneId = (String) it.next();				
				Item gene = genes.get(geneId); 


				if(gene != null){

					if (desc != null && !StringUtils.isEmpty(desc)) {
						gene.setAttribute("briefDescription", desc);	
					}

					if (synonyms != null && !StringUtils.isEmpty(synonyms)) {

						gene.setAttribute("alias", synonyms);	

						if(synonyms.indexOf("|") != -1) {					
							String[] syns = synonyms.split("\\|"); 
							for (int i = 0; i < syns.length; i++) {
								getSynonym(gene.getIdentifier(), syns[i]);
							}
						}else{
							getSynonym(gene.getIdentifier(), synonyms);
						}

					}

				}else{
					System.out.println("gene page id for a gene that is not laoded in the prev file.." + genePageId);
				}

			}

		}

		preader.close();

	}

	/**
	 * 
	 * @param subjectId
	 * @param value
	 * @return
	 * @throws ObjectStoreException
	 */
	private String getSynonym(String subjectId, String value)
			throws ObjectStoreException {

		if (StringUtils.isEmpty(value)) {
			return null;
		}
		String refId = synonyms.get(value);
		if (refId == null) {
			Item syn = createItem("Synonym");
			syn.setReference("subject", subjectId);
			syn.setAttribute("value", value);
			refId = syn.getIdentifier();
			try {
				store(syn);
			} catch (ObjectStoreException e) {
				throw new ObjectStoreException(e);
			}
		}
		return refId;
	}


	/**
	 * 
	 * @param identifier
	 * @return
	 * @throws ObjectStoreException
	 */
	private String getChromosome(String identifier, String orgId) throws ObjectStoreException {
		if (StringUtils.isEmpty(identifier)) {
			return null;
		}
		String refId = chromosomes.get(identifier);
		if (refId == null) {
			Item item = createItem("Chromosome");
			item.setAttribute("primaryIdentifier", identifier);
			item.setReference("organism", orgId);
			refId = item.getIdentifier();
			chromosomes.put(identifier, refId);
			try {
				store(item);
			} catch (ObjectStoreException e) {
				throw new ObjectStoreException(e);
			}
		}
		return refId;
	}

	/**
	 * 
	 * @param subject
	 * @param chromosomeRefId
	 * @param startCoord
	 * @param stopCoord
	 * @param strand
	 * @return
	 * @throws ObjectStoreException
	 */
	private String getLocation(Item subject, String chromosomeRefId,
			String startCoord, String stopCoord, String strand)
					throws ObjectStoreException {

		String start = startCoord;
		String end = stopCoord;

		if (!StringUtils.isEmpty(start) && !StringUtils.isEmpty(end)) {
			subject.setAttribute("length", getLength(start, end));
		}

		Item location = createItem("Location");

		if (!StringUtils.isEmpty(start))
			location.setAttribute("start", start);
		if (!StringUtils.isEmpty(end))
			location.setAttribute("end", end);
		if (!StringUtils.isEmpty(strand))
			location.setAttribute("strand", strand);

		location.setReference("feature", subject);
		location.setReference("locatedOn", chromosomeRefId);

		try {
			store(location);
		} catch (ObjectStoreException e) {
			throw new ObjectStoreException(e);
		}
		return location.getIdentifier();
	}

	/**
	 * 
	 * @param start
	 * @param end
	 * @return
	 * @throws NumberFormatException
	 */
	private String getLength(String start, String end)
			throws NumberFormatException {
		Integer a = new Integer(start);
		Integer b = new Integer(end);

		if (a.compareTo(b) > 0) {
			a = new Integer(end);
			b = new Integer(start);
		}

		Integer length = new Integer(b.intValue() - a.intValue());
		return length.toString();
	}

	/**
	 * 
	 * @param subjectId
	 * @param id
	 * @param source
	 * @return
	 * @throws ObjectStoreException
	 */
	private String getCrossReference(String subjectId, String id, String source)
			throws ObjectStoreException {

		String refId = "";
		Item crf = createItem("CrossReference");
		crf.setReference("subject", subjectId);
		crf.setAttribute("identifier", id);
		//crf.setAttribute("dbxrefsource", source);

		String dsId = datasources.get(source);
		if (dsId == null) {
			Item ds = createItem("DataSource");
			ds.setAttribute("name", source);
			try {
				store(ds);
			} catch (ObjectStoreException e) {
				throw new ObjectStoreException(e);
			}

			crf.setReference("source", ds.getIdentifier());
			datasources.put(source, ds.getIdentifier());
		} else {
			crf.setReference("source", dsId);
		}

		try {
			store(crf);
		} catch (ObjectStoreException e) {
			throw new ObjectStoreException(e);
		}

		refId = crf.getIdentifier();
		return refId;

	}

	private String getPub(String pubMedId, String literatureId)
			throws ObjectStoreException {

		Item storedRef = publications.get(pubMedId);

		if (storedRef == null) {

			storedRef = createItem("Publication");

			if (StringUtils.isNotEmpty(pubMedId)) {
				storedRef.setAttribute("pubMedId", pubMedId);
				storedRef.setAttribute("DbPubId", literatureId);
			}
			store(storedRef);

			publications.put(pubMedId, storedRef);
			pmid_xbartid.put(literatureId, pubMedId);
		}

		return storedRef.getIdentifier();

	}

	/**
	 * 
	 * @param geneId
	 * @param taxonId
	 * @return
	 * @throws ObjectStoreException
	 */
	private String getGene(String geneId, String taxonId)  throws ObjectStoreException {

		String identifierType = "primaryIdentifier";          
		Item gene = genes.get(geneId);          

		if (gene == null) {
			gene = createItem("Gene");
			gene.setAttribute(identifierType, geneId);
			if(taxonId != null) {
				gene.setReference("organism", getOrganism(taxonId));
			}
			genes.put(geneId, gene);

		}
		return gene.getIdentifier();
	}

	/**
	 * 
	 * @param gene1
	 * @param gene2
	 * @throws ObjectStoreException
	 */
	private void processHomologues(String gene1, String gene2)
			throws ObjectStoreException {
		if (gene1 == null || gene2 == null) {
			return;
		}
		Item homologue = createItem("Homologue");
		homologue.setReference("gene", gene1);
		homologue.setReference("homologue", gene2);
		store(homologue);
	}

	/**
	 * 
	 * @throws ObjectStoreException
	 */

	private void storeGenes() throws ObjectStoreException {
		for (Item gene : genes.values()) {
			try {
				store(gene);
			} catch (ObjectStoreException e) {
				throw new ObjectStoreException(e);
			}
		}
	}


}
