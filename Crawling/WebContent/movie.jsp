<%@page import="data.CGV"%>
<%@page import="dto.MovieDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	List<MovieDto> list = CGV.getCGVdata();
	String json = "[";
	
	for(MovieDto dto : list){
		json += "{name: '" + dto.getTitle() + "', y: " + dto.getPercent() + "}, ";
	}
	json = json.substring(0, json.length()-2);
		// json.substring(0, json.lastIndexOf(",")); 맨 마지막에 있는 , 제거
	json += "]";
	
	System.out.println(json);
	
	request.setAttribute("moviedata", json);%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Movie</title>
<!-- https://www.highcharts.com/demo 에서 원하는 차트 클릭 > EDIT IN CODPEN > html 윗부분 복붙 -->
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script src="https://code.highcharts.com/modules/export-data.js"></script>
<script src="https://code.highcharts.com/modules/accessibility.js"></script>

<!-- css 복붙 -->
<style>
	.highcharts-figure,
	.highcharts-data-table table {
	  min-width: 320px;
	  max-width: 800px;
	  margin: 1em auto;
	}
	
	.highcharts-data-table table {
	  font-family: Verdana, sans-serif;
	  border-collapse: collapse;
	  border: 1px solid #ebebeb;
	  margin: 10px auto;
	  text-align: center;
	  width: 100%;
	  max-width: 500px;
	}
	
	.highcharts-data-table caption {
	  padding: 1em 0;
	  font-size: 1.2em;
	  color: #555;
	}
	
	.highcharts-data-table th {
	  font-weight: 600;
	  padding: 0.5em;
	}
	
	.highcharts-data-table td,
	.highcharts-data-table th,
	.highcharts-data-table caption {
	  padding: 0.5em;
	}
	
	.highcharts-data-table thead tr,
	.highcharts-data-table tr:nth-child(even) {
	  background: #f8f8f8;
	}
	
	.highcharts-data-table tr:hover {
	  background: #f1f7ff;
	}
	
	input[type="number"] {
	  min-width: 50px;
	}
</style>
</head>
<body>	
	<!-- html 밑부분 복붙 -->
	<figure class="highcharts-figure">
	  <div id="container"></div>
	</figure>
	
	<script type="text/javascript">	
		// js 복붙
		Highcharts.chart('container', {
		  chart: {
		    plotBackgroundColor: null,
		    plotBorderWidth: null,
		    plotShadow: false,
		    type: 'pie'},
		  title: { text: 'CGV 무비차트' },
		  tooltip: {
		    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
		  },
		  accessibility: {
		    point: { valueSuffix: '%' }},
		  plotOptions: {
		    pie: {
		      allowPointSelect: true,
		      cursor: 'pointer',
		      dataLabels: {
		        enabled: true,
		        format: '<b>{point.name}</b>: {point.percentage:.1f} %'
		      }
		    }},
		  series: [{
		    name: 'Brands',
		    colorByPoint: true,
		    data: <%=request.getAttribute("moviedata")%>
		  }]
		});
	</script>
</body>
</html>