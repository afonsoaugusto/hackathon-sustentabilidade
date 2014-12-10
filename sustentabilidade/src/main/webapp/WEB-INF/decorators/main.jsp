<!DOCTYPE html>
<html lang="en">

  <head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title><sitemesh:write property='title'/></title>
    <sitemesh:write property='head'/>

    <!-- Bootstrap core CSS -->
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet"> 
      
    <!-- <link href="http://getbootstrap.com/2.3.2/assets/css/bootstrap.css" rel="stylesheet">  -->

    <!-- Custom CSS for the 'Application' -->
    <link href="/resources/css/base.css" rel="stylesheet">

    <!-- JavaScript -->
    <script src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
    <script src="http://datatables.net/release-datatables/media/js/jquery.dataTables.min.js"></script>
    <script src="/resources/js/jquery.validate.js"></script>
    <script src="/resources/js/jquery.scrollTo-min.js"></script>

    <script src="/resources/js/base.js"></script>

  </head>

  <body >
    <nav class="navbar navbar-fixed-top navbar-inverse" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="/home/">Área de Risco</a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse navbar-ex1-collapse">
          

          <% if( session.getAttribute("login") != null ){ %>
          <ul class="nav navbar-nav">
            <li><a href="/risk/index/">Risco</a></li>
            <li><a href="/risk/areas/">Áreas de Risco</a></li>
          </ul>
          <ul class="nav navbar-nav navbar-right navbar-user">
            <li class="dropdown user-dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> <%= session.getAttribute("login") == null ? "" :  ((com.ciandt.hackathon.sustentability.model.ProfileEntity)session.getAttribute("login")).getName()  %> <b class="caret"></b></a>
              <ul class="dropdown-menu">
                <li><a href="/profile/edit/"><i class="fa fa-user"></i> Perfil </a></li>
                <li class="divider"></li>
                <li><a href="/login/logout/"><i class="fa fa-power-off"></i> Log Out </a></li>
              </ul>
            </li>
          </ul>
          <% } %>

          <% if( session.getAttribute("login") == null ){ %>
          <ul class="nav navbar-nav navbar-right navbar-user">
            <li><a href="/login/"> Entrar </a></li>
          </ul>
          <% } %>

        </div><!-- /.navbar-collapse -->
      </div><!-- /.container -->
    </nav>

    <div class="container">
        <p>&nbsp;</p>

      <sitemesh:write property='body'/>

      <hr>
      <footer>
        <div class="row">
          <div class="col-lg-12">
            <p>Copyright &copy; CI&amp;T Hackathon 2014 </p>
          </div>
        </div>
      </footer>
    </div> <!-- /container -->
  </body>
</html>