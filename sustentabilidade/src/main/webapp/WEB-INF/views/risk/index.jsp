<body>
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">Risco</h1>
        </div>
    </div>

     <div class="row">
        <div class="msgs">
        </div>
    </div>

    <div class="row">
        <div class="col-lg-6">
            <form role="form" id="form" class="form-horizontal">
                <div class="form-group">
                    <label for="name">Name:</label>
                    <span><%= ((com.ciandt.hackathon.sustentability.model.ProfileEntity)session.getAttribute("login")).getName()  %></span>
                </div>
                <div class="form-group">
                    <label for="Data">Data Atual:</label>
                    <span><%=  new java.text.SimpleDateFormat("dd/mm/yyyy").format(new java.util.Date())  %></span>
                </div>
                <div class="form-group">
                    <label for="descricao">Descrição:</label>
                    <textarea name="descricao" class="form-control" id="descricao"></textarea>
                </div>
                
                <div class="form-group">
                    <label for="tipoMoradia">Tipos de Moradia:</label> <br/>
                    <input name="tipoMoradia" type="radio" checked value="naoinformado">Não Informado
                    <input name="tipoMoradia" type="radio"  value="alvenaria">Alvenaria
                    <input name="tipoMoradia" type="radio"  value="madeira">Madeira
                    <input name="tipoMoradia" type="radio"  value="misto">Misto(Alvenaria e Madeira)
                </div>
                <div class="form-group">
                    <input  type="checkbox" name="encosta" id="encostaNatural"/>
                        <label for="encostaNatural">Encosta natural:</label> <br/>
                        Inclusão (marque o desenho que apresenta a condição mais parecida com a situação) <br/>
                        
                        <input name="anguloEncosta" type="radio" value="90" style="margin-left: 50px;">
                        <input name="anguloEncosta" type="radio"  value="60" style="margin-left: 100px;">
                        <input name="anguloEncosta" type="radio"  value="30" style="margin-left: 100px;">
                        <input name="anguloEncosta" type="radio"  value="17" style="margin-left: 100px;">
                        <input name="anguloEncosta" type="radio"  value="10" style="margin-left: 100px;">
                        <br/>
                        <img src="/resources/img/Task7.png">
                         
                </div>
                
               <div class="form-group">
                    <input type="checkbox" name="aterro" id="aterro"/>
                        <label for="aterroLancado">Aterro Lançado:</label> <br/>
                        Inclusão (marque o desenho que apresenta a condição mais parecida com a situação) <br/>
                        <input name="anguloAterro" type="radio" value="90" style="margin-left: 50px;">
                        <input name="anguloAterro" type="radio"  value="60" style="margin-left: 100px;">
                        <input name="anguloAterro" type="radio"  value="30" style="margin-left: 100px;">
                        <input name="anguloAterro" type="radio"  value="17" style="margin-left: 100px;">
                        <input name="anguloAterro" type="radio"  value="10" style="margin-left: 100px;">
                        <img src="/resources/img/Task8.png">
                         
                </div>
                
                <div class="form-group">
                    <input type="checkbox" name="lixo"/>
                    <label for="lixo">Presença de lixo/entulho</label>                
                </div>
                
                <div class="form-group">
                    <input type="checkbox" name="concentracao"/>
                    <label for="concentracao">Concentração de água de chuva em superficie (enxurrada)</label>      
                </div>
                
                <div>
                    <label for="existeVazamento">Existe vazamento na tubulação?</label> <br/>
                    <input name="existeVazamento" type="radio" checked value="naoinformado" class="existeVazamento">Não Informado
                    <input name="existeVazamento" type="radio"  value="sim" class="existeVazamento">Sim (
                    <span>
                        <input name="tipoVazamento" type="radio"  value="esgoto">esgoto
                        <input name="tipoVazamento" type="radio"  value="agua">água
                    </span>
                    )
                    <input name="existeVazamento" type="radio"  value="nao" class="existeVazamento">Não          
                </div>

                <div>
                    <label for="minasBarranco">Minas d'água no barranco (talude)</label> <br/>
                    <input name="minasBarranco" type="radio"  checked value="naoinformado">Não Informado
                    <input name="minasBarranco" type="radio"  value="nope">no pé
                    <input name="minasBarranco" type="radio"  value="nomeio">no meio
                    <input name="minasBarranco" type="radio"  value="topo">Topo do talude ou aterro          
                </div>


                <div class="form-group">
                    <div class="row-fluid">
                        <div class="span6">
                                <input type="checkbox" name="presencaArvore"/>
                                <label for="presencaArvore">Presença de árvores</label><br/>
                                <input type="checkbox" name="areaDesmatada"/>
                                <label for="areaDesmatada">Área Desmatada</label>
                        </div>
                        <div class="span6">
                                <input type="checkbox" name="vegetacaoRasteira"/>
                                <label for="vegetacaoRasteira">Vegetação rasteira (arbustos, capim, etc..)</label><br/>
                                <input type="checkbox" name="areaCultivo"/>
                                <label for="areaCultivo">Área de Cultivo (exemplo: banana, horta, etc...)</label>
                        </div>
                    </div>
                       
                </div>
                
               <div class="form-group">
                    <input type="checkbox" name="existeTrincas"/>
                    <label for="existeTrincas">Trincas</label> <br/>
                        <input name="tipoTrinca" type="radio" checked value="naoInformado" >Não informado
                        <input name="tipoTrinca" type="radio"  value="noTerreno" >no terreno
                        <input name="tipoTrinca" type="radio"  value="naMoradia" >na moradia <br/>
                        <img src="/resources/img/Task12.png">
                </div>
                
                
               <div class="form-group">
                    <label for="numMoradias">Número de moradias em risco:</label>
                    <input name="numMoradias" type="number">
                    <br/>
                    <label for="numRemocao">Estimativa do número de pessoas para remoção:</label>
                    <input name="numRemocao" type="number">
                    <br/>
                    <label for="outrasInformacoes">Outras informaões:</label>
                    <textarea name="outrasInformacoes"></textarea>
                </div>
                
                

                <div class="form-group">
                    <input type="button" class="btn btn-primary" id="btnSave" value="Salvar" />
                </div> 
            </form>
        </div>
    </div>

    <script type="text/javascript" src="/resources/js/risk/index.js"></script> 
</body>