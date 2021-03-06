<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%-- The prefix shows up in the core tags you use --%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Projects</title>
	 <link rel="stylesheet" href="css/main.css">
</head>
<body>
	<h1>Projects</h1>
	<table>
	<tr><th>id</th><th>project name</th><th>leader</th><th>team members and ratings</th></tr>
	<c:forEach items="${projects}" var="project" varStatus="loop">
      <tr>
      	<td>${project.id}</td>
		<td>${project.name}</td>
		<td>${project.leader.firstName} ${project.leader.lastName}</td>
		<td>
			<ul>
			<%-- Key: Employee, Value: rating of employee as project member --%>
			<c:forEach var="projectMemberRating" items="${project.projectMembersRatings}">
				<li id="${projectMemberRating.key.id}-${project.id}">${projectMemberRating.key.firstName} ${projectMemberRating.key.lastName}: ${projectMemberRating.value}</li>
			</c:forEach>
			</ul>
			<hr>
			<a href="ChangeRating?projectId=${project.id}">Change Ratings</a>
		</td>
      </tr>
	</c:forEach>
	</table>
	<br><br>
</body>
</html>
