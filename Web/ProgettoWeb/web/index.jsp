<%@page session="false"%>
<!DOCTYPE HTML>
<html lang="it-IT">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="refresh" content="0; url=/ProgettoWeb/Dispatcher">
    <script type="text/javascript">
      function onLoadHandler() {
        window.location.href = "/ProgettoWeb/Dispatcher";
      }
      window.addEventListener("load", onLoadHandler);
    </script>
    <title>Page Redirection</title>
  </head>
  <body>
    If you are not redirected automatically, follow the <a href='/ProgettoWeb/Dispatcher'>link</a>
  </body>
</html>
