<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<style>
  #xenmine-expression div.data-table { margin-top:20px; }
</style>

<c:choose>
  <c:when test="${empty(rnaseqResults)}">
    <h3 class="goog gray">RNA-Seq data from Li et al.  <a href="http://www.ncbi.nlm.nih.gov/pubmed/22960373"> 22960373 </a></h3>
    <p>No expression data available for this gene.</p>
  </c:when>
  <c:otherwise>
    <h3 class="goog">RNA-Seq data from Tam et al.  <a href="http://www.ncbi.nlm.nih.gov/pubmed/22960373"> 22960373 </a></h3>
    <div class="chart" id="xenmine-expression-chart"></div>

    <script type="text/javascript">
      function drawChart() {
    	  
    	  console.log(arguments);	
          <%-- setup Google DataTable with the different columns --%>
          var data = new google.visualization.DataTable();
          data.addColumn('string', 'Stage');
          data.addColumn('number', 'Expression');

          <%-- take the Java result and make into a DataTable rows --%>
          <c:forEach var="stage" items="${rnaseqResults}">
            data.addRow(['${stage.key}', ${stage.value}]);
          </c:forEach>

          <%-- modify the chart properties --%>
          var windowSize = jQuery(window).width();
          var options = { 
        		  
        	chartArea: {
              top: 55,
              height: '35%' 
             },
        	width: 1000,
        	height: 340,
        	
        	hAxis: {
              title: 'Developmental Stage',
              showTextEvery:1,
              slantedText: true, slantedTextAngle: 90, viewWindow:{max:50}
            },
            vAxis: {
              title: 'FPKM Score'
            },
            colors: ['#097138'],
            crosshair: {
              color: '#000',
              trigger: 'selection'
            }
          };

          <%-- aim & shoot --%>
          var chart = new google.visualization.LineChart(document.getElementById("xenmine-expression-chart"));
          chart.draw(data, options);
      }

      google.load("visualization", "1", {"packages":["corechart"], "callback":drawChart});

    </script>

  </c:otherwise>
</c:choose>

</div>