app.controller("liveAccessController", function($scope, $http) {

	// 总条数
	$scope.total = 0;
	// 当前的页数
	$scope.pageindex = 1;
	// 一页展示多少行
	$scope.pagenum = 20;

	// 页面展示
	$scope.loaddata = function() {
		if ($scope.liveId == null) {
			alert("请填写直播id~");
			return;
		}
		$http.get("/api/questionbank/liveAccess",{"params":{"notToEnter":$scope.notToEnter,"liveId":$scope.liveId,"pageindex":$scope.pageindex,"pagenum":$scope.pagenum}},{'Content-Type' : 'application/json;charset=UTF-8'})
		.success(function (result) {
			if (result.status == "0") {
				$scope.total = result.count;
				$scope.list = result.data;
			}else {
				alert(result.message);
			}
		})
	}
});