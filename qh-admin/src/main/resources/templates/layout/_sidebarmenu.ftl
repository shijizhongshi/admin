
<#macro sidebarmenu activemenuid activesubmenuid>
    <!-- add class "sidebar-collapsed" to close sidebar by default, "chat-visible" to make chat appear always -->
    <!-- Add "fixed" class to make the sidebar fixed always to the browser viewport. -->
    <!-- Adding class "toggle-others" will keep only one menu item open at a time. -->
    <!-- Adding class "collapsed" collapse sidebar root elements and show only icons. -->
    <style>
    .subactive{color:#ffffff!important;}
    .displeyactive{display:block!important;}
    </style>
    <div class="sidebar-menu toggle-others fixed">
        <div class="sidebar-menu-inner" ng-controller="IsloginController">
            <header class="logo-env" style="background-color:#e9484d">
                <!-- logo -->
                <div class="logo">
                    <a href="/" class="logo-expanded">
                         <img src="/images/sjk-.png" width="100%" alt=""/>
                    </a>
                    <a href="/" class="logo-collapsed">
                        <img src="/assets/images/logo-collapsed@2x.png" width="40" alt=""/>
                    </a>
                </div>
                <input type="hidden" value="${activemenuid}" class="activemenuid">
                 <input type="hidden" value="${activesubmenuid}" class="activesubmenuid">
                <!-- This will toggle the mobile menu and will be visible only on mobile devices -->
                <div class="mobile-menu-toggle visible-xs">
                    <!--<a href="#" data-toggle="user-info-menu">
                        <i class="fa-bell-o"></i>
                        <span class="badge badge-success">7</span>
                    </a>-->
                    <a href="#" data-toggle="mobile-menu">
                        <i class="fa-bars"></i>
                    </a>
                </div>
                <!-- This will open the popup with user profile settings, you can use for any purpose, just be creative -->
                <!--<div class="settings-icon">
                    <a href="#" data-toggle="settings-pane" data-animate="true">
                        <i class="linecons-cog"></i>
                    </a>
                </div>-->
            </header>
            
            <ul id="main-menu" class="main-menu">
                <!-- add class "multiple-expanded" to allow multiple submenus to open -->
                <!-- class "auto-inherit-active-class" will automatically add "active" class for parent elements who are marked already with class "active" -->
                 
                  <li ng-repeat="menu in menus" ng-click="getOpenSub(menu)" ng-class="{'active':active==menu}">
                    <a>
                        <i class="linecons-cog"></i>
                        <span class="title">{{menu.names}}</span>
                        <i style="float: right;" class="menuxy" >></i>
                    </a>
                    <ul ng-class="{'displeyactive':displeyactive==menu}">
                        <li ng-repeat="sub in menu.list" >
                            <a href="{{sub.outLinks}}">
                                <span class="title" ng-class="{'subactive':subactive==sub}">{{sub.names}}</span>
                            </a>
                        </li>
                       

                    </ul>
                </li>
                
                
                

             </ul>
             
        </div>
    </div>
    <script type="text/javascript">
        jQuery(document).ready(function($){
         
    		var menuid = "${activemenuid}";
            var submenuid = "${activesubmenuid}";
            
           
            $("#"+menuid).addClass("active").addClass("expanded").addClass("opened");
            $("#"+submenuid).addClass("active");
        	
		});
    </script>
<style>
.active .menuxy{transform:rotate(90deg);-webkit-transform:rotate(90deg);
    -moz-transform:rotate(90deg);transition: all 0.3s;}
</style>
</#macro>