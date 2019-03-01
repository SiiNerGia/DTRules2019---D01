<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<display:table name="areas" id="row" requestURI="area/administrator/list.do" pagesize="5" class="displaytag">

   <security:authorize access="hasRole('ADMINISTRATOR')">
        <display:column>
          <a href="area/administrator/delete.do?areaId=${row.id}">
            <spring:message code="area.delete"/>
          </a>
        </display:column>
        <display:column>
          <a href="area/administrator/edit.do?areaId=${row.id}">
            <spring:message code="area.edit"/>
          </a>
        </display:column>
   </security:authorize>
	
	<!-- Title -->
	<spring:message code="area.name" var="nameHeader" />
	<display:column property="name" title="${nameHeader}" />
	
	
	<!-- Picture -->
	<spring:message code="area.picture" var="pictureHeader" />
	<display:column property="picture" title="${pictureHeader}" />

</display:table>