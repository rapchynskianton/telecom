<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>

<h4 class="header">
	<mytaglib:i18n key="attributeValue" />
</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pagesAttributeValue}"
					column="id">
					<mytaglib:i18n key="table.column.id" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesAttributeValue}"
					column="value">
					<mytaglib:i18n key="table.column.value" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesAttributeValue}"
					column="created">
					<mytaglib:i18n key="table.column.created" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesAttributeValue}"
					column="updated">
					<mytaglib:i18n key="table.column.updated" />
				</mytaglib:sort-link></th>
			<th></th>
		</tr>
		<c:forEach var="attributeValue" items="${gridItems}"
			varStatus="loopCounter">


			<tr>
				<td><c:out value="${attributeValue.id}" /></td>
				<td><c:out value="${attributeValue.value}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${attributeValue.created}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${attributeValue.updated}" /></td>



				<td class="right"><a class="btn-floating"
					href="${pagesAttributeValue}/${attributeValue.id}/edit"><i
						class="material-icons">edit</i></a> <a class="btn-floating red"
					href="${pagesAttributeValue}/${id}/${attributeValue.id}/delete"><i
						class="material-icons">delete</i></a></td>

			</tr>


		</c:forEach>
	</tbody>
</table>




<jspFragments:paging />
<div class="row">
	<div class="col s6"></div>
	<div class="col s3">
		<a class="btn waves-effect waves-light right" href="${pagesAttribute}"><mytaglib:i18n
				key="backToList" /><i class="material-icons right"></i> </a>
	</div>
	<div class="col s3">
		<a class="waves-effect waves-light btn right"
			href="${pagesAttributeValue}/${id}/add"><i class="material-icons">add</i></a>
	</div>
</div>
