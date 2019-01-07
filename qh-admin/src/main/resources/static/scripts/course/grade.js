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
		
	}
	$scope.typeBases();//////保证已经来有默认的参数
	 //总条数
    $scope.total = 0;
    //当前的页数
    $scope.current = 1;
    //一页显示多少条
    $scope.pageSize = 20;
    $scope.classes=null;
	$scope.classBases=function(){
		$http.get("/api/courseclass/select",{"params": {"page":$scope.current,"courseTypeName":$scope.courseTypeName,"courseTypeSubclassName":$scope.courseTypeSubclassName}}, 
			{'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				$scope.classlist=data.data;
				$scope.total=data.count;
			}
		})
	}
	
	$scope.templateBases=function(){
		$http.get("/api/classtemplate/select",{"params": {"page":0}}, {'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				$scope.templatelist=data.data;
			}
		})
	}
	
	$scope.teacherBases=function(){
		   
		$http.get("/api/courseteacher/select",{"params": {"page":0,"courseTypeName":$scope.courseTypeName,"courseTypeSubclassName":$scope.courseTypeSubclassName}}, {'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				$scope.teacherlist=data.data;
			}
		})
	}
	$scope.courseBases=function(){
	$http.get("/api/course/courseList",{"params": {"pageNo":0,"pageSize":0,
		"courseTypeName":$scope.courseTypeName,"courseTypeSubclassName":$scope.courseTypeSubclassName}}, {'Content-Type': 'application/json;charset=UTF-8'})
	.success(function(data){
		if(data.status=="0"){
			$scope.courselist=data.data;
		}
	})
	}
	$scope.coursess=[];
	$scope.classBases();
	$scope.imgUrl=null;
	$scope.uploadmainimage = function(file){
	    var fd = new FormData();
	    fd.append("file", file.files[0]);
		$http.post("/api/upload/single",fd,{
	        withCredentials: true,
	        headers: {'Content-Type': undefined },
	        transformRequest: angular.identity
	    })
	    .success(function(data){
	    	$scope.imgUrl=data.data;
	    	$scope.iurl=true;
		})
	};
	$scope.iurl=false;
	////////////////以上是通过不同的条件查章节的集合的	
	
	
	///////////选中课程ID的集合开始////////////////////////////////
	 $scope.courseselected = [];
	    var updateSelected = function(action, course) {
	      if(action == 'add' & $scope.courseselected.indexOf(course) == -1) $scope.courseselected.push(course);
	      if(action == 'remove' && $scope.courseselected.indexOf(course) != -1) $scope.courseselected.splice($scope.selected.indexOf(course), 1);
	    };
	 
	    $scope.updateSelection = function($event, course) {
	      var checkbox = $event.target;
	      var action = (checkbox.checked ? 'add' : 'remove');
	      updateSelected(action, course);
	    };
	 
	    $scope.isSelected = function(course) {
	     for(var i=0;i<$scope.courseselected.length;i++){
	    	 if($scope.courseselected[i].id==course.id){
	    		 return true;
	    	 }else{
	    		 return false;
	    	 }
	     }
	      //return $scope.courseselected.indexOf(course) >= 0;
	    };   /*checkbox选中*/

	    ///////////选中课程ID的集合结束////////////////////////////////
	    
	    ////////////选中教师的Id开始////////////////////////////////
	    $scope.teacherselected = [];
	    var updateTeacherSelected = function(action, teacher) {
		      if(action == 'add' & $scope.teacherselected.indexOf(teacher) == -1) $scope.teacherselected.push(teacher);
		      if(action == 'remove' && $scope.teacherselected.indexOf(teacher) != -1) $scope.teacherselected.splice($scope.teacherselected.indexOf(teacher), 1);
		    };
	    $scope.updateTeacherSelection = function($event, teacher) {
		      var checkbox = $event.target;
		      var action = (checkbox.checked ? 'add' : 'remove');
		      updateTeacherSelected(action, teacher);
		    
		    };
	    
	    $scope.isTeacherSelected = function(teacher) {
	    	for(var i=0;i<$scope.teacherselected.length;i++){
		    	 if($scope.teacherselected[i].id==teacher.id){
		    		 return true;
		    	 }else{
		    		 return false;
		    	 }
		     }
		     // return $scope.teacherselected.indexOf(teacher) >= 0;
		};  
	    ////////////选中教师的Id结束////////////////////////////////
	    
	$scope.classId=null;
	
	$scope.addClass=function(){
		if($scope.imgUrl==null){
			////模板的图片
			$scope.classes.imgUrl=$scope.classes.classUrl;
		}else{
			$scope.classes.imgUrl=$scope.imgUrl;
			
		}
		if($scope.classes.id!=null){
			/////说明有模板
			$scope.classes.templateId=$scope.classes.id;
		}
		$scope.classes.listTeacher=$scope.teacherselected;
		$scope.classes.listCourse=$scope.courseselected;
		$scope.classes.courseTypeName=$scope.courseTypeName;
		$scope.classes.courseTypeSubclassName=$scope.courseTypeSubclassName;
		if($scope.classId==null){
			$http.post("/api/courseclass/save",$scope.classes,{'Content-Type': 'application/json;charset=UTF-8'})
		    .success(function(data){
		    	if(data.status=="0"){
		    		alert("保存成功~");
		    		document.getElementById('add').style.display="none"; 
		    		$scope.classBases();
		    	}else{
		    		alert(data.message)
		    	}
			})
		}else{
			$scope.classes.id=$scope.classId;
			$http.post("/api/courseclass/update",$scope.classes,{'Content-Type': 'application/json;charset=UTF-8'})
		    .success(function(data){
		    	if(data.status=="0"){
		    		alert("更新成功~");
		    		document.getElementById('add').style.display="none"; 
		    		$scope.classBases();
		    	}else{
		    		alert(data.message)
		    	}
			})
			
		}
		
		
	};
	///////做选中的时候用
	$scope.checkedclass=function(c){
		$scope.selected=c;
		$scope.classes=c;
		$scope.classId=c.id;
		$scope.courseselected=c.listCourse;
		console.log($scope.courseselected);
		$scope.teacherselected=c.listTeacher;
		console.log($scope.teacherselected);
		
	}
	$scope.add=function(){
		$scope.classId=null;
		$scope.classes=null;
		$scope.templateBases();
		$scope.teacherBases();
		$scope.courseBases();
		document.getElementById('add').style.display="block"; 
		
	}
	////点击修改的按钮先看看是否已经选中了
	$scope.update=function(){
		
		if($scope.classId!=null){
			$scope.templateBases();
			$scope.teacherBases();
			$scope.courseBases();
			document.getElementById('add').style.display="block"; 
		}else{
			alert("请选中信息~");
		}
		
	}
	
	$scope.deleteClass=function(){
		if($scope.classId!=null){
			////删除课程/
			if(confirm("您确定要删出这个班级吗")){
				$http.get("/api/courseclass/delete",{"params": {"id":$scope.classId}}, {'Content-Type': 'application/json;charset=UTF-8'})
				.success(function(data){
					if(data.status=='0'){
						alert("删除成功~");
						$scope.classBases();
					}else{
						alert("删除失败~");
					}
				})
			}
			
		}else{
			alert("请选中信息~");
		}
	}

});