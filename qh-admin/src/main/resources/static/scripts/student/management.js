app.controller("gradeController", function($scope, $http){

	$scope.active=1;
	$scope.typeId=1;
	$scope.courseTypeName="医师资格";
	$scope.courseTypeSubclassName="临床(执业)助理医师";
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
/////点击专业的事件
	$scope.typeSub=function(typename,sub){
		////////查班级的集合
		$scope.courseTypeName=typename;
		$scope.courseTypeSubclassName=sub.courseTypeSubclassName;
		$scope.classBases();
		$scope.selected=sub;
		
	}
	$scope.typeBases();//////保证已经来有默认的参数
	
});