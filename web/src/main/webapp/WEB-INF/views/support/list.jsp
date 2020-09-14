<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<h4 class="header"><mytaglib:i18n key="support" /></h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pagesSupport}" column="id"><mytaglib:i18n key="table.column.id" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesSupport}" column="problem_name"><mytaglib:i18n key="table.column.problemName" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesSupport}" column="problem"><mytaglib:i18n key="table.column.problem" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesSupport}" column="status"><mytaglib:i18n key="table.column.status" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesSupport}" column="user_cabinet_id"><mytaglib:i18n key="table.column.userCabinet" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesSupport}" column="user_account_id"><mytaglib:i18n key="table.column.userAccount" /></mytaglib:sort-link></th>
	      	<sec:authorize access="hasAnyRole('admin','manager')">	
			<th><mytaglib:sort-link pageUrl="${pagesSupport}" column="created"><mytaglib:i18n key="table.column.created" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesSupport}" column="updated"><mytaglib:i18n key="table.column.updated" /></mytaglib:sort-link></th>
			</sec:authorize>
			<th></th>
		</tr>
		<c:forEach var="support" items="${gridItems}"
			varStatus="loopCounter">
			<tr>
				<td><c:out value="${support.id}" /></td>
				<td><c:out value="${support.problemName}" /></td>
				<td><c:out value="${support.problem}" /></td>
				<td><c:out value="${support.status}" /></td>
				<td><c:out value="${support.userCabinetId}" /></td>
				<td><c:out value="${support.userAccountEmail}" /></td>	
					      	<sec:authorize access="hasAnyRole('admin','manager')">	
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${support.created}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${support.updated}" /></td>



				<td class="right"><a class="btn-floating"
					href="${pagesSupport}/${support.id}"><i
						class="material-icons">info</i></a> <a class="btn-floating"
					href="${pagesSupport}/${support.id}/edit"><i
						class="material-icons">edit</i></a> <a class="btn-floating red"
					href="${pagesSupport}/${support.id}/delete"><i
						class="material-icons">delete</i></a></td>
			</sec:authorize>

			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />
<sec:authorize access="hasAnyRole('user')">	
<a class="waves-effect waves-light btn right"
	href="${pagesSupport}/add"><i class="material-icons">add</i></a>
	</sec:authorize>

