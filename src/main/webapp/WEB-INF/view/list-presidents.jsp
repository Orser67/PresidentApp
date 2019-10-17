<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>

<html>

<head>
	<title>List Presidents</title>
	
	<!-- reference our style sheet -->

	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/style.css" />

</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>List of Presidents</h2>
		</div>
	</div>
	
	<div id="container">
	
		<div id="content">
		
			<p>
				User: <security:authentication property="principal.username" />, Role(s): <security:authentication property="principal.authorities" />
			</p>
		

			<security:authorize access="hasAnyRole('MANAGER', 'ADMIN')">
			
				<!-- put new button: Add President -->
			
				<input type="button" value="Add President"
					   onclick="window.location.href='showFormForAdd'; return false;"
					   class="add-button"
				/>
			
			</security:authorize>
	
		
			<!--  add our html table here -->
		
			<table>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Party</th>
					<th>Term start</th>
					<th>Term end</th>
					
					<%-- Only show "Action" column for managers or admin --%>
					<security:authorize access="hasAnyRole('MANAGER', 'ADMIN')">
					
						<th>Action</th>
					
					</security:authorize>
					
				</tr>
				
				<!-- loop over and print the presidents -->
				<c:forEach var="tempPresident" items="${presidents}">
				
					<!-- construct an "update" link with president id -->
					<c:url var="updateLink" value="/president/showFormForUpdate">
						<c:param name="presidentId" value="${tempPresident.id}" />
					</c:url>					

					<!-- construct a "delete" link with president id -->
					<c:url var="deleteLink" value="/president/delete">
						<c:param name="presidentId" value="${tempPresident.id}" />
					</c:url>					
					
					<tr>
						<td> ${tempPresident.firstName} </td>
						<td> ${tempPresident.lastName} </td>
						<td> ${tempPresident.party} </td>
						<td> ${tempPresident.termStart} </td>
						<td> ${tempPresident.termEnd} </td>

						<security:authorize access="hasAnyRole('MANAGER', 'ADMIN')">
						
							<td>
								<security:authorize access="hasAnyRole('MANAGER', 'ADMIN')">
									<!-- display the update link -->
									<a href="${updateLink}">Update</a>
								</security:authorize>
	
								<security:authorize access="hasAnyRole('ADMIN')">
									<a href="${deleteLink}"
									   onclick="if (!(confirm('Are you sure you want to delete this president?'))) return false">Delete</a>
								</security:authorize>
							</td>

						</security:authorize>
												
					</tr>
				
				</c:forEach>
						
			</table>
				
		</div>
	
	</div>
	
	<p></p>
		
	<!-- Add a logout button -->
	<form:form action="${pageContext.request.contextPath}/logout" 
			   method="POST">
	
		<input type="submit" value="Logout" class="add-button" />
	
	</form:form>

</body>

</html>









