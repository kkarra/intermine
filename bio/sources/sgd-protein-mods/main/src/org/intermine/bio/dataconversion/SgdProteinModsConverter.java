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

import java.io.Reader;
import java.util.Iterator;

import org.apache.tools.ant.BuildException;
import org.intermine.dataconversion.ItemWriter;
import org.intermine.metadata.Model;
import org.intermine.objectstore.ObjectStoreException;
import org.intermine.util.FormattedTextParser;
import org.intermine.xml.full.Item;
import org.apache.commons.collections.keyvalue.MultiKey;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import java.util.Map;
import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


/**
 * 
 * @author
 */
public class SgdProteinModsConverter extends BioFileConverter
{
	//
	protected static final Logger LOG = Logger.getLogger(SgdProteinModsConverter.class);
	private static final String DATASET_TITLE = "Protein Modifications  data set";
	private static final String DATA_SOURCE_NAME = "SGD";
	private final Map<String, Item> proteinIdMap = new HashMap<String, Item>();
	private final Map<String, Item> pubmedIdMap = new HashMap<String, Item>();
    private final Map<String, Item> geneIdMap = new HashMap<String, Item>();
    private static final String TAXON_ID = "4932";
	/**
	 * Constructor
	 * @param writer the ItemWriter used to handle the resultant items
	 * @param model the Model
	 */
	public SgdProteinModsConverter(ItemWriter writer, Model model) {
		super(writer, model, DATA_SOURCE_NAME, DATASET_TITLE);
	}

	/**
	 * 
	 *
	 * {@inheritDoc}
	 */
	public void process(Reader reader) throws Exception {
		processDomainDataFile(reader); 
		storeProteins();
		storeGenes();
	}


	/**
	 * 
	 * @param reader
	 * @throws Exception
	 * @throws ObjectStoreException
	 */
	private void processDomainDataFile(Reader preader) throws Exception, ObjectStoreException {

		/*
		 * Sample line 
		 * feature_name    Site    Site Functions  Modification    Modifier        Source  Reference
          YAL005C K521            ubiquitination          SGD     23793018    
		 */   	 
		System.out.println("Processing Protien Modification Data  file....");    

		Iterator<?> tsvIter;
		try {
			tsvIter = FormattedTextParser.parseTabDelimitedReader(preader);
		} catch (Exception e) {
			throw new BuildException("cannot parse file: " + getCurrentFile(), e);
		}

		while (tsvIter.hasNext()) {

			String[] line = (String[]) tsvIter.next();

			if (line.length < 5) {
				LOG.error("Couldn't process line. Expected 5 cols, but was " + line.length);
				System.out.println("line skipped: " + line.length);
				continue;

			}

			String protein =  line[0].trim();     
			String modSite = line[1].trim();
			String modType =  line[3].trim();
			String modifier = line[4].trim(); //can be multiple with | separation
			String source =  line[5].trim(); 
			String pmid = line[6].trim();

		     System.out.println(protein + " " + modSite + " " + modType);
			newProduct(protein, modSite, modType, modifier, source, pmid);

		}

		preader.close();

	}

	/**
	 * 
	 * @param proteinId
	 * @param modSite
	 * @param modType
	 * @param source
	 * @param pmid
	 * @throws ObjectStoreException
	 * @throws Exception
	 */
	private void newProduct(String proteinId, String modSite, String modType, String modifier, String source, String pmid)
			throws ObjectStoreException, Exception {		

		Item protein = getProteinItem(proteinId);		
		Item pmods = getProteinMod(modSite, modType, modifier, source, pmid);
		protein.addToCollection("proteinModificationSites", pmods.getIdentifier());

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
	 * @param modSite
	 * @param modType
	 * @param source
	 * @param pmid
	 * @return
	 */
	private Item getProteinMod(String modSite, String modType, String modifier, String source, String pmid) throws ObjectStoreException {

		Item item = createItem("ProteinModificationSite");

		item.setAttribute("modificationType", modType);
		item.setAttribute("modificationSite", modSite);
		item.setAttribute("source", source);

		if (!StringUtils.isEmpty(modifier)) {
			if(modifier.contains("|")){
				String[] mods = modifier.split("\\|");
				for (int i = 0; i < mods.length; i++) {
					String geneId = getGene(mods[i]);
					item.addToCollection("modifier", geneId);
					System.out.println("modifier:  "+ mods[i] + "geneid" + geneId);
				}
			}else{
				String geneId = getGene(modifier);
				item.addToCollection("modifier", geneId);
			}
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
		item.setReference("publication", publication);      

		try {
			store(item);
		} catch (ObjectStoreException e) {
			throw new ObjectStoreException(e);
		}	

		return item;
	}
	
	/**
	 * 
	 * @param geneId
	 * @param taxonId
	 * @return
	 * @throws ObjectStoreException
	 */
    private String getGene(String geneId)
            throws ObjectStoreException {

            Item gene = geneIdMap.get(geneId);
            
            if (gene == null) {
                gene = createItem("Gene");
                gene.setAttribute("symbol", geneId);
                gene.setReference("organism", getOrganism(TAXON_ID));
                geneIdMap.put(geneId, gene);
                //refId = gene.getIdentifier();
                //store(gene);
            }
            return gene.getIdentifier();
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
	/**
	 * 
	 * @throws Exception
	 */
	private void storeGenes() throws Exception{
		for (Item gene : geneIdMap.values()) {
			try {
				store(gene);
			} catch (ObjectStoreException e) {
				throw new Exception(e);
			}
		}

	}

}
