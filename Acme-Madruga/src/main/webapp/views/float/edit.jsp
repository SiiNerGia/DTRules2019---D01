<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="float/brotherhood/edit.do" modelAttribute="f">

	<%-- Hidden properties --%>
	<form:hidden path="id" />
	<form:hidden path="version" />


    <%-- title--%>
    <acme:textbox code="float.title" path="title" />
    <br>

    <%-- description--%>
    <acme:textbox code="float.description" path="description" />
    <br>

    <%-- picture --%>
	<jstl:if test="${not empty f.pictures}">
		<jstl:if test="${f.id!=0}">
			<display:table name="f.pictures"  id="row" >
				<spring:message code="float.picture" var="pictureNameHeader" />
				<display:column title="${pictureNameHeader}" sortable="false" >
					<img src="${row.link}" width="50%" height="200"/>
				</display:column>

				<spring:message code="float.pictures.delete" var="deleteHeader" />
				<display:column title="${deleteHeader}">
					<a href="float/brotherhood/deletePicture.do?link=${row.link}&floatId=${f.id}"><spring:message code="float.picture.delete"/></a>
				</display:column>

				<display:caption><spring:message code="float.pictures"/></display:caption>
			</display:table>
		</jstl:if>
	</jstl:if>
	<br>
	<jstl:if test="${f.id!=0}">
		<a href="float/brotherhood/addPicture.do?floatId=${f.id}">
			<spring:message code="float.picture.create"/>
		</a>
	</jstl:if>
	<br>

	<%-- Buttons --%>
	<input type="submit" name="save"
		value="<spring:message code="float.save"/>"/>
	
	<acme:cancel code="float.cancel" url="/" />
</form:form>