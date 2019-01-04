app.controller("CourseSubnavController", function($scope, $http){
	$scope.active=1;
	
$scope.typeList=function(typeId){

		$scope.active=typeId;

	
$http.get("/api/course/courseTypeSubclassList",{"params": {"courseTypeId":typeId}}, {'Content-Type': 'application/json;charset=UTF-8'})
.success(function(data){
	if(data.status=="0"){
		$scope.courseTypeSubclass=data.data;
	}
})	
}
	

});