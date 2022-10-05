import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:pull_to_refresh/pull_to_refresh.dart';
import 'package:share_app/main.dart';
import 'package:share_app/model/Share.dart';
import 'package:share_app/model/User.dart';
import 'package:share_app/page/share_detail_page.dart';
import 'package:share_app/util/cache.dart';

class SharePage extends StatefulWidget {
  const SharePage({Key? key}) : super(key: key);

  @override
  State<SharePage> createState() => _SharePageState();
}

class _SharePageState extends State<SharePage> {
  User? user;
  final RefreshController _refreshController =
      RefreshController(initialRefresh: false);
  int _page = 0;
  int _size = 5;

  Future<List<Share>>? data;

  void _onRefresh() async {
    await Future.delayed(Duration(milliseconds: 1000));
    _page = 0;
    _size = 5;
    setState(() {
      data = getData(_page, _size);
    });
    _refreshController.refreshCompleted();
    _refreshController.loadComplete();
  }

  void _onLoading() async {
    await Future.delayed(Duration(milliseconds: 1000));
    List<Share>? futureData = await data;
    List<Share> moreData = await getData(++_page, _size);
    setState(() {
      futureData?.addAll(moreData);
      if (moreData.isEmpty) {
        _refreshController.loadNoData();
      } else {
        _refreshController.loadComplete();
      }
    });
  }

  @override
  void initState() {
    setState(() {
      data = getData(_page, _size);
      _refreshController.loadComplete();
    });
  }

  Future<List<Share>> getData(page, size) async {
    String? token = await Cache.getString('token');
    List data = await request.get(
      '/shares/all',
      params: {'page': page, 'size': size},
      headers: {'X-Token': token?.replaceAll(RegExp('"'), "")},
    );
    return data.map((e) => Share.fromJson(e)).toList();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: FutureBuilder(
          future: data,
          builder: (context, AsyncSnapshot snapshot) {
            if (snapshot.hasData) {
              List<Share> shares = snapshot.data;
              return SizedBox(
                height: snapshot.data.length * 211.toDouble(),
                child: SmartRefresher(
                  controller: _refreshController,
                  enablePullUp: true,
                  onRefresh: _onRefresh,
                  onLoading: _onLoading,
                  child: ListView.builder(
                      // physics: const NeverScrollableScrollPhysics(),
                      itemCount: snapshot.data.length,
                      itemBuilder: (context, index) {
                        return InkWell(
                          onTap: () {
                            Navigator.of(context).push(MaterialPageRoute(
                                builder: (context) =>
                                    ShareDetailPage(share: shares[index])));
                          },
                          child: SizedBox(
                            height: 200,
                            width: MediaQuery.of(context).size.width,
                            child: Container(
                              decoration: BoxDecoration(
                                borderRadius: BorderRadius.circular(10),
                                color: Colors.white,
                              ),
                              margin: const EdgeInsets.symmetric(
                                  horizontal: 12, vertical: 12),
                              child: Row(
                                children: [
                                  Stack(
                                    children: [
                                      Padding(
                                        padding: const EdgeInsets.only(
                                            top: 15,
                                            bottom: 15,
                                            left: 10,
                                            right: 10),
                                        child: Container(
                                          width: 130,
                                          height: 130,
                                          decoration: BoxDecoration(
                                              borderRadius:
                                                  BorderRadius.circular(20)),
                                          // padding: EdgeInsets.all(25),
                                          child: ClipRRect(
                                            borderRadius:
                                                BorderRadius.circular(20),
                                            child: FittedBox(
                                              alignment: Alignment.center,
                                              fit: BoxFit.cover,
                                              clipBehavior: Clip.hardEdge,
                                              child: Image.network(shares[index]
                                                  .cover
                                                  .toString()),
                                            ),
                                          ),
                                        ),
                                      ),
                                      Positioned(
                                        top: 5,
                                        left: 0,
                                        child: Container(
                                          padding: EdgeInsets.all(5),
                                          decoration: BoxDecoration(
                                              borderRadius:
                                                  BorderRadius.circular(10),
                                              color:
                                                  shares[index].isOriginal != 1
                                                      ? Colors.orange
                                                      : Colors.blue),
                                          child: Text(
                                              shares[index].isOriginal != 1
                                                  ? '转载'
                                                  : '原创'),
                                        ),
                                      ),
                                    ],
                                  ),
                                  Align(
                                    alignment: Alignment.topCenter,
                                    child: Column(
                                      children: [
                                        Container(
                                          padding: const EdgeInsets.symmetric(
                                              vertical: 10),
                                          width: 180,
                                          margin:
                                              const EdgeInsets.only(top: 30),
                                          child: DefaultTextStyle(
                                            style: const TextStyle(
                                                fontSize: 16,
                                                color: Colors.black87,
                                                overflow: TextOverflow.fade),
                                            child: Text(
                                              shares[index].title.toString(),
                                              maxLines: 4,
                                              style: const TextStyle(
                                                  overflow: TextOverflow.clip),
                                            ),
                                          ),
                                        ),
                                        Align(
                                          alignment: Alignment.bottomLeft,
                                          child: SizedBox(
                                            width: 180,
                                            child: DefaultTextStyle(
                                              style: const TextStyle(
                                                fontSize: 12,
                                                overflow: TextOverflow.fade,
                                                color: Colors.grey,
                                              ),
                                              child: Text(
                                                  '${shares[index].author}  |   ${shares[index].buyCount}'),
                                            ),
                                          ),
                                        ),
                                        Container(
                                          padding: EdgeInsets.all(8),
                                          width: 200,
                                          child: Text(
                                            shares[index].summary.toString(),
                                            maxLines: 2,
                                          ),
                                        )
                                      ],
                                    ),
                                  ),
                                ],
                              ),
                            ),
                          ),
                        );
                      }),
                ),
              );
            } else {
              return const CupertinoActivityIndicator();
            }
          },
        ),
      ),
    );
  }
}
