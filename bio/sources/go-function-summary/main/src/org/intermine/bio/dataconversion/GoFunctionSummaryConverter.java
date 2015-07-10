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
public class GoFunctionSummaryConverter extends BioFileConverter   {
	//
	private static final String DATASET_TITLE = "GO Function Summary";
	private static final String DATA_SOURCE_NAME = "SGD curated";
	private final Map<String, Item> genes = new HashMap<String, Item>();

	/**
	 * Constructor
	 * @param writer the ItemWriter used to handle the resultant items
	 * @param model the Model
	 */
	public GoFunctionSummaryConverter(ItemWriter writer, Model model) {
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
		 * Sample line 
		 * UniProtKB	[0]
		 * P00330	[1]
		 * ADH1 [2]	
		 * Alcohol dehydrogenase 1	[3]
		 * ADH1_YEAST|ADH1|ADC1|YOL086C|O0947	[4]
		 * protein	[5]
		 * taxon:559292		[6]
		 * SGD:S000005446	[7]
		 * db_subset=Swiss-Prot|go_annotation_complete=20051214|go_annotation_summary=Alcohol dehydrogenase required for the reduction of acetaldehyde to ethanol [8]
		 */   	 

		/* -- new sample line 
		 * UniProtKB	[0]
		 * Q99344	[1]
		 * UBA3	[2]
		 * NEDD8-activating enzyme E1 catalytic subunit	[3]
		 * UBA3_YEAST|UBA3|YPR066W|YP9499.21	[4]
		 * protein	[5]
		 * taxon:559292	[6]	
		 * ***empty column*** [7]
		 * SGD:S000006270	[8]
		 * db_subset=Swiss-Prot|go_annotation_complete=20031028|go_annotation_summary=NEDD8 (Rub1) activating enzyme involved in protein neddylation|uniprot_proteome=UP000002311 [9]
		 * 
		 */
		System.out.println("Processing Function Summary Data file....");  

		BufferedReader br = new BufferedReader(preader);
		String line = null;
		int count = 0;

		while ((line = br.readLine()) != null) {

			if (line.startsWith("!")) {
				continue;
			}

			if (line.indexOf("go_annotation_summary") < 0 || line.indexOf("SGD:") < 0){
				System.out.println("Line does not contain summary and SGD:  " + count);				
				continue;
			}

			String[] array = line.split("\t", -1); // keep trailing empty Strings

			if (array.length < 10) {
				System.out.println("Not enough elements (should be  10 not "+ array.length + ") in line: " + line);
				continue;
			}
			String annot = array[9].trim();
			String gene =  array[8].trim().replaceAll("SGD:", ""); 

			String t[] = annot.split("go_annotation_summary=");

			if(t.length != 0) {
				String summary = t[1];			
				newProduct(gene, summary);
			}	

			count++;
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
		gene.setAttribute("functionSummary", summary);

	}

	/**
	 * 
	 * @param geneId
	 * @return
	 * @throws ObjectStoreException
	 */
	private Item getGeneItem(String geneId) throws ObjectStoreException{

		Item gene = genes.get(geneId);

		if (gene == null) {      	
			gene = createItem("Gene");
			genes.put(geneId, gene);
			gene.setAttribute("primaryIdentifier", geneId);                  		
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
