/**
 * @User:lhy
 * @Time:2018/1/29
 * dataTable初始化文件
 */

/*
  id:data_tables
  url:ajax请求地址
  columns:对应的数据列
  [
      {data: null},
      {data: "id"},
      {data: "name"}
  ],
  columnDefs:列渲染，可以添加一些操作等
  [
      {
      //表示第0列
      targets: 0,
      //渲染函数
      render: function (data) {
          return "<td><input type=\"checkbox\" name=\"cb\" value=\"" + data.id + "\"/></td>";
      }
      }, {
          //禁用排序
          orderable: false,
          //指定的列
          targets: [0]
      }
  ],
 */
function dataTables(url, columns, columnDefs) {
    /*表格初始化_服务器模式*/
    $('#data_tables').dataTable({
        //确保有只有一个dataTables实例
        retrieve: true,
        //开启服务器模式
        serverSide: true,
        //设置为true,就会有表格加载时的提示
        processing: true,
        ajax: {
            url: url,
            type: 'POST'
        },
        //对应的数据列
        columns: columns,
        //列渲染，可以添加一些操作等
        columnDefs: columnDefs,
        //国际化
        language: {
            //这里设置就是在加载时给用户的提示
            sProcessing: "处理中...",
            sLengthMenu: "显示 _MENU_ 项结果",
            sZeroRecords: "没有匹配结果",
            sInfo: "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
            sInfoEmpty: "显示第 0 至 0 项结果，共 0 项",
            sInfoFiltered: "(由 _MAX_ 项结果过滤)",
            sInfoPostFix: "",
            sSearch: "搜索:",
            sUrl: "",
            sEmptyTable: "表中数据为空",
            sLoadingRecords: "载入中...",
            sInfoThousands: ",",
            oPaginate: {
                "sFirst": "首页",
                "sPrevious": "上页",
                "sNext": "下页",
                "sLast": "末页"
            },
            oAria: {
                "sSortAscending": ": 以升序排列此列",
                "sSortDescending": ": 以降序排列此列"
            }
        }
    });
}

/*自定义搜索页面使用*/
function dataTablesSearch(url, columns, columnDefs) {
    $.extend( $('#data_tables').dataTable.defaults, {
        "searching": false,
    } );
    /*表格初始化_服务器模式*/
    $('#data_tables').dataTable({
        //确保有只有一个dataTables实例
        retrieve: true,
        //开启服务器模式
        serverSide: true,
        //设置为true,就会有表格加载时的提示
        processing: true,
        ajax: {
            url: url,
            type: 'POST'
        },
        //对应的数据列
        columns: columns,
        //列渲染，可以添加一些操作等
        columnDefs: columnDefs,
        //国际化
        language: {
            //这里设置就是在加载时给用户的提示
            sProcessing: "处理中...",
            sLengthMenu: "显示 _MENU_ 项结果",
            sZeroRecords: "没有匹配结果",
            sInfo: "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
            sInfoEmpty: "显示第 0 至 0 项结果，共 0 项",
            sInfoFiltered: "(由 _MAX_ 项结果过滤)",
            sInfoPostFix: "",
            sSearch: "搜索:",
            sUrl: "",
            sEmptyTable: "表中数据为空",
            sLoadingRecords: "载入中...",
            sInfoThousands: ",",
            oPaginate: {
                "sFirst": "首页",
                "sPrevious": "上页",
                "sNext": "下页",
                "sLast": "末页"
            },
            oAria: {
                "sSortAscending": ": 以升序排列此列",
                "sSortDescending": ": 以降序排列此列"
            }
        }
    });
}
