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
                update Reservation numero ${Myresa.id}
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
                        <form class="form-horizontal" method="post" action="/RentManager/rents/update">
                            <div class="box-body">
                            <input type="hidden" name="id" value="${Myresa.id}">
                            <p class="alert alert-info"><svg style="display:inline-block;margin-right:10px" class="bi bi-info-circle-fill" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  <path fill-rule="evenodd" d="M8 16A8 8 0 108 0a8 8 0 000 16zm.93-9.412l-2.29.287-.082.38.45.083c.294.07.352.176.288.469l-.738 3.468c-.194.897.105 1.319.808 1.319.545 0 1.178-.252 1.465-.598l.088-.416c-.2.176-.492.246-.686.246-.275 0-.375-.193-.304-.533L8.93 6.588zM8 5.5a1 1 0 100-2 1 1 0 000 2z" clip-rule="evenodd"/>
</svg>Seul les champs comportant des valeurs seront mis a jour </p>
                            <%
                                if(request.getSession().getAttribute("errorupdateresa")!=null){ %>
                            	<p class="alert alert-warning"><%=request.getSession().getAttribute("errorupdateresa")%></p>
                               <% }else{} %>
                            	<div class="form-group">
                                    <label for="last_name" class="col-sm-2 control-label">Date de debut</label>

                                    <div class="col-sm-10">
                                        <input type="date" class="form-control" id="date_debut" name="date_debut" placeholder="date de début">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="first_name" class="col-sm-2 control-label">Date de fin</label>

                                    <div class="col-sm-10">
                                        <input type="date" class="form-control" id="date_fin" name="date_fin" placeholder="date de fin">
                                    </div>
                                </div>
                            <!-- /.box-body -->
                            <div class="box-footer">
                            <span id="debut"></span>
                            <span id="fin"></span>
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
let input_debut=document.querySelector('#date_debut');
let input_fin=document.querySelector('#date_fin');
let button=document.querySelector('button');
input_fin.addEventListener('input',(e)=>{
})
</script>
</body>
</html>