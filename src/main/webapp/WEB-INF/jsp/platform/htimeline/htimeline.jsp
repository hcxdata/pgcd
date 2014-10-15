<html>
    <head>
        <meta content="text/html; charset=utf-8" http-equiv="Content-Type">
        <link rel="stylesheet" type="text/css" href="styles/platform/htimelinecontroller/demo.css" />
        <link rel="stylesheet" type="text/css" href="styles/platform/htimelinecontroller/timeline.css" />
        <script type="text/javascript" src="scripts/jqurey/jquery.js"></script>
        <script type="text/javascript" src="scripts/platform/htimelinecontroller/timeline.js"></script>
    </head>
    <body>
        <div id="timeline" style="width:80%;max-width:800px;margin:auto;"></div>
        <script type="text/javascript">
            $(document).ready(function() {
                var timeline_data =${timelineData} ;

                var timeline = new Timeline($('#timeline'), timeline_data);
                timeline.setOptions({
                    animation:   true,
                    lightbox:    true,
                    separator:   'day',
                    columnMode:  'dual',
                    order:       'asc',
                    first_separator:  true,
                    max:         null
                });
                timeline.display();
            });
        </script>
    </body>
</html>

