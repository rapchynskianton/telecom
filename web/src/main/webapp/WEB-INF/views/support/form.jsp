<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<h4 class="header">
	<mytaglib:i18n key="editSupport" />
</h4>
<div class="row">
	<form:form class="col s12" method="POST" action="${pagesSupport}"
		modelAttribute="formModel">
		<form:input path="id" type="hidden" />

		<div class="row">
			<div class="input-field col s12">
				<form:input path="problemName" type="text" disabled="${readonly}" />
				<form:errors path="problemName" cssClass="red-text" />
				<label for="problemName"><mytaglib:i18n
						key="table.column.problemName" /></label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12">
				<form:input path="problem" type="text" disabled="${readonly}" />
				<form:errors path="problem" cssClass="red-text" />
				<label for="problem"><mytaglib:i18n
						key="table.column.problem" /></label>
			</div>
		</div>
			<div class="row">
				<div class="input-field col s12">
					<div class="switch">
						<label> free <form:checkbox path="status"
								disabled="${readonly}" /> <span class="lever"></span>
						<mytaglib:i18n key="deactivated" />
						</label>
					</div>
					<label class="switch-label"><mytaglib:i18n key="active" /></label>
					<br />
				</div>
			</div>

		<div class="row">
			<div class="input-field col s12">
				<form:select path="userCabinetId" disabled="${readonly}">
					<form:options items="${userCabinetChoices}" />
				</form:select>
				<form:errors path="userCabinetId" cssClass="red-text" />
				<label for="userCabinetId"><mytaglib:i18n
						key="table.column.userCabinet" /></label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:select path="userAccountId" disabled="${readonly}">
					<form:options items="${userAccountChoices}" />
				</form:select>
				<form:errors path="userAccountId" cssClass="red-text" />
				<label for="userAccountId"><mytaglib:i18n
						key="table.column.userAccount" /></label>
			</div>
		</div>


		<div class="row">
			<div class="col s6"></div>
			<div class="col s3">
				<c:if test="${!readonly}">
					<button class="btn waves-effect waves-light right" type="submit">
						<mytaglib:i18n key="save" />
					</button>
				</c:if>
			</div>
			<div class="col s3">
				<a class="btn waves-effect waves-light right" href="${pagesSupport}"><mytaglib:i18n
						key="backToList" /><i class="material-icons right"></i> </a>
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

