<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="area/administrator/edit.do" modelAttribute="area">

	<%-- Hidden properties --%>
	<form:hidden path="id" />
	<form:hidden path="version" />


    <%-- title--%>
    <acme:textbox code="area.name" path="name" />
    <br>


    <%-- picture --%>
	<jstl:if test="${not empty area.pictures}">
		<jstl:if test="${area.id!=0}">
			<display:table name="area.pictures"  id="row" >
				<spring:message code="area.picture" var="pictureNameHeader" />
				<display:column title="${pictureNameHeader}" sortable="false" >
					<img src="${row.link}" width="50%" height="200"/>
				</display:column>

				<spring:message code="area.pictures.delete" var="deleteHeader" />
				<display:column title="${deleteHeader}">
					<a href="area/administrator/deletePicture.do?link=${row.link}"><spring:message code="area.picture.delete"/></a>
				</display:column>

				<display:caption><spring:message code="area.pictures"/></display:caption>
			</display:table>
		</jstl:if>
	</jstl:if>
	<br>
	<a href="area/administrator/addPicture.do?areaId=${area.id}">
		<spring:message code="area.picture.create"/>
	</a>
	<br>

	<%-- Buttons --%>
	<input type="submit" name="save"
		value="<spring:message code="area.save"/>"/>
	
	<acme:cancel code="area.cancel" url="/" />
</form:form>