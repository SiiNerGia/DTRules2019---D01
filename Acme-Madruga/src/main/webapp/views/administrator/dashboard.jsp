<%--
 * action-2.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<spring:message code="administrator.dashboard.avg" var="avgHeader" />
<spring:message code="administrator.dashboard.min" var="minHeader" />
<spring:message code="administrator.dashboard.max" var="maxHeader" />
<spring:message code="administrator.dashboard.std" var="stdHeader" />
<spring:message code="administrator.dashboard.ratio" var="ratioHeader" />
<spring:message code="administrator.dashboard.name" var="nameHeader" />
<spring:message code="administrator.dashboard.email" var="emailHeader" />
<spring:message code="administrator.brotherhood" var="brotherhoodlHeader" />
<spring:message code="administrator.member" var="memberHeader" />
<spring:message code="administrator.status" 	var="statusHeader" />
<spring:message code="administrator.procession" var="processionHeader" />
<spring:message code="administrator.moment" 	var="momentHeader" />


<spring:message code="administrator.dashboard.query1" var="query1Header" />
<spring:message code="administrator.dashboard.query2" var="query2Header" />
<spring:message code="administrator.dashboard.query3" var="query3Header" />
<spring:message code="administrator.dashboard.query4" var="query4Header" />
<spring:message code="administrator.dashboard.query5" var="query5Header" />
<spring:message code="administrator.dashboard.query6" var="query6Header" />
<spring:message code="administrator.dashboard.query7" var="query7Header" />
<spring:message code="administrator.dashboard.query8" var="query8Header" />


<!--  Custom table style -->
<head>
<link rel="stylesheet" href="styles/tablas.css" type="text/css">
<link rel="stylesheet" href="styles/charts.css" type="text/css">
</head>



<!-- Charts -->
<div class="chart-container">
	<div class="pie-chart-container">
		<canvas id="pie-chartcanvas-1"></canvas>
	</div>
</div>

<script>
$(document).ready(function () {
	var ctx1 = $("#pie-chartcanvas-1");
	
	var data1 = {
		labels: ["Spammers", "Not Spammers"],
		datasets: [
			{
				label: "Acme-Madruga",
				data: [${spammers}, ${notSpammers}],
				backgroundColor: [
					"#0000FF",
					"#FF0000"
				],
				borderColor: ["#000000"],
				borderWidth: [1]
			}
		]
	};
	
	
	var chart1 = new Chart(ctx1, {
		type: "pie",
		data: data1,
		options: {}
	});
});


</script>


<!-- C level -->

<!-- Query 1 -->
<table>
	<caption>
		<jstl:out value="${query1Header}"></jstl:out>
	</caption>
	<tr>
		<th><jstl:out value="${avgHeader}"></jstl:out></th>
		<th><jstl:out value="${minHeader}"></jstl:out></th>
		<th><jstl:out value="${maxHeader}"></jstl:out></th>
		<th><jstl:out value="${stdHeader}"></jstl:out></th>
	</tr>

	<tr>
		<td><jstl:out value="${query1[0]}"></jstl:out></td>
		<td><jstl:out value="${query1[1]}"></jstl:out></td>
		<td><jstl:out value="${query1[2]}"></jstl:out></td>
		<td><jstl:out value="${query1[3]}"></jstl:out></td>
	</tr>
</table>
<br />

<!-- Query 2  -->
<table>
	<caption>
		<jstl:out value="${query2Header}"></jstl:out>
	</caption>
	<tr>
		<th><jstl:out value="${brotherhoodlHeader}"></jstl:out></th>
		<th><jstl:out value="${memberHeader}"></jstl:out></th>
	</tr>
	<jstl:forEach items="${query2}" var="row">
      <tr>
        	<td>${row.title}</td>
        	<td>${row.enrols.size()}</td>
      </tr>
   </jstl:forEach>
</table>
<br />

<!-- Query 3  -->
<table>
	<caption>
		<jstl:out value="${query3Header}"></jstl:out>
	</caption>
	<tr>
		<th><jstl:out value="${brotherhoodlHeader}"></jstl:out></th>
		<th><jstl:out value="${memberHeader}"></jstl:out></th>
	</tr>
	<jstl:forEach items="${query3}" var="row">
      <tr>
        	<td>${row.title}</td>
        	<td>${row.enrols.size()}</td>
      </tr>
   </jstl:forEach>
</table>
<br />

<!-- Query 4  -->
<table>
	<caption>
		<jstl:out value="${query4Header}"></jstl:out>
	</caption>
	<tr>
		<th><jstl:out value="${ratioHeader}"></jstl:out></th>
		<th><jstl:out value="${statusHeader}"></jstl:out></th>
	</tr>
	<tr>
		<td><jstl:out value="${query4[0]}"></jstl:out></td>
		<td><jstl:out value="Aproved"></jstl:out></td>
	</tr>
	<tr>
		<td><jstl:out value="${query4[1]}"></jstl:out></td>
		<td><jstl:out value="Pending"></jstl:out></td>
	</tr>
	<tr>
		<td><jstl:out value="${query4[2]}"></jstl:out></td>
		<td><jstl:out value="Rejected"></jstl:out></td>
	</tr>
</table>
<br />

<!-- Query 5  -->
<table>
	<caption>
		<jstl:out value="${query5Header}"></jstl:out>
	</caption>
	<tr>
		<th><jstl:out value="${processionHeader}"></jstl:out></th>
		<th><jstl:out value="${momentHeader}"></jstl:out></th>
	</tr>
	<jstl:forEach items="${query5}" var="row">
      <tr>
        	<td>${row.title}</td>
        	<td><fmt:formatDate value="${row.moment}" pattern="dd/MM/yyyy" /></td>
      </tr>
   </jstl:forEach>
</table>


<!-- Query 6  -->
<!-- <table> -->
<!-- 	<caption> -->
<%-- 		<jstl:out value="${query6Header}"></jstl:out> --%>
<!-- 	</caption> -->
<!-- 	<tr> -->
<%-- 		<th><jstl:out value="${ratioHeader}"></jstl:out></th> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<%-- 		<td><jstl:out value="${query6}"></jstl:out></td> --%>
<!-- 	</tr> -->
<!-- </table> -->
<!-- <br /> -->

<!-- Query 7  -->
<!-- <table> -->
<!-- 	<caption> -->
<%-- <%-- 		<jstl:out value="${query7Header}"></jstl:out> --%> --%>
<!-- 	</caption> -->
<!--  	<tr> -->
<%-- <%-- 		<th><jstl:out value="${ratioHeader}"></jstl:out></th> --%> --%>
<!--  	</tr> -->
<!--  	<tr> -->
<%-- <%-- 		<td><jstl:out value="${query7}"></jstl:out></td> --%> --%>
<!-- 	</tr> -->
<!-- </table> -->
<!-- <br /> -->

<!-- Query 8  -->
<!-- <table> -->
<!-- 	<caption> -->
<%-- 		<jstl:out value="${query8Header}"></jstl:out> --%>
<!-- 	</caption> -->
<!-- 	<tr> -->
<%-- 		<th><jstl:out value="${ratioHeader}"></jstl:out></th> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<%-- 		<td><jstl:out value="${query8}"></jstl:out></td> --%>
<!-- 	</tr> -->
<!-- </table> -->












<!-- <br /> -->