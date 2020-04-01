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
console.log('huhuh')
let debut=document.querySelector('#debut');
let fin=document.querySelector('#fin');
let input_debut=document.querySelector('#date_debut');
let input_fin=document.querySelector('#date_fin');
let button=document.querySelector('button');
input_fin.addEventListener('input',(e)=>{
console.log(e.taget.value})
input_fin.addEventListener('blur',(e)=>console.log(e.target.value))
</script>
</body>
</html>