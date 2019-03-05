<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<h2> <spring:message code="request.pending"/> </h2>

<display:table name="pendingRequests" id="row" requestURI="request/list.do" pagesize="5" class="displaytag">

   <security:authorize access="hasRole('MEMBER')">
        <display:column>
        <jstl:if test="${row.status eq 'PENDING'}">
          <a href="request/member/delete.do?requestId=${row.id}">
            <spring:message code="request.delete"/>
          </a>
        </jstl:if>
        </display:column>
     </security:authorize>

    <security:authorize access="hasRole('BROTHERHOOD')">
        <display:column>
        <jstl:if test="${row.status eq 'PENDING'}">
          <a href="request/brotherhood/edit.do?requestId=${row.id}">
            <spring:message code="request.edit"/>
          </a>
        </jstl:if>
        </display:column>
     </security:authorize>
	
	<!-- Status -->
	<spring:message code="request.status" var="statusHeader" />
	<display:column property="status" title="${statusHeader}" />
	
	<!-- AssignedRow -->
	<spring:message code="request.assignedRow" var="assignedRowHeader" />
	<display:column property="assignedRow" title="${assignedRowHeader}" />

	<!-- AssignedColumn -->
	<spring:message code="request.assignedColumn" var="assignedColumnHeader" />
	<display:column property="assignedColumn" title="${assignedColumnHeader}" />

	<!-- Reason -->
	<spring:message code="request.reason" var="reasonHeader" />
	<display:column property="reason" title="${reasonHeader}" />

	<!-- Member -->
	<spring:message code="request.member.name" var="memberHeader" />
	<display:column property="member.name" title="${memberHeader}" />

	<!-- Procession -->
	<spring:message code="request.procession.ticker" var="processionHeader" />
	<display:column property="procession.ticker" title="${processionHeader}" />
	
</display:table>

<h2> <spring:message code="request.approved"/> </h2>

<display:table name="approvedRequests" id="row" requestURI="request/list.do" pagesize="5" class="displaytag">

   <security:authorize access="hasRole('MEMBER')">
        <display:column>
        <jstl:if test="${row.status eq 'PENDING'}">
          <a href="request/member/delete.do?requestId=${row.id}">
            <spring:message code="request.delete"/>
          </a>
        </jstl:if>
        </display:column>
     </security:authorize>

    <security:authorize access="hasRole('BROTHERHOOD')">
        <display:column>
        <jstl:if test="${row.status eq 'PENDING'}">
          <a href="request/brotherhood/edit.do?requestId=${row.id}">
            <spring:message code="request.edit"/>
          </a>
        </jstl:if>
        </display:column>
     </security:authorize>
	
	<!-- Status -->
	<spring:message code="request.status" var="statusHeader" />
	<display:column property="status" title="${statusHeader}" />
	
	<!-- AssignedRow -->
	<spring:message code="request.assignedRow" var="assignedRowHeader" />
	<display:column property="assignedRow" title="${assignedRowHeader}" />

	<!-- AssignedColumn -->
	<spring:message code="request.assignedColumn" var="assignedColumnHeader" />
	<display:column property="assignedColumn" title="${assignedColumnHeader}" />

	<!-- Reason -->
	<spring:message code="request.reason" var="reasonHeader" />
	<display:column property="reason" title="${reasonHeader}" />

	<!-- Member -->
	<spring:message code="request.member.name" var="memberHeader" />
	<display:column property="member.name" title="${memberHeader}" />

	<!-- Procession -->
	<spring:message code="request.procession.ticker" var="processionHeader" />
	<display:column property="procession.ticker" title="${processionHeader}" />
	
</display:table>

<h2> <spring:message code="request.rejected"/> </h2>

<display:table name="rejectedRequests" id="row" requestURI="request/list.do" pagesize="5" class="displaytag">

   <security:authorize access="hasRole('MEMBER')">
        <display:column>
        <jstl:if test="${row.status eq 'PENDING'}">
          <a href="request/member/delete.do?requestId=${row.id}">
            <spring:message code="request.delete"/>
          </a>
        </jstl:if>
        </display:column>
     </security:authorize>

    <security:authorize access="hasRole('BROTHERHOOD')">
        <display:column>
        <jstl:if test="${row.status eq 'PENDING'}">
          <a href="request/brotherhood/edit.do?requestId=${row.id}">
            <spring:message code="request.edit"/>
          </a>
        </jstl:if>
        </display:column>
     </security:authorize>
	
	<!-- Status -->
	<spring:message code="request.status" var="statusHeader" />
	<display:column property="status" title="${statusHeader}" />
	
	<!-- AssignedRow -->
	<spring:message code="request.assignedRow" var="assignedRowHeader" />
	<display:column property="assignedRow" title="${assignedRowHeader}" />

	<!-- AssignedColumn -->
	<spring:message code="request.assignedColumn" var="assignedColumnHeader" />
	<display:column property="assignedColumn" title="${assignedColumnHeader}" />

	<!-- Reason -->
	<spring:message code="request.reason" var="reasonHeader" />
	<display:column property="reason" title="${reasonHeader}" />

	<!-- Member -->
	<spring:message code="request.member.name" var="memberHeader" />
	<display:column property="member.name" title="${memberHeader}" />

	<!-- Procession -->
	<spring:message code="request.procession.ticker" var="processionHeader" />
	<display:column property="procession.ticker" title="${processionHeader}" />
	
</display:table>