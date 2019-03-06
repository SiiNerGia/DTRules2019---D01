<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="float/brotherhood/addPicture.do?floatId=${floatId}" modelAttribute="url">
		<%-- Hidden properties--%>
		<form:hidden path="targetId" />

		<%-- Link--%>
		<form:label path="link">
			<spring:message code="float.picture.link" />
		</form:label>
		<form:input path="link" />
		<form:errors class="error" path="link" />
		<br>
		<br>

		<%-- Buttons --%>

		<input type="submit" name="save" value="<spring:message code="float.save"/>" />
		
		<input type="button" name="cancel"
			value="<spring:message code="float.cancel" />"
			onClick="javascript: window.location.replace('float/brotherhood/list.do')" />
	<br><br>
</form:form>