app.controller("CourseController", function($scope, $http){
       /*如果需要参数的时候就这届这样写,{"params": {"title":$scope.title}}, {'Content-Type': 'application/json;charset=UTF-8'}*/
      /*如果参数是对象类型的话, $scope.course, {'Content-Type': 'application/json;charset=UTF-8'}*/   
	$http.get("/api/course/courseTypeList")
            .success(function(data){
                if(data.status=="0"){
                	$scope.courseType=data.data;
                    return;
                }else{
                    alert(data.message);
                }
            }).error(function(data){
            	alert(data.message);
            });
});
