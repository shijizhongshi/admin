<#import "/layout/header.ftl" as h/>

<!DOCTYPE html>
<html lang="en">
<meta name="referrer" content="always">
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
<@h.header title="课程管理页面"/>
<link rel="stylesheet" href="./styles/admin.css" />
<script src='https://player.polyv.net/script/polyvplayer.min.js'></script>
<script src="/scripts/questionbank.js"></script>

<body ng-app="app">
<!--<div class="details" ng-controller="bankController">

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
 
<div id='polyved'></div>
</div>-->
<!--<div>
<div ng-controller="myCtrl">
xiaohaiyaoguan
<div ng-controller="myCtrl1">
<input type="button" value="测试" ng-click="test()"/>
xiaohaiyaoguan11111
</div>
</div>

</div>
<script src='' type='text/javascript'></script>
<video><playcode><![CDATA[
-->

<div ng-controller="bankController">

<div>
上传视频
<input type="file" id="file" onchange="angular.element(this).scope().uploadmainimage(this)"/>
</div>

<iframe id="{{scriptss2}}" 
src="{{trustSrc()}}" 
frameborder="0" height="490" width="600"></iframe>

</div>

</body>
 
 </html>
