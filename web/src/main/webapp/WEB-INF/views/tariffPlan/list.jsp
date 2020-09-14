<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<h4 class="header"><mytaglib:i18n key="tariffPlan" /></h4>
<table class="bordered highlight">
    <tbody>
        <tr>
            <th><mytaglib:sort-link pageUrl="${pagesTariffPlan}" column="id"><mytaglib:i18n key="table.column.id" /></mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesTariffPlan}" column="name"><mytaglib:i18n key="name" /></mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesTariffPlan}" column="cos2t_per_unit"><mytaglib:i18n key="table.column.cos2t_per_unit" /></mytaglib:sort-link></th>
             <th><mytaglib:sort-link pageUrl="${pagesTariffPlan}" column="unit"><mytaglib:i18n key="table.column.unit" /></mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesTariffPlan}" column="type"><mytaglib:i18n key="table.column.type" /></mytaglib:sort-link></th>
      	<sec:authorize access="hasRole('admin')">	
            <th><mytaglib:sort-link pageUrl="${pagesTariffPlan}" column="created"><mytaglib:i18n key="table.column.created" /></mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesTariffPlan}" column="updated"><mytaglib:i18n key="table.column.updated" /></mytaglib:sort-link></th>
            			</sec:authorize>
            
            <th></th>
        </tr>
        <c:forEach var="tariffPlan" items="${gridItems}" varStatus="loopCounter">
            <tr>
                <td><c:out value="${tariffPlan.id}" /></td>
                <td><c:out value="${tariffPlan.name}" /></td>
                <td><c:out value="${tariffPlan.costPerUnit}" /></td>
                <td><c:out value="${tariffPlan.unit}" /></td>
                <td><c:out value="${tariffPlan.type}" /></td>
                      	<sec:authorize access="hasRole('admin')">	
                <td><fmt:formatDate pattern="yyyy-MM-dd" value="${tariffPlan.created}" /></td>
                <td><fmt:formatDate pattern="yyyy-MM-dd" value="${tariffPlan.updated}" /></td>
                
                <td class="right"><a class="btn-floating" href="${pagesTariffPlan}/${tariffPlan.id}"><i class="material-icons">info</i></a> <a
                    class="btn-floating" href="${pagesTariffPlan}/${tariffPlan.id}/edit"><i class="material-icons">edit</i></a>  <a class="btn-floating red"
					href="${pagesTariffPlan}/${tariffPlan.id}/delete"><i
						class="material-icons">delete</i></a></td>
							</sec:authorize>
            </tr>
        </c:forEach>
    </tbody>
</table>
<jspFragments:paging />
<sec:authorize access="hasAnyRole('admin')">	
<a class="waves-effect waves-light btn right" href="${pagesTariffPlan}/add"><i class="material-icons">add</i></a>
	</sec:authorize>
