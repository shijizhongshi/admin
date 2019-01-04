app.controller("CourseController", function($scope, $http){

	
/*$scope.typeList=function(typeId){

		$scope.active=typeId;

	
$http.get("/api/course/courseTypeSubclassList",{"params": {"courseTypeId":typeId}}, {'Content-Type': 'application/json;charset=UTF-8'})
.success(function(data){
	if(data.status=="0"){
		$scope.courseTypeSubclass=data.data;
	}
})	
}*/
	
	$scope.courseSub=function(typename,sub){
		////////查课程的集合
		alert("查对应类别课程的集合")
		
	}
	
	
	

});