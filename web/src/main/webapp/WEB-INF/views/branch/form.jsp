<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<h4 class="header"><mytaglib:i18n key="editBranch" /></h4>
<div class="row">
	<form:form class="col s12" method="POST" action="${pagesBranch}"
		modelAttribute="formModel">
		<form:input path="id" type="hidden" />


		<div class="row">
			<div class="input-field col s12">
				<form:input path="region" type="text" disabled="${readonly}" />
				<form:errors path="region" cssClass="red-text" />
				<label for="region"><mytaglib:i18n key="table.column.region" /></label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:input path="adress" type="text" disabled="${readonly}" />
				<form:errors path="adress" cssClass="red-text" />
				<label for="adress"><mytaglib:i18n key="table.column.adress" /></label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:input path="telephone" type="text" disabled="${readonly}" />
				<form:errors path="telephone" cssClass="red-text" />
				<label for="telephone"><mytaglib:i18n key="table.column.telephone" /></label>
			</div>
		</div>

		<div class="row">
			<div class="col s6"></div>
			<div class="col s3">
				<c:if test="${!readonly}">
					<button class="btn waves-effect waves-light right" type="submit"><mytaglib:i18n key="save" /></button>
				</c:if>
			</div>
			<div class="col s3">
				<a class="btn waves-effect waves-light right" href="${pagesBranch}"><mytaglib:i18n key="backToList" /><i class="material-icons right"></i>
				</a>
			</div>
		</div>

	</form:form>
</div>


<c:if test='${param["showAlerts"]}'>
	<!-- checks the URL parameter -->


	<script src="${contextPath}/resources/js/sample-alert-with-params.js"></script>
	<script>
		showMessage('${contextPath}');
	</script>

</c:if>

