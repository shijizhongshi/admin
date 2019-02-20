app.controller("fuwushopController", function($scope, $http){
	
		$scope.total = 0;
	   //当前的页数
	   $scope.current = 1;
	   //一页显示多少条
	   $scope.pageSize = 20;
	
	   
	   $scope.istotal = 0;
	   //当前的页数
	   $scope.iscurrent = 1;
	   //一页显示多少条
	   $scope.ispageSize = 20;
	   
	   $scope.islimits = 1;
	   $scope.isrecommend = 0;
	   $scope.address = null;
	   $scope.shopName = null;
	   $scope.fcount=null;
	   
		$scope.shopList=function(){
			$scope.pageNo=( $scope.current-1)*$scope.pageSize;
			$http.get("/api/shop/shopList",{"params": {"pageNo":$scope.pageNo,"shopType":1,
				"address":$scope.address,"shopName":$scope.shopName,"isrecommend":$scope.isrecommend,
				"islimits":$scope.islimits,"pageSize":$scope.pageSize}}, {'Content-Type': 'application/json;charset=UTF-8'})
			.success(function(data){
				if(data.status=="0"){
					$scope.shoplist=data.data;
					$scope.total=data.count;
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
			$scope.pageNo=( $scope.iscurrent-1)*$scope.ispageSize;
			$http.get("/api/shop/shopList",{"params": {"pageNo":$scope.pageNo,"pageSize":$scope.ispageSize,"shopType":1,
				"address":null,"shopName":$scope.shopName,"isrecommend":null,
				"islimits":0}}, {'Content-Type': 'application/json;charset=UTF-8'})
			.success(function(data){
				if(data.status=="0"){
					$scope.islimitlist=data.data;
					$scope.istotal=data.count;
					
				}
			})
		}
		$scope.islimitList();
	
		
		$scope.checkshop=function(s){
			
			 if($scope.selected!=s){
					$scope.selected=s;
			 		$scope.id=s.id;
			 		$scope.shop=s;
			 		$scope.shopId=s.id;
			 		
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
		
		$scope.updateshop=function(s){
			 
			$http.get("/api/shop/updateShop",{"params": {"islimits":$scope.islimits,"isrecommend":s.isrecommend,"userId":s.userId,"shopType":1,
				"id":s.id}}, {'Content-Type': 'application/json;charset=UTF-8'})
			.success(function(data){
				if(data.status=="0"){
					alert("修改成功")
					document.getElementById('revise').style.display="none"; 
					$scope.islimitList();
					location.reload();
				}
				else if(data.status=="1"){
					
					alert(data.message);
				}
			})
	}
		
		 $scope.reset=function(){
			 $scope.shopList();
			 $scope.islimitList();
				document.getElementById('revise').style.display="none"; 
				
				
		}
		 $scope.fuwucount=function(){

				$http.get("/api/shop/shopcount",{"params": {"shopType":1,"islimits":0}}, {'Content-Type': 'application/json;charset=UTF-8'})
				.success(function(data){
					if(data.status=="0"){
						$scope.fcount=data.count;
						$scope.ffcount="审核列表( "+$scope.fcount+" )";
					}
				})
		}
			$scope.fuwucount();
			
			$scope.guanli=function(){
				if($scope.shopId!=null){
					location.href="/web/league/xiangmu?shopId="+$scope.shopId;
				}else{
					alert("请选中信息~");
				}
			}
			
			$scope.refresh=function(){
				
				location.reload();
				
			}
})