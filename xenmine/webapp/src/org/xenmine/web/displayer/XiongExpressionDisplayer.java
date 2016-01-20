package org.intermine.bio.web.displayer;

/*
 * Copyright (C) 2002-2015 FlyMine
 *
 * This code may be freely distributed and modified under the
 * terms of the GNU Lesser General Public Licence.  This should
 * be distributed with the code.  See the LICENSE file for more
 * information or http://www.gnu.org/copyleft/lesser.html.
 *
 */

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.intermine.api.InterMineAPI;
import org.intermine.api.profile.Profile;
import org.intermine.api.query.PathQueryExecutor;
import org.intermine.api.results.ExportResultsIterator;
import org.intermine.api.results.ResultElement;
import org.intermine.metadata.FieldDescriptor;
import org.intermine.metadata.Model;
import org.intermine.model.InterMineObject;
import org.intermine.model.bio.Gene;
import org.intermine.pathquery.Constraints;
import org.intermine.pathquery.OrderDirection;
import org.intermine.pathquery.PathQuery;
import org.intermine.web.displayer.ReportDisplayer;
import org.intermine.web.logic.config.ReportDisplayerConfig;
import org.intermine.web.logic.pathqueryresult.PathQueryResultHelper;
import org.intermine.web.logic.results.ReportObject;
import org.intermine.web.logic.session.SessionMethods;
import org.apache.log4j.Logger;


/**
 * FlyBase modENCODE expression data
 *
 * @author Julie
 */
public class XiongExpressionDisplayer extends ReportDisplayer
{

	   private static final org.apache.log4j.Logger LOG = Logger.getLogger(ExpressionDisplayer.class);
	   
    /**
     * Constructor
     * @param config .
     * @param im .
     */
    public XiongExpressionDisplayer(ReportDisplayerConfig config, InterMineAPI im) {
        super(config, im);
    }

    @Override
    public void display(HttpServletRequest request, ReportObject reportObject){

        // get the gene/protein in question from the request
        InterMineObject object = reportObject.getObject();

        // API connection
        HttpSession session = request.getSession();
        im = SessionMethods.getInterMineAPI(session);
        Model model = im.getModel();
        PathQuery query = new PathQuery(model);

        // cast me Gene
        Gene gene = (Gene) object;
        Object id = gene.getId();
        if (id != null) {
        	
        	try{
            // fetch the expression
            String geneId = String.valueOf(id);
                query = getQuery(geneId, query);
                // execute the query
                Profile profile = SessionMethods.getProfile(session);
                PathQueryExecutor executor = im.getPathQueryExecutor(profile);
                ExportResultsIterator values = executor.execute(query);
                
                Map results = processResults(values);
                request.setAttribute("xiongrnaseqResults", results);
                
        } catch (Exception e) {
            request.setAttribute("xiongrnaseqResults", new LinkedHashMap<String, String>());
        }

        }
    }

    private static Map<String, String> processResults(ExportResultsIterator it) {
        Map<String, String> results = new LinkedHashMap<String, String>();
        while (it.hasNext()) {
            List<ResultElement> row = it.next();           
            String stage =  (String) row.get(0).getField();
            String expressionLevel =  (String) row.get(1).getField();
            String expressionScore = ((Float) row.get(2).getField()).toString();
            results.put(stage, expressionScore ); 
        }
        return results;
    }

    private static PathQuery getQuery(String geneId, PathQuery query) {
        query.addViews(
                "Gene.expressionScores.stage",
                "Gene.expressionScores.expressionLevel",
                "Gene.expressionScores.FPKM",
                "Gene.primaryIdentifier");
        query.addConstraint(Constraints.eq("Gene.id", geneId));
        query.addConstraint(Constraints.eq("Gene.expressionScores.experiments.dataset.name", "Xiong"));
        query.addOrderBy("Gene.expressionScores.order", OrderDirection.ASC);
        return query;
    }

}
