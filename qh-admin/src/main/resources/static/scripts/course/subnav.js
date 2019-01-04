app.controller("CourseSubnavController", function($scope, $http){

	
$scope.typeList=function(typeId){

		$scope.active=typeId;

	
$http.get("/api/course/courseTypeSubclassList",{"params": {"courseTypeId":typeId}}, {'Content-Type': 'application/json;charset=UTF-8'})
.success(function(data){
	if(data.status=="0"){
		$scope.courseTypeSubclass=data.data;
	}
})	
}
	
$scope.courseSub=function(courseTypeName,sub){
	if($("#type").val()=="1"){
		////跳转到course
		location.href="/web/course/course?courseTypeName="+courseTypeName+"&courseSubTypeName="+sub.courseTypeSubclassName;
		
	}else if($("#type").val()=="2"){
	////跳转到章
		
	}else if($("#type").val()=="3"){
	////跳转到班级
		
	}
}

});