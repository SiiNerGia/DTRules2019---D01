<%--
 * header.jsp
 *
 * Copyright (C) 2019 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<div>
	<a href="#"><img src="https://gaceta.es/wp-content/uploads/madruga_1.jpg" alt="Acme-Madruga Co., Inc." width="1000" height="300" /></a>
</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		
	
		<!-- An actor who is authenticated as an ADMIN -->
		<security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv"><spring:message	code="master.page.administrator" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="administrator/list.do"><spring:message code="master.page.administrator.list" /></a></li>					
				</ul>
			</li>
		</security:authorize>
		
		
		<!-- An actor who is authenticated as a BROTHERHOOD -->
		<security:authorize access="hasRole('BROTHERHOOD')">
			<li><a class="fNiv"><spring:message	code="master.page.processions" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="procession/list.do"><spring:message code="master.page.procession.list" /></a></li>				
				</ul>
			</li>
		</security:authorize>
		
		
		<!-- An actor who is NOT authenticated -->
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv"><spring:message	code="master.page.brotherhood" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="brotherhood/list.do"><spring:message code="master.page.brotherhood.list" /></a></li>				
				</ul>
			</li>
			<li><a class="fNiv"><spring:message	code="master.page.register" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="brotherhood/create.do"><spring:message code="master.page.brotherhood.register" /></a></li>				
				</ul>
			</li>
			<li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a></li>
		</security:authorize>
		
		
		<!-- An actor who is authenticated -->
		<security:authorize access="isAuthenticated()">
			<li>
				<a class="fNiv"> 
					<spring:message code="master.page.profile" /> 
			        (<security:authentication property="principal.username" />)
				</a>
				<ul>
					<li class="arrow"></li>					
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
					<security:authorize access="hasRole('BROTHERHOOD')">
						<li><a href="brotherhood/edit.do">
							<spring:message code="master.page.brotherhood.edit" />
						</a></li>
					</security:authorize>
				</ul>
			</li>
		</security:authorize>
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

