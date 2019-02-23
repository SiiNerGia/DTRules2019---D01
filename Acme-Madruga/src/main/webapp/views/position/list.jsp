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
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

<!-- Listing Grid -->
<display:table name="positions" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">

	<!-- Edit -->
	<security:authorize access="hasRole('ADMIN')">
		<spring:message code="position.edit" var="editHeader" />
		<display:column title="${ editHeader }">
			<a href="position/admin/edit.do?positionId=${row.id}"><spring:message code="position.edit" /></a>
		</display:column>
	</security:authorize>

	<!-- Spanish Name -->
	<spring:message code="position.spanish" var="spanishHeader" />
	<display:column property="spanishName" title="${spanishHeader}" />
	
	<!-- English Name -->
	<spring:message code="position.english" var="englishHeader" />
	<display:column property="englishName" title="${englishHeader}" />
	
</display:table>




<!-- Action link -->
<security:authorize access="hasRole('ADMIN')">
	<a href=position/admin/create.do><spring:message code="position.create" /></a>
</security:authorize>