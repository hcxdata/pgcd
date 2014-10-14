<script type="text/javascript" >
var M = {};
var Main = {};
Main.jsonrpc = {};

//环境变量
Main.contextPath = '<%=request.getContextPath()%>';

//jsonrpc信息
Main.jsonrpc.path = "<%=request.getContextPath()%>/JSONRPC.do";
Main.jsonrpc.jsonRpcClient = new JSONRpcClient(Main.jsonrpc.path);

//常用变量
M.rpc =  Main.jsonrpc.jsonRpcClient;
M.rpc.path = Main.jsonrpc.path;

</script>