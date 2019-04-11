app.controller("courseBuyController", function($scope, $http){
	
	var changeDate = function (date) { 
		if(date){
		  	var y = date.getFullYear();  
		    var m = date.getMonth() + 1;  
		    m = m < 10 ? '0' + m : m;  
		    var d = date.getDate();  
		    d = d < 10 ? ('0' + d) : d;  
		    return y + '-' + m + '-' + d;
		} else{
			return '';
		}
	      
	};
	
	$scope.total=0;
	$scope.page=1;
	$scope.pageSize=20;
	$scope.loaddata=function(){
		$http.get("/api/btl/record",{"params": {"nicknameORmobile":$scope.nicknameORmobile,"courseName":$scope.courseName,
			"fromdate":changeDate($scope.fromdate),"todate":changeDate($scope.todate),"page":$scope.page,"types":2,"courseId":$("#courseId").val()}}, {'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				$scope.classrecord=data.data;
				$scope.total=data.count;
			}
		})
	}
	
	$scope.loaddata();
	
})