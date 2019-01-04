<#import "/layout/header.ftl" as h/>
<#import "/layout/body.ftl" as b/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="后台管理"/>
<style type="text/css">
.classify{width:100%; background:#FFFFFF;}
.classify ul.menu .list{background:#FFFFFF;border-bottom:none;font-size:18px;}
.classify ul.menu .list a:hover{background:#7489A2;color:#FFFFFF;}

.classify ul.menu .list a{color:black}
*:after{color:#E5B70D;}
*:before{color:#E5B70D;}
.items{display:none;}
.active{display:block;}

</style>
<script src="/scripts/course/subnav.js"></script>
<@b.body menu="" submenu="">
<div class="classify" ng-controller="CourseSubnavController">
	<ul class="menu">
	<input type="hidden" value="${type}" id="type">
   <li class="list" ng-click="typeList(1)" >医师资格 
      <ul class="items" ng-class="{'active':active==1}">
         <li ng-repeat="sub in courseTypeSubclass" ng-click="courseSub('医师资格',courseTypeSubclass)">{{sub.courseTypeSubclassName}}</li>
        
      </ul>
   </li>
   <li class="list" ng-click="typeList(2)">药师资格
      <ul class="items" ng-class="{'active':active==2}">
         <li ng-repeat="sub in courseTypeSubclass">{{sub.courseTypeSubclassName}}</li>
        
      </ul>
      </li>
       <li class="list" ng-click="typeList(3)">中医基础理论
      <ul class="items" ng-class="{'active':active==3}">
         <li ng-repeat="sub in courseTypeSubclass">{{sub.courseTypeSubclassName}}</li>
      </ul>
      </li>
       <li class="list" ng-click="typeList(4)">卫生资格 
      <ul class="items" ng-class="{'active':active==4}">
        <li ng-repeat="sub in courseTypeSubclass">{{sub.courseTypeSubclassName}}</li>
        
      </ul>
      </li>
      <li class="list"><a href="#">健康管理师</a> 
      <ul class="items" ng-class="{'active':active==5}">
         <li ng-repeat="sub in courseTypeSubclass">{{sub.courseTypeSubclassName}}</li>
      </ul>
      </li>
      
      
      
      </ul>
      
      
     
</@b.body>

 </html>
