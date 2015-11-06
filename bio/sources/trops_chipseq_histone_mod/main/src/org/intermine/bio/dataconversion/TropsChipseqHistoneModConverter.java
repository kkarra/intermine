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
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

import java.io.BufferedReader;
import org.intermine.dataconversion.ItemWriter;
import org.intermine.metadata.Model;
import org.intermine.xml.full.Item;
import org.apache.commons.lang.StringUtils;
import org.intermine.util.FormattedTextParser;
import org.intermine.objectstore.ObjectStoreException; 
import org.apache.log4j.Logger;
import org.apache.tools.ant.BuildException;


/**
 * 
 * @author
 */
public class TropsChipseqHistoneModConverter extends BioFileConverter
{
	//
	private static final Logger LOG = Logger.getLogger(TropsChipseqHistoneModConverter.class);
	private static final String DATASET_TITLE = "Tropicalis Histone Modification ChIP-Seq experiments.";
	private static final String DATA_SOURCE_NAME = "Baker Lab, GEO etc";
	private Map<String, String> chromosomes = new HashMap();
	private Map<String, Item> organisms = new HashMap();
	private Map<String, Item> devstages = new HashMap();
	private Map<String, Item> abtargets = new HashMap();
	private Map<String, Item> publications = new HashMap();
	private static final String TAXON_ID = "8364";
	private Item organism;
	private Map<String, Item> datasets = new HashMap();

	/**
	 * Constructor
	 * @param writer the ItemWriter used to handle the resultant items
	 * @param model the Model
	 */
	public TropsChipseqHistoneModConverter(ItemWriter writer, Model model) throws ObjectStoreException{
		super(writer, model, DATA_SOURCE_NAME, DATASET_TITLE);
		organism = createItem("Organism");
		organism.setAttribute("taxonId", TAXON_ID);
		organism.setAttribute("genus", "Xenopus");
		organism.setAttribute("species", "tropicalis");
		organism.setAttribute("name", "Xenopus tropicalis");
		organism.setAttribute("shortName", "X. tropicalis");
		store(organism);
		organisms.put("X. tropicalis", organism);
	}


	/**
	 * 
	 * 3. NAME_peaks.encodePeak is BED6+4 format file which contains the
 	peak locations together with peak summit, pvalue and qvalue. You can
 	load it to UCSC genome browser. Definition of some specific columns
 	are: 

	1st: chromosome name,
	2nd: start,
	3rd: end,
	4th: peak name,
	5th: -log10pvalue*10,
	6th: strand
	7th: fold-change, 
	8th: -log10pvalue, 
	9th: -log10qvalue, 
	10th: relative summit position to peak start.
	 * {@inheritDoc}
	 */

	@Override
	public void process(Reader reader) throws Exception {
		processScoreFile(reader);
	}


	private void processScoreFile(Reader reader) throws Exception, ObjectStoreException {

		BufferedReader br = new BufferedReader(reader);
		String lines = null;
		String metadata[] = new String[19];  //needs adjustment or a better strategy
		int i = 0;
		boolean first = true;
		boolean firstLine = true;
		Item exp = null;
		String stageMarker="";
		
		while ((lines = br.readLine()) != null) {
			
			if(firstLine && lines.startsWith("_")){			
				stageMarker = lines;
				firstLine = false;
				continue;
			}

			if (lines.startsWith("!")) { 
				metadata[i] = lines;
				i++;
				continue;
			}

			if(first) {
				exp = processMetaData(metadata);
				first = false;
			}

			String[] line = lines.split("\t", -1); // keep trailing empty Strings
			if (line.length < 10 && StringUtils.isNotEmpty(line.toString())) {
				throw new RuntimeException("Invalid line, should be 10 columns but is '"
						+ line.length + "' instead");
			}

			String chrNumber = line[0];
			String chrStart = line[1];
			String chrEnd = line[2];
			String peakName = line[3];
			String peakScore = line[4];
			String foldChange = line[6]; 
			String pvalue = line[7];
			String qvalue = line[8]; 
			String relativeSummitPosition = line[9];

			Item bindingSite = createItem("TFBindingSite");  
			//String name = peakName + "_binding_site";
			//String name = chrNumber+"_"+chrStart+"_"+chrEnd+stageMarker;
			String shortName = chrNumber+"_"+chrStart+"_"+chrEnd;
			String trim_stageMarker = stageMarker.substring(1);
			System.out.println(" stage marker .. " + trim_stageMarker);
			//System.exit(1);

			//bindingSite.setAttribute("name", name);
			bindingSite.setAttribute("primaryIdentifier", shortName);
			bindingSite.setAttribute("secondaryIdentifier", trim_stageMarker);

			if (StringUtils.isNotEmpty(peakScore)) {
				try {
					Float.valueOf(peakScore);
					bindingSite.setAttribute("score", peakScore);
				} catch (NumberFormatException e) {
					LOG.warn("bad score: " + peakScore, e);
				}
			}
			if (StringUtils.isNotEmpty(foldChange)) {
				try {
					Float.valueOf(foldChange);
					bindingSite.setAttribute("foldChange", foldChange);
				} catch (NumberFormatException e) {
					LOG.warn("bad score: " + foldChange, e);
				}
			}
			if (StringUtils.isNotEmpty(pvalue)) {
				try {
					Float.valueOf(pvalue);
					bindingSite.setAttribute("pvalue", pvalue);
				} catch (NumberFormatException e) {
					LOG.warn("bad score: " + pvalue, e);
				}
			}
			if (StringUtils.isNotEmpty(qvalue)) {
				try {
					Float.valueOf(qvalue);
					bindingSite.setAttribute("qvalue", qvalue);
				} catch (NumberFormatException e) {
					LOG.warn("bad score: " + qvalue, e);
				}
			}
			if (StringUtils.isNotEmpty(relativeSummitPosition)) {
				bindingSite.setAttribute("relativeSummitPosition", relativeSummitPosition);
			}

			String chromosomeRefId = getChromosome(chrNumber);
			String locationRefId = getLocation(bindingSite,  chromosomeRefId, chrStart,  chrEnd, "");

			bindingSite.setReference("chromosome", chromosomeRefId);
			bindingSite.setReference("chromosomeLocation", locationRefId);

			bindingSite.addToCollection("experiments", exp.getIdentifier());
	


			try {
				store(bindingSite);
			} catch (ObjectStoreException e) {
				throw new ObjectStoreException(e);
			}	

		}


	}

	/**
	 * 
	 * @param reader
	 *            Experiment Name ChIP-Seq Identification of X.tropicalis TF Binding Sites 
	 *            Experiment Category: TF binding sites 
	 *            Experiment Type: ChIP-seq 
	 *            Experiment Title Stage8-Smad2-Trops-ChIP-Seq
	 *            Experiment Description Something more descriptive and detailed
	 *            Experiment Date 05/28/2014 
	 *            Experiment PMID 1234 
	 *            Organism: X. (Silurana) tropicalis 
	 *            Experimental Factor Name: Any Name -- kw  pair 
	 *            Experimental Factor Type: Any Value 
	 *            Experiment Property Name: Any Name --kw pair 
	 *            Experiment Property Type: Any Value
	 *            Developmental Stage: Stage 8 
	 *            Developmental Stage Sex: Female
	 *            Tissue Organism Part: Whole Frog 
	 *            Antibody TargetName: XB-GENE-482930
	 *             Antibody HostOrganism: Unknown 
	 *             Antibody Antigen: Unknown
	 */

	private Item processMetaData(String[] md) throws ObjectStoreException {

		Item experiment = null;
		String experimentName = "";
		String experimentCategory = "";
		String experimentType = "";
		String experimentTitle = "";
		String experimentDescription = "";
		String experimentDate = "";
		String organism = "";
		String stage = "";
		String antibodyTarget = "";
		String pmid = "";
		String dataset = "";


		for(int i=0; i< md.length; i++) {
			
			String[] line = md[i].split("\\:"); 
			String name = line[0].trim();
			String value = line[1].trim();

			if(name.equalsIgnoreCase("!Experiment Name")){
				experimentName = value;
			}else if (name.equalsIgnoreCase("!Experiment Category")){
				experimentCategory = value;
			}else if (name.equalsIgnoreCase("!Experiment Type")){
				experimentType = value;
			}else if (name.equalsIgnoreCase("!Experiment Title")){
				experimentTitle = value;
			}else if (name.equalsIgnoreCase("!Experiment Description")){
				experimentDescription = value;
			}else if (name.equalsIgnoreCase("!Experiment Date")){
				experimentDate = value;
			}else if (name.equalsIgnoreCase("!Experiment PMID")){
				pmid = value;
			}else if (name.equalsIgnoreCase("!Organism")){
				organism = value;
			}else if (name.equalsIgnoreCase("!Developmental Stage")){
				stage = value;
			}else if (name.equalsIgnoreCase("!Antibody TargetName")){
				antibodyTarget = value;
			}else if (name.equalsIgnoreCase("!Experiment DataSet")){
				dataset = value;
			}


		}

		experiment = getExperiment(experimentName, experimentCategory,  experimentType, experimentTitle, experimentDescription, 
				experimentDate, pmid, organism, stage, antibodyTarget, dataset);

		return experiment;

	}

	/**
	 * 
	 * @param experimentName
	 * @param experimentCategory
	 * @param experimentType
	 * @param experimentTitle
	 * @param experimentDescription
	 * @param experimentDate
	 * @param organism
	 * @param stage
	 * @param antibodyTarget
	 * @return
	 * @throws ObjectStoreException
	 */
	private Item getExperiment(String experimentName, String experimentCategory,  String experimentType, String experimentTitle, String experimentDescription, 
			String experimentDate, String pmid, String organism, String stage, String antibodyTarget, String dataset) throws ObjectStoreException {

		Item org = organisms.get(organism);
		if(org == null){
			org = createItem("Organism");
			org.setAttribute("shortname", organism);
			try {
				store(org);
			} catch (ObjectStoreException e) {
				e.printStackTrace();
				throw new ObjectStoreException(e);
			}
		}

		Item devstage = devstages.get(stage);
		if(devstage == null){
			devstage = createItem("DevelopmentalStage");
			devstage.setAttribute("name", stage);
			try {
				store(devstage);
			} catch (ObjectStoreException e) {
				e.printStackTrace();
				throw new ObjectStoreException(e);
			}
			devstages.put(stage, devstage);
		}

		Item antibody = abtargets.get(antibodyTarget);
		if(antibody == null){
		    antibody = createItem("Antibody");
			antibody.setAttribute("name", antibodyTarget);
			try {
				store(antibody);
			} catch (ObjectStoreException e) {
				e.printStackTrace();
				throw new ObjectStoreException(e);
			}
			abtargets.put(antibodyTarget, antibody);
		}
		
		Item ds = datasets.get(dataset);
		if(ds == null){
			ds = createItem("DataSet");
			ds.setAttribute("name", dataset);
			try {
				store(ds);
			} catch (ObjectStoreException e) {
				e.printStackTrace();
				throw new ObjectStoreException(e);
			}
			datasets.put(dataset, ds);
		}

		Item exp = createItem("Experiment");
		exp.setAttribute("name", experimentName);
		exp.setAttribute("category", experimentCategory);
		exp.setAttribute("experimentType", experimentType);
		if (!StringUtils.isEmpty(experimentTitle))  exp.setAttribute("title", experimentTitle);
		if (!StringUtils.isEmpty(experimentDescription)) exp.setAttribute("description", experimentDescription);
		if (!StringUtils.isEmpty(experimentDate))exp.setAttribute("experimentDate", experimentDate);
		exp.setReference("organism", org.getIdentifier());
		
		exp.addToCollection("developmentalStages", devstage.getIdentifier());
		exp.addToCollection("antibodies", antibody.getIdentifier());
		exp.setReference("dataset", ds.getIdentifier());


		if(StringUtils.isNotEmpty(pmid)){
		Item pub = publications.get(pmid);
		if(pub == null){
			pub = createItem("Publication");
			pub.setAttribute("pubMedId", pmid);
			try {
				store(pub);
			} catch (ObjectStoreException e) {
				e.printStackTrace();
				throw new ObjectStoreException(e);
			}
			publications.put(pmid, pub);
		}
		exp.setReference("publication", pub.getIdentifier());
		}
		
		
		try {
			store(exp);
		} catch (ObjectStoreException e) {
			e.printStackTrace();
			throw new ObjectStoreException(e);
		}

		return exp;

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


		int intstart =  Integer.valueOf(startCoord) + 1;
		int intend = Integer.valueOf(stopCoord) + 1;

		String start = String.valueOf(intstart);
		String end = String.valueOf(intend);

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

		// if the coordinates are on the crick strand, they need to be reversed
		// or they
		// result in a negative number
		if (a.compareTo(b) > 0) {
			a = new Integer(end);
			b = new Integer(start);
		}

		Integer length = new Integer(b.intValue() - a.intValue());
		return length.toString();
	}

	/**
	 * 
	 * @param identifier
	 * @return
	 * @throws ObjectStoreException
	 */

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


}
