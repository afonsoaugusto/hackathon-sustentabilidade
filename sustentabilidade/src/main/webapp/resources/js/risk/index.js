function validate() {

  if (!$("#form").valid()) {
    return false;
  }

  if ($('textarea[name="descricao"]').val()) {
    return true;
  }
  return false;
}

$('#encostaNatural').click(function() {
  var isChecked = $(this).is(':checked');
  var radioChecked = $('input[name="anguloEncosta"]:checked');
  if (!isChecked) {
    if (radioChecked.length > 0) {
      radioChecked[0].checked = false;
    }
  } 
});


$('#aterro').click(function() {
  var isChecked = $(this).is(':checked');
  var radioChecked = $('input[name="anguloAterro"]:checked');
  if (!isChecked) {
    if (radioChecked.length > 0) {
      radioChecked[0].checked = false;
    }
  } 
});


$('.existeVazamento').on('change',function() {
  var value = $(this).val();
  var radioChecked = $('input[name="tipoVazamento"]:checked');
  if (value != "sim") {
    if (radioChecked.length > 0) {
      radioChecked[0].checked = false;
    }
  } 
});



$('#btnSave').click(function(e) {
  save();
});

$('#btnSaveLocation').click(function(e) {
  saveLocation();
});

function save() {
  clearAllError();
  if (validate()) {
    $.ajax({
      type : "POST",
      url : "/risk/save/",
      data : {
        description : $('textarea[name="descricao"]').val(),
        tipoMoradia : $('input[name="tipoMoradia"]:checked').val(),
        encosta : $('input[name="encosta"]').is(':checked'),
        aterro : $('input[name="aterro"]').is(':checked'),
        anguloEncosta : $('input[name="anguloEncosta"]:checked').val(),
        anguloAterro : $('input[name="anguloAterro"]:checked').val(),

        lixo : $('input[name="lixo"]').is(':checked'),
        concentracao : $('input[name="concentracao"]').is(':checked'),
        existeVazamento : $('input[name="existeVazamento"]:checked').val(),
        tipoVazamento : $('input[name="tipoVazamento"]:checked').val(),
        minasBarranco : $('input[name="minasBarranco"]:checked').val(),
        presencaArvore : $('input[name="presencaArvore"]').is(':checked'),
        areaDesmatada : $('input[name="areaDesmatada"]').is(':checked'),
        vegetacaoRasteira : $('input[name="vegetacaoRasteira"]').is(':checked'),
        areaCultivo : $('input[name="areaCultivo"]').is(':checked'),
        existeTrincas : $('input[name="existeTrincas"]').is(':checked'),
        tipoTrinca : $('input[name="tipoTrinca"]:checked').val(),
        numMoradias : $('input[name="numMoradias"]').val(),
        numRemocao : $('input[name="numRemocao"]').val(),
        outrasInformacoes : $('textarea[name="outrasInformacoes"]').val(),

      }
    }).done(function(data) {
      processAjax(data, function() {

        window.location = '/risk/uploadPhoto/';

      });

    });
  }
}

function saveLocation() {
  clearAllError();
  $.ajax({
    type : "POST",
    url : "/risk/saveLocation/",
    data : {
      longitude : posicaoAtual.lng(),
      latitude : posicaoAtual.lat()
    }
  }).done(function(data) {
    processAjax(data, function() {

      window.location = '/home/';

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
