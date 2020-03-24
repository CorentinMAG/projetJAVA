<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/common/head.jsp"%>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <!-- Left side column. contains the logo and sidebar -->
    <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                Utilisateurs
            </h1>
        </section>
        
        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-md-12">
                    <!-- Horizontal Form -->
                    <div class="box">
                        <!-- form start -->
                        <!-- Le  type de methode http qui sera appel� lors de action submit du formulaire -->
                        <!-- est d�crit an l'attribut "method" de la balise forme -->
                        <!-- action indique � quel "cible" sera envoyr la requ�te, ici notre Servlet qui sera bind sur -->
                        <!-- /vehicles/create -->
                        <form class="form-horizontal" method="post" action="/RentManager/users/create">
                        <%
                                if(request.getSession().getAttribute("erroraddclient")!=null){ %>
                            	<p class="alert alert-warning"><%=request.getSession().getAttribute("erroraddclient")%></p>
                               <% }else{} %>
                            <div class="box-body">
                            	<div class="form-group">
                                    <label for="last_name" class="col-sm-2 control-label">Nom</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="last_name" name="last_name" placeholder="Nom" required>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="first_name" class="col-sm-2 control-label">Prenom</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="first_name" name="first_name" placeholder="Prenom" required>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="email" class="col-sm-2 control-label">Email</label>

                                    <div class="col-sm-10">
                                        <input type="email" class="form-control" id="email" name="email" placeholder="Email" required>
                                    </div>
                            </div>
                            <div class="form-group">
                                    <label for="naissance" class="col-sm-2 control-label">date de naissance</label>

                                    <div class="col-sm-10">
                                        <input type="date" class="form-control" id="naissance" name="naissance" placeholder="yyyy-mm-dd" required>
                                    </div>
                            </div>
                            <!-- /.box-body -->
                            <div class="box-footer">
                            <span id="errorNom"></span>
                            <span id="errorPrenom"></span>
                            <span id="errorMail"></span>
                            <span id="errorNaissance"></span>
                            <button type="submit" class="btn btn-info pull-right">Ajouter</button>
                            </div>
                            <!-- /.box-footer -->
                        </form>
                    </div>
                    <!-- /.box -->
                </div>
                <!-- /.col -->
            </div>
        </section>
        <!-- /.content -->
    </div>

    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<!-- ./wrapper -->
<script>
let button = document.querySelector('button');
let input_date=document.querySelector('#naissance');
let input_nom = document.querySelector('#last_name');
let input_prenom=document.querySelector('#first_name');
let input_email=document.querySelector('#email');
let span_nom=document.querySelector('#errorNom');
let span_prenom=document.querySelector('#errorPrenom');
let span_mail=document.querySelector('#errorMail');
let span_naissance=document.querySelector('#errorNaissance');
input_nom.addEventListener('input',(e)=>{
	if(e.target.value.length<3){
	span_nom.classList="alert alert-warning";
	button.disabled=true;
	span_nom.textContent="Le nom doit comporter plus de 3 caracteres !";
	}else{
	span_nom.classList="alert alert-success";
	button.disabled=false;
	span_nom.textContent="Nom valide !";
	}

})
input_prenom.addEventListener('input',(e)=>{
	if(e.target.value.length<3){
	span_prenom.classList="alert alert-warning";
	button.disabled=true;
	span_prenom.textContent="Le prenom doit comporter plus de 3 caracteres !";
	}else{
	span_prenom.classList="alert alert-success";
	button.disabled=false;
	span_prenom.textContent="Prenom valide !";
	}

})
input_email.addEventListener('input',(e)=>{
	if(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(e.target.value)){
	span_mail.classList="alert alert-success";
	button.disabled=false;
	span_mail.textContent="Mail valide !";
	}else{
	span_mail.classList="alert alert-warning";
	button.disabled=true;
	span_mail.textContent="Entrer un mail valide !";
	
	}

})
input_date.addEventListener('input',(e)=>{
	if(Number(e.target.value.split('-')[0])>2002){
	span_naissance.classList="alert alert-warning";
	button.disabled=true;
	span_naissance.textContent="Le client doit avoir plus de 18 ans !";
	}else{
	span_naissance.classList="alert alert-success";
	button.disabled=false;
	span_naissance.textContent="Date valide !";
	}

})
</script>
<%@ include file="/WEB-INF/views/common/js_imports.jsp" %>
</body>
</html>
