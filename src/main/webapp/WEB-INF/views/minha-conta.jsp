<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Minha Conta</title>

	<link
		href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
		rel="stylesheet" />
	
	<!-- estilo para os campos e mensagens de erro de validação -->
	<style>
		label.error { color: #d9534f; }
		input.error, select.error, textarea.error { border: 1px solid #d9534f; }
	</style>

</head>
<body>

	<!-- Menu do sistema -->
	<jsp:include page="/WEB-INF/views/components/menu.jsp"/>	
	<!-- notificações -->
	<jsp:include page="/WEB-INF/views/components/notifications.jsp"/>

	<div class="container mt-4">
		
		<div class="row mb-3">
			<div class="col-md-12">
				<h4><strong>AgendaWeb</strong> Minha Conta</h4>
				Informações do usuário autenticado na agenda:
			</div>
		</div>
		
		<div class="row mt-3">
			<div class="col-md-12">
				Nome do Usuário: <strong>${usuario_auth.nome}</strong>
			</div>
			<div class="col-md-12">
				Email de Acesso: <strong>${usuario_auth.email}</strong>
			</div>
		</div>
		
		<hr/>
		
		<div class="mt-3 mb-3">
			<h5>Altere sua senha de acesso:</h5>
		</div>
		
		<form id="formEdicao" action="atualizar-senha" method="post" class="mb-3">
		
			<div class="row mb-3">
				<div class="col-md-3">
					<label>Nova senha:</label>
					<form:input path="model.novaSenha" id="novaSenha" name="novaSenha" type="password" class="form-control" placeholder="Digite aqui."/>
				</div>
				<div class="col-md-3">
					<label>Confirme a nova senha:</label>
					<form:input path="model.novaSenhaConfirmacao" id="novaSenhaConfirmacao" name="novaSenhaConfirmacao" type="password" class="form-control" placeholder="Digite aqui."/>
				</div>
			</div>		
			
			<div class="row mb-3">
				<div class="col-md-3">
					<input type="submit" value="Atualizar Senha" class="btn btn-success"/>
				</div>
			</div>			
		
		</form>
		
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
		
		$("#formEdicao").validate({
			
			//Regras de validação dos campos
			rules: {									
				'novaSenha' : {
					required : true,
					minlength : 8,
					maxlength : 20
				},				
				'novaSenhaConfirmacao' : {
					required : true,
					equalTo : '#novaSenha'
				}
			},
			
			//Mensagens de validação dos campos
			messages: {				
				novaSenha : {
					required : 'Por favor, informe a nova senha.',
					minlength : 'Entre com no mínimo 8 caracteres.',
					maxlength : 'Entre com no máximo 20 caracteres'
				},				
				novaSenhaConfirmacao : {
					required: 'Por favor, confirme a nova senha.',
					equalTo : 'Senhas não conferem, por favor verifique.'
				}
			}			
		});		
	})
	
	</script>
	
</body>
</html>





