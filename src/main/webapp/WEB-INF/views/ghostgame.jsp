<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<html>
<head>
    <title>Play Ghost coding challenge game</title>
   
</head>
<body>
	<div id="playbox" align="center">
		<br>
		<h2>
			Ghost Game Challenge - Play  Game
		</h2>
		<textarea id="string" disabled="disabled" readonly="readonly" rows="2" cols="20">${response.string}</textarea>
		<br>
		<input id="playgame" type="text" maxlength="10" size="5" value="${response.playgame}" />
		<input id="playButton" type="button" value="Type a letter and press enter" onclick="ajaxPost();"/>
		<br>
	</div>
	<br>
	<div id="gameOverMessage" align="center">
		<h4 id="message" align="center">${response.message}</h4>
		<form id="playAgainForm">
			<input id="playAgain" type="submit" value="Do you want to play again?" />
		</form>
	</div>
</body>

 <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
	<script type="text/javascript">
	function ajaxPost() {  

		  var playgame = $('#playgame').val();
		  if (playgame ==='') {
			  $('#playgame').focus();
			  return;
		  }
			  
		  $.ajax({  
		    type: "POST",  
		    url: "<c:url value="/ghostgame"/>", 
		    dataType: 'json',
		    data: "playgame=" + playgame ,  
		    
		    success: function(response){  

		      $('#string').val(response.string);
		      $('#message').html(response.message);
		      $('#playgame').val(response.playgame);
		      if (response.message ==='') {
			      $('#play').focus();
		      } else {
		    	  $("#playbox").hide();
		    	  $("#gameOverMessage").show();
		    	  $('#playAgain').focus();
		      }
		    },  
		    
		    error: function(e){  
		      alert('Error: ' + e.statusError);  
		    }  
		  });  
		}  

	$( document ).ready(function() {
		$("#gameOverMessage").hide();
		$('#playgame').focus();
			$('#playgame').keypress(function(event){
			  if(event.keyCode == 13){
			    $('#playButton').click();
			  }
			});
		});
	</script>
</html>