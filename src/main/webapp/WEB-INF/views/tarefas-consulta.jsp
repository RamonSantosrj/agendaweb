<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<!-- biblioteca de tags do Spring -->
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>	

<!-- biblioteca de tags do JSTL -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
		
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Consulta de Tarefas</title>

	<!-- folha de estilos CSS do bootstrap -->
	<link
		href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
		rel="stylesheet" />
		
	<!-- folha de estilos CSS do JQuery do DataTables -->
	<link 
		href="//cdn.datatables.net/1.11.5/css/jquery.dataTables.min.css"
		rel="stylesheet" />
	
	<!-- estilo para os campos e mensagens de erro de validação -->
	<style>
		label.error { color: #d9534f; }
		input.error { border: 1px solid #d9534f; }
	</style>

</head>
<body>

	<!-- Menu do sistema -->
	<jsp:include page="/WEB-INF/views/components/menu.jsp"/>
	<!-- notificações -->
	<jsp:include page="/WEB-INF/views/components/notifications.jsp"/>

	<div class="container mt-4">
		
		<div class="row mb-3">
			<div class="col-md-6">
				<h4><strong>AgendaWeb</strong> Consulta de Tarefas</h4>
				Pesquise as tarefas desejadas:
			</div>
		</div>
		
		<form id="formConsulta" action="consultar-tarefas" method="get" class="mb-3">
		
			<div class="row">
				<div class="col-md-3">
					<form:input path="model.dataMin" id="dataMin" name="dataMin" type="date" class="form-control"/>
				</div>
				<div class="col-md-3">
					<form:input path="model.dataMax" id="dataMax" name="dataMax" type="date" class="form-control"/>
				</div>
				<div class="col-md-6">
				
					<input type="submit" class="btn btn-primary" value="Pesquisar Tarefas"/>
					
					<a href="/agendaweb/tarefas-cadastro" class="btn btn-success">
						Cadastrar Tarefa
					</a>
				</div>
			</div>
		
		</form>
		
		<c:if test="${tarefas.size() > 0}">
		
			<table id="tabela-tarefas" class="table table-hover table-striped">
			<thead>
				<tr>
					<th>Nome da tarefa</th>
					<th>Data</th>
					<th>Hora</th>
					<th>Prioridade</th>
					<th>Descrição</th>
					<th>Operações</th>
				</tr>
			</thead>
			<tbody>
				
				<c:forEach items="${tarefas}" var="t">
					
					<tr>
						<td>${t.nome}</td>
						<td><fmt:formatDate value="${t.data}" pattern="dd/MM/yyyy"/></td>
						<td>${t.hora}</td>
						<td>
							<c:if test="${t.prioridade == 1}">
								<span class="badge bg-danger">Alta</span>
							</c:if>
							<c:if test="${t.prioridade == 2}">
								<span class="badge bg-warning">Média</span>
							</c:if>
							<c:if test="${t.prioridade == 3}">
								<span class="badge bg-success">Baixa</span>
							</c:if>
						</td>
						<td>${t.descricao}</td>
						<td>
							<a href="/agendaweb/tarefas-edicao?id=${t.idTarefa}" 
							   class="btn btn-primary btn-sm">
								Editar
							</a>
							<a href="/agendaweb/tarefas-exclusao?id=${t.idTarefa}" 
							   onclick="return confirm('Deseja realmente excluir a tarefa: ${t.nome}?');"
							   class="btn btn-danger btn-sm">
								Excluir
							</a>
						</td>
					</tr>
					
				</c:forEach>
				
			</tbody>
			<tfoot>
				<tr>
					<td colspan="6">
						Quantidade de tarefas: <strong>${tarefas.size()}</strong>
					</td>
				</tr>
			</tfoot>
			</table>	
		
		</c:if>
		
	</div>
	
	<!-- JS do bootstrap -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	
	<!-- JS do jquery -->
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	
	<!-- JS do jquery validation (posicionados após a referencia do JQuery JS) -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.3/jquery.validate.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.3/additional-methods.min.js"></script>
	
	<!-- JS do jquery data tables -->
	<script src="//cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
	
	<script>
	
	$(document).ready(function(){
		
		//aplicando o datatables
		$('#tabela-tarefas').DataTable({
			language : {
				url : "//cdn.datatables.net/plug-ins/1.11.5/i18n/pt-BR.json"
			}
		});
		
		//aplicando o jquery validate
		$("#formConsulta").validate({
			
			//Regras de validação dos campos
			rules: {									
				'dataMin' : {
					required : true
				},				
				'dataMax' : {
					required : true
				}
			},
			
			//Mensagens de validação dos campos
			messages: {				
				dataMin : {
					required : 'Por favor, informe a data de início do período.'
				},				
				dataMax : {
					required : 'Por favor, informe a data de fim do período.'
				}
			}
			
		});
		
	})
	
	</script>
	
</body>
</html>




