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
                Voitures
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
                        <form class="form-horizontal" method="post" action="/RentManager/cars">
                            <div class="box-body">
                                <div class="form-group">
                                    <label for="manufacturer" class="col-sm-2 control-label">Marque</label>

									<!-- Pour r�up�rer la valeur rentr�e dans un champ input de cette jsp au niveau de votre servlet -->
									<!-- vous devez passer par les methodes getParameter de l'objet request, est sp�cifiant la valeur -->
									<!-- de l'attribut "name" de l'input -->
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="manufacturer" name="manufacturer" placeholder="Marque" required>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="modele" class="col-sm-2 control-label">Modele</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="modele" name="modele" placeholder="Modele" required>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="seats" class="col-sm-2 control-label">Nombre de places</label>

                                    <div class="col-sm-10">
                                        <input type="number" min="2" max="9" class="form-control" id="seats" name="seats" placeholder="Nombre de places" required>
                                    </div>
                                </div>
                                <!--
                                <div class="form-group">
                                    <label for="owner" class="col-sm-2 control-label">Propriétaire</label>

                                </div>
                                -->
                            </div>
                            <!-- /.box-body -->
                            <div class="box-footer">
                            <span id="span_marque"></span>
                            <span id="span_modele"></span>
                            <span id="span_places"></span>
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

<%@ include file="/WEB-INF/views/common/js_imports.jsp" %>
<script>
let button=document.querySelector('button');
let input_marque=document.querySelector('#manufacturer');
let input_modele=document.querySelector('#modele');
let input_places=document.querySelector('#seats');
let span_marque=document.querySelector('#span_marque');
let span_modele=document.querySelector('#span_modele');
let span_places=document.querySelector('#span_places');

input_marque.addEventListener('input',(e)=>{
if(e.target.value==""){
span_marque.classList="alert alert-warning";
button.disabled=true;
span_marque.textContent="Veuillez indiquer une marque !";
}
else{
span_marque.classList="alert alert-success";
button.disabled=false;
span_marque.textContent="Marque valide !";
}
})

input_modele.addEventListener('input',(e)=>{
if(e.target.value==""){
span_modele.classList="alert alert-warning";
button.disabled=true;
span_modele.textContent="Veuillez indiquer un modele !";
}
else{
span_modele.classList="alert alert-success";
button.disabled=false;
span_modele.textContent="Modele valide !";
}
})

input_places.addEventListener('input',(e)=>{
if(Number(e.target.value)){
	if(Number(e.target.value)>9 || Number(e.target.value)<2){
	span_places.classList="alert alert-warning";
	button.disabled=true;
	span_places.textContent="Le nombre de places doit etre compris entre 2 et 9 !";
	}else{
	span_places.classList="alert alert-success";
	button.disabled=false;
	span_places.textContent=" Nombre de places valide !";
	}
}else{
	span_places.classList="alert alert-warning";
	button.disabled=true;
	span_places.textContent="Vous devez entrer un chiffre !";
}
	
})
</script>
</body>
</html>
