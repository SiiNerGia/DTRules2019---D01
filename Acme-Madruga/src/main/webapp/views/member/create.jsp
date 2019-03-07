<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="member/member/create.do" modelAttribute="memberForm">

	<%-- Hidden properties from handy worker
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="userAccount.authorities" />
	<form:hidden path="enrols" />
	<form:hidden path="dropouts" />
	<form:hidden path="requests" />
	<form:hidden path="finder" />
	<form:hidden path="messageBoxes" />
--%>
	<%-- UserAccount--%>

	<%-- username--%>
	<acme:textbox code="member.username" path="userAccount.username" />
	<br>

	<%-- password--%>
	<acme:password code="member.password" path="userAccount.password" />
	<br>

	<%-- Name --%>
	<acme:textbox code="member.name" path="name" />
	<br>

	<%-- Middlename --%>
	<acme:textbox code="member.middleName" path="middleName" />
	<br>

	<%-- Surname --%>
	<acme:textbox code="member.surname" path="surname" />
	<br>

	<%-- Photo --%>
	<acme:textbox code="member.photo" path="photo" />
	<br>

	<%-- Phone --%>
	<acme:textbox code="member.phone" path="phoneNumber" />
	<br>

	<%-- email --%>
	<acme:textbox code="member.email" path="email" />
	<br>

	<%-- Address --%>
	<acme:textbox code="member.address" path="address" />
	<br>
	
	<p><input id="field_terms" onchange="this.setCustomValidity(validity.valueMissing ? '<spring:message code="member.check.terms"/>' : '');" type="checkbox" required name="terms"><spring:message code="member.terms"/></p>

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
				return confirm('<spring:message code="member.confirm"/>');
		}
	</script>
	<%-- Buttons --%>
	<input type="submit" name="save"
		value="<spring:message code="member.save"/>"
	  	onClick="javascript: return phoneNumberValidator()"/>
	<acme:cancel code="member.cancel" url="/" />
</form:form>

<script>

  document.getElementById("field_terms").setCustomValidity("<spring:message code="member.check.terms"/>");

</script>