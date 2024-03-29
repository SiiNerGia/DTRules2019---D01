<%--
 * action-2.jsp
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


<!-- modelAttribute="configurations" -->

<form:form action="${ action }" >
	
	
	<%-- Add word --%>
	<spring:message code="admin.addWord" />
	<input name="brand" />
	<br>
	<br>


	<%-- Buttons --%>
	<input type="submit" name="save" value="<spring:message code="note.add"/>" />
	
	
	<input type="button" name="cancel" value="<spring:message code="administrator.cancel" />"
		   onClick="javascript: window.location.replace('administrator/config/brand/list.do')" />

</form:form>
