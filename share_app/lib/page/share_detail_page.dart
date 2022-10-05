import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:share_app/model/Share.dart';

class ShareDetailPage extends StatefulWidget {
  final Share share;

  const ShareDetailPage({Key? key, required this.share}) : super(key: key);

  @override
  State<ShareDetailPage> createState() => _ShareDetailPageState();
}

class _ShareDetailPageState extends State<ShareDetailPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text(widget.share.title.toString())),
      body: Padding(
        padding: const EdgeInsets.all(10),
        child:  Column(
          children: [
            Container(
              margin: const EdgeInsets.symmetric(
                  horizontal: 10, vertical: 5),
              padding: const EdgeInsets.all(20.0),
              decoration: BoxDecoration(
                boxShadow: [
                  BoxShadow(
                    color: Colors.grey.withOpacity(0.2),
                    spreadRadius: 4,
                    blurRadius: 5,
                    offset: const Offset(0, 7),
                  ),
                ],
                color: CupertinoColors.white,
                borderRadius: BorderRadius.circular(15.0),
              ),
              child: Column(
                mainAxisAlignment: MainAxisAlignment.start,
                crossAxisAlignment: CrossAxisAlignment.center,
                children: [
                  Image.network(
                    widget.share.cover.toString(),
                    filterQuality: FilterQuality.high,
                    height: 200,
                    fit: BoxFit.fitHeight,
                  ),
                  const SizedBox(
                    height: 20.0,
                  ),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      Flexible(
                        child: Text(
                          widget.share.title.toString(),
                          textAlign: TextAlign.center,
                          style: const TextStyle(
                            fontSize: 18.0,
                            fontWeight: FontWeight.bold,
                          ),
                        ),
                      ),
                      const SizedBox(
                        width: 5.0,
                      ),
                      Text(
                        widget.share.price.toString(),
                        style: const TextStyle(
                          fontSize: 18.0,
                          fontWeight: FontWeight.bold,
                        ),
                      ),
                    ],
                  ),
                  const SizedBox(
                    height: 10.0,
                  ),

                  const SizedBox(height: 10),
                  Text(
                    widget.share.summary.toString(),
                    textAlign: TextAlign.center,
                  ),
                ],
              ),
            ),
          ],
        ),
      ),
    );
  }
}
