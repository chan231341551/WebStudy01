<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>04/messageView.jsp</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-3.6.1.min.js"></script>
</head>
<body>
<select id="selName">
	
</select>
<h4 id="greetingArea" data-key1="prop3" data-key2="sample" data-other-key="TEST"></h4>
<input type="radio" name="dataType" data-data-type="json" checked/>JSON
<input type="radio" name="dataType" data-data-type="xml"/>XML
<input type="radio" name="dataType" data-data-type="text"/>PLAIN

<input type="radio" name="language" data-locale="ko"/>한국어
<input type="radio" name="language" data-locale="en"/>영어
<script>
   let greetingArea = $("#greetingArea");
   console.log(greetingArea.data("key1"));
   console.log(greetingArea.data("otherKey"));
   greetingArea.data("key2", "otherValue");
   console.log(greetingArea.data("key2"));
   let radioBtns = $('[type="radio"]');
   let dataTypes = radioBtns.filter("[name=dataType]");
   let locales = radioBtns.filter("[name=language]");
   let name = greetingArea.data("key1");
   let dataType;
   console.log(name);
   let successes = {
      json:function(resp){
         console.log("resp : " + resp.keyList);
	     $("#selName").empty();
         let options = [];
         $.each(resp.keyList,function(index,str){
        	 //console.log("str : " + str);
        	 
        	 let option = $("<option>")
             .html(str);
   			 options.push(option);
         });
         $("#selName").append(options);
         greetingArea.text(resp.message);
      },
      xml:function(docResp){
         console.log(docResp);
         let message = $(docResp).find("message").text();
         greetingArea.html(message);
      },
      text:function(plain){
         console.log(plain);
         greetingArea.html(plain);
      }
   }
   
   let settings={
      url : "<%=request.getContextPath() %>/04/getGreetingMessage",
      error : function(jqXHR, status, error) {
         console.log(jqXHR);
         console.log(status);
         console.log(error);
      }
   };
   
   radioBtns.on("change", function(){
//       greetingArea.empty();
      greetingArea.html("");
      console.log(name);
      dataType = dataTypes.filter(":checked").data("dataType");
      settings.dataType = dataType;
      settings.success = successes[dataType];
      settings.data = {
      		//name:greetingArea.data("key1")
      		name:greetingArea.attr("data-key1")
      }
      let locale = locales.filter(":checked").data("locale");
      if(locale) {
         /* settings.data={
            locale:locale,
            name:name
         } */
         settings.data.locale = locale;
      }
      console.log("====================")
      console.log(settings);
      console.log("====================")
      $.ajax(settings);
   }).trigger("change");
   
let selName = $("#selName");
console.log("========",selName);
var objOption = document.createElement("option");
objOption.text = selName

$("#selName").change(function(){
	
	let selName = $("#selName option:selected").text();
	console.log(selName);
	$("#greetingArea").attr("data-key1",selName);
	
}).trigger("change")



 
   
</script>
</body>
</html>