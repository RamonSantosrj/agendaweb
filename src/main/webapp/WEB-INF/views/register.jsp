<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro de Usuários</title>

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
						<h3 class="mt-2">Crie sua Conta de Usuário</h3>
						<p>Preencha os campos para cadastrar sua conta!</p>
						<hr/>
					</div>
					
					<form id="formCadastro" action="cadastrar-usuario" method="post" autocomplete="off">
					
						<div class="mb-3">
							<label>Nome do usuário:</label>
							<form:input path="model.nome" name="nome" id="nome" type="text" class="form-control" placeholder="Digite seu nome aqui"/>
						</div>	
					
						<div class="mb-3">
							<label>Email de acesso:</label>
							<form:input path="model.email" name="email" id="email" type="text" class="form-control" placeholder="Digite seu email aqui"/>
							<span class="text-danger">${erro_email}</span>
						</div>
						
						<div class="mb-3">
							<label>Senha de acesso:</label>
							<form:input path="model.senha" name="senha" id="senha" type="password" class="form-control" placeholder="Digite sua senha aqui"/>
						</div>
						
						<div class="mb-3">
							<label>Confirme sua senha:</label>
							<form:input path="model.senhaConfirmacao" name="senhaConfirmacao" id="senhaConfirmacao" type="password" class="form-control" placeholder="Confirme sua senha aqui"/>
						</div>
						
						<div class="mb-3">
							<div class="d-grid">
								<input type="submit" value="Realizar Cadastro" class="btn btn-success"/>
							</div>
						</div>
						
						<div class="mb-3">
							<div class="d-grid">
								<a href="/agendaweb/" class="btn btn-light">
									Já possui conta? <strong>Acesse aqui!</strong>
								</a>
							</div>
						</div>
						
						<div class="text-center">
							<strong>${mensagem}</strong>
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
	
	<!-- código JS com JQuery -->
	<script>
		
		//quando a página carregar, faça..
		$(document).ready(function(){

			//escrevendo a validação do formulário
			$("#formCadastro").validate({
				
				//Regras de validação dos campos
				rules: {
					'nome' : {
						required : true,
						minlength : 8
					},
					
					'email' : {
						required : true,
						email : true
					},
					
					'senha' : {
						required : true,
						minlength : 8,
						maxlength : 20
					},
					
					'senhaConfirmacao' : {
						required : true,
						equalTo : '#senha'
					}
				},
				
				//Mensagens de validação dos campos
				messages: {
					nome : {
						required : 'Por favor, informe o nome do usuário.',
						minlength : 'Entre com no mínimo 8 caracteres.'
					},
					
					email : {
						required : 'Por favor, informe o email do usuário.',
						email : 'Entre com um endereço de email válido.'
					},
					
					senha : {
						required: 'Por favor, informe a senha do usuário.',
						minlength : 'Entre com no mínimo 8 caracteres.',
						maxlength : 'Entre com no máximo 20 caracteres'
					},
					
					senhaConfirmacao : {
						required: 'Por favor, confirme a senha do usuário.',
						equalTo : 'Senhas não conferem, por favor verifique.'
					},
				}
				
			})
			
		})
	
	</script>

</body>
</html>