app.controller("replyPatientController", function($scope, $http){
	
		$scope.replypatient=function(){
			$http.get("/api/patient/single",{"params": {"id":$("#id").val()}}, {'Content-Type': 'application/json;charset=UTF-8'})
			.success(function(data){
				if(data.status=="0"){
					$scope.replypatient=data.data;
					$scope.imglist=$scope.replypatient.listimg;
					$scope.replyList=$scope.replypatient.listreply;
				}else{
					alert("没有符合条件的信息")
				}
			})
		}
	   $scope.replypatient();
	   
	   $scope.patientdelete=function(){
			if(confirm("您确定要删除这个信息吗")){
				$http.get("/api/patient/delete",{"params": {"id":$("#id").val()}}, {'Content-Type': 'application/json;charset=UTF-8'})
				.success(function(data){
					if(data.status=="0"){
						$scope.returnpatient();
					}
				})
				}
			
		}
	   
	   $scope.checkimg=function(il){
		   $scope.selectdelete=il;
			
		}
	   
	   
	   $scope.deletereply=function(rl){
			if(confirm("您确定要删除这个信息吗")){
				$http.get("/api/replypatient/delete",{"params": {"id":rl.id}}, {'Content-Type': 'application/json;charset=UTF-8'})
				.success(function(data){
					if(data.status=="0"){
						location.reload();
					}
				})
				}
			
		}
	   
	  $scope.deleteimg=function(il){
			if(confirm("您确定要删除这个信息吗")){
				$http.get("/api/patientimg/delete",{"params": {"id":il.id}}, {'Content-Type': 'application/json;charset=UTF-8'})
				.success(function(data){
					if(data.status=="0"){
						location.reload();
					}
				})
				}
			
		}
	   
	   $scope.returnpatient=function(){
		   $scope.id=id;
			if($scope.id!=null){
				location.href="/web/patientmanage/patient";
			}else{
				alert("请选中信息~");
			}
		}
	   
	   
	   $scope.refresh=function(){
			
			location.reload();
			
		}
	   
	   $scope.touser=function(nickname){
		   $scope.nickname=nickname;
		   if($scope.nickname!=null){
				location.href="/web/user/userinfo?nickname="+$scope.nickname;
			}else{
				alert("请选中信息~");
			}
		}
	  	
})