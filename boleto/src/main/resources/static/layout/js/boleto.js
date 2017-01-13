$('#confirmacaoExclusaoModal').on('show.bs.modal', function(event) {
	var button = $(event.relatedTarget);
	
	var codigoBoleto = button.data('codigo');
	
	
	var descricaoBoleto = button.data('descricao');
	
	
	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');
//	var action = form.attr('action');
	if (!action.endsWith('/')) {
		action += '/';
	}
	form.attr('action', action + codigoBoleto);
	
	modal.find('.modal-body span').html('Tem certeza que deseja excluir o Cheque <strong>' + descricaoBoleto + '</strong>?');
});



$(function() {
	$('[rel="tooltip"]').tooltip();
	$('.js-currency').maskMoney({decimal: ',', thousands: '.', allowZero: true});
	
	
	$('.js-atualizar-status').on('click', function(event) {
		event.preventDefault();
		
		var botaoReceber = $(event.currentTarget);
		var urlReceber = botaoReceber.attr('href');
		
		var response = $.ajax({
			url: urlReceber,
			type: 'PUT'
		});
		
		
		response.done(function(e) {
			var codigoBoleto = botaoReceber.data('codigo');
			$('[data-role=' + codigoBoleto + ']').html('<span class="label label-success">' + e + '</span>');
			botaoReceber.hide();
		});
		
		response.fail(function(e) {
			console.log(e);
			alert('Erro recebendo cobrança');
		});
		
	});
});
