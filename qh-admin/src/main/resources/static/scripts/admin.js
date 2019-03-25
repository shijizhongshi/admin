        function showDiv(){
        document.getElementById('add').style.display="block"; 
        }
function CloseDiv(){
document.getElementById('add').style.display='none';
}
function formReset() {
  document.getElementById("myform").reset()
  }
  
  function showDiv2(){
        document.getElementById('revise').style.display="block"; 
        }
function CloseDiv2(){
document.getElementById('revise').style.display='none';
}
function formReset2() {
	  document.getElementById("myform2").reset()
	  }
	  
 function showDiv3(){
	        document.getElementById('resource').style.display="block"; 
	        }
	function CloseDiv3(){
	document.getElementById('resource').style.display='none';
	}
	function formReset3() {
		  document.getElementById("myform3").reset()
		  }



	$(function(){ 
	    $(window).resize(); 
	})
	$(window).resize(function(){ 
	    $("#add").css({ 
	        position: "absolute", 
	        left: ($(".details-frame").width() - $("#add").outerWidth())/2, 
	        top: ($(".details-frame").height() - $("#add").outerHeight())/2 
	    });        
	}); 
	$(window).resize(function(){ 
	    $("#revise").css({ 
	        position: "absolute", 
	        left: ($(".details-frame").width() - $("#revise").outerWidth())/2, 
	        top: ($(".details-frame").height() - $("#revise").outerHeight())/2 
	    });        
	}); 
	$(window).resize(function(){ 
	    $("#resource").css({ 
	        position: "absolute", 
	        left: ($(".details-frame").width() - $("#resource").outerWidth())/2, 
	        top: ($(".details-frame").height() - $("#resource").outerHeight())/2 
	    });        
	}); 