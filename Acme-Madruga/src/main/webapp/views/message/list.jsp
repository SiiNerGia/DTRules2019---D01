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

<display:table name="messages" id="row" requestURI="message/list.do" pagesize="5" class="displaytag">


	<spring:message code="message.subject" var="subjectHeader" />
	<display:column property="subject" title="${subjectHeader}" />

	<spring:message code="message.sender" var="senderHeader" />
	<display:column property="sender.name" title="${senderHeader}" sortable="true" />
	
	<spring:message code="message.priority" var="priorityHeader" />
	<display:column property="priority" title="${priorityHeader}" sortable="true" />
	
	<!-- Display -->
	<display:column>
		<a href="message/display.do?messageID=${row.id}&messageBoxID=${messageBox}">
 		<spring:message code="message.display"/></a>
 	</display:column>

</display:table>

<security:authorize access="isAuthenticated()">
	<a href=message/create.do><spring:message code="message.create" /></a>
</security:authorize>

<input type="submit" name="goBack" value="<spring:message code="messageBox.goBack" />"
		onClick="javascript: window.location.replace('messageBox/list.do')" />