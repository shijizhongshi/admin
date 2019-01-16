app.controller("userinfoController", function($scope, $http){
	
	
	$scope.mobile=null;
	$scope.nickname=null;
	$scope.userrole=null;
	
	$scope.selectUser=function(){
		
		$http.get("/api/user/select",{"params":{"mobile":$scope.mobile,"nickname":$scope.nickname,"userrole":$scope.userrole}},
				{'Content-Type':'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				
				$scope.userlist=data.data;
				
				angular.forEach($scope.userlist, function(user){  
					
					if(user.isdisabled==0){
						
						user.disabled="启用";
					}
					else if(user.isdisabled==1){
						user.disabled="禁用";
					}
					
					if(user.userrole==0){
						
						user.userroles="普通用户";
					}
					else if(user.userrole==1){
						user.userroles="商家用户";
					}
				}); 
			}
		})
	}
	
	$scope.selectUser();
	
	$scope.checkuser=function(u){
		if($scope.selected!=u)
		$scope.selected=u;
		else{
			$scope.selected=null;
		}
		}
	
	$scope.changedisabled=function(isdisabled,id){
		if(confirm("您确定要修改这个用户的状态吗？")){
				
				$http.get("/api/user/updateuser",{"params": {"isdisabled":isdisabled,"id":id}}, {'Content-Type': 'application/json;charset=UTF-8'})
				.success(function(data){
					if(data.status!='0'){
						
					alert("修改失败~");
					}
				})
			}
			
		location.reload();
	}
})