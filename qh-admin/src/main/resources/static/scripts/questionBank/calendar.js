app.controller("calendarController", function($scope, $http){
	
	$scope.active=1;
	$scope.typeId=1;
	$scope.typeSelected=null;
	$scope.html="添加";
	
	$scope.typeList=function(typename,typeId){
		
		$scope.active=typeId;
		$scope.typeId=typeId;
		$scope.typeBases();
		$scope.courseTypeName=typename;
		
	};
	$scope.typeBases=function(){
		
		$http.get("/api/course/courseTypeSubclassList",{"params": {"courseTypeId":$scope.typeId}}, {'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				$scope.courseTypeSubclass=data.data;
				$scope.typeSelected=$scope.courseTypeSubclass[0].courseTypeSubclassName;
				$scope.courseTypeSubclassName=$scope.courseTypeSubclass[0].courseTypeSubclassName;
				$scope.calendarList();
			
			}
		})
	};
	$scope.typesList=function(){
		
		$http.get("/api/course/courseTypeList", {'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				$scope.courseTypeList=data.data;
				
			}
		})
		
	}
	$scope.typesList();
	$scope.typeBases();
	
	$scope.calendarList=function(){
		
		$http.get("/api/calendar/list",{"params": {"courseTypeSubclassName":$scope.courseTypeSubclassName}}, {'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				$scope.examCalendarList=data.data;
				
			}
		})
		
	}
	
	$scope.calendarsub=function(typename,sub,$event){
		$event.stopPropagation();
		$scope.courseTypeName=typename;
		$scope.courseTypeSubclassName=sub.courseTypeSubclassName;
		$scope.calendarList();
		$scope.typeSelected=sub.courseTypeSubclassName;
	
	}
	
	$scope.checkCalendar=function(ecl){
		
		if($scope.selected!=ecl){
		$scope.selected=ecl;
		$scope.calendar=ecl;
		$scope.id=ecl.id;
		}else{
			$scope.selected=null;
			$scope.calendar=null;
			$scope.id=null;
		}
	}
	
	$scope.update=function(){
		if($scope.id!=null){
			$scope.html="修改";
		document.getElementById('add').style.display="block"; 
		
		}
		else{
			alert("请选中信息");
		}
	}
	
	$scope.add=function(){
		$scope.selected=null;
		$scope.id=null;
		$scope.calendar=null;
		$scope.html="添加";
		document.getElementById('add').style.display="block"; 
	}
	
	$scope.reset=function(){
		
		document.getElementById('add').style.display="none"; 
		$scope.selected=null;
		$scope.id=null;
		$scope.calendar=null;
	}
	
	$scope.calendaradd=function(){
		$scope.calendar.courseTypeSubclassName=$scope.courseTypeSubclassName;
		$http.post("/api/calendar/insert",$scope.calendar, {'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				alert("添加成功")
			location.reload();
			}
			
		})
	};
	
	
	$scope.updateCalendar=function(){
		$http.post("/api/calendar/update",$scope.calendar, {'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				alert("修改成功")
			location.reload();
			}
			
		})
	};
	
	
	$scope.deleteCalendar=function(){
		if($scope.id!=null){
			
			if(confirm("您确定要删除这个日历吗")){
				$http.get("/api/calendar/delete",{"params": {"id":$scope.id}}, {'Content-Type': 'application/json;charset=UTF-8'})
				.success(function(data){
					if(data.status=='0'){
						alert("删除成功~");
						$scope.id=null;
						location.reload();
					}else{
						alert("删除失败~");
					}
				})
			}
			
		}else{
			alert("请选中信息~");
		}
	}
	
})