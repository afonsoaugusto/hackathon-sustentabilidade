<body>
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">Login</h1>
        </div>
    </div>

    <div class="row">
        <div class="msgs"></div>
    </div>

    <div class="row">
        <div class="col-lg-6">
            <form role="form" id="form">
                <div class="form-group">
                    <label for="email">E-mail:</label>
                    <input class="form-control"  name="email">
                </div>
                <div class="form-group">
                    <label for="password">Senha:</label>

                    <input name="password" type="password" class="form-control">
                </div>
                <div class="form-group">
                    <input type="button" class="btn btn-primary" id="btnSave" value="Entrar" />
                    <a href="/profile/edit/">
                        <input type="button" class="btn btn-link" id="btnSignIn" value="Cadastrar" />
                    </a>
                </div>
            </form>
        </div>
    </div>

    <script type="text/javascript" src="/resources/js/login/index.js"></script>

</body>