<html>
<head>
 <script src="https://code.jquery.com/jquery-1.10.2.min.js"
 	integrity="sha256-C6CB9UYIS9UJeqinPHWTHVqh/E1uhG5Twh+Y5qFQmYg="
 	crossorigin="anonymous">
 </script>
 
 <script type="text/javascript">
 
 function call(jobName) { 
    var data = {"job":jobName};
    $.ajax({
     type : "POST",
     datatype : "json",
     url : "/SpringBatchScheduler/job/run",
     contentType: "application/json; charset=utf-8",
     data : JSON.stringify(data),
     success : function(data, textStatus) {
      //alert("executing");
     },
        statusCode: {
      901: function() {
       window.location.href=window.location.href;
      }
     }
    });
   }
  
 </script>
</head>
<body>
 <input type="button" value="Execute Tasklet" onclick="call('executeTasklet')"/>
 <input type="button" value="Execute Job" onclick="call('executeJob')"/>
 <input type="button" value="Execute Lotto Job" onclick="call('lottoJob')"/>
</body>
</html>