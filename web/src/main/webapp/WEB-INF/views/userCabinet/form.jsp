<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<h4 class="header"><mytaglib:i18n key="editUserCabinet" /></h4>
<div class="row">
	<form:form class="col s12" method="POST" action="${pagesUserCabinet}"
		modelAttribute="formModel">
		<form:input path="id" type="hidden" />


			<div class="input-field col s6">
				<form:input path="activationDate" type="text" disabled="${readonly}" cssClass="datepicker" />
				<form:errors path="activationDate" cssClass="red-text" />
				<label for="activationDate"><mytaglib:i18n key="table.column.actiovationDate" /></label>
			</div>


		
		<div class="row">
			<div class="input-field col s12">
				<div class="switch">
					<label> free <form:checkbox path="status" disabled="${readonly}" /> <span
						class="lever"></span><mytaglib:i18n key="deactivated" />
					</label>
				</div>
				<label class="switch-label"><mytaglib:i18n key="active" /></label> <br />
			</div>
		</div>
        <div class="row">
            <div class="input-field col s12">
                <form:select path="userAccountId" disabled="${readonly}">
                    <form:options items="${userAccountChoices}" />
                </form:select>
                <form:errors path="userAccountId" cssClass="red-text" />
                <label for="userAccountId"><mytaglib:i18n key="userAccount" /></label>
            </div>
        </div>
        
        <div class="row">
            <div class="input-field col s12">
                <form:select path="branchId" disabled="${readonly}">
                    <form:options items="${branchChoices}" />
                </form:select>
                <form:errors path="branchId" cssClass="red-text" />
                <label for="branchId"><mytaglib:i18n key="branch" /></label>
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
				<a class="btn waves-effect waves-light right"
					href="${pagesUserCabinet}"><mytaglib:i18n key="backToList" /><i
					class="material-icons right"></i>
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


