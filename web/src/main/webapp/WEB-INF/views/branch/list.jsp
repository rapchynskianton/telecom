<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<h4 class="header"><mytaglib:i18n key="branch" /></h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pagesBranch}" column="id"><mytaglib:i18n key="table.column.id" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesBranch}" column="region"><mytaglib:i18n key="table.column.region" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesBranch}" column="adress"><mytaglib:i18n key="table.column.adress" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesBranch}" column="telephone"><mytaglib:i18n key="table.column.telephone" /></mytaglib:sort-link></th>
			<sec:authorize access="hasRole('admin')">	
			<th><mytaglib:sort-link pageUrl="${pagesBranch}" column="created"><mytaglib:i18n key="table.column.created" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesBranch}" column="updated"><mytaglib:i18n key="table.column.updated" /></mytaglib:sort-link></th>
			</sec:authorize>
			
			<th></th>
		</tr>
		<c:forEach var="branch" items="${gridItems}"
			varStatus="loopCounter">
			<tr>
				<td><c:out value="${branch.id}" /></td>
				<td><c:out value="${branch.region}" /></td>
				<td><c:out value="${branch.adress}" /></td>
				<td><c:out value="${branch.telephone}" /></td>
			<sec:authorize access="hasRole('admin')">	

				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${branch.created}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${branch.updated}" /></td>


				<td class="right"><a class="btn-floating"
					href="${pagesBranch}/${branch.id}"><i
						class="material-icons">info</i></a> <a class="btn-floating"
					href="${pagesBranch}/${branch.id}/edit"><i
						class="material-icons">edit</i></a> <a class="btn-floating red"
					href="${pagesBranch}/${branch.id}/delete"><i
						class="material-icons">delete</i></a></td>
									</sec:authorize>
						
			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />
			<sec:authorize access="hasRole('admin')">	

<a class="waves-effect waves-light btn right"
	href="${pagesBranch}/add"><i class="material-icons">add</i></a>
										</sec:authorize>
	
