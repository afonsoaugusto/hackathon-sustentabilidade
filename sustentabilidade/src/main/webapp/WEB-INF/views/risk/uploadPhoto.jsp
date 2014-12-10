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
            <form action='<%= com.google.appengine.api.blobstore.BlobstoreServiceFactory.getBlobstoreService().createUploadUrl("/risk/upload/") %>' method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="upload">Foto:</label> 
                    <input name="upload" type="file" />
                </div>
                <input type="submit" value="Submit">
            </form>
        </div>
    </div>

    <script type="text/javascript" src="/resources/js/risk/index.js"></script> 
</body>