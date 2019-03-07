<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="brotherhood/create.do" modelAttribute="brotherhoodForm">

	<%-- Hidden properties from handy worker--%>
<%--<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="userAccount.Authorities" />
	<form:hidden path="processions" />
	<form:hidden path="floats" />
	<form:hidden path="establishment" />
	<form:hidden path="pictures" /> --%>
	<%-- UserAccount--%>

	<%-- username--%>
	<acme:textbox code="brotherhood.username" path="userAccount.username" />
	<br>

	<%-- password--%>
	<acme:password code="brotherhood.password" path="userAccount.password" />
	<br>

	<%-- Name --%>
	<acme:textbox code="brotherhood.name" path="name" />
	<br>

	<%-- Middlename --%>
	<acme:textbox code="brotherhood.middleName" path="middlename" />
	<br>

	<%-- Surname --%>
	<acme:textbox code="brotherhood.surname" path="surname" />
	<br>

	<%-- Photo --%>
	<acme:textbox code="brotherhood.photo" path="photo" />
	<br>

	<%-- Phone --%>
	<acme:textbox code="brotherhood.phone" path="phone" />
	<br>

	<%-- email --%>
	<acme:textbox code="brotherhood.email" path="email" />
	<br>

	<%-- Address --%>
	<acme:textbox code="brotherhood.address" path="address" />
	<br>

	<%-- Title --%>
	<acme:textbox code="brotherhood.title" path="title" />
	<%-- Picture --%>
<%--  	<acme:textbox code="brotherhood.picture" path="pictures" />
	<br> --%>
	
	<p><input id="field_terms" onchange="this.setCustomValidity(validity.valueMissing ? '<spring:message code="brotherhood.check.terms"/>' : '');" type="checkbox" required name="terms"><spring:message code="brotherhood.terms"/></p>

	<script type="text/javascript">

		function phoneNumberValidator() {

			var phoneNumber = document.getElementById("phone").value;

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
		onClick="javascript: return phoneNumberValidator()"/>
	<acme:cancel code="brotherhood.cancel" url="/"/>
	
</form:form>

<script>

  document.getElementById("field_terms").setCustomValidity("<spring:message code="brotherhood.check.terms"/>");

</script>