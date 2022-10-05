import 'dart:convert';
/// id : 1
/// content : "关注编程世界，发现更多精彩"
/// cover : "https://ruerjiefei.oss-cn-hangzhou.aliyuncs.com/49vrxu4gwmq0000.jpeg"
/// url : "https://www.bilibili.com/"
/// showFlag : true
/// createTime : "2022-09-01 12:36:49"

Notice noticeFromJson(String str) => Notice.fromJson(json.decode(str));
String noticeToJson(Notice data) => json.encode(data.toJson());
class Notice {
  Notice({
      num? id, 
      String? content, 
      String? cover, 
      String? url, 
      bool? showFlag, 
      String? createTime,}){
    _id = id;
    _content = content;
    _cover = cover;
    _url = url;
    _showFlag = showFlag;
    _createTime = createTime;
}

  Notice.fromJson(dynamic json) {
    _id = json['id'];
    _content = json['content'];
    _cover = json['cover'];
    _url = json['url'];
    _showFlag = json['showFlag'];
    _createTime = json['createTime'];
  }
  num? _id;
  String? _content;
  String? _cover;
  String? _url;
  bool? _showFlag;
  String? _createTime;
Notice copyWith({  num? id,
  String? content,
  String? cover,
  String? url,
  bool? showFlag,
  String? createTime,
}) => Notice(  id: id ?? _id,
  content: content ?? _content,
  cover: cover ?? _cover,
  url: url ?? _url,
  showFlag: showFlag ?? _showFlag,
  createTime: createTime ?? _createTime,
);
  num? get id => _id;
  String? get content => _content;
  String? get cover => _cover;
  String? get url => _url;
  bool? get showFlag => _showFlag;
  String? get createTime => _createTime;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['id'] = _id;
    map['content'] = _content;
    map['cover'] = _cover;
    map['url'] = _url;
    map['showFlag'] = _showFlag;
    map['createTime'] = _createTime;
    return map;
  }

}