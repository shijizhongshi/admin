app.controller("operatingController", function($scope, $http) {
	
	$scope.total = 0;
	//当前的页数
	$scope.current = 1;
	//一页显示多少条
	$scope.pageSize = 20;
	
	$scope.selected=null;
	$scope.userRoleCategory="";
	$scope.operating=function(){
		
		$scope.pageNo = ($scope.current - 1) * $scope.pageSize;
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
	
	// 查询类别 为下拉框提供数据
	$scope.selectCategory = function() {
		$http.get("/api/userRole/selectCategory", {
			'Content-Type' : 'application/json;charset=UTF-8'
		}).success(function(list) {
			if (list != null) {
				$scope.category = list;
			} else {
				alert("下拉列表数据返回错误!")
			}
		})
	}
	$scope.selectCategory();
})