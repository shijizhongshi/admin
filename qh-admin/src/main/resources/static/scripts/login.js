app.controller("loginController", function($scope, $http){
	
//上传主展示图片
$scope.login = function(){
	$http.get("/api/admin/login",{"params": {"username":$scope.username,"password":$scope.password}}, {'Content-Type': 'application/json;charset=UTF-8'})
    .success(function(data){
    	if(data.status=="0"){
    		alert("登录页成功~");
    		location.href="/web/system/banner";
    	}else{
    		alert(data.message);
    	}
    	
	})
};

});