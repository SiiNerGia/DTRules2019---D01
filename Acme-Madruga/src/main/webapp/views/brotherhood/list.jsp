<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<display:table name="brotherhoods" id="row"
	requestURI="brotherhood/list.do" pagesize="5" class="displaytag">

	<!-- Title -->
	<spring:message code="brotherhood.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" />

	<!-- Establishment -->
	<spring:message code="brotherhood.establishment"
		var="establishmentHeader" />
	<display:column property="establishment" title="${establishmentHeader}"
		format="{0,date,dd/MM/yyyy}" />

	<!-- Members -->
	<spring:message code="brotherhood.members" var="membersHeader" />
	<display:column>
		<a href="member/list.do?brotherhoodId=${row.id}"> <spring:message
				code="brotherhood.members" />
		</a>
	</display:column>

	<!-- Processions -->
	<spring:message code="brotherhood.processions" var="processionsHeader" />
	<display:column>
		<a href="procession/list.do?brotherhoodId=${row.id}"> <spring:message
				code="brotherhood.processions" />
		</a>
	</display:column>

	<!-- Floats -->
	<spring:message code="brotherhood.coaches" var="coachesHeader" />
	<display:column>
		<a href="coach/list.do?brotherhoodId=${row.id}"> <spring:message
				code="brotherhood.coaches" />
		</a>
	</display:column>

	<!-- Enrol/Dropout -->
	<security:authorize access="hasRole('MEMBER')">




		<display:column>


			<!-- para cada row, recorremos su coleccion de enrols, para cada enrol, 
	si ese enrol.member.id es igual a nuestro id, colocamos el link a dropout, 
	si no lo colocamos a enrol -->

		<jstl:set var= "username">	<security:authentication property="principal.username" /> </jstl:set>

			<jstl:set var="contains" value="false" />
			<jstl:forEach var="enr" items="${row.enrols }">
				<jstl:if test="${enr.member.userAccount.username == username }">
					<jstl:set var="contains" value="true" />
				</jstl:if>

			</jstl:forEach>
			<jstl:choose>
				<jstl:when test="${contains == true }">
					<a href="dropout/member/create.do?brotherhoodId=${row.id}"> <spring:message
							code="brotherhood.dropout" />
					</a>
				</jstl:when>

				<jstl:when test="${contains == false }">
					<a href="enrol/member/create.do?brotherhoodId=${row.id}"> <spring:message
							code="brotherhood.enrol" />
					</a>
				</jstl:when>
			</jstl:choose>
			<!-- 
	 <a href="enrol/member/enrol.do?brotherhoodId=${row.id}">
		<spring:message code ="brotherhood.enrol"/>
	</a>
	
	-->
		</display:column>

	</security:authorize>

</display:table>