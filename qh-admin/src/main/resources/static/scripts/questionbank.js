app.controller("bankController", function($scope, $http,$sce){

	
	
	
//上传主展示图片
	$scope.uploadmainimage = function(file){
		if(!file.files || file.files.length < 1) return;
	    var fd = new FormData();
	    fd.append("file", file.files[0]);
		$http.post("/api/upload/video",fd,{
	        withCredentials: true,
	        headers: {'Content-Type': undefined },
	        transformRequest: angular.identity
	    })
	    .success(function(data){
	    	//////成功之后返回的cc video的信息
	    	
		})
	};
	

/*$http.get("/api/coursenofree/polyv",{"params": {"vid":"b826fec7541e00ae5c2364c1a6c42870_b"}}, {'Content-Type': 'application/json;charset=UTF-8'})
.success(function(data){
	if(data.status=="0"){
		$scope.video=data.data;
		if($scope.video.videoId!=null){
			var player = polyvObject('#polyved').videoPlayer({
			    'width':'90%',
			    'height':'100',
			    'vid':$scope.video.videoId,
			    'ts':$scope.video.ts,
			    'sign':$scope.video.sign
			});
		}
		
	}
})*/
//$scope.videourl="https://p.bokecc.com/player?vid=7E8E3E5FFA79D1C69C33DC5901307461&siteid=91DD94C27B488135&autoStart=false&width=600&height=490&playerid=023C4DD30D07346E&playertype=1";
/*	$(':element').append('<script>……</script>');
	// $('body').append('<script>……</script>');
	var scr = document.createElement('script');
	scr.innerText='https://p.bokecc.com/player?vid=7E8E3E5FFA79D1C69C33DC5901307461&siteid=91DD94C27B488135&autoStart=false&width=600&height=490&playerid=023C4DD30D07346E&playertype=1'
	document.body.appendChild(scr)*/
	$scope.scriptss1="https://p.bokecc.com/playhtml.bo?vid=7E8E3E5FFA79D1C69C33DC5901307461&siteid=91DD94C27B488135&autoStart=false&playerid=023C4DD30D07346E&playertype=1";
	 $scope.serverUrl = 'https://www.baidu.com/';
     $scope.trustSrc = function() {
         return $sce.trustAsResourceUrl($scope.scriptss1);
     }
     $scope.scriptss2="cciframe_7E8E3E5FFA79D1C69C33DC5901307461";
});


/*var app=angular.module("app",[]);
app.controller('myCtrl', ['$scope','appService',function ($scope,appService) {
	$scope.$on('to-parent', function(d,data) {  
        console.log(data);         //父级能得到值  
    });  
}]);
app.controller('myCtrl1', ['$scope', 'appService',function ($scope,appService) {
    $scope.name=appService.name;
    $scope.test=function(){
    	 $scope.$emit('to-parent', "geifuji");  
    }
}]);
app.service("appService", [function(){
    this.name="hello";
}]);*/
