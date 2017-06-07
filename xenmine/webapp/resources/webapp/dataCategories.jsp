<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="im"%>

<!-- dataCategories -->

<html:xhtml/>

<div class="body">
<im:boxarea title="Data" stylename="plainbox"><p><fmt:message key="dataCategories.intro"/></p></im:boxarea>


<table cellpadding="0" cellspacing="0" border="0" class="dbsources">
  <tr>
    <th>Data Category</th>
    <th>Data</th>
    <th>Source</th>
	</tr>

 <tr><td class="leftcol">
        <p> <h2>Genome</h2></p></td>
    <td>X.tropicalis v8.3 X.laevis 7.1</td>
    <td><a href="http://www.xenbase.org" target="_new">Xenbase</a> </td>
  </tr>
  
  
  <tr>
  <td class="leftcol">
  <h2><p>Genes</p></h2></td>
   <td>Annotation for <i>Xenopus tropicalis and laevis</i>.  Data loaded includes:
      <ul>     
      <li>GeneExpression_*.txt                          
      <li>GenePageAnatomyOntologyMapping.txt              
      <li>GenePageEnsemblModelMapping.txt                
      <li>GenePageGeneralInfo_ManuallyCurated.txt          
      <li>GenePageGoTerms.txt                             
      <li>GenePageInteractants.txt                        
      <li>GenePageToJgiLaevisScaffoldMapping_7.1.txt     
      <li>GenePageToJgiTropicalisScaffoldMapping_8.0.txt  
      <li>GenePage_*_EntrezGeneUnigeneMapping.txt       
      <li>JgiToXenbaseGenePage_8.0.txt                   
      <li>LiteratureMatchedGenesByPaper.txt             
      <li>NcbiMrnaXenbaseGene_*.txt                     
      <li>NcbiProteinXenbaseGene_*.txt  
      <li>XenbaseGeneHumanOrthologMapping.txt  
      <li>XenbaseGeneMouseOrthologMapping.txt 
      <li>XenbaseGeneNonEntrezOrthologMapping.txt    
      <li>XenbaseGeneZebrafishOrthologMapping.txt 
      <li>XenbaseGenepageToGeneIdMapping.txt 
      <li> xenopus_anatomy.obo  
      </ul>
      </td>
   <td> <a href="http://www.xenbase.org" target="_new">Xenbase</a></td>
  </tr>
 
 <tr><td class="leftcol">
  <p> <h2>Literature</h2></p></td>
  <td>  LiteratureMatchedGenesByPaper.txt  </td>
   <td><a href="http://www.xenbase.org" target="_new">Xenbase</a> </td>
 </tr>

 <tr><td class="leftcol">
 <p> <h2>Function</h2></p></td>
 <td>GO annotation from QuickGO and XenBase:
	    <ul> 
	    <li>GenePageGoTerms.txt
	    <li>gene_association_trops_laevis.goa
	    <li>gene_ontology_edit.obo
	    </ul>
  </td>
  <td><a href="http://www.xenbase.org" target="_new">Xenbase</a> </td>
</tr>


<tr><td class="leftcol">
<p> <h2>Orthologs</h2></p></td>
<td> Includes:
    <ul>     
  <li>XenbaseGeneZebrafishOrthologMapping.txt
  <li>XenbaseGeneMouseOrthologMapping.txt
  <li>XenbaseGeneHumanOrthologMapping.txt
  <li> xenopus_anatomy.obo  
   </ul>
  </td>
 <td><a href="http://www.xenbase.org" target="_new">Xenbase</a> </td>
</tr>


 <tr><td class="leftcol">
 	   <p> <h2>ChIP-Seq</h2></p></td>
	    <td>
	    <ul>
	    <li>Tropicalis_Stage_12_H3K27me3   
	    <li>Tropicalis_Stage_12_H3K4me3 
	    <li>Tropicalis_Stage_12_RNAPII   
	    <li>Tropicalis_Stage_16_H3K27me3  
	    <li>Tropicalis_Stage_16_H3K4me3  
	    <li>Tropicalis_Stage_16_RNAPII  
	    <li>Tropicalis_Stage_30_H3K27me3 
	    <li>Tropicalis_Stage_30_H3K4me3 
	    <li>Tropicalis_Stage_30_RNAPII
	    <li>Tropicalis_Stage_9_EZH2 
	    <li>Tropicalis_Stage_9_H3K27me3 
	    <li>Tropicalis_Stage_9_H3K4me1 
	    <li>Tropicalis_Stage_9_H3K4me3 
	    <li>Tropicalis_Stage_9_Jarid2 
	    <li>Tropicalis_Stage_9_RNAPII 
	    </ul>
	    </td>
 	    <td> <a href="http://veenstra.science.ru.nl/" target="_new"> Veenstra Lab</a></td>
 	  </tr>

 	  
 	 <tr><td class="leftcol">
	   <p> <h2>ChIP-Seq</h2></p></td>
	    <td>
	    <ul>
	    <li>Tropicalis_Stage10.5_FoxH1
	    <li>Tropicalis_Stage10.5_Smad2_3
	    </ul>
	    </td>
	    <td> <a href="http://devcell.bio.uci.edu/faculty/ken-cho/" target="_new"> Ken Cho Lab</a></td>
	  </tr>
  
	 	 <tr><td class="leftcol">
		   <p> <h2>ChIP-Seq</h2></p></td>
		    <td>
		    <ul>
		    <li>Tropicalis_stage10.5_H3K27Ac 
		    <li>Tropicalis_stage10.5_H3K27Me3 
		    <li>Tropicalis_stage10.5_H3K4Me1 
		    <li>Tropicalis_stage10.5_H3K4Me3 
		    <li>Tropicalis_stage10.5_Smad2-3 
		    <li>Tropicalis_stage8_H3K27Ac 
		    <li>Tropicalis_stage8_H3K27Me3 
		    <li>Tropicalis_stage8_H3K4Me1 
		    <li>Tropicalis_stage8_H3K4Me3 
		    <li>Tropicalis_stage9_H3K27Ac 
		    <li>Tropicalis_stage9_H3K27Me3 
		    <li>Tropicalis_stage9_H3K4Me1
		    <li>Tropicalis_stage9_H3K4Me3
		    </ul>
		    </td>
		    <td> <a href="https://baker-lab.stanford.edu/" target="_new"> Baker Lab</a></td>
		  </tr>
	    
 	  
    <tr><td class="leftcol">
 	   <p> <h2>RNA-Seq</h2></p></td>
	    <td>
	    <ul>
	    <li>Tropicalis_Jim_Smith_Brachyury_KD_rna_seq
	    <li>Tropicalis_Ken_Cho_FoxH1_rna_seq
	    <li>Tropicalis_Mike_Gilchrist_MBT_rna_seq
	    <li>Tropicalis_Xiong_Tissues_rna_seq
	    <li>Tropicalis_Li_dev_stages_merge
	    <li>Tropicalis_Veenstra_lncRNA_rna_seq
	    
	    </ul>
	    </td>
 	    <td> <a href="http://www.stanford.edu/group/bakerlab/Welcome.html" target="_new"> Multiple Sources</a></td>
 	  </tr>
 	  	  
 	  
</table>

<im:boxarea title="Analysis" stylename="plainbox"><p><fmt:message key="dataCategories.intro2"/></p></im:boxarea>

<table cellpadding="0" cellspacing="0" border="0" class="dbsources">
<tr>
  <th>Data Category</th>
  <th> Analysis</th>
  <th>Script</th>
	</tr>

<tr><td class="leftcol">
      <p> <h2>ChIP-Seq</h2></p></td>
  <td>Reads were mapped to the X. tropicalis genome and annotations v8.0 with bowtie2 using --very-sensitive-local parameters 
  (Langmead and Salzberg, 2012). SAMtools was used to sort, index and remove PCR duplicates (Li et al., 2009).  </td>
  <td><a href="http://www.xenmine.org/XenMine_peakcall.html" target="_new">XenMine</a> </td>
</tr>

<tr><td class="leftcol">
<p> <h2>ChIP-Seq</h2></p></td>
<td>MACS v2.0.8 was 
used to call peaks with a qvalue of 0.01 (Zhang et al., 2008) (https://github.com/taoliu/MACS/).   </td>
<td><a href="http://www.xenmine.org/XenMine_ChIPseq.html" target="_new">XenMine</a> </td>
</tr>

<tr><td class="leftcol">
<p> <h2>RNA-Seq</h2></p></td>
<td>Reads were mapped to X. tropicalis genome and annotations v8.0 or X. laevis genome and annotations v8.0 using
STAR with common parameters specified (Dobin et al., 2013). Mapped reads were sorted using SAMtools (Li et al., 2009)
and transcript expression was summarized using eXpress (Roberts and Pachter, 2013). </td>
<td><a href="http://www.xenmine.org/XenMine_RNAseq.html" target="_new">XenMine</a> </td>
</tr>


</table>

</div>
<!-- /dataCategories -->
