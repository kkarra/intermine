<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="im"%>


<!-- /objectDetailsAspectRefsCols.jsp -->

<html:xhtml/>

<tiles:importAttribute name="object"/>
<tiles:importAttribute name="aspect"/>


<table border="0">
  <c:if test="${!empty aspectRefsAndCollections[aspect]}">
    <c:forEach items="${aspectRefsAndCollections[aspect]}" var="entry">
      <c:set var="collection" value="${entry.value}"/>
      <c:set var="verbose" value="${!empty object.verbosity[entry.key]}"/>
      <c:set var="fieldName" value="${entry.key}"/>
      <tr>
        <td width="10px">
          <div style="white-space:nowrap">
            <c:choose>
              <c:when test="${verbose && collection.size > 0}">
                <html:link action="/modifyDetails?method=unverbosify&amp;field=${fieldName}&amp;id=${object.id}&amp;trail=${param.trail}">
                  <img border="0" src="images/minus.gif" alt="-" width="11" height="11"/>
                  <span class="collectionField">${fieldName}</span>
                </html:link>
              </c:when>
              <c:when test="${collection.size > 0}">
                <html:link action="/modifyDetails?method=verbosify&amp;field=${fieldName}&amp;id=${object.id}&amp;trail=${param.trail}">
                  <img border="0" src="images/plus.gif" alt="+" width="11" height="11"/>
                  <span class="collectionField">${fieldName}</span>
                </html:link>
                <c:if test="${collection.size == 1}">
                  <c:forEach items="${LEAF_DESCRIPTORS_MAP[collection.table.rowObjects[0]]}" var="cld2">
                    <c:if test="${WEBCONFIG.types[cld2.name].tableDisplayer != null}">
                      <c:set var="cld2" value="${cld2}" scope="request"/>
                      <c:set var="backup" value="${object}"/>
                      <c:set var="object" value="${collection.table.rowObjects[0]}" scope="request"/>
                      <tiles:insert page="${WEBCONFIG.types[cld2.name].tableDisplayer.src}"/>
                      <c:set var="object" value="${backup}"/>
                    </c:if>
                  </c:forEach>
                </c:if>
              </c:when>
              <c:otherwise>
                <span class="nullStrike">
                  <img border="0" src="images/plus-disabled.gif" alt=" " width="11" height="11"/>
                  <span class="collectionField nullReferenceField">${fieldName}</span>
                </span>
              </c:otherwise>
            </c:choose>
          </div>
        </td>
        <td width="1%" nowrap>
          <span class="collectionDescription ${collection.size == 0 ? 'nullReferenceField' : ''}">
            ${collection.size} <span class="type">${collection.cld.unqualifiedName}</span>
          </span>
          <c:if test="${collection.size == 1 && empty object.verbosity[fieldName]}">
            [<html:link action="/objectDetails?id=${collection.table.ids[0]}&amp;trail=${param.trail}_${collection.table.ids[0]}">
              <fmt:message key="results.details"/>
            </html:link>]
          </c:if>
         <%-- <c:if test="${collection.size == 0}">
            </span>
          </c:if> --%>
          &nbsp;
          <c:choose>
            <c:when test="${IS_SUPERUSER}">
              <c:set var="descriptor" value="${collection.descriptor}"/>
              <tiles:insert name="inlineTagEditor.tile">
                <tiles:put name="taggable" beanName="descriptor"/>
              </tiles:insert>
            </c:when>
            <c:otherwise>
              &nbsp;
            </c:otherwise>
          </c:choose>
        </td>
      </tr>
      <c:if test="${verbose && collection.size > 0}">
        <tr>
          <td colspan="2">
            
            <tiles:insert page="/objectDetailsCollectionTable.jsp">
              <tiles:put name="collection" beanName="collection"/>
            </tiles:insert>
            
            <c:choose>
              <c:when test="${collection.size > WEB_PROPERTIES['inline.table.size']}">
                <div class="refSummary">
                  [<html:link action="/collectionDetails?id=${object.id}&amp;field=${fieldName}&amp;pageSize=25&amp;trail=${param.trail}">
                    <fmt:message key="results.showallintable"/>
                  </html:link>]
                </div>
              </c:when>
              <c:otherwise>
                <div class="refSummary">
                  [<html:link action="/collectionDetails?id=${object.id}&amp;field=${fieldName}&amp;pageSize=25&amp;trail=${param.trail}">
                    <fmt:message key="results.showintable"/>
                  </html:link>]
                </div>
              </c:otherwise>
            </c:choose>
          </td>
        </tr>
      </c:if>
    </c:forEach>
  </c:if>
</table>

<!-- /objectDetailsAspectRefsCols.jsp -->

