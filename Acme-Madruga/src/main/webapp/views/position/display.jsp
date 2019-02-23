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

<display:table name="category" id="row">

	<spring:message code="category.name" var="nameHeader" />
	<display:column property="name" title="${nameHeader}" sortable="false" />

	<spring:message code="category.father" var="fatherHeader" />
	<display:column property="father.name" title="${fatherHeader}" />
	
</display:table>

<display:table name="category.sons" id="row">

	<!-- Create a header for the sons table -->
	<spring:message code="category.sons" var="sonsHeader"></spring:message>
	<display:caption style="display: table-caption;text-align: center;">
		<jstl:out value="${sonsHeader}"></jstl:out>
	</display:caption>
	
	<!-- Make visible the header -->
	<display:setProperty name="basic.show.header" value="true"/>
	
	<!-- Cell content -->
	<display:column property="category.name" title="${sonsHeader}" />

</display:table>

<input type="button" name="goBack" value="<spring:message code="category.goBack"/>" />

<onclick="javascript: window.location.replace('position/admin/list.do')" /> 