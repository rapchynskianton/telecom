<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<h4 class="header"><mytaglib:i18n key="transaction" /></h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pagesTransaction}" column="id"><mytaglib:i18n key="table.column.id" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesTransaction}" column="value"><mytaglib:i18n key="table.column.value" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesTransaction}" column="user_cabinet_id"><mytaglib:i18n key="table.column.userCabinet" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesTransaction}" column="description"><mytaglib:i18n key="table.column.description" /></mytaglib:sort-link></th>
	<sec:authorize access="hasAnyRole('admin','manager')">	
	 		<th><mytaglib:sort-link pageUrl="${pagesTransaction}" column="created"><mytaglib:i18n key="table.column.created" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesTransaction}" column="updated"><mytaglib:i18n key="table.column.updated" /></mytaglib:sort-link></th>
			</sec:authorize>
			<th></th>
		</tr>
		<c:forEach var="transaction" items="${gridItems}"
			varStatus="loopCounter">
			<tr>
				<td><c:out value="${transaction.id}" /></td>
				<td><c:out value="${transaction.value}" /></td>
				<td><c:out value="${transaction.userCabinetId}" /></td>
				<td><c:out value="${transaction.description}" /></td>
		<sec:authorize access="hasAnyRole('admin','manager')">				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${transaction.created}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${transaction.updated}" /></td>
			</sec:authorize>
				<td class="right"><a class="btn-floating"
					href="${pagesTransaction}/${transaction.id}"><i
						class="material-icons">info</i></a> <a class="btn-floating"
					href="${pagesTransaction}/${transaction.id}/edit"><i
						class="material-icons">edit</i></a> <a class="btn-floating red"
					href="${pagesTransaction}/${transaction.id}/delete"><i
						class="material-icons">delete</i></a></td>

			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />


