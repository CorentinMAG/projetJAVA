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
                update ${Myclient.nom} ${Myclient.prenom}
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
                        <form class="form-horizontal" method="post" action="/RentManager/users/update">
                            <div class="box-body">
                            <input type="hidden" name="id" value="${Myclient.id}">
                           <p class="alert alert-info"><svg style="display:inline-block;margin-right:10px" class="bi bi-info-circle-fill" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  <path fill-rule="evenodd" d="M8 16A8 8 0 108 0a8 8 0 000 16zm.93-9.412l-2.29.287-.082.38.45.083c.294.07.352.176.288.469l-.738 3.468c-.194.897.105 1.319.808 1.319.545 0 1.178-.252 1.465-.598l.088-.416c-.2.176-.492.246-.686.246-.275 0-.375-.193-.304-.533L8.93 6.588zM8 5.5a1 1 0 100-2 1 1 0 000 2z" clip-rule="evenodd"/>
</svg>Seul les champs comportant des valeurs seront mis a jour </p>
                            <%
                                if(request.getSession().getAttribute("errorupdateclient")!=null){ %>
                            	<p class="alert alert-warning"><%=request.getSession().getAttribute("errorupdateclient")%></p>
                               <% }else{} %>
                            	<div class="form-group">
                                    <label for="last_name" class="col-sm-2 control-label">Nom</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="last_name" name="last_name" placeholder="Nom">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="first_name" class="col-sm-2 control-label">Prenom</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="first_name" name="first_name" placeholder="Prenom">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="email" class="col-sm-2 control-label">Email</label>

                                    <div class="col-sm-10">
                                        <input type="email" class="form-control" id="email" name="email" placeholder="Email">
                                    </div>
                            </div>
                            <div class="form-group">
                                    <label for="naissance" class="col-sm-2 control-label">date de naissance</label>

                                    <div class="col-sm-10">
                                        <input type="date" class="form-control" id="naissance" name="naissance" placeholder="yyyy-mm-dd">
                                    </div>
                            </div>
                            <!-- /.box-body -->
                            <div class="box-footer">
                            <span id="errorNom"></span>
                            <span id="errorPrenom"></span>
                            <span id="errorMail"></span>
                            <span id="errorNaissance"></span>
                                <button type="submit" class="btn btn-info pull-right">Update</button>
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

<%@ include file="/WEB-INF/views/common/js_imports.jsp" %>
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
	if(e.target.value.length<3 && e.target.value.length>0){
	span_nom.classList="alert alert-warning";
	button.disabled=true;
	span_nom.textContent="Le nom doit comporter plus de 3 caracteres !";
	}else if(e.target.value.length>=3){
	span_nom.classList="alert alert-success";
	button.disabled=false;
	span_nom.textContent="Nom valide !";
	}else{
	span_nom.innerHTML="";
	span_nom.classList="";
	button.disabled=false;
	}

})
input_prenom.addEventListener('input',(e)=>{
	if(e.target.value.length<3 && e.target.value.length>0){
	span_prenom.classList="alert alert-warning";
	button.disabled=true;
	span_prenom.textContent="Le prenom doit comporter plus de 3 caracteres !";
	}else if (e.target.value.length>=3){
	span_prenom.classList="alert alert-success";
	button.disabled=false;
	span_prenom.textContent="Prenom valide !";
	}else{
	span_prenom.innerHTML="";
	span_prenom.classList="";
	button.disabled=false;
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
	if(e.target.value.length==0){
	span_mail.innerHTML="";
	span_mail.classList="";
	button.disabled=false;
	}
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
</body>
</html>