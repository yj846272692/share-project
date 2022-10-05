import 'package:flutter/material.dart';
import 'package:share_app/model/User.dart';
import 'package:share_app/route/page.dart';
import 'package:share_app/util/cache.dart';

import '../main.dart';

class MinePage extends StatefulWidget {
  const MinePage({Key? key}) : super(key: key);

  @override
  State<MinePage> createState() => _MinePageState();
}

class _MinePageState extends State<MinePage> {
  User? user;

  getInfo() async {
    if (user == null) {
      int? id = await Cache.getInt('id');
      String? token = await Cache.getString('token');
      Map data = await request.get(
        'users/$id',
        headers: {'X-Token': token?.replaceAll(RegExp('"'), "")},
      );
      setState(() {
        user = User.fromJson(data);
      });

      await Cache.saveObject('user', user);
    }
  }

  @override
  void didChangeDependencies() {
    super.didChangeDependencies();
    getInfo();
  }

  @override
  Widget build(BuildContext context) {
    return user != null
        ? Container(
            padding: EdgeInsets.all(20),
            child: Center(
              child: Column(
                children: [
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      CircleAvatar(
                        radius: 40,
                        backgroundImage: NetworkImage(
                          user?.avatar.toString() ?? '',
                        ),
                      ),
                      SizedBox(
                        width: 20,
                      ),
                      Text(
                        user?.nickname ?? '',
                        style: Theme.of(context).textTheme.headline4,
                      ),
                    ],
                  ),
                  SizedBox(
                    height: 20,
                  ),
                  Text(
                    '积分： ${user?.bonus.toString()}',
                    style: Theme.of(context).textTheme.headline6,
                  ),
                  user == null
                      ? Container()
                      : ElevatedButton(
                          onPressed: () {
                            showDialog(
                                context: context,
                                builder: (c) {
                                  return AlertDialog(
                                    title: Center(
                                      child: Text('是否退出'),
                                    ),
                                    actions: [
                                      TextButton(
                                          onPressed: () {
                                            Cache.clear();
                                            R.to(c, '/login');
                                          },
                                          child: Text('是')),
                                      TextButton(
                                          onPressed: () {
                                            R.pop(c);
                                          },
                                          child: Text('否'))
                                    ],
                                  );
                                });
                          },
                          child: Text('退出登录'))
                ],
              ),
            ),
          )
        : Center(
            child: ElevatedButton(
                onPressed: () {
                  R.to(context, '/login');
                },
                child: Text('去登录')),
          );
  }
}
