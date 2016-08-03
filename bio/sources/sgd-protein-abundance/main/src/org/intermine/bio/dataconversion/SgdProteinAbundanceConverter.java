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
public class SgdProteinAbundanceConverter extends BioFileConverter
{
	//
	protected static final Logger LOG = Logger.getLogger(SgdProteinAbundanceConverter.class);
	private static final String DATASET_TITLE = "Protein Abundance data from Ghaemmaghami et al 2003";
	private static final String DATA_SOURCE_NAME = "SGD";
	private final Map<String, Item> proteinIdMap = new HashMap<String, Item>();
	private final Map<String, Item> sites = new HashMap<String, Item>();
	private final Map<String, Item> pubmedIdMap = new HashMap<String, Item>();

	/**
	 * Constructor
	 * @param writer the ItemWriter used to handle the resultant items
	 * @param model the Model
	 */
	public SgdProteinAbundanceConverter(ItemWriter writer, Model model) {
		super(writer, model, DATA_SOURCE_NAME, DATASET_TITLE);
	}

	/**
	 * 
	 *
	 * {@inheritDoc}
	 */
	public void process(Reader reader) throws Exception {
		processAbundanceDataFile(reader); 
		storeProteins();
	}


	/**
	 * 
	 * @param reader
	 * @throws Exception
	 * @throws ObjectStoreException
	 */
	private void processAbundanceDataFile(Reader preader) throws Exception, ObjectStoreException {

		/*
		 * Sample line 
		 * orfid   yORF    gene name GFP tagged?  GFP visualized? 
		 * TAP visualized? abundance error   localization summary   
		 * ambiguous mitochondrion vacuole spindle pole cell periphery punctate composite
		 * vacuolar membrane ER  nuclear periphery  endosome bud neck  microtubule Golgi  
		 * Golgi to vacuole peroxisome  actin   nucleolus  cytoplasm      
		 * ER to Golgi Golgi to ER lipid particle  nucleus bud 
		 */   	 
		System.out.println("Processing Protien Abundance Data file....");    

		Iterator<?> tsvIter;
		try {
			tsvIter = FormattedTextParser.parseTabDelimitedReader(preader);
		} catch (Exception e) {
			throw new BuildException("cannot parse file: " + getCurrentFile(), e);
		}

		while (tsvIter.hasNext()) {

			String[] line = (String[]) tsvIter.next();

			if (line.length < 8) {
				LOG.error("Couldn't process line. Expected 8 cols, but was " + line.length);
				continue;
			}

			String protein =  line[1].trim();     
			String gfptagged = line[3].trim();
			String gfpvisualized =  line[4].trim(); 
			String tapvisualized = line[5].trim();
			String abundance = line[6].trim();
			String newabundance;
			if(abundance.equalsIgnoreCase("not quantitated") || abundance.equalsIgnoreCase("not visualized")) {
				newabundance = "";
			}else{
				newabundance = abundance;
			}
			String error = line[7].trim();
			String localization = line[8].trim();
			String pmid = line[9].trim();
			String units = line[10].trim();

			newProduct(protein, gfptagged, gfpvisualized, tapvisualized, newabundance, error, localization, pmid, units);

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
	private void newProduct(String proteinId, String gfptagged, String gfpvisualized, String tapvisualized, String abundance,
			String error, String localization, String pmid, String units)
					throws ObjectStoreException, Exception {		

		Item protein = getProteinItem(proteinId);		
		Item pmods = getProteinAbundance(gfptagged, gfpvisualized,tapvisualized, abundance, error, localization, pmid, units);
		protein.addToCollection("proteinAbundance", pmods.getIdentifier());

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
	private Item getProteinAbundance(String gfptagged, String gfpvisualized, String tapvisualized, String abundance,
			String error, String localization, String pmid, String units) throws ObjectStoreException {

		Item item = createItem("ProteinAbundance");

		if(StringUtils.isNotEmpty(gfptagged)){  item.setAttribute("gfpTagged", gfptagged);}
		if(StringUtils.isNotEmpty(gfpvisualized)){  item.setAttribute("gfpVisualized", gfpvisualized);}
		if(StringUtils.isNotEmpty(tapvisualized)){  item.setAttribute("tapVisualized", tapvisualized);}
		if(StringUtils.isNotEmpty(abundance)){ item.setAttribute("abundance", abundance);}
		if(StringUtils.isNotEmpty(units)){ item.setAttribute("units", units);}
		if(StringUtils.isNotEmpty(error)){  item.setAttribute("error", error);}
		
		item.setAttribute("source", "SGD");

		if(!StringUtils.isEmpty(localization)) {

			if(localization.contains(",")){

				String[]t = localization.split(",");
				for(int i=0; i< t.length; i++){
					String site = t[i];
					Item locsite = sites.get(site);			
					if(locsite == null){
						locsite = createItem("Localization");
						locsite.setAttribute("site", site);
						try {
							store(locsite);
						} catch (ObjectStoreException e) {
							throw new ObjectStoreException(e);
						}	

						item.addToCollection("localizedIn", locsite.getIdentifier());
					}else{
						item.addToCollection("localizedIn", locsite.getIdentifier());
					}
				}
			}else{

				Item locsite = sites.get(localization);			
				if(locsite == null){
					locsite = createItem("Localization");
					locsite.setAttribute("site", localization);
					try {
						store(locsite);
					} catch (ObjectStoreException e) {
						throw new ObjectStoreException(e);
					}	

					item.addToCollection("localizedIn", locsite.getIdentifier());
				}else{
					item.addToCollection("localizedIn", locsite.getIdentifier());
				}

			}
		}

		Item publication = pubmedIdMap.get(pmid);

		if (publication == null) {
			
			publication = createItem("Publication");			
			publication.setAttribute("pubMedId", pmid);			 
			pubmedIdMap.put(pmid, publication);
			item.setReference("publication", publication);  
			try {
				store(publication);
			} catch (ObjectStoreException e) {
				throw new ObjectStoreException(e);
			}				
		}else{
			item.setReference("publication", publication);   
		}

		try {
			store(item);
		} catch (ObjectStoreException e) {
			throw new ObjectStoreException(e);
		}	

		return item;
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
