package org.intermine.bio.dataconversion;

/*
 * Copyright (C) 2002-2010 FlyMine
 *
 * This code may be freely distributed and modified under the
 * terms of the GNU Lesser General Public Licence.  This should
 * be distributed with the code.  See the LICENSE file for more
 * information or http://www.gnu.org/copyleft/lesser.html.
 *
 */

import java.sql.Connection;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.keyvalue.MultiKey;
import org.apache.commons.lang.StringUtils; 
//import org.biojava.bio.program.homologene.OrthoPairSet.Iterator;
import org.intermine.dataconversion.ItemWriter;
import org.intermine.metadata.Model;
import org.intermine.objectstore.ObjectStoreException;
import org.intermine.sql.Database;
import org.intermine.xml.full.Item;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Iterator;

/**
 * Converts results sets into intermine objects
 * 
 * @author Julie Sullivan
 */
public class SgdConverter extends BioDBConverter {

	// private static final Logger LOG = Logger.getLogger(SgdConverter.class);
	private static final String DATASET_TITLE = "SGD data set";
	private static final String DATA_SOURCE_NAME = "SGD";
	private Map<String, String> chromosomes = new HashMap();
	private Map<String, String> plasmids = new HashMap();
	private Map<String, String> sequences = new HashMap();
	private Map<String, Item> interactions = new HashMap();
	private Map<String, String> interactionterms = new HashMap<String, String>();
	private Map<MultiKey, Item> interactionsnew = new HashMap<MultiKey, Item>();
	private final Map<String, Item> ecoMap = new HashMap<String, Item>(); //regulation data
	private Map<String, String> literatureTopics = new HashMap();
	private Map<String, Item> genes = new HashMap();
	private Map<String, Item> proteins = new HashMap();
	private Map<String, Item> genesName = new HashMap();
	private Map<String, String> genesAliases = new HashMap();
	private Map<String, String> synonyms = new HashMap();
	private Map<String, Item> publications = new HashMap();
	private Map<String, Item> interactiontype = new HashMap();
	private Map<String, Item> interactiondetail = new HashMap();
	private Map<String, Item> experimenttype = new HashMap();
	private Map<String, Item> interactiondetectionmethods = new HashMap();
	private Map<String, Item> pathways = new HashMap();
	private Map<String, Item> phenotypes = new HashMap();
	private Map<String, Item> phenotypeannots = new HashMap();
	private Map<String, Item> phenotypeconds = new HashMap();
	private Map<String, String> datasources = new HashMap();
	private static final String TAXON_ID = "4932";
	private Item organism;
	private Map<String, String> featureMap = new HashMap();
	private static final boolean TEST_LOCAL = true;


	private static final SgdProcessor PROCESSOR = new SgdProcessor();

	/**
	 * Construct a new SgdConverter.
	 * 
	 * @param database
	 *            the database to read from
	 * @param model
	 *            the Model used by the object store we will write to with the
	 *            ItemWriter
	 * @param writer
	 *            an ItemWriter used to handle Items created
	 * @throws ObjectStoreException
	 *             if organism can't be stored
	 */
	public SgdConverter(Database database, Model model, ItemWriter writer)
			throws ObjectStoreException {
		super(database, model, writer, DATA_SOURCE_NAME, DATASET_TITLE);
		organism = createItem("Organism");
		organism.setAttribute("taxonId", TAXON_ID);
		organism.setAttribute("genus", "Saccharomyces");
		organism.setAttribute("species", "cerevisiae");
		organism.setAttribute("name", "Saccharomyces cerevisiae");
		organism.setAttribute("shortName", "S. cerevisiae");
		store(organism);
	}

	/**
	 * {@inheritDoc}
	 */
	public void process() throws Exception {

		// a database has been initialized from properties starting with db.sgd
		Connection connection = getDatabase().getConnection();

		processChromosomeSequences(connection);
		processGenes(connection);
		processNISS(connection);
		processAliases(connection);
		processCrossReferences(connection);
		processGeneLocations(connection);
		processGeneChildrenLocations(connection);	
		processProteins(connection);
		processAllPubs(connection);           						
		processPubsWithFeatures(connection);  
		processParalogs(connection);
		//processRegulation(connection);
		//processRegulationSummary(connection);
		processFunctionSummary(connection);
		//processGeneSummary(connection);
				
	    if(TEST_LOCAL) {
				
			processPathways(connection);
			storePathways();
			
			processPhysicalInteractions(connection);
			processGeneticInteractions(connection);
			storeInteractionTypes();
			storeInteractionExperiments();
			storeInteractions();
			
			processPhenotypes(connection);
			processPubsForPhenotypes(connection);
			processPhenotypeSummary(connection);
			storePhenotypeAnnotations();
		
		}
		storePublications();
		storeGenes();
		storeProteins();


	}

	/**
	 * 
	 * @param connection
	 * @throws SQLException
	 * @throws ObjectStoreException
	 */

	private void processGenes(Connection connection) throws SQLException,
	ObjectStoreException {

		System.out.println("Processing Genes...");
		ResultSet res = PROCESSOR.getChromosomalFeatureResults(connection);
		
		while (res.next()) {

			String featureNo = res.getString("dbentity_id");
			if (genes.get(featureNo) == null) {
				//~~~ gene ~~~
				String primaryIdentifier = res.getString("sgdid");
				String secondaryIdentifier = res.getString("systematic_name");
				String symbol = res.getString("gene_name");
				String name = res.getString("name_description");
				String feature_type = res.getString("feature_type");
				String headline = res.getString("headline");
				String description = res.getString("description");
				String qualifier = res.getString("qualifier");
				//String feat_attribute = res.getString("feat_attribute");
				String status = res.getString("dbentity_status");

				Item item = null;
				if (feature_type.equalsIgnoreCase("ORF")) {
					item = createItem("ORF");
				} else if (feature_type.equalsIgnoreCase("pseudogene")) {
					item = createItem("Pseudogene");
				} else if (feature_type
						.equalsIgnoreCase("transposable_element_gene")) {
					item = createItem("TransposableElementGene");
				} else if (feature_type
						.equalsIgnoreCase("not physically mapped")) {
					item = createItem("NotPhysicallyMapped");
				} else if (feature_type
						.equalsIgnoreCase("long_terminal_repeat")) {
					item = createItem("LongTerminalRepeat");
				} else if (feature_type.equalsIgnoreCase("ARS")) {
					item = createItem("ARS");
				} else if (feature_type.equalsIgnoreCase("tRNA_gene")) {
					item = createItem("TRNAGene");
				} else if (feature_type.equalsIgnoreCase("snoRNA_gene")) {
					item = createItem("SnoRNAGene");
				} else if (feature_type.equalsIgnoreCase("not in systematic sequence of S288C")) {
					item = createItem("NotInSystematicSequenceOfS288C");
				} else if (feature_type.equalsIgnoreCase("LTR_retrotransposon")) {
					item = createItem("Retrotransposon");
				} else if (feature_type.equalsIgnoreCase("X_element_combinatorial_repeats")) {
					item = createItem("XElementCombinatorialRepeat");
				} else if (feature_type.equalsIgnoreCase("X_element")) {
					item = createItem("XElement");
				} else if (feature_type.equalsIgnoreCase("telomere")) {
					item = createItem("Telomere");
				} else if (feature_type.equalsIgnoreCase("telomeric_repeat")) {
					item = createItem("TelomericRepeat");
				} else if (feature_type.equalsIgnoreCase("rRNA_gene")) {
					item = createItem("RRNAGene");
				} else if (feature_type.equalsIgnoreCase("Y_prime_element")) {
					item = createItem("YPrimeElement");
				} else if (feature_type.equalsIgnoreCase("centromere")) {
					item = createItem("Centromere");
				} else if (feature_type.equalsIgnoreCase("ncRNA_gene")) {
					item = createItem("NcRNAGene");
				} else if (feature_type.equalsIgnoreCase("snRNA_gene")) {
					item = createItem("SnRNAGene");
				}else if (feature_type.equalsIgnoreCase("blocked_reading_frame")) {
					item = createItem("BlockedReadingFrame");
				}else if (feature_type.equalsIgnoreCase("origin_of_replication")) {
					item = createItem("OriginOfReplication");
				}else if (feature_type.equalsIgnoreCase("matrix_attachment_site")) {
					item = createItem("MatrixAttachmentSite");
				}else if (feature_type.equalsIgnoreCase("telomerase_RNA_gene")) {
					item = createItem("TelomeraseRNAGene");
				}else if (feature_type.equalsIgnoreCase("gene_group")) {
					item = createItem("GeneGroup");
				}else if (feature_type.equalsIgnoreCase("silent_mating_type_cassette_array")) {
					item = createItem("SilentMatingTypeCassetteArray");
				}else if (feature_type.equalsIgnoreCase("mating_type_region")) {
					item = createItem("MatingTypeRegion");
				}else if (feature_type.equalsIgnoreCase("intein_encoding_region")) {
					item = createItem("InteinEncodingRegion");
				}

				// set for all types, so you can use LSF to query for these
				// different type of objects in a template.
				item.setAttribute("featureType", feature_type);
				item.setAttribute("primaryIdentifier", primaryIdentifier);
				if (StringUtils.isNotEmpty(name)) item.setAttribute("name", name);
				item.setAttribute("secondaryIdentifier", secondaryIdentifier);
				item.setReference("organism", organism);
				if (StringUtils.isNotEmpty(symbol)) item.setAttribute("symbol", symbol);				
				if (StringUtils.isNotEmpty(description)) item.setAttribute("description", description);
				if (StringUtils.isNotEmpty(headline)) item.setAttribute("briefDescription", headline);				
				if (qualifier != null) {
					if (StringUtils.isNotEmpty(qualifier)) {
						item.setAttribute("qualifier", qualifier);
					}
				}
				/*if (feat_attribute != null) {
					if (StringUtils.isNotEmpty(feat_attribute)) {
						item.setAttribute("featAttribute", feat_attribute);
					}
				}*/
				if (status != null) {
					if (StringUtils.isNotEmpty(status)) {
						item.setAttribute("status", status);
					}
				}
				String refId = item.getIdentifier();
				genes.put(featureNo, item);
				genesName.put(secondaryIdentifier, item);

				// ~~~ synonyms ~~~
				getSynonym(refId, "symbol", symbol);
				getSynonym(refId, "identifier", secondaryIdentifier);
			}
		}
		System.out.println("size of genes:  " + genes.size());
	}
	
	/**
	 * 
	 * @param connection
	 * @throws SQLException
	 * @throws ObjectStoreException
	 */

	private void processNISS(Connection connection) throws SQLException,
	ObjectStoreException {

		System.out.println("Processing NISS 55 features...");
		ResultSet res = PROCESSOR.getNISS(connection);
		
		while (res.next()) {

			String featureNo = res.getString("dbentity_id");
			if (genes.get(featureNo) == null) {
				//~~~ gene ~~~
				String primaryIdentifier = res.getString("sgdid");
				String secondaryIdentifier = res.getString("systematic_name");
				String symbol = res.getString("gene_name");
				String name = res.getString("name_description");
				String headline = res.getString("headline");
				String description = res.getString("description");
				String qualifier = res.getString("qualifier");
				String status = res.getString("dbentity_status");

				Item item = createItem("NotInSystematicSequenceOfS288C");
		
				// set for all types, so you can use LSF to query for these
				// different type of objects in a template.
				item.setAttribute("featureType", "not in systematic sequence of S288C");
				item.setAttribute("primaryIdentifier", primaryIdentifier);
				if (StringUtils.isNotEmpty(name)) item.setAttribute("name", name);
				item.setAttribute("secondaryIdentifier", secondaryIdentifier);
				item.setReference("organism", organism);
				if (StringUtils.isNotEmpty(symbol)) item.setAttribute("symbol", symbol);				
				if (StringUtils.isNotEmpty(description)) item.setAttribute("description", description);
				if (StringUtils.isNotEmpty(headline)) item.setAttribute("briefDescription", headline);				
				if (qualifier != null) {
					if (StringUtils.isNotEmpty(qualifier)) {
						item.setAttribute("qualifier", qualifier);
					}
				}

				if (status != null) {
					if (StringUtils.isNotEmpty(status)) {
						item.setAttribute("status", status);
					}
				}
				String refId = item.getIdentifier();
				genes.put(featureNo, item);
				genesName.put(secondaryIdentifier, item);

				// ~~~ synonyms ~~~
				getSynonym(refId, "symbol", symbol);
				getSynonym(refId, "identifier", secondaryIdentifier);
			}
		}
	}
	
	/**
	 * 
	 * @param connection
	 * @throws SQLException
	 * @throws ObjectStoreException
	 */

	private void processParalogs(Connection connection) throws SQLException,
	ObjectStoreException {

		System.out.println("Processing Paralog pairs...");
		ResultSet res = PROCESSOR.getParalogs(connection); // ordered by featureNo

		while (res.next()) {

			String parentFeatureNo = res.getString("parent_id");
			String childFeatureNo = res.getString("child_id");
			String refNo = res.getString("reference_id");		
			String source="";
			if(refNo.equalsIgnoreCase("50997")){ //hack for PMID 16169922
				source= "YGOB";
			}else{
				source = "SGD";
			}
			Item pmid = getExistingPub(refNo);
			Item parentGene = genes.get(parentFeatureNo);
			Item childGene = genes.get(childFeatureNo);

			if(parentGene!= null && childGene != null) {	
				processHomologues(parentGene.getIdentifier(), childGene.getIdentifier(), pmid.getIdentifier(), source);
				processHomologues(childGene.getIdentifier(), parentGene.getIdentifier(), pmid.getIdentifier(), source);

			}

		}
	}

	
	
	/**
	 * 
	 * @param connection
	 * @throws SQLException
	 * @throws ObjectStoreException
	 */

	private void processRegulation(Connection connection) throws SQLException,
	ObjectStoreException {

		System.out.println("Processing Regulation data...");
		ResultSet res = PROCESSOR.getRegulationData(connection); // ordered by featureNo

		while (res.next()) {
			String factorGene = res.getString("parent_id");
			String targetGene = res.getString("child_id");
			String evidenceCode = res.getString("reference_id");	
			String condition = res.getString("reference_id");	
			String regulationDirection = res.getString("reference_id");	
			String pmid = res.getString("reference_id");	
			String source = res.getString("reference_id");	
			String pvalue = res.getString("reference_id");	
			String fdr = res.getString("reference_id");	
			String strainBackground = res.getString("reference_id");	
			String strain = res.getString("reference_id");	
		
			getRegulation(factorGene, targetGene, evidenceCode, condition,  regulationDirection,  pmid,  source,  pvalue,  fdr, strainBackground, strain);

		}
	}
	
	/**
	 * 
	 * @param factorGene
	 * @param targetGene
	 * @param evidenceCode
	 * @param condition
	 * @param regulationDirection
	 * @param pmid
	 * @param source
	 * @param pvalue
	 * @param fdr
	 * @param strainBackground
	 * @param strain
	 * @throws ObjectStoreException
	 */
	private void getRegulation(String factorGene, String targetGene, 
			String evidenceCode, String condition, String regulationDirection, String pmid, String source, String pvalue, String fdr, String strainBackground, String strain) 
					throws ObjectStoreException {

		Item rGene = genes.get(factorGene);
		Item tGene = genes.get(targetGene);

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
				String newcond = t[0]+";"+t[3];
				bindingSite.setAttribute("experimentCondition", newcond);
				bindingSite.setAttribute("assay", t[1]);
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

			Item publication = publications.get(pmid);

			if(publication == null) {
				publication = createItem("Publication");
				publications.put(pmid, publication);
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
	 * @param gene1
	 * @param gene2
	 * @param pmid
	 * @param source
	 * @throws ObjectStoreException
	 */
	private void processHomologues(String gene1, String gene2, String pmid, String source) throws ObjectStoreException {
		if (gene1 == null || gene2 == null) {
			return;
		}
		Item homologue = createItem("Homologue");
		homologue.setReference("gene", gene1);
		homologue.setReference("homologue", gene2);
		homologue.setAttribute("type", "paralogue"); 
		homologue.setAttribute("source", source);
	    homologue.setReference("publication", pmid);
		store(homologue);
	}

	/**
	 * 
	 * @param connection
	 * @throws SQLException
	 * @throws ObjectStoreException
	 */

	private void processAliases(Connection connection) throws SQLException, ObjectStoreException {

		ResultSet res = PROCESSOR.getAliases(connection); // ordered by featureNo
		
		System.out.println("Processing ALiases...");
		while (res.next()) {

			String geneFeatureNo = res.getString("dbentity_id");
			String alias_type = res.getString("alias_type");
			String alias_name = res.getString("display_name");

			Item item = genes.get(geneFeatureNo);
			if (item != null) {
				getSynonym(item.getIdentifier(), alias_type, alias_name); // adding sgd_aliases as synonyms
				String name = genesAliases.get(geneFeatureNo);
				if (name == null) {
					genesAliases.put(geneFeatureNo, alias_name);
				} else {
					String newname = name + " " + alias_name;
					genesAliases.put(geneFeatureNo, newname);
				}
			}

		}

		// Add the concatenated string as alias name
		Set<Map.Entry<String, String>> set = genesAliases.entrySet();
		java.util.Iterator<Map.Entry<String, String>> it = set.iterator();

		while (it.hasNext()) {
			Map.Entry<String, String> anEntry = it.next();
			String geneFeatureNo = anEntry.getKey();
			String alias = anEntry.getValue();
			Item item = genes.get(geneFeatureNo);
			item.setAttribute("sgdAlias", alias);
		}

	}


	/**
	 * 
	 * @param connection
	 * @throws SQLException
	 * @throws ObjectStoreException
	 */
	private void processPathways(Connection connection)
			throws SQLException, ObjectStoreException {

		System.out.println("Processing Pathways...");
		ResultSet res = PROCESSOR.getPathways(connection); // ordered by featureNo

		while (res.next()) {			
			String geneFeatureNo = res.getString("dbentity_id");
			String dbxref_name = res.getString("biocyc_id"); //pathway name
			String dbxref_id = res.getString("display_name"); //pathway identifier i.e. short name
			String summary_type = res.getString("summary_type");
			String text = res.getString("text");
			
			Item item = genes.get(geneFeatureNo);
			if (item != null) {
				getPathway(item.getIdentifier(), dbxref_id, dbxref_name, summary_type, text);
			}
		}
	}
	
	/**
	 * 
	 * @param connection
	 * @throws SQLException
	 * @throws ObjectStoreException
	 */
	private void processCrossReferences(Connection connection) throws SQLException, ObjectStoreException {

		System.out.println("Processing DbXRefs...");
		ResultSet res = PROCESSOR.getCrossReferences(connection); // ordered by featureNo

		while (res.next()) {
			String geneFeatureNo = res.getString(1);
			String dbxref_id = res.getString(2);
			String dbx_source = res.getString(3);
			String dbxref_type = res.getString(4);

			Item item = genes.get(geneFeatureNo);

			if (item != null) {
				String url = getCrossRefURL(dbx_source,dbxref_id, dbxref_type);
				getCrossReference(item.getIdentifier(), dbxref_id, dbx_source, dbxref_type, url);
			}
		}
	}

/**
 * 
 * @param source
 * @param dbxref_id
 * @param dbxref_type
 * @return
 * @throws SQLException
 * @throws ObjectStoreException
 */
	private String getCrossRefURL(String source, String dbxref_id, String dbxref_type) throws SQLException, ObjectStoreException {
		
		String url = "";
		
		if(source.equalsIgnoreCase("AspGD")){
			url = "http://www.aspgd.org/cgi-bin/locus.pl?locus="+dbxref_id;
		}else if (source.equalsIgnoreCase("BioGRID")){
			url = "http://thebiogrid.org/"+dbxref_id;
		}else if (source.equalsIgnoreCase("CGD")){
			url = " http://www.candidagenome.org/cgi-bin/locus.pl?locus="+dbxref_id;
		}else if (source.equalsIgnoreCase("DIP")){
			url = "http://dip.doe-mbi.ucla.edu/dip/Browse.cgi?PK="+dbxref_id;
		}else if (source.equalsIgnoreCase("EBI")){
			url = "http://www.uniprot.org/uniprot/"+dbxref_id;
		}else if (source.equalsIgnoreCase("EUROSCARF")){
	        url = "http://web.uni-frankfurt.de/fb15/mikro/euroscarf/data/"+dbxref_id;
		}else if (source.equalsIgnoreCase("IUBMB")){
			url = "http://www.expasy.org/enzyme/"+dbxref_id;
		}else if (source.equalsIgnoreCase("LoQate")){
			url = "http://www.weizmann.ac.il/molgen/loqate/gene/view/"+dbxref_id;
		}else if (source.equalsIgnoreCase("MetaCyc")){
			url = "http://pathway.yeastgenome.org/YEAST/new-image?type=PATHWAY&object="+dbxref_id;
		}else if (source.equalsIgnoreCase("PDB")){
			url = "http://www.rcsb.org/pdb/explore/explore.do?structureId="+dbxref_id;
        }else if (source.equalsIgnoreCase("NCBI") && (dbxref_type.equalsIgnoreCase("Gene ID"))){
			url = "http://www.ncbi.nlm.nih.gov/gene/"+dbxref_id;
        }else if (source.equalsIgnoreCase("NCBI") && (dbxref_type.equalsIgnoreCase("RefSeq protein version ID"))){
			url = "http://www.ncbi.nlm.nih.gov:80/entrez/viewer.fcgi?val="+dbxref_id;
        }else if (source.equalsIgnoreCase("PomBase")){
	       url = "http://www.pombase.org/spombe/result/"+dbxref_id;
        }else if (source.equalsIgnoreCase("RNAcentral")){
        	url = "http://rnacentral.org/rna/"+dbxref_id;
        }else if (source.equalsIgnoreCase("TCDB")){
        	url = "http://www.tcdb.org/tcdb/index.php?tc="+dbxref_id;
        }
			
		return url;
	}
	
/**
 * 
 * @param subjectId
 * @param id
 * @param source
 * @param dbxref_type
 * @param url
 * @return
 * @throws ObjectStoreException
 */
	private String getCrossReference(String subjectId, String id, String source, String dbxref_type, String url)
			throws ObjectStoreException {

		String refId = "";
		Item crf = createItem("CrossReference");
		crf.setReference("subject", subjectId);
		crf.setAttribute("identifier", id);
		crf.setAttribute("dbxreftype", dbxref_type);
		if(! StringUtils.isEmpty(url)) {
			crf.setAttribute("url", url);	
		}

		String dsId = datasources.get(source);
		if (dsId == null) {
			Item ds = createItem("DataSource");
			//ds.setAttribute("name", source);
			String join = source+"/"+dbxref_type;
			ds.setAttribute("name", join);
			String sourceUrl = getSourceURL(source);
			if(! StringUtils.isEmpty(sourceUrl)) {
				ds.setAttribute("url", sourceUrl);
			}
			try {
				store(ds);
			} catch (ObjectStoreException e) {
				throw new ObjectStoreException(e);
			}

			crf.setReference("source", ds.getIdentifier());
			datasources.put(join, ds.getIdentifier());
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
 * @param source
 * @return
 * @throws ObjectStoreException
 */
	private String getSourceURL(String source) throws ObjectStoreException {
		
		String url = "";
		
		if(source.equalsIgnoreCase("AspGD")){
			url = "http://www.aspgd.org/";
		}else if (source.equalsIgnoreCase("BioGRID")){
			url = "http://thebiogrid.org/";
		}else if (source.equalsIgnoreCase("CGD")){
			url = " http://www.candidagenome.org/";
		}else if (source.equalsIgnoreCase("DIP")){
			url = "http://dip.doe-mbi.ucla.edu/";
		}else if (source.equalsIgnoreCase("EBI")){
			url = "http://www.uniprot.org/uniprot/";
		}else if (source.equalsIgnoreCase("EUROSCARF")){
	        url = "http://web.uni-frankfurt.de/";
		}else if (source.equalsIgnoreCase("IUBMB")){
			url = "http://www.expasy.org/";
		}else if (source.equalsIgnoreCase("LoQate")){
			url = "http://www.weizmann.ac.il/molgen/loqate/";
		}else if (source.equalsIgnoreCase("MetaCyc")){
			url = "http://pathway.yeastgenome.org/";
		}else if (source.equalsIgnoreCase("PDB")){
			url = "http://www.rcsb.org/pdb/";
        }else if (source.equalsIgnoreCase("NCBI")){
			url = "http://www.ncbi.nlm.nih.gov/";
        }else if (source.equalsIgnoreCase("PomBase")){
	       url = "http://www.pombase.org/";
        }else if (source.equalsIgnoreCase("RNAcentral")){
        	url = "http://rnacentral.org/rna/";
        }else if (source.equalsIgnoreCase("TCDB")){
        	url = "http://www.tcdb.org/tcdb/";
        }
		
		return url;
	}
	
	/**
	 * 
	 * @param connection
	 * @throws SQLException
	 * @throws ObjectStoreException
	 * @throws Exception
	 */

	private void processGeneLocations(Connection connection)
			throws SQLException, ObjectStoreException, Exception {

		System.out.println("Processing GeneLocations...");
		ResultSet res = PROCESSOR.getChromosomalFeatureLocationResults(connection);
		
		while (res.next()) {
			String featureNo = res.getString("contig_id");
			String featureName = res.getString("format_name");
			String featureType = res.getString("feature_type");
			String geneFeatureNo = res.getString("dbentity_id");
			String geneFeatureName = res.getString("gene_name");
			String strand = res.getString("strand");
			String seq_length = res.getString(10);

			String newstrand = "";
			if (strand.equals("+")) {
				newstrand = "1";
			} else if (strand.equals("-")) {
				newstrand = "-1";
			} else if (strand.equals("0")) {
				newstrand = "0";
			}
		   String fixed_chromosome_no = getFixedChrName(featureName);
			Item item = genes.get(geneFeatureNo);

			// ~~~ chromosome OR plasmid ~~~
			String refId = null;
			if (featureType.equalsIgnoreCase("plasmid")) {
				refId = getPlasmid(fixed_chromosome_no); 
				item.setReference("plasmid", refId);
			} else if (featureType.equalsIgnoreCase("chromosome")) {
				refId = getChromosome(fixed_chromosome_no);
				item.setReference("chromosome", refId);
			}

			// ~~~ location ~~~
			String locationRefId = getLocation(item, refId, res.getString("start_index"), res.getString("end_index"),newstrand); 

			if (featureType.equalsIgnoreCase("plasmid")) {
				item.setReference("plasmidLocation", locationRefId);
			} else if (featureType.equalsIgnoreCase("chromosome")) {
				item.setReference("chromosomeLocation", locationRefId);
			}
			// ~~ add sequence
			String seqRefId = getSequence(geneFeatureNo, res.getString("residues"), seq_length);
			item.setReference("sequence", seqRefId);
			item.setAttribute("length", seq_length);

		}
		res.close();
		System.out.println("size of genes:  " + genes.size());
	}

	/**
	 * To add a chromosome number to the NISS and NPM features that do have a
	 * chromosome number as parent
	 * 
	 * @param connection
	 * @throws SQLException
	 * @throws ObjectStoreException
	 *
	private void processChrLocations(Connection connection)
			throws SQLException, ObjectStoreException, Exception {

		ResultSet res = PROCESSOR.getChromosomeLocationResults(connection);
		System.out.println("Processing ChrLocations...");
		while (res.next()) {
			String featureNo = res.getString("feature_no");
			String geneFeatureNo = res.getString("gene_feature_no");
			String featureType = res.getString("feature_type");
			String featureName = res.getString("identifier");

			String fixed_chromosome_no = getFixedChrName(featureName);

			Item item = genes.get(geneFeatureNo);

			// ~~~ chromosome OR plasmid ~~~
			String refId = null;
			if (featureType.equalsIgnoreCase("plasmid")) {
				refId = getPlasmid(fixed_chromosome_no);
				item.setReference("plasmid", refId);
			} else if (featureType.equalsIgnoreCase("chromosome")) {
				refId = getChromosome(fixed_chromosome_no);
				item.setReference("chromosome", refId);
			}

			// ~~~ location ~~~
			String locationRefId = getLocation(item, refId, "1", "1", "0"); // was

			if (featureType.equalsIgnoreCase("plasmid")) {
				item.setReference("plasmidLocation", locationRefId);
			} else if (featureType.equalsIgnoreCase("chromosome")) {
				item.setReference("chromosomeLocation", locationRefId);
			}

		}
		res.close();
	}*/

	private void processGeneChildrenLocations(Connection connection) throws SQLException, ObjectStoreException, Exception {

		System.out.println("Processing GeneChildrenLocations...");
		ResultSet res = PROCESSOR.getChildrenFeatureLocationResults(connection);

		while (res.next()) {
	    			   
			String geneFeatureNo = res.getString("parent_id");
			String parentFeatureType = res.getString("parent_type").trim();

			String geneChildFeatureNo = res.getString("child_id");
			String childFeatureType = res.getString("child_type").trim();

			String chromosome_no = res.getString("format_name"); //root chr.number
			//String secondaryIdentifier = res.getString("child_identifier"); //child identifier is wrong -- fix it 11/13
			String primaryIdentifier = res.getString("child_sgdid")+"_C"; // SXX

			String maxcoord = res.getString("child_end_coord");
			String mincoord = res.getString("child_start_coord");
			String strand = res.getString("strand");

			String seq = res.getString("residues");
			String seqLen = res.getString("seq_length");
			String child_status = res.getString("child_status");

			String newstrand = "";
			if (strand.equals("+")) {
				newstrand = "1";
			} else if (strand.equals("-")) {
				newstrand = "-1";
			} else if (strand.equals("0")) {
				newstrand = "0";
			}

			String fixed_chromosome_no = getFixedChrName(chromosome_no);

			// figure out why duplicates in the SQL..???..
			/*if (featureMap.get(geneChildFeatureNo) == null) {
				featureMap.put(geneChildFeatureNo, geneFeatureNo);
			} else {
				continue;
			}*/

			Item parent = genes.get(geneFeatureNo);
			System.out.println("parent: "+ parentFeatureType + " child: "+ childFeatureType);
			// create the child Item
			Item childItem = getChildItem(childFeatureType);

			childItem.setAttribute("primaryIdentifier", primaryIdentifier);
			childItem.setReference("organism", organism);
			childItem.setAttribute("featureType", childFeatureType);
			childItem.setAttribute("status", child_status);

			if (childFeatureType.equalsIgnoreCase("intron")) {
				childItem.addToCollection("genes", parent.getIdentifier());
			} else {
				String refname = getReferenceName(childFeatureType,parentFeatureType);
				System.out.println("refname "+refname+ "    " +parent.getIdentifier());
				childItem.setReference(refname, parent.getIdentifier());
			}
			// ~~ add sequence
			String seqRefId = getSequence(geneChildFeatureNo, seq, seqLen);
			childItem.setReference("sequence", seqRefId);

			// ~~~ chromosome and location ~~~
			String refId = null;
			if (chromosome_no.equalsIgnoreCase("2-micron_plasmid")) {
				refId = getPlasmid(fixed_chromosome_no);
				childItem.setReference("plasmid", refId);
				String locationRefId = getLocation(childItem, refId, mincoord, maxcoord, newstrand);
				childItem.setReference("plasmidLocation", locationRefId);
			} else {
				refId = getChromosome(fixed_chromosome_no);
				childItem.setReference("chromosome", refId);
				String locationRefId = getLocation(childItem, refId, mincoord, maxcoord, newstrand);
				childItem.setReference("chromosomeLocation", locationRefId);
			}

			try {
				store(childItem);
			} catch (ObjectStoreException e) {
				e.printStackTrace();
				throw new ObjectStoreException(e);
			}

			// ~~~ store these last ~~~
			String childId = childItem.getIdentifier();
			getSynonym(childId, "identifier", primaryIdentifier);

		}
		res.close();

	}

	private String getReferenceName(String type, String ptype)
			throws ObjectStoreException {

		String name = "";

      if (type.equalsIgnoreCase("ARS_consensus_sequence") && ptype.equalsIgnoreCase("ARS")) {
			name = "ars";
		}else if (type.equalsIgnoreCase("CDS") && ptype.equalsIgnoreCase("blocked_reading_frame")) {
			name = "blockedreadingframe";
		}else if (type.equalsIgnoreCase("CDS") && ptype.equalsIgnoreCase("ORF")) {
			name = "orf";
		} else if (type.equalsIgnoreCase("uORF") && ptype.equalsIgnoreCase("ORF")) {
			name = "orf";
		}else if (type.equalsIgnoreCase("CDS") && ptype.equalsIgnoreCase("pseudogene")) {
			name = "pseudogene";
		} else if (type.equalsIgnoreCase("CDS") && ptype.equalsIgnoreCase("transposable_element_gene")) {
			name = "transposableelementgene";
		}else if (type.equalsIgnoreCase("centromere_DNA_Element_I")) {
			name = "centromere";
		} else if (type.equalsIgnoreCase("centromere_DNA_Element_II")) {
			name = "centromere";
		} else if (type.equalsIgnoreCase("centromere_DNA_Element_III")) {
			name = "centromere";
		} else if (type.equalsIgnoreCase("external_transcribed_spacer_region")) {
			name = "ncrna_gene";
		} else if (type.equalsIgnoreCase("five_prime_UTR_intron")) {
			name = "orf";
		}else if (type.equalsIgnoreCase("intein_encoding_region")) {
			name = "orf";
		}else if (type.equalsIgnoreCase("internal_transcribed_spacer_region")) {
			name = "ncrna_gene";
		} else if (type.equalsIgnoreCase("intron") && ptype.equalsIgnoreCase("ORF")) {
			name = "orf";
		}else if (type.equalsIgnoreCase("intron") && ptype.equalsIgnoreCase("rRNA_gene")) {
			name = "rrna_gene";
		}else if (type.equalsIgnoreCase("intron") && ptype.equalsIgnoreCase("snoRNA_gene")) {
			name = "snorna_gene";
		}else if (type.equalsIgnoreCase("intron") && ptype.equalsIgnoreCase("tRNA_gene")) {
			name = "trna_gene";
		} else if (type.equalsIgnoreCase("non_transcribed_region")) {
			name = "ncrna_gene";
		}else if (type.equalsIgnoreCase("noncoding_exon") && ptype.equalsIgnoreCase("ncRNA_gene")) {
			name = "ncrna_gene";
		} else if (type.equalsIgnoreCase("noncoding_exon") && ptype.equalsIgnoreCase("rRNA_gene")) {
			name = "ncrna_gene";
		} else if (type.equalsIgnoreCase("noncoding_exon") && ptype.equalsIgnoreCase("snoRNA_gene")) {
			name = "ncrna_gene";
		} else if (type.equalsIgnoreCase("noncoding_exon") && ptype.equalsIgnoreCase("snRNA_gene")) {
			name = "ncrna_gene";
		} else if (type.equalsIgnoreCase("noncoding_exon") && ptype.equalsIgnoreCase("tRNA_gene")) {
			name = "ncrna_gene";
		} else if (type.equalsIgnoreCase("plus_1_translational_frameshift") && ptype.equalsIgnoreCase("ORF")) {
			name = "orf";
		} else if (type.equalsIgnoreCase("plus_1_translational_frameshift") && ptype.equalsIgnoreCase("pseudogene")) {
			name = "pseudogene";
		} else if (type.equalsIgnoreCase("plus_1_translational_frameshift") && ptype.equalsIgnoreCase("transposable_element_gene")) {
			name = "transposableelementgene";
		} else if (type.equalsIgnoreCase("telomeric_repeat") && ptype.equalsIgnoreCase("telomere")) {
			name = "telomere";
		}  else if (type.equalsIgnoreCase("telomeric_repeat") && ptype.equalsIgnoreCase("ORF")) {
			name = "orf";
		} else if (type.equalsIgnoreCase("X_element") && ptype.equalsIgnoreCase("telomere")) {
			name = "telomere";
		} else if (type.equalsIgnoreCase("X_element_combinatorial_repeat") && ptype.equalsIgnoreCase("telomere")) {
			name = "telomere";
		}   else if (type.equalsIgnoreCase("Y_prime_element") && ptype.equalsIgnoreCase("telomere")) {
			name = "telomere";
		}  else if (type.equalsIgnoreCase("noncoding_exon") && ptype.equalsIgnoreCase("telomerase_RNA_gene")) {
			name = "ncrna_gene";
		}else if (type.equalsIgnoreCase("W_region") && ptype.equalsIgnoreCase("silent_mating_type_cassette_array")) {
			name = "matingtyperegion";
		}else if (type.equalsIgnoreCase("W_region") && ptype.equalsIgnoreCase("mating_type_region")) {
			name = "matingtyperegion";
		}else if (type.equalsIgnoreCase("Y_region") && ptype.equalsIgnoreCase("mating_type_region")) {
			name = "matingtyperegion";
		}else if (type.equalsIgnoreCase("X_region") && ptype.equalsIgnoreCase("silent_mating_type_cassette_array")) {
			name = "matingtyperegion";
		}else if (type.equalsIgnoreCase("Y_region") && ptype.equalsIgnoreCase("silent_mating_type_cassette_array")) {
			name = "matingtyperegion";
		}else if (type.equalsIgnoreCase("X_region") && ptype.equalsIgnoreCase("mating_type_region")) {
			name = "matingtyperegion";
		}else if (type.equalsIgnoreCase("Z1_region") && ptype.equalsIgnoreCase("silent_mating_type_cassette_array")) {
			name = "matingtyperegion";
		}else if (type.equalsIgnoreCase("Z1_region") && ptype.equalsIgnoreCase("mating_type_region")) {
			name = "matingtyperegion";
		}else if (type.equalsIgnoreCase("Z2_region") && ptype.equalsIgnoreCase("silent_mating_type_cassette_array")) {
			name = "matingtyperegion";
		}else if (type.equalsIgnoreCase("Z2_region") && ptype.equalsIgnoreCase("mating_type_region")) {
			name = "matingtyperegion";
		}

		return name;

	}

	private String getCasedName(String name) throws Exception {

		String newname = name;

		StringBuffer sb = new StringBuffer();
		Matcher m = Pattern
				.compile("([a-z])([a-z]*)", Pattern.CASE_INSENSITIVE).matcher(
						name);
		while (m.find()) {
			m.appendReplacement(sb, m.group(1).toUpperCase()
					+ m.group(2).toLowerCase());
		}
		newname = m.appendTail(sb) + "p".toString();

		return newname;

	}

	private void processProteins(Connection connection) throws SQLException,
	ObjectStoreException, Exception {

		ResultSet res = PROCESSOR.getProteinResults(connection);
		System.out.println("Processing Proteins...");
		while (res.next()) {
			String featureNo = res.getString("dbentity_id");
			String primaryIdentifier = res.getString("sgdid");
			String secondaryIdentifier = res.getString("format_name");
			String symbol = res.getString("display_name");
			String residues = res.getString("residues");
			String length = res.getString(6);

			Item item = genes.get(featureNo);

			// ~~~ sequence ~~~
			Item protein = createItem("Protein");
			protein.setAttribute("primaryIdentifier", primaryIdentifier);
			protein.setAttribute("secondaryIdentifier", secondaryIdentifier);
			protein.setAttribute("length", length);
			protein.setReference("organism", organism);

			if (symbol != null) {
				String modSymbol = getCasedName(symbol);
				protein.setAttribute("symbol", modSymbol);
			}
			
			Item seq = createItem("Sequence");
			seq.setAttribute("residues", residues);
			seq.setAttribute("length", length);

			try {
				store(seq);
			} catch (ObjectStoreException e) {
				throw new ObjectStoreException(e);
			}

			protein.setReference("sequence", seq.getIdentifier());
			if(item != null) protein.addToCollection("genes", item.getIdentifier());
			proteins.put(featureNo, protein);

		}

	}

	private String getFixedChrName(String name) throws Exception {

		String newname = "";

		if (name.equalsIgnoreCase("Chromosome_Mito")) {
			newname = "chrmt";
		}else if(name.equalsIgnoreCase("2-micron_plasmid")){
			newname = name;
		}else if(name.equalsIgnoreCase("Chromosome_I")){
			newname ="chrI";
		}else if(name.equalsIgnoreCase("Chromosome_II")){
			newname ="chrII";
		}else if(name.equalsIgnoreCase("Chromosome_III")){
			newname ="chrIII";
		}else if(name.equalsIgnoreCase("Chromosome_IV")){
			newname ="chrIV";
		}else if(name.equalsIgnoreCase("Chromosome_V")){
			newname ="chrV";
		}else if(name.equalsIgnoreCase("Chromosome_VI")){
			newname ="chrVI";
		}else if(name.equalsIgnoreCase("Chromosome_VII")){
			newname ="chrVII";
		}else if(name.equalsIgnoreCase("Chromosome_VIII")){
			newname ="chrVIII";
		}else if(name.equalsIgnoreCase("Chromosome_IX")){
			newname ="chrIX";
		}else if(name.equalsIgnoreCase("Chromosome_X")){
			newname ="chrX";
		}else if(name.equalsIgnoreCase("Chromosome_XI")){
			newname ="chrXI";
		}else if(name.equalsIgnoreCase("Chromosome_XII")){
			newname ="chrXII";
		}else if(name.equalsIgnoreCase("Chromosome_XIII")){
			newname ="chrXIII";
		}else if(name.equalsIgnoreCase("Chromosome_XIV")){
			newname ="chrXIV";
		}else if(name.equalsIgnoreCase("Chromosome_XV")){
			newname ="chrXV";
		}else if(name.equalsIgnoreCase("Chromosome_XVI")){
			newname ="chrXVI";
		}
		return newname;

	}

	private Item getChildItem(String childType) throws ObjectStoreException {

		Item item = null;

		if (childType.equalsIgnoreCase("CDS")) {
			item = createItem("CDS");
		} else if (childType.equalsIgnoreCase("intron")) {
			item = createItem("Intron");
		} else if (childType.equalsIgnoreCase("five_prime_UTR_intron")) {
			item = createItem("FivePrimeUTRIntron");
		} else if (childType.equalsIgnoreCase("plus_1_translational_frameshift")) {
			item = createItem("Plus1TranslationalFrameshift");
		} else if (childType.equalsIgnoreCase("ARS consensus sequence")) {
			item = createItem("ARSConsensusSequence");
		} else if (childType.equalsIgnoreCase("binding_site")) {
			item = createItem("BindingSite");
		} else if (childType.equalsIgnoreCase("insertion")) {
			item = createItem("Insertion");
		} else if (childType.equalsIgnoreCase("repeat_region")) {
			item = createItem("RepeatRegion");
		} else if (childType.equalsIgnoreCase("noncoding_exon")) {
			item = createItem("NoncodingExon");
		} else if (childType.equalsIgnoreCase("external_transcribed_spacer_region")) {
			item = createItem("ExternalTranscribedSpacerRegion");
		} else if (childType.equalsIgnoreCase("internal_transcribed_spacer_region")) {
			item = createItem("InternalTranscribedSpacerRegion");
		} else if (childType.equalsIgnoreCase("non_transcribed_region")) {
			item = createItem("NonTranscribedRegion");
		} else if (childType.equalsIgnoreCase("centromere_DNA_Element_I")) {
			item = createItem("CentromereDNAElementI");
		} else if (childType.equalsIgnoreCase("centromere_DNA_Element_II")) {
			item = createItem("CentromereDNAElementII");
		} else if (childType.equalsIgnoreCase("centromere_DNA_Element_III")) {
			item = createItem("CentromereDNAElementIII");
		} else if (childType.equalsIgnoreCase("intein_encoding_region")) {
			item = createItem("InteinEncodingRegion");
		} else if (childType.equalsIgnoreCase("ARS_consensus_sequence")) {
			item = createItem("ARSConsensusSequence");
		}else if (childType.equalsIgnoreCase("Y_prime_element")) {
			item = createItem("YPrimeElement");
		}else if (childType.equalsIgnoreCase("X_element_combinatorial_repeat")) {
			item = createItem("XElementCombinatorialRepeat");
		}else if (childType.equalsIgnoreCase("X_element")) {
			item = createItem("XElement");
		}else if (childType.equalsIgnoreCase("telomeric_repeat")) {
			item = createItem("TelomericRepeat");
		}else if (childType.equalsIgnoreCase("uORF")) {
			item = createItem("uORF");
		}else if (childType.equalsIgnoreCase("W_region")) {
			item = createItem("W_region");
		}else if (childType.equalsIgnoreCase("X_region")) {
			item = createItem("X_region");
		}else if (childType.equalsIgnoreCase("Y_region")) {
			item = createItem("Y_region");
		}else if (childType.equalsIgnoreCase("Z1_region")) {
			item = createItem("Z1_region");
		}else if (childType.equalsIgnoreCase("Z2_region")) {
			item = createItem("Z2_region");
		}

		return item;

	}
	
	private void processChromosomeSequences(Connection connection)
			throws SQLException, ObjectStoreException, Exception {
		
		System.out.println("Processing ChromosomeSequence...");
		ResultSet res = PROCESSOR.getChromosomeSequenceResults(connection);

		while (res.next()) {
			String featureNo = res.getString("contig_id");
			String chromosomeNo = res.getString("format_name");
			String feature_type = res.getString("display_name");
			String residues = res.getString("residues");
			String length = res.getString(5);

			String fixed_chromosome_no = getFixedChrName(chromosomeNo);
			
			if (feature_type.equalsIgnoreCase("chromosome")) {
				Item chr = createItem("Chromosome");
				chr.setAttribute("primaryIdentifier", fixed_chromosome_no);
				chr.setReference("organism", organism);
				chr.setAttribute("length", length);
				chr.setAttribute("featureType", feature_type);

				Item seq = createItem("Sequence");
				seq.setAttribute("residues", residues);
				seq.setAttribute("length", length);

				try {
					store(seq);
				} catch (ObjectStoreException e) {
					throw new ObjectStoreException(e);
				}

				chr.setReference("sequence", seq.getIdentifier());
				chromosomes.put(fixed_chromosome_no, chr.getIdentifier());

				try {
					store(chr);
				} catch (ObjectStoreException e) {
					throw new ObjectStoreException(e);
				}

			} else if (feature_type.equalsIgnoreCase("plasmid")) {

				Item item = createItem("Plasmid");
				item.setAttribute("primaryIdentifier", fixed_chromosome_no); 
				item.setReference("organism", organism);
				plasmids.put(fixed_chromosome_no, item.getIdentifier());
				try {
					store(item);
				} catch (ObjectStoreException e) {
					throw new ObjectStoreException(e);
				}
			}

		}

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
	
	/**
	 * 
	 * @throws ObjectStoreException
	 */

	private void storeProteins() throws ObjectStoreException {
		for (Item protein : proteins.values()) {
			try {
				store(protein);
			} catch (ObjectStoreException e) {
				throw new ObjectStoreException(e);
			}
		}
	}
	/**
	 * 
	 * @throws ObjectStoreException
	 */

	private void storeInteractionTypes() throws ObjectStoreException {
		for (Item type : interactiontype.values()) {
			try {
				store(type);
			} catch (ObjectStoreException e) {
				throw new ObjectStoreException(e);
			}
		}
	}


	/**
	 * 
	 * @throws ObjectStoreException
	 */

	private void storeInteractionExperiments() throws ObjectStoreException {
		for (Item exp : experimenttype.values()) {
			try {
				store(exp);
			} catch (ObjectStoreException e) {
				throw new ObjectStoreException(e);
			}
		}
	}


	/**
	 * 
	 * @throws ObjectStoreException
	 */

	private void storeInteractionDetails() throws ObjectStoreException {
		for (Item det : interactiondetail.values()) {
			try {
				store(det);
			} catch (ObjectStoreException e) {
				throw new ObjectStoreException(e);
			}
		}
	}


	/**
	 * 
	 * @throws ObjectStoreException
	 */

	private void storePublications() throws ObjectStoreException {
		for (Item pub : publications.values()) {
			try {
				store(pub);
			} catch (ObjectStoreException e) {
				throw new ObjectStoreException(e);
			}
		}
	}

	/**
	 * 
	 * @throws ObjectStoreException
	 */

	private void storePathways() throws ObjectStoreException {
		for (Item path : pathways.values()) {
			try {
				store(path);
			} catch (ObjectStoreException e) {
				throw new ObjectStoreException(e);
			}
		}
	}

	/**
	 * 
	 * @throws ObjectStoreException
	 */

	private void storeInteractions() throws ObjectStoreException {
		for (Item intact : interactions.values()) {
			try {
				store(intact);
			} catch (ObjectStoreException e) {
				throw new ObjectStoreException(e);
			}
		}
	}

	/**
	 * 
	 * @throws ObjectStoreException
	 */

	private void storePhenotypes() throws ObjectStoreException {
		for (Item pheno : phenotypes.values()) {
			try {
				store(pheno);
			} catch (ObjectStoreException e) {
				throw new ObjectStoreException(e);
			}
		}
	}
	

	private void storePhenotypeAnnotations() throws ObjectStoreException {
		for (Item pheno : phenotypeannots.values()) {
			try {
				store(pheno);
			} catch (ObjectStoreException e) {
				throw new ObjectStoreException(e);
			}
		}
	}
	

	private void storePhenotypeConditions() throws ObjectStoreException {
		for (Item pheno : phenotypeconds.values()) {
			try {
				store(pheno);
			} catch (ObjectStoreException e) {
				throw new ObjectStoreException(e);
			}
		}
	}
	

	/**
	 * 
	 * @param connection
	 * @throws SQLException
	 * @throws ObjectStoreException
	 */

	private void processAllPubs(Connection connection) throws SQLException,
	ObjectStoreException {

		String prevReferenceNo = "";
		String prevPubMedId = "";
		String prevTitle = "";
		String prevCitation = "";
		String prevJournal = "";
		String prevVolume = "";
		String prevPages = "";
		String prevYear = "";
		String prevIssue = "";
		String prevAbst = "";
		String prevDbxRef = "";
		String prevDateCreated = "";

		ArrayList<String> hm = new ArrayList<String>();

		Item gene = null;
		boolean firstrow = true;

		ResultSet res = PROCESSOR.getPubAllResults(connection);

		System.out.println("Processing All Publications with Topics...");

		while (res.next()) {

			String referenceNo = res.getString("dbentity_id");
			String pubMedId = res.getString("pmid");
			String title = res.getString("title");
			String citation = res.getString("citation");
			String topic = res.getString("topic");
			String journal = res.getString("med_abbr");
			String volume = res.getString("volume");
			String pages = res.getString("page");
			String year = res.getString("year");
			String issue = res.getString("issue");
			String abst = res.getString("fulltext_status");
			String dbxrefid = res.getString("sgdid");
			String date_created = res.getString("date_created");

			if (firstrow) {
				prevReferenceNo = referenceNo;
				firstrow = false;
			}

			if (!referenceNo.equalsIgnoreCase(prevReferenceNo)) {
				getPub(prevReferenceNo, prevTitle, prevPubMedId, prevCitation,
						hm, prevJournal, prevVolume, prevPages, prevYear,
						prevIssue, prevAbst, prevDbxRef, prevDateCreated);
				hm.clear();
			}

			if (topic != null) {
				hm.add(new String(topic));
			}

			prevReferenceNo = referenceNo;
			prevTitle = title;
			prevPubMedId = pubMedId;
			prevCitation = citation;
			prevJournal = journal;
			prevVolume = volume;
			prevYear = year;
			prevIssue = issue;
			prevPages = pages;
			prevAbst = abst;
			prevDbxRef = dbxrefid;
			prevDateCreated = date_created;

		}
		// process the very last reference group
		getPub(prevReferenceNo, prevTitle, prevPubMedId, prevCitation, hm,
				prevJournal, prevVolume, prevPages, prevYear, prevIssue,
				prevAbst, prevDbxRef, prevDateCreated);
		hm.clear();

	}

	/**
	 * 
	 * @param connection
	 * @throws SQLException
	 * @throws ObjectStoreException
	 */

	private void processPubsWithFeatures(Connection connection)
			throws SQLException, ObjectStoreException {

		String prevGeneFeatureNo = "";
		String prevReferenceNo = "";
		String prevPubMedId = "";
		String prevTitle = "";
		String prevCitation = "";
		String prevJournal = "";
		String prevVolume = "";
		String prevPages = "";
		String prevYear = "";
		String prevIssue = "";
		String prevDbxRef = "";
		String prevDateCreated = "";

		ArrayList<String> hm = new ArrayList<String>();

		Item gene = null;
		boolean firstrow = true;
		System.out.println("Processing Publications With Chromosomal Features...");
		ResultSet res = PROCESSOR.getPubWithFeaturesResults(connection);

		while (res.next()) {
			
			String referenceNo = res.getString("referenceFeatureNo");
			String geneFeatureNo = res.getString("featureNo");
			String pubMedId = res.getString("pmid");
			String title = res.getString("title");
			String citation = res.getString("citation");
			String topic = res.getString("topic");
			String journal = res.getString("med_abbr");
			String volume = res.getString("volume");
			String pages = res.getString("page");
			String year = res.getString("year");
			String issue = res.getString("issue");
			String dbxrefid = res.getString("sgdid");
			String date_created = res.getString("date_created");

			if (!geneFeatureNo.equalsIgnoreCase(prevGeneFeatureNo)) {

				if (!firstrow) {
					
					gene = genes.get(geneFeatureNo);
					
					if(gene != null) {
					
					getPubAnnot(prevReferenceNo, prevTitle, prevPubMedId,
							prevCitation, hm, gene, prevJournal, prevVolume,
							prevPages, prevYear, prevIssue, prevDbxRef, prevDateCreated);
					hm.clear();
					prevReferenceNo = referenceNo;
					}
				}
				
			}

			if (firstrow) {
				prevReferenceNo = referenceNo;
				firstrow = false;
			}

			if (!referenceNo.equalsIgnoreCase(prevReferenceNo)) {
				
				if(gene != null) {
				getPubAnnot(prevReferenceNo, prevTitle, prevPubMedId,
						prevCitation, hm, gene, prevJournal, prevVolume,
						prevPages, prevYear, prevIssue, prevDbxRef, prevDateCreated);
				hm.clear();
				}
			}

			if (topic != null) {
				hm.add(new String(topic));
			}

			prevGeneFeatureNo = geneFeatureNo;
			prevReferenceNo = referenceNo;
			prevTitle = title;
			prevPubMedId = pubMedId;
			prevCitation = citation;
			prevJournal = journal;
			prevVolume = volume;
			prevYear = year;
			prevIssue = issue;
			prevPages = pages;
			prevDbxRef = dbxrefid;

		}

		// process the very last reference group
		if(gene != null) {
		getPubAnnot(prevReferenceNo, prevTitle, prevPubMedId, prevCitation, hm,
				gene, prevJournal, prevVolume, prevPages, prevYear, prevIssue, prevDbxRef, prevDateCreated);
		hm.clear();
		}

	}

	/**
	 * 
	 * @param connection
	 * @throws SQLException
	 * @throws ObjectStoreException
	 */
	private void processPubsForPhenotypes(Connection connection)
			throws SQLException, ObjectStoreException {

		System.out.println("Processing Publications associated with Pheno_annot_no....");
		ResultSet res = PROCESSOR.getPubForPhenotype(connection);
				
		while (res.next()) {

			String phenoAnnotNo = res.getString("annotation_id");
			String referenceNo = res.getString("reference_id");
			String pubMedId = res.getString("pmid");
			String status = res.getString("fulltext_status");
			String title = res.getString("title");
			String volume = res.getString("volume");
			String pages = res.getString("page");
			String year = res.getString("year");
			String issue = res.getString("issue");
			String citation = res.getString("citation");
			String journal = res.getString("med_abbr");
			String dbxrefid = res.getString("sgdid");
			String date_created = res.getString("date_created");

			getPubPhenotype(phenoAnnotNo, referenceNo, title, pubMedId,
					citation, journal, volume, pages, year, issue, dbxrefid, date_created);

		}

	}
	
	/**
	 * 
	 * @param connection
	 * @throws SQLException
	 * @throws ObjectStoreException
	 */
	private void processPhenotypeSummary(Connection connection)
			throws SQLException, ObjectStoreException {
		
		System.out.println("Processing Phenotype Summary....");
		ResultSet res = PROCESSOR.getPhenotypeSummary(connection);	
		
		while (res.next()) {

			String featureNo = res.getString("dbentity_id");
			String summary = res.getString("text");
	
			Item gene = genes.get(featureNo);		
			gene.setAttribute("phenotypeSummary", summary);

		}
	}
	/**
	 * 
	 * @param connection
	 * @throws SQLException
	 * @throws ObjectStoreException
	 */
	private void processFunctionSummary(Connection connection)
			throws SQLException, ObjectStoreException {
		
		System.out.println("Processing Function Summary....");
		ResultSet res = PROCESSOR.getFunctionSummary(connection);	
		
		while (res.next()) {

			String featureNo = res.getString("dbentity_id");
			String summary = res.getString("text");
	
			Item gene = genes.get(featureNo);		
			gene.setAttribute("functionSummary", summary);

		}
	}
	/**
	 * 
	 * @param connection
	 * @throws SQLException
	 * @throws ObjectStoreException
	 */
	private void processGeneSummary(Connection connection)
			throws SQLException, ObjectStoreException {
		
		System.out.println("Processing Gene Summary....");
		ResultSet res = PROCESSOR.getGeneSummary(connection);	
		
		while (res.next()) {

			String featureNo = res.getString("dbentity_id");
			String summary = res.getString("text");
	
			Item gene = genes.get(featureNo);		
			gene.setAttribute("geneSummary", summary);

		}
	}
	/*
	 * private void processPubs(Connection connection) throws SQLException,
	 * ObjectStoreException { ResultSet res =
	 * PROCESSOR.getPubResults(connection); while (res.next()) {
	 * 
	 * String featureNo = res.getString("reference_no"); String geneFeatureNo =
	 * res.getString("gene_feature_no"); Item gene = genes.get(geneFeatureNo);
	 * if (gene == null) { continue; }
	 * 
	 * String issue = res.getString("issue"); String volume =
	 * res.getString("volume"); String pubMedId = res.getString("pubmed");
	 * String pages = res.getString("page"); String title =
	 * res.getString("title"); String year = res.getString("year"); String
	 * citation = res.getString("citation"); String topic =
	 * res.getString("literature_topic"); String journal =
	 * res.getString("abbreviation");
	 * 
	 * String refId = getPub(featureNo, issue, volume, pubMedId, pages, title,
	 * year, citation); //publication
	 * 
	 * Item item = createItem("PublicationAnnotation");
	 * item.setReference("subject", gene.getIdentifier());
	 * item.setReference("literatureTopic", getLiteratureTopic(topic));
	 * item.addToCollection("publications", refId); try { store(item); } catch
	 * (ObjectStoreException e) { throw new ObjectStoreException(e); }
	 * 
	 * gene.addToCollection("publicationAnnotations", item.getIdentifier());
	 * 
	 * } }
	 */


	/**
	 * 
	 * @param connection
	 * @throws SQLException
	 * @throws ObjectStoreException
	 */

	private void processPhysicalInteractions(Connection connection)
			throws SQLException, ObjectStoreException {

		String dsId = getBioGridDataSet();
		int count = 0;

		ResultSet res = PROCESSOR.getPhysicalInteractionResults(connection);
		System.out.println("Processing Physical Interactions...");
		while (res.next()) {
			count++;
			String geneFeatureName = res.getString("dbentity1_id");
			Item gene = genes.get(geneFeatureName);
	    			
			String interactionNo = res.getString("annotation_id");
			String referenceNo = res.getString("reference_id");
			String interactionType = "physical interactions";
			String experimentType = res.getString("biogrid_experimental_system");
			String annotationType = res.getString("annotation_type");
			String modification = res.getString("modification");

			String interactingGeneFeatureName = res.getString("dbentity2_id");
			Item interactingGene = genes.get(interactingGeneFeatureName);

			//seen act1 interacting with act1 - check later
			//if(geneFeatureName.equals(interactingGeneFeatureName)) {
				//continue;
			//}
			
			String action = res.getString("bait_hit");
			String[] a = action.split("-");
			String role1 = a[0];
			String role2 = a[1];
			String source = res.getString("display_name");
			String phenotype = ""; //res.getString("phenotype");
			String citation = res.getString("citation");
			String pubmed = res.getString("pmid");
			String title = res.getString("title");
			String volume = res.getString("volume");
			String page = res.getString("page");
			String year = res.getString("year");
			String issue = res.getString("issue");
			String abbreviation = res.getString("med_abbr");
			String firstAuthor = res.getString("first_author");
			String dbxrefid = res.getString("sgdid");

			String interactionRefId = getInteraction(interactionNo,
					referenceNo, interactionType, experimentType,
					annotationType, modification, interactingGene, role1, source,
					phenotype, citation, gene, pubmed, title, volume, page,
					year, issue, abbreviation, dsId, firstAuthor, dbxrefid);
			
			//store the reverse relationship so that template changes do not have to be made
			String interactionRefId2 = getInteraction(interactionNo,
					referenceNo, interactionType, experimentType,
					annotationType, modification, gene, role2, source,
					phenotype, citation, interactingGene, pubmed, title, volume, page,
					year, issue, abbreviation, dsId, firstAuthor, dbxrefid);

		}
	}

	/**
	 * 
	 * @param connection
	 * @throws SQLException
	 * @throws ObjectStoreException
	 */

	private void processGeneticInteractions(Connection connection)
			throws SQLException, ObjectStoreException {

		String dsId = getBioGridDataSet();
		int count = 0;

		System.out.println("Processing Genetic Interactions...");
		ResultSet res = PROCESSOR.getGeneticInteractionResults(connection);

		while (res.next()) {
			count++;
			String geneFeatureName = res.getString("dbentity1_id");
	    			
			Item gene = genes.get(geneFeatureName); //can save on look-ups here

			String interactionNo = res.getString("annotation_id");
			String referenceNo = res.getString("reference_id");
			String interactionType = "genetic interactions"; //res.getString("interaction_type");
			String experimentType = res.getString("biogrid_experimental_system");
			String annotationType = "genetic interactions"; //res.getString("annotation_type");
			String modification = ""; //res.getString("modification");

			String interactingGeneFeatureName = res.getString("dbentity2_id");
			Item interactingGene = genes.get(interactingGeneFeatureName);

			//seen act1 interacting with act1 - check later
			//if(geneFeatureName.equals(interactingGeneFeatureName)) {
				//continue;
			//}
			
			String action = res.getString("bait_hit");
			String[] a = action.split("-");
			String role1 = a[0];
			String role2 = a[1];
			String source = res.getString("source");
			String phenotype = res.getString("phenotype");
			String citation = res.getString("citation");
			String pubmed = res.getString("pmid");
			String title = res.getString("title");
			String volume = res.getString("volume");
			String page = res.getString("page");
			String year = res.getString("year");
			String issue = res.getString("issue");
			String abbreviation = res.getString("med_abbr");
			String firstAuthor = res.getString("first_author");
			String dbxrefid = res.getString("sgdid");

			String interactionRefId = getInteraction(interactionNo,
					referenceNo, interactionType, experimentType,
					annotationType, modification, interactingGene, role1, source,
					phenotype, citation, gene, pubmed, title, volume, page,
					year, issue, abbreviation, dsId, firstAuthor, dbxrefid);
			
			//store the reverse relationship so that template changes do not have to be made; act1 in gene.X or participant.X
			String interactionRefId2 = getInteraction(interactionNo,
					referenceNo, interactionType, experimentType,
					annotationType, modification, gene, role2, source,
					phenotype, citation, interactingGene, pubmed, title, volume, page,
					year, issue, abbreviation, dsId, firstAuthor, dbxrefid);

		}
	}

	private void processPhenotypes(Connection connection) throws SQLException,
	ObjectStoreException {

		String prevGeneFeatureNo = "0"; //dbentity_id
		String prevPhenotypeAnnotNo = "0"; //annotation_id
		String prevGroupNo = "0"; //group_id	
		//String prevCondName = "";
		//String prevCondValue = "";	
		Item gene = null;
		Item phenoannot = null;
		Item pheno = null;
		
		System.out.println("Processing Phenotypes...");
		ResultSet res = PROCESSOR.getPhenotypeResults(connection);

		while (res.next()) {

			// use these to switch from feature to next && annotation to next && condition to next
			
			String geneFeatureNo = res.getString("dbentity_id");
			String phenotypeAnnotNo = res.getString("annotation_id");
			String groupNo = res.getString("group_id");
			
			System.out.println("dbentity no gene found " + geneFeatureNo);
			
			if (!geneFeatureNo.equalsIgnoreCase(prevGeneFeatureNo)) {
				gene = genes.get(geneFeatureNo);
			}
			
			//phenotype info
			String qualifier_observable = res.getString("phenotype");
			pheno = phenotypes.get(qualifier_observable);
			if(pheno == null){
				pheno = getPhenotype(qualifier_observable);
			}
			
			//phenotype annotation
			String experimentType = res.getString("experiment");
			String experimentComment = res.getString("experiment_comment");
			String alleleComment = res.getString("allele_comment");
			String reporterComment = res.getString("reporter_comment");
			String mutantType = res.getString("mutant");
			String reporter = res.getString("reporter");
			String allele = res.getString("allele");
			String assay = res.getString("assay");
			String strain_background = res.getString("strain_name");
			String details = res.getString("details");
			String pmid = res.getString("pmid");
			String feature_type = ""; //res.getString("feature_type");  <-- fix when NPM is addressed
			
			//phenotype condition
			String key = res.getString("condition_class");
			String value = res.getString("condition_name");
			String desc = res.getString("condition_value");
			String unit = res.getString("condition_unit");
			Item phenocond = null; 
			
			if (!phenotypeAnnotNo.equalsIgnoreCase(prevPhenotypeAnnotNo)) {
				phenoannot = getPhenotypeAnnotation(phenotypeAnnotNo, experimentType, experimentComment, alleleComment,
						reporterComment, mutantType, reporter, allele, assay, strain_background, details, gene, feature_type);	
			}else{
				phenoannot = phenotypeannots.get(phenotypeAnnotNo);	
			}

			phenocond = getPhenotypeCondition(key, value, desc, unit);
			
			phenoannot.addToCollection("phenotypeAnnotConds", phenocond.getIdentifier());
			phenoannot.addToCollection("phenotypes", pheno.getIdentifier());
			
			if (feature_type.equalsIgnoreCase("not physically mapped")) {
				phenoannot.addToCollection("notphysicallymapped", gene.getIdentifier());
			} else {
				phenoannot.addToCollection("genes", gene.getIdentifier());
			}			
			prevGeneFeatureNo = geneFeatureNo;
			prevPhenotypeAnnotNo = phenotypeAnnotNo;
			prevGroupNo = groupNo;
			//prevCondName = value;
			//prevCondValue = desc;
		}

	}

	private Item getPhenotype(String phenotype) throws ObjectStoreException {
		Item pheno = phenotypes.get("phenotype");
		//System.out.println("phenotype pair is  "+ phenotype);
		if(pheno == null) {
			
			if(phenotype.contains(":")){
			String t[] = phenotype.split(":");
			String qualifier = t[0].trim();
			String observable = t[1].trim();
			pheno = createItem("Phenotype");
			pheno.setAttribute("qualifier", qualifier);
			pheno.setAttribute("observable", observable);
			}else{
				pheno = createItem("Phenotype");
				pheno.setAttribute("qualifier", phenotype.trim());
			}
			phenotypes.put(phenotype, pheno);
          try {
				store(pheno);
			} catch (ObjectStoreException e) {
				throw new ObjectStoreException(e);
			}
		}
		return pheno;
	}
		
	private Item getPhenotypeCondition(String condClass, String condName, String condValue, String condUnit) throws ObjectStoreException {
		
		Item phenocond = createItem("PhenotypeAnnotationCond");
		
		if (condClass != null && StringUtils.isNotEmpty(condClass)) phenocond.setAttribute("className", condClass);
		if (condName != null && StringUtils.isNotEmpty(condName)) phenocond.setAttribute("name", condName);
		if (condValue != null && StringUtils.isNotEmpty(condValue)) phenocond.setAttribute("value", condValue);
		if (condUnit != null && StringUtils.isNotEmpty(condUnit)) phenocond.setAttribute("unit", condUnit);

		try {
			store(phenocond);
		} catch (ObjectStoreException e) {
			throw new ObjectStoreException(e);
		}
		return phenocond;
		
	}
	
	private Item getPhenotypeAnnotation(String phenotypeAnnotNo, String experimentType, String experimentComment, String alleleComment, String reporterComment,
			String mutantType, String reporter, String allele, String assay, String strain_background, String details, Item gene, String feature_type) throws ObjectStoreException {
		
		Item phenoannot = createItem("PhenotypeAnnotation");
		
		if (experimentType != null && StringUtils.isNotEmpty(experimentType)) phenoannot.setAttribute("experimentType", experimentType);
		if (experimentComment != null && StringUtils.isNotEmpty(experimentComment)) phenoannot.setAttribute("experimentComment", experimentComment);
		if (mutantType != null && StringUtils.isNotEmpty(mutantType)) phenoannot.setAttribute("mutantType", mutantType);		
		if (alleleComment != null && StringUtils.isNotEmpty(alleleComment)) phenoannot.setAttribute("alleleComment", alleleComment);
		if (reporterComment != null && StringUtils.isNotEmpty(reporterComment)) phenoannot.setAttribute("reporterComment", reporterComment);
		if (reporter != null && StringUtils.isNotEmpty(reporter)) phenoannot.setAttribute("reporter", reporter);
		if (allele != null && StringUtils.isNotEmpty(allele)) phenoannot.setAttribute("allele", allele);
		if (assay != null && StringUtils.isNotEmpty(assay)) phenoannot.setAttribute("assay", assay);
		if (strain_background != null && StringUtils.isNotEmpty(strain_background)) phenoannot.setAttribute("strainBackground", strain_background);
		if (details != null && StringUtils.isNotEmpty(details)) phenoannot.setAttribute("details", details);
	
		phenotypeannots.put(phenotypeAnnotNo, phenoannot);
		return phenoannot;

	}
	
	private String getLocation(Item subject, String chromosomeRefId,
			String startCoord, String stopCoord, String strand)
					throws ObjectStoreException {

		String start = startCoord;
		String end = stopCoord;

		/*
		 * String start = ""; //(strand.equals("-") ? stopCoord : startCoord);
		 * //was C for crick String end = ""; // (strand.equals("-") ?
		 * startCoord : stopCoord); //was C for crick
		 * 
		 * if(strand.equals("-")){ start = stopCoord; end = startCoord;
		 * System.out.println("start and stop should be reveresed.. " + start +
		 * "  " + end); }else{ end = stopCoord; start = startCoord; }
		 */

		/*
		 * if (StringUtils.isEmpty(startCoord)) { start = "0"; } if
		 * (StringUtils.isEmpty(stopCoord)) { end = "0"; }
		 */

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

	private String getLength(String start, String end)
			throws NumberFormatException {
		Integer a = new Integer(start);
		Integer b = new Integer(end);

		// if the coordinates are on the crick strand, they need to be reversed
		// or they result in a negative number
		if (a.compareTo(b) > 0) {
			a = new Integer(end);
			b = new Integer(start);
		}

		Integer length = new Integer(b.intValue() - a.intValue());
		return length.toString();
	}

	private String getChromosome(String identifier) throws ObjectStoreException {
		if (StringUtils.isEmpty(identifier)) {
			return null;
		}
		String refId = chromosomes.get(identifier);
		if (refId == null) {
			Item item = createItem("Chromosome");
			item.setAttribute("primaryIdentifier", identifier);
			item.setReference("organism", organism);
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

	private String getBioGridDataSet() throws ObjectStoreException {

		Item item = createItem("DataSet");
		item.setAttribute("name", "BioGRID interaction data set");


		Item ds = createItem("DataSource");
		ds.setAttribute("name", "BioGRID");
		ds.addToCollection("dataSets", item.getIdentifier());
		try {
			store(item);
			store(ds);
		} catch (ObjectStoreException e) {
			throw new ObjectStoreException(e);
		}

		return item.getIdentifier();

	}
	private String getPlasmid(String identifier) throws ObjectStoreException { // String
		// id,
		if (StringUtils.isEmpty(identifier)) {
			return null;
		}
		String refId = plasmids.get(identifier);
		if (refId == null) {
			Item item = createItem("Plasmid");
			item.setAttribute("primaryIdentifier", identifier);
			item.setReference("organism", organism);
			refId = item.getIdentifier();
			plasmids.put(identifier, refId);
			try {
				store(item);
			} catch (ObjectStoreException e) {
				throw new ObjectStoreException(e);
			}
		}
		return refId;
	}

	private String getSequence(String id, String residues, String length)
			throws ObjectStoreException {
		if (StringUtils.isEmpty(id)) {
			return null;
		}
		String refId = sequences.get(id);
		if (refId == null) {
			Item item = createItem("Sequence");
			item.setAttribute("residues", residues);
			item.setAttribute("length", length);
			// item.setReference("organism", organism);
			refId = item.getIdentifier();
			sequences.put(id, refId);
			try {
				store(item);
			} catch (ObjectStoreException e) {
				throw new ObjectStoreException(e);
			}
		}
		return refId;
	}

	private String getSynonym(String subjectId, String type, String value)
			throws ObjectStoreException {
		String key = subjectId + type + value;
		if (StringUtils.isEmpty(value)) {
			return null;
		}
		String refId = synonyms.get(key);
		if (refId == null) {
			Item syn = createItem("Synonym");
			syn.setReference("subject", subjectId);
			//syn.setAttribute("type", type);
			syn.setAttribute("value", value);
			refId = syn.getIdentifier();
			// synonyms.get(key);
			try {
				store(syn);
			} catch (ObjectStoreException e) {
				throw new ObjectStoreException(e);
			}
		}
		return refId;
	}


	private String getPathway(String geneIdentifier, String id, String name, String summaryType, String text)
			throws ObjectStoreException {

		Item crf = pathways.get(id);
		if (crf == null) {

			crf = createItem("Pathway");
			crf.setAttribute("identifier", id);
			crf.setAttribute("name", name);
			crf.setAttribute("summaryType", summaryType);
			crf.setAttribute("text", text);
			
			pathways.put(id, crf);

			crf.addToCollection("genes", geneIdentifier);

		}else{
			crf.addToCollection("genes", geneIdentifier);
		}

		String refId = crf.getIdentifier();
		return refId;

	}

	/**
	 * 
	 * @param interactionNo
	 * @param referenceNo
	 * @param interactionType
	 * @param experimentType
	 * @param annotationType
	 * @param modification
	 * @param interactingGene
	 * @param action
	 * @param source
	 * @param phenotype
	 * @param citation
	 * @param gene
	 * @param pubMedId
	 * @param title
	 * @param volume
	 * @param page
	 * @param year
	 * @param issue
	 * @param journal
	 * @param dsetIdentifier
	 * @param firstAuthor
	 * @param dbxrefid
	 * @return
	 * @throws ObjectStoreException
	 */
	private String getInteraction(String interactionNo, String referenceNo,
			String interactionType, String experimentType,
			String annotationType, String modification, Item interactingGene, String action,
			String source, String phenotype, String citation, Item gene,
			String pubMedId, String title, String volume, String page,
			String year, String issue, String journal, String dsetIdentifier, String firstAuthor, String dbxrefid)
					throws ObjectStoreException {

		Item item = getInteractionItem(gene.getIdentifier(), interactingGene.getIdentifier());	
		Item detail = createItem("InteractionDetail");		   

		detail.setAttribute("type", interactionType);		
		detail.setAttribute("annotationType", annotationType);
		if (StringUtils.isNotEmpty(modification)) detail.setAttribute("modification", modification);
		if (StringUtils.isNotEmpty(phenotype)) detail.setAttribute("phenotype", phenotype);
		detail.setAttribute("role1", action);
		detail.addToCollection("allInteractors", interactingGene.getIdentifier());
		detail.addToCollection("dataSets", dsetIdentifier);		

		String shortType = interactionType.substring(0, interactionType.indexOf(' ')); 
		detail.setAttribute("relationshipType", shortType); //interactionType
		String unqName = firstAuthor+"-"+pubMedId+"-"+experimentType;  

		//add publication as experiment type
		Item storedExperimentType = experimenttype.get(unqName);		
		if(storedExperimentType == null) {	
			
			storedExperimentType = createItem("InteractionExperiment");
			storedExperimentType.setAttribute("name", unqName);	
			experimenttype.put(unqName, storedExperimentType);			
			
			String storedTermId = interactionterms.get(experimentType);
			if (storedTermId != null) {
				storedExperimentType.addToCollection("interactionDetectionMethods", storedTermId );
			} else {
				storedTermId = getInteractionTerm(experimentType);
				storedExperimentType.addToCollection("interactionDetectionMethods", storedTermId );
			}
		}

		//add publication as reference on experiment
		Item storedRef = publications.get(referenceNo);

		if (storedRef != null) {
			storedExperimentType.setReference("publication", storedRef.getIdentifier());
		} else {

			Item pub = createItem("Publication");

			if (StringUtils.isNotEmpty(pubMedId)) {
				pub.setAttribute("pubMedId", pubMedId);
			}
			if (StringUtils.isNotEmpty(dbxrefid)) {
				pub.setAttribute("sgdDbXrefId", dbxrefid);
			}
			if (StringUtils.isNotEmpty(title)) {
				pub.setAttribute("title", title);
			}
			if (StringUtils.isNotEmpty(citation)) {
				pub.setAttribute("citation", citation);
			}
			if (StringUtils.isNotEmpty(journal)) {
				pub.setAttribute("journal", journal);
			}
			if (StringUtils.isNotEmpty(volume)) {
				pub.setAttribute("volume", volume);
			}
			if (StringUtils.isNotEmpty(page)) {
				pub.setAttribute("pages", page);
			}
			if (StringUtils.isNotEmpty(year)) {
				pub.setAttribute("year", year);
			}
			if (StringUtils.isNotEmpty(issue)) {
				pub.setAttribute("issue", issue);
			}
			publications.put(referenceNo, pub);
			storedExperimentType.setReference("publication", pub.getIdentifier());
		}

		detail.setReference("experiment", storedExperimentType.getIdentifier());	
		detail.setReference("interaction", item);
		//interactiondetail.put(detail.getIdentifier(), detail);	

		try {
			store(detail);
		} catch (ObjectStoreException e) {
			throw new ObjectStoreException(e);
		}

		interactions.put(item.getIdentifier(), item);	
		String refId = item.getIdentifier();
		return refId;


	}

	private Item getInteractionItem(String refId, String gene2RefId) throws ObjectStoreException {
		MultiKey key = new MultiKey(refId, gene2RefId);
		Item interaction = interactionsnew.get(key);
		if (interaction == null) {
			interaction = createItem("Interaction");
			interaction.setReference("participant1", refId); //gene1
			interaction.setReference("participant2", gene2RefId); //gene2
		}
		return interaction;
	}


	private String getLiteratureTopic(String topic) throws ObjectStoreException {
		String refId = literatureTopics.get(topic);
		if (refId == null) {
			Item item = createItem("LiteratureTopic");
			item.setAttribute("name", topic);
			try {
				store(item);
			} catch (ObjectStoreException e) {
				throw new ObjectStoreException(e);
			}
			refId = item.getIdentifier();
			literatureTopics.put(topic, refId);
		}
		return refId;
	}

	private void getPub(String referenceNo, String title, String pubMedId,
			String citation, ArrayList hm, String journal, String volume,
			String pages, String year, String issue, String abst, String dbxrefid, String datecreated)
					throws ObjectStoreException {

		Item storedRef = publications.get(referenceNo);

		if (storedRef == null) {

			Item item = createItem("Publication");

			if (StringUtils.isNotEmpty(pubMedId)) {
				item.setAttribute("pubMedId", pubMedId);
			}
			if (StringUtils.isNotEmpty(dbxrefid)) {
				item.setAttribute("sgdDbXrefId", dbxrefid);
			}
			if (StringUtils.isNotEmpty(title)) {
				item.setAttribute("title", title);
			}
			if (StringUtils.isNotEmpty(citation)) {
				item.setAttribute("citation", citation);
			}
			if (StringUtils.isNotEmpty(journal)) {
				item.setAttribute("journal", journal);
			}
			if (StringUtils.isNotEmpty(volume)) {
				item.setAttribute("volume", volume);
			}
			if (StringUtils.isNotEmpty(pages)) {
				item.setAttribute("pages", pages);
			}
			if (StringUtils.isNotEmpty(year)) {
				item.setAttribute("year", year);
			}
			if (StringUtils.isNotEmpty(issue)) {
				item.setAttribute("issue", issue);
			}
			if (StringUtils.isNotEmpty(abst)) {
				item.setAttribute("summary", abst);
			}
			if (StringUtils.isNotEmpty(datecreated)) {
				item.setAttribute("dateCreated", datecreated);
			}

			Iterator iter = hm.iterator();
			while (iter.hasNext()) {
				String value = (String) iter.next();
				item.addToCollection("literatureTopics",getLiteratureTopic(value));
			}
			publications.put(referenceNo, item);
		}

	}
	
	private Item getExistingPub(String referenceNo)
					throws ObjectStoreException {
		Item storedRef = publications.get(referenceNo);
		return storedRef;
	}
	

	private void getPubPhenotype(String phenoAnnotNo, String prevReferenceNo,
			String title, String pubMedId, String citation, String journal,
			String volume, String pages, String year, String issue, String dbxrefid, String datecreated)
					throws ObjectStoreException {

		Item storedPheno = phenotypeannots.get(phenoAnnotNo);

		if (storedPheno != null) {

			Item storedRef = publications.get(prevReferenceNo);

			if (storedRef != null) {
				storedPheno.addToCollection("publications", storedRef
						.getIdentifier());
				storedRef.addToCollection("phenotypeAnnotations", storedPheno.getIdentifier());
			} else {
				Item item = createItem("Publication");

				if (StringUtils.isNotEmpty(pubMedId)) {
					item.setAttribute("pubMedId", pubMedId);
				}
				if (StringUtils.isNotEmpty(dbxrefid)) {
					item.setAttribute("sgdDbXrefId", dbxrefid);
				}
				if (StringUtils.isNotEmpty(title)) {
					item.setAttribute("title", title);
				}
				if (StringUtils.isNotEmpty(citation)) {
					item.setAttribute("citation", citation);
				}
				if (StringUtils.isNotEmpty(journal)) {
					item.setAttribute("journal", journal);
				}
				if (StringUtils.isNotEmpty(volume)) {
					item.setAttribute("volume", volume);
				}
				if (StringUtils.isNotEmpty(pages)) {
					item.setAttribute("pages", pages);
				}
				if (StringUtils.isNotEmpty(year)) {
					item.setAttribute("year", year);
				}
				if (StringUtils.isNotEmpty(issue)) {
					item.setAttribute("issue", issue);
				}
				if (StringUtils.isNotEmpty(datecreated)) {
					item.setAttribute("dateCreated", datecreated);
				}

				publications.put(prevReferenceNo, item);

				storedPheno.addToCollection("publications", item.getIdentifier());
				item.addToCollection("phenotypes", storedPheno.getIdentifier());

			}

		}

	}

	/**
	 * 
	 * @param featureNo
	 * @param issue
	 * @param volume
	 * @param pubMedId
	 * @param pages
	 * @param title
	 * @param year
	 * @param citation
	 * @return
	 * @throws ObjectStoreException
	 */
	private void getPubAnnot(String referenceNo, String title, String pubMedId,
			String citation, ArrayList hm, Item gene, String journal,
			String volume, String pages, String year, String issue, String dbxrefid, String datecreated)
					throws ObjectStoreException {

		Item pubAnnot = createItem("PublicationAnnotation");
		Item storedRef = publications.get(referenceNo);

		if (storedRef == null) {
			Item item = createItem("Publication");

			if (StringUtils.isNotEmpty(pubMedId)) {
				item.setAttribute("pubMedId", pubMedId);
			}
			if (StringUtils.isNotEmpty(dbxrefid)) {
				item.setAttribute("sgdDbXrefId", dbxrefid);
			}
			if (StringUtils.isNotEmpty(title)) {
				item.setAttribute("title", title);
			}
			if (StringUtils.isNotEmpty(citation)) {
				item.setAttribute("citation", citation);
			}
			if (StringUtils.isNotEmpty(journal)) {
				item.setAttribute("journal", journal);
			}
			if (StringUtils.isNotEmpty(volume)) {
				item.setAttribute("volume", volume);
			}
			if (StringUtils.isNotEmpty(pages)) {
				item.setAttribute("pages", pages);
			}
			if (StringUtils.isNotEmpty(year)) {
				item.setAttribute("year", year);
			}
			if (StringUtils.isNotEmpty(issue)) {
				item.setAttribute("issue", issue);
			}
			if (StringUtils.isNotEmpty(datecreated)) {
				item.setAttribute("dateCreated", datecreated);
			}

			Iterator iter = hm.iterator();
			while (iter.hasNext()) {
				String value = (String) iter.next();
				pubAnnot.addToCollection("literatureTopics", getLiteratureTopic(value));
			}

			//item.addToCollection("genes", gene.getIdentifier());
			item.addToCollection("bioEntities", gene.getIdentifier());

			publications.put(referenceNo, item);

			pubAnnot.setReference("publication", item.getIdentifier());
			pubAnnot.addToCollection("genes", gene.getIdentifier());

			// in order for the publication enrichment to work, genes needs to
			// have publications directly as a collection, I think.
			gene.addToCollection("publications", item.getIdentifier());

		} else {
			// this means this publication appears for a different gene, lets
			// add the literature topics
			Iterator iter = hm.iterator();
			while (iter.hasNext()) {
				String value = (String) iter.next();
				pubAnnot.addToCollection("literatureTopics",
						getLiteratureTopic(value));
			}

			pubAnnot.setReference("publication", storedRef.getIdentifier());
			pubAnnot.addToCollection("genes", gene.getIdentifier());

			//storedRef.addToCollection("genes", gene.getIdentifier());
			storedRef.addToCollection("bioEntities", gene.getIdentifier());

			gene.addToCollection("publications", storedRef.getIdentifier());
		}

		try {
			store(pubAnnot);
		} catch (ObjectStoreException e) {
			throw new ObjectStoreException(e);
		}

	}
	
    private String getInteractionTerm(String identifier)  throws ObjectStoreException {
            String refId = interactionterms.get(identifier);
            if (refId != null) {
                return refId;
            }
            Item item = createItem("InteractionTerm");
            item.setAttribute("identifier", identifier);
            interactionterms.put(identifier, item.getIdentifier());
            try {
                store(item);
            } catch (ObjectStoreException e) {
                throw new ObjectStoreException(e);
            }
            return item.getIdentifier();
        }

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getDataSetTitle(@SuppressWarnings("unused") int taxonId) {
		return DATASET_TITLE;
	}
}
