<%--
 * action-1.jsp
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


<display:table name="mesage" id="row" requestURI="message/display.do"
	class="displaytag">
	<spring:message code="message.subject" var="subjectHeader" />
	<display:column property="subject" title="${subjectHeader}"
		sortable="false" />
</display:table>

<display:table name="mesage" id="row" requestURI="message/display.do"
	class="displaytag">
	<spring:message code="message.sender" var="senderHeader" />
	<display:column property="sender.email" title="${senderHeader}"
		sortable="false" />
</display:table>

<display:table name="mesageRecipients" id="row" requestURI="message/display.do"
	class="displaytag">
	<spring:message code="message.recipients" var="recipientHeader" />
	<display:column property="email" title="${recipientHeader}"
		sortable="false" />
</display:table>

<display:table name="mesage" id="row" requestURI="message/display.do"
	class="displaytag">
	<spring:message code="message.priority" var="priorityHeader" />
	<display:column property="priority" title="${priorityHeader}"
		sortable="false" />
</display:table>

<display:table name="mesage" id="row" requestURI="message/display.do"
	class="displaytag">
	<spring:message code="message.moment" var="momentHeader" />
	<display:column property="moment" title="${momentHeader}"
		sortable="false" />
</display:table>

<display:table name="mesage" id="row" requestURI="message/display.do"
	class="displaytag">
	<spring:message code="message.body" var="bodyHeader" />
	<display:column property="body" title="${bodyHeader}" sortable="false" />
</display:table>

<display:table name="mesage" id="row" requestURI="message/display.do"
	class="displaytag">
	<spring:message code="message.tags" var="tagsHeader" />
	<display:column title="${tagsHeader}">
		<jstl:forEach var="text" items="${row.tags}" varStatus="loop">
			${text}${!loop.last ? ',' : ''}&nbsp
		</jstl:forEach>
	</display:column>
</display:table>

<jstl:if test="${!empty messageBoxes}">
	<select id="actorBoxes">
		<jstl:forEach items="${messageBoxes}" var="msgBox">
			<option value="${msgBox.id}">${msgBox.name}</option>
		</jstl:forEach>
	</select>

	<a id="moveButton"
		href="message/move.do?messageID=${row.id}&srcBoxID=${messageBoxID}&destBoxID=">
		<spring:message code="message.move" />
	</a>

	<a id="copyButton"
		href="message/copy.do?messageID=${row.id}&srcBoxID=${messageBoxID}&destBoxID=">
		<spring:message code="message.copy" />
	</a>
	<br>
	<br>
</jstl:if>

<a
	href="message/delete.do?messageID=${row.id}&messageBoxID=${messageBoxID}">
	<spring:message code="message.delete" />
</a>
<input type="button" name="goBack"
	value="<spring:message code="message.goBack"/>"
	onclick="javascript: window.location.replace('message/list.do?messageBoxID=${messageBoxID}')" />

<script type="text/javascript">
	$(document).ready(function() {
		changeLink();
		$('#actorBoxes').change(function() {
			changeLink();
		});
	});

	function changeLink() {
		var box = document.getElementById("actorBoxes").selectedOptions[0].value;
		var strBox = box.toString();
		var moveUrl = "message/move.do?messageID=${row.id}&srcBoxID=${messageBoxID}&destBoxID=";
		var copyUrl = "message/copy.do?messageID=${row.id}&srcBoxID=${messageBoxID}&destBoxID=";
		document.getElementById("moveButton").setAttribute("href", moveUrl + strBox);
		document.getElementById("copyButton").setAttribute("href", copyUrl + strBox);
	}
</script>