<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!-- Static content -->


<script type="text/javascript" src="/resources/js/app.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="http://www.technicalkeeda.com/js/javascripts/plugin/json2.js"></script>


<title>Spring Boot </title>
<style>
.grid-container {
  display: grid;
  grid-template-columns: auto auto auto auto auto auto auto auto;
  background-color: #2196F3;
  padding: 10px;
}
.grid-item {
  background-color: rgba(255, 255, 255, 0.8);
  border: 1px solid rgba(0, 0, 0, 0.8);
  padding: 20px;
  font-size: 30px;
  text-align: center;
}
</style>
</head>
<body onload="newGame()">
  <h1>Kalaha</h1>
  <hr>
  
	<div>
		<td><input id = "newGame" onClick="newGame()" type="submit" value="New Game"></td> 	
	</div>

	<div>
		<td>Enter position (1-6)</td>
		<td><input id="position" name="position"></td>
		<td><input id = "button" onClick="validate()" type="submit" value="Submit"></td> 
	</div>

  
  <div class="board">
  
	  	<div class="grid-container">
		  <div class="grid-item">Player 2 Pit</div>
		  <div class="grid-item">1</div>
		  <div class="grid-item">2</div>  
		  <div class="grid-item">3</div>
		  <div class="grid-item">4</div>
		  <div class="grid-item">5</div>  
		  <div class="grid-item">6</div>
		  <div class="grid-item">Player 1 Pit</div>
		  
		  <div class="grid-item" id="p2-pit"></div>
		  <div class="grid-item" id="p2-1"></div>
		  <div class="grid-item" id="p2-2"></div>  
		  <div class="grid-item" id="p2-3"></div>
		  <div class="grid-item" id="p2-4"></div>
		  <div class="grid-item" id="p2-5"></div>  
		  <div class="grid-item" id="p2-6"></div>
		  <div class="grid-item">//</div> 
		  <div class="grid-item">//</div>
		  <div class="grid-item" id="p1-1"></div>
		  <div class="grid-item" id="p1-2"></div>  
		  <div class="grid-item" id="p1-3"></div>
		  <div class="grid-item" id="p1-4"></div>
		  <div class="grid-item" id="p1-5"></div>  
		  <div class="grid-item" id="p1-6"></div>
		  <div class="grid-item" id="p1-pit"></div>
		</div>
		</div>

 
</body>
</html>
