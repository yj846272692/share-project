import 'package:flutter/material.dart';
import 'package:pull_to_refresh/pull_to_refresh.dart';
import 'package:share_app/theme/themes.dart';
import 'package:share_app/util/http.dart';
import 'package:share_app/util/requet.dart';

import 'route/page.dart';

final GlobalKey<NavigatorState> navigatorKey = GlobalKey<NavigatorState>();

Request request = Request();

void main() {
  request.init(
    baseUrl: 'http://10.40.203.107:10000/api/',
    responseFormat: HttpResponseFormat('code', 'data', 'msg', '1'),
  );
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    /// 刷新配置
    return RefreshConfiguration(
      // 配置默认头部指示器,假如你每个页面的头部指示器都一样的话,你需要设置这个
      headerBuilder: () => const ClassicHeader(
        refreshingText: '',
        idleText: '',
        releaseText: '',
        completeText: '',
      ),
      // 配置默认底部指示器
      footerBuilder: () => const ClassicFooter(
        loadingText: '',
        idleText: '',
        noDataText: '没有更多',
        canLoadingText: '',
      ),
      // 头部触发刷新的越界距离
      // headerTriggerDistance: 80.0,
      // 自定义回弹动画,三个属性值意义请查询flutter api
      // springDescription:
      //     SpringDescription(stiffness: 170, damping: 16, mass: 1.9),
      // 头部最大可以拖动的范围,如果发生冲出视图范围区域,请设置这个属性
      // maxOverScrollExtent: 100,
      // 底部最大可以拖动的范围
      // maxUnderScrollExtent: 0,
      // 这个属性不兼容PageView和TabBarView,如果你特别需要TabBarView左右滑动,你需要把它设置为true
      enableScrollWhenRefreshCompleted: true,
      // 在加载失败的状态下,用户仍然可以通过手势上拉来触发加载更多
      enableLoadingWhenFailed: true,
      // Viewport不满一屏时,禁用上拉加载更多功能
      hideFooterWhenNotFull: false,
      // 可以通过惯性滑动触发加载更多
      enableBallisticLoad: true,
      child: MaterialApp(
        navigatorKey: navigatorKey,
        theme: buildDefaultTheme(),
        builder: (context, child) {
          // 键盘点击空白处收回
          return GestureDetector(
            onTap: () {
              FocusScopeNode currentFocus = FocusScope.of(context);
              if (!currentFocus.hasPrimaryFocus &&
                  currentFocus.focusedChild != null) {
                FocusManager.instance.primaryFocus?.unfocus();
              }
            },
            child: child,
          );
        },

        /// 不显示右上角的debug
        debugShowCheckedModeBanner: false,

        /// 配置路由
        routes: Pages.routes,

        /// 路由初始化
        initialRoute: Pages.INITIAL,
      ),
    );
  }
}
