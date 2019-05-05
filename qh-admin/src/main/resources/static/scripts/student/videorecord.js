app.controller("videorecordController", function($scope, $http){
	
	//总条数
    $scope.total = 0;
    //当前的页数
    $scope.page = 1;
    //一页显示多少条
    $scope.pageSize = 20;
    //上传主展示图片
    $scope.mobile="";
    
    $scope.videoRecordList = function(){
		$scope.pageNo=( $scope.page-1)*$scope.pageSize;
		$http.get("/api/videorecord/list",{"params": {"mobile":$scope.mobile,"pageNo":$scope.pageNo,"pageSize":$scope.pageSize,
			"courseName":$scope.courseName,"chapterName":$scope.chapterName,"sectionName":$scope.sectionName}}, {'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(result){
    		if(result.status=="0"){
    			$scope.videoRecordlist=result.data;
    			$scope.total=result.count;
    		}
    	})
	};

	$scope.videoRecordList();

	
	$scope.checkedvideoRecord=function(vr){
		
		if($scope.selected!=vr){
			$scope.selected=vr;
		}else{
			$scope.selected=null;
		}
	}
	
	$scope.videoRecordListToOne=function(){
		$scope.page = 1;
		$scope.videoRecordList();
	}

	$scope.refresh=function(){
		
		location.reload();
	}
	
});