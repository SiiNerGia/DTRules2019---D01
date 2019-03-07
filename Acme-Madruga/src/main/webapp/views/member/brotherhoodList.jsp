<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<display:table name="members" id="row" requestURI="${uri}" pagesize="5" class="displaytag">
	<!-- Name -->
	<spring:message code="member.name" var="nameHeader" />
	<display:column property="name" title="${nameHeader}" />
	
	<!-- Username -->
	<spring:message code="member.username" var="usernameHeader" />
	<display:column property="username" title="${usernameHeader}" />
	
	<!-- Middle Name -->
	<spring:message code="member.middleName" var="middleNameHeader" />
	<display:column property="middleName" title="${middleNameHeader}" />
	
	<!-- Surname -->
	<spring:message code="member.surname" var="surnameHeader" />
	<display:column property="surname" title="${surnameHeader}" />
</display:table>