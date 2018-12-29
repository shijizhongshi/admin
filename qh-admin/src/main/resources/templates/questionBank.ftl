<#import "/layout/header.ftl" as h/>

<!DOCTYPE html>
<html lang="en">
<meta http-equiv="Access-Control-Allow-Origin" content="*" />
<@h.header title="课程管理页面"/>
<link rel="stylesheet" href="./styles/admin.css" />
<script src="/scripts/questionbank.js"></script>
<body ng-app="app">
<div class="details" ng-controller="bankController">



 <input type="file" onchange="angular.element(this).scope().uploadmainimage(this)" />
</div>
</body>
 
 </html>
