<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="procession/brotherhood/edit.do" modelAttribute="procession">

	<%-- Hidden properties from handy worker--%>
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="ticker" />
	<form:hidden path="brotherhood" />
	<form:hidden path="requests" />



	<%-- Title --%>
	<acme:textbox code="procession.title" path="title" />
	<br>

	<%-- description --%>
	<acme:textbox code="procession.description" path="description" />
	<br>

	<%-- moment --%>
	<form:label path="moment"><spring:message code="procession.moment" /></form:label>
	<form:input path="moment" placeholder="dd/mm/yyyy" format="{0,date,dd/MM/yyyy HH:mm}" />	
	<form:errors class="error" path="moment" />
	<br><br>

	<%-- draftMode --%>
	<form:label path="draftMode">
		<spring:message code="procession.draftMode" />
	</form:label>
	<form:select id="modeDropdown" path="draftMode">
		<form:option value="">--</form:option>
		<form:option value="0"><spring:message code="procession.false" /></form:option>
		<form:option value="1"><spring:message code="procession.true" /></form:option>
	</form:select>
	<form:errors class="error" path="draftMode" />
	<br>
	<br>


	<%-- Buttons --%>
	<input type="submit" name="save" value="<spring:message code="procession.save"/>" />
	<acme:cancel code="procession.cancel" url="procession/list.do" />
	
</form:form>