app.controller("liveVerifyController", function($scope, $http) {

	// 给专业下拉列表提供数据
	$scope.typeBases = function() {
		$http.get("/api/course/selectCourseTypeSubclassNameAll", {
			'Content-Type' : 'application/json;charset=UTF-8'
		}).success(function(result) {
			if (result.status == "0") {
				$scope.nameList = result.data;
			}
		})
	};
	$scope.typeBases(); // 加载页面时调用
	// 总条数
	$scope.total = 0;
	// 当前的页数
	$scope.page = 1;
	//一页展示多少行
	$scope.pageSize = 20;
	
	//页面展示
	$scope.loaddata = function () {
		$scope.pageNo=( $scope.page-1)*$scope.pageSize;
		$http.get("/api/questionbank/liveVerifyList",{"params":{"pageNo":$scope.pageNo,"pageSize":$scope.pageSize,"mobile":$scope.mobile,"courseTypeSubclassName":$scope.courseTypeSubclassName,"roomId":$scope.roomId}}, {'Content-Type': 'application/json;charset=UTF-8'})
		.success(function (result) {
			if (result.status == 0) {
				$scope.liveVerifyList = result.data;
				$scope.total = result.count;
			}else {
				alert(result.message);
			}
		})
		$http.get("/api/questionbank/liveVerifyList",{"params":{"pageNo":$scope.pageNo,"pageSize":0,"mobile":$scope.mobile,"courseTypeSubclassName":$scope.courseTypeSubclassName,"roomId":$scope.roomId}}, {'Content-Type': 'application/json;charset=UTF-8'})
		.success(function (result) {
			if (result.status == 0) {
				$scope.liveVerifyLists = result.data;
				
					angular.forEach($scope.liveVerifyLists, function(live){  
					
					if(live.isRegister==0){
						
						live.isRegisters="游客用户";
					}
					else if(live.isRegister==1){
						live.isRegisters="注册用户";
					}
					if(live.updatetime==null){
						
						live.time=live.addtime;
					}
					else{
						live.time=live.updatetime;
					}
				})
			}
		})
	};
	$scope.loaddata();
});