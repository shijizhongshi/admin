app.controller("CourseClassTemplateController",function($scope,$http){
	
	
	
   $scope.total = 0;
   //当前的页数
   $scope.page = 1;
   //一页显示多少条
   $scope.pageSize = 20;
   
   $scope.templateName=null;
   
   $scope.courseBases=function(){
	   
		$http.get("/api/classtemplate/select",{"params": {"templateName":$scope.templateName,"page":$scope.page}}, {'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				$scope.templatelist=data.data;
			}
		})
	}
	$scope.courseBases();
	
	$scope.courseSub=function(typename,sub){
			////////查模板的集合
		$scope.templateName=null;
		$scope.courseTypeSubclassName=sub.courseTypeSubclassName;
		$scope.courseBases();
			
	}
});