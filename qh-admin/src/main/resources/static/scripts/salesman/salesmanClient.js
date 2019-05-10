app.controller("salesmanClientController", function($scope, $http){
	
	
	$scope.salesmanId=$("#salesmanId").val();
	 //总条数
    $scope.total = 0;
    //当前的页数
    $scope.page = 1;
    //一页显示多少条
    $scope.pageSize = 20;
$scope.SalesmanClientList = function(){
	$scope.pageNo=( $scope.page-1)*$scope.pageSize;
	$http.get("/api/salesmanClient/list",{"params": {"salesmanId":$scope.salesmanId,"mobile":$scope.mobile,"pageNo":$scope.pageNo,"pageSize":$scope.pageSize}}, {'Content-Type': 'application/json;charset=UTF-8'})
    .success(function(date){
    	if(date.status=="0"){
    		$scope.salesmanClientlist=date.data;
    		$scope.total=date.count;
    		
    	}
    	
	})
};

$scope.SalesmanClientList();

	$scope.id=null;
	
	$scope.checkedsalesmanClient=function(scl){
		$scope.selected=scl;
		$scope.id=scl.id;
	}
	
	$scope.reset=function(){
		
		location.reload();
	}

	
})