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
import java.io.BufferedReader;


import org.intermine.dataconversion.ItemWriter;
import org.intermine.metadata.Model;
import org.intermine.xml.full.Item;
import java.util.Map;
import java.util.HashMap;
import org.apache.log4j.Logger;
import org.intermine.objectstore.ObjectStoreException; 



/**
 * 
 * @author
 */
public class SgdYetfascoConverter extends BioFileConverter
{
    
    /**
	 * Constructor
	 * @param writer the ItemWriter used to handle the resultant items
	 * @param model the Model
	 */
	public SgdYetfascoConverter(ItemWriter writer, Model model) {
	    super(writer, model, DATA_SOURCE_NAME, DATASET_TITLE);
	}


	private final Map<String, Item> pubmedIdMap = new HashMap<String, Item>();
    private final Map<String, Item> geneIdMap = new HashMap<String, Item>();
    private static final String TAXON_ID = "4932";
    protected static final Logger LOG = Logger.getLogger(SgdYetfascoConverter.class);
    private static final String DATASET_TITLE = "SGD/YEASTRACT Regulation data";
    private static final String DATA_SOURCE_NAME = "Regulation";

    /**
     * 
     *
     * {@inheritDoc}
     */
    public void process(Reader reader) throws Exception {
    	

        BufferedReader br = new BufferedReader(reader);
        String line = null;

        // loop through entire file
        while ((line = br.readLine()) != null) {
           
            System.out.println("line is.. " + line);
            
            String[] array = line.split("\t", -1); // keep trailing empty Strings
            /*if (array.length < 10) {
                throw new IllegalArgumentException("Not enough elements (should be > 10  not "
                        + array.length + ") in line: " + line);
            }*/

            String regulatorGene = array[1];
            String targetGene = array[3];
            String strEvidence = array[4];
            String evidenceCode = array[5];
            String condition = array[6]; 
            String regulationDirection = array[7];
            String pmid = array[8];
            String source = array[9];
            
            String productIdentifier = newProduct(regulatorGene, targetGene, strEvidence, 
            		evidenceCode, condition,  regulationDirection, pmid, source  );
                     
        }


    }
    
    

    private String newProduct(String regulatorGene, String targetGene, String strEvidence, 
            		String evidenceCode, String condition, String regulationDirection, String pmid, String source) 
            				throws ObjectStoreException {
    	        
           Item bindingSite = createItem("TFBindingSite");
           
           Item gene = getGene(regulatorGene);
           
            if (gene == null) {       	   
             	 gene = createItem("Gene");
            	  geneIdMap.put(regulatorGene, gene);
                  gene.setAttribute("secondaryIdentifier", regulatorGene);
             }
          			
            /*bindingSite.setReference("evidenceMethod", strEvidence);
            bindingSite.setReference("evidenceId", evidenceCode);
            bindingSite.setReference("experimentCondition", condition);
            bindingSite.setReference("regulationDirection", regulationDirection);
            bindingSite.setReference("datasource", source);*/
            
        	
			try {
				store(gene);
			} catch (ObjectStoreException e) {
				throw new ObjectStoreException(e);
			}

           return gene.getIdentifier();
          
    }
    
    
    private Item getGene(String regulatorGene) {
    	 
        Item gene = geneIdMap.get(regulatorGene);
        if (gene == null) {
            gene = createItem("Gene");
            geneIdMap.put(regulatorGene, gene);
            gene.setAttribute("secondaryIdentifier", regulatorGene);
            //gene.setReference("organism", getOrganism());
        }
        return gene;
 
    }
    
    
    
    
}
