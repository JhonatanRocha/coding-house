<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:url value="/" var="contextPath" />
 <header id="layout-header">
	<div class="clearfix container">
		<a href="${s:mvcUrl('HC#index').build()}" id="logo">
		</a>
		<div id="header-content">
			<nav id="main-nav">
				
				<ul class="clearfix">
					<li>
						<a href="${s:mvcUrl('SCC#itens').build() }" rel="nofollow">
							<s:message code="menu.shoppingcart" arguments="${shoppingCart.quantity}"/>
						</a>
					</li>
					<li>
						<a href="/pages/sobre-a-casa-do-codigo" rel="nofollow">
							<fmt:message key="menu.about"/>
						</a>
					</li>
					<li>
						<a href="/pages/perguntas-frequentes" rel="nofollow">
							<fmt:message key="menu.faq"/>
						</a>
					</li>
					<li>
						<a href="?locale=en_US" rel="nofollow" title='<fmt:message key="menu.en"/>'>
							<img alt="" src="${contextPath}resources/imagens/us-flag.png" />
						</a>
					</li>
					<li>
						<a href="?locale=pt" rel="nofollow" title='<fmt:message key="menu.pt"/>'>
							<img alt="" src="${contextPath}resources/imagens/brazil.png" />
						</a>
					</li>
					<!-- <li>
						<a href="?locale=en_US" rel="nofollow">
							<fmt:message key="menu.en"/>
						</a>
					</li>
					<li>
						<a href="?locale=pt" rel="nofollow">
							<fmt:message key="menu.pt"/>
						</a>
					</li> -->
				</ul>
			</nav>
		</div>
	</div>
</header>
<nav class="categories-nav">
	<ul class="container">
		<li class="category">
			<a href="http://www.casadocodigo.com.br">
				<fmt:message key="navigation.category.home"/>
			</a>
		</li>
		<li class="category">
			<a href="/collections/livros-de-agile">
				<fmt:message key="navigation.category.agile"/>
			</a>
		</li>
		<li class="category">
			<a href="/collections/livros-de-front-end">
				<fmt:message key="navigation.category.front_end"/>
			</a>
		</li>
		<li class="category">
			<a href="/collections/livros-de-games">
				<fmt:message key="navigation.category.games"/>
			</a>
		</li>
		<li class="category">
			<a href="/collections/livros-de-java">
				<fmt:message key="navigation.category.java"/>
			</a>
		</li>
		<li class="category">
			<a href="/collections/livros-de-mobile">
				<fmt:message key="navigation.category.mobile"/>
			</a>
		</li>
		<li class="category">
			<a href="/collections/livros-desenvolvimento-web">
				<fmt:message key="navigation.category.web"/>
			</a>
		</li>
		<li class="category">
			<a href="/collections/outros">
				<fmt:message key="navigation.category.others"/>
			</a>
		</li>
	</ul>
</nav>