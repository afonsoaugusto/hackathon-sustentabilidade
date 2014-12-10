function validate() {

  if (!$("#form").valid()) {
    return false;
  }

  if ($('input[name="password"]').val() == $('input[name="password1"]').val()) {
    return true;
  }

  $('<div/>', {
    text : "A confirmação de sua senha está errada.",
    class : "alert alert-danger"
  }).appendTo($('.msgs'));
  $.scrollTo({
    top : 0,
    left : 0
  });

  return false;

}

$('#btnSave').click(function(e) {
  save();
});

function save() {
  clearAllError();
  if (validate()) {
    $.ajax({
      type : "POST",
      url : "/profile/save/",
      data : {
        email : $('input[name="email"]').val(),
        password : $('input[name="password"]').val(),
        address : $('input[name="address"]').val(),
        name : $('input[name="name"]').val(),
      }
    }).done(function(data) {
      processAjax(data, function() {

        $('<div/>', {
          text : data.message,
          class : "alert alert-success"
        }).appendTo($('.msgs'));

          window.location = '/home/';

      });

    });
  }
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

function fill() {

  $.ajax({
    type : "POST",
    url : "/login/info/",
    data : {}
  }).done(function(data) {
    processAjax(data, function() {

      if (data.email != null) {
        $('input[name="name"]').val(data.name);
        $('input[name="address"]').val(data.address);
        $('input[name="email"]').val(data.email);
        $('input[name="email"]').attr('readonly', true);
      }

    });

  });
}

$(document).ready(function() {

  fill();

  $('#form').validate({
    rules : {
      name : {
        minlength : 2,
        required : true
      },
      email : {
        required : true,
        email : true
      },
      password : {
        minlength : 6,
        required : true
      },
      password1 : {
        minlength : 6,
        required : true
      },
      address : {
        minlength : 2,
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
