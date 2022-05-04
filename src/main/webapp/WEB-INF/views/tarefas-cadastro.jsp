<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro de Tarefas</title>

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
				<h4><strong>AgendaWeb</strong> Cadastro de Tarefas</h4>
				Preencha o formulário para incluir uma tarefa:
			</div>
		</div>
		
		<form id="formCadastro" action="cadastrar-tarefa" method="post" class="mb-3">
		
			<div class="row mb-3">
				<div class="col-md-6">
					<label>Nome da tarefa:</label>
					<form:input path="model.nome" id="nome" name="nome" type="text" class="form-control" placeholder="Digite o nome aqui."/>
				</div>
				<div class="col-md-2">
					<label>Data da tarefa:</label>
					<form:input path="model.data" id="data" name="data" type="date" class="form-control"/>
				</div>
				<div class="col-md-2">
					<label>Hora da tarefa:</label>
					<form:input path="model.hora" id="hora" name="hora" type="time" class="form-control"/>
				</div>
				<div class="col-md-2">
					<label>Prioridade:</label>
					<form:select path="model.prioridade" id="prioridade" name="prioridade" class="form-select">
						<option value="">Selecione</option>
						<option value="1">ALTA</option>
						<option value="2">MÉDIA</option>
						<option value="3">BAIXA</option>
					</form:select>
				</div>
			</div>
			
			<div class="mb-3">
				<label>Descrição da tarefa:</label>
				<form:textarea path="model.descricao" id="descricao" name="descricao" class="form-control" rows="4" placeholder="Digite a descrição aqui."></form:textarea>
			</div>
			
			<div class="mb-3">
			
				<input type="submit" value="Realizar Cadastro" class="btn btn-success"/>
				
				<a href="/agendaweb/tarefas-consulta" class="btn btn-secondary">
					Voltar para a consulta
				</a>
			
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
		
		$("#formCadastro").validate({
			
			//Regras de validação dos campos
			rules: {
									
				'nome' : {
					required : true
				},				
				'data' : {
					required : true
				},
				'hora' : {
					required : true
				},
				'prioridade' : {
					required : true
				},
				'descricao' : {
					required : true
				}
			},
			
			//Mensagens de validação dos campos
			messages: {				
				nome : {
					required : 'Por favor, informe o nome da tarefa.'
				},				
				data : {
					required: 'Por favor, selecione a data da tarefa.'
				},
				hora : {
					required: 'Por favor, selecione a hora da tarefa.'
				},
				prioridade : {
					required: 'Por favor, selecione a prioridade da tarefa.'
				},
				descricao : {
					required: 'Por favor, preencha a descrição da tarefa.'
				}
			}
			
		});
		
	})
	
	</script>
	
</body>
</html>





