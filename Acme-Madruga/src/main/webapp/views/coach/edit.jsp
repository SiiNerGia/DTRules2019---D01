<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="coach/brotherhood/edit.do" modelAttribute="coach">

	<%-- Hidden properties --%>
	<form:hidden path="id" />
	<form:hidden path="version" />


    <%-- title--%>
    <acme:textbox code="coach.title" path="title" />
    <br>

    <%-- description--%>
    <acme:textbox code="coach.description" path="description" />
    <br>

    <%-- picture --%>
    <acme:textbox code="coach.picture" path="picture" />
    <br>

	<%-- Buttons --%>
	<input type="submit" name="save"
		value="<spring:message code="coach.save"/>"/>
	
	<acme:cancel code="coach.cancel" url="/" />
</form:form>