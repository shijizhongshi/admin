app.controller("feedbackController", function($scope, $http){
	
	
	 //总条数
    $scope.total = 0;
    //当前的页数
    $scope.page = 1;
    //一页显示多少条
    $scope.pageSize = 20;
    
    $scope.status = 0;
	
	$scope.feedbackList=function(){
		$scope.pageNo=( $scope.page-1)*$scope.pageSize;
		$http.get("/api/feedback/list",{"params": {"pageNo":$scope.pageNo,
			"pageSize":$scope.pageSize,"status":$scope.status,"nickname":$scope.nickname,
			"courseTypeSubclassName":$scope.courseTypeSubclassName,"name":$scope.name}}, 
			{'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				$scope.feedbacklist=data.data;
				$scope.total=data.count;
				angular.forEach($scope.feedbacklist, function(Feedback){  
					if(Feedback.bank.types=="A"){
						
						$scope.questionanswerlist=Feedback.bank.answer;
						
						angular.forEach($scope.questionanswerlist, function(questionanswer){  
							
							if(questionanswer.correct==true){
								
								questionanswer.corrects="正确";
							}else{
								
								questionanswer.corrects="错误";
							}
						})
					}
					
					else if(Feedback.bank.types=="C")	{
						
						$scope.questionunitlist=Feedback.bank.unit;
						
						
							for(i=0;i<$scope.questionunitlist.length;i++){
								
								$scope.questionunitlist[i].shiti="试题"+(i+1);
								
								if($scope.questionunitlist[i].correct==true){
									
									$scope.questionunitlist[i].corrects="正确";
								}else{
									
									$scope.questionunitlist[i].corrects="错误";
								}
								
								
							}
							
					}
				})
			}
		})
	};
	
	$scope.feedbackList();
	
	$scope.updatefeedback=function(){
		if($scope.id!=null){
		$http.get("/api/feedback/update",{"params": {"id":$scope.id,
			"status":1}}, 
			{'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				$scope.operating.operatingStatus="标记已读";
		    	$scope.operating.operatingUser=$scope.feedback.content;
		    	$scope.insertOperating();
		    	
		    	$scope.feedbackList();
		    	document.getElementById('add').style.display="none"; 
			}
			else{
				alert(data.message);
			}
		})
		}else{
			
			alert("请先选中信息");
		}
	};
	$scope.checkfeedback=function(fl){
		
		if($scope.selected!=fl){
		$scope.selected=fl;
		$scope.feedback=fl;
		$scope.questionbanks=fl.bank;
		$scope.id=fl.id;
		$scope.questionanswers=$scope.questionbanks.answer;
		
		}else{
			$scope.selected=null;
			$scope.feedback=null;
			$scope.questionbanks=null;
			$scope.id=null;
			$scope.questionanswers=null;
		}
	}
	
	
	$scope.reset=function(){
		
		document.getElementById('add').style.display="none"; 
		$scope.selected=null;
		$scope.cateId=null;
		$scope.questionCategory=null;
	}
	
	$scope.update=function(){
		if($scope.id!=null){
			if($scope.questionbanks.types=="单选题" || $scope.questionbanks.types=="多选题"){
				
				document.getElementById('resources').style.display="none"; 
				document.getElementById('resource').style.display="block"; 
			
			}
			else if($scope.questionbanks.types=="共用题干" || $scope.questionbanks.types=="共用选项"){
				
				document.getElementById('resources').style.display="block"; 
				document.getElementById('resource').style.display="none"; 
				$scope.checkshiti($scope.questionunitlist[0]);
				
			}
			
		}
		else{
			alert("请选中信息");
		}
	}
	
	
	
	
	
	$scope.refresh=function(){
		
		location.reload();
	}
	
	$scope.checkshiti=function(qbul){
		
		$scope.questionunitanswerlist=qbul.unitAnswer;
		$scope.questionunitlists=qbul;
		$scope.typeselected=qbul;
	}
	
	$scope.updatequestionbank=function(){
		$scope.questionBank=$scope.questionbanks;
		$scope.questionBank.answer=$scope.questionanswers;
		$http.post("/api/questionbank/update",$scope.questionBank, {'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				alert("修改成功")
				$scope.feedbackList();
				document.getElementById('resource').style.display="none"; 
				document.getElementById('resources').style.display="none"; 
			}
			
		})
	};
	
	$scope.deletefeedback=function(){
		if($scope.id!=null){
			
			if(confirm("您确定要删除这个试题吗")){
				$http.get("/api/feedback/delete",{"params": {"id":$scope.id}}, {'Content-Type': 'application/json;charset=UTF-8'})
				.success(function(data){
					if(data.status=='0'){
						alert("删除成功~");
						$scope.id=null;
						$scope.page=1;
						$scope.feedbackList();
					}else{
						alert("删除失败~");
					}
				})
			}
			
		}else{
			alert("请选中信息~");
		}
	}
	
	
	$scope.resetbank=function(){
		
		if($scope.questionbanks.types=="单选题" || $scope.questionbanks.types=="多选题"){
			
			document.getElementById('resource').style.display="none"; 
		}
		else if($scope.questionbanks.types=="共用题干" || $scope.questionbanks.types=="共用选项"){
			
			document.getElementById('resources').style.display="none"; 
		}
		$scope.selected=null;
		$scope.feedback=null;
		$scope.questionbanks=null;
		$scope.types=null;
		$scope.id=null;
		$scope.questionanswers=null;
	}
	
})