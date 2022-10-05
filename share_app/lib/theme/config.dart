import 'package:flutter/material.dart';

/// FileName config
///
/// @Author mqxu
/// @Date 2022/9/29 09:03
///
/// @Description 全局配置类
class Config {
  /// 主题颜色
  static const int _primaryColorValue = 0xFFfec7d7;
  static const Color primaryColor = Color(_primaryColorValue);
  static const MaterialColor primarySwatchColor = MaterialColor(
    _primaryColorValue,
    <int, Color>{
      50: Color(0xFFFFE7E7),
      100: Color(0xFFFFBEBE),
      200: Color(0xFFDE8C8C),
      300: Color(0xFFDE8C8C),
      400: Color(0xFFF9B9BE),
      500: Color(_primaryColorValue),
      600: Color(0xFFE2A8BD),
      700: Color(0xFFC37B94),
      800: Color(0xFFBC7795),
      900: Color(0xFFAF6E95),
    },
  );
}
