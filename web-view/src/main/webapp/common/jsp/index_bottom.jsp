<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- footer -->
<div class="app-footer wrapper b-t bg-light">
  <span class="pull-right">{{app.version}} <a href ui-scroll="app" class="m-l-sm text-muted"><i class="fa fa-long-arrow-up"></i></a></span>
  &copy; 2016 Copyright.
</div>
<!-- / footer -->

<div data-ng-include=" '${rootTemplate}/blocks/settings.htm' " class="settings panel panel-default">
</div>

</div>

</body>
</html>
