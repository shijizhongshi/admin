app.controller("fuwushopController", function($scope, $http){
	
		$scope.total = 0;
	   //当前的页数
	   $scope.current = 1;
	   //一页显示多少条
	   $scope.pageSize = 20;
	
	   $scope.shopList=function(){
			$scope.pageNo=( $scope.current-1)*$scope.pageSize;
			$http.get("/api/shop/shopList",{"params": {"pageNo":$scope.pageNo,"shopType":$scope.shopType,
				"islimits":1,"pageSize":$scope.pageSize}}, {'Content-Type': 'application/json;charset=UTF-8'})
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
				$scope.id=null;
				}else{
					alert("没有符合条件的医生信息")
				}
			})
		}
		$scope.shopList();
	
	
	
})