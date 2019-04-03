app.controller("withdrawController", function($scope, $http){
	
	 //总条数
    $scope.total = 0;
    //当前的页数
    $scope.current = 1;
    //一页显示多少条
    $scope.pageSize = 20;
    
    var formatDate = function (date) { 
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
 
$scope.loaddata = function(){
	$http.get("/api/userwithdraw/select",{"params": {"page":$scope.current,"fromdate":formatDate($scope.fromdate),"todate":formatDate($scope.todate),"mobile":$scope.mobile,"payStatus":$scope.payStatus}}, {'Content-Type': 'application/json;charset=UTF-8'})
    .success(function(result){
    	if(result.status=="0"){
    		$scope.withdrwlist=result.data;
    		$scope.total=result.count;
    		
    	}else{
    		alert(result.message);
    	}
    	
	})
};

$scope.loaddata();


});