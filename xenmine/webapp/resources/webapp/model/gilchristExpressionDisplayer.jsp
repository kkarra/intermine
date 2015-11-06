<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<style>
  #gilchrist-expression div.data-table { margin-top:20px; }
</style>

<c:choose>
  <c:when test="${empty(gilchristrnaseqResults)}">
    <h3 class="goog gray">RNA-Seq data from Gilchrist et al. <a href="http://www.ncbi.nlm.nih.gov/pubmed/24757007"> 24757007 </a></h3>
    <p>No expression data available for this gene.</p>
  </c:when>
  <c:otherwise>
    <h3 class="goog">RNA-Seq data from Gilchrist et al. <a href="http://www.ncbi.nlm.nih.gov/pubmed/24757007"> 24757007 </a></h3>
    <div class="chart" id="gilchrist-expression-chart"></div>

    <script type="text/javascript">
      function drawChart() {
    	  
    	  console.log(arguments);	
          <%-- setup Google DataTable with the different columns --%>
          var data = new google.visualization.DataTable();
          data.addColumn('string', 'Stage');
          data.addColumn('number', 'Expression');

          <%-- take the Java result and make into a DataTable rows --%>
          <c:forEach var="stage" items="${gilchristrnaseqResults}">
            data.addRow(['${stage.key}', ${stage.value}]);
          </c:forEach>

          <%-- modify the chart properties --%>
          var windowSize = jQuery(window).width();
          var options = { 
        		  
        	chartArea: {
              top: 55,
              height: '33%' 
             },
        	width: 1000,
        	height: 340,
        	
        	hAxis: {
              title: 'Developmental Stage',
              showTextEvery: 1,
              slantedText: true, slantedTextAngle: 90, viewWindow:{max:52}
            },
            vAxis: {
              title: 'FPKM Score'
            },
            colors: ['881A1A'],
            crosshair: {
              color: '#000',
              trigger: 'selection'
            }
          };

          <%-- aim & shoot --%>
          var chart = new google.visualization.LineChart(document.getElementById("gilchrist-expression-chart"));
          chart.draw(data, options);
      }

      google.load("visualization", "1", {"packages":["corechart"], "callback":drawChart});

    </script>

  </c:otherwise>
</c:choose>

</div>