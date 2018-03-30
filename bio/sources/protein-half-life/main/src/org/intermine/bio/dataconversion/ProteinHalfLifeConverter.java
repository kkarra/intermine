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
public class ProteinHalfLifeConverter extends BioFileConverter
{
	//
	protected static final Logger LOG = Logger.getLogger(ProteinHalfLifeConverter.class);
	private static final String DATASET_TITLE = "Protein Half-life data from Christiano R, et al 2014";
	private static final String DATA_SOURCE_NAME = "SGD";
	private final Map<String, Item> proteinIdMap = new HashMap<String, Item>();
	private final Map<String, Item> sites = new HashMap<String, Item>();
	private final Map<String, Item> pubmedIdMap = new HashMap<String, Item>();

	/**
	 * Constructor
	 * @param writer the ItemWriter used to handle the resultant items
	 * @param model the Model
	 */
	public ProteinHalfLifeConverter(ItemWriter writer, Model model) {
		super(writer, model, DATA_SOURCE_NAME, DATASET_TITLE);
	}

	/**
	 * 
	 *
	 * {@inheritDoc}
	 */
	public void process(Reader reader) throws Exception {
		processHalfLifeDataFile(reader); 
		storeProteins();
	}


	/**
	 * 
	 * @param reader
	 * @throws Exception
	 * @throws ObjectStoreException
	 */
	private void processHalfLifeDataFile(Reader preader) throws Exception, ObjectStoreException {

		/*
		 * Sample line 
		 * protein half-life       YMR028W 8.2     min     0.1     hr      25466257
         * protein half-life       YLR110C 10.0    min     0.2     hr      25466257
		 */   	 
		System.out.println("Processing Protien HalfLife Data file....");    

		Iterator<?> tsvIter;
		try {
			tsvIter = FormattedTextParser.parseTabDelimitedReader(preader);
		} catch (Exception e) {
			throw new BuildException("cannot parse file: " + getCurrentFile(), e);
		}

		while (tsvIter.hasNext()) {

			String[] line = (String[]) tsvIter.next();

			if (line.length < 5) {
				LOG.error("Couldn't process line. Expected 6 cols, but was " + line.length);
				continue;
			}

			String experiment = line[0].trim();
			String protein =  line[1].trim();  
			String value = line[2].trim();
			String units = line[3].trim();
			String pmid = line[4].trim();

			newProduct(experiment, protein, value, units, pmid);

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
	private void newProduct(String experiment, String proteinId, String value, String units, String pmid)
					throws ObjectStoreException, Exception {		

		Item protein = getProteinItem(proteinId);		
		
		Item pmods = getProteinHalfLife(experiment, value, units, pmid);
		 protein.addToCollection("proteinHalfLife", pmods.getIdentifier());

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
	private Item getProteinHalfLife(String experiment, String value, String units, String pmid) throws ObjectStoreException {

		Item item = createItem("ProteinHalfLife");

		if(StringUtils.isNotEmpty(experiment)){  item.setAttribute("experiment", experiment);}
		if(StringUtils.isNotEmpty(value)){  item.setAttribute("value", value);}
		if(StringUtils.isNotEmpty(units)){  item.setAttribute("units", units);}

		
		item.setAttribute("source", "SGD");

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
