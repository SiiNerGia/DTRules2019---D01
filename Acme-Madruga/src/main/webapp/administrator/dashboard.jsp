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
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<spring:message code="administrator.dashboard.avg" var="avgHeader" />
<spring:message code="administrator.dashboard.min" var="minHeader" />
<spring:message code="administrator.dashboard.max" var="maxHeader" />
<spring:message code="administrator.dashboard.std" var="stdHeader" />
<spring:message code="administrator.dashboard.ratio" var="ratioHeader" />
<spring:message code="administrator.dashboard.name" var="nameHeader" />
<spring:message code="administrator.dashboard.email" var="emailHeader" />

<spring:message code="administrator.dashboard.query1" var="query1Header" />
<spring:message code="administrator.dashboard.query2" var="query2Header" />
<spring:message code="administrator.dashboard.query3" var="query3Header" />
<spring:message code="administrator.dashboard.query4" var="query4Header" />
<spring:message code="administrator.dashboard.query5" var="query5Header" />
<spring:message code="administrator.dashboard.query6" var="query6Header" />
<spring:message code="administrator.dashboard.query7" var="query7Header" />
<spring:message code="administrator.dashboard.query8" var="query8Header" />
<spring:message code="administrator.dashboard.query9" var="query9Header" />
<spring:message code="administrator.dashboard.query10" var="query10Header" />
<spring:message code="administrator.dashboard.query11" var="query11Header" />
<spring:message code="administrator.dashboard.query12" var="query12Header" />
<spring:message code="administrator.dashboard.query13" var="query13Header" />
<spring:message code="administrator.dashboard.query14" var="query14Header" />
<spring:message code="administrator.dashboard.query15" var="query15Header" />

<spring:message code="administrator.dashboard.query1aExam" var="query1aExamHeader" />
<spring:message code="administrator.dashboard.query1bExam" var="query1bExamHeader" />
<spring:message code="administrator.dashboard.query2Exam" var="query2ExamHeader" />
<spring:message code="administrator.dashboard.query3Exam" var="query3ExamHeader" />



<!--  Cumtom table style -->
<head>
<link rel="stylesheet" href="styles/tablas.css" type="text/css">
</head>

<!-- Exam -->

<!-- Query 1a Customer -->
<table>
	<caption>
		<jstl:out value="${query1aExamHeader}"></jstl:out>
	</caption>
	<tr>
		<th><jstl:out value="${avgHeader}"></jstl:out></th>
		<th><jstl:out value="${stdHeader}"></jstl:out></th>
	</tr>

	<tr>
		<td><jstl:out value="${query1aExam[0]}"></jstl:out></td>
		<td><jstl:out value="${query1aExam[1]}"></jstl:out></td>
	</tr>
</table>
<br />

<!-- Query 1b Task -->
<table>
	<caption>
		<jstl:out value="${query1bExamHeader}"></jstl:out>
	</caption>
	<tr>
		<th><jstl:out value="${avgHeader}"></jstl:out></th>
		<th><jstl:out value="${stdHeader}"></jstl:out></th>
	</tr>

	<tr>
		<td><jstl:out value="${query1bExam[0]}"></jstl:out></td>
		<td><jstl:out value="${query1bExam[1]}"></jstl:out></td>
	</tr>
</table>
<br />

<!-- Query 2 Exam  -->
<table>
	<caption>
		<jstl:out value="${query2ExamHeader}"></jstl:out>
	</caption>
	<tr>
		<th><jstl:out value="${ratioHeader}"></jstl:out></th>
	</tr>
	<tr>
		<td><jstl:out value="${query2Exam}"></jstl:out></td>
	</tr>
</table>
<br />

<!-- Query 3 Exam  -->
<table>
	<caption>
		<jstl:out value="${query3ExamHeader}"></jstl:out>
	</caption>
	<tr>
		<th><jstl:out value="${ratioHeader}"></jstl:out></th>
	</tr>
	<tr>
		<td><jstl:out value="${query3Exam}"></jstl:out></td>
	</tr>
</table>
<br />


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
		<th><jstl:out value="${avgHeader}"></jstl:out></th>
		<th><jstl:out value="${minHeader}"></jstl:out></th>
		<th><jstl:out value="${maxHeader}"></jstl:out></th>
		<th><jstl:out value="${stdHeader}"></jstl:out></th>
	</tr>

	<tr>
		<td><jstl:out value="${query2[0]}"></jstl:out></td>
		<td><jstl:out value="${query2[1]}"></jstl:out></td>
		<td><jstl:out value="${query2[2]}"></jstl:out></td>
		<td><jstl:out value="${query2[3]}"></jstl:out></td>
	</tr>
</table>
<br />

<!-- Query 3  -->
<table>
	<caption>
		<jstl:out value="${query3Header}"></jstl:out>
	</caption>
	<tr>
		<th><jstl:out value="${avgHeader}"></jstl:out></th>
		<th><jstl:out value="${minHeader}"></jstl:out></th>
		<th><jstl:out value="${maxHeader}"></jstl:out></th>
		<th><jstl:out value="${stdHeader}"></jstl:out></th>
	</tr>

	<tr>
		<td><jstl:out value="${query3[0]}"></jstl:out></td>
		<td><jstl:out value="${query3[1]}"></jstl:out></td>
		<td><jstl:out value="${query3[2]}"></jstl:out></td>
		<td><jstl:out value="${query3[3]}"></jstl:out></td>
	</tr>
</table>
<br />

<!-- Query 4  -->
<table>
	<caption>
		<jstl:out value="${query4Header}"></jstl:out>
	</caption>
	<tr>
		<th><jstl:out value="${avgHeader}"></jstl:out></th>
		<th><jstl:out value="${minHeader}"></jstl:out></th>
		<th><jstl:out value="${maxHeader}"></jstl:out></th>
		<th><jstl:out value="${stdHeader}"></jstl:out></th>
	</tr>

	<tr>
		<td><jstl:out value="${query4[0]}"></jstl:out></td>
		<td><jstl:out value="${query4[1]}"></jstl:out></td>
		<td><jstl:out value="${query4[2]}"></jstl:out></td>
		<td><jstl:out value="${query4[3]}"></jstl:out></td>
	</tr>
</table>
<br />

<!-- Query 5  -->
<table>
	<caption>
		<jstl:out value="${query5Header}"></jstl:out>
	</caption>
	<tr>
		<th><jstl:out value="${ratioHeader}"></jstl:out></th>
	</tr>
	<tr>
		<td><jstl:out value="${query5}"></jstl:out></td>
	</tr>
</table>
<br />

<!-- Query 6  -->
<table>
	<caption>
		<jstl:out value="${query6Header}"></jstl:out>
	</caption>
	<tr>
		<th><jstl:out value="${ratioHeader}"></jstl:out></th>
	</tr>
	<tr>
		<td><jstl:out value="${query6}"></jstl:out></td>
	</tr>
</table>
<br />

<!-- Query 7  -->
<table>
	<caption>
		<jstl:out value="${query7Header}"></jstl:out>
	</caption>
	<tr>
		<th><jstl:out value="${ratioHeader}"></jstl:out></th>
	</tr>
	<tr>
		<td><jstl:out value="${query7}"></jstl:out></td>
	</tr>
</table>
<br />

<!-- Query 8  -->
<table>
	<caption>
		<jstl:out value="${query8Header}"></jstl:out>
	</caption>
	<tr>
		<th><jstl:out value="${ratioHeader}"></jstl:out></th>
	</tr>
	<tr>
		<td><jstl:out value="${query8}"></jstl:out></td>
	</tr>
</table>
<br />


<!-- Query 9  -->
<table>
	<caption>
		<jstl:out value="${query9Header}"></jstl:out>
	</caption>
	<tr>
		<th><jstl:out value="${nameHeader}"></jstl:out></th>
		<th><jstl:out value="${emailHeader}"></jstl:out></th>
	</tr>
	<jstl:forEach items="${query9}" var="customer">
		<tr>

			<td><jstl:out value="${customer.name}"></jstl:out></td>
			<td><jstl:out value="${customer.email}"></jstl:out></td>

		</tr>
	</jstl:forEach>
</table>
<br />


<!-- Query 10  -->
<table>
	<caption>
		<jstl:out value="${query10Header}"></jstl:out>
	</caption>
	<tr>
		<th><jstl:out value="${nameHeader}"></jstl:out></th>
		<th><jstl:out value="${emailHeader}"></jstl:out></th>
	</tr>
	<jstl:forEach items="${query10}" var="handyWorker">
		<tr>

			<td><jstl:out value="${handyWorker.name}"></jstl:out></td>
			<td><jstl:out value="${handyWorker.email}"></jstl:out></td>
		</tr>
	</jstl:forEach>
</table>


<!-- B level -->

<!-- Query 11 -->
<table>
	<caption>
		<jstl:out value="${query11Header}"></jstl:out>
	</caption>
	<tr>
		<th><jstl:out value="${avgHeader}"></jstl:out></th>
		<th><jstl:out value="${minHeader}"></jstl:out></th>
		<th><jstl:out value="${maxHeader}"></jstl:out></th>
		<th><jstl:out value="${stdHeader}"></jstl:out></th>
	</tr>

	<tr>
		<td><jstl:out value="${query11[0]}"></jstl:out></td>
		<td><jstl:out value="${query11[1]}"></jstl:out></td>
		<td><jstl:out value="${query11[2]}"></jstl:out></td>
		<td><jstl:out value="${query11[3]}"></jstl:out></td>
	</tr>
</table>
<br />

<!-- Query 12 -->
<table>
	<caption>
		<jstl:out value="${query12Header}"></jstl:out>
	</caption>
	<tr>
		<th><jstl:out value="${avgHeader}"></jstl:out></th>
		<th><jstl:out value="${minHeader}"></jstl:out></th>
		<th><jstl:out value="${maxHeader}"></jstl:out></th>
		<th><jstl:out value="${stdHeader}"></jstl:out></th>
	</tr>

	<tr>
		<td><jstl:out value="${query12[0]}"></jstl:out></td>
		<td><jstl:out value="${query12[1]}"></jstl:out></td>
		<td><jstl:out value="${query12[2]}"></jstl:out></td>
		<td><jstl:out value="${query12[3]}"></jstl:out></td>
	</tr>
</table>
<br />

<!-- Query 13  -->
<table>
	<caption>
		<jstl:out value="${query13Header}"></jstl:out>
	</caption>
	<tr>
		<th><jstl:out value="${ratioHeader}"></jstl:out></th>
	</tr>
	<tr>
		<td><jstl:out value="${query13}"></jstl:out></td>
	</tr>
</table>
<br />


<!-- Query 14  -->
<table>
	<caption>
		<jstl:out value="${query14Header}"></jstl:out>
	</caption>
	<tr>
		<th><jstl:out value="${nameHeader}"></jstl:out></th>
		<th><jstl:out value="${emailHeader}"></jstl:out></th>
	</tr>
	<jstl:forEach items="${query14}" var="customer">
		<tr>

			<td><jstl:out value="${customer.name}"></jstl:out></td>
			<td><jstl:out value="${customer.email}"></jstl:out></td>

		</tr>
	</jstl:forEach>
</table>
<br />

<!-- Query 15  -->
<table>
	<caption>
		<jstl:out value="${query15Header}"></jstl:out>
	</caption>
	<tr>
		<th><jstl:out value="${nameHeader}"></jstl:out></th>
		<th><jstl:out value="${emailHeader}"></jstl:out></th>
	</tr>
	<jstl:forEach items="${query15}" var="handyWorker">
		<tr>

			<td><jstl:out value="${handyWorker.name}"></jstl:out></td>
			<td><jstl:out value="${handyWorker.email}"></jstl:out></td>
		</tr>
	</jstl:forEach>
</table>








