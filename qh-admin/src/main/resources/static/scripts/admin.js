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
	        position: "fixed", 
	        left: ($(window).width() - $("#add").outerWidth())/2, 
	        top: ($(window).height() - $("#add").outerHeight())/2 
	    });        
	}); 
	$(window).resize(function(){ 
	    $("#revise").css({ 
	        position: "fixed", 
	        left: ($(window).width() - $("#revise").outerWidth())/2, 
	        top: ($(window).height() - $("#revise").outerHeight())/2 
	    });        
	}); 
	$(window).resize(function(){ 
	    $("#resource").css({ 
	        position: "fixed", 
	        left: ($(window).width() - $("#resource").outerWidth())/2, 
	        top: ($(window).height() - $("#resource").outerHeight())/2 
	    });        
	}); 