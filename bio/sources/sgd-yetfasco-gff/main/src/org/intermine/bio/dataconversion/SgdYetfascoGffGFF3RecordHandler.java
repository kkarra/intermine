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

import org.intermine.bio.io.gff3.GFF3Record;
import org.intermine.metadata.Model;
import org.intermine.xml.full.Item;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * A converter/retriever for the SgdYetfascoGff dataset via GFF files.
 */

public class SgdYetfascoGffGFF3RecordHandler extends GFF3RecordHandler
{
    private final Map<String, Item> pubmedIdMap = new HashMap<String, Item>();
    private final Map<String, Item> geneIdMap = new HashMap<String, Item>();
    private static final String TAXON_ID = "4932";
    
    /**
     * Create a new SgdYetfascoGffGFF3RecordHandler for the given data model.
     * @param model the model for which items will be created
     */
    public SgdYetfascoGffGFF3RecordHandler (Model model) {
        super(model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void process(GFF3Record record) {
        // This method is called for every line of GFF3 file(s) being read.  Features and their
        // locations are already created but not stored so you can make changes here.  Attributes
        // are from the last column of the file are available in a map with the attribute name as
        // the key.   For example:
        //
        //     Item feature = getFeature();
        //     String symbol = record.getAttributes().get("symbol");
        //     feature.setAttrinte("symbol", symbol);
        //
        // Any new Items created can be stored by calling addItem().  For example:
        // 
        //     String geneIdentifier = record.getAttributes().get("gene");
        //     gene = createItem("Gene");
        //     gene.setAttribute("primaryIdentifier", geneIdentifier);
        //     addItem(gene);
        //
        // You should make sure that new Items you create are unique, i.e. by storing in a map by
        // some identifier. 
    	  	
        getFeature().setClassName("TFBindingSite");
        Item bindingSite = getFeature();
        String name = record.getId();
        String factorGeneName = record.getAttributes().get("binding_factor").get(0);
        String factorStructuralClass = record.getAttributes().get("factor_structural_class").get(0);
        String factorStructuralFamily = record.getAttributes().get("factor_structural_family").get(0);
        String motifEvidence = record.getAttributes().get("motif_evidence").get(0);
        String pvalue = record.getAttributes().get("pvalue").get(0);
        String qvalue = record.getAttributes().get("qvalue").get(0);
        String sequence = record.getAttributes().get("sequence").get(0);
        

            Item gene = getGene(factorGeneName);
            if (gene != null) {
                bindingSite.setReference("factor", gene.getIdentifier());
                bindingSite.setAttribute("factorStructuralClass",factorStructuralClass);
                bindingSite.setAttribute("factorStructuralFamily", factorStructuralFamily);
                bindingSite.setAttribute("motifEvidence", motifEvidence);
                bindingSite.setAttribute("pvalue", pvalue);
                bindingSite.setAttribute("qvalue", qvalue);
                bindingSite.setAttribute("motifSequence", sequence);
                
            }
 
    }

    private Item getGene(String symbol) {
 
        Item gene = geneIdMap.get(symbol);
        if (gene == null) {
            gene = converter.createItem("Gene");
            geneIdMap.put(symbol, gene);
            gene.setAttribute("symbol", symbol);
            gene.setReference("organism", getOrganism());
            addItem(gene);
        }
        return gene;
 
    }
}
