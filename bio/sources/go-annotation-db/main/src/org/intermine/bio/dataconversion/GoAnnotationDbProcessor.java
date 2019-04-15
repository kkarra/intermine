package org.intermine.bio.dataconversion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;


public class GoAnnotationDbProcessor {
	
	private static final Logger LOG = Logger.getLogger(GoAnnotationDbProcessor.class);  
	
    /**
     * Return the results of running a query for genes
     * @param connection the connection
     * @return the results
     * @throws SQLException if there is a database problem
     *
     */
	protected ResultSet getGOAnnotations(Connection connection) throws SQLException {		
		
		String query = "select sgdid, gene_name, ga.go_qualifier, goid, pmid, ra.display_name as sgdrefid, ea.display_name as evidence_code, gse.dbxref_id as withText, "
				+ "t.taxid, s.display_name as source, ga.annotation_type, ge.dbxref_id as annotext, r.display_name as annottextprepend " 
				+ "from  nex.dbentity db " 
				+"inner join nex.locusdbentity ldb on db.dbentity_id = ldb.dbentity_id " 
				+ "inner join nex.goannotation ga on db.dbentity_id = ga.dbentity_id " 
				+ "inner join nex.go g on g.go_id = ga.go_id "
				+ "inner join nex.taxonomy t on t.taxonomy_id = ga.taxonomy_id "
				+ "inner join nex.source s on ga.source_id = s.source_id "
				+ "inner join nex.referencedbentity rdb on ga.reference_id = rdb.dbentity_id "
				+ "left join nex.reference_alias ra on rdb.dbentity_id = ra.reference_id "
				+ "inner join nex.eco e on e.eco_id = ga.eco_id "
				+ "inner join nex.eco_alias ea on ea.eco_id = e.eco_id "
				+ "left join nex.goextension ge on ga.annotation_id = ge.annotation_id "
				+ "left join nex.ro r on ge.ro_id = r.ro_id "
				+ "left join nex.gosupportingevidence gse on ga.annotation_id = gse.annotation_id "
				+ " where length(ea.display_name) <= 3 "
				//+ "where ea.display_name in ('HDA', 'HGI', 'HMP', 'IBA', 'IC', 'IDA', 'IEA', 'IEP', 'IGI', 'IKR' ,'IMP', 'IPI', 'ISA', 'ISM', 'ISO', 'ISS', 'NAS', 'ND', 'TAS') " 
				+ "group by  sgdid, gene_name, ga.go_qualifier, goid, pmid, ra.display_name, ea.display_name,  gse.dbxref_id, taxid, s.display_name, ga.annotation_type, ge.dbxref_id, r.display_name "
				+ " order by sgdid, goid, gse.dbxref_id, pmid"; 

		LOG.info("executing: " + query);
		Statement stmt = connection.createStatement();
		ResultSet res = stmt.executeQuery(query);
		return res;
	}


}
