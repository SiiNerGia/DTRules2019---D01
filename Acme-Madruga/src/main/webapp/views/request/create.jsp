<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="request/member/create.do" modelAttribute="request">

	<%-- Hidden properties --%>
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="member" />
    <form:hidden path="status" />
    <form:hidden path="reason" />
    <form:hidden path="assignedRow" />
    <form:hidden path="assignedColumn" />

    <!-- Select Procession -->
       <form:label path="procession">
           <spring:message code="request.procession" />:
       </form:label>
       <form:select id="processions" path="procession">
           <form:option value="0" label="----" />
           <form:options items="${processions}" itemValue="id" itemLabel="ticker" />
       </form:select>
       <form:errors cssClass="error" path="procession" />
       <br />

	<%-- Buttons --%>
	<input type="submit" name="save" value="<spring:message code="request.save"/>" />
	
	<acme:cancel code="request.cancel" url="/" />
</form:form>