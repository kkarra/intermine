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
import org.apache.log4j.Logger;
import java.util.Map;
import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


/**
 * 
 * @author
 */
public class SgdDomainsConverter extends BioFileConverter
{
    //
	 protected static final Logger LOG = Logger.getLogger(SgdDomainsConverter.class);
	    private static final String DATASET_TITLE = "InterProScan  data set";
	    private static final String DATA_SOURCE_NAME = "InterProScan";
	    private static final String INTERPRO_PREFIX = "InterPro:";
	    private final Map<String, Item> proteinIdMap = new HashMap<String, Item>();
	    private Map<String, Item> proteinDomains = new HashMap<String, Item>();
	    private Map<String, Item> interproDomains = new HashMap<String, Item>();
	    private Map<String, String> goTerms = new HashMap<String, String>();
	    private Pattern pattern = Pattern.compile("\\(GO:(.*?)\\)"); 
    /**
     * Constructor
     * @param writer the ItemWriter used to handle the resultant items
     * @param model the Model
     */
    public SgdDomainsConverter(ItemWriter writer, Model model) {
        super(writer, model, DATA_SOURCE_NAME, DATASET_TITLE);
    }

    /**
     * 
     *
     * {@inheritDoc}
     */
    public void process(Reader reader) throws Exception {
    	
    	processDomainDataFile(reader); 
    	storeProteinDomains();
    	storeProteins();
    }
    
    
	/**
	 * 
	 * @param reader
	 * @throws Exception
	 * @throws ObjectStoreException
	 */
	private void processDomainDataFile(Reader preader) throws Exception, ObjectStoreException {

		/*
		 * Sample line from the domains.tab is shown below-    
       YAL001C	E16ACB7893ED5365	1161	HMMPfam	PF04182.2	
       B-block binding subunit of TFIIIC	8	411	0	T	03-Aug-2005	
       IPR007309	B-block binding subunit of TFIIIC	
       Molecular Function: DNA binding (GO:0003677), Molecular Function: transcription factor activity (GO:0003700),
        Biological Process: regulation of transcription (GO:0045449)


      The columns of this tab_delimited file contain the following
      information:

       1) S. cerevisiae systematic name NF00181542:             is the id of the input sequence.
       2) is the crc64 (checksum) of the proteic sequence (supposed to be
       unique).	       
       3) is the length of the sequence (in AA)
       4) is the anaysis method launched.      
       5) is the database members entry for this match.
       6) is the database member description for the entry.
       7) is the start of the domain match.
       8) is the end of the domain match.
       9) is the evalue of the match (reported by member database anayling method).
      10)is the status of the match (T: true, ?: unknown).
      11)is the date of the run.
      12)is the corresponding InterPro entry (if iprlookup requested by the user)
      13)is the description of the InterPro entry.
      14)is the GO (gene ontology) description for the InterPro entry.

		 */   	 
		System.out.println("Processing Domain  Data  file....");    

		Iterator<?> tsvIter;
		try {
			tsvIter = FormattedTextParser.parseTabDelimitedReader(preader);
		} catch (Exception e) {
			throw new BuildException("cannot parse file: " + getCurrentFile(), e);
		}

		while (tsvIter.hasNext()) {

			String[] line = (String[]) tsvIter.next();

			if (line.length < 14) {
				LOG.error("Couldn't process line. Expected 14 cols, but was " + line.length);
				continue; //lines which have ? and are NULL will not get loaded (mostly Seg)
			}
			
			String protein =  line[0].trim();     
			String method = line[3].trim();
			String domainMatch =  line[4].trim();
			String domainDesc =  line[5].trim(); 
			String start = line[6].trim();
			String end = line[7].trim();
			String evalue = line[8].trim();
			String runDate = line[10].trim();
			String interproEntry =  line[11].trim();
			String interproEntryDesc = line[12].trim();
			String goAnnot = line[13].trim();

			newProduct(protein, method,
					domainMatch, domainDesc, start, end, evalue, runDate, interproEntry, interproEntryDesc, goAnnot);

		}

		preader.close();

	}
	
	
	private void newProduct(String proteinId, String method, String domainMatch, String domainDesc, String start, String end, String evalue, String runDate, String interproEntry, String interproEntryDesc, String goAnnot)
			throws ObjectStoreException, Exception {
		
		
		Item protein = getProteinItem(proteinId);
		
		Item pdomain = getDomain(domainMatch, domainDesc, start, end, evalue, runDate);
		
		Item interpro = getInterproDomain(interproEntry, interproEntryDesc);	
		pdomain.setReference("interpro", interpro.getIdentifier());	
		//processGOInfo(pdomain, goAnnot);

    	Matcher matcher = pattern.matcher(goAnnot);
    	while (matcher.find())
    	{
    		String goId = "GO:"+matcher.group(1);
    		System.out.println("GOID: " + goId);

    		// create go term
    		String goTermRefId = getGoTerm(goId);

    		// create Go annotation
    		Item goAnnotation = createItem("GOAnnotation");
    		goAnnotation.setReference("subject", pdomain);
    		goAnnotation.setReference("ontologyTerm", goTermRefId);

    		pdomain.addToCollection("goAnnotation", goAnnotation);

    		try {
    			store(goAnnotation);
    		} catch (ObjectStoreException e) {
    			throw new Exception(e);
    		}

    	}

		protein.addToCollection("proteinDomains", pdomain.getIdentifier());
		
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
	
	  private Item getDomain(String identifier, String description, String start, String end, String evalue, String runDate) {
	        Item item = proteinDomains.get(identifier);
	        if (item == null) {
	            item = createItem("ProteinDomain");
	            item.setAttribute("name", identifier);
	            item.setAttribute("description", description);
	            item.setAttribute("start", description);
	            item.setAttribute("end", description);
	            item.setAttribute("evalue", description);
	            item.setAttribute("runDate", description);            	            
	            proteinDomains.put(identifier, item);
	        }
	        return item;
	    }
	    private Item getInterproDomain(String identifier, String description)  throws Exception{
	        Item item = interproDomains.get(identifier);
	        if (item == null) {
	            item = createItem("InterProDomain");
	            item.setAttribute("name", identifier);
	            item.setAttribute("description", description);
	            interproDomains.put(identifier, item);
	            try {
                    store(item);
                } catch (ObjectStoreException e) {
                    throw new Exception(e);
                }
	        }
	        return item;
	    }

	    
	    private String getGoTerm(String identifier)
	            throws Exception {
	            String refId = goTerms.get(identifier);
	            if (refId == null) {
	                Item item = createItem("GOTerm");
	                item.setAttribute("identifier", identifier);
	                refId = item.getIdentifier();
	                goTerms.put(identifier, refId);
	                try {
	                    store(item);
	                } catch (ObjectStoreException e) {
	                    throw new Exception(e);
	                }
	            }
	            return refId;
	        }

	    private void processGOInfo(Item pdomain, String goAnnot) throws ObjectStoreException, Exception {

	    	//Molecular Function: DNA binding (GO:0003677), 
	    	//Molecular Function: transcription factor activity (GO:0003700),
	    	//Biological Process: regulation of transcription (GO:0045449)


	    	Matcher matcher = pattern.matcher(goAnnot);
	    	while (matcher.find())
	    	{
	    		String goId = matcher.group(1);
	    		System.out.println("GOID: " + goId);

	    		// create go term
	    		String goTermRefId = getGoTerm(goId);

	    		// create Go annotation
	    		Item goAnnotation = createItem("GOAnnotation");
	    		goAnnotation.setReference("subject", pdomain);
	    		goAnnotation.setReference("ontologyTerm", goTermRefId);

	    		pdomain.addToCollection("goAnnotation", goAnnotation);

	    		try {
	    			store(goAnnotation);
	    		} catch (ObjectStoreException e) {
	    			throw new Exception(e);
	    		}

	    	}



	    }
	
    private void storeProteinDomains() throws Exception{
        for (Item proteinDomain : proteinDomains.values()) {
            try {
                store(proteinDomain);
            } catch (ObjectStoreException e) {
                throw new Exception(e);
            }
        }

    }
    
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
