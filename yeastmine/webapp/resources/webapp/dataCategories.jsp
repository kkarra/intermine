<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="im"%>


<!-- dataCategories -->

<c:set var="note1" value="Only genes that have been mapped to the genome have been loaded"/>
<c:set var="note2" value="Also orthologues from these organisms to <i>C. familiaris</i>, <i>D. discoideum</i>, <i>D. rerio</i>, <i>G. gallus</i>, <i>H. sapiens</i>, <i>M. musculus</i>, <i>P. troglodytes</i>, <i>R. norvegicus</i>, <i>S. pombe</i>." />
<c:set var="note3" value="These data have been re-mapped to genome sequence release 5.0 as of FlyMine release 7.0."/>

<html:xhtml/>

<div class="body">
<im:boxarea title="Data" stylename="plainbox"><p><fmt:message key="dataCategories.intro"/></p></im:boxarea>


<table cellpadding="0" cellspacing="0" border="0" class="dbsources">
  <tr>
    <th>Data Category</th>
    <th>Organism</th>
    <th>Data</th>
    <th>Source</th>
    <th>PubMed</th>
  </tr>
  <tr>
    <td class="leftcol"><html:link action="/aspect?name=Genomics"> <p><img src="model/images/genomics.gif" /></p><p> Genomics </p></html:link></td>
    <td><i>S. cerevisiae</i></td>
    <td>Genome annotation for <i>Saccharomyces cerevisiae</i>.  Data loaded includes:
      <ul>
		<li>PrimaryIdentifier
        <li>SecondaryIdentifier
		<li>Symbol
        <li>Name
        <li>Length
        <li>Description
        <li>Feature type
        <li>Locations
        <li>Sequences
      </ul>
    </td>
    <td><a href="http://www.yeastgenome.org" target="_new">SGD</a></td>
    <td>Saccharomyces Genome Database - <a href="http://www.ncbi.nlm.nih.gov/pubmed/9169866" target="_new">PubMed: 9169866</a></td>
    <td> &nbsp;</td>
  </tr>

    <tr><td class="leftcol">
	        <html:link action="/aspect?name=Proteins">	      
	        <p> <img src="model/images/proteins.png" /></p>
	        <p> Proteins </p></html:link></td>
	    <td> <i>S. cerevisiae</i> </td>
	    <td> Protein annotation</td>
 	    <td> <a href="http://www.yeastgenome.org" target="_new">SGD</a> </td>
 	    <td> SGD - <a href="http://www.ncbi.nlm.nih.gov/pubmed/17142230" target="_new">PubMed: 17142230</a></td>
 	    <td> &nbsp;</td>
 	  </tr>


  <tr><td class="leftcol">
        <html:link action="/aspect?name=Gene%20Ontology">
         <p> <img src="model/images/geneOntology.png" /></p>
        <p> Gene Ontology </p></html:link></td>
    <td> <i>S. cerevisiae</i> </td>
    <td> GO annotations </td>
    <td> <a href="http://www.geneontology.org" target="_new">GO at SGD</a></td>
    <td> Gene Ontology Consortium - <a href="http://www.ncbi.nlm.nih.gov/pubmed/10802651" target="_new">PubMed:10802651</a></td>
    <td> &nbsp;</td>
  </tr>
  
    <tr> <td class="leftcol">
        <html:link action="/aspect?name=Comparative%20Genomics">
          <p>  <img src="model/images/comparativeGenomics.png" /></p>
          <p> Comparative Genomics </p></html:link></td>
    <td>
       <p><i>S. cerevisiae</i></p>
       <p><i>D. melanogaster</i></p>
       <p><i>D. rerio</i></p>
       <p><i>C. elegans</i></p>
       <p><i>M. musculus</i></p>
       <p><i>R. norvegicus</i></p>
       <p><i>H. sapiens</i></p>
       <p><i>A. gambiae</i></p>
    </td>
     <td>Orthologue and paralogue relationships between these 8 organisms</td>
    <td><a href="http://www.treefam.org/" target="_new">Treefam</a> - release 7.0</td>
    <td>Ruan et al - <a href="http://www.ncbi.nlm.nih.gov/pubmed/18056084 " target="_new">PubMed: 18056084</a></td>
    <td> &nbsp; </td>
  </tr>
  
  <tr><td class="leftcol">
       <html:link action="/aspect?name=Phenotypes">
        <p> <img src="model/images/phenotypes.png" /></p>
        <p> Phenotypes</p></html:link></td>
    <td><i>S. cerevisiae</i> </td>
    <td>Phenotypes</td>
    <td><a href="http://www.yeastgenome.org" target="_new">SGD</a> </td>
    <td>Saccharomyces Genome Database - <a href="http://www.ncbi.nlm.nih.gov/pubmed/9169866" target="_new">PubMed: 9169866</a></td>
    <td> &nbsp;</td>
  </tr>
  
  
  <tr><td class="leftcol">
        <html:link action="/aspect?name=Interactions">
         <p> <img src="model/images/interaction.gif" /></p>
        <p> Interactions</p></html:link></td>
    <td> <i>S. cerevisiae</i></td>
    <td> Interactions from the BioGRID</td>
    <td> <a href="http://www.thebiogrid.org" target="_new">BioGRID at SGD </a> - Version 2.0.xx </td>
    <td> Stark et al - <a href="http://www.ncbi.nlm.nih.gov/pubmed/16381927" target="_new">PubMed:16381927</a></td>
    <td> &nbsp;</td>
  </tr> 

 <tr><td class="leftcol">
       <html:link action="/aspect?name=Literature">
        <p> <img src="model/images/book.png" /></p>
        <p>Literature</p></html:link></td>
    <td> <i>S. cerevisiae</i> </td>
    <td> Gene versus publications</td>
    <td><a href="http://www.yeastgenome.org" target="_new">SGD</a> - 02 Sep, 2009</td>
    <td>Saccharomyces Genome Database - <a href="http://www.ncbi.nlm.nih.gov/pubmed/9169866" target="_new">PubMed: 9169866</a></td>
    <td> &nbsp;</td>
  </tr>
  

 <tr><td class="leftcol">
 	       <html:link action="/aspect?name=Pathways">
 	        <p> <img src="model/images/pathways.png" /></p>
	        <p> Pathways</p></html:link></td>
 	    <td> <i>S. cerevisiae</i></td>
 	    <td> Curated pathway information and the genes involved in them</td>
 	    <td> <a href="http://pathway.yeastgenome.org/" target="_new">Pathways at SGD</a> - Release 57, 25 January 2011</td>
 	    <td> Kanehisa et al - <a href="http://www.ncbi.nlm.nih.gov/pubmed/16381885" target="_new">PubMed: 16381885</a></td>
	    <td> &nbsp;</td>
	  </tr>
	

 <tr><td class="leftcol">
	        <html:link action="/aspect?name=Gene%20Expression">
 	        <p> <img src="model/images/embryos.jpg" /></p>
 	        	        <p> Gene Expression</p>
 	         </html:link></td>
 	    <td> <i>S. cerevisiae</i> </td>
	    <td> Microarray-based gene expression data of S. cerevisiae</td>
 	    <td> <a href="http://spell.yeastgenome.org/" target="_new"> SPELL </a> SPELL Data Sets</td>
 	    <td> AHibbs MA, et al.  - <a href="http://bioinformatics.oxfordjournals.org/content/23/20/2692.abstract" target="_new">PubMed: 1234567</a></td>
 	    <td> &nbsp;</td>
 	  </tr>
  
</table>


</div>
<!-- /dataCategories -->
