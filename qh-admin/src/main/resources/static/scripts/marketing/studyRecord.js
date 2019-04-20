app.controller("studyRecordController", function($scope, $http) {

	// 总条数
	$scope.total = 0;
	// 当前的页数
	$scope.page = 1;
	// 一页展示多少行
	$scope.numPerPage = 20;

	// 格式化日期 yyyy-MM-dd
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
	// 页面展示
	$scope.isshow = 2;
	console.log($scope.isshow);
	console.log();
	$scope.loaddata = function() {
		// BUG:无法判断 空字符串
		// cc接口名称：获取用户自定义参数播放记录
		if ($scope.mobile != null && $scope.videoId == null) {
			
		}
		//{"params":{"pageNo":$scope.pageNo,"pageSize":$scope.pageSize,"fromdate":changeDate($scope.fromdate),"todate":changeDate($scope.todate),"mobile":$scope.mobile,"courseTypeSubclassName":$scope.courseTypeSubclassName,"roomId":$scope.roomId}}
		// cc接口名称：获取视频播放记录
		if ($scope.mobile == null && $scope.videoId != null) {
			$http.get("/api/questionbank/video/v2",
					{"params":{"page":$scope.page,"numPerPage":$scope.numPerPage,"videoId":$scope.videoId,"date":changeDate($scope.date)}},
					{'Content-Type' : 'application/json;charset=UTF-8'})
			.success(function (result) {
				if (result.status == "0") {
					$scope.isshow = 1;
					$scope.list = result.data;
					console.log(result.data);
					return;
				}
			})
		};
		// cc接口名称：获取视频自定义参数播放记录
		if ($scope.mobile != null && $scope.videoId != null) {
			
		}
	}
});