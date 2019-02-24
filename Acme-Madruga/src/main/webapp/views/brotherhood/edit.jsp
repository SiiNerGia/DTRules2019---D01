<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="brotherhood/edit.do" modelAttribute="brotherhood">

	<%-- Hidden properties from handy worker--%>
	<form:hidden path="id" />
<%-- 	<form:hidden path="version" />
	<form:hidden path="userAccount.Authorities" />
	<form:hidden path="userAccount" />
	<form:hidden path="processions" />
	<form:hidden path="coaches" /> --%>
	<form:hidden path="establishment" />
	
	<%-- Name --%>
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
	
	
	<jstl:if test="${not empty brotherhood.pictures}">
	<display:table name="brotherhood.pictures"  id="row" >
		<spring:message code="brotherhood.picture" var="pictureNameHeader" />
		<display:column title="${pictureNameHeader}" sortable="false" >
			<img src="${row.link}" width="50%" height="200"/>
		</display:column>
			
 		<spring:message code="brotherhood.pictures.delete" var="deleteHeader" />
		<display:column title="${deleteHeader}">
			<a href="brotherhood/deletePicture.do?link=${row.link}"><spring:message code="brotherhood.picture.delete"/></a>
		</display:column>
		
	<display:caption><spring:message code="brotherhood.pictures"/></display:caption>
	</display:table>
	</jstl:if>
	<br>
	<a href="brotherhood/addPicture.do">
			<spring:message code="brotherhood.picture.create"/>
	</a>
	<br>

	<script type="text/javascript">
		function phoneNumberValidator() {

			var phoneNumber = document.getElementById("phoneNumber").value;

			var patternCCACPN = /^(\+[1-9][0-9]{0,2}) (\([1-9][0-9]{0,2}\)) (\d{3}\d+)/
			$;
			var patternCCPN = /^(\+[1-9][0-9]{0,2}) (\d{3}\d+)/
			$;
			var patternPN = /^(\d{3}\d+)/
			$;

			if (patternCCACPN.test(phoneNumber))
				return true;
			else if (patternCCPN.test(phoneNumber))
				return true;
			else if (patternPN.test(phoneNumber))
				return true;
			else
				return confirm('<spring:message code="brotherhood.confirm"/>');
		}
	</script>
	<%-- Buttons --%>
	<input type="submit" name="save"
		value="<spring:message code="brotherhood.save"/>"
		onClick="javascript: return phoneNumberValidator()" />
	
	<acme:cancel code="brotherhood.cancel" url="/" />
</form:form>