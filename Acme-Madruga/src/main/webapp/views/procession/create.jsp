<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="brotherhood/create.do" modelAttribute="brotherhood">

	<%-- Hidden properties from handy worker--%>
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="ticker" />


	<%-- Title --%>
	<acme:textbox code="brotherhood.name" path="name" />
	<br>

	<%-- Middlename --%>
	<acme:textbox code="brotherhood.middleName" path="middleName" />
	<br>

	<%-- Surname --%>
	<acme:textbox code="brotherhood.surname" path="surname" />
	<br>

	<%-- Photo --%>
	<acme:textbox code="brotherhood.photo" path="photo" />
	<br>

	<%-- Phone --%>
	<acme:textbox code="brotherhood.phone" path="phoneNumber" />
	<br>

	<%-- email --%>
	<acme:textbox code="brotherhood.email" path="email" />
	<br>

	<%-- Address --%>
	<acme:textbox code="brotherhood.address" path="address" />
	<br>

	<%-- Title --%>
	<acme:textbox code="brotherhood.title" path="title" />
	<br>

	<%-- Buttons --%>
	<input type="submit" name="save"
		value="<spring:message code="brotherhood.save"/>"
		onClick="javascript: return phoneNumberValidator()" />
	
	<acme:cancel code="brotherhood.cancel" url="/" />
</form:form>