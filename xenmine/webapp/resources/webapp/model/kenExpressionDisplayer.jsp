<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<style>
  #ken-expression div.data-table { margin-top:20px; }
</style>

<c:choose>
  <c:when test="${empty(kenrnaseqResults)}">
    <h3 class="goog gray">RNA-Seq data from Cho et al.  <a href="http://www.ncbi.nlm.nih.gov/pubmed/25359723"> 25359723 </a> </h3>
    <p>No expression data available for this gene.</p>
  </c:when>
  <c:otherwise>
    <h3 class="goog">RNA-Seq data from Cho et al.  <a href="http://www.ncbi.nlm.nih.gov/pubmed/25359723"> 25359723 </a> </h3>
    <div class="chart" id="ken-expression-chart"></div>

    <script type="text/javascript">
      function drawChart() {
    	  
    	  console.log(arguments);	
          <%-- setup Google DataTable with the different columns --%>
          var data = new google.visualization.DataTable();
          data.addColumn('string', 'Stage');
          data.addColumn('number', 'Expression');

          <%-- take the Java result and make into a DataTable rows --%>
          <c:forEach var="stage" items="${kenrnaseqResults}">
            data.addRow(['${stage.key}', ${stage.value}]);
          </c:forEach>

          <%-- modify the chart properties --%>
          var windowSize = jQuery(window).width();
          var options = { 
        		  
        	width: 700,
        	height: 240,
        	
        	hAxis: {
              title: 'Developmental Stage',
              showTextEvery:1
            },
            vAxis: {
              title: 'FPKM Score'
            },
            colors: ['#e7711c'],
            crosshair: {
              color: '#000',
              trigger: 'selection'
            }
          };

          <%-- aim & shoot --%>
          var chart = new google.visualization.ColumnChart(document.getElementById("ken-expression-chart"));
          chart.draw(data, options);
      }

      google.load("visualization", "1", {"packages":["corechart"], "callback":drawChart});

    </script>

  </c:otherwise>
</c:choose>

</div>