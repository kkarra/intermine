<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<style>
  #smith-expression div.data-table { margin-top:20px; }
</style>

<c:choose>
  <c:when test="${empty(smithrnaseqResults)}">
    <h3 class="goog gray">RNA-Seq data from Smith et al.  <a href="http://www.ncbi.nlm.nih.gov/pubmed/24055059"> 24055059 </a></h3>
    <p>No expression data available for this gene.</p>
  </c:when>
  <c:otherwise>
    <h3 class="goog">RNA-Seq data from Smith et al.  <a href="http://www.ncbi.nlm.nih.gov/pubmed/24055059"> 24055059 </a></h3>
    <div class="chart" id="smith-expression-chart"></div>

    <script type="text/javascript">
      function drawChart() {
    	  
    	  console.log(arguments);	
          <%-- setup Google DataTable with the different columns --%>
          var data = new google.visualization.DataTable();
          data.addColumn('string', 'Stage');
          data.addColumn('number', 'Expression');

          <%-- take the Java result and make into a DataTable rows --%>
          <c:forEach var="stage" items="${smithrnaseqResults}">
            data.addRow(['${stage.key}', ${stage.value}]);
          </c:forEach>

          <%-- modify the chart properties --%>
          var windowSize = jQuery(window).width();
          var options = { 
            chartArea: {
              top: 55,
              height: '50%' 
             },
        	width: 700,
        	height: 240,
        	
        	hAxis: {
              title: 'Developmental Stage',
              showTextEvery: 1
            },
            vAxis: {
              title: 'FPKM Score'
            },
            colors: ['#76A7FA'],
            crosshair: {
              color: '#000',
              trigger: 'selection'
            }
          };

          <%-- aim & shoot --%>
          var chart = new google.visualization.ColumnChart(document.getElementById("smith-expression-chart"));
          chart.draw(data, options);
      }

      google.load("visualization", "1", {"packages":["corechart"], "callback":drawChart});

    </script>

  </c:otherwise>
</c:choose>

</div>