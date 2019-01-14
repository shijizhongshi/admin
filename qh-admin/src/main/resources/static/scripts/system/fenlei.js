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
	
	$scope.shopsubBases=function(){
		$http.get("/api/shopdrugsubcategory/select",{"params": {"categoryId":$scope.id}}, {'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				$scope.shopDrugSubcategory=data.data;
			}
		})
	};
	$scope.id=null;
	///////做选中的时候用
	$scope.checkedShopcate=function(s){

		$scope.shopDrugCategory=s;
		$scope.shopDrugCategory.id=s.id;
		$scope.shopDrugCategory.categoryName=s.categoryName;
		
		$scope.id=s.id;
		$scope.shopsubBases();
		$scope.shopcateBases();
	}
	$scope.shopDrugCategory=null;
	
	$scope.addShopcate=function(){
		$scope.shopDrugCategory.id=$scope.id;
		$http.get("/api/shopdrugcategory/insert",$scope.shopDrugCategory, {'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				$scope.shopDrugCategory=data.data;
			}
		})
	};
});