app.controller("questionChapter", function($scope, $http){
	
	
	 //总条数
    $scope.total = 0;
    //当前的页数
    $scope.current = 1;
    //一页显示多少条
    $scope.pageSize = 20;
	
	$scope.active=1;
	$scope.typeId=1;
	$scope.typeSelected=null;
	$scope.selected=null;
	$scope.questionCategory=null;
	$scope.cateId=null;
	
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
				$scope.questioncate();
			
			}
		})
	};
	$scope.typeBases();
	
	$scope.questioncate=function(){
		$scope.pageNo=( $scope.current-1)*$scope.pageSize;
		$http.get("/api/questionCategory/select",{"params": {"pageNo":$scope.pageNo,
			"pageSize":$scope.pageSize,
			"courseTypeSubclassName":$scope.courseTypeSubclassName,
			"types":"章节"}}, {'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				$scope.questioncatelist=data.data;
				$scope.total=data.count;
				angular.forEach($scope.questioncatelist, function(questionCategory){  
					
					if(questionCategory.isshow==0){
						
						questionCategory.show="不展示";
					}
					else if(questionCategory.isshow==1){
						questionCategory.show="展示";
					}
					
					
				})
			}
		})
	};
	
	$scope.questioncateadd=function(){
		$scope.questionCategory.courseTypeName=$scope.courseTypeName;
		$scope.questionCategory.courseTypeSubclassName=$scope.courseTypeSubclassName;
		$scope.questionCategory.types="章节";
		$http.post("/api/questionCategory/insert",$scope.questionCategory, {'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				alert("添加成功")
			location.reload();
			}
			else{
				alert(data.message);
			}
		})
	};
	
	$scope.updateCategory=function(){
		$http.post("/api/questionCategory/update",$scope.questionCategory, {'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				alert("修改成功")
			location.reload();
			}
			else{
				alert(data.message);
			}
		})
	};
	
	$scope.courseTypeName="医师资格";
	$scope.courseTypeSubclassName="临床(执业)助理医师";
	$scope.questioncate();
	
	$scope.checkquestioncate=function(qc){
		
		if($scope.selected!=qc){
		$scope.selected=qc;
		$scope.questionCategory=qc;
		$scope.cateId=qc.id;
		$scope.name=qc.name;
		}else{
			$scope.selected=null;
			$scope.questionCategory=null;
			$scope.cateId=null;
		}
	}
	
	$scope.questionSub=function(typename,sub,$event){
		$event.stopPropagation();
		$scope.courseTypeName=typename;
		$scope.courseTypeSubclassName=sub.courseTypeSubclassName;
		$scope.questioncate();
		$scope.typeSelected=sub.courseTypeSubclassName;
	
	}
	
	$scope.update=function(){
		if($scope.cateId!=null){
			
		document.getElementById('add').style.display="block"; 
		
		}
		else{
			alert("请选中信息");
		}
	}
	
	$scope.add=function(){
		$scope.selected=null;
		$scope.cateId=null;
		$scope.questionCategory=null;
		document.getElementById('add').style.display="block"; 
	}
	
	$scope.reset=function(){
		
		document.getElementById('add').style.display="none"; 
		$scope.selected=null;
		$scope.cateId=null;
		$scope.questionCategory=null;
	}
	
	$scope.deletequestion=function(){
		if($scope.cateId!=null){
			
			if(confirm("您确定要删出这个题库章节吗")){
				$http.get("/api/questionCategory/delete",{"params": {"id":$scope.cateId}}, {'Content-Type': 'application/json;charset=UTF-8'})
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
	$scope.tojie=function(){
		if($scope.cateId!=null){
			
			location.href="/web/questionBank/questionJie?id="+$scope.cateId+"&name="+$scope.name;
			
		}else{
			alert("请选中信息~");
		}
	}
})