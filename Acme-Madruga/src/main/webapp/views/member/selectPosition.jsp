<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="member/brotherhood/selectPosition.do" modelAttribute="enrol">

	<%-- Hidden properties from handy worker--%>
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="member" />
	<form:hidden path="brotherhood" />
	<form:hidden path="moment" />
	
	<%-- Position --%>
	<jstl:if test="${pageContext.response.locale == 'en'}">
		<acme:select2 code="enrol.position" path="positions" items="${positions}" itemLabel="englishName" />
	</jstl:if>
		
	<jstl:if test="${pageContext.response.locale == 'es'}">
		<acme:select2 code="enrol.position" path="positions" items="${positions}" itemLabel="spanishName" />
	</jstl:if>
	
	<br>

	<%-- Buttons --%>
	<input type="submit" name="save" value="<spring:message code="brotherhood.save"/> "/>
	
	<acme:cancel code="brotherhood.cancel" url="/member/brotherhood/list.do" />
</form:form>