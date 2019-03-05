<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<display:table name="member" id="row" requestURI="member/brotherhood/display.do" class="displaytag">

	<!-- Name -->
	<spring:message code="member.name" var="nameHeader" />
	<display:column property="name" title="${nameHeader}" />

	<!-- Middle Name -->
	<spring:message code="member.middleName" var="middlenameHeader" />
	<display:column property="middleName" title="${middlenameHeader}" />

	<!-- SurName -->
	<spring:message code="member.surname" var="surnameHeader" />
	<display:column property="surname" title="${surnameHeader}" />

</display:table>
<display:table name="member" id="row" requestURI="member/display.do" class="displaytag">
	<!-- Email -->
	<spring:message code="member.email" var="emailHeader" />
	<display:column property="email" title="${emailHeader}" />

	<!-- phoneNumber -->
	<spring:message code="member.phoneNumber" var="phoneNumberHeader" />
	<display:column property="phoneNumber" title="${phoneNumberHeader}" />

	<!-- address -->
	<spring:message code="member.address" var="addressHeader" />
	<display:column property="address" title="${addressHeader}" />
<display:caption><spring:message code="member.contact"/></display:caption>
</display:table>

<jstl:if test="${not empty member.photo}">
	<img src="${member.photo.link}" width="50%" height="200"/>
</jstl:if>

<acme:cancel code="member.goback" url="/member/brotherhood/list.do" />