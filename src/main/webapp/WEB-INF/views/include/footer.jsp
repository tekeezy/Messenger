<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>



<!-- footer begin -->

<script>
// Script to open and close sidenav


  function delete_image() {

      var img = document.getElementById('delete_img');
      img.submit();
  }

  function goSearch() {
      form2.submit();
    }
  

   

function w3_open() {
    document.getElementById("mySidenav").style.display = "block";
    document.getElementById("myOverlay").style.display = "block";
}
 
function w3_close() {
    document.getElementById("mySidenav").style.display = "none";
    document.getElementById("myOverlay").style.display = "none";
}


function myFunction() {
    var x = document.getElementById("search");
    if (x.className.indexOf("w3-show") == -1) {
        x.className += " w3-show";
    } else { 
        x.className = x.className.replace(" w3-show", "");
    }
}

</script>


</body>
</html>
<!-- footer end -->