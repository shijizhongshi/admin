app.controller("gradeController", function($scope, $http){

	$scope.className=null;
	$scope.selected=null;
	$scope.active=1;
	$scope.typeId=1;
	$scope.courseTypeName="医师资格";
	$scope.courseTypeSubclassName="临床(执业)助理医师";
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
				$scope.classBases();
			}
		})
	};
/////点击专业的事件
	$scope.typeSub=function(typename,sub,$event){
		////////查班级的集合
		$event.stopPropagation();
		$scope.courseTypeName=typename;
		$scope.courseTypeSubclassName=sub.courseTypeSubclassName;
		$scope.classBases();
		$scope.typeSelected=sub.courseTypeSubclassName;
		
	}
	$scope.typesList=function(){
		
		$http.get("/api/course/courseTypeList", {'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				$scope.courseTypeList=data.data;
				
			}
		})
		
	}
	$scope.typesList();
	$scope.typeBases();//////保证已经来有默认的参数
	 //总条数
    $scope.total = 0;
    //当前的页数
    $scope.page = 1;
    //一页显示多少条
    $scope.pageSize = 20;
    $scope.classes=null;
    
	$scope.classBases=function(){
		
		$http.get("/api/courseclass/select",{"params": {"page":$scope.page,"courseTypeName":$scope.courseTypeName,"courseTypeSubclassName":$scope.courseTypeSubclassName,"className":$scope.className}}, 
			{'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				$scope.classlist=data.data;
				$scope.total=data.count;
				$scope.courseselected = [];
				 $scope.courseNameselected=[];
				 $scope.teacherselected = [];
				 $scope.teacherNameselected = [];
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
	    	
		})
	};
	
	////////////////以上是通过不同的条件查章节的集合的	
	
	
	///////////选中课程ID的集合开始////////////////////////////////
	 $scope.courseselected = [];
	 $scope.courseNameselected=[];
	    var updateSelected = function(action, course) {
	      if(action == 'add' && $scope.courseNameselected.indexOf(course.courseName) == -1){
	    	  $scope.courseselected.push(course);
	    	  $scope.courseNameselected.push(course.courseName);
	      }
	      if(action == 'remove' && $scope.courseNameselected.indexOf(course.courseName)!= -1){
	    	  $scope.courseselected.splice($scope.courseNameselected.indexOf(course.courseName), 1);
	    	  $scope.courseNameselected.splice($scope.courseNameselected.indexOf(course.courseName), 1);
	      }
	    };
	 
	    $scope.updateSelection = function($event, course) {
	      var checkbox = $event.target;
	      var action = (checkbox.checked ? 'add' : 'remove');
	      updateSelected(action, course);
	    };
	 
	    $scope.isSelected = function(courseName) {
	     
	      return $scope.courseNameselected.indexOf(courseName) >= 0;
	    };   /*checkbox选中*/

	    ///////////选中课程ID的集合结束////////////////////////////////
	    
	    ////////////选中教师的Id开始////////////////////////////////
	    $scope.teacherselected = [];
	    $scope.teacherNameselected = [];
	    var updateTeacherSelected = function(action, teacher) {
		      if(action == 'add' && $scope.teacherNameselected.indexOf(teacher.name) == -1){
		    	  $scope.teacherselected.push(teacher);
		    	  $scope.teacherNameselected.push(teacher.name);
		      }
		      if(action == 'remove' && $scope.teacherNameselected.indexOf(teacher.name) != -1){
		    	  $scope.teacherselected.splice($scope.teacherNameselected.indexOf(teacher.name), 1);
		    	  $scope.teacherNameselected.splice($scope.teacherNameselected.indexOf(teacher.name), 1);
		      } 
		    };
	    $scope.updateTeacherSelection = function($event, teacher) {
		      var checkbox = $event.target;
		      var action = (checkbox.checked ? 'add' : 'remove');
		      updateTeacherSelected(action, teacher);
		    
		    };
	    
	    $scope.isTeacherSelected = function(teacherName) {
	    	
		      return $scope.teacherNameselected.indexOf(teacherName) >= 0;
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
		var difference = $scope.classes.classPrice - $scope.classes.classDiscountPrice;
		if (difference < 0) {
			alert("折扣价不能高于原价！");
			return;
		}
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
	$scope.checkedclass = function(c){
		if($scope.selected!=c){
		$scope.selected=c;
		$scope.classes=c;
		$scope.classId=c.id;
		
		$scope.imgUrl=c.imgUrl;
		angular.forEach(c.listCourse,function(course){
			$scope.courseNameselected.push(course.courseName);
			$scope.courseselected.push(course);
		});
		angular.forEach(c.listTeacher,function(teacher){
			$scope.teacherNameselected.push(teacher.name);
			$scope.teacherselected.push(teacher);
		});
		}
		else{
			$scope.selected=null;
			$scope.classes=null;
			$scope.classId=null;
			
			
		}
	}
	$scope.add=function(){
		$scope.classId=null;
		$scope.classes=null;
		$scope.templateBases();
		$scope.teacherBases();
		$scope.courseBases();
		$scope.courseselected = [];
		 $scope.courseNameselected=[];
		 $scope.teacherselected = [];
		 $scope.teacherNameselected = [];
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
			if(confirm("您确定要删除这个班级吗")){
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

	$scope.rest=function(){
		$scope.selected=null;
		$scope.classes=null;
		$scope.classId=null;
		document.getElementById('add').style.display="none"; 
	}
	$scope.refresh=function(){
		location.reload();
	}
	
	$scope.goBuyCourse=function(){
		//////跳转到购买记录页面
		if($scope.classId!=null){
			location.href="/web/student/classbuy?classId="+$scope.classId;
		}else{
			alert("请选中信息~");
		}
		
	}
	
	$scope.removeStudent=function(){
		if($scope.classId!=null){
			$http.get("/api/btl/remove/student",{"params": {"classId":$scope.classId}}, {'Content-Type': 'application/json;charset=UTF-8'})
			.success(function(data){
				if(data.status=='0'){
					alert("移除学员成功~");
				}else{
					alert(data.message);
				}
			})
		}else{
			alert("请选中信息~");
		}
		
	}
	
	$scope.classMoveApi=function(operateType){
		$http.get("/api/course/subclass/sectionOrders",{"params": {"id":$scope.classId,
			"orders":$scope.classes.orders,"operateType":operateType,"tables":"cclass","comment":$scope.courseTypeSubclassName}}, 
			{'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=='0'){
				$scope.classes.orders = data.data;
			}else{
				alert("移动失败~");
			}
		})
	}
	
	$scope.classmove=function(types){
		if($scope.classId!=null){
			if(types==1){
				/////上移
				 var index=$scope.classlist.indexOf($scope.classes);
				  var tmp=angular.copy($scope.classlist[index-1]);
				  if(index==0){
				  alert('已经是第一个了，不能再向上移动了！');
				  location.reload() ;
				  }
				  $scope.classlist[index-1]=$scope.classlist[index];
				  $scope.classlist[index]=tmp;
				  
				$scope.classMoveApi("up");
			}
			if(types==2){
				/////下移
				var index=$scope.classlist.indexOf($scope.classes);
				 
				  if(index==$scope.classlist.length-1){
				  alert('已经是最后一个了，不能再向下移动了！');
				  location.reload() ;
				  }
				  var tmp=angular.copy($scope.classlist[index+1]);
				 
				  $scope.classlist[index+1]=$scope.classlist[index];
				  $scope.classlist[index]=tmp;
				  $scope.classMoveApi("down");
			}
			
		}else{
			alert("请选中信息~");
		}
		
	}
});