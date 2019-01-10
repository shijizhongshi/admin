<#import "/layout/header.ftl" as h/>

<!DOCTYPE html>
<html lang="en">
<meta http-equiv="Access-Control-Allow-Origin" content="*" />
<@h.header title="课程管理页面"/>
<link rel="stylesheet" href="./styles/admin.css" />
<script src="/scripts/questionbank.js"></script>
<body ng-app="app">
<div class="details" ng-controller="bankController">

<body>
<form>

<div>
Writetoken:
</div>
<div>
  JSONRPC:onchange="angular.element(this).scope().uploadmainimage(this)"
</div>
<div>
<input name="writetoken" id="writetoken" value="a1df864b-405e-4782-9494-733e9b51c5d5" type="hidden">
<input name="JSONRPC" type="hidden" id="JSONRPC" value="{'title': '标题', 'tag':'标签','desc':'描述'}">
Filedata:<input name="Filedata" id="file" type="file" onchange="angular.element(this).scope().uploadmainimage(this)" accept=".avi, .wmv, .mp4, .mp3, .mov, .flv, .mkv, .rmvb">
</div>

<div>
</div>
</form>
 
 <script>
 

</script>
</div>
</body>
 
 </html>
