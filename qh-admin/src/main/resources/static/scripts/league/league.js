 function go(n){
 			var tabs=document.getElementById("details-frame-content").getElementsByTagName("li");
 			var show=document.getElementsByClassName("manage");
            for(var i=0;i<tabs.length;i++){
                 tabs[i].style.borderBottom='none';
            	show[i].style.display='none';
            	tabs[i].style.fontWeight='200';
                if(i==n){
 
                	tabs[i].style.borderBottom='3px solid red';
                    show[i].style.display='block';
                    tabs[i].style.fontWeight='900';
                }

            }


        }
 