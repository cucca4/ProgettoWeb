<meta charset="utf-8"/>

<!-- Linking styles -->
<link rel="stylesheet" href="css/addressbook.css" type="text/css" media="screen">
<title>Rubrica: <%=menuActiveLink%></title>
<script>
  var applicationMessage;
  <%if (applicationMessage != null) {%>//CREA UN PEZZO DI JAVA DINAMICO
    applicationMessage="<%=applicationMessage%>";
  <%}%>
  function onLoadHandler() {
    headerOnLoadHandler(); // dentro header.inc
    try { mainOnLoadHandler(); } catch (e) {}
    if (applicationMessage!=undefined) alert(applicationMessage); //questo popup sara in tutte le pagine perche è in questo file.inc
  }
  window.addEventListener("load", onLoadHandler); // per ogni pag jsp vado a lanciare l'onloadhandler(uguale in tutte) che lancia un headerhandler e poi try
</script> 