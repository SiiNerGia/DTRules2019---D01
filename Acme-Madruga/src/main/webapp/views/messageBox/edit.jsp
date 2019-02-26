<%--
 * action-2.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
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

<form:form action="messageBox/edit.do" modelAttribute="messageBox">

	<%-- Hidden properties--%>
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="isSystemBox" />
	<form:hidden path="messages" />

	<%-- Description--%>
	<form:label path="name">
		<spring:message code="messageBox.name" />
	</form:label>
	<form:input path="name" />
	<form:errors class="error" path="name" />
	<br>
	<br>

	<%-- Buttons --%>
	<security:authorize access="isAuthenticated()">
		<input type="submit" name="save" value="<spring:message code="messageBox.save"/>"/>
		
		<input type="button" name="cancel"
			value="<spring:message code="messageBox.cancel" />"
			onClick="javascript: window.location.replace('messageBox/list.do')" />
			
		<jstl:if test="${messageBox.id != 0 && messageBox.isSystemBox == false}">
			<input type="submit" name="delete" value="<spring:message code="messageBox.delete"/>" />
		</jstl:if>
		
	</security:authorize>
	<br><br>
</form:form>