<?xml version="1.0" encoding="iso-8859-1"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-1-transitional.dtd">

<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<html:html locale="true" xhtml="true">
<head>
<html:base/>
<link rel="stylesheet" type="text/css" href="flymine.css" />
<meta content="microarray, bioinformatics, drosophila, genomics" name="keywords" />
<meta content="Integrated queryable database for Drosophila and Anopheles genomics" name="description" />
<meta content="text/html; charset=iso-8859-1" http-equiv="Content-Type" />
<title><tiles:getAsString name="title"/></title>
</head>

<body bgcolor="#f4eeff">

<table cellpadding="10" border="0" bordercolor="red" width="100%" align="center">
  <tr>
    <td valign="bottom" align="left" colspan="3"><tiles:get name="header" /></td>
  </tr>
  <tr>
    <td valign="top" align="left" width="5%" height="10%" class="sidebar">
      <tiles:get name='menu' />
    </td>
    <td rowspan="2" valign="top" align="left" class="main"> <tiles:get name='body' /> </td>
    <td valign="top" align="left" class="main" width="15%"> <tiles:get name='right' /> </td>
  </tr>
  <tr>
    <td />
  </tr>
  <tr>
    <td colspan="3" align="center" class="footer"> <tiles:get name="footer" /> </td>
  </tr>
</table>

</body>
</html:html>
