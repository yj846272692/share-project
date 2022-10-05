import 'dart:convert';
/// id : 1
/// userId : 1
/// title : "Spring Cloud Alibaba"
/// createTime : "2020-10-15 21:13:44"
/// updateTime : "2020-10-15 21:13:48"
/// isOriginal : 1
/// author : "阿里巴巴"
/// cover : "https://img2.sycdn.imooc.com/szimg/5b3082da0001d7e905400300-360-202.jpg"
/// summary : "SpringCloudAlibaba微服务全家桶系列组件"
/// price : 30
/// downloadUrl : "链接: https://pan.baidu.com/s/1Hk9i9VOTz2eSuy8p-kFGrQ  密码: 5njn"
/// buyCount : 5
/// showFlag : 1
/// auditStatus : "PASS"
/// reason : "通过审核"

Share shareFromJson(String str) => Share.fromJson(json.decode(str));
String shareToJson(Share data) => json.encode(data.toJson());
class Share {
  Share({
      num? id, 
      num? userId, 
      String? title, 
      String? createTime, 
      String? updateTime, 
      num? isOriginal, 
      String? author, 
      String? cover, 
      String? summary, 
      num? price, 
      String? downloadUrl, 
      num? buyCount, 
      bool? showFlag,
      String? auditStatus, 
      String? reason,}){
    _id = id;
    _userId = userId;
    _title = title;
    _createTime = createTime;
    _updateTime = updateTime;
    _isOriginal = isOriginal;
    _author = author;
    _cover = cover;
    _summary = summary;
    _price = price;
    _downloadUrl = downloadUrl;
    _buyCount = buyCount;
    _showFlag = showFlag;
    _auditStatus = auditStatus;
    _reason = reason;
}

  Share.fromJson(dynamic json) {
    _id = json['id'];
    _userId = json['userId'];
    _title = json['title'];
    _createTime = json['createTime'];
    _updateTime = json['updateTime'];
    _isOriginal = json['isOriginal'];
    _author = json['author'];
    _cover = json['cover'];
    _summary = json['summary'];
    _price = json['price'];
    _downloadUrl = json['downloadUrl'];
    _buyCount = json['buyCount'];
    _showFlag = json['showFlag'];
    _auditStatus = json['auditStatus'];
    _reason = json['reason'];
  }
  num? _id;
  num? _userId;
  String? _title;
  String? _createTime;
  String? _updateTime;
  num? _isOriginal;
  String? _author;
  String? _cover;
  String? _summary;
  num? _price;
  String? _downloadUrl;
  num? _buyCount;
  bool? _showFlag;
  String? _auditStatus;
  String? _reason;
Share copyWith({  num? id,
  num? userId,
  String? title,
  String? createTime,
  String? updateTime,
  num? isOriginal,
  String? author,
  String? cover,
  String? summary,
  num? price,
  String? downloadUrl,
  num? buyCount,
  bool? showFlag,
  String? auditStatus,
  String? reason,
}) => Share(  id: id ?? _id,
  userId: userId ?? _userId,
  title: title ?? _title,
  createTime: createTime ?? _createTime,
  updateTime: updateTime ?? _updateTime,
  isOriginal: isOriginal ?? _isOriginal,
  author: author ?? _author,
  cover: cover ?? _cover,
  summary: summary ?? _summary,
  price: price ?? _price,
  downloadUrl: downloadUrl ?? _downloadUrl,
  buyCount: buyCount ?? _buyCount,
  showFlag: showFlag ?? _showFlag,
  auditStatus: auditStatus ?? _auditStatus,
  reason: reason ?? _reason,
);
  num? get id => _id;
  num? get userId => _userId;
  String? get title => _title;
  String? get createTime => _createTime;
  String? get updateTime => _updateTime;
  num? get isOriginal => _isOriginal;
  String? get author => _author;
  String? get cover => _cover;
  String? get summary => _summary;
  num? get price => _price;
  String? get downloadUrl => _downloadUrl;
  num? get buyCount => _buyCount;
  bool? get showFlag => _showFlag;
  String? get auditStatus => _auditStatus;
  String? get reason => _reason;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['id'] = _id;
    map['userId'] = _userId;
    map['title'] = _title;
    map['createTime'] = _createTime;
    map['updateTime'] = _updateTime;
    map['isOriginal'] = _isOriginal;
    map['author'] = _author;
    map['cover'] = _cover;
    map['summary'] = _summary;
    map['price'] = _price;
    map['downloadUrl'] = _downloadUrl;
    map['buyCount'] = _buyCount;
    map['showFlag'] = _showFlag;
    map['auditStatus'] = _auditStatus;
    map['reason'] = _reason;
    return map;
  }

}