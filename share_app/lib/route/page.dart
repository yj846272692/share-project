import 'package:flutter/material.dart';
import 'package:share_app/page/home_page.dart';
import 'package:share_app/page/login_page.dart';
import 'package:share_app/page/mine_page.dart';
import 'package:share_app/page/share_page.dart';

part 'route.dart';

/// 没有设计成嵌套子路由的形式
/// 使用注释分成不同模块
class Pages {
  static const INITIAL = Route.LOGIN;

  static final Map<String, WidgetBuilder> routes = {
    Route.HOME: (_) => HomePage(),
    Route.LOGIN: (_) => LoginPage(),
    Route.MINE: (_) => MinePage(),
    Route.SHARE: (_) => SharePage(),
  };
}

class R {
  /// 路由跳转
  static Future<T?> to<T extends Object?>(BuildContext context, String name,
      {Object? arguments}) {
    return Navigator.of(context).pushNamed(name, arguments: arguments);
  }

  /// 返回
  static void pop<T extends Object?>(BuildContext context, [T? result]) {
    Navigator.pop(context, result);
  }

  /// 获取参数
  static Object? get(BuildContext context) {
    return ModalRoute.of(context)?.settings.arguments;
  }
}
