<html lang="en">
	<head>
	    <title>Timeline JS Example</title>
	    <meta charset="utf-8">
	    <meta content="yes" name="apple-mobile-web-app-capable">
	    <meta content="yes" name="apple-touch-fullscreen">
	    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0" name="viewport">
	    <style>
	      html, body {
	       height:100%;
	       padding: 0px;
	       margin: 0px;
	      }
	    </style>
	    <!-- HTML5 shim, for IE6-8 support of HTML elements--><!--[if lt IE 9]>
	    <script src="styles/platform/htimelinecontroller/html5.js"></script><![endif]-->
	  <style class="lazyload" charset="utf-8">@import "styles/platform/htimelinecontroller/timeline.css"</style>
	  <script src="scripts/jqurey/jquery.js" class="lazyload" charset="utf-8"></script>
	  <script src="scripts/platform/htimelinecontroller/TimelineJS.min.js" class="lazyload" charset="utf-8"></script>
	</head>
  <body>
      <!-- BEGIN Timeline Embed -->
      <div id="timeline-embed" ></div>
      <script type="text/javascript">
      	var timeline_data =${timelineData} ;
        var timeline_config = {
         width: "100%",
         height: "100%",
         lang: "zh-cn",
         source: timeline_data
        }
      </script>
      <script src="scripts/platform/htimelinecontroller/storyjs-embed.js" type="text/javascript"></script>
      <!-- END Timeline Embed-->
  
</body></html>