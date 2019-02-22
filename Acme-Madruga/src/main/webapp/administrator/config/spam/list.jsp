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

<b><spring:message code="config.spamWords"/>
<display:table name="spamWords" id="row" requestURI="administrator/config/spam/list.do" pagesize="5" class="displaytag">

	<!-- Word -->
	<spring:message code="admin.word" var="wordHeader" />
	<display:column title="${wordHeader}">${ row }</display:column>
	
	<!-- Edit word link -->
	<spring:message code="admin.edit.word" var="editHeader" />
	<display:column title="${editHeader}">
		<a href="administrator/config/spam/edit.do?word=${ row }&index=${ row_rowNum}"><spring:message code="admin.edit.word" /></a>
	</display:column>
	
	<!-- Remove word link -->
	<spring:message code="admin.remove.word" var="removeHeader" />
	<display:column title="${removeHeader}">
		<a href="administrator/removeSpamWord.do?word=${ row }"><spring:message code="admin.remove.word" /></a>
	</display:column>

</display:table>

<!-- Action link -->
<a href=administrator/config/spam/add.do><spring:message code="admin.addWord" /></a>
<br><br>



