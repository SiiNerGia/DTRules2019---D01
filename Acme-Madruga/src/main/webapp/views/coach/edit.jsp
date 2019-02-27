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
	<jstl:if test="${not empty coach.pictures}">
		<jstl:if test="${coach.id!=0}">
			<display:table name="coach.pictures"  id="row" >
				<spring:message code="coach.picture" var="pictureNameHeader" />
				<display:column title="${pictureNameHeader}" sortable="false" >
					<img src="${row.link}" width="50%" height="200"/>
				</display:column>

				<spring:message code="coach.pictures.delete" var="deleteHeader" />
				<display:column title="${deleteHeader}">
					<a href="coach/brotherhood/deletePicture.do?link=${row.link}"><spring:message code="coach.picture.delete"/></a>
				</display:column>

				<display:caption><spring:message code="coach.pictures"/></display:caption>
			</display:table>
		</jstl:if>
	</jstl:if>
	<br>
	<a href="coach/brotherhood/addPicture.do?coachId=${coach.id}">
		<spring:message code="coach.picture.create"/>
	</a>
	<br>

	<%-- Buttons --%>
	<input type="submit" name="save"
		value="<spring:message code="coach.save"/>"/>
	
	<acme:cancel code="coach.cancel" url="/" />
</form:form>