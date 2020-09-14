<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<h4 class="header">
	<mytaglib:i18n key="connectedTariffPlan" />
</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pagesConnectedTariffPlan}" column="id"><mytaglib:i18n key="table.column.id" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesConnectedTariffPlan}" column="user_cabinet_id"><mytaglib:i18n key="table.column.userCabinet" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesConnectedTariffPlan}" column="tariff_plan_id"><mytaglib:i18n key="table.column.tariffPlan" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesConnectedTariffPlan}" column="activation_date"><mytaglib:i18n key="table.column.actiovationDate" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesConnectedTariffPlan}" column="sum_cost"><mytaglib:i18n key="table.column.sumCost" /></mytaglib:sort-link></th>
		    <sec:authorize access="hasAnyRole('admin','manager')">	
			<th><mytaglib:sort-link pageUrl="${pagesConnectedTariffPlan}" column="created"><mytaglib:i18n key="table.column.created" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesConnectedTariffPlan}" column="updated"><mytaglib:i18n key="table.column.updated" /></mytaglib:sort-link></th>
			</sec:authorize>
			<th></th>
		</tr>

		<c:forEach var="connectedTariffPlan" items="${gridItems}"
			varStatus="loopCounter">
			<tr>
				<td><c:out value="${connectedTariffPlan.id}" /></td>
				<td><c:out value="${connectedTariffPlan.userCabinetId}" /></td>
				<td><c:out value="${connectedTariffPlan.tariffPlanName}" /></td>
				<td><c:out value="${connectedTariffPlan.activationDate}" /></td>
				<td><c:out value="${connectedTariffPlan.sumCost}" /></td>
				<sec:authorize access="hasAnyRole('admin','manager')">	
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${connectedTariffPlan.created}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${connectedTariffPlan.updated}" /></td>



				<td class="right"><a class="btn-floating"
					href="${pagesConnectedTariffPlan}/${connectedTariffPlan.id}"><i
						class="material-icons">info</i></a> <a class="btn-floating"
					href="${pagesConnectedTariffPlan}/${connectedTariffPlan.id}/edit"><i
						class="material-icons">edit</i></a> <a class="btn-floating red"
					href="${pagesConnectedTariffPlan}/${connectedTariffPlan.id}/delete"><i
						class="material-icons">delete</i></a></td>
													</sec:authorize>
						

			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />
<sec:authorize access="hasAnyRole('admin','manager')">	
<a class="waves-effect waves-light btn right"
	href="${pagesConnectedTariffPlan}/add"><i class="material-icons">add</i></a>
							</sec:authorize>
