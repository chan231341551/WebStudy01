<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- jpeg-red, png-green, gif-blue -->
<style type="text/css">
   .red{
      background-color: red;
   }
   .green{
      background-color: green;
   }
   .blue{
      background-color: blue;
   }
</style>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-3.6.1.min.js"></script>
</head>
<body>
   <form name="imgForm" action='<%=request.getContextPath()%>/imageStreaming.do'>
   <select name='imgChoice'> 
   
   </select>  
   <input type='submit' value='전송' />
   </form>
   <div id="imgArea">
   		
   </div>
   
   <script type="text/javascript">
   	  const DIVTAG = $("#imgArea");
      const SELECTTAG = $("[name=imgChoice]").on("change", function(event){
         let option = $(this).find("option:selected");
         let mime = option.attr("class");
         let clzName = matchedClass(mime);
         console.log(clzName);
         $(this).removeClass(colors);
         $(this).addClass(clzName);
         
         let srcURL =  document.imgForm.action;
         let queryString = $(document.imgForm).serialize();
         let src = "%U?%P".replace("%U",srcURL).replace("%P",queryString);
         
         let img = $("<img>").attr("src",src)
         DIVTAG.html(img);
<%--      	$('#catList').attr("src","<%=request.getContextPath()%>/imageStreaming.do?imgChoice="+$(this).val()); --%>
      });
      const changeCondition = {
         jpeg:"red"
         , png:"green"
         , gif:"blue"
      }
      const colors = [];
      $.each(changeCondition, function(prop, propValue){
         console.log(prop+", "+propValue);
         colors.push(propValue);
         //console.log(colors)
      });
      let matchedClass = function(mime){
         let clzName = "";
         for(let prop in changeCondition) {
            let idx = mime.indexOf(prop);
            console.log(idx);
            if(idx>=0){
               clzName = changeCondition[prop];
               break;
            } 
         }
         return clzName;
      }
      $.ajax({
    	 
         dataType : "json",
         success : function(resp) {
            console.log(resp);
            let options = [];
             $.each(resp, function (index, file) {
                let option = $("<option>")
                         .addClass(file.mime)   
                         .html(file.name);
                		
                options.push(option);
             });
             SELECTTAG.append(options);
           	 <c:if test="${not empty cookie['imageCookie']}">           	 
	             SELECTTAG.val("${cookie['imageCookie']['value']}");
	             SELECTTAG.trigger('change'); // 트리거로 인해 이벤트 발생
           	 </c:if>
         },
         error : function(jqXHR, status, error) {
            console.log(jqXHR);
            console.log(status);
            console.log(error);
         }
      });
      
      
//       SELECTTAG.on('change', function(){
//          if ($('select option:selected').attr('class') == "image/jpeg") {
//             $(this).attr("class", "image/jpeg red");
//          } else if ($('select option:selected').attr('class') == "image/png") {
//             $(this).attr("class", "image/png green");
//          } else if ($('select option:selected').attr('class') == "image/gif") {
//             $(this).attr("class", "image/gif blue"); 
//          }   else {
//          }
//       });
   </script>
</body>
</html>