var DY = {};

DY.post = function(url, param, success, error) {
	
	$.post(url, param, function(data, status) {
			
			var ajaxDTO = data;
			
			if (!ajaxDTO.success) {
				
				if ('302' == ajaxDTO.code) {
					window.location.href='/user/toLogin.do';
					return false;
				}
				
				error(ajaxDTO, status);
				
				return false;
			}
			
			success(ajaxDTO, status);
	});		
	
}

DY.alert = function (confirm, message, url) {
	
	if (confirm) {
		$('#my-alert').on('close.modal.amui', function() {
			window.location.href= url || "/user/toUserCenter.do";
		});
		
		$('#successAlet').html(message || '操作成功');
		$('#my-alert').modal('toggle');
	} else {
		$('#errorAlert').html(message || '操作失败');
		$('#your-modal').modal('toggle');
	}
	
}

$(document).ready(function() {
	$('input[type=text]').each(function(){
	    $(this).val('');
	});
});

