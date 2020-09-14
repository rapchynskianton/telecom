<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>

<h4 class="header"><mytaglib:i18n key="editUserAccount" /></h4>

<div class="row">
	<form:form class="col s12" method="POST" action="${pagesUserAccount}/save"
		modelAttribute="formModel">

		<form:input path="id" type="hidden" />


		<div class="row">
			<div class="input-field col s12">
				<form:input path="email" type="text" disabled="${readonly}" />
				<form:errors path="email" cssClass="red-text" />
				<label for="email"><mytaglib:i18n key="table.column.email" /></label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:input path="userPassword" type="text" disabled="${readonly}" />
				<form:errors path="userPassword" cssClass="red-text" />
				<label for="userPassword"><mytaglib:i18n key="table.column.password" /></label>
			</div>
		</div>
         <div class="row">
            <div class="input-field col s12">
                 <form:select path="role" disabled="${readonly}">
                    <form:options items="${roleChoices}" />
                </form:select>
                <form:errors path="role" cssClass="red-text" />
                <label for="role"><mytaglib:i18n key="table.column.role" /></label>
            </div>
        </div>
				<div class="row">
			<div class="input-field col s12">
				<form:input path="firstName" type="text" disabled="${readonly}" />
				<form:errors path="firstName" cssClass="red-text" />
				<label for="firstName"><mytaglib:i18n key="table.column.firstName" /></label>
			</div>
		</div>
				<div class="row">
			<div class="input-field col s12">
				<form:input path="lastName" type="text" disabled="${readonly}" />
				<form:errors path="lastName" cssClass="red-text" />
				<label for="lastName"><mytaglib:i18n key="table.column.lastName" /></label>
			</div>
		</div>
				<div class="row">
			<div class="input-field col s12">
				<form:input path="fathersName" type="text" disabled="${readonly}" />
				<form:errors path="fathersName" cssClass="red-text" />
				<label for="fathersName"><mytaglib:i18n key="table.column.fathersName" /></label>
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
		
		<h4 class="header"><mytaglib:i18n key="passportDetails" />:</h4>
		
        <div class="row">
            <div class="input-field  col s12">
                <form:input path="info.serial" disabled="${readonly}" />
                <form:errors path="info.serial" cssClass="red-text" />
                <label for="info.description"><mytaglib:i18n key="table.column.serial" /></label>
            </div>
        </div>
        
        <div class="row">
            <div class="input-field  col s12">
                <form:input path="info.serialNumber" disabled="${readonly}" />
                <form:errors path="info.serialNumber" cssClass="red-text" />
                <label for="info.description"><mytaglib:i18n key="table.column.serialNumber" /></label>
            </div>
        </div>
        
 
        			<div class="input-field col s6">
				<form:input path="info.dateOfIssue" type="text" disabled="${readonly}" cssClass="datepicker" />
				<form:errors path="info.dateOfIssue" cssClass="red-text" />
				<label for="info.dateOfIssue"><mytaglib:i18n key="table.column.dateOfIssue" /></label>
			</div>
        
        <div class="row">
            <div class="input-field  col s12">
                <form:input path="info.identificationNumber" disabled="${readonly}" />
                <form:errors path="info.identificationNumber" cssClass="red-text" />
                <label for="info.description"><mytaglib:i18n key="table.column.identificationNumber" /></label>
            </div>
        </div>
        
                <div class="row">
            <div class="input-field  col s12">
                <form:input path="info.passportAuthority" disabled="${readonly}" />
                <form:errors path="info.passportAuthority" cssClass="red-text" />
                <label for="info.description"><mytaglib:i18n key="table.column.passportAuthority" /></label>
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
					href="${pagesUserAccount}"><mytaglib:i18n key="backToList" /><i
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

