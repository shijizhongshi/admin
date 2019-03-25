app.controller("IsloginController", function($scope, $http){
	
        //上传主展示图片
    	if($("#username").val()==undefined ||　$("#username").val()==""){
    		location.href="/";
    	}else{
    		$scope.systems=false;
        	$scope.courses=false;
        	$scope.leagues=false;
        	$scope.orders=false;
        	$scope.users=false;
        	$scope.students=false;
        	$scope.manages=false;
        	$scope.banks=false;
        	
        	$http.get("/api/admin/islogin")
            .success(function(data){
            	if(data.status=="0"){
            		if($("#isrole").val()=="1"){
            			$scope.systems=true;
            	    	$scope.courses=true;
            	    	$scope.leagues=true;
            	    	$scope.orders=true;
            	    	$scope.users=true;
            	    	$scope.students=true;
            	    	$scope.manages=true;
            	    	$scope.banks=true;
            			
            		}else if($("#isrole").val()=="2"){
            			$scope.students=true;
            		}else{
            			$scope.meun=data.data;
            			if($scope.meun.indexOf("公共资源管理")>=0){
            				$scope.systems=true;
            			}if($scope.meun.indexOf("网课资源管理")>=0){
            				$scope.courses=true;
            			}if($scope.meun.indexOf("加盟商管理")>=0){
            				$scope.leagues=true;
            			}if($scope.meun.indexOf("订单管理")>=0){
            				$scope.orders=true;
            			}if($scope.meun.indexOf("用户管理")>=0){
            				$scope.users=true;
            			}if($scope.meun.indexOf("学员信息管理")>=0){
            				$scope.students=true;
            			}if($scope.meun.indexOf("发布管理")>=0){
            				$scope.manages=true;
            			}if($scope.meun.indexOf("题库管理")>=0){
            				$scope.banks=true;
            			}
            		}
            		
            		
            		
            	}else{
            		alert(data.message);
            		location.href="/";
            	}
            	
        	});
    	}
    	
    	

});