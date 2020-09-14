<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>

<header>
	<ul id="dropdown1" class="dropdown-content">

		<sec:authorize access="hasRole('user')">
			<li><a href="${pagesUserCabinet}"><mytaglib:i18n
						key="userCabinet" /></a></li>

			<sec:authorize access="!isAnonymous()">
				<li><a href="${pagesSupport}"><mytaglib:i18n key="support" /></a></li>
			</sec:authorize>

		</sec:authorize>
		<li><a href="${contextPath}/execute_logout" title="logout"><mytaglib:i18n
					key="logout" /></a></li>
	</ul>


	<ul id="dropdown2" class="dropdown-content">
		<li><a href="${pagesTariffPlan}"><mytaglib:i18n
					key="tariffPlan" /></a></li>

		<sec:authorize access="hasAnyRole('admin', 'manager')">
			<li><a href="${pagesConnectedTariffPlan}"><mytaglib:i18n
						key="connectedTariffPlan" /></a></li>
		</sec:authorize>

		<sec:authorize access="hasRole('admin')">
			<li><a href="${pagesAttribute}"><mytaglib:i18n
						key="attribute" /></a></li>
		</sec:authorize>
	</ul>

	<ul id="dropdown3" class="dropdown-content">

	</ul>

	<nav>
		<div class="nav-wrapper container">
			<ul class="left hide-on-med-and-down">

				<li><a href="${pagesUserCabinet}"><mytaglib:i18n
							key="userCabinet" /></a></li>


				<li><a href="${pagesBranch}"><mytaglib:i18n key="branch" /></a></li>


				<sec:authorize access="hasAnyRole('admin', 'manager')">
					<li><a href="${pagesUserAccount}"><mytaglib:i18n
								key="userAccount" /></a></li>
				</sec:authorize>


				<li><a class="dropdown-trigger" href="#!"
					data-target="dropdown2"><mytaglib:i18n key="tariffPlan" /><i
						class="material-icons right">arrow_drop_down</i></a></li>

				<sec:authorize access="hasRole('admin')">
					<li><a href="${pagesTransaction}"><mytaglib:i18n
								key="transaction" /></a></li>
				</sec:authorize>

				<li><a href="${contextPath}?language=ru">RU</a></li>
				<li><a href="${contextPath}?language=en">EN</a></li>
			</ul>

		</div>

		<div align="right" class="nav-wrapper container">
			<ul class="right hide-on-med-and-down">

				<li><sec:authorize access="isAnonymous()">
						<div class="mydivv">
							<a href="${contextPath}/login"><mytaglib:i18n key="login" />
								<i class="material-icons right">input</i> </a>
						</div>
					</sec:authorize></li>


				<sec:authorize access="!isAnonymous()">
					<div class="nav-wrapper">
						<ul class="right hide-on-med-and-down">
							<li><a class="dropdown-trigger" href="#!"
								data-target="dropdown1"><sec:authentication
										property="principal" /><i class="material-icons right">arrow_drop_down</i></a></li>
						</ul>
					</div>
				</sec:authorize>


			</ul>
		</div>
	</nav>
</header>