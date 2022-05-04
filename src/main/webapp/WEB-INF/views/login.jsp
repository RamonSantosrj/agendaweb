<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

	<link
		href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
		rel="stylesheet"
	/>
	
	<!-- estilo para os campos e mensagens de erro de validação -->
	<style>
		label.error { color: #d9534f; }
		input.error { border: 1px solid #d9534f; }
	</style>

</head>
<body class="bg-secondary">

	<div class="row">
		<div class="col-md-4 offset-md-4">
			<div class="card mt-5">
				<div class="card-body">
					
					<div class="text-center">
					
						<img src="https://www.cotiinformatica.com.br/imagens/logo-coti-informatica.png" class="img-fluid"/>
					
						<h5 class="mt-2">Seja bem vindo a AgendaWeb</h5>
						<p>Informe seu dados para acessar o sistema!</p>
						<hr/>
					</div>
					
					<form id="formLogin" action="autenticar-usuario" method="post" autocomplete="off">
					
						<div class="mb-3">
							<label>Email de acesso:</label>
							<form:input path="model.email" name="email" id="email" type="text" class="form-control" placeholder="Digite seu email aqui"/>
						</div>
						
						<div class="mb-3">
							<label>Senha de acesso:</label>
							<form:input path="model.senha" name="senha" id="senha" type="password" class="form-control" placeholder="Digite sua senha aqui"/>
							<div class="text-end">
								<a href="/agendaweb/password">Esqueci minha senha</a>
							</div>
						</div>
						
						<div class="mb-3">
							<div class="d-grid">
								<input type="submit" value="Acessar Sistema" class="btn btn-success"/>
							</div>
						</div>
						
						<div class="text-center text-danger mb-3">
							<strong>${mensagem}</strong>
						</div>
						
						<div class="mb-3">
							<div class="d-grid">
								<a href="/agendaweb/register" class="btn btn-light">
									Ainda não possui conta? <strong>Cadastre-se aqui!</strong>
								</a>
							</div>
						</div>
					
					</form>
					
				</div>
			</div>
		</div>
	</div>

	<!-- JS do bootstrap -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	
	<!-- JS do jquery -->
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	
	<!-- JS do jquery validation (posicionados após a referencia do JQuery JS) -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.3/jquery.validate.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.3/additional-methods.min.js"></script>
	
	<script>
	
		$(document).ready(function(){
			
			$("#formLogin").validate({
				
				//Regras de validação dos campos
				rules: {
										
					'email' : {
						required : true,
						email : true
					},
					
					'senha' : {
						required : true
					}
				},
				
				//Mensagens de validação dos campos
				messages: {
					
					email : {
						required : 'Por favor, informe o email do usuário.',
						email : 'Entre com um endereço de email válido.'
					},
					
					senha : {
						required: 'Por favor, informe a senha do usuário.'
					}
				}
				
			});
			
		})
	
	</script>

</body>
</html>