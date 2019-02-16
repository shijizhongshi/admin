app.controller("shopServeController", function($scope, $http){
	
	
		$scope.total = 0;
	   //当前的页数
	   $scope.current = 1;
	   //一页显示多少条
	   $scope.pageSize = 20;
	   
	   if($("#shopId").val()!=''){
			////说明是在服务点进来的
			$scope.shopId=$("#shopId").val();
			$scope.serveStatus=null;
			
	   }else{
		   $scope.shopId=null;
		   $scope.serveStatus=0;
	   }
	   $scope.hot=null;
	   $scope.Status=null;
	   $scope.shopName=null;
	   $scope.serveName=null;
	   $scope.serveType=null;
	   
	   $scope.id=null;
	   $scope.ishot=null;
	   
	  
	   $scope.serveList=function(){
			$scope.pageNo=( $scope.current-1)*$scope.pageSize;
			$http.get("/api/shopserve/list",{"params": {"shopName":$scope.shopName,"serveName":$scope.serveName,
				"serveType":$scope.serveType,"pageNo":$scope.pageNo,"pageSize":$scope.pageSize,"serveStatus":$scope.serveStatus,"shopId":$scope.shopId}}, {'Content-Type': 'application/json;charset=UTF-8'})
			.success(function(data){
				if(data.status=="0"){
					$scope.servelist=data.data;
					
					angular.forEach($scope.servelist, function(serve){  
						
						if(serve.ishot==0){
							
							serve.hot="不推荐";
						}
						else if(serve.ishot==1){
							serve.hot="推荐";
						}
						
						if(serve.serveStatus==0){
							
							serve.Status="待审核";
							serve.yunxu=true;
							serve.jujue=true;
						}
						else{
							serve.yunxu=false;
							serve.jujue=false;
							
						}
						 if(serve.serveStatus==1){
							serve.Status="已通过";
							
						}
						else if(serve.serveStatus==2){
							serve.Status="已下架";
							
						}
						else if(serve.serveStatus==3){
							serve.Status="已删除";
							
						}
						else if(serve.serveStatus==4){
							serve.Status="未通过";
							
						}
						
					})
				
				}else{
					alert("没有符合条件的信息")
				}
			})
		}
		$scope.serveList();
		
		$scope.servetypeList=function(){
			$http.get("/api/shopservetype/select", {'Content-Type': 'application/json;charset=UTF-8'})
			.success(function(data){
				if(data.status=="0"){
					$scope.servetypelist=data.data;
					
				}else{
					$scope.servetypelist=null;
				}
			})
		}
		$scope.servetypeList();		
		
		$scope.checkserve=function(s){
			
			 if($scope.selected!=s){
					$scope.selected=s;
			 		$scope.id=s.id;
			 		$scope.shop=s;
			 		
			 		
			 }else{
						$scope.selected=null;
						$scope.id=null;
					}
		}
		
	$scope.checkedAll=function(s){
			
		 document.getElementById('add').style.display="block"; 
		 $scope.s=s;
		 $scope.$emit('to-parent',s);  
			
		}
	
	$scope.updateserve=function(ishot,serveStatused,id){
		if(confirm("您确定修改吗")){
		$http.get("/api/shopserve/update",{"params": {"ishot":ishot,"serveStatus":serveStatused,
			"id":id}}, {'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				alert("修改成功");
				document.getElementById('add').style.display="none"; 
				$scope.serveList();
				
			}
			else{
				
				alert("修改失败");
			}
		})
		}
	}
	
	$scope.refresh=function(){
		
		location.reload();
		
	}
})

//app.controller("shopServeControllered", function($scope, $http){
	
	//$scope.$on('to-parent', function(d,data) { 
	//	$scope.s=data;   //父级能得到值  
	//    });
//})