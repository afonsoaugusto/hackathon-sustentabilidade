<body>
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">Risco</h1>
        </div>
    </div>

    <div class="row">
        <div class="msgs"></div>
    </div>

    <div class="row">
        <div class="col-lg-6">
            <form>
                <div class="form-group">
                    <label for="image">Foto:</label> <img name="image" src="/risk/viewImage/" />
                </div>

                <div id="maps"></div>

                <div class="form-group">
                    <input type="button" class="btn btn-primary" id="btnSaveLocation" value="Salvar" />
                </div>

            </form>
        </div>
    </div>

    <style>
html,body,#maps {
	height: 300px;
	width: 300px;
	margin: 0px;
	padding: 0px
}
</style>

    <script src="https://maps.googleapis.com/maps/api/js?v=3.exp"></script>
    <script type="text/javascript" src="/resources/js/risk/index.js"></script>
    <script type="text/javascript" src="/resources/js/risk/maps.js"></script>
</body>