app.controller("shopServeController", function($scope, $http){
	
	
		$scope.total = 0;
	   //当前的页数
	   $scope.current = 1;
	   //一页显示多少条
	   $scope.pageSize = 20;
	
	   $scope.hot=null;
	   $scope.Status=null;
	   $scope.shopName=null;
	   $scope.serveName=null;
	   $scope.serveType=null;
	   $scope.serveStatus=0;
	   $scope.yunxu=true;
	   $scope.jujue=true;
	   $scope.serveStatused=null;
	  
	   $scope.serveList=function(){
			$scope.pageNo=( $scope.current-1)*$scope.pageSize;
			$http.get("/api/shopserve/list",{"params": {"shopName":$scope.shopName,"serveName":$scope.serveName,
				"serveType":$scope.serveType,"pageNo":$scope.pageNo,"pageSize":$scope.pageSize,"serveStatus":$scope.serveStatus}}, {'Content-Type': 'application/json;charset=UTF-8'})
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
							$scope.yunxu=true;
							$scope.jujue=true;
						}
						else if(serve.serveStatus==1){
							serve.Status="已通过";
							$scope.yunxu=false;
							$scope.jujue=false;
						}
						else if(serve.serveStatus==2){
							serve.Status="已下架";
							$scope.yunxu=false;
							$scope.jujue=false;
						}
						else if(serve.serveStatus==3){
							serve.Status="已删除";
							$scope.yunxu=false;
							$scope.jujue=false;
						}
						else if(serve.serveStatus==4){
							serve.Status="未通过";
							$scope.yunxu=false;
							$scope.jujue=false;
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
			
		}
	
	$scope.updateserve=function(s){
		 
		$scope.s=s;
		
		if(s.serveStatus=="允许"){
			$scope.serveStatused=1;
		}
		else if(s.serveStatus=="拒绝"){
			
			$scope.serveStatused=4;
		}
		$http.get("/api/shopserve/update",{"params": {"ishot":s.ishot,"serveStatus":$scope.serveStatused,
			"id":s.id}}, {'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				alert("修改成功")
				document.getElementById('add').style.display="none"; 
				$scope.serveList();
				
			}
		})
	}
	
})