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
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<display:table name="suspicious" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">

	<spring:message code="administrator.name" var="nameHeader" />
	<display:column property="name" title="${nameHeader}" />

	<spring:message code="administrator.surname" var="surnameHeader" />
	<display:column property="surname" title="${surnameHeader}" />
	
	<spring:message code="administrator.email" var="emailHeader" />
	<display:column property="email" title="${emailHeader}" />


	<spring:message code="administrator.ban" var="banHeader" />
	<display:column title="${banHeader}">
		<jstl:if test="${!row.isBanned}">
			<a href="administrator/ban.do?actorId=${row.id}"><spring:message code="administrator.ban" /></a>
		</jstl:if>
	</display:column>


	<spring:message code="administrator.unban" var="unbanHeader" />
	<display:column title="${unbanHeader}">
		<jstl:if test="${row.isBanned}">
			<a href="administrator/unban.do?actorId=${row.id}"><spring:message code="administrator.unban" /></a>
		</jstl:if>
	</display:column>


</display:table>
<br>
<br>

<a href=administrator/computeSpammers.do><spring:message code="administrator.launchSpammers" /></a>
