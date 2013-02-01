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
    private final Map<String, Item> evidenceMap = new HashMap<String, Item>();
    private static final String TAXON_ID = "4932";
    private Item organism;
    private File bindingSitesFile; 
    private File paragraphsFile;
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

    /**
     * 
     *
     * {@inheritDoc}
     */
    public void process(Reader reader) throws Exception {     
    	
    	processBindingSitesFile(new FileReader(bindingSitesFile));            
        processParagraphsFile(new FileReader(paragraphsFile));      
        processRegulationDataFile(reader);        
        storeGenes();
        
    }
    
   
/**
 * 
 * @param reader
 * @throws Exception
 * @throws ObjectStoreException
 */
    private void processRegulationDataFile(Reader preader) throws Exception, ObjectStoreException {
    	
    	/* Columns in the file..
    	 * factor gene name
    	 * factor feature name
    	 * target gene name
    	 * target feature name
    	 * evidence
    	 * ECO ID
    	 * condition
    	 * direction of regulation
    	 * PMID
    	 * Source
    	 */
    	
	    System.out.println("Processing Regulation Data file....");
	    
    	
        Iterator<?> tsvIter;
        try {
            tsvIter = FormattedTextParser.parseTabDelimitedReader(preader);
        } catch (Exception e) {
            throw new BuildException("cannot parse file: " + getCurrentFile(), e);
        }

            while (tsvIter.hasNext()) {
            
           String[] line = (String[]) tsvIter.next();
           
           if (line.length < 10) {
               LOG.error("Couldn't process line. Expected 10 cols, but was " + line.length);
               continue;
           }
                
            String factorGene =  line[1].trim();
            factorGene.toUpperCase();
            String targetGene = line[3].trim();
            targetGene.toUpperCase();
            String strEvidence = line[4].trim();
            String evidenceCode =  line[5].trim();
            String condition =  line[6].trim(); 
            String regulationDirection =  line[7].trim();
            String pmid =  line[8].trim();
            String source =  line[9].trim();

            newProduct(factorGene, targetGene, strEvidence, 
            		evidenceCode, condition,  regulationDirection, pmid, source);
                     
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
            geneId.toUpperCase();
           
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
        System.out.println("line in pargraphs is ... " + tsvIter.next());
        
        while (tsvIter.hasNext()) {
            String[] line = (String[]) tsvIter.next();

            if (line.length != 4) {
                LOG.error("Couldn't process line. Expected 4 cols, but was " + line.length);
                continue;
            }
            
            String geneId = line[0].trim();
            geneId.toUpperCase();
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
    private void newProduct(String factorGene, String targetGene, String strEvidence, 
            		String evidenceCode, String condition, String regulationDirection, String pmid, String source) 
            				throws ObjectStoreException {
    	                  
       Item rGene = getGeneItem(factorGene);
       Item tGene = getGeneItem(targetGene);
           
        if (rGene != null && tGene != null) {       	               	           			

           Item bindingSite = createItem("TFBindingSite");  
           String name = factorGene + "_binding_site";
           
           bindingSite.setAttribute("name", name);
           bindingSite.setReference("factor", rGene.getIdentifier());
       	   bindingSite.setReference("gene", tGene.getIdentifier());

    	
       	if (StringUtils.isNotEmpty(strEvidence)) {
       		          	
           	Item evidence = evidenceMap.get(pmid);
           	
           	if(evidence == null) {
           		evidence = createItem("RegulationEvidence");
                evidenceMap.put(strEvidence, evidence);
                evidence.setAttribute("evidenceMethod", strEvidence); 
               	if (StringUtils.isNotEmpty(evidenceCode)) {
                   evidence.setAttribute("evidenceId", evidenceCode);
               	}
       			try {
       				store(evidence);
       			} catch (ObjectStoreException e) {
       				throw new ObjectStoreException(e);
       			}			
           	}
           	bindingSite.setReference("regEvidence", evidence.getIdentifier());
                   	
       	}
       	
       	if (StringUtils.isNotEmpty(condition)) {
       		bindingSite.setAttribute("experimentCondition", condition);
       	}
       	if (StringUtils.isNotEmpty(regulationDirection)) {
       		bindingSite.setAttribute("regulationDirection", regulationDirection);
       	}
       	if (StringUtils.isNotEmpty(source)) { 
       		bindingSite.setAttribute("datasource", source);
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
