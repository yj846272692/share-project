import 'dart:async';

import 'package:carousel_slider/carousel_slider.dart';
import 'package:flutter/material.dart';
import 'package:share_app/main.dart';
import 'package:share_app/model/Notice.dart';
import 'package:share_app/theme/config.dart';

class Carousel extends StatefulWidget {
  const Carousel({Key? key}) : super(key: key);

  @override
  State<Carousel> createState() => _CarouselState();
}

class _CarouselState extends State<Carousel> {
  List<Notice> notices = [];

  GlobalKey dynamicIndexKey = GlobalKey();

  ScrollController dynamicScrollController =
      ScrollController(initialScrollOffset: 0);

  int dynamicIndex = 0;

  late Timer dynamicTimer;

  getNotice() async {
    List data = await request.get('/notices/all');
    setState(() {
      notices = data.map((e) => Notice.fromJson(e)).toList();
    });
  }

  /// 动态列表滚动
  void listScroll() {
    // 判断列表是否存在
    // currentContext构建有些问题
    // if (dynamicIndexKey.currentContext != null) {
    dynamicTimer = Timer.periodic(Duration(seconds: 5), (timer) {
      dynamicIndex += dynamicIndexKey.currentContext!.size!.height.toInt();
      dynamicScrollController.animateTo((dynamicIndex).toDouble(),
          duration: Duration(seconds: 6), curve: Curves.easeOutSine);
      // 滚动到底部从头开始
      if ((dynamicIndex - dynamicIndexKey.currentContext!.size!.height.toInt())
          .toDouble() >
          dynamicScrollController.position.maxScrollExtent) {
        dynamicScrollController
            .jumpTo(dynamicScrollController.position.minScrollExtent);
        dynamicIndex = 0;
      }
    });
    // }
  }

  @override
  void initState() {
    super.initState();
    getNotice();
    listScroll();
  }

  @override
  Widget build(BuildContext context) {
    return Center(
      child: Column(
        children: [
          dynamic(),
          CarouselSlider(
            options: CarouselOptions(
              height: 400.0,
              autoPlay: true,
              autoPlayInterval: Duration(seconds: 3),
              autoPlayAnimationDuration: Duration(milliseconds: 800),
              autoPlayCurve: Curves.fastOutSlowIn,
              enlargeCenterPage: true,
            ),
            items: notices.map((i) {
              return Builder(
                builder: (BuildContext context) {
                  return Container(
                      width: MediaQuery.of(context).size.width,
                      margin: EdgeInsets.symmetric(horizontal: 5.0),
                      decoration: BoxDecoration(
                          color: Config.primarySwatchColor.shade400),
                      child: Align(
                          alignment: Alignment.bottomCenter,
                          child: Image.network(
                            i.cover.toString(),
                            height: 500,
                            fit: BoxFit.cover,
                          )));
                },
              );
            }).toList(),
          ),
        ],
      ),
    );
  }

  Widget dynamic() {
    return Container(
      width: 220,
      height: 130,
      child: ListView.builder(
        key: dynamicIndexKey,
        //禁止手动滑动
        physics: NeverScrollableScrollPhysics(),
        itemCount: 3,
        //item固定高度
        itemExtent: 320,
        scrollDirection: Axis.horizontal,
        controller: dynamicScrollController,
        itemBuilder: (context, index) {
          return Container(
              alignment: Alignment.centerLeft,
              padding: EdgeInsets.only(left: 35),
              child: Row(
                children: [
                  Expanded(
                    child: Text(
                      '可更换IP,年付赠送2张商用证书',
                      overflow: TextOverflow.ellipsis,
                      maxLines: 1,
                      style: TextStyle(
                        fontSize: 20,
                      ),
                    ),
                  ),
                ],
              ));
        },
      ),
    );
  }
}
