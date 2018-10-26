function validate() {
	var name = document.getElementById("position").value;
	var regex=/^[0-9]+$/;
    if (!name.match(regex) || name<1 || name > 6){
		alert('Please enter a valid position.');
		return false;
    }else{
    	turn();
    	return true;
    }
}


function newGame(){

    $.post("newGame",
    function(data,status){
        updateBoard(data.board);
        alert("Players turn = " + getName(data));
    });
}

function turn(){
	$.post("turn",$("#position").serialize(),
    function(data,status){

        if(data == true){
        	getBoard();
        }
        if(data == false){
        	alert("Invalid Position Specified");
        }
    });
	
}

function getBoard(){
	
	$.get("getBoard",
    function(data,status){
		
		var winnerAlert = false;
        updateBoard(data.board);
   
        if(data.winner != -1){
        	var winner = data.winner;
        	var winnerName = "";
        	
        	if(winner == 0) winnerName = "Player 2";
        	if(winner == 1) winnerName = "Player 1";
        	if(winner == 2) winnerName = "No one, it's a Draw";
        	alert("Winner is " + winnerName);
        	winnerAlert = true;
        	
        }
        if(!winnerAlert)alert("Players turn = " + getName(data));
    });
}

function getName(data){
	if(data.playerRow == 0)return "Player 2";
	if(data.playerRow == 1)return "Player 1";
}

function updateBoard(board){
	
	//player 2 pit
	$("#p2-pit").text(board[0][6]);
	//player 2 board
	$("#p2-1").text(board[0][0]);
	$("#p2-2").text(board[0][1]);
	$("#p2-3").text(board[0][2]);
	$("#p2-4").text(board[0][3]);
	$("#p2-5").text(board[0][4]);
	$("#p2-6").text(board[0][5]);
	
	//player 1 pit
	$("#p1-pit").text(board[1][6]);
	//player 1 board
	$("#p1-1").text(board[1][0]);
	$("#p1-2").text(board[1][1]);
	$("#p1-3").text(board[1][2]);
	$("#p1-4").text(board[1][3]);
	$("#p1-5").text(board[1][4]);
	$("#p1-6").text(board[1][5]);
	
}