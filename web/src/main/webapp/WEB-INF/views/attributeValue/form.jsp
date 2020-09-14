<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>

<h4 class="header"><mytaglib:i18n key="editAttributeValue" /></h4>



<div class="row">
	<form:form class="col s12" method="POST" action="${pagesAttributeValue}/save"
		modelAttribute="formModel">
		<form:input path="id" type="hidden" />

          <div class="row" hidden="true">
            <div class="input-field col s12">
                <form:select path="attributeId" disabled="${readonly}"> 
                    <form:options items="${attributeChoices}" />
                </form:select> 
                <form:errors path="attributeId" cssClass="red-text" />
                <label for="attributeId"><mytaglib:i18n key="table.column.attribute" /></label>
            </div>
        </div>
        
		<div class="row">
			<div class="input-field col s12">
				<form:input path="value" type="text" disabled="${readonly}" />
				<form:errors path="value" cssClass="red-text" />
				<label for="value"><mytaglib:i18n key="table.column.value" /></label>
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
					href="${pagesAttributeValue}/${id}"><mytaglib:i18n key="backToList" /><i
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

