<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<h4 class="header">
	<mytaglib:i18n key="attribute" />
</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pagesAttribute}" column="id">
					<mytaglib:i18n key="table.column.id" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesAttribute}"
					column="name">
					<mytaglib:i18n key="name" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesAttribute}"
					column="created">
					<mytaglib:i18n key="table.column.created" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesAttribute}"
					column="updated">
					<mytaglib:i18n key="table.column.updated" />
				</mytaglib:sort-link></th>
			<th></th>
		</tr>

		<c:forEach var="attribute" items="${gridItems}"
			varStatus="loopCounter">


			<tr>
				<td><c:out value="${attribute.id}" /></td>
				<td><c:out value="${attribute.name}" /></td>

				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${attribute.created}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${attribute.updated}" /></td>

				<td><a class="waves-effect waves-light btn right"	href="${pagesAttributeValue}/${attribute.id}">
					<i class="material-icons">список значений</i></a></td>
				<td class="right">
				

					
					
				<a class="btn-floating"	href="${pagesAttribute}/${attribute.id}/edit"><i
						class="material-icons">edit</i></a>
				<a class="btn-floating red"	href="${pagesAttribute}/${attribute.id}/delete"><i
						class="material-icons">delete</i></a></td>
			</tr>

		</c:forEach>
	</tbody>
</table>

<jspFragments:paging />
<a class="waves-effect waves-light btn right"
	href="${pagesAttribute}/add"><i class="material-icons">add</i></a>
