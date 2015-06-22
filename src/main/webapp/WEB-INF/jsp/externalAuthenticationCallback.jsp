<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
    </head>
    <body>
        Finishing External Authentication
        <script type="text/javascript">
            var params = document.location.search.replace(/(^\?)/,'')
                .split("&")
                .map(function(n) {
                    var n = n.split("=");
                    this[n[0]] = n[1]
                    return this;
                }.bind({}))[0];

            window.opener.continueAuthentication("${provider}", params);
            window.close();
        </script>
    </body>
</html>
