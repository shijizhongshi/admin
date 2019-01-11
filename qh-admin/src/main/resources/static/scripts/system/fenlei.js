app.controller("ShopDrugCategoryController", function($scope, $http){

	
	$scope.shopcateBases=function(){
		$http.get("/api/shopdrugcategory/select", {'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				$scope.shopDrugCategory=data.data;
			}
		})
	};
	

	$scope.shopcateBases();
	
	///////做选中的时候用
	$scope.checkedShopcate=function(s){
		$scope.selected=s;
		$scope.shopDrugCategory=s;
		$scope.shopDrugCategory.id=s.id;
	}
});