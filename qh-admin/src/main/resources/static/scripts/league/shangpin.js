app.controller("shangpinshopController", function($scope, $http){
	
		$scope.total = 0;
	   //当前的页数
	   $scope.current = 1;
	   //一页显示多少条
	   $scope.pageSize = 20;
	
	   
	   $scope.islimits = 1;
	   $scope.isrecommend = 0;
	   $scope.address = null;;
	   $scope.shopName = null;
	   $scope.scount=null;
	   
		$scope.shopList=function(){
			$scope.pageNo=( $scope.current-1)*$scope.pageSize;
			$http.get("/api/shop/shopList",{"params": {"pageNo":$scope.pageNo,"shopType":2,
				"address":$scope.address,"shopName":$scope.shopName,"isrecommend":$scope.isrecommend,
				"islimits":$scope.islimits,"pageSize":$scope.pageSize}}, {'Content-Type': 'application/json;charset=UTF-8'})
			.success(function(data){
				if(data.status=="0"){
					$scope.shoplist=data.data;
					
					angular.forEach($scope.shoplist, function(shop){  
						
						if(shop.isrecommend==0){
							
							shop.recommend="不推荐";
						}
						else if(shop.isrecommend==1){
							shop.recommend="推荐";
						}
					})
				
				}else{
					alert("没有符合条件的医生信息")
				}
			})
		}
		$scope.shopList();
	
		$scope.islimitList=function(){
			$scope.pageNo=( $scope.current-1)*$scope.pageSize;
			$http.get("/api/shop/shopList",{"params": {"pageNo":$scope.pageNo,"pageSize":$scope.pageSize,"shopType":2,
				"address":null,"shopName":$scope.shopName,"isrecommend":null,
				"islimits":0}}, {'Content-Type': 'application/json;charset=UTF-8'})
			.success(function(data){
				if(data.status=="0"){
					$scope.islimitlist=data.data;
					
				}
			})
		}
		$scope.islimitList();
	
		
		$scope.checkshop=function(s){
			
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
			
			 if(s.islimits==1){
			document.getElementById('islimits').style.display="none"; 
			 
			 
			 }
			 else{
				 document.getElementById('islimits').style.display="block"; 
			 }
			 
			 document.getElementById('revise').style.display="block"; 
			 $scope.s=s;
			
		}
		
		$scope.shangpincount=function(){
			 
			$http.get("/api/shop/shopcount",{"params": {"shopType":2}}, {'Content-Type': 'application/json;charset=UTF-8'})
			.success(function(data){
				if(data.status=="0"){
					$scope.scount=data.data;
					$scope.sscount="审核列表( "+$scope.scount+" )";
				}
			})
	}
		$scope.shangpincount();
		
		$scope.updateshop=function(s){
			 
			$http.get("/api/shop/updateShop",{"params": {"islimits":$scope.islimits,"isrecommend":s.isrecommend,
				"id":s.id}}, {'Content-Type': 'application/json;charset=UTF-8'})
			.success(function(data){
				if(data.status=="0"){
					alert("修改成功")
					document.getElementById('revise').style.display="none"; 
					$scope.islimitList();
					location.reload();
					$scope.shangpincount();
				}
			})
	}
		
		 $scope.reset=function(){
			 $scope.shopList();
			 $scope.islimitList();
				document.getElementById('revise').style.display="none"; 
				
				
		}
		 
})