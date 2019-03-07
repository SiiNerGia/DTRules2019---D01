<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<display:table name="belonging" id="row" requestURI="brotherhood/member/list.do" pagesize="5" class="displaytag">
	
	<!-- Title -->
	<spring:message code="brotherhood.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" />
	
	<!-- Establishment -->
	<spring:message code="brotherhood.establishment" var="establishmentHeader" />
	<display:column property="establishment" title="${establishmentHeader}" format="{0,date,dd/MM/yyyy}"/>
	
	<!-- Members -->
	<spring:message code="brotherhood.members" var="membersHeader" />
	<display:column>
			<a href="member/list.do?brotherhoodId=${row.id}">
				<spring:message code="brotherhood.members" />
			</a>
	</display:column>
	
	<!-- Processions -->
	<spring:message code="brotherhood.processions" var="processionsHeader" />
	<display:column>
			<a href="procession/list.do?brotherhoodId=${row.id}">
				<spring:message code="brotherhood.processions" />
			</a>
	</display:column>
	
	<!-- Floats -->
	<spring:message code="brotherhood.floats" var="floatsHeader" />
	<display:column>
			<a href="coach/list.do?brotherhoodId=${row.id}">
				<spring:message code="brotherhood.floats" />
			</a>
	</display:column>
	<display:caption><spring:message code="brotherhood.list.belonging"/></display:caption>
</display:table>

<jstl:if test="${not empty belonged}">
<display:table name="belonged" id="row" requestURI="brotherhood/member/list.do" pagesize="5" class="displaytag">
	
	<!-- Title -->
	<spring:message code="brotherhood.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" />
	
	<!-- Establishment -->
	<spring:message code="brotherhood.establishment" var="establishmentHeader" />
	<display:column property="establishment" title="${establishmentHeader}" format="{0,date,dd/MM/yyyy}"/>
	
	<!-- Members -->
	<spring:message code="brotherhood.members" var="membersHeader" />
	<display:column>
			<a href="member/list.do?brotherhoodId=${row.id}">
				<spring:message code="brotherhood.members" />
			</a>
	</display:column>
	
	<!-- Processions -->
	<spring:message code="brotherhood.processions" var="processionsHeader" />
	<display:column>
			<a href="procession/list.do?brotherhoodId=${row.id}">
				<spring:message code="brotherhood.processions" />
			</a>
	</display:column>
	
	<!-- Floats -->
	<spring:message code="brotherhood.floats" var="floatsHeader" />
	<display:column>
			<a href="coach/list.do?brotherhoodId=${row.id}">
				<spring:message code="brotherhood.floats" />
			</a>
	</display:column>
	<display:caption><spring:message code="brotherhood.list.belonged"/></display:caption>
</display:table>
</jstl:if>