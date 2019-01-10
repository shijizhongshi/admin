app.controller("bankController", function($scope, $http){

	
	
	
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
	


});