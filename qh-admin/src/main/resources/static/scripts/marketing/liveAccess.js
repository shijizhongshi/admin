app.controller("liveAccessController", function($scope, $http) {

	// 总条数
	$scope.total = 0;
	// 当前的页数
	$scope.pageindex = 1;
	// 一页展示多少行
	$scope.pagenum = 1000;

	// 页面展示
	$scope.loaddata = function() {
		if ($scope.liveId == null) {
			alert("请填写直播Id~");
			return;
		}
		$http.get("/api/questionbank/liveAccess",{"params":{"notToEnter":$scope.notToEnter,"liveId":$scope.liveId,"pageindex":$scope.pageindex,"pagenum":$scope.pagenum}},{'Content-Type' : 'application/json;charset=UTF-8'})
		.success(function (result) {
			if (result.status == "0") {
				$scope.total = result.count;
				$scope.list = result.data;
				
				angular.forEach($scope.list,function(list){
				    if(list.watchTime==null){
				    	list.watchTimes="未观看直播"
				    }else{
				    	list.watchTimes=parseInt(list.watchTime/60)+"分钟";
				    	
				    }
				    if(list.terminal==0){
				    	list.terminals="电脑端"
				    }else if(list.terminal==1){
				    	list.terminals="移动端"
				    }else if(list.terminal==null){
				    	list.terminals="无终端类型"
				    }
				})
			}else {
				alert(result.message);
			}
		})
	}
});