app.controller("salesmanSecondController", function($scope, $http){
	
	
	$scope.salesmanId=$("#salesmanId").val();
	 //总条数
    $scope.total = 0;
    //当前的页数
    $scope.page = 1;
    //一页显示多少条
    $scope.pageSize = 20;
$scope.SalesmanSecondList = function(){
	$scope.pageNo=( $scope.page-1)*$scope.pageSize;
	$http.get("/api/salesmanSecond/list",{"params": {"salesmanId":$scope.salesmanId,"mobile":$scope.mobile,"pageNo":$scope.pageNo,"pageSize":$scope.pageSize}}, {'Content-Type': 'application/json;charset=UTF-8'})
    .success(function(date){
    	if(date.status=="0"){
    		$scope.salesmanSecondlist=date.data;
    		$scope.total=date.count;
    		
    	}
    	
	})
};

$scope.SalesmanSecondList();

	$scope.id=null;
	
	$scope.checkedsalesmanSecond=function(scl){
		$scope.selected=scl;
		$scope.id=scl.id;
	}
	
	$scope.reset=function(){
		
		location.reload();
	}

	
})