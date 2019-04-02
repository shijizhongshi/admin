app.controller("userinfoController", function($scope, $http){
	
	if($("#nickname").val()!=''){
		
		$scope.nickname=$("#nickname").val();
	}else{
		$scope.nickname=null;
	}
	$scope.mobile=null;
	$scope.userrole=null;
	
		//总页数
	$scope.total = 0;
	   //当前的页数
	$scope.page = 1;
	   //一页显示多少条
	$scope.pageSize = 20;
	
	$scope.selectUser=function(){
		
		$http.get("/api/user/select",{"params":{"page":$scope.page,"mobile":$scope.mobile,"nickname":$scope.nickname,"userrole":$scope.userrole}},
				{'Content-Type':'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				
				$scope.userlist=data.data;
				$scope.total=data.count;
				angular.forEach($scope.userlist, function(user){  
					
					if(user.isdisabled==0){
						
						user.disabled="启用";
					}
					else if(user.isdisabled==1){
						user.disabled="禁用";
					}
					
					if(user.isdoctor==0){
						
						user.isdoctors="普通用户";
					}
					else if(user.isdoctor==1){
						user.isdoctors="医生用户";
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
	
	$scope.changedisabled=function(isdisabled,id,mobile){
		if(confirm("您确定要修改这个用户的状态吗？")){
				
				$http.get("/api/user/updateuser",{"params": {"isdisabled":isdisabled,"id":id}}, {'Content-Type': 'application/json;charset=UTF-8'})
				.success(function(data){
					if(data.status!='0'){
						$scope.operating.operatingStatus="修改用户状态";
				    	$scope.operating.operatingUser=$scope.mobile;
				    	$scope.insertOperating();
					alert("修改失败~");
					}
				})
			}
			
		location.reload();
	}
	$scope.operating={operatingScope:"用户信息",userRoleUsername:$("#username").val(),operatingStatus:"",operatingUser:""}
	$scope.insertOperating = function(){
		
		$http.post("/api/operating/insert",$scope.operating, {'Content-Type': 'application/json;charset=UTF-8'})
	    
	};
})