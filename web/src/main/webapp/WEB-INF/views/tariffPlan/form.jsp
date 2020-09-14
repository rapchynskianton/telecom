<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>

<h4 class="header">
	<mytaglib:i18n key="editTariffPlan" />
</h4>
<div class="row">
	<form:form class="col s12" method="POST" action="${pagesTariffPlan}"
		modelAttribute="formModel">
		<form:input path="id" type="hidden" />
		<div class="row">
			<div class="input-field col s12">
				<form:input path="name" type="text" disabled="${readonly}" />
				<form:errors path="name" cssClass="red-text" />
				<label for="name"><mytaglib:i18n key="name" /></label>
			</div>
		</div>

		<div class="row">
			<c:if test="${readonly}">
				<div class="input-field  col s12">
					<form:select path="attributeValueIds" disabled="${readonly}"
						multiple="true">
						<option value=""disabled ${emptyformModel.attributeValueIds? "selected":""}></option>
						<form:options items="${attributeValueChoices}" />
					</form:select>
					<form:errors path="attributeValueIds" cssClass="red-text" />
					<label for="attributeValueIds" class="multiselect-label"><mytaglib:i18n key="value" /></label>
				</div>
			</c:if>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:input path="costPerUnit" type="text" disabled="${readonly}" />
				<form:errors path="costPerUnit" cssClass="red-text" />
				<label for="costPerUnit"><mytaglib:i18n
						key="table.column.cos2t_per_unit" /></label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:input path="unit" type="text" disabled="${readonly}" />
				<form:errors path="unit" cssClass="red-text" />
				<label for="unit"><mytaglib:i18n key="table.column.unit" /></label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:input path="type" type="text" disabled="${readonly}" />
				<form:errors path="type" cssClass="red-text" />
				<label for="type"><mytaglib:i18n key="table.column.type" /></label>
			</div>
		</div>

		<div class="row">
			<c:if test="${!readonly}">
				<div class="row">
					<div class="col s12">
						<label for="nameAttribute" class="multiselect-label"><mytaglib:i18n
								key="selectAttributs" /></label>
						<form:select path="nameAttribute" cssClass="browser-default"
							disabled="${readonly}" />
					</div>
				</div>

				<div class="row">
					<div class="col s12">
						<label for="valueAttribute" class="multiselect-label"><mytaglib:i18n
								key="selectAttributsValue" /></label>
						<form:select path="valueAttribute" cssClass="browser-default"
							disabled="${readonly}" />
					</div>
				</div>
				
				<div class="row" hidden="">
				<form:select path="attributeValueIds" multiple="true">
					<form:options items="${attributeValueChoices}" /> </form:select>
				</div>
			</c:if>



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
				<a class="btn waves-effect waves-light right"
					href="${pagesTariffPlan}"><mytaglib:i18n key="backToList" /><i
					class="material-icons right"></i> </a>
			</div>
		</div>
	</form:form>
</div>




<script
	src="${pageContext.request.contextPath}/resources/js/init-combos.js"></script>
<script>
	initComboboxes('${pageContext.request.contextPath}');
</script>
