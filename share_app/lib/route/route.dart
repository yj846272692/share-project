part of 'page.dart';

abstract class Route {
  static const HOME = _Paths.HOME;
  static const LOGIN = _Paths.LOGIN;
  static const SHARE = _Paths.SHARE;
  static const MINE = _Paths.MINE;
}

abstract class _Paths {
  /// 主页
  static const HOME = '/home';

  /// 登录
  static const LOGIN = '/login';

  /// 分享信息
  static const SHARE = '/share';

  /// 我的页面
  static const MINE = '/mine';
}
