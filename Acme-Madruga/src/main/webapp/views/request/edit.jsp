<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="request/brotherhood/edit.do" modelAttribute="request">

	<%-- Hidden properties --%>
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="member" />
    <form:hidden path="procession" />

       <!-- Select Status -->
        <form:label path="status">
            <spring:message code="request.status"/>
            </form:label>
        <form:select id="status" path="status">
            <option value="${'APPROVED'}">
                   <spring:message code="request.status.approved"/>
               </option>
               <option value="${'REJECTED'}">
                   <spring:message code="request.status.rejected"/>
               </option>
        </form:select>
        <form:errors cssClass="error" path="status"/>
        <br/>

       <%-- assignedRow--%>
       <acme:textbox code="request.assignedRow" path="assignedRow" />
       <br>

       <%-- assignedColumn--%>
       <acme:textbox code="request.assignedColumn" path="assignedColumn" />
       <br>

       <%-- reason --%>
       <acme:textbox code="request.reason" path="reason" />
       <br>

	<%-- Buttons --%>
	<input type="submit" name="save" value="<spring:message code="request.save"/>" />
	
	<acme:cancel code="request.cancel" url="/" />
</form:form>