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

<form:form action="message/create.do" modelAttribute="mesage">

	<security:authorize
		access="hasAnyRole('ADMIN', 'REFEREE', 'CUSTOMER', 'HANDYWORKER', 'SPONSOR')">

		<%-- Hidden properties from message--%>
		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="sender" />
		<form:hidden path="moment" />
		<form:hidden path="messageBoxes" />


		<%-- Subject --%>
		<form:label path="subject">
			<spring:message code="message.subject" />
		</form:label>
		<form:input path="subject" />
		<form:errors class="error" path="subject" />
		<br>
		<br>

		<form:label path="priority">
			<spring:message code="message.priority" />
		</form:label>
		<form:select id="priorityDropdown" path="priority">
			<form:option value="0">--Select Priority--</form:option>
			<form:option value="LOW">LOW</form:option>
			<form:option value="MEDIUM">MEDIUM</form:option>
			<form:option value="HIGH">HIGH</form:option>
		</form:select>
		<form:errors class="error" path="priority" />
		<br>
		<br>

		<%-- Body --%>
		<form:label path="body">
			<spring:message code="message.body" />
		</form:label>
		<form:textarea path="body" />
		<form:errors class="error" path="body" />
		<br>
		<br>

		<%-- Recipients --%>
		<spring:message code="message.recipients" />
		<form:select path="recipients" multiple="true" itemValue="id">
			<form:options items="${actorList}" itemLabel="name" itemValue="id" />
		</form:select>
		<form:errors class="error" path="recipients" />
		<br>
		<br>

		<%-- Tags --%>
		<spring:message code="message.tags" />
		<form:input path="tags" />
		<form:errors class="error" path="tags" />
		<br>
		<br>

		<%-- Buttons --%>

		<input type="submit" name="save"
			value="<spring:message code="message.send"/>" />

		<input type="button" name="cancel"
			value="<spring:message code="message.cancel" />"
			onClick="javascript: window.location.replace('messageBox/list.do')" />

		<br>
		<br>
	</security:authorize>
</form:form>