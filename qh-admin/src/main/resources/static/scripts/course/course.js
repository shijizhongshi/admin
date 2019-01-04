app.controller("CourseController", function($scope, $http){

	$scope.active=1;
	$scope.typeId=1;
	$scope.typeList=function(typeId){
			$scope.active=typeId;
			$scope.typeId=typeId;
			$scope.typeBases();
	};
	$scope.typeBases=function(){
		$http.get("/api/course/courseTypeSubclassList",{"params": {"courseTypeId":$scope.typeId}}, {'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				$scope.courseTypeSubclass=data.data;
			}
		})
	};
	$scope.typeBases();//////保证已经来有默认的参数
	 //总条数
    $scope.total = 0;
    //当前的页数
    $scope.current = 1;
    //一页显示多少条
    $scope.pageSize = 20;
	
	$scope.courseBases=function(){
		$scope.pageNo=( $scope.current-1)*$scope.pageSize;
		$http.get("/api/course/courseList",{"params": {"pageNo":$scope.pageNo,"pageSize":$scope.pageSize,
			"courseTypeName":$scope.courseTypeName,"courseTypeSubclassName":$scope.courseTypeSubclassName}}, {'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				$scope.courselist=data.data;
			}
		})
	}
	
	$scope.courseTypeName="医师资格";
	$scope.courseTypeSubclassName="临床(执业)助理医师";
	$scope.courseBases();
	
	$scope.courseSub=function(typename,sub){
		////////查课程的集合
		$scope.courseTypeName=typename;
		$scope.courseTypeSubclassName=sub.courseTypeSubclassName;
		$scope.courseBases();
		
	}
	////////////////以上是通过不同的条件查课程的集合的	
	$scope.course=null;
	$scope.uploadmainimage = function(file){
		if(!file.files || file.files.length < 1) return;
	    var fd = new FormData();
	    fd.append("file", file.files[0]);
		$http.post("/api/upload/single",fd,{
	        withCredentials: true,
	        headers: {'Content-Type': undefined },
	        transformRequest: angular.identity
	    })
	    .success(function(data){
	    	$scope.course.courseImg=data.data;
		})
	};
	
	
	

});