/*app.controller("bankController", function($scope, $http){

	
	
	
//上传主展示图片
$scope.uploadmainimage = function(file){
	if(!file.files || file.files.length < 1) return;
	var formData = new FormData();
	formData.append('Filedata', $('#file')[0].files[0]);
	formData.append("writetoken","a1df864b-405e-4782-9494-733e9b51c5d5");
	formData.append("JSONRPC","{'title': '标题', 'tag':'标签','desc':'描述'}");
	$.ajax({
	    url: 'https://v.polyv.net/uc/services/rest?method=uploadfile',
	    type: 'POST',
	    data: formData,
	    cache: false,
	    processData: false,
	    contentType: false
	}).success(function(res) {
		alert(res.error);
		console.log(res.data[0]);
		console.log(res.data[0].vid);
		console.log(res.data[0].mp4);
	}).fail(function(res) {
		
	});
};
	

$http.get("/api/coursenofree/polyv",{"params": {"vid":"b826fec7541e00ae5c2364c1a6c42870_b"}}, {'Content-Type': 'application/json;charset=UTF-8'})
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
})

});*/


var app=angular.module("app",[]);
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
}]);
