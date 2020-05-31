<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
     <meta charset="utf-8">
     <title>SCD</title>

     <!--Favicon de la pagina-->
       <link rel="shorcut icon" type="image/ico" 
       href='<i class="fas fa-space-shuttle"></i>'>    

     <!--Conexion con estilos.css-->
      <link rel="stylesheet" href="css/estilos.css">

     <!--Links para el funcionamiento de bootstrap-->
      <link rel="stylesheet" href="css/bootstrap.min.css">
      <script  src="js/jquery.min.js"></script>
      <script src="js/popper.min.js"></script>
      <script src="js/bootstrap.min.js"></script>

      <!--Links para el funcionamiento de font awesome icons-->
         <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/solid.css">
         <script src="https://use.fontawesome.com/releases/v5.0.7/js/all.js"></script>
       
      <!---Link pra el funcionamiento de chart  js librarie-->
         <script src="chart/Chart.min.js"></script>

  </head>
  <body>
        <!--navbar-->
        <nav class="navbar sticky-top navbar-expand-lg navbar-light" id="mainNav">
             <div class="container">
                 <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                     <ul class="nav navbar-nav navbar-right">
                         <a class="display-4"><i class="fas fa-shower"></i></a>
                     </ul>
                 </div>
                  <button class="navbar-toggler navbar-toggler-right" type="button" 
                  data-toggle="collapse" data-target="#navbarResponsive" 
                  aria-controls="navbarResponsive" aria-expanded="false"
                  aria-label="Toggle navigation">
                  <span class="navbar-toggler-icon"></span>
                 </button>
                 <div class="collapse navbar-collapse" id="navbarResponsive">
                     <ul class="navbar-nav ml-auto">
                          <li class="nav-item">
                             <a class="nav-link" href="tablas.php">Tablas</a>
                          </li>
                          <li class="nav-item">
                             <a  class="nav-link" href="index.php">Inicio</a>
                          </li>
                      </ul>
                 </div>
             </div>
         </nav>
     <!--Finaliza el navbar-->
       <?php
          include("conexion.php");
          $sql = "SELECT * FROM Datos_ducha";
          $query = $conexion->query($sql);
          $datosGasto = array();
          $datosFecha = array();
          $datosCosto = array();
          $datosTiempo = array();
          while ($row = $query->fetch_assoc()) {
              array_push($datosFecha,$row['Fecha']);
              array_push($datosGasto,$row['Gasto']);
              array_push($datosCosto,$row['Costo']);
              array_push($datosTiempo,$row['Tiempo']);
          }
        ?>
      <div class="container container-fluid">
            <div class="row shadow-lg p-3 cajaGraficas ">
             <div class="col-sm-12">
                 <canvas id="gastoChart" height="135%"><!-----Inicia grafica de Fecha-Costo-tiempo---->
                   <script>
                 var gastoCanvas = document.getElementById("gastoChart");

                 Chart.defaults.global.defaultFontFamily = "Lato";
                 Chart.defaults.global.defaultFontSize = 18;

                 var gastoData = {
                  labels:[<?php for($f = 0; $f < count($datosFecha); $f++)
                  {
                    ?>  
                     "<?php echo $datosFecha[$f]." ".$datosTiempo[$f]; ?>",
                 <?php } ?>
                 ],
                 datasets: [{
                   label: "Gasto Total(Litros)",
                   data:[<?php for($i = 0; $i < count($datosGasto); $i++) {?>  <?php echo $datosGasto[$i]; ?>,
                                    <?php } ?>],
                                  lineTension: 0,
                   fill: false,
                   borderColor: '#335eff',
                   backgroundColor: 'transparent',
                   borderDash: [5, 5],
                   pointBorderColor: 'blue',
                   pointBackgroundColor: 'rgba(25,150,189,0.5)',
                   pointRadius: 5,
                   pointHoverRadius: 10,
                   pointHitRadius: 30,
                   pointBorderWidth: 2,
                   pointStyle: 'rectRounded'
                 },
                 { label: "Costo($)",
                   data:[<?php for($i = 0; $i < count($datosCosto); $i++) {?>  <?php echo $datosCosto[$i]; ?>,
                                    <?php } ?>],
                                  lineTension: 0,
                   fill: false,
                   borderColor: '#49ff33',
                   backgroundColor: 'transparent',
                   borderDash: [5, 5],
                   pointBorderColor: 'green',
                   pointBackgroundColor: 'rgba(25,189,150,0.5)',
                   pointRadius: 5,
                   pointHoverRadius: 10,
                   pointHitRadius: 30,
                   pointBorderWidth: 2,
                   pointStyle: 'rectRounded'
                 },
                 /*{ label: "Tiempo(Minutos)",
                   data:[<?php for($i = 0; $i < count($datosTiempo); $i++) {?>  <?php echo $datosTiempo[$i]; ?>,
                                    <?php } ?>],
                                  lineTension: 0,
                   fill: false,
                   borderColor: '#ff7d33',
                   backgroundColor: 'transparent',
                   borderDash: [5, 5],
                   pointBorderColor: 'orange',
                   pointBackgroundColor: 'rgba(255,153,51,0.5)',
                   pointRadius: 5,
                   pointHoverRadius: 10,
                   pointHitRadius: 30,
                   pointBorderWidth: 2,
                   pointStyle: 'rectRounded'
                 }*/
                ]
                };

                var chartOptions = {
                  legend: {
                    display: true,
                    position: 'top',
                    labels: {
                      boxWidth: 80,
                      fontColor: 'grey',
                    }
                  },
                  options:{
                      responsive: true,
                   }
                };

                var lineChart = new Chart(gastoCanvas, {
                  type: 'line',
                  data: gastoData,
                  options: chartOptions
                },);
                </script>
             </canvas>
            </div>  <!--------Finaliza Grafica de Fecha(Gasto-Costo-Tiempo)------------------------>
              </div>
            <div class="row shadow-lg p-3 cajaGraficas">
               <div class="col-sm-12">
            <canvas id="datosChart" heigth="400">
              <script>
var datosCanvas = document.getElementById("datosChart");

Chart.defaults.global.defaultFontFamily = "Lato";
Chart.defaults.global.defaultFontSize = 18;

var gastoData = {
  label: 'Gasto (L)',
  data: [<?php for($i = 0; $i < count($datosGasto); $i++) {?>  <?php echo $datosGasto[$i]; ?>,
    <?php } ?>],
  backgroundColor: '#335eff',
  borderWidth: 0,
  yAxisID: "y-axis-gasto"
};

var costoData = {
  label: 'Costo ($)',
  data: [<?php for($i = 0; $i < count($datosCosto); $i++) {?>  <?php echo $datosCosto[$i]; ?>,
    <?php } ?>],
  backgroundColor: '#49ff33',
  borderWidth: 0,
  yAxisID: "y-axis-costo"
};

/*var tiempoData = {
    label: 'Tiempo (Minutos)',
    data: [<?php for($i = 0; $i < count($datosTiempo); $i++) {?>  <?php echo $datosTiempo[$i]; ?>,
        <?php } ?>],
    backgroundColor: '#ff7d33',
    borderWidth: 0,
    yAxisID: "y-axis-tiempo"
  };*/

var fechaData = {
  labels: [<?php for($f = 0; $f < count($datosFecha); $f++)
  {
    ?>  
     "<?php echo $datosFecha[$f]." ".$datosTiempo[$f]; ?>",
 <?php } ?>],
  datasets: [gastoData, costoData]
};

var chartOptions = {
  scales: {
    xAxes: [{
      barPercentage: 1,
      categoryPercentage: 0.6
    }],
    yAxes: [{
      id: "y-axis-gasto"
    }, {
      id: "y-axis-costo"
    },]
  }
};

var barChart = new Chart(datosCanvas, {
  type: 'bar',
  data: fechaData,
  options: chartOptions
});

            </script>
           </canvas>
          </div>
         </div>
       </div>
      </div>
        <br>
     <!--Inicia el footer-->
     <footer>
         <div class="container container-fluid footer">
             <div class="row">
                 <div class="col-sm-3"><span class="">Julian Andres Ramirez Jimenez</span></div>
                 <div class="col-sm-3"><span class="">Samuel David Villegas Bedoya</span></div>
                 <div class="col-sm-3"><span class="">Edier Morales Barrera</span></div>
                 <div class="col-sm-3"><span class="">Kevin Sossa Chavarria</span></div>
              </div>
              <br><br>
              <div class="row">
                 <div class="col-sm-4"></div>
                 <div class="col-sm-4"><p class="text-light">Sistema Control Ducha</p></div>
                 <div class="col-sm-4"></div>
              </div>
          </div>
          <br>
     </footer>
     <!-- jQuery -->
        <script language="javascript" 
        src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
  </body>