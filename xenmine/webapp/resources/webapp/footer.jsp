<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- footer.jsp -->
<br/>
<br/>
<br/>

<div class="body" align="center" style="clear:both">
    <!-- contact -->
    <c:if test="${pageName != 'contact'}">
        <div id="contactFormDivButton">
            <im:vspacer height="11" />
            <div class="contactButton">
                <a href="#" onclick="showContactForm();return false">
                    <b><fmt:message key="feedback.title"/></b>
                </a>
            </div>
        </div>
        <div id="contactFormDiv" style="display:none;">
            <im:vspacer height="11" />
            <tiles:get name="contactForm" />
        </div>
    </c:if>
    <br/>

    <!-- funding -->
    <div id="funding-footer">
    
    <ul class="footer-links">
    <!-- contact us form link -->
    <c:set value="${WEB_PROPERTIES['header.links']}" var="headerLinks"/>
    <!-- web properties -->
    <c:forEach var="entry" items="${headerLinks}" varStatus="status">
        <c:set value="header.links.${entry}" var="linkProp"/>
        <c:choose>
            <c:when test="${!empty WEB_PROPERTIES[linkProp]}">
                <li><a href="${WEB_PROPERTIES[linkProp]}">${entry}</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="${WEB_PROPERTIES['project.sitePrefix']}/${entry}.shtml">${entry}</a></li>
            </c:otherwise>
        </c:choose>
    </c:forEach>
    </ul>
        <!-- powered -->
        <p>Powered by</p>
        <a target="new" href="http://intermine.org" title="InterMine">
            <img src="images/icons/intermine-footer-logo.png" alt="InterMine logo" />
        </a>
    </div>

    <div  class="body" align="center" style="clear:both">
        <blockquote><font size="-2">XenMine is a collaboration between Baker, Li and Cherry Labs and the Intermine project at the <a href=" http://www.sysbiol.cam.ac.uk/"> Cambridge Systems Biology Centre</a>. XenMine is funded by NHGRI grant 5R01HD076839-02.
        </font></blockquote>

        <blockquote><font size="-2">Copyright &#169; 1997-2015 The Board of Trustees of Leland Stanford Junior University. Permission to use the information contained in this database was given by the researchers/institutes who contributed or published the information. Users of the database are solely responsible for compliance with any copyright restrictions, including those applying to the author abstracts. Documents from this server are provided "AS-IS" without any warranty, expressed or implied.
        <br>
        <br></font></blockquote>

      </div>
</div>
<!-- /footer.jsp -->