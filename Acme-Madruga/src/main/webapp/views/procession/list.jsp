<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<display:table name="processions" id="row" requestURI="${uri}" pagesize="5" class="displaytag">

	<security:authorize access="hasRole('BROTHERHOOD')">
		<spring:message code="procession.edit" var="editHeader" />
		<display:column title="${editHeader}">
			<a href="procession/brotherhood/edit.do?processionId=${row.id}"> <spring:message code="procession.edit" /></a>
		</display:column>
	</security:authorize>

	<!-- title -->
	<spring:message code="procession.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" />

	<!-- ticker -->
	<spring:message code="procession.ticker" var="tickerHeader" />
	<display:column property="ticker" title="${tickerHeader}" />

	<!-- description -->
	<spring:message code="procession.description" var="descriptionHeader" />
	<display:column property="description" title="${ descriptionHeader }" />

	<!-- moment -->
	<spring:message code="procession.moment" var="momentHeader" />
	<display:column property="moment" title="${momentHeader}" format="{0,date,dd/MM/yyyy}" />

	<!-- brotherhood -->
	<spring:message code="procession.brotherhood" var="brotherhoodHeader" />
	<display:column property="brotherhood.title" title="${brotherhoodHeader}" />

	<security:authorize access="hasRole('BROTHERHOOD')">
		<spring:message code="procession.delete" var="deleteHeader" />
		<display:column title="${deleteHeader}">
			<a href="procession/brotherhood/delete.do?processionId=${row.id}"> <spring:message code="procession.delete" /></a>
		</display:column>
	</security:authorize>

</display:table>

<security:authorize access="hasRole('BROTHERHOOD')">
	<a href=procession/brotherhood/create.do><spring:message code="procession.create" /></a>
</security:authorize>