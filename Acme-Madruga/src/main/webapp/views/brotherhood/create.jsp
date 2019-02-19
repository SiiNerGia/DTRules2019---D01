<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="brotherhood/create.do" modelAttribute="brotherhood">

	<%-- Hidden properties from handy worker--%>
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="userAccount.Authorities" />
	<form:hidden path="processions" />
	<form:hidden path="coaches" />
	<form:hidden path="establishment" />

	<%-- UserAccount--%>

	<%-- username--%>
	<form:label path="userAccount.username">
		<spring:message code="brotherhood.username" />
	</form:label>
	<form:input path="userAccount.username" />
	<form:errors class="error" path="userAccount.username" />
	<br>
	<br>

	<%-- password--%>
	<form:label path="userAccount.password">
		<spring:message code="brotherhood.password" />
	</form:label>
	<form:password path="userAccount.password" />
	<form:errors class="error" path="userAccount.password" />
	<br>
	<br>

	<%-- Name --%>
	<form:label path="name">
		<spring:message code="brotherhood.name" />
	</form:label>
	<form:input path="name" />
	<form:errors class="error" path="name" />
	<br>
	<br>

	<%-- Middlename --%>
	<form:label path="middleName">
		<spring:message code="brotherhood.middleName" />
	</form:label>
	<form:input path="middleName" />
	<form:errors class="error" path="middleName" />
	<br>
	<br>

	<%-- Surname --%>
	<form:label path="surname">
		<spring:message code="brotherhood.surname" />
	</form:label>
	<form:input path="surname" />
	<form:errors class="error" path="surname" />
	<br>
	<br>

	<%-- Photo --%>
	<form:label path="photo">
		<spring:message code="brotherhood.photo" />
	</form:label>
	<form:input path="photo" />
	<form:errors class="error" path="photo" />
	<br>
	<br>

	<%-- Phone --%>
	<form:label path="phoneNumber">
		<spring:message code="brotherhood.phone" />
	</form:label>
	<form:input path="phoneNumber" />
	<form:errors class="error" path="phoneNumber" />
	<br>
	<br>

	<%-- email --%>
	<form:label path="email">
		<spring:message code="brotherhood.email" />
	</form:label>
	<form:input path="email" />
	<form:errors class="error" path="email" />
	<br>
	<br>

	<%-- Address --%>
	<form:label path="address">
		<spring:message code="brotherhood.address" />
	</form:label>
	<form:input path="address" />
	<form:errors class="error" path="address" />
	<br>
	<br>

	<%-- Title --%>
	<form:label path="title">
		<spring:message code="brotherhood.title" />
	</form:label>
	<form:input path="title" />
	<form:errors class="error" path="title" />
	<br>
	<br>

	<%-- Picture --%>
 	<form:label path="pictures">
		<spring:message code="brotherhood.picture" />
	</form:label>
	<form:input path="pictures" />
	<form:errors class="error" path="pictures" />
	<br>
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

	<input type="button" name="cancel"
		value="<spring:message code="brotherhood.cancel" />"
		onClick="javascript: window.location.replace('/Acme-Madruga/')" />
</form:form>