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

import java.io.BufferedReader;
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
public class PhenotypeSummaryConverter extends BioFileConverter   {
	//
	private static final String DATASET_TITLE = "Phenotype Summary";
	private static final String DATA_SOURCE_NAME = "SGD curated";
	private final Map<String, Item> genes = new HashMap<String, Item>();

	/**
	 * Constructor
	 * @param writer the ItemWriter used to handle the resultant items
	 * @param model the Model
	 */
	public PhenotypeSummaryConverter(ItemWriter writer, Model model) {
		super(writer, model, DATA_SOURCE_NAME, DATASET_TITLE);
	}

	/**
	 * 
	 *
	 * {@inheritDoc}
	 */
	public void process(Reader reader) throws Exception {
		processFile(reader); 
		storeGenes();

	}


	/**
	 * 
	 * @param reader
	 * @throws Exception
	 * @throws ObjectStoreException
	 */
	private void processFile(Reader preader) throws Exception, ObjectStoreException {

		/*
		 * Systematic name
		 * summary
		 */ 	 
		System.out.println("Processing Phenotype Summary Data file....");  

		BufferedReader br = new BufferedReader(preader);
		String line = null;

		while ((line = br.readLine()) != null) {

			String[] array = line.split("\t", -1); // keep trailing empty Strings
			if (array.length < 2) {
				throw new IllegalArgumentException("Not enough elements (should be  2 not "
						+ array.length + ") in line: " + line);
			}
			String gene =  array[0].trim(); 			
			String summary = array[1].trim();		
			newProduct(gene, summary);
		}
		preader.close();

	}

	/**
	 * 
	 * @param geneId
	 * @param summary
	 * @throws ObjectStoreException
	 * @throws Exception
	 */
	private void newProduct(String geneId, String summary)
			throws ObjectStoreException, Exception {		

		Item gene = getGeneItem(geneId);		
		gene.setAttribute("phenotypeSummary", summary);

	}


	private Item getGeneItem(String geneId) throws ObjectStoreException{

		Item gene = genes.get(geneId);

		if (gene == null) {      	
			gene = createItem("Gene");
			genes.put(geneId, gene);
			gene.setAttribute("secondaryIdentifier", geneId);                  		
		}

		return gene;

	}

	/**
	 * 
	 * @throws Exception
	 */
	private void storeGenes() throws Exception{
		for (Item gene : genes.values()) {
			try {
				store(gene);
			} catch (ObjectStoreException e) {
				throw new Exception(e);
			}
		}

	}

}
