app.controller("ElementController", function($scope, $http){
	
	
	$scope.cateid=$("#cateid").val();
	$scope.name=null;
	
	//总条数
    $scope.total = 0;
    //当前的页数
    $scope.page = 1;
    //一页显示多少条
    $scope.pageSize = 20;
	
    $scope.questionsubcate=function(){
		$scope.pageNo=( $scope.page-1)*$scope.pageSize;
		$http.get("/api/questionsubcategory/select",{"params": {"pageNo":$scope.pageNo,
			"pageSize":$scope.pageSize,
			"categoryId":$scope.cateid}}, {'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				$scope.questionsubcatelist=data.data;
				$scope.total=data.count;
				$scope.subId=$scope.questionsubcatelist[0].id;
				$scope.selecteds=$scope.questionsubcatelist[0].id;
				$scope.questionbank();
				angular.forEach($scope.questionsubcatelist, function(questionSubCategory){  
					
					if(questionSubCategory.isshow==0){
						
						questionSubCategory.show="不展示";
					}
					else if(questionSubCategory.isshow==1){
						questionSubCategory.show="展示";
					}
					
					
				})
			}
		})
	};
	
	$scope.questioncateadd=function(){
		$scope.questionSubCategory.courseTypeName=$scope.courseTypeName;
		$scope.questionSubCategory.categoryId=$scope.cateid;
		$scope.questionSubCategory.courseTypeSubclassName=$scope.courseTypeSubclassName;
		$scope.questionSubCategory.types="章节";
		$http.post("/api/questionsubcategory/insert",$scope.questionSubCategory, {'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				alert("添加成功");
				document.getElementById('add').style.display="none"; 
			$scope.questionsubcate();
			}
			else{
				alert(data.message);
			}
		})
	};
	
	$scope.updatesubCategory=function(){
		$http.post("/api/questionsubcategory/update",$scope.questionSubCategory, {'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				alert("修改成功");
				document.getElementById('add').style.display="none"; 
			$scope.questionsubcate();
			}
			else{
				alert(data.message);
			}
		})
	};
	
	$scope.courseTypeName="医师资格";
	$scope.courseTypeSubclassName="临床(执业)助理医师";
	$scope.questionsubcate();
	
	$scope.checkquestionsubcate=function(qbc){
		
		if($scope.selected!=qbc){
		$scope.selected=qbc;
		$scope.questionSubCategory=qbc;
		$scope.id=qbc.id;
		$scope.name=qbc.name;
		}else{
			$scope.selected=null;
			$scope.questionSubCategory=null;
			$scope.id=null;
			$scope.name=null;
		}
	}
	
	$scope.alerts=function(correct){
		alert(correct);
	}
	
	$scope.questionSub=function(typename,sub,$event){
		$event.stopPropagation();
		$scope.courseTypeName=typename;
		$scope.courseTypeSubclassName=sub.courseTypeSubclassName;
		$scope.questionsubcate();
		$scope.typeSelected=sub.courseTypeSubclassName;
	
	}
	
	$scope.update=function(){
		if($scope.id!=null){
			
		document.getElementById('add').style.display="block"; 
		
		}
		else{
			alert("请选中信息");
		}
	}
	
	$scope.add=function(){
		$scope.selected=null;
		$scope.name=null;
		$scope.id=null;
		$scope.questionSubCategory=null;
		document.getElementById('add').style.display="block"; 
	}
	
	$scope.reset=function(){
		
		document.getElementById('add').style.display="none"; 
		$scope.selected=null;
		$scope.id=null;
		$scope.name=null;
		$scope.questionSubCategory=null;
	}
	
	$scope.deletequestion=function(){
		if($scope.id!=null){
			
			if(confirm("您确定要删除这个单元吗")){
				$http.get("/api/questionsubcategory/delete",{"params": {"id":$scope.id}}, {'Content-Type': 'application/json;charset=UTF-8'})
				.success(function(data){
					if(data.status=='0'){
						alert("删除成功~");
						$scope.id=null;
						$scope.page=1;
						$scope.questionsubcate();
					}else{
						alert("删除失败~");
					}
				})
			}
			
		}else{
			alert("请选中信息~");
		}
	}
	
	
	
	 
    //////////////////////////////////////////////////////华丽的分界线
    //////////////////////////////////////////////////////以下是试题相关
	
	//总条数
    $scope.total1 = 0;
    //当前的页数
    $scope.page1 = 1;
    //一页显示多少条
    $scope.pageSize1 = 20;
    
    $scope.subId=null;
    
	$scope.questionbank=function(){
		$scope.pageNo1=( $scope.page1-1)*$scope.pageSize1;
		$http.get("/api/questionbank/select",{"params": {"pageNo":$scope.pageNo1,
			"pageSize":$scope.pageSize1,
			"subId":$scope.subId}}, {'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				$scope.questionbanklist=data.data;
				$scope.total1=data.count;
				angular.forEach($scope.questionbanklist, function(questionbank){  
					if(questionbank.types=="单选题" || questionbank.types=="多选题"){
						
						$scope.questionanswerlist=questionbank.answer;
						
						angular.forEach($scope.questionanswerlist, function(questionanswer){  
							
							if(questionanswer.correct==true){
								
								questionanswer.corrects="正确";
							}else{
								
								questionanswer.corrects="错误";
							}
						})
					}
					
					else if(questionbank.types=="共用题干" || questionbank.types=="共用选项")	{
						
						$scope.questionunitlist=questionbank.unit;
						
						
							for(i=0;i<$scope.questionunitlist.length;i++){
								
								$scope.questionunitlist[i].shiti="试题"+(i+1);
								
								if($scope.questionunitlist[i].correct==true){
									
									$scope.questionunitlist[i].corrects="正确";
								}else{
									
									$scope.questionunitlist[i].corrects="错误";
								}
								
								
							}
							
							
							
							
							/*angular.forEach($scope.questionunitanswerlist, function(questionunitanswer){  
								
								if(questionunitanswer.correct==true){
									
									questionunitanswer.corrects="正确";
								}else{
									
									questionunitanswer.corrects="错误";
								}
							})*/
							
						
						
					}
				})
			}
		})
	};
	
	$scope.updatequestionbank=function(){
		$scope.questionBank=$scope.questionbanks;
		$scope.questionBank.answer=$scope.questionanswers;
		$http.post("/api/questionbank/update",$scope.questionBank, {'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				alert("修改成功");
				document.getElementById('resource').style.display="none"; 
				document.getElementById('resources').style.display="none"; 
			$scope.questionbank();
			}
			
		})
	};
	
	$scope.updatebank=function(){
		if($scope.bankid!=null){
			if($scope.types=="单选题" || $scope.types=="多选题"){
				
				document.getElementById('resources').style.display="none"; 
				document.getElementById('resource').style.display="block"; 
			}
			else if($scope.types=="共用题干" || $scope.types=="共用选项"){
				
				document.getElementById('resources').style.display="block"; 
				document.getElementById('resource').style.display="none"; 
				$scope.checkshiti($scope.questionunitlist[0]);
				
			}
		
		}
		else{
			alert("请选中信息");
		}
	}
	
	
	
	$scope.checkshiti=function(qbul){
		
		$scope.questionunitanswerlist=qbul.unitAnswer;
		$scope.questionunitlists=qbul;
		$scope.typeselected=qbul;
	}
	
	$scope.checkquestionsub=function(qbc){
		
		$scope.subId=qbc.id;
		$scope.subName=qbc.name;
		$scope.questionbank();
		$scope.selecteds=qbc.id;
	}
	
	$scope.checkquestionbank=function(qb){
		
		if($scope.selected!=qb){
		$scope.selected=qb;
		$scope.questionbanks=qb;
		$scope.bankid=qb.id;
		$scope.types=qb.types;
		$scope.questionunitlist=qb.unit;
		$scope.questionanswers=qb.answer;
		}else{
			$scope.selected=null;
			$scope.questionbanks=null;
			$scope.bankid=null;
			$scope.types=null;
			$scope.questionanswers=null;
		}
	}
	
	$scope.deletequestionbank=function(){
		if($scope.bankid!=null){
			
			if(confirm("您确定要删除这个试题吗")){
				$http.get("/api/questionbank/delete",{"params": {"id":$scope.bankid}}, {'Content-Type': 'application/json;charset=UTF-8'})
				.success(function(data){
					if(data.status=='0'){
						alert("删除成功~");
						$scope.page1=1;
						$scope.bankid=null;
						$scope.questionbank();
					}else{
						alert("删除失败~");
					}
				})
			}
			
		}else{
			alert("请选中信息~");
		}
	}
	
	$scope.addfile=function(){
		
		
		var fd = new FormData();
	    fd.append("file", $("#file")[0].files[0]);
	    fd.append("subId", $scope.subId);
	    
	   $http.post("/api/questionbank/improtExcel",fd, {
	        withCredentials: true,
	        headers: {'Content-Type': undefined },
	        transformRequest: angular.identity
	    })
		.success(function(data){
			if(data.status=='0'){
				alert("导入成功~");
					$scope.file=null;
					location.reload();
				}else{
					alert("导入失败~");
				}
			})
		
	}
	
	$scope.showrevise=function(){
		if($scope.subId!=null){
			document.getElementById('revise').style.display="block"; 
		}else{
		alert("请先选中单元~");
		}
	}
	
	$scope.changefile=function(file){
		
		$scope.file=file;
	}
	
	$scope.resetbank=function(){
		
		if($scope.types=="单选题"||$scope.types=="多选题"){
			
			document.getElementById('resource').style.display="none"; 
		}
		else if($scope.types=="共用题干"||$scope.types=="共用选项"){
			
			document.getElementById('resources').style.display="none"; 
		}
		$scope.selected=null;
		$scope.questionbanks=null;
		$scope.bankid=null;
		$scope.types=null;
		$scope.questionanswers=null;
	}
	
	$scope.changeCorrect=function(qbas){
		
		if($scope.questionbanks.types=="单选题"){
		angular.forEach($scope.questionanswers,function(answers){
			
			
			if(qbas.id!=answers.id){
				
				answers.correct=false;
			}
			
			
		})
		}
	}
	$scope.changeUnitCorrect=function(qbual){
		
		if($scope.questionunitlists.types=="单选题"){
		angular.forEach($scope.questionunitanswerlist,function(unitanswers){
			
			if(qbual.id!=unitanswers.id){
				
				unitanswers.correct=false;
			}
			
		})
		}
	}
	
	
})