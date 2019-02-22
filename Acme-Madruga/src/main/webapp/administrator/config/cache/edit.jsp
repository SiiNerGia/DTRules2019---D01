<%--
 * action-1.jsp
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<form:form action="administrator/config/cache/edit.do" modelAttribute="configurations">
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="title" />
	<form:hidden path="logo" />
	<form:hidden path="spamWords" />
	<form:hidden path="positiveWords" />
	<form:hidden path="negativeWords" />
	<form:hidden path="vat" />
	<form:hidden path="countryCode" />
	<form:hidden path="brandName" />
	<form:hidden path="defaultCategories" />


	<%-- cacheTime --%>
	<form:label path="cacheTime">
		<spring:message code="cache.hours" />
	</form:label>
	<form:input type="number" step="1" path="cacheTime" />
	<form:errors class="error" path="cacheTime" />
	<br><br>

	<%-- finderMaxResult --%>
	<form:label path="finderMaxResult">
		<spring:message code="cache.finderMaxResult" />
	</form:label>
	<form:input type="number" step="5" path="finderMaxResult" />
	<form:errors class="error" path="finderMaxResult" />
	<br><br>


	<acme:submit name="update" code="administrator.update.cache" />

	<input type="button" name="cancel"
		value="<spring:message code="administrator.cancel" />"
		onClick="javascript: window.location.replace('/')" />

</form:form>