$('#btnSave').click(function(e) {

	if ($("#form").valid()) {
		save();
	}

});

function save() {
	clearAllError();

	$.ajax({
		type : "POST",
		url : "/login/authenticate/",
		data : {
			email : $('input[name="email"]').val(),
			password : $('input[name="password"]').val(),
		}
	}).done(function(data) {
		processAjax(data, function() {

			$('<div/>', {
				text : data.message,
				class : "alert alert-success"
			}).appendTo($('.msgs'));

			setTimeout(function() {
				window.location = '/home/';
			}, 3000);

		});

	});
}

function clearAllError() {
	$('.error').removeClass('error');
	$('.msgs').html('');
}

function processAjax(data, fnSucess) {
	if (!data.error) {
		fnSucess(data);
	} else {

		$('<div/>', {
			text : data.message,
			class : "alert alert-danger"
		}).appendTo($('.msgs'));
		$.scrollTo({
			top : 0,
			left : 0
		});

		$(data.errorFields).each(function(i, field) {
			$field = $('#' + field);
			if ($field) {
				$field.addClass('error');
			}
		});
	}
}

$(document).ready(function() {

	$('#form').validate({
		rules : {
			email : {
				required : true,
				email : true
			},
			password : {
				required : true
			}
		},
		highlight : function(element) {
			$(element).addClass('error');
		},
		success : function(element) {
			element.addClass('valid');
		}
	});

});