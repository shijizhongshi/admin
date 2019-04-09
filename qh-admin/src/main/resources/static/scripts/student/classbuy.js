app.controller("classBuyController", function($scope, $http){
	
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
	//加载时展示页面
	$scope.total=0;
	$scope.page=1;
	$scope.pageSize=20;
	$scope.loaddata=function(){
		console.log($scope.courseName);
		$http.get("/api/btl/record",{"params": {"nicknameORmobile":$scope.nicknameORmobile,"courseName":$scope.courseName,
			"fromdate":changeDate($scope.fromdate),"todate":changeDate($scope.todate),"page":$scope.page,"types":1,"classId":$("#classId").val()}}, {'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				$scope.classrecord=data.data;
				$scope.total=data.count;
			}
		})
	}
	
	$scope.loaddata();
	
})