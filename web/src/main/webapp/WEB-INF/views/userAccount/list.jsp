<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>

<h4 class="header"><mytaglib:i18n key="userAccount" /></h4>

<div class="row">
	<div class="col s12 m10">
		<div class="card-panel blue lighten-5">
			<div class="row">
	
				<form:form  method="POST" action="${pagesUserAccount}" modelAttribute="searchFormModel">
					<div class="input-field col s4">
			
						<form:input path="email" type="text" />
						<label for="email">email</label>
					</div>

					<div class="col s4">
						<button class="btn waves-effect waves-light right" type="submit">
							Search
						</button>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>

<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pagesUserAccount}" column="id"><mytaglib:i18n key="table.column.id" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesUserAccount}"	column="email"><mytaglib:i18n key="table.column.email" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesUserAccount}"	column="role"><mytaglib:i18n key="table.column.role" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesUserAccount}"	column="first_name"><mytaglib:i18n key="table.column.firstName" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesUserAccount}"	column="last_name"><mytaglib:i18n key="table.column.lastName" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesUserAccount}"	column="fathers_name"><mytaglib:i18n key="table.column.fathersName" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesUserAccount}"	column="adress"><mytaglib:i18n key="table.column.adress" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesUserAccount}"	column="telephone"><mytaglib:i18n key="table.column.telephone" /></mytaglib:sort-link></th>			
			<th><mytaglib:sort-link pageUrl="${pagesUserAccount}"	column="created"><mytaglib:i18n key="table.column.created" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesUserAccount}"	column="updated"><mytaglib:i18n key="table.column.updated" /></mytaglib:sort-link></th>
			<th></th>
		</tr>
		<c:forEach var="userAccount" items="${gridItems}"
			varStatus="loopCounter">
			<tr>
			
				<td><c:out value="${userAccount.id}" /></td>
				<td><c:out value="${userAccount.email}" /></td>
				<td><c:out value="${userAccount.role}" /></td>
				<td><c:out value="${userAccount.firstName}" /></td>
				<td><c:out value="${userAccount.lastName}" /></td>
				<td><c:out value="${userAccount.fathersName}" /></td>
				<td><c:out value="${userAccount.adress}" /></td>
				<td><c:out value="${userAccount.telephone}" /></td>

				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${userAccount.created}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${userAccount.updated}" /></td>


				<td class="right"><a class="btn-floating"
					href="${pagesUserAccount}/${userAccount.id}"><i
						class="material-icons">info</i></a> <a class="btn-floating"
					href="${pagesUserAccount}/${userAccount.id}/edit"><i
						class="material-icons">edit</i></a> <a class="btn-floating red"
					href="${pagesUserAccount}/${userAccount.id}/delete"><i
						class="material-icons">delete</i></a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />
<a class="waves-effect waves-light btn right"
	href="${pagesUserAccount}/add"><i class="material-icons">add</i></a>
	
	
	
	
	<script>
    var latestId = '${newestUserAccountId}';

    setInterval(function() {
        $.get("${pagesUserAccount}/lastId", function(lastIdFromServer) {

            if (latestId < lastIdFromServer) {
                M.toast({html:'Someone created a new user account!!!'}) // simple popup message using Materialize framework
                latestId = lastIdFromServer;
            }
        })
    }, 5 * 1000);
</script>
	