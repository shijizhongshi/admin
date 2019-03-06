app.controller("commonController", function($scope, $http){
	//////父极
	   document.getElementById('drugshow').style.display="block";
		 

	  $scope.go=function(type){
		  
		if(type==0){
		document.getElementById('serveshow').style.display="none"; 
		document.getElementById('drugshow').style.display="block"; 
		   }else{
			   document.getElementById('drugshow').style.display="none";
			   document.getElementById('serveshow').style.display="block"; 
		   }
		}

	
})