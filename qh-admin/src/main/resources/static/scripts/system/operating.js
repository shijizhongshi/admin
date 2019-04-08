app.controller("operatingController", function($scope, $http) {
	
	$scope.total = 0;
	//当前的页数
	$scope.page = 1;
	//一页显示多少条
	$scope.pageSize = 20;
	
	$scope.selected=null;
	$scope.userRoleCategory="";
	$scope.operating=function(){
		
		$scope.pageNo = ($scope.page - 1) * $scope.pageSize;
		$http.get("/api/operating/list", {
			"params" : {
				"pageNo" : $scope.pageNo,
				"pageSize" : $scope.pageSize,
				"userRoleCategory" : $scope.userRoleCategory,
				"userRoleUsername" : $scope.userRoleUsername,
				"operatingScope" : $scope.operatingScope,
				"operatingStatus" : $scope.operatingStatus,
			}
		}, {
			'Content-Type' : 'application/json;charset=UTF-8'
		}).success(function(data) {
			if (data.status == "0") {
				$scope.operatinglist = data.data;
				$scope.total = data.count;
			}
		})
	}
	$scope.operating();
	
	$scope.checkOperating=function(ol){
		
		if($scope.selected!=ol){
			
			$scope.selected=ol;
			$scope.operating=ol;
			$scope.id=ol.id;
		}else{
			$scope.selected=null;
			$scope.operating=null;
			$scope.id=null;
		}
			
	}
	
	
})