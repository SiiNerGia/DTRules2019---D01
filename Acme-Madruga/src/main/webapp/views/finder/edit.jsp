<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="finder/member/edit.do" modelAttribute="finder">
	
	<%-- Hidden properties from finder--%>
	<form:hidden path="id" />
	
	<%-- keyword--%>
	<acme:textbox code="finder.keyword" path="keyword" />
	<br>
	
	<%-- max date --%>
	<acme:textbox code="finder.maxDate" path="maxDate" />
	<br>
	
	<%-- min date --%>
	<acme:textbox code="finder.minDate" path="minDate" />
	<br>
	
	<%-- Area 
	<acme:select code="finder.area" path="area" items="${areas}" itemLabel="name" />
	<br>--%>
	
	
	<%-- Buttons --%>
	<input type="submit" name="save" 
		value="<spring:message code="finder.save"/>"/>
	
	<acme:cancel code="finder.cancel" url="/" />
		
</form:form>