<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<display:table name="floats" id="row" requestURI="float/list.do" pagesize="5" class="displaytag">

   <security:authorize access="hasRole('BROTHERHOOD')">
        <display:column>
          <a href="float/brotherhood/delete.do?floatId=${row.id}">
            <spring:message code="float.delete"/>
          </a>
        </display:column>
        <display:column>
          <a href="float/brotherhood/edit.do?floatId=${row.id}">
            <spring:message code="float.edit"/>
          </a>
        </display:column>
   </security:authorize>
	
	<!-- Title -->
	<spring:message code="float.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" />
	
	<!-- Description -->
	<spring:message code="float.description" var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader}" />

	<!-- Picture -->
	<spring:message code="float.pictures" var="picturesHeader" />
	<display:column property="pictures" title="${picturesHeader}" />

</display:table>