<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
	
<h4 class="header"><mytaglib:i18n key="userCabinet" /></h4>

<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pagesUserCabinet}" column="id"><mytaglib:i18n key="table.column.id" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesUserCabinet}"	column="activation_date"><mytaglib:i18n key="table.column.actiovationDate" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesUserCabinet}"	column="status"><mytaglib:i18n key="table.column.status" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesUserCabinet}"	column="user_account_id"><mytaglib:i18n key="table.column.userAccount" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesUserCabinet}"	column="branch_id"><mytaglib:i18n key="branch" /></mytaglib:sort-link></th>
		      	<sec:authorize access="hasAnyRole('admin','manager')">	
			<th><mytaglib:sort-link pageUrl="${pagesUserCabinet}"	column="created"><mytaglib:i18n key="table.column.created" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesUserCabinet}"	column="updated"><mytaglib:i18n key="table.column.updated" /></mytaglib:sort-link></th>
				</sec:authorize>
			<th></th>
		</tr>
		<c:forEach var="userCabinet" items="${gridItems}"
			varStatus="loopCounter">
			<tr>
				<td><c:out value="${userCabinet.id}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
				value="${userCabinet.activationDate}" /></td>				
				<td><c:out value="${userCabinet.status}" /></td>
				<td><c:out value="${userCabinet.userAccountEmail}" /></td>
				<td><c:out value="${userCabinet.branchRegion}" /></td>
				<sec:authorize access="hasAnyRole('admin','manager')">	
					<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${userCabinet.created}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${userCabinet.updated}" /></td>
					</sec:authorize>
<td> <a class="waves-effect waves-light btn right"
	href="${pagesTransaction}/${userCabinet.id}/add"><i class="material-icons"><mytaglib:i18n key="topUpAccount" /></i></a></td>
	<sec:authorize access="hasAnyRole('admin','manager')">	
				<td class="right"><a class="btn-floating"
					href="${pagesUserCabinet}/${userCabinet.id}"><i
						class="material-icons">info</i></a> <a class="btn-floating"
					href="${pagesUserCabinet}/${userCabinet.id}/edit"><i
						class="material-icons">edit</i></a> <a class="btn-floating red"
					href="${pagesUserCabinet}/${userCabinet.id}/delete"><i
						class="material-icons">delete</i></a></td>
								</sec:authorize>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />
<sec:authorize access="hasAnyRole('admin','manager')">	
<a class="waves-effect waves-light btn right"
	href="${pagesUserCabinet}/add"><i class="material-icons">add</i></a>
		</sec:authorize>
