<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<display:table name="processions" id="row" requestURI="${ uri }" pagesize="5" class="displaytag">
	
	<!-- ticker -->
	<spring:message code="procession.ticker" var="tickerHeader" />
	<display:column property="title" title="${tickerHeader}" />
	
	<!-- title -->
	<spring:message code="procession.title" var="titleHeader" />
	<display:column property="establishment" title="${titleHeader}" format="{0,date,dd/MM/yyyy}"/>
	
	<!-- description -->
	<spring:message code="procession.description" var="descriptionHeader" />
	<display:column property="description" title="${ descriptionHeader }" />
	
	<!-- moment -->
	<spring:message code="procession.moment" var="momentHeader" />
	<display:column property="moment" title="${momentHeader}" format="{0,date,dd/MM/yyyy}"/>
	
	<!-- brotherhood -->
	<spring:message code="procession.brotherhood" var="brotherhoodHeader" />
	<display:column property="brotherhood" title="${brotherhoodHeader}" />
	
</display:table>

<security:authorize access="hasRole('BROTHERHOOD')">
	<a href=procession/brotherhood/create.do><spring:message code="procession.create" /></a>
</security:authorize>