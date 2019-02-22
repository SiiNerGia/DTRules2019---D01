<%--
 * action-1.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!-- Listing Grid -->

<b><spring:message code="administrator.config.brands"/>
<display:table name="brands" id="row" requestURI="${ requestURI }" class="displaytag">

	<!-- Word -->
	<spring:message code="admin.brand" var="brandHeader" />
	<display:column title="${brandHeader}">${row}</display:column>
	
	<!-- Edit word link -->
	<spring:message code="admin.edit.brand" var="editHeader" />
	<display:column title="${editHeader}">
		<a href="administrator/config/brand/edit.do?brand=${ row }&index=${ row_rowNum}"><spring:message code="administrator.edit" /></a>
	</display:column>
	
	<!-- Remove word link -->
	<spring:message code="admin.remove.brand" var="removeHeader" />
	<display:column title="${removeHeader}">
		<a href="administrator/config/brand/remove.do?word=${ row }"><spring:message code="admin.remove" /></a>
	</display:column>

</display:table>

<!-- Action link -->
<a href=administrator/config/brand/add.do><spring:message code="admin.add.brand" /></a>
<br><br>



